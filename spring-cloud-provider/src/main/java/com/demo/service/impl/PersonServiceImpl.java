package com.demo.service.impl;

import com.demo.model.Person;
import org.springframework.stereotype.Service;

/**
 * Program Name: spring-cloud-demo
 * <p>
 * Description:
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/6/20 3:07 PM
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Override
    public Person findById(String id) {
        Person person = new Person();
        person.setName("登封");
        return person;
    }
}
