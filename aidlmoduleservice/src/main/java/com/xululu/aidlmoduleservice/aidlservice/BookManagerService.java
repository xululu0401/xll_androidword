package com.xululu.aidlmoduleservice.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.xululu.aidlmodule.aidl.Book;
import com.xululu.aidlmodule.aidl.IBookManager;
import com.xululu.aidlmodule.aidl.IOnBookArrivedListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Author: pipilu
 * Time: 2020-02-26 08:50
 */
public class BookManagerService extends Service {

    private static final String TAG = "TAG_BookManagerService";

    private AtomicBoolean mServiceAlive = new AtomicBoolean(true);

    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<Book>();

    private RemoteCallbackList<IOnBookArrivedListener> mListeners = new RemoteCallbackList<>();

    private Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public void registerListener(IOnBookArrivedListener listener) throws RemoteException {
            mListeners.register(listener);

        }

        @Override
        public void unregisterListener(IOnBookArrivedListener listener) throws RemoteException {

            mListeners.unregister(listener);
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int check = checkCallingOrSelfPermission("com.xululu.aidlmoduleservice.permission.ACCESS_BOOK_SERVICE");
            if (check == PackageManager.PERMISSION_DENIED) {
                return false;
            }

            String pkgName = null;
            String[] pkgs = getPackageManager().getPackagesForUid(getCallingUid());
            if (null != pkgs && pkgs.length > 0) {
                pkgName = pkgs[0];
            }
            if (null != pkgName && !pkgName.startsWith("com.xululu")) {
                return false;
            }
//            return true;
            return super.onTransact(code, data, reply, flags);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "Android"));
        mBookList.add(new Book(2, "flutter"));
        mBookList.add(new Book(3, "js"));

        new Thread(new ServiceWork()).start();
    }

    private class ServiceWork implements Runnable {
        @Override
        public void run() {
            while (mServiceAlive.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int bookId = mBookList.size() + 1;
                Book newBook = new Book(bookId, "new book " + bookId);
                try {
                    onBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void onBookArrived(Book book) throws RemoteException {
        mBookList.add(book);
//        Log.d(TAG, "onBookArrived: notify listeners:  " + mListeners.size());
//        for (IOnBookArrivedListener listener : mListeners) {
//            Log.d(TAG, "onBookArrived: notify listener: " + listener);
//            listener.onNewBookArrived(book);
//        }
        int n = mListeners.beginBroadcast();
        for (int i = 0; i < n; i++) {
            IOnBookArrivedListener listener = mListeners.getBroadcastItem(i);
            if (null != listener) {
                listener.onNewBookArrived(book);
            }
        }

        mListeners.finishBroadcast();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
