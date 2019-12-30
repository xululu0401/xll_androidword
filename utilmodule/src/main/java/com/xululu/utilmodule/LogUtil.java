package com.xululu.utilmodule;

import android.util.Log;

/**
 * 日志类，封装日志级别
 * Author: pipilu
 * Time: 2019-07-25 23:30
 */
public class LogUtil {

    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;

    private static int level = VERBOSE;
    private static boolean mDebugable;

    //判断有没有被设置；如果没有被设置，使用buildtype配置，否则根据设置确定是否打印日志；
    private static boolean mIsDebugableSetted;

    public static void setLevel(int logLevel) {
        level = logLevel;
    }

    /**
     *
     * @param debugable
     */
    public static void setDebugable(boolean debugable) {
        mDebugable = debugable;
        mIsDebugableSetted = true;
    }

    private static boolean isDebugable() {
        if (mIsDebugableSetted) {
            return mDebugable;
        } else {
            return BuildConfig.DEBUG;
        }
    }

    public static void v(String tag, String log) {
        if (isDebugable() && level <= VERBOSE) {
            Log.v(tag, log);
        }
    }

    public static void d(String tag, String log) {
        if (isDebugable() && level <= DEBUG) {
            Log.d(tag, log);
        }
    }

    public static void i(String tag, String log) {
        if (isDebugable() && level <= INFO) {
            Log.i(tag, log);
        }
    }

    public static void w(String tag, String log) {
        if (isDebugable() && level <= WARN) {
            Log.w(tag, log);
        }
    }

    public static void e(String tag, String log) {
        if (isDebugable() && level <= ERROR) {
            Log.e(tag, log);
        }
    }
}
