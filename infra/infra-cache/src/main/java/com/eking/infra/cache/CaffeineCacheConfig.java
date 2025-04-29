package com.eking.infra.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import org.checkerframework.checker.index.qual.NonNegative;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

/**
 * Caffeine本地缓存配置
 */
@Configuration
@EnableCaching
public class CaffeineCacheConfig {

    /**
     * 默认缓存管理器
     */
    @Bean
    @Primary
    public CacheManager caffeineCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                // 设置最后一次写入或访问后经过固定时间过期
                .expireAfterWrite(1, TimeUnit.DAYS)
                // 初始的缓存空间大小
                .initialCapacity(100)
                // 缓存的最大条数
                .maximumSize(100000));
        return cacheManager;
    }

    @Bean
    public Cache<Object, Object> customCache() {
        return Caffeine.newBuilder()
                .maximumSize(100).expireAfter(new Expiry<>() {
                    @Override
                    public long expireAfterCreate(Object key, Object value, long currentTime) {
                        if (value instanceof ExpirableValue) {
                            ExpirableValue<?> expirableValue = (ExpirableValue<?>) value;
                            // 1秒 = 1000毫秒 = 1000000微秒 = 1000000000纳秒
                            return expirableValue.getExpireTime() * 1000000;
                        }
                        return 24L * 60 * 60 * 1000000; // 默认1天
                    }

                    @Override
                    public long expireAfterUpdate(Object key, Object value, long currentTime, @NonNegative long currentDuration) {
                        return currentDuration;
                    }

                    @Override
                    public long expireAfterRead(Object key, Object value, long currentTime, @NonNegative long currentDuration) {
                        return currentDuration;
                    }
                }).build();
    }
}
