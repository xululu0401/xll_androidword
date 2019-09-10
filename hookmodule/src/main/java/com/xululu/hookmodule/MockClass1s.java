package com.xululu.hookmodule;

import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;

import com.xululu.hookmodule.utils.AMSHookHelper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Author: llxu4
 * Time: 2019-08-27 23:38
 */
public class MockClass1s implements InvocationHandler {
    private static final String TAG = "MockClass1s";

    Object mBase;
    public MockClass1s(Object base){
        mBase = base;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.d(TAG, method.getName());
        if ("startActivity".equals(method.getName())){
            //拦截启动方法，替换掉要启动的activity
            Intent raw;
            int index = 0;
            for (int i = 0;i < args.length;i++){
                if (args[i] instanceof Intent) {
                    index = i;
                    break;
                }
            }
            raw = (Intent)args[index];
            Intent newIntent = new Intent();
            String stubPackage = raw.getComponent().getPackageName();
            ComponentName componentName = new ComponentName(stubPackage, StubActivity.class.getName());
            newIntent.setComponent(componentName);
            newIntent.putExtra(AMSHookHelper.EXTRA_TARGET_INTENT, raw);
            args[index] = newIntent;
            Log.d(TAG, "hook success");
            return method.invoke(mBase, args);
        }
        return method.invoke(mBase, args);
    }
}
