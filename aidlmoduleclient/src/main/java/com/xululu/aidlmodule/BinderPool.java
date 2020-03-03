package com.xululu.aidlmoduleservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.xululu.aidlmodule.aidl.IBindPool;
import com.xululu.aidlmoduleservice.aidlservice.BindPoolService;

import java.util.concurrent.CountDownLatch;

/**
 * binder连接池
 * Author: llxu4
 * Time: 2020-03-03 15:34
 */
public class BinderPool {

    private static final String TAG = "TAG_BinderPool";
    public static final int BINDER_NONE = -1;
    public static final int BINDER_COMPUTE = 0;
    public static final int BINDER_SECURITY_CENTER = 1;

    private Context mContext;
    private IBindPool mBinderPool;
    private static volatile BinderPool sInstance;
    private CountDownLatch mCountDownLatch;

    private BinderPool(Context context) {
        mContext = context.getApplicationContext();
        connectBindService();

    }

    private synchronized void connectBindService() {
        mCountDownLatch = new CountDownLatch(1);
        Intent i = new Intent(mContext, BindPoolService.class);
        mContext.bindService(i, mBindPoolConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mBindPoolConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinderPool = IBindPool.Stub.asInterface(service);
            try {
                mBinderPool.asBinder().linkToDeath(mBindPoolDeathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mCountDownLatch.countDown();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private IBinder.DeathRecipient mBindPoolDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            mBinderPool.asBinder().unlinkToDeath(mBindPoolDeathRecipient, 0);
            mBinderPool = null;
            connectBindService();
        }
    };

    public static BinderPool getInstance(Context context) {
        if (sInstance == null) {
            synchronized (BinderPool.class) {
                if (sInstance == null) {
                    sInstance = new BinderPool(context);
                }
            }
        }
        return sInstance;
    }


    public static class BinderPoolImpl extends IBindPool.Stub {


        @Override
        public IBinder queryBinder(int binderCode) throws RemoteException {
            switch (binderCode) {
                case BINDER_SECURITY_CENTER:
                    return new SecurityCenterImpl();
                case BINDER_COMPUTE:
                    return new ComputeImpl();
                default:
                    break;
            }
            return null;
        }
    }


}
