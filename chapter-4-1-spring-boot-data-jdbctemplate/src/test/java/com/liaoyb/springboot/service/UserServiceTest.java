package com.liaoyb.springboot.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liaoyb
 * @date 2018-07-03 17:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void test() {
        userService.create();
        userService.add("1", "taobi", 20);
        Assert.assertEquals(new Integer(1), userService.getAllUserCount());
    }


}
