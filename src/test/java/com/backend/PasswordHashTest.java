package com.backend;

import com.backend.mapper.UserMapper;
import com.backend.domain.query.UserQuery;
import com.backend.utils.PasswordUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PasswordHashTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testPasswordHash() {
        // 测试生成密码哈希
        String rawPassword = "123456abc";
        String hashedPassword = PasswordUtil.hashPassword(rawPassword);
        System.out.println("Generated hash: " + hashedPassword);
        System.out.println("Hash length: " + hashedPassword.length());

        // 测试密码验证
        assertTrue(PasswordUtil.checkPassword(rawPassword, hashedPassword));
        assertFalse(PasswordUtil.checkPassword("wrongpassword", hashedPassword));
    }

    @Test
    void testDatabasePassword() {
        // 从数据库中获取用户信息
        UserQuery query = new UserQuery();
        query.setAccount("winxp");
        var users = userMapper.selectWithCondition(query);
        if (!users.isEmpty()) {
            var user = users.get(0);
            System.out.println("User account: " + user.getAccount());
            System.out.println("Password hash from database: " + user.getPasswordHash());
            System.out.println("Password hash length: " + user.getPasswordHash().length());

            // 测试密码验证
            boolean result = PasswordUtil.checkPassword("123456abc", user.getPasswordHash());
            System.out.println("Password validation result: " + result);
        } else {
            System.out.println("User 'winxp' not found");
        }
    }
}
