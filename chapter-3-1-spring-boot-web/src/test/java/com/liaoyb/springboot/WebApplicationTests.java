package com.liaoyb.springboot;

import com.liaoyb.springboot.web.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTests {
    private MockMvc mvc;

    @Before
    public void setUp(){
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }


    @Test
    public void testUserController() throws Exception {
        RequestBuilder request = null;
        request = get("/users/");

        //get查询user列表，为空
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

        //post提交一个user
        request = post("/users/")
                .param("id", "1")
                .param("name", "taobi")
                .param("age", "20");
        mvc.perform(request)
                .andExpect(content().string(equalTo("post success")));

        //get查询user列表，应该有刚才插入的数据
        request = get("/users/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":\"1\",\"name\":\"taobi\",\"age\":20}]")));

        //put 修改id为1的user
        request = put("/users/1")
                .param("name", "taobi2")
                .param("age", "32");
        mvc.perform(request)
                .andExpect(content().string(equalTo("put success")));

        //get一个id为1的user
        request = get("/users/1");
        mvc.perform(request)
                .andExpect(content().string(equalTo("{\"id\":\"1\",\"name\":\"taobi2\",\"age\":32}")));

        //delete 删除id为1的user
        request = delete("/users/1");
        mvc.perform(request)
                .andExpect(content().string(equalTo("delete success")));

        //get查询user列表
        request = get("/users/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

}
