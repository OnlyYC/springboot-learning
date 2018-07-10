package com.liaoyb.springboot.service;

import com.liaoyb.springboot.model.User;
import org.assertj.core.util.Lists;
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
        userService.add("2", "hong", 22);
        userService.add("3", "ji", 24);
        Assert.assertEquals(3, userService.getAllUserCount().intValue());
        Assert.assertEquals(Lists.newArrayList(
                new User("1", "taobi", 20),
                new User("2", "hong", 22),
                new User("3", "ji", 24)
        ), userService.getAllUser());

        userService.deleteByName("taobi");
        Assert.assertEquals(2, userService.getAllUserCount().intValue());

        userService.deleteAllUsers();
        Assert.assertEquals(0, userService.getAllUserCount().intValue());
    }


}
