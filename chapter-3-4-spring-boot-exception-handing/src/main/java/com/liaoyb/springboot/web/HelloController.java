package com.liaoyb.springboot.web;

import com.liaoyb.springboot.exception.CustomException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异常
 *
 * @author liaoyb
 * @date 2018-07-01 22:07
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() throws Exception {
        throw new Exception("发生错误了");
    }

    @RequestMapping("/json")
    public String json(){
        throw new CustomException("发生错误，自定义异常");
    }
}
