package com.txd.androidipcdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;

import com.txd.androidipcdemo.Constants;
import com.txd.androidipcdemo.R;
import com.txd.androidipcdemo.bean.UserSerializable;
import com.txd.androidipcdemo.utils.PermissionUtil;
import com.txd.androidipcdemo.utils.ToastUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {
    private AppCompatButton bundleAppCompatButton, fileAppCompatButton;
    
    
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {"android.permission.READ_EXTERNAL_STORAGE",
                                                   "android.permission.WRITE_EXTERNAL_STORAGE"};
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initData();
    }
    
    private void initData() {
        PermissionUtil.onRequestMorePermissionsResult(MainActivity.this, PERMISSIONS_STORAGE,
                                                      new PermissionUtil.PermissionCheckCallBack() {
                    @Override
                    public void onHasPermission() {
                    }
                    
                    @Override
                    public void onUserHasAlreadyTurnedDown(String... permission) {
                        ToastUtil.showShortCenter(MainActivity.this, "打开失败，你已拒绝权限，我们需要sd卡读写权限");
                        PermissionUtil.toAppSetting(MainActivity.this);
                    }
                    
                    @Override
                    public void onUserHasAlreadyTurnedDownAndDontAsk(String... permission) {
                        ToastUtil.showShortCenter(MainActivity.this, "打开失败，你已拒绝权限，我们需要sd卡读写权限");
                        PermissionUtil.toAppSetting(MainActivity.this);
                    }
                });
    }
    
    private void initListener() {
        //通过Bundle
        bundleAppCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 1.跳转应用
                 */
                /*Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);*/
                
                /**
                 * 2.跳转指定页面
                 */
                /*Intent intent=new Intent(MainActivity.this,BundleActivity.class);*/
                //隐式（外部应用通过隐式方式跳转）
                Intent intent = new Intent("com.txd.androidipcdemo.bundle");
               /* Bundle mBundle=new Bundle();
                mBundle.put..();
                intent.putExtra("KEY_BUNDLE",mBundle);*/
                
                //通过Activity
                startActivity(intent);
                //通过Service
                /*startService(intent);*/
                //通过Receiver
                /*sendBroadcast(intent);*/
            }
        });
        //通过文件共享
        fileAppCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //动态申请读写权限
                PermissionUtil.checkAndRequestMorePermissions(MainActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE,
                                                              new PermissionUtil.PermissionRequestSuccessCallBack() {
                    @Override
                    public void onHasPermission() {
                        persistToFile();
                        Intent intent = new Intent(MainActivity.this, FileActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
    
    private void initView() {
        bundleAppCompatButton = findViewById(R.id.btn_main_bundle);
        fileAppCompatButton = findViewById(R.id.btn_main_file);
    }
    
    //写入文件
    private void persistToFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserSerializable user = new UserSerializable(1, "hello world", false);
                File dir = new File(Constants.FILE_SHARE_PATH);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File cachedFile = new File(Constants.CACHE_FILE_PATH);
                ObjectOutputStream objectOutputStream = null;
                try {
                    objectOutputStream = new ObjectOutputStream(new FileOutputStream(cachedFile));
                    objectOutputStream.writeObject(user);
                    Log.d(Constants.TAG, "persist user:" + user.toString());
                }
                catch (IOException e) {
                    e.printStackTrace();
                    Log.d(Constants.TAG, "persist IOException:" + e.getLocalizedMessage());
                }
                finally {
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
