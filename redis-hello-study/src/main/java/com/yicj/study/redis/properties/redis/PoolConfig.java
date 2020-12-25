package com.yicj.study.redis.properties.redis;

import lombok.Data;

@Data
public class PoolConfig {
    private Integer maxIdle;
    private Integer maxTotal;
    private Boolean testOnBorrow;
}