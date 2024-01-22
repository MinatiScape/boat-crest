package com.mappls.sdk.navigation.apis;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.util.navigationLogs.NavigationTrace;
@Keep
/* loaded from: classes11.dex */
public class NavigationLogger {
    private static final boolean DEBUG = false;
    private static final String TAG = "NavigationLogger";
    private static NavigationApplication application;

    private NavigationLogger() {
    }

    public static void d(@NonNull String str, Object... objArr) {
        NavigationTrace.writeLine(str, objArr);
    }

    public static void d(Throwable th) {
        NavigationTrace.writeLine(th.getMessage());
    }

    public static void d(Throwable th, @NonNull String str, Object... objArr) {
        NavigationTrace.writeLine(str, objArr);
    }

    public static void e(@NonNull String str, Object... objArr) {
        NavigationTrace.writeLine(str, objArr);
    }

    public static void e(Throwable th) {
        NavigationTrace.writeLine(th.getMessage());
    }

    public static void e(Throwable th, @NonNull String str, Object... objArr) {
        NavigationTrace.writeLine(str, objArr);
    }

    public static void i(@NonNull String str, Object... objArr) {
        NavigationTrace.writeLine(str, objArr);
    }

    public static void i(Throwable th) {
        NavigationTrace.writeLine(th.getMessage());
    }

    public static void i(Throwable th, @NonNull String str, Object... objArr) {
        NavigationTrace.writeLine(str, objArr);
    }

    public static void setApplication(NavigationApplication navigationApplication) {
        application = navigationApplication;
    }

    public static void v(@NonNull String str, Object... objArr) {
        NavigationTrace.writeLine(str, objArr);
    }

    public static void v(Throwable th) {
        NavigationTrace.writeLine(th.getMessage());
    }

    public static void v(Throwable th, @NonNull String str, Object... objArr) {
        NavigationTrace.writeLine(str, objArr);
    }

    public static void w(@NonNull String str, Object... objArr) {
        NavigationTrace.writeLine(str, objArr);
    }

    public static void w(Throwable th) {
        NavigationTrace.writeLine(th.getMessage());
    }

    public static void w(Throwable th, @NonNull String str, Object... objArr) {
        NavigationTrace.writeLine(str, objArr);
    }

    public static void wtf(@NonNull String str, Object... objArr) {
    }

    public static void wtf(Throwable th) {
    }

    public static void wtf(Throwable th, @NonNull String str, Object... objArr) {
    }
}
