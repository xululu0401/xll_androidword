package com.xululu.aidlmodule.aidl;

import com.xululu.aidlmodule.aidl.Book;
import com.xululu.aidlmodule.aidl.IOnBookArrivedListener;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);

    void registerListener(IOnBookArrivedListener listener);
    void unregisterListener(IOnBookArrivedListener listener);
}