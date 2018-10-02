package com.liaoyb.test.service;

/**
 * 用户服务
 *
 * @author liaoyb
 * @date 2018-10-01 16:53
 */
public interface UserService {
    /**
     * 获取用户积分（依赖于积分系统）
     *
     * @param userId
     * @return
     */
    int getCredit(String userId);

    /**
     * 添加积分
     *
     * @param userId
     * @param score
     * @return
     */
    boolean addCredit(String userId, int score);
}
