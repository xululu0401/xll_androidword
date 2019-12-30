package com.xululu.hookmodule;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.xululu.hookmodule.utils.AMSHookHelper;
import com.xululu.hookmodule.utils.RefInvoke;

/**
 * Author: pipilu
 * Time: 2019-08-28 01:03
 */
public class MockClass2 implements Handler.Callback {

    Handler mBase;

    public MockClass2(Handler base){
        mBase = base;
    }


    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 100:
                handleLaunchActivity(msg);
                break;
        }
        mBase.handleMessage(msg);
        return true;
    }

    private void handleLaunchActivity(Message msg){
        Object obj = msg.obj;
        Intent intent = (Intent) RefInvoke.getFieldObject(obj, "intent");
        Intent targetIntent = intent.getParcelableExtra(AMSHookHelper.EXTRA_TARGET_INTENT);
        intent.setComponent(targetIntent.getComponent());
    }
}
