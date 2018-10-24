package com.txd.androidipcdemo.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * <com.txd.binderdemo.aidl>
 * Created by XiaoDun on 2018/10/23.
 * Feature:
 */

public class Book implements Parcelable{
    public int bookId;
    private String bookName;
    
    public Book(int bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(bookId);
        parcel.writeString(bookName);
    }
    
    public static final Creator<Book> CREATOR=new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel parcel) {
            return new Book(parcel);
        }
    
        @Override
        public Book[] newArray(int i) {
            return new Book[i];
        }
    };
    
    private Book(Parcel parcel){
        bookId=parcel.readInt();
        bookName=parcel.readString();
    }
}
