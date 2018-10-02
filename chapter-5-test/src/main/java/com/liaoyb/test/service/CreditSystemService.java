package com.liaoyb.test.service;

/**
 * 积分系统service
 *
 * @author liaoyb
 * @date 2018-10-01 16:51
 */
public interface CreditSystemService {
    /**
     * 获取积分
     *
     * @param userId
     * @return
     */
    int getUserCredit(String userId);

    /**
     * 添加积分
     *
     *
     * @param userId
     * @param score
     * @return
     */
    boolean addCredit(String userId, int score);
}
