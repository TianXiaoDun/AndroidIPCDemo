package com.txd.androidipcdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.txd.androidipcdemo.Constants;
import com.txd.androidipcdemo.R;
import com.txd.androidipcdemo.bean.UserSerializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class FileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        recoverFromFile();
    }
    
    //从文件恢复
    private void recoverFromFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserSerializable user = null;
                File cachedFile = new File(Constants.CACHE_FILE_PATH);
                if (cachedFile.exists()) {
                    ObjectInputStream objectInputStream = null;
                    try {
                        objectInputStream = new ObjectInputStream(new FileInputStream(cachedFile));
                        user = (UserSerializable) objectInputStream.readObject();
                        Log.d(Constants.TAG, "recover user:"+user.toString());
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        Log.d(Constants.TAG, "recover Exception:"+e.getLocalizedMessage());
                    }finally {
                        if(objectInputStream!=null){
                            try {
                                objectInputStream.close();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }).start();
    }
}
