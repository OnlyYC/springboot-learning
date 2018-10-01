package com.liaoyb.starter.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author liaoyb
 * @date 2018-10-01 11:37
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyStarterConfig.class)
public @interface EnableMyStarter {
}
