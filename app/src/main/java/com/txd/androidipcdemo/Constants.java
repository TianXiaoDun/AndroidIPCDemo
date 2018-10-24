package com.txd.androidipcdemo;

import com.txd.androidipcdemo.utils.FileUtil;

import java.io.File;

/**
 * <com.txd.androidipcdemo>
 * Created by XiaoDun on 2018/10/23.
 * Feature:
 */

public class Constants {
    public static final String FILE_SHARE_PATH= FileUtil.getInnerSDCardPath();
    public static final String CACHE_FILE_PATH= FileUtil.getInnerSDCardPath() + File.separator + "cache.txt";
    public static final String TAG="AndroidIPCDemo";
    public static final int MSG_FROM_CLIENT=0;
    public static final int MSG_FROM_SERVICE=1;
}
