package com.liaoyb.springboot.dto;

import lombok.Data;

/**
 * @author liaoyb
 * @date 2018-07-01 22:39
 */
@Data
public class ErrorInfo<T> {
    public static final Integer OK = 0;
    public static final Integer ERROR = 100;

    private Integer code;
    private String message;
    private String url;
    private T data;
}
