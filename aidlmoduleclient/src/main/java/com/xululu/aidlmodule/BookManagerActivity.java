package com.xululu.aidlmodule;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.xululu.aidlmodule.aidl.Book;
import com.xululu.aidlmodule.aidl.IBookManager;
import com.xululu.aidlmodule.aidl.IOnBookArrivedListener;

import java.util.List;

/**
 * Author: pipilu
 * Time: 2020-02-27 00:13
 */
public class BookManagerActivity extends Activity {

    private static final String TAG = "TAG_BookManagerActivity";

    private static final int MSG_NEW_BOOK_ARRIVED = 1;

    private IBookManager mRemoteManager;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case MSG_NEW_BOOK_ARRIVED:
                    Log.d(TAG, "handleMessage: receive new book:  " + msg.obj);
                    break;
            }

        }
    };

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager bookManager = IBookManager.Stub.asInterface(service);
            mRemoteManager = bookManager;
            try {
                List<Book> list = bookManager.getBookList();
                Log.i(TAG, "onServiceConnected: query book list, list type: " + list.getClass().getCanonicalName());
                Log.i(TAG, "onServiceConnected: query book list: " + list.toString());

                Book newBook = new Book(4, "python");
                bookManager.addBook(newBook);
                list = bookManager.getBookList();
                Log.i(TAG, "onServiceConnected: query book after add: " + list.toString());

                mRemoteManager.registerListener(mBookarrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private IOnBookArrivedListener mBookarrivedListener = new IOnBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            mHandler.obtainMessage(MSG_NEW_BOOK_ARRIVED, newBook).sendToTarget();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.xululu.aidlmoduleservice", "com.xululu.aidlmoduleservice.aidlservice.BookManagerService"));
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        if (null != mRemoteManager && mRemoteManager.asBinder().isBinderAlive()) {
            Log.d(TAG, "onDestroy: unregister listener:" + mBookarrivedListener);
            try {
                mRemoteManager.unregisterListener(mBookarrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }
}
