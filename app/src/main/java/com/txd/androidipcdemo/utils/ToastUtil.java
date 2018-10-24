package com.txd.androidipcdemo.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.Toast;

/**
 * ToastUtil <com.ftet.base.utils>
 * Created by XiaoDun on 2017/4/1.
 * Feature:toast
 */
public class ToastUtil {
    
    private static final int GRAVITY_DEFAULT = Gravity.BOTTOM;

    private ToastUtil() {

    }

    /**
     * 显示短时间Toast
     *
     * @param context
     * @param msg
     */
    public static void showShort(Context context, CharSequence msg) {
        showToast(context, msg, Toast.LENGTH_SHORT, GRAVITY_DEFAULT);
    }

    /**
     * 显示短时间Toast
     *
     * @param context
     * @param msg
     */
    public static void showShort(Context context, @StringRes int msg) {
        showToast(context, msg, Toast.LENGTH_SHORT, GRAVITY_DEFAULT);
    }

    /**
     * 显示短时间Toast(中心显示)
     *
     * @param context
     * @param msg
     */
    public static void showShortCenter(Context context, CharSequence msg) {
        showToast(context, msg, Toast.LENGTH_SHORT, Gravity.CENTER);
    }

    /**
     * 避免反复弹出同一Toast
     */
    private static String oldMsg;
    private static long time;
    public static void showToastCenterNoFast(Context context, String msg) {
        if (!msg.equals(oldMsg)) { // 当显示的内容不一样时，即断定为不是同一个Toast
            showToast(context, msg, Toast.LENGTH_SHORT, Gravity.CENTER);
            time = System.currentTimeMillis();
        } else {
            // 显示内容一样时，只有间隔时间大于2秒时才显示
            if (System.currentTimeMillis() - time > 2000) {
                showToast(context, msg, Toast.LENGTH_SHORT, Gravity.CENTER);
                time = System.currentTimeMillis();
            }
        }
        oldMsg = msg;
    }

    /**
     * 显示短时间Toast(中心显示)
     *
     * @param context
     * @param msg
     */
    public static void showShortCenter(Context context, @StringRes int msg) {
        showToast(context, msg, Toast.LENGTH_SHORT, Gravity.CENTER);
    }

    /**
     * 显示长时间Toast
     *
     * @param context
     * @param msg
     */
    public static void showLong(Context context, CharSequence msg) {
        showToast(context, msg, Toast.LENGTH_LONG, GRAVITY_DEFAULT);
    }

    /**
     * 显示长时间Toast
     *
     * @param context
     * @param msg
     */
    public static void showLong(Context context, @StringRes int msg) {
        showToast(context, msg, Toast.LENGTH_LONG, GRAVITY_DEFAULT);
    }

    /**
     * 显示长时间Toast(中心显示)
     *
     * @param context
     * @param msg
     */
    public static void showLongCenter(Context context, CharSequence msg) {
        showToast(context, msg, Toast.LENGTH_LONG, Gravity.CENTER);
    }

    /**
     * 显示长时间Toast(中心显示)
     *
     * @param context
     * @param msg
     */
    public static void showLongCenter(Context context, @StringRes int msg) {
        showToast(context, msg, Toast.LENGTH_LONG, Gravity.CENTER);
    }

    private static void showToast(Context context, CharSequence msg, int duration, int gravity) {
        Toast toast = Toast.makeText(context, msg, duration);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

    private static void showToast(Context context, @StringRes int msg, int duration, int gravity) {
        Toast toast = Toast.makeText(context, msg, duration);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

}