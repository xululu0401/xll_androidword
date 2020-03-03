package com.xululu.aidlmodule.aidl;

import com.xululu.aidlmodule.aidl.Book;

interface IOnBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}