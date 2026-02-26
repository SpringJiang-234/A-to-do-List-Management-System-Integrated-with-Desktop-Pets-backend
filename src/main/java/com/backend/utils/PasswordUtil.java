package com.backend.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // 1. 密码加密 (生成器)
    public static String hashPassword(String plainTextPassword) {
        // gensalt() 方法的参数是强度 (log_rounds)，默认为10
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(plainTextPassword, salt);
    }

    // 2. 密码验证 (验证器)
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    public static void main(String[] args) {
        String password = "mySecret123";
        String hashed = hashPassword(password);
        System.out.println("Hashed: " + hashed);

        // 验证正确密码
        System.out.println("Match: " + checkPassword(password, hashed));
        // 验证错误密码
        System.out.println("Match: " + checkPassword(password + "wrong", hashed));
    }
}