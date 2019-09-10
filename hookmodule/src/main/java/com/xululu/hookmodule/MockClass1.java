package com.xululu.hookmodule;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Author: llxu4
 * Time: 2019-08-27 13:22
 */
public class MockClass1 implements InvocationHandler {

    private static final String TAG = "MockClass1";

    Object mBase;

    public MockClass1(Object base) {
        mBase = base;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("startActivity".equals(method.getName())) {
            Log.d(TAG,method.getName());
        }
        return method.invoke(mBase, args);
    }
}
