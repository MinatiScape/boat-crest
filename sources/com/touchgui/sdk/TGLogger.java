package com.touchgui.sdk;

import android.util.Log;
/* loaded from: classes12.dex */
public class TGLogger {
    public static final String TAG = "touchgui-sdk";
    private static TGLogListener logListener;
    private static Boolean sDebug = Boolean.FALSE;

    private static String buildLog(com.touchgui.sdk.internal.a0 a0Var, String str) {
        return buildLog(a0Var.c(), str);
    }

    public static void d(com.touchgui.sdk.internal.a0 a0Var, String str) {
        d(buildLog(a0Var, str));
    }

    public static void e(com.touchgui.sdk.internal.a0 a0Var, String str) {
        d(buildLog(a0Var, str));
    }

    public static void i(com.touchgui.sdk.internal.a0 a0Var, String str) {
        d(buildLog(a0Var, str));
    }

    public static boolean isDebug() {
        return sDebug.booleanValue();
    }

    private static boolean log(int i, String str) {
        TGLogListener tGLogListener = logListener;
        if (tGLogListener != null) {
            tGLogListener.log(TAG, str);
            return false;
        }
        return sDebug.booleanValue();
    }

    public static void setDebug(boolean z) {
        sDebug = Boolean.valueOf(z);
    }

    public static void setLogListener(TGLogListener tGLogListener) {
        logListener = tGLogListener;
    }

    public static void w(com.touchgui.sdk.internal.a0 a0Var, String str) {
        d(buildLog(a0Var, str));
    }

    private static String buildLog(String str, String str2) {
        return str != null ? String.format("[%s] %s", str, str2) : str2;
    }

    public static void d(String str) {
        if (log(3, str)) {
            Log.d(TAG, str);
        }
    }

    public static void e(String str) {
        if (log(6, str)) {
            Log.e(TAG, str);
        }
    }

    public static void i(String str) {
        if (log(4, str)) {
            Log.i(TAG, str);
        }
    }

    public static void w(String str) {
        if (log(5, str)) {
            Log.w(TAG, str);
        }
    }

    public static void d(String str, String str2) {
        d(buildLog(str, str2));
    }

    public static void e(String str, String str2) {
        d(buildLog(str, str2));
    }

    public static void i(String str, String str2) {
        d(buildLog(str, str2));
    }

    public static void w(String str, String str2) {
        d(buildLog(str, str2));
    }
}
