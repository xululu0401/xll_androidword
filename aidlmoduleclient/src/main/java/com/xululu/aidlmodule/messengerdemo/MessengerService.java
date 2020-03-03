package com.xululu.aidlmodule.messengerdemo;

import android.app.Service;
import android.os.Handler;
import android.os.Message;

/**
 * Author: llxu4
 * Time: 2020-02-24 23:58
 */
public class MessengerService extends Service {

    private static final String TAG = "MessengerService";

    private static class MessengerHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

            }
            super.handleMessage(msg);
        }
    }
}
