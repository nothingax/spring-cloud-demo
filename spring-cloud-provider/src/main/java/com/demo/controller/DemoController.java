package com.demo.controller;

import com.demo.service.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Program Name: spring-cloud-demo
 * <p>
 * Description: provider  demo Controller
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/5/16 3:08 PM
 */
@RestController
@RequestMapping("demoController")
public class DemoController {

    @Resource
    private DemoService demoService;

    @RequestMapping("demoMethod")
    void demo(String name){
        System.out.println(name);
        demoService.doSomething("hello");
    }
}
