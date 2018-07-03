package com.liaoyb.springboot.exception;

/**
 * 自定义异常
 *
 * @author liaoyb
 * @date 2018-07-01 22:41
 */
public class CustomException extends RuntimeException{
    public CustomException(String message) {
        super(message);
    }
}
