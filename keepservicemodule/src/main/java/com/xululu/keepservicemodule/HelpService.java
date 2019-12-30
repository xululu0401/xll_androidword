package com.xululu.keepservicemodule;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Author: pipilu
 * Time: 2019-09-18 22:48
 */
public class HelpService extends Service {

    private static final String TAG = "HelpService";

    class HelpeBinder extends Binder{
        public void startForegroundNoti(int id, Notification notification){
            startForeground(id, notification);
        }

        public void stopForegroundNoti(int id){
            stopForeground(id);
        }
    }

    private HelpeBinder mBinder = new HelpeBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "help service oncreate");


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "help service onStartcommand");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "help service ondestroy");

    }
}
