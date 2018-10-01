package com.liaoyb.starter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaoyb
 * @date 2018-10-01 11:16
 */
@RestController
@RequestMapping("/my")
public class MyController {

    @GetMapping("/hello")
    public String hello() {
        return "this is from my simple starter";
    }
}
