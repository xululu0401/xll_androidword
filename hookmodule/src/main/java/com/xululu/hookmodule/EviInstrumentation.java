package com.xululu.hookmodule;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.xululu.hookmodule.utils.AMSHookHelper;
import com.xululu.hookmodule.utils.RefInvoke;

/**
 * Author: llxu4
 * Time: 2019-08-27 10:13
 */
public class EviInstrumentation extends Instrumentation {

    private static final String TAG = "pipilu EviInstru";

    String packageName = "com.xululu.hookmodule";

    Instrumentation mInstrumentation;

    public EviInstrumentation(Instrumentation instrumentation) {
        mInstrumentation = instrumentation;
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target,
                                            Intent intent, int requestCode, Bundle options){
        Log.d(TAG, "hook log");
        Class[] p1 = {Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, int.class, Bundle.class};
        Object[] v1 = {who, contextThread, token, target, intent, requestCode, options};
        return (ActivityResult) RefInvoke.invokeInstanceMehod(mInstrumentation, "execStartActivity", p1, v1);
    }

    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Intent rawIntent = (Intent)intent.getParcelableExtra(AMSHookHelper.EXTRA_TARGET_INTENT);
        if (null == rawIntent) {
            return mInstrumentation.newActivity(cl, className, intent);
        }
        String newClassName = rawIntent.getComponent().getClassName();
        return mInstrumentation.newActivity(cl, newClassName, rawIntent);
    }
}
