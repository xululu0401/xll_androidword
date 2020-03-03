package com.xululu.aidlmodule.aidl;

import com.xululu.aidlmodule.aidl.Book;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}