package com.demo.config;

import com.demo.exception.BizException;
import com.demo.exception.JsonUtil;
import com.demo.exception.ResponseCodeEnum;
import com.netflix.hystrix.exception.HystrixBadRequestException;
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

    @Override
    public Exception decode(String methodKey, Response response) {
        log.error("进入decode response.status() = {}", response.status());
        if (response.status() == 500) {
            try {
                if (response.body() != null) {
                    String targetMsg;
                    String body = Util.toString(response.body().asReader());
                    Map bodyMap = JsonUtil.jsonToMap(body);
                    // String message = bodyMap.get("message").toString();
                    log.error("body 中的内容,{}", bodyMap);
                }
            } catch (Exception e) {
                log.error("feign error decoder, methodKey:{},response:{},msg:{}", methodKey, response,
                        ExceptionUtils.getStackTrace(e));
                return new BizException(ResponseCodeEnum.SERVER_ERROR.getErrCode(), e.getMessage());
            }
        }

        // TODO 这里可以打印出下游的异常
        //
        // try {
        //     if (response.body() != null) {
        //         String targetMsg;
        //         String body = Util.toString(response.body().asReader());
        //         Map bodyMap = JsonUtil.jsonToMap(body);
        //         String message = bodyMap.get("message").toString();
        //         if (message.contains("errCode")) {
        //             targetMsg = message.substring(message.indexOf("{"), message.indexOf("}") + 1);
        //             return JsonUtil.jsonToBean(targetMsg, BizException.class);
        //         }else{
        //             return new BizException(ResponseCodeEnum.SERVER_ERROR.getErrCode(), body);
        //         }
        //
        //     }
        // } catch (Exception e) {
        //     log.error("feign error decoder, methodKey:{},response:{},msg:{}", methodKey, response,
        //             ExceptionUtils.getStackTrace(e));
        //     return new BizException(ResponseCodeEnum.SERVER_ERROR.getErrCode(), e.getMessage());
        // }
        //
        return new HystrixBadRequestException("如果请求如果是个badRequest异常，则不会进fallback");
    }
}