package com.liaoyb.springboot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 通过@Value注解读取单个属性
 *
 * @author liaoyb
 * @date 2018-07-01 10:55
 */
@Data
@Component
public class BookProperties {

    /**
     * 书名
     */
    @Value("${book.name}")
    private String name;

    /**
     * 作者
     */
    @Value("${book.writer}")
    private String writer;

    @Value("${book.uuid}")
    private String uuid;
}
