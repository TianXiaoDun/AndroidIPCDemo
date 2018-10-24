package com.txd.androidipcdemo.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;

import com.txd.androidipcdemo.App;
import com.txd.androidipcdemo.Constants;
import com.txd.androidipcdemo.R;
import com.txd.androidipcdemo.utils.ToastUtil;

public class MessengerActivity extends AppCompatActivity {
    private Messenger mMessenger;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mMessenger = new Messenger(iBinder);
            Message msg = Message.obtain(null, Constants.MSG_FROM_CLIENT);
            Bundle data = new Bundle();
            data.putString("msg", "hello,this is client.");
            msg.setData(data);
            msg.replyTo=mGetReplyMessenger;
            try {
                mMessenger.send(msg);
            }
            catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        
        }
    };
    private Messenger mGetReplyMessenger = new Messenger(new MessengerHandler());
    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.MSG_FROM_SERVICE:
                    Log.i(Constants.TAG, "receive msg from Service:" + msg.getData().getString("reply"));
                    ToastUtil.showLongCenter(App.getContext(), "receive msg from Service:" + msg.getData().getString("reply"));
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
    
    private AppCompatButton sendAppCompatButton;
    private AppCompatEditText msgAppCompatEditText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        initView();
        initListener();

//        service启动 . 方式一：
//        注意:android5.0之前是可以通过设置隐式意图来跨应用打开Service的,5.0之后就必须要通过显示意图来开启Service.
//        Intent intent=new Intent(this, MessengerService.class);

//        跨应用service启动 . 方式二：
//        ComponentName的参数1:目标app的包名,参数2:目标app的Service完整类名
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.txd.androidipcdemo", "com.txd.androidipcdemo.service" +
                                                                        ".MessengerService"));
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }
    
    private void initListener() {
        sendAppCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msgStr = msgAppCompatEditText.getText().toString().trim();
                Message msg = Message.obtain(null, Constants.MSG_FROM_CLIENT);
                Bundle data = new Bundle();
                data.putString("msg", msgStr);
                msg.setData(data);
                msg.replyTo=mGetReplyMessenger;
                try {
                    mMessenger.send(msg);
                }
                catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private void initView() {
        sendAppCompatButton = findViewById(R.id.btn_messenger_send);
        msgAppCompatEditText = findViewById(R.id.et_messenger_msg);
    }
    
    @Override
    protected void onDestroy() {
        unbindService(mServiceConnection);
        super.onDestroy();
    }
}
