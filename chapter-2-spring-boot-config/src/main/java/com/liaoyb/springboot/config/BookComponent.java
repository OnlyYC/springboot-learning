package com.liaoyb.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liaoyb
 * @date 2018-07-01 11:02
 */
@Data
@Component
@ConfigurationProperties(prefix = "book")
public class BookComponent {
    /**
     * 书名
     */
    @NotEmpty
    private String name;

    /**
     * 作者
     */
    @NotNull
    private String writer;

    private String uuid;
}
