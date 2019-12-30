package com.xululu.buildermodelmodule.data;

/**
 * Author: pipilu
 * Time: 2019-08-06 22:21
 */
public abstract class AbsStudent {
    protected String stuId;
    protected String name;
    protected int age;

    public abstract void setStuId(String stuId);


    public abstract void setName(String name);


    public abstract void setAge(int age);

    public String getStuId() {
        return stuId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
