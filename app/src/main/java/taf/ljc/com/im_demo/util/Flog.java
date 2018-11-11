package taf.ljc.com.im_demo.util;

import android.text.TextUtils;
import android.util.Log;

import taf.ljc.com.im_demo.BuildConfig;

public final class Flog {
    private static final boolean isDebug = BuildConfig.DEBUG;
    private static final String TAG = BuildConfig.APP_TAG;

    public static void i(String tag, String msg) {
        if (!isDebug) {
            return;
        }
        Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (!isDebug) {
            return;
        }
        Log.d(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (!isDebug) {
            return;
        }
        Log.w(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (!isDebug) {
            return;
        }
        Log.e(tag, msg);
    }

    public static void i(String msg) {
        if (!isDebug) {
            return;
        }
        Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (!isDebug) {
            return;
        }
        Log.d(TAG, msg);
    }

    public static void w(String msg) {
        if (!isDebug) {
            return;
        }
        Log.w(TAG, msg);
    }

    public static void e(String msg) {
        if (!isDebug) {
            return;
        }
        Log.e(TAG, msg);
    }

    public static void trace(String info) {
        if (!isDebug) {
            return;
        }
        Exception ex = new Exception();
        if (!TextUtils.isEmpty(info)) {
            e(TAG, info);
        }
        e(TAG, "######## trace begin ########");
        e(TAG, Log.getStackTraceString(ex));
        e(TAG, "######## trace end ########");
    }
}
