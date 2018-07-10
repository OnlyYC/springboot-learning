package com.liaoyb.springboot.service;

import com.liaoyb.springboot.dao.UserRepository;
import com.liaoyb.springboot.domain.dto.PageParam;
import com.liaoyb.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author liaoyb
 * @date 2018-07-07 13:18
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> getUsers(PageParam pageParam) {
        Pageable pageable = pageParam.convertToPageRequest();
        return userRepository.findAll(pageable);
    }
}
