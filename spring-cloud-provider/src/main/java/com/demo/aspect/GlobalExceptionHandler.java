package com.demo.aspect;

import com.demo.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Program Name: spring-cloud-demo
 * <p>
 * Description: rest controller 统一异常处理
 *
 *
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/6/20 3:08 PM
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BizException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BizException exceptionHandle(BizException e) {
        throw e;
    }


    /**
     * 其他异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Exception exceptionHandle(Exception e) {
        throw new BizException(e.getMessage(), e.getCause());
    }
}
