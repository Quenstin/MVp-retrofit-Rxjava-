package com.example.mtestlibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * 创建作者:zhuguoqing
 * 创建时间: 2018/8/13
 * 作用:键值对储存
 */
public class SPUtil {
    private static final String SP_NAME = "CYF";

    public static void add(Context context, String name, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name, value);
        editor.commit();
    }

    public static String getString(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    public static void clear(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(key).commit();
    }



    //储存boolean
    public static void addBoolean(Context context, String key, boolean b) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit().putBoolean(key, b);
        editor.commit();
    }

    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    //储存boolean
    public static void addLongId(Context context, String key, long b) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit().putLong(key, b);
        editor.commit();
    }

    public static long getLongId(Context context, String key,long l) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, l);
    }

}
