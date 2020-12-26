package com.yicj.study.redis.properties.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.ArrayList;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "jedis")
public class RedisProperties {
    private PoolConfig poolConfig ;
    private Master master ;
    private String password ;
    private List<SentinelNode> sentinelNodes = new ArrayList<>();
    private String userName ;

}
