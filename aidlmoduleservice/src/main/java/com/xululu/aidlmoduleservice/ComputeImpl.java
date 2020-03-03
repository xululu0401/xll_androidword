package com.xululu.aidlmoduleservice;


import android.os.RemoteException;
import android.util.Log;

import com.xululu.aidlmodule.aidl.ICompute;

/**
 * Author: pipilu
 * Time: 2020-03-03 14:59
 */
public class ComputeImpl extends ICompute.Stub {

    private static final String TAG = "TAG_ComputeImpl";

    @Override
    public int add(int a, int b) throws RemoteException {
        Log.d(TAG, "add is called in service");
        return a + b;
    }
}
