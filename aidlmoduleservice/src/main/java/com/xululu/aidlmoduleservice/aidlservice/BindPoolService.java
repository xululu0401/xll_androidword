package com.xululu.aidlmoduleservice.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.xululu.aidlmoduleservice.BinderPool;

/**
 * Author: pipilu
 * Time: 2020-03-03 15:47
 */
public class BindPoolService extends Service {

    private static final String TAG = "BindPoolService";
    private Binder mBindPool = new BinderPool.BinderPoolImpl();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: onBind");
        return mBindPool;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
