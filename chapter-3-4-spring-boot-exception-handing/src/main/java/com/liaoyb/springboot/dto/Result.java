package com.liaoyb.springboot.dto;

import lombok.Data;

/**
 * 返回结果实体
 *
 * @author liaoyb
 * @date 2018-07-01 23:13
 */
@Data
public class Result<T> {
    private static final long serialVersionUID = 1L;

    public static final int NO_LOGIN = -1;

    public static final int SUCCESS = 0;

    public static final int FAIL = 1;

    public static final int NO_PERMISSION = 2;

    private String msg = "success";

    private int code = SUCCESS;

    private T data;

    public Result() {
        super();
    }

    public Result(T data) {
        super();
        this.data = data;
    }

    public Result(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL;
    }

    public static <T> Result<T> error(String s) {
        Result e = new Result();
        e.setMsg(s);
        e.setCode(FAIL);
        return e;
    }
}
