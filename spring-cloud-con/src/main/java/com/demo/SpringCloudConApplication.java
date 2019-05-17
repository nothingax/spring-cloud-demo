package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * 服务消费者
 * 开启 feign
 * 开启 eureka客户端
 * @author zhangjianwei
 */
@EnableFeignClients
// @EnableDiscoveryClient
@SpringBootApplication
// @ComponentScan(basePackages = {"com.demo.*"})
public class SpringCloudConApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConApplication.class, args);
    }

}
