package com.backend.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 从请求中获取当前登录用户的ID
     *
     * @param request HTTP请求对象
     * @return 用户ID，如果验证失败返回null
     */
    public Long getCurrentUserId(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return null;
        }

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        Object userIdObj = redisUtil.get("token:" + token);
        if (userIdObj == null) {
            return null;
        }

        return Long.valueOf(userIdObj.toString());
    }
}
