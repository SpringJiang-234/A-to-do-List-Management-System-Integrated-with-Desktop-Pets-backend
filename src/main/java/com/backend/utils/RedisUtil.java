package com.backend.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 */
@Component
public class RedisUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置缓存
     * @param key 键
     * @param value 值
     * @return true 成功 false 失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            logger.error("Redis set error, key: {}, value: {}, error: {}", key, value, e.getMessage());
            return false;
        }
    }
    /**
     * 设置缓存并指定过期时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return true 成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            logger.error("Redis set with timeout error, key: {}, value: {}, time: {}, error: {}", key, value, time, e.getMessage());
            return false;
        }
    }

    /**
     * 获取缓存
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        try {
            return key == null ? null : redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            logger.error("Redis get error, key: {}, error: {}", key, e.getMessage());
            return null;
        }
    }

    /**
     * 删除缓存
     * @param key 键
     * @return true 成功 false 失败
     */
    public boolean delete(String key) {
        try {
            if (key != null) {
                redisTemplate.delete(key);
            }
            return true;
        } catch (Exception e) {
            logger.error("Redis delete error, key: {}, error: {}", key, e.getMessage());
            return false;
        }
    }

    /**
     * 删除多个缓存
     * @param keys 键集合
     * @return true 成功 false 失败
     */
    public boolean deleteBatch(String... keys) {
        try {
            if (keys != null && keys.length > 0) {
                redisTemplate.delete(java.util.Arrays.asList(keys));
            }
            return true;
        } catch (Exception e) {
            logger.error("Redis delete batch error, keys: {}, error: {}", java.util.Arrays.toString(keys), e.getMessage());
            return false;
        }
    }

    /**
     * 设置过期时间
     * @param key 键
     * @param time 时间(秒)
     * @return true 成功 false 失败
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            logger.error("Redis expire error, key: {}, time: {}, error: {}", key, time, e.getMessage());
            return false;
        }
    }

    /**
     * 获取过期时间
     * @param key 键
     * @return 时间(秒) 返回-1表示永久有效
     */
    public long getExpire(String key) {
        try {
            return redisTemplate.getExpire(key, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("Redis get expire error, key: {}, error: {}", key, e.getMessage());
            return -1;
        }
    }

    /**
     * 判断键是否存在
     * @param key 键
     * @return true 存在 false 不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            logger.error("Redis has key error, key: {}, error: {}", key, e.getMessage());
            return false;
        }
    }
}
