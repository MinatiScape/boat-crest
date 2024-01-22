package com.google.android.datatransport.runtime.logging;

import android.util.Log;
/* loaded from: classes6.dex */
public final class Logging {
    public static String a(String str) {
        return "TransportRuntime." + str;
    }

    public static void d(String str, String str2) {
        Log.d(a(str), str2);
    }

    public static void e(String str, String str2, Throwable th) {
        Log.e(a(str), str2, th);
    }

    public static void i(String str, String str2) {
        Log.i(a(str), str2);
    }

    public static void w(String str, String str2, Object obj) {
        Log.w(a(str), String.format(str2, obj));
    }

    public static void d(String str, String str2, Object obj) {
        Log.d(a(str), String.format(str2, obj));
    }

    public static void d(String str, String str2, Object obj, Object obj2) {
        Log.d(a(str), String.format(str2, obj, obj2));
    }

    public static void d(String str, String str2, Object... objArr) {
        Log.d(a(str), String.format(str2, objArr));
    }
}
