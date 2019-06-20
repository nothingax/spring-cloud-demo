package com.demo.model;

/**
 *
 * 模型、实体类
 * @author zhangjianwei
 * @date 2019/1/10
 */
public class Person {
    private String name;
    private String age;
    private Dog dog;

    public Person() {
    }

    public Person(Dog dog) {
        this.dog = dog;
    }

    public Person(String name, String age, Dog dog) {
        this.name = name;
        this.age = age;
        this.dog = dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", dog=" + dog +
                '}';
    }
}
