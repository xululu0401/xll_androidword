package com.xululu.aidlmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.xululu.aidlmodule.aidl.ICompute;
import com.xululu.aidlmodule.aidl.ISecurityCenter;

public class BinderPoolActivity extends AppCompatActivity {

    private static final String TAG = "TAG_BinderPoolActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_pool);

        new Thread(new Runnable() {
            @Override
            public void run() {
                BinderPool binderPool = BinderPool.getInstance(BinderPoolActivity.this);
                IBinder secBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER);
                Log.d(TAG, "run: binder is " + secBinder);
                ISecurityCenter securityCenter = ISecurityCenter.Stub.asInterface(secBinder);
                Log.d(TAG, "run: sec binder is " + secBinder);

                String msg = "hello js";

                try {
                    String psw = securityCenter.encrypt(msg);
                    Log.d(TAG, "run: encrypt:  " + psw);

                    Log.d(TAG, "run: descrypt:  " + securityCenter.decrypt(psw));

                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "run: visit ICompute");
                IBinder binder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
                Log.d(TAG, "run: binder is " + binder);

                ICompute compute = ICompute.Stub.asInterface(binder);

                Log.d(TAG, "run: compute binder is " + binder);


                try {
                    Log.d(TAG, "run: 3 + 5 = " + compute.add(3, 5));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
