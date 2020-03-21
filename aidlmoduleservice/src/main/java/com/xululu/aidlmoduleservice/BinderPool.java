package com.xululu.aidlmoduleservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.xululu.aidlmodule.aidl.IBindPool;
import com.xululu.aidlmoduleservice.aidlservice.BindPoolService;

import java.util.concurrent.CountDownLatch;

/**
 * binder连接池
 * Author: pipilu
 * Time: 2020-03-03 15:34
 */
public class BinderPool {

    private static final String TAG = "TAG_BinderPool";
    public static final int BINDER_NONE = -1;
    public static final int BINDER_COMPUTE = 0;
    public static final int BINDER_SECURITY_CENTER = 1;

    public static class BinderPoolImpl extends IBindPool.Stub {


        @Override
        public IBinder queryBinder(int binderCode) throws RemoteException {
            switch (binderCode) {
                case BINDER_SECURITY_CENTER:
                    Log.d(TAG, "queryBinder: return sec binder");
                    return new SecurityCenterImpl();
                case BINDER_COMPUTE:
                    Log.d(TAG, "queryBinder: return compute binder");
                    return new ComputeImpl();
                default:
                    break;
            }
            return null;
        }
    }


}
