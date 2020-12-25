package com.yicj.study.redis.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements ApplicationRunner {

    @Autowired
    private RedisTemplate redisTemplate ;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        redisTemplate.opsForValue().set("helloworld","yicj-hello-test");
        Thread.sleep(1000);
        Object helloworld = redisTemplate.opsForValue().get("helloworld");
        System.out.println("====> " + helloworld);
    }
}
