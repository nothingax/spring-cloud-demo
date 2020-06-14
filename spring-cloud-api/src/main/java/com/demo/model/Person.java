package com.demo.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * 模型、实体类
 * @author zhangjianwei
 * @date 2019/1/10
 */
@Data
@Accessors(chain = true)
public class Person {
    private String name;
    private String age;
    private Dog dog;

    private String headPicUrl;

}
