package com.liaoyb.springboot.data.message;

import com.liaoyb.springboot.data.ApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis pub/sub测试
 *
 * @author liaoyb
 * @date 2018-10-02 16:06
 */
public class RedisPubSubTest extends ApplicationTests {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Test
    public void testSend(){
        redisTemplate.convertAndSend("news.redis", "hello,world");
        redisTemplate.convertAndSend("news.china", "hello,world");
    }
}
