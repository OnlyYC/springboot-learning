package com.liaoyb.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author liaoyb
 * @date 2018-10-01 11:16
 */
@Configuration
@ComponentScan(basePackages = {"com.liaoyb.starter.controller"})
public class MyStarterConfig {
    /**
     * 导入配置
     */
    @Bean
    @ConfigurationProperties(prefix = "my")
    public MyProperties myProperties() {
        return new MyProperties();
    }

}
