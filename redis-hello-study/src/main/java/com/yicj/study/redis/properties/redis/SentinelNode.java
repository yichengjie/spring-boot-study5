package com.yicj.study.redis.properties.redis;

import lombok.Data;

@Data
public class SentinelNode {
    private String host;
    private Integer port;
}