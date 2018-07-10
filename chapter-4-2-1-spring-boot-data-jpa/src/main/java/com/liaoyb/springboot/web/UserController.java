package com.liaoyb.springboot.web;

import com.liaoyb.springboot.domain.dto.PageDto;
import com.liaoyb.springboot.domain.dto.PageParam;
import com.liaoyb.springboot.model.User;
import com.liaoyb.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaoyb
 * @date 2018-07-07 13:20
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 分页查询(从第0页开始)
     *
     * @param pageParam
     * @return
     */
    @RequestMapping("findAll")
    public PageDto<User> findAll(PageParam pageParam) {
        Page<User> userPage = userService.getUsers(pageParam);
        return PageDto.convertFor(userPage);
    }

}
