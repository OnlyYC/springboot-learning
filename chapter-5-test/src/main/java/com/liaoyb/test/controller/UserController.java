package com.liaoyb.test.controller;

import com.liaoyb.test.model.User;
import com.liaoyb.test.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author liaoyb
 * @date 2018-10-01 17:15
 */
@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable String id) {
        User user = new User();
        user.setId(id);
        user.setName("taobi");
        user.setAge(24);
        return user;
    }

    /**
     * 获取用户积分
     *
     * @param userId
     * @return
     */
    @GetMapping("/user/credit/{userId}")
    @ResponseBody
    public Integer getUserCredit(@PathVariable String userId) {
        return userService.getCredit(userId);
    }

    /**
     * 文件上传
     *
     * @param file
     * @throws IOException
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        String filename = !StringUtils.isEmpty(file.getOriginalFilename()) ? file.getOriginalFilename() : UUID.randomUUID().toString();
        File targetFile = new File("D:/mytmp/" + filename);
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }
        file.transferTo(targetFile);
        return targetFile.getAbsolutePath();
    }
}
