package com.liaoyb.springboot.env.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaoyb
 * @date 2018-10-24 13:58
 */
@RestController
public class EnvController {
    @Value("${custom.name}")
    private String customName;

    @GetMapping("/custom")
    public String custom() {
        return customName;
    }
}
