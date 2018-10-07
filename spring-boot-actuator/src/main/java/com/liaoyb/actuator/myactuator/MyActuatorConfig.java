package com.liaoyb.actuator.myactuator;

import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liaoyb
 * @date 2018-10-07 16:35
 */
@Configuration
public class MyActuatorConfig {
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnEnabledEndpoint
    public MyActuatorEndpoint myActuatorEndpoint() {
        return new MyActuatorEndpoint();
    }
}
