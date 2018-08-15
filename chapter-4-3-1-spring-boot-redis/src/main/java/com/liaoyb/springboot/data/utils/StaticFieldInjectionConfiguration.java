package com.liaoyb.springboot.data.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;

/**
 * 工具类注入
 */
@Slf4j
@Configuration
public class StaticFieldInjectionConfiguration {

    @Autowired
    RedisTemplate redisTemplate;

    @PostConstruct
    private void init() {
        log.info("\n\n-----StaticFieldInjectionConfiguration----\n\n");

        RedisUtil.setRedisTemplate(redisTemplate);
    }
}