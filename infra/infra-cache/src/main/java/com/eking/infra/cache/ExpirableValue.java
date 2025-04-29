package com.eking.infra.cache;

import lombok.Getter;

// 包装值和过期时间的内部类
@Getter
public class ExpirableValue<V> {
    private final V value;
    // 过期时间，单位为毫秒
    private final long expireTime;

    public ExpirableValue(V value, long expireTime) {
        this.value = value;
        this.expireTime = expireTime;
    }

}