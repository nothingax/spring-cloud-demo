package com.demo.service.impl;

import com.demo.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * Program Name: spring-cloud-demo
 * <p>
 * Description:
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/5/16 4:50 PM
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public int doSomething(String name) {
        System.out.println("com.demo.service.impl.DemoServiceImpl.doSomething 调用...");
        return 0;
    }
}
