package com.backend.utils;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.regex.Pattern;

/**
 * 字符串工具类 - 提供高效、安全的字符串处理方法
 * @author hc
 * @date 2025-09-05 14:22
 */
public final class StringUtil {
    // 字符池，使用不可变字符数组提高性能
    private static final char[] CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    // 安全的随机数生成器
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    // 邮箱格式正则表达式
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    // 手机号格式正则表达式（中国大陆）
    private static final Pattern PHONE_PATTERN = Pattern.compile("^1[3-9]\\d{9}$");

    private StringUtil() {
        // 禁止实例化工具类
        throw new AssertionError("工具类不允许实例化");
    }

    /**
     * 生成指定长度的随机安全字符串
     *
     * @param length 长度
     * @return 随机字符串
     * @throws IllegalArgumentException 当长度小于等于0时抛出
     */
    public static String genStr(Integer length) {
        if (length == null || length <= 0) {
            throw new IllegalArgumentException("生成的字符串长度必须大于0");
        }

        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            // 使用安全随机数生成器
            builder.append(CHARS[SECURE_RANDOM.nextInt(CHARS.length)]);
        }
        return builder.toString();
    }

    /**
     * 生成指定长度的随机安全token
     *
     * @param length 长度
     * @return Base64编码的随机token
     * @throws IllegalArgumentException 当长度小于等于0时抛出
     */
    public static String genSecureToken(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("生成的token长度必须大于0");
        }

        byte[] tokenBytes = new byte[length];
        SECURE_RANDOM.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }

    /**
     * 检查给定的字符串是否为空或空字符串
     * 此方法用于判断字符串是否为null或者长度为0，从而统一处理空字符串和null值，避免空指针异常
     *
     * @param str 要检查的字符串，可以为null
     * @return 如果字符串为null或长度为0，则返回true；否则返回false
     */
    public static boolean isEmpty(final String str) {
        // 检查字符串是否为null或长度是否为0
        return str == null || str.isEmpty();
    }

    /**
     * 检查给定的字符串是否不为空
     *
     * @param str 待检查的字符串
     * @return 如果字符串不为空，则返回true；否则返回false
     */
    public static boolean isNotEmpty(final String str) {
        return !isEmpty(str);
    }

    /**
     * 判断一个字符串是否为空或仅由空白字符组成
     * 与isEmpty不同，isBlank认为字符串中只包含空白字符也是空的
     *
     * @param str 待检查的字符串
     * @return 如果字符串为null、空或仅由空白字符组成，则返回true；否则返回false
     */
    public static boolean isBlank(final String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotBlank(final String str) {
        return !isBlank(str);
    }

    /**
     * 将字符串的首字母转换为小写
     *
     * @param str 输入字符串
     * @return 首字母小写的字符串，如果输入为空则返回原字符串
     */
    public static String lowerCaseFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        char[] chars = str.toCharArray();
        if (chars[0] >= 'A' && chars[0] <= 'Z') {
            // 使用位运算替代加法，提高性能
            chars[0] ^= 32;
        }
        return new String(chars);
    }

    /**
     * 首字母大写
     *
     * @param str 字符串
     * @return 首字母大写的字符串
     */
    public static String firstLetterToUpper(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        char[] chars = str.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            // 使用位运算替代减法，提高性能
            chars[0] ^= 32;
        }
        return new String(chars);
    }

    /**
     * 安全的字符串比较，防止时序攻击
     *
     * @param a 第一个字符串
     * @param b 第二个字符串
     * @return 是否相等
     */
    public static boolean secureEquals(String a, String b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null || a.length() != b.length()) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < a.length(); i++) {
            result |= a.charAt(i) ^ b.charAt(i);
        }
        return result == 0;
    }

    /**
     * 验证邮箱格式是否正确
     *
     * @param email 邮箱地址
     * @return 格式是否正确
     */
    public static boolean isValidEmail(String email) {
        if (isBlank(email)) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * 验证手机号格式是否正确（中国大陆）
     *
     * @param phone 手机号码
     * @return 格式是否正确
     */
    public static boolean isValidPhone(String phone) {
        if (isBlank(phone)) {
            return false;
        }
        return PHONE_PATTERN.matcher(phone).matches();
    }

    /**
     * 限制字符串长度
     *
     * @param str 原始字符串
     * @param maxLength 最大长度
     * @return 限制长度后的字符串
     */
    public static String limitLength(String str, int maxLength) {
        if (str == null || maxLength < 0) {
            return str;
        }
        if (str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength);
    }

    /**
     * 隐藏字符串中间部分，常用于敏感信息显示
     *
     * @param str 原始字符串
     * @param start 保留的前几位
     * @param end 保留的后几位
     * @return 处理后的字符串
     */
    public static String maskMiddle(String str, int start, int end) {
        if (str == null || str.length() <= start + end) {
            return str;
        }

        StringBuilder sb = new StringBuilder(str.length());
        sb.append(str, 0, start);
        for (int i = 0; i < str.length() - start - end; i++) {
            sb.append('*');
        }
        sb.append(str, str.length() - end, str.length());
        return sb.toString();
    }
}
