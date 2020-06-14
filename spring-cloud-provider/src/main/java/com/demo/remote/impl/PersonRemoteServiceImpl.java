package com.demo.remote.impl;

import com.demo.exception.BizException;
import com.demo.model.Person;
import com.demo.remote.PersonRemoteService;
import com.demo.service.impl.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Program Name: spring-cloud-demo
 * <p>
 * Description:
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/6/20 3:05 PM
 */
@RestController
@Slf4j
public class PersonRemoteServiceImpl implements PersonRemoteService {
    @Resource
    private PersonService personService;

    @Override
    public Person findById(String id) {
        log.info("provider执行");
        Person person = personService.findById(id);
        throw new BizException(2323, "业务异常message");
        // return person;
    }

    @Override
    public Person findPersonHeadPicReturnSlow(String id) {
        // 模拟超长的响应时间
        try {
            Thread.sleep(10000000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Person testProviderException(String s) {
        log.error("provider 是否重试执行？");
        throw new RuntimeException("provider：执行出错，抛出异常");
    }
}
