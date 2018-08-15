package com.liaoyb.springboot.data.utils;

import com.liaoyb.springboot.data.ApplicationTests;
import com.liaoyb.springboot.data.domain.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class RedisUtilTest extends ApplicationTests {

    @Test
    public void test() throws InterruptedException {
        RedisUtil.remove("gg");
        String value = RedisUtil.getString("gg");
        Assert.assertNull(value);

        //存取
        RedisUtil.set("gg", "gg");
        Assert.assertEquals("gg", RedisUtil.getString("gg"));

        //存放指定时间
        RedisUtil.set("expireVal","anything",10L);
        TimeUnit.SECONDS.sleep(11L);
        Assert.assertFalse(RedisUtil.exists("expireVal"));

        //存取对象
        RedisUtil.set("user1", new User("taobi", 23));
        Assert.assertEquals("taobi", RedisUtil.get("user1", User.class).getName());

    }
}
