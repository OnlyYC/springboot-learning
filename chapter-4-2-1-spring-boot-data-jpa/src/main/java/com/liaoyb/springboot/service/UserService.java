package com.liaoyb.springboot.service;

import com.liaoyb.springboot.domain.dto.PageParam;
import com.liaoyb.springboot.model.User;
import org.springframework.data.domain.Page;

/**
 * @author liaoyb
 * @date 2018-07-07 13:09
 */
public interface UserService {
    Page<User> getUsers(PageParam pageParam);
}
