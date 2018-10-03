package com.liaoyb.session.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author liaoyb
 * @date 2018-10-02 17:28
 */
@RestController
public class HomeController {
    @GetMapping({"/", "/index"})
    public String index(HttpSession session) {
        session.setAttribute("name", "taobi");
        return "首页";
    }
}
