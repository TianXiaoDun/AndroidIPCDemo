package com.txd.androidipcdemo.utils;

import android.os.Environment;

/**
 * <com.txd.androidipcdemo.utils>
 * Created by XiaoDun on 2018/10/23.
 * Feature:
 */

public class FileUtil {
    
    /**
     * 获取内置SD卡路径
     * @return
     */
    public static String getInnerSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }
}
