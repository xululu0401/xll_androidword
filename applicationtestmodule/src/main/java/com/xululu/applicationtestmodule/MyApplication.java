package com.xululu.applicationtestmodule;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;

/**
 * Author: llxu4
 * Time: 2019/6/19 22:35
 */
public class MyApplication extends Application {


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void registerComponentCallbacks(ComponentCallbacks callback) {
        super.registerComponentCallbacks(callback);
    }

    @Override
    public void unregisterComponentCallbacks(ComponentCallbacks callback) {
        super.unregisterComponentCallbacks(callback);
    }

    @Override
    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.registerActivityLifecycleCallbacks(callback);
    }

    @Override
    public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.unregisterActivityLifecycleCallbacks(callback);
    }

    @Override
    public void registerOnProvideAssistDataListener(OnProvideAssistDataListener callback) {
        super.registerOnProvideAssistDataListener(callback);
    }

    @Override
    public void unregisterOnProvideAssistDataListener(OnProvideAssistDataListener callback) {
        super.unregisterOnProvideAssistDataListener(callback);
    }
}
