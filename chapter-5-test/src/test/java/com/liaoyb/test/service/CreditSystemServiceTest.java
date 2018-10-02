package com.liaoyb.test.service;

import com.liaoyb.test.ApplicationTests;
import org.junit.Test;
import org.mockito.InOrder;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author liaoyb
 * @date 2018-10-02 12:27
 */
public class CreditSystemServiceTest extends ApplicationTests {
    @MockBean
    private CreditSystemService creditSystemService;

    /**
     * Mockito通过verifu方法精确校验对象是否被调用、调用次数、调用顺序
     */
    @Test
    public void testAddCredit() {
        //模拟mock对象调用(需对验证方法进行模拟)
        String userId = "100";
        when(creditSystemService.getUserCredit(eq(userId))).thenReturn(1000);
        when(creditSystemService.addCredit(eq(userId), anyInt())).thenReturn(true);

        //实际调用(应放在addCredit实现中，这里做模拟调用)
        int ret = creditSystemService.getUserCredit(userId);
        creditSystemService.addCredit(userId, ret + 10);

        //验证调用顺序,确保先调用getUserCredit，然后调用addCredit
        InOrder order = inOrder(creditSystemService);
        order.verify(creditSystemService).getUserCredit(userId);
        order.verify(creditSystemService).addCredit(userId, ret + 10);
    }

    /**
     * 设置参数
     * session、cookie
     * http header
     * <p>
     * 比较结果：
     * 比较视图
     * 比较forward、redirect
     * 使用content()比较返回内容
     * 模拟异常
     */


    /**
     * doThrow对没有返回值的方法，模拟抛出异常
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testError() {
        List list = mock(List.class);
        doThrow(new UnsupportedOperationException("不支持clear方法调用"))
                .when(list).clear();

        //实际调用、将抛出异常
        list.clear();
    }
}
