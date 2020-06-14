package com.demo.common;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author zhangjw
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class WebResp<T> {
    private static final Integer successCode = 200;
    private static final Integer errorCode = -1;
    private Integer code;
    private String message;
    private String stackTrace;
    private T data;


    public static WebResp ok() {
        return new WebResp().setCode(successCode);
    }


    public static <T> WebResp<T> ok(T data) {
        return new WebResp<T>().setCode(successCode).setData(data);
    }

    public static WebResp ok(String message) {
        return new WebResp().setCode(successCode).setMessage(message);
    }

    public static WebResp error(String message) {
        return new WebResp().setCode(errorCode).setMessage(message);
    }


}
