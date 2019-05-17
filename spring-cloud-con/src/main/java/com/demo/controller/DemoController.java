package com.demo.controller;

import com.demo.restservice.DemoClient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Program Name: spring-cloud-demo
 * <p>
 * Description: demo Controller
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/5/16 3:08 PM
 */
@RestController
@RequestMapping("consumerController")
public class DemoController {
    @Resource
    private DemoClient demoClient;

    @RequestMapping("invokeProvider")
    Object demo(){
        demoClient.invokeProvider("hahhah");
        System.out.println("请求 demo 成功");
        return true;
    }
}
