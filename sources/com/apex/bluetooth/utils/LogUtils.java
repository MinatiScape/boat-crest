package com.apex.bluetooth.utils;

import android.util.Log;
/* loaded from: classes.dex */
public class LogUtils {
    public static boolean showLog;

    public static void d(String str, String str2) {
        if (showLog) {
            Log.d(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (showLog) {
            Log.e(str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (showLog) {
            Log.i(str, str2);
        }
    }

    public static void setShowLog(boolean z) {
        showLog = z;
    }

    public static void v(String str, String str2) {
        if (showLog) {
            Log.v(str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (showLog) {
            Log.w(str, str2);
        }
    }
}
