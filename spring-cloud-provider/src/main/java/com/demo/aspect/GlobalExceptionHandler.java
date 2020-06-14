package com.demo.aspect;

import com.demo.common.WebResp;
import com.demo.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

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
    private boolean includeQueryString = true;

    private boolean includeClientInfo = true;

    private boolean includeHeaders = true;

    private boolean includePayload = true;
    private int maxPayloadLength = 50;

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
    public WebResp exceptionHandle(Exception e, HttpServletRequest request) {
        log.error("系统异常 ====================================", e);
        return WebResp.error(e.getMessage());
    }
}
