package com.xululu.plugindemo;

import android.app.Application;

/**
 * Author: pipilu
 * Time: 2019-08-28 20:20
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VersionHelper.version = getApplicationInfo().targetSdkVersion;
    }
}
