package com.demo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @author zhangjianwei
 */
@SpringBootApplication
public class SpringCloudGatewayApplication {


    /**
     * 基本的转发
     * 当访问http://localhost:8080/baidu
     * 转发到http://baidu.com
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                //basic proxy
                .route(r ->r.path("/baidu")
                        .uri("http://baidu.com:80/").id("baidu_route")
                ).build();
    }

    // TODO filter 添加；

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }

}
