package com.demo.remote;

import com.demo.model.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Program Name: leo
 * <p>
 * Description: peningCase 待办案件服务
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/6/14 8:34 PM
 */
@RequestMapping("/remote/person")
public interface PersonRemoteService {

    @RequestMapping("/findById")
    Person findById(@RequestParam("id") String id);
}
