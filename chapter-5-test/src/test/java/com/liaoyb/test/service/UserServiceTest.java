package com.liaoyb.test.service;

import com.liaoyb.test.ApplicationTests;
import com.liaoyb.test.controller.UserController;
import org.junit.Test;
import org.mockito.InOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

/**
 * @author liaoyb
 * @date 2018-10-01 16:58
 */
public class UserServiceTest extends ApplicationTests {
    @Autowired
    private UserService userService;

    @MockBean
    private CreditSystemService creditSystemService;

    @Test
    public void testGetCredit() {
        int expectedCredit = 100;
        given(this.creditSystemService.getUserCredit(anyString())).willReturn(expectedCredit);
        int credit = userService.getCredit("10");
        assertEquals(expectedCredit, credit);
    }


    /**
     * Mockito通过verifu方法精确校验对象是否被调用、调用次数、调用顺序
     */
    @Test
    public void testAddCredit() {
        //模拟mock对象调用(需对验证方法进行模拟)
        String userId = "100";
        when(creditSystemService.getUserCredit(eq(userId))).thenReturn(1000);
        when(creditSystemService.addCredit(eq(userId), anyInt())).thenReturn(true);

        //实际调用
        userService.addCredit(userId, 10);

        //验证调用顺序,确保先调用getUserCredit，然后调用addCredit
        InOrder order = inOrder(creditSystemService);
        order.verify(creditSystemService).getUserCredit(userId);
        order.verify(creditSystemService).addCredit(userId, 1000 + 10);
    }


}
