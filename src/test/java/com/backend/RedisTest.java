package com.backend;

import com.backend.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;

    private boolean isRedisAvailable() {
        // 测试Redis是否可用
        try {
            String testKey = "test:availability";
            boolean result = redisUtil.set(testKey, "test");
            if (result) {
                redisUtil.delete(testKey);
                System.out.println("Redis服务器连接正常");
                return true;
            } else {
                System.out.println("Redis服务器连接失败: 可能是密码错误或Redis服务器未运行");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Redis服务器连接异常: " + e.getMessage());
            System.out.println("请检查Redis服务器是否运行，以及配置文件中的密码是否正确");
            return false;
        }
    }

    @Test
    public void testRedisConnection() {
        // 测试Redis连接是否正常
        if (!isRedisAvailable()) {
            System.out.println("Redis服务器不可用，跳过测试");
            return;
        }

        String testKey = "test:connection";
        String testValue = "Hello Redis";

        // 测试设置缓存
        boolean setResult = redisUtil.set(testKey, testValue);
        assertTrue(setResult, "设置缓存失败");

        // 测试获取缓存
        Object getResult = redisUtil.get(testKey);
        assertEquals(testValue, getResult, "获取缓存失败");

        // 测试判断键是否存在
        boolean hasKeyResult = redisUtil.hasKey(testKey);
        assertTrue(hasKeyResult, "键不存在");

        // 测试设置过期时间
        boolean expireResult = redisUtil.expire(testKey, 60);
        assertTrue(expireResult, "设置过期时间失败");

        // 测试获取过期时间
        long expireTime = redisUtil.getExpire(testKey);
        assertTrue(expireTime > 0, "获取过期时间失败");

        // 测试删除缓存
        boolean deleteResult = redisUtil.delete(testKey);
        assertTrue(deleteResult, "删除缓存失败");

        // 测试删除后键是否存在
        boolean hasKeyAfterDelete = redisUtil.hasKey(testKey);
        assertFalse(hasKeyAfterDelete, "删除后键仍然存在");
    }

    @Test
    public void testRedisUtilMethods() {
        // 测试RedisUtil的其他方法
        if (!isRedisAvailable()) {
            System.out.println("Redis服务器不可用，跳过测试");
            return;
        }

        String key1 = "test:key1";
        String key2 = "test:key2";
        String value1 = "Value 1";
        String value2 = "Value 2";

        // 测试批量设置
        redisUtil.set(key1, value1);
        redisUtil.set(key2, value2);

        // 测试批量删除
        boolean deleteBatchResult = redisUtil.deleteBatch(key1, key2);
        assertTrue(deleteBatchResult, "批量删除失败");

        // 测试删除后键是否存在
        assertFalse(redisUtil.hasKey(key1), "删除后key1仍然存在");
        assertFalse(redisUtil.hasKey(key2), "删除后key2仍然存在");

        // 测试设置带过期时间的缓存
        String keyWithExpire = "test:expire";
        String valueWithExpire = "Value with expire";
        redisUtil.set(keyWithExpire, valueWithExpire, 30);

        // 测试获取带过期时间的缓存
        Object getExpireResult = redisUtil.get(keyWithExpire);
        assertEquals(valueWithExpire, getExpireResult, "获取带过期时间的缓存失败");

        // 测试获取过期时间
        long expireTime = redisUtil.getExpire(keyWithExpire);
        assertTrue(expireTime > 0, "获取带过期时间的缓存的过期时间失败");

        // 清理测试数据
        redisUtil.delete(keyWithExpire);
    }

    @Test
    public void testRedisEdgeCases() {
        // 测试边界情况
        if (!isRedisAvailable()) {
            System.out.println("Redis服务器不可用，跳过测试");
            return;
        }

        String nullKey = null;
        Object nullValue = null;

        // 测试null键
        Object getNullKeyResult = redisUtil.get(nullKey);
        assertNull(getNullKeyResult, "获取null键应该返回null");

        // 测试null值
        boolean setNullValueResult = redisUtil.set("test:null", nullValue);
        assertTrue(setNullValueResult, "设置null值失败");

        // 测试获取null值
        Object getNullValueResult = redisUtil.get("test:null");
        assertNull(getNullValueResult, "获取null值应该返回null");

        // 测试删除null键
        boolean deleteNullKeyResult = redisUtil.delete(nullKey);
        assertTrue(deleteNullKeyResult, "删除null键失败");

        // 测试批量删除空数组
        boolean deleteEmptyBatchResult = redisUtil.deleteBatch();
        assertTrue(deleteEmptyBatchResult, "批量删除空数组失败");

        // 测试设置过期时间为0
        String keyWithZeroExpire = "test:zero:expire";
        boolean setZeroExpireResult = redisUtil.set(keyWithZeroExpire, "Value", 0);
        assertTrue(setZeroExpireResult, "设置过期时间为0失败");

        // 清理测试数据
        redisUtil.delete("test:null");
        redisUtil.delete(keyWithZeroExpire);
    }
}