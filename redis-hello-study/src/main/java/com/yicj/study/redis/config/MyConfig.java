package com.yicj.study.redis.config;

import com.yicj.study.redis.properties.redis.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class MyConfig {

    @Autowired
    private RedisProperties redisProperties ;

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(redisProperties.getPool().getMaxIdle());
        config.setMaxTotal(redisProperties.getPool().getMaxTotal());
        config.setTestOnBorrow(redisProperties.getPool().getTestOnBorrow());
        return config;
    }

    @Bean
    public RedisSentinelConfiguration sentinelConfig(){
        RedisSentinelConfiguration configuration = new RedisSentinelConfiguration();
        //配置matser的名称
        configuration.master(redisProperties.getMaster().getName()) ;
        configuration.setPassword(redisProperties.getPassword());
        //配置redis的哨兵sentinel
        Set<RedisNode> redisNodeSet = new HashSet<>();
        redisProperties.getSentinel().forEach(item->{
            redisNodeSet.add(new RedisNode(item.getHost(), item.getPort())) ;
        });
        configuration.setSentinels(redisNodeSet);
        return configuration ;
    }

    @Bean
    public JedisConnectionFactory connectionFactory(
            JedisPoolConfig jedisPoolConfig,RedisSentinelConfiguration sentinelConfig){
        return new JedisConnectionFactory(sentinelConfig,jedisPoolConfig) ;
    }

    @Bean
    public RedisTemplate redisTemplate(JedisConnectionFactory connectionFactory){
        RedisTemplate template = new RedisTemplate() ;
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        return template ;
    }
}
