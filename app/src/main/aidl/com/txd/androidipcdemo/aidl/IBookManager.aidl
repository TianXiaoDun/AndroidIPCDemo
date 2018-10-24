// IBookManager.aidl
package com.txd.androidipcdemo.aidl;

// Declare any non-default types here with import statements
import com.txd.androidipcdemo.aidl.Book;
import com.txd.androidipcdemo.aidl.IOnNewBookArrivedListener;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}
