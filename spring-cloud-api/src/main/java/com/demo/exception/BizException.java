package com.demo.exception;

import lombok.Data;

/**
 * Program Name: spring-cloud-demo
 * <p>
 * Description: 业务异常
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/6/17 6:46 PM
 */
@Data
public class BizException extends RuntimeException {
    private static final long serialVersionUID = -6131476368606527012L;

    /**
     * 异常编码
     */
    private Integer errCode;
    /**
     * 异常信息
     */
    private String errMsg;
    /**
     * 异常堆栈
     */
    private String stackTraceString;

    public BizException() {
        super();
    }

    public BizException(String errMsg) {
        super(errMsg);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String errMsg,Throwable cause) {
        super(cause);
        this.errMsg = errMsg;
    }

    public BizException(Integer errCode, Throwable cause) {
        super(cause);
        this.errCode = errCode;
    }

    public BizException(Integer errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BizException(Integer errCode, String errMsg, Throwable cause) {
        super(errMsg, cause);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BizException(ResponseCodeEnum exceptionEnum) {
        super(exceptionEnum.getErrMsg());
        this.errCode = exceptionEnum.getErrCode();
        this.errMsg = exceptionEnum.getErrMsg();
    }

    @Override
    public String getMessage() {
        return "{" +
                "\"errCode\":" + errCode +
                ", \"errMsg\":'" + errMsg + '\'' +
                ", \"stackTraceString\":'" + stackTraceString + '\'' +
                '}';
    }

}

