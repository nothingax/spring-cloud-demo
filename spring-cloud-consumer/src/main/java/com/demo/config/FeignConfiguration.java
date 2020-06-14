package com.demo.config;

import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class FeignConfiguration {

    /**
     * feign错误处理bean
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(FeignErrorDecoder.class)
    public ErrorDecoder feignErrorDecoder() {
        log.error("创建自定义ErrorDecoder bean");
        return new FeignErrorDecoder();
    }
}
