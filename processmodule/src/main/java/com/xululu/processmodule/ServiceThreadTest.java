package com.xululu.processmodule;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ServiceThreadText extends Service {
    public ServiceThreadText() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
