package com.xululu.aidlmoduleservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;


/**
 * Author: pipilu
 * Time: 2020-02-24 23:58
 */
public class MessengerService extends Service {

    private static final String TAG = "MessengerService";

    private static class MessengerHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.MSG_FROM_CLIENT:
                    Log.d(TAG, "receive msg from Client" + msg.getData().getString("msg"));
                    Messenger client = msg.replyTo;
                    Message replyMsg = Message.obtain(null, Constants.MSG_FROM_SERVICE);
                    Bundle data = new Bundle();
                    data.putString("reply", "收到，这是回复消息");
                    replyMsg.setData(data);
                    try {
                        client.send(replyMsg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    super.handleMessage(msg);
            }

        }
    }

    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
