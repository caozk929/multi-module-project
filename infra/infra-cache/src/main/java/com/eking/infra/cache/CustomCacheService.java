package com.eking.infra.cache;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 自定义缓存服务，提供对本地缓存的直接操作
 */
@RequiredArgsConstructor
@Service
public class CustomCacheService {

    /**
     * 默认缓存实例
     */
    private final Cache<Object, Object> defaultCache;

    /**
     * 向缓存中插入数据
     *
     * @param key   缓存键
     * @param value 缓存值
     */
    public <T> void put(String key, T value) {
        defaultCache.put(key, value);
    }

    /**
     * 向缓存中插入数据，并支持设置超时时间（单位：秒）
     *
     * @param key        缓存键
     * @param value      缓存值
     * @param timeUnit   超时时间单位
     * @param expireTime 超时时间
     */
    public <T> void put(String key, T value, TimeUnit timeUnit, int expireTime) {
        if (expireTime > 0) {
            Date now = new Date();
            // 超时毫秒数
            long expireMs = DateUtil.betweenMs(now, DateUtil.offset(now, transferTimeUnit2DateField(timeUnit), expireTime));
            defaultCache.put(key, new ExpirableValue<>(value, expireMs));
        } else {
            defaultCache.put(key, value);
        }
    }

    /**
     * 将时间单位转换为 DateField
     *
     * @param timeUnit 时间单位
     * @return 对应的 DateField
     */
    private DateField transferTimeUnit2DateField(TimeUnit timeUnit) {
        if (timeUnit == TimeUnit.SECONDS) {
            return DateField.SECOND;
        } else if (timeUnit == TimeUnit.MINUTES) {
            return DateField.MINUTE;
        } else if (timeUnit == TimeUnit.HOURS) {
            return DateField.HOUR;
        } else if (timeUnit == TimeUnit.DAYS) {
            return DateField.DAY_OF_MONTH;
        } else if (timeUnit == TimeUnit.MILLISECONDS) {
            return DateField.MILLISECOND;
        }
        return DateField.SECOND;
    }


    /**
     * 从缓存中获取数据
     *
     * @param key 缓存键
     * @return 缓存值，如果不存在则返回 null
     */
    public <T> T get(final String key) {
        Object value = defaultCache.getIfPresent(key);
        if (value instanceof ExpirableValue<?>) {
            ExpirableValue<?> expirableValue = (ExpirableValue<?>) value;
            return (T) expirableValue.getValue();
        }
        return (T) value;
    }

    /**
     * 从缓存中删除指定的 key
     *
     * @param key 缓存键
     */
    public void remove(String key) {
        defaultCache.invalidate(key);
    }

    /**
     * 从缓存中删除指定的 key列表
     *
     * @param keys 缓存键
     */
    public void remove(List<String> keys) {
        defaultCache.invalidateAll(keys);
    }

    /**
     * 清空整个缓存
     */
    public void clear() {
        defaultCache.invalidateAll();
    }


}
