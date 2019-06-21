package com.demo.config;

import com.demo.exception.BizException;
import com.demo.exception.JsonUtil;
import com.demo.exception.ResponseCodeEnum;
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
        try {
            if (response.body() != null) {
                String targetMsg;
                String body = Util.toString(response.body().asReader());
                Map bodyMap = JsonUtil.jsonToMap(body);
                String message = bodyMap.get("message").toString();
                if (message.contains("errCode")) {
                    targetMsg = message.substring(message.indexOf("{"), message.indexOf("}") + 1);
                    return JsonUtil.jsonToBean(targetMsg, BizException.class);
                }else{
                    return new BizException(ResponseCodeEnum.SERVER_ERROR.getErrCode(), body);
                }

            }
        } catch (Exception e) {
            log.error("feign error decoder, methodKey:{},response:{},msg:{}", methodKey, response,
                    ExceptionUtils.getStackTrace(e));
            return new BizException(ResponseCodeEnum.SERVER_ERROR.getErrCode(), e.getMessage());
        }
        return new BizException(ResponseCodeEnum.SERVER_ERROR);
    }
}