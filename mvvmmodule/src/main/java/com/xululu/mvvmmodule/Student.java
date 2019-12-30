package com.xululu.mvvmmodule;

/**
 * Author: pipilu
 * Time: 2019-07-15 14:23
 */
public class Student {

    private String name;
    private String stuId;
    private int age;

    public Student(String name, String stuId, int age){
        this.name = name;
        this.stuId = stuId;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
