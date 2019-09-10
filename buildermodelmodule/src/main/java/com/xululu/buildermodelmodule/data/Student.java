package com.xululu.buildermodelmodule.data;

import android.graphics.Bitmap;
import android.os.Build;

/**
 * Author: llxu4
 * Time: 2019-08-06 22:13
 */
public class Student extends AbsStudent{

    private Student(){}

    private Student(Builder builder) {
        this.stuId = builder.student.getStuId();
        this.name = builder.student.getName();
        this.age = builder.student.getAge();
    }

    @Override
    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    public static class Builder{
        Student student = new Student();
        public Builder setStuId(String stuId){
            student.setStuId(stuId);
            return this;
        }

        public Builder setName(String name) {
            student.setName(name);
            return this;
        }

        public Builder setAge(int age) {
            student.setAge(age);
            return this;
        }

        public Student build(){
            return new Student(this);
        }

    }


}
