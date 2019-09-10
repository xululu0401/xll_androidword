package com.xululu.lifecylcedemo;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

/**
 * Author: llxu4
 * Time: 2019-09-02 20:43
 */
public class LifecyclePresenter implements IPresenter{

    private static final String TAG = "LifecyclePresenter  pipilu";

    @Override
    public void onCreate(LifecycleOwner owner) {
        Log.d(TAG, "lifecycle oncreate");
    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        Log.d(TAG, "lifecycle onDestroy");
    }

    @Override
    public void onLifecycleChanged(LifecycleOwner owner, Lifecycle.Event event) {
        Log.d(TAG, "lifecycle onLifecycleChanged");

    }
}
