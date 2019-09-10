package com.xululu.hookmodule.utils;

import android.app.Instrumentation;
import android.os.Build;
import android.os.Handler;

import com.xululu.hookmodule.EviInstrumentation;
import com.xululu.hookmodule.MockClass1;
import com.xululu.hookmodule.MockClass1s;
import com.xululu.hookmodule.MockClass2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.sql.Ref;

/**
 * Author: llxu4
 * Time: 2019-08-27 13:12
 */
public class AMSHookHelper {

    public static final String EXTRA_TARGET_INTENT = "extra_target_intent";

    public static void hookAMN() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException, NoSuchFieldException {

        Object gDefault = RefInvoke.getStaticFieldObject("android.app.ActivityManagerNative", "gDefault");
        Object mInstance = RefInvoke.getFieldObject("android.util.Singleton", gDefault, "mInstance");

        Class<?> classB2Interface = Class.forName("android.app.IActivityManager");
        Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{classB2Interface}, new MockClass1(mInstance));
        RefInvoke.setFieldObject("android.util.Singleton", gDefault, "mInstance", proxy);

    }

    public static void hookAmNS() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        //获取gdefault实例
        Object gDefault = null;
        if (Build.VERSION.SDK_INT <= 25){
            gDefault = RefInvoke.getStaticFieldObject("android.app.ActivityManagerNative", "gDefault");
        } else {
            gDefault = RefInvoke.getStaticFieldObject("android.app.ActivityManager", "IActivityManagerSingleton");
        }
        Object mInstance = RefInvoke.getFieldObject("android.util.Singleton", gDefault, "mInstance");
        Class classB2Interface = Class.forName("android.app.IActivityManager");
        Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{classB2Interface},
                new MockClass1s(mInstance));
        RefInvoke.setFieldObject("android.util.Singleton", gDefault, "mInstance", proxy);
    }

    public static void attachBaseContext() throws Exception{
        Object currentActivityThread = RefInvoke.getStaticFieldObject("android.app.ActivityThread", "sCurrentActivityThread");
        Handler mH = (Handler) RefInvoke.getFieldObject(currentActivityThread, "mH");
        RefInvoke.setFieldObject(Handler.class, mH, "mCallback", new MockClass2(mH));
    }

    public static void attachContext() throws Exception{
        Object currentActivityThread = RefInvoke.invokeStaticMethod("android.app.ActivityThread", "currentActivityThread");
        Instrumentation mInstrumentation = (Instrumentation) RefInvoke.getFieldObject(currentActivityThread, "mInstrumentation");
        Instrumentation eviInstrumentation = new EviInstrumentation(mInstrumentation);
        RefInvoke.setFieldObject(currentActivityThread, "mInstrumentation", eviInstrumentation);
    }

}
