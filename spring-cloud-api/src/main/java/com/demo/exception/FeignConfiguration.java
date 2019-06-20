package com.demo.exception;

import feign.codec.ErrorDecoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Program Name: spring-cloud-demo
 * <p>
 * Description: FeignConfiguration
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/6/17 6:50 PM
 */

@Configuration
public class FeignConfiguration {

    /**
     * feign错误处理bean
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(FeignErrorDecoder.class)
    public ErrorDecoder feignErrorDecoder() {
        return new FeignErrorDecoder();
    }
}
