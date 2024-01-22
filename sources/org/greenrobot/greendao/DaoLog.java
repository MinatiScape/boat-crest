package org.greenrobot.greendao;

import android.util.Log;
/* loaded from: classes13.dex */
public class DaoLog {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;

    public static int d(String str) {
        return Log.d("greenDAO", str);
    }

    public static int e(String str) {
        return Log.w("greenDAO", str);
    }

    public static String getStackTraceString(Throwable th) {
        return Log.getStackTraceString(th);
    }

    public static int i(String str) {
        return Log.i("greenDAO", str);
    }

    public static boolean isLoggable(int i) {
        return Log.isLoggable("greenDAO", i);
    }

    public static int println(int i, String str) {
        return Log.println(i, "greenDAO", str);
    }

    public static int v(String str) {
        return Log.v("greenDAO", str);
    }

    public static int w(String str) {
        return Log.w("greenDAO", str);
    }

    public static int d(String str, Throwable th) {
        return Log.d("greenDAO", str, th);
    }

    public static int e(String str, Throwable th) {
        return Log.e("greenDAO", str, th);
    }

    public static int i(String str, Throwable th) {
        return Log.i("greenDAO", str, th);
    }

    public static int v(String str, Throwable th) {
        return Log.v("greenDAO", str, th);
    }

    public static int w(String str, Throwable th) {
        return Log.w("greenDAO", str, th);
    }

    public static int w(Throwable th) {
        return Log.w("greenDAO", th);
    }
}
