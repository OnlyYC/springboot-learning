package com.liaoyb.test.controller;

import com.liaoyb.test.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * SpringBoot测试，会初始化容器
 *
 * @author liaoyb
 * @date 2018-10-01 17:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserController2Test {
    private MockMvc mvc;
    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new UserController(userService)).build();
    }

    @Test
    public void testGetUser() throws Exception {
        String userId = "10";
        mvc.perform(get("/user/{id}", userId))
                .andExpect(status().isOk())
                //期望返回内容是application/json
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                //检查返回内容
                .andExpect(jsonPath("$.name").value("taobi"));
    }
}
