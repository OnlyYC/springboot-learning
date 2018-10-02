package com.liaoyb.test.config;

import com.liaoyb.test.service.CreditSystemService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liaoyb
 * @date 2018-10-01 17:03
 */
@Configuration
public class MyConfig {
    /**
     * 模拟远程调用方式注入的实例
     *
     * @return
     */
    @Bean
    public CreditSystemService creditSystemService(){
        return new CreditSystemService() {
            @Override
            public int getUserCredit(String userId) {
                return 0;
            }

            @Override
            public boolean addCredit(String userId, int score) {
                return false;
            }
        };
    }
}
