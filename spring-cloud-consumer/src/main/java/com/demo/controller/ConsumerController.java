package com.demo.controller;

import com.demo.common.WebResp;
import com.demo.feignclient.PersonClient;
import com.demo.model.Person;
import com.demo.restservice.DemoClient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Program Name: spring-cloud-demo
 * <p>
 * Description: ConsumerController
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/5/16 3:08 PM
 */
@RestController
@RequestMapping("consumerController")
public class ConsumerController {
    @Resource
    private DemoClient demoClient;
    @Resource
    private PersonClient personClient;

    @RequestMapping("invokeProvider")
    Object demo(){
        demoClient.invokeProvider("hahhah");
        System.out.println("请求 demo 成功");
        return true;
    }


    @RequestMapping("testFeign")
    Object testFeign(){
        Person person = personClient.findById("2323");
        System.out.println(person);
        return person;
    }

    @RequestMapping("testGlobalExceptionHandler")
    WebResp testGlobalExceptionHandler(){
        throw new RuntimeException("抛出自定义或其他异常");
    }

    /**
     * 模拟访问下游服务，下游响应慢，触发fallback
     */
    @RequestMapping("testFallback")
    WebResp testFallback(){
        Person person = personClient.findPersonHeadPicReturnSlow("1");
        return WebResp.ok(person);
    }


    /**
     * 模拟访问下游服务，下游响应慢，触发fallback
     */
    @RequestMapping("testProviderException")
    WebResp testProviderException(){
        Person person = personClient.testProviderException("1");
        return WebResp.ok(person);
    }

}
