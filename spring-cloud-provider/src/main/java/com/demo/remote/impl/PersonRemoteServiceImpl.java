package com.demo.remote.impl;

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
        return personService.findById(id);
    }
}
