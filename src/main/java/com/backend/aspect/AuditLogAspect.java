package com.backend.aspect;

import com.backend.domain.entity.AuditLog;
import com.backend.domain.entity.User;
import com.backend.service.AuditLogService;
import com.backend.service.UserService;
import com.backend.utils.RedisUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

/**
 * 审计日志切面
 * 自动记录除查询外的所有管理员操作
 */
@Aspect
@Component
public class AuditLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(AuditLogAspect.class);

    @Autowired
    private AuditLogService auditLogService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 定义切点：拦截admin控制器的所有方法，除了查询相关的方法
     */
    @Pointcut("execution(* com.backend.controller.admin.*Controller.*(..)) && !execution(* com.backend.controller.admin.*Controller.*page*(..)) && !execution(* com.backend.controller.admin.*Controller.*get*(..)) && !execution(* com.backend.controller.admin.*Controller.*export*(..))")
    public void auditLogPointcut() {
    }

    /**
     * 后置通知：方法执行成功后记录审计日志
     */
    @AfterReturning(pointcut = "auditLogPointcut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        logger.info("开始记录审计日志...");
        // 获取请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            logger.warn("请求信息为空，跳过审计日志记录");
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        logger.info("请求路径: {}", request.getRequestURI());

        // 获取操作人信息
        String token = request.getHeader("Authorization");
        if (token == null) {
            logger.warn("请求头中没有Authorization token，跳过审计日志记录");
            return;
        }
        // 移除token中的"Bearer "前缀
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        logger.info("获取到token: {}", token);

        // 从Redis中获取用户ID
        Long userId = 1L; // 默认用户ID
        try {
            Object userIdObj = redisUtil.get("token:" + token);
            if (userIdObj != null) {
                userId = Long.valueOf(userIdObj.toString());
                logger.info("从Redis中获取到用户ID: {}", userId);
            } else {
                logger.warn("Redis中没有找到用户信息，使用默认用户ID: {}", userId);
            }
        } catch (Exception e) {
            logger.warn("Redis连接失败，使用默认用户ID: {}", userId);
        }

        // 获取用户信息
        User user = userService.getById(userId);
        String username = user != null ? user.getNickname() : "未知用户";
        logger.info("获取到用户名: {}", username);

        // 构建审计日志对象
        AuditLog auditLog = new AuditLog();
        auditLog.setUserId(userId);
        auditLog.setUsername(username);
        auditLog.setIpAddress(getClientIp(request));
        auditLog.setModule(getModuleName(joinPoint));
        auditLog.setOperation(getOperationName(joinPoint));
        auditLog.setCreateTime(LocalDateTime.now());

        // 保存审计日志
        int resultCode = auditLogService.insertOrUpdate(auditLog);
        logger.info("审计日志保存结果: {}", resultCode > 0 ? "成功" : "失败");
        logger.info("审计日志记录完成");
    }

    /**
     * 获取客户端IP地址
     */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取模块名称
     */
    private String getModuleName(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        return className.substring(className.lastIndexOf(".") + 1).replace("Controller", "");
    }

    /**
     * 获取操作名称
     */
    private String getOperationName(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        switch (methodName) {
            case "insert":
                return "添加";
            case "update":
                return "修改";
            case "delete":
                return "删除";
            case "batchDelete":
                return "批量删除";
            case "importData":
                return "导入";
            case "register":
                return "注册";
            case "login":
                return "登录";
            case "logout":
                return "登出";
            default:
                return methodName;
        }
    }
}
