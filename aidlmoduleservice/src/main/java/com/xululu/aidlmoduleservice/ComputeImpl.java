package com.xululu.aidlmodule;


import android.os.RemoteException;

import com.xululu.aidlmodule.aidl.ICompute;

/**
 * Author: llxu4
 * Time: 2020-03-03 14:59
 */
public class ComputeImpl extends ICompute.Stub {

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
