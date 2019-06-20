package com.demo.restservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Program Name: spring-cloud-demo
 * <p>
 * Description: feign client
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/5/16 5:11 PM
 */
@FeignClient("provider-demo"/* provider工程的项目名，注册到了eureka中 */)
public interface DemoClient {

    /**
     * value 值是另一个服务（provider-demo）controller 中的接口
     * @param anyName 任何名称均可以
     */
    @RequestMapping(method = RequestMethod.GET, value = "/demoController/demoMethod")
    void invokeProvider(@RequestParam("name")/*RequestParam里的名称必须和服务提供者的接口名称相同 */
                                String anyName);

    // TODO 添加其他类型（如实体）参数的接口
}
