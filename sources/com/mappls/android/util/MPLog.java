package com.mappls.android.util;

import android.util.Log;
/* loaded from: classes11.dex */
public class MPLog {
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int NONE = Integer.MAX_VALUE;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static int sMinLevel = 5;

    public static void d(String str, String str2) {
        if (shouldLog(3)) {
            Log.d(str, str2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (shouldLog(3)) {
            Log.d(str, str2, th);
        }
    }

    public static void e(String str, String str2) {
        if (shouldLog(6)) {
            Log.e(str, str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (shouldLog(6)) {
            Log.e(str, str2, th);
        }
    }

    public static int getLevel() {
        return sMinLevel;
    }

    public static void i(String str, String str2) {
        if (shouldLog(4)) {
            Log.i(str, str2);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (shouldLog(4)) {
            Log.i(str, str2, th);
        }
    }

    public static void setLevel(int i) {
        sMinLevel = i;
    }

    private static boolean shouldLog(int i) {
        return sMinLevel <= i;
    }

    public static void v(String str, String str2) {
        if (shouldLog(2)) {
            Log.v(str, str2);
        }
    }

    public static void v(String str, String str2, Throwable th) {
        if (shouldLog(2)) {
            Log.v(str, str2, th);
        }
    }

    public static void w(String str, String str2) {
        if (shouldLog(5)) {
            Log.w(str, str2);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (shouldLog(5)) {
            Log.w(str, str2, th);
        }
    }
}
