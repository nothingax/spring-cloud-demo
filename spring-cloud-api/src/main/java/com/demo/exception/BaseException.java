package com.demo.exception;

import lombok.Data;

/**
 * Program Name: spring-cloud-demo
 * <p>
 * Description:
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/6/17 6:46 PM
 */
@Data
public class BaseException extends RuntimeException {
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

    public BaseException() {
        super();
    }

    public BaseException(String errMsg) {
        super(errMsg);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(Integer errCode, Throwable cause) {
        super(cause);
        this.errCode = errCode;
    }

    public BaseException(Integer errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BaseException(Integer errCode, String errMsg, Throwable cause) {
        super(errMsg, cause);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BaseException(ResponseCodeEnum exceptionEnum) {
        super(exceptionEnum.getErrMsg());
        this.errCode = exceptionEnum.getErrCode();
        this.errMsg = exceptionEnum.getErrMsg();
    }

    @Override
    public String getMessage() {
        return JsonUtil.beanToJson(this);
    }

}

