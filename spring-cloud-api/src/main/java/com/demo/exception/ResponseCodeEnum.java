package com.demo.exception;

import java.text.MessageFormat;

/**
 * Program Name: spring-cloud-demo
 * <p>
 * Description: spring cloud 内部调用返回枚举
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/6/10 10:58 AM
 */
public enum ResponseCodeEnum {
    /**
     *
     */
    SERVER_ERROR(500, "服务端错误"),
    ;
    private Integer errCode;
    private String errMsg;

    ResponseCodeEnum(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public ResponseCodeEnum message(String param) {
        MessageFormat format = new MessageFormat(errMsg);
        this.errMsg = format.format(param);
        return this;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
