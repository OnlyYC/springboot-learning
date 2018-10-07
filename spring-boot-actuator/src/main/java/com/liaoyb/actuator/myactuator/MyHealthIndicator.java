package com.liaoyb.actuator.myactuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 自定义/actuator/health节点
 *
 * @author liaoyb
 * @date 2018-10-07 16:30
 */
@Component
public class MyHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        int errorCode = check();
        if (errorCode != 0) {
            return Health.down().withDetail("Message", "error:" + errorCode).build();
        }

        return Health.up().build();
    }

    /**
     * 检查服务状态
     *
     * @return
     */
    private int check() {
        //模拟返回错误状态
        return 1;
    }
}
