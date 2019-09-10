package com.xululu.plugindemo;

import android.os.Build;

import com.xululu.mypluginlibrary.IBean;

/**
 * Author: pipilu
 * Time: 2019-08-28 18:49
 */
public class Bean implements IBean {

    private String name = "pipilu";

    @Override
    public String getName() {
        return name +"   ***   "+ VersionHelper.version;
    }

    @Override
    public void setName(String paramString) {
        name = paramString;
    }
}
