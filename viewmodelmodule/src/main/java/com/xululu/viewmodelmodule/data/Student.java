package com.xululu.viewmodelmodule.data;

/**
 * Author: pipilu
 * Time: 2019-08-09 22:49
 */
public class Student {

    public Student(){}

    public Student(String stuId, String name, int age) {
        this.stuId = stuId;
        this.age = age;
        this.name = name;
    }

    private String name;
    private int age;
    private String stuId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }
}
