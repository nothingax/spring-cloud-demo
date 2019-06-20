package com.demo.service.impl;

import com.demo.model.Person;

/**
 * Program Name: spring-cloud-demo
 * <p>
 * Description:
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/6/20 3:06 PM
 */
public interface PersonService {

    Person findById(String id);
}
