package com.txd.androidipcdemo.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.txd.androidipcdemo.Constants;
import com.txd.androidipcdemo.R;
import com.txd.androidipcdemo.aidl.Book;
import com.txd.androidipcdemo.aidl.BookManagerService;
import com.txd.androidipcdemo.aidl.IBookManager;
import com.txd.androidipcdemo.aidl.IOnNewBookArrivedListener;

import java.util.List;


public class AidlActivity extends AppCompatActivity {
    
    private static final int MESSAGE_NEW_BOOK_ARRIVED = 1;
    
    private IBookManager mRemoteBookManager;
    
    private Handler mHandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_NEW_BOOK_ARRIVED:
                    Book newBook= (Book) msg.obj;
                    Log.d(Constants.TAG, "receive new book:" + new Gson().toJson(newBook));
                    break;
            }
            return false;
        }
    });
    
    private IOnNewBookArrivedListener mIOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, newBook).sendToTarget();
        }
    };
    
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IBookManager bookManager = IBookManager.Stub.asInterface(iBinder);
            try {
                mRemoteBookManager = bookManager;
                List<Book> list = bookManager.getBookList();
                Log.i(Constants.TAG, "query book list:" + new Gson().toJson(list));
                Book newBook = new Book(3, "Windows");
                bookManager.addBook(newBook);
                List<Book> newList = bookManager.getBookList();
                Log.i(Constants.TAG, "query book list:" + new Gson().toJson(newList));
                bookManager.registerListener(mIOnNewBookArrivedListener);
            }
            catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }
    
    @Override
    protected void onDestroy() {
        if (mRemoteBookManager != null && mRemoteBookManager.asBinder().isBinderAlive()) {
            try {
                Log.i(Constants.TAG, "unregister listener:"+mIOnNewBookArrivedListener);
                mRemoteBookManager.unregisterListener(mIOnNewBookArrivedListener);
            }
            catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(mServiceConnection);
        super.onDestroy();
    }
    
}
