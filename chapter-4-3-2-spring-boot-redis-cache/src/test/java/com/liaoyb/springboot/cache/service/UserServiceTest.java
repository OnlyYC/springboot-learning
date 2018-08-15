package com.liaoyb.springboot.cache.service;

import com.liaoyb.springboot.cache.RedisCacheApplicationTests;
import com.liaoyb.springboot.cache.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class UserServiceTest extends RedisCacheApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void test(){
        User user = userService.findUserById("1");
        log.info(user.toString());
        //从缓存中查询
        user = userService.findUserById("1");
        log.info(user.toString());

        //更新User
        User user1 = userService.upuser("1");

        //查询，从缓存中查询到最新
        user = userService.findUserById("1");
        log.info("更新user,从缓存中查询到最新, user:{}"+user);


        //新增User
        userService.put(new User("4","jj", 24));

        //查询，从缓存中查询到最新
        user = userService.findUserById("4");
        log.info("新增User,从缓存中查询到最新, user:{}", user);

        //查询所有
        List<User> userList = userService.findAllUser();
        log.info("userList:"+userList);

        //清空缓存
//        userService.deleteAll();
    }
}
