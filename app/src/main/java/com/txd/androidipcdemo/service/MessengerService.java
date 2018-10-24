package com.txd.androidipcdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.txd.androidipcdemo.App;
import com.txd.androidipcdemo.Constants;
import com.txd.androidipcdemo.utils.ToastUtil;

/**
 * <com.txd.androidipcdemo.service>
 * Created by XiaoDun on 2018/10/24.
 * Feature:
 */

public class MessengerService extends Service{
    private static class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Constants.MSG_FROM_CLIENT:
                    Log.i(Constants.TAG, "receive msg from client:"+msg.getData().getString("msg"));
                    ToastUtil.showLongCenter(App.getContext(), "receive msg from client:" + msg.getData().getString("msg"));
                    Messenger clientMessenger=msg.replyTo;
                    Message replyMessage=Message.obtain(null,Constants.MSG_FROM_SERVICE);
                    Bundle bundle=new Bundle();
                    bundle.putString("reply","你的消息已经收到。");
                    replyMessage.setData(bundle);
                    try {
                        clientMessenger.send(replyMessage);
                    }
                    catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
    
    //这里Messenger的作用是将客户端发送的消息传递给MessengerHandler处理。
    private final Messenger mMessenger = new Messenger(new MessengerHandler());
    
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
