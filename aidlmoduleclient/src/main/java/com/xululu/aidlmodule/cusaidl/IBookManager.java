package com.xululu.aidlmodule.cusaidl;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

import com.xululu.aidlmodule.aidl.Book;

import java.util.List;

/**
 * Author: pipilu
 * Time: 2020-02-24 22:07
 */
public interface IBookManager extends IInterface {

    static final String DESCRIPTOR = "com.xululu.aidlmodule.cusaidl.IBookManager";

    static final int TRANSACTION_getBookList = IBinder.FIRST_CALL_TRANSACTION + 0;
    static final int TRANSACTION_addBook = IBinder.FIRST_CALL_TRANSACTION + 1;

    public List<Book> getBookList() throws RemoteException;
    public void addBook(Book book) throws RemoteException;
}
