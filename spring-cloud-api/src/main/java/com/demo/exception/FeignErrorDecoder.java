package com.demo.exception;

import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Map;

/**
 * Program Name: spring-cloud-demo
 * <p>
 * Description: FeignErrorDecoder
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/6/17 7:08 PM
 */
@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {

    // @Override
    public Exception decode(String methodKey, Response response) {
        try {
            if (response.body() != null) {
                String targetMsg;
                String body = Util.toString(response.body().asReader());
                Map bodyMap = JsonUtil.jsonToMap(body);

                Object exception = bodyMap.get("com/demo/exception");
                String message = bodyMap.get("message").toString();
                if (null != exception) {
                    Class clazz = Class.forName(exception.toString());
                    Object obj = clazz.newInstance();
                    if (obj instanceof BaseException) {
                        targetMsg = message.substring(message.indexOf("{"), message.indexOf("}") + 1);
                        String errorMessage = FeignException.errorStatus(methodKey, response).getMessage();
                        System.out.println("比较两种异常信息:");
                        System.out.println(targetMsg);
                        System.out.println(errorMessage);

                        return JsonUtil.jsonToBean(targetMsg, BaseException.class);
                    }else{
                        targetMsg = message.substring(message.indexOf(":"), message.length());
                        return new BaseException(ResponseCodeEnum.SERVER_ERROR.getErrCode(), targetMsg);
                    }
                }

                if (message.contains("code:")) {
                    targetMsg = message.substring(message.indexOf("{"), message.indexOf("}") + 1);
                    return JsonUtil.jsonToBean(targetMsg, BaseException.class);
                }else{
                    return new BaseException(ResponseCodeEnum.SERVER_ERROR.getErrCode(), body);
                }

            }
        } catch (Exception e) {
            log.error("feign error decoder, methodKey:{},response:{},msg:{}", methodKey, response,
                    ExceptionUtils.getStackTrace(e));
            return new BaseException(ResponseCodeEnum.SERVER_ERROR.getErrCode(), e.getMessage());
        }
        return new BaseException(ResponseCodeEnum.SERVER_ERROR);
    }
}