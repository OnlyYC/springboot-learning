package com.liaoyb.test.controller;

import com.liaoyb.test.service.UserService;
import org.hamcrest.core.IsNot;
import org.hamcrest.text.IsEmptyString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 单独测试MVC，需对Controller中的所有Service进行模拟
 *
 * @author liaoyb
 * @date 2018-10-01 17:30
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;


    @Test
    public void testGetUser() throws Exception {
        String userId = "10";
        int expectedCredit = 100;
        given(this.userService.getCredit(userId)).willReturn(expectedCredit);
        mvc.perform(get("/user/credit/{userId}", userId))
                .andExpect(status().isOk())
                //期望返回内容是application/json
                .andExpect(content().string(String.valueOf(expectedCredit)));
    }

    /**
     * 测试文件上传
     */
    @Test
    public void testUpload() throws Exception {
        mvc.perform(multipart("/upload").file("file", "文件内容".getBytes("UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().string(new IsNot<>(new IsEmptyString())));
    }
}
