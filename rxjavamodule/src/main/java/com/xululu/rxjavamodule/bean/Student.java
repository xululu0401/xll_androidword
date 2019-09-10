package com.xululu.rxjavamodule.bean;

import java.util.List;

/**
 * Author: llxu4
 * Time: 2019-07-27 14:36
 */
public class Student {

    private String name;
    private int age;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    private List<Course> courses;

    public Student() {

    }

    public Student(String name, int age){
        this.name = name;
        this.age = age;
    }

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


}
