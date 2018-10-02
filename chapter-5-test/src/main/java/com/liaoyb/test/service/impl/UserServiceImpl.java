package com.liaoyb.test.service.impl;

import com.liaoyb.test.service.CreditSystemService;
import com.liaoyb.test.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author liaoyb
 * @date 2018-10-01 16:54
 */
@Service
public class UserServiceImpl implements UserService {
    private CreditSystemService creditSystemService;

    public UserServiceImpl(CreditSystemService creditSystemService) {
        this.creditSystemService = creditSystemService;
    }

    @Override
    public int getCredit(String userId) {
        //获取用户信息

        //通过积分系统查询用户积分
        return creditSystemService.getUserCredit(userId);
    }

    @Override
    public boolean addCredit(String userId, int score) {
        //获取积分
        int ret = creditSystemService.getUserCredit(userId);

        //增加积分
        return creditSystemService.addCredit(userId, ret + score);
    }
}
