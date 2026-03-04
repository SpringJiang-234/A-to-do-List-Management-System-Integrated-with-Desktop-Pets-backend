package com.backend.config;

import com.backend.bean.ResultBean;
import com.backend.domain.entity.User;
import com.backend.service.UserService;
import com.backend.utils.JsonUtil;
import com.backend.utils.RedisUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 权限验证拦截器
 * 用于验证用户角色和权限
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求路径
        String requestURI = request.getRequestURI();
        logger.info("Request URI: {}", requestURI);

        // 不需要验证的路径（如登录、注册等）
        if (requestURI.contains("/login") || requestURI.contains("/register") || requestURI.contains("/accountExist")) {
            logger.info("跳过验证: {}", requestURI);
            return true;
        }

        // 获取token
        String token = request.getHeader("Authorization");
        logger.info("Authorization token: {}", token);
        if (token == null || token.isEmpty()) {
            logger.warn("Token为空，拒绝访问: {}", requestURI);
            sendError(response, "未登录或token已过期");
            return false;
        }

        // 从Redis获取用户ID
        Object userIdObj = null;
        try {
            logger.info("从Redis获取用户ID，key: {}", "token:" + token);
            userIdObj = redisUtil.get("token:" + token);
            logger.info("Redis返回结果: {}", userIdObj);
        } catch (Exception e) {
            logger.error("Redis连接失败，无法验证token", e);
            sendError(response, "服务器内部错误");
            return false;
        }

        if (userIdObj == null) {
            logger.warn("Redis中未找到token对应的用户ID: {}", token);
            sendError(response, "未登录或token已过期");
            return false;
        }

        // 获取用户信息
        Long userId = Long.valueOf(userIdObj.toString());
        logger.info("获取用户信息，用户ID: {}", userId);
        User user = userService.getById(userId);
        if (user == null) {
            logger.warn("用户不存在，用户ID: {}", userId);
            sendError(response, "用户不存在");
            return false;
        }

        logger.info("用户信息: {}, 类型: {}", user.getAccount(), user.getType());

        // 验证角色权限
        if (requestURI.startsWith("/admin")) {
            // 管理员路径需要管理员权限
            logger.info("验证管理员权限，用户类型: {}", user.getType());
            if (user.getType() != 1) {
                logger.warn("无权限访问管理员资源，用户类型: {}", user.getType());
                sendError(response, "无权限访问管理员资源");
                return false;
            }
            logger.info("管理员权限验证通过");
        } else if (requestURI.startsWith("/client")) {
            // 客户端路径普通用户可以访问
            // 管理员也可以访问客户端路径
            logger.info("客户端路径，允许访问");
        } else if (requestURI.startsWith("/common")) {
            // 公共路径所有用户都可以访问
            logger.info("公共路径，允许访问");
        }

        // 将用户信息存储到请求中，方便后续使用
        request.setAttribute("user", user);
        logger.info("权限验证通过，允许访问: {}", requestURI);
        return true;
    }

    /**
     * 发送错误响应
     */
    private void sendError(HttpServletResponse response, String message) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        ResultBean<Void> result = ResultBean.error(message, null);
        out.write(JsonUtil.OBJECT_MAPPER.writeValueAsString(result));
        out.flush();
        out.close();
    }
}