package com.liaoyb.springboot.data;

import com.liaoyb.springboot.data.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("jj", "eageae");
        String value = (String) redisTemplate.opsForValue().get("jj");
        Assert.assertEquals(value, "eageae");
    }

    @Test
    public void testRedisStoreUser(){
        redisTemplate.opsForValue().set("user1", new User("taobi", 24));
        User user = (User) redisTemplate.opsForValue().get("user1");
        System.out.println(user);
    }
}
