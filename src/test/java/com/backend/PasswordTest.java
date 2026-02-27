package com.backend;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试使用 jbcrypt 进行 Bcrypt 密码哈希与验证
 */
class PasswordTest {

    /**
     * 测试密码加密与正确验证
     */
    @Test
    void testHashAndVerifySuccess() {
        String rawPassword = "123456abc";

        // 1. 生成哈希密码
        String hashed = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
        System.out.println("Generated hash: " + hashed);

        // 2. 验证正确的密码应该返回 true
        assertTrue(BCrypt.checkpw(rawPassword, hashed), "密码验证应该成功");
    }

    /**
     * 测试错误密码验证失败
     */
    @Test
    void testVerifyWithWrongPassword() {
        String rawPassword = "mySecret123";
        String wrongPassword = "wrongPassword";

        // 生成哈希
        String hashed = BCrypt.hashpw(rawPassword, BCrypt.gensalt());

        // 验证错误的密码应该返回 false
        assertFalse(BCrypt.checkpw(wrongPassword, hashed), "错误密码验证应该失败");
    }

    /**
     * 测试不同的原始密码生成不同的哈希值（即使明文相同，因随机盐不同也会不同）
     */
    @Test
    void testSamePasswordDifferentHashes() {
        String password = "samePassword";

        String hash1 = BCrypt.hashpw(password, BCrypt.gensalt());
        String hash2 = BCrypt.hashpw(password, BCrypt.gensalt());

        // 两次生成的哈希应该不同（因为盐不同）
        assertNotEquals(hash1, hash2, "相同密码的两次哈希应不相同");

        // 但两次验证都应该成功
        assertTrue(BCrypt.checkpw(password, hash1));
        assertTrue(BCrypt.checkpw(password, hash2));
    }

    /**
     * 测试指定强度的哈希生成（强度默认为10，可自定义）
     */
    @Test
    void testHashWithCustomStrength() {
        String password = "123456";

        // 使用强度 12 (成本因子，越高越慢)
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
        System.out.println(hashed);

        // 验证
        assertTrue(BCrypt.checkpw(password, hashed));
    }
}