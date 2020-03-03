package com.xululu.processmodule;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ServiceThreadTest extends Service {

    private static final String TAG = "ServiceThreadTest";

    public ServiceThreadTest() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "service created");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: service destroyed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: service start command");
        return super.onStartCommand(intent,flags, startId);
    }
}
