package com.xululu.activitylifecylemoduel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CloseAppReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        System.exit(0);
    }
}
