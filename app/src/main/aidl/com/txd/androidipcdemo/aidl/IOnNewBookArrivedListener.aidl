// IOnNewBookArrivedListener.aidl
package com.txd.androidipcdemo.aidl;

// Declare any non-default types here with import statements
import com.txd.androidipcdemo.aidl.Book;
interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
