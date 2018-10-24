package com.txd.androidipcdemo;

import android.app.Application;
import android.content.Context;

/**
 * <com.txd.androidipcdemo>
 * Created by XiaoDun on 2018/10/24.
 * Feature:
 */

public class App extends Application{
    
    private static Context mContext;
    
    @Override
    public void onCreate() {
        super.onCreate();
        //获取context
        mContext = getApplicationContext();
    }
    
    public static Context getContext(){
        return mContext;
    }
}
