package com.clevertap.android.sdk;

import android.util.Log;
import com.clevertap.android.sdk.CleverTapAPI;
import com.mappls.sdk.navigation.NavigationConstants;
/* loaded from: classes2.dex */
public final class Logger {

    /* renamed from: a  reason: collision with root package name */
    public int f2582a;

    public Logger(int i) {
        this.f2582a = i;
    }

    public static int b() {
        return CleverTapAPI.getDebugLevel();
    }

    public static void d(String str) {
        if (b() > CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.d(Constants.CLEVERTAP_LOG_TAG, str);
        }
    }

    public static void i(String str) {
        if (b() >= CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.i(Constants.CLEVERTAP_LOG_TAG, str);
        }
    }

    public static void v(String str) {
        if (b() > CleverTapAPI.LogLevel.DEBUG.intValue()) {
            Log.v(Constants.CLEVERTAP_LOG_TAG, str);
        }
    }

    public final int a() {
        return this.f2582a;
    }

    public void debug(String str) {
        if (b() > CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.d(Constants.CLEVERTAP_LOG_TAG, str);
        }
    }

    public void info(String str) {
        if (a() >= CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.i(Constants.CLEVERTAP_LOG_TAG, str);
        }
    }

    public void setDebugLevel(int i) {
        this.f2582a = i;
    }

    public void verbose(String str) {
        if (b() > CleverTapAPI.LogLevel.DEBUG.intValue()) {
            Log.v(Constants.CLEVERTAP_LOG_TAG, str);
        }
    }

    public static void d(String str, String str2) {
        if (b() > CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.d("CleverTap:" + str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (b() >= CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.i("CleverTap:" + str, str2);
        }
    }

    public static void v(String str, String str2) {
        if (b() > CleverTapAPI.LogLevel.DEBUG.intValue()) {
            Log.v("CleverTap:" + str, str2);
        }
    }

    public void debug(String str, String str2) {
        if (b() > CleverTapAPI.LogLevel.INFO.intValue()) {
            if (str2.length() > 4000) {
                Log.d("CleverTap:" + str, str2.substring(0, NavigationConstants.UI_HANDLER_MAP_CONTROLS));
                debug(str, str2.substring(NavigationConstants.UI_HANDLER_MAP_CONTROLS));
                return;
            }
            Log.d("CleverTap:" + str, str2);
        }
    }

    public void info(String str, String str2) {
        if (a() >= CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.i("CleverTap:" + str, str2);
        }
    }

    public void verbose(String str, String str2) {
        if (b() > CleverTapAPI.LogLevel.DEBUG.intValue()) {
            if (str2.length() > 4000) {
                Log.v("CleverTap:" + str, str2.substring(0, NavigationConstants.UI_HANDLER_MAP_CONTROLS));
                verbose(str, str2.substring(NavigationConstants.UI_HANDLER_MAP_CONTROLS));
                return;
            }
            Log.v("CleverTap:" + str, str2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (b() > CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.d("CleverTap:" + str, str2, th);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (b() >= CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.i("CleverTap:" + str, str2, th);
        }
    }

    public static void v(String str, String str2, Throwable th) {
        if (b() > CleverTapAPI.LogLevel.DEBUG.intValue()) {
            Log.v("CleverTap:" + str, str2, th);
        }
    }

    public void info(String str, String str2, Throwable th) {
        if (a() >= CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.i("CleverTap:" + str, str2, th);
        }
    }

    public static void d(String str, Throwable th) {
        if (b() > CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.d(Constants.CLEVERTAP_LOG_TAG, str, th);
        }
    }

    public static void i(String str, Throwable th) {
        if (b() >= CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.i(Constants.CLEVERTAP_LOG_TAG, str, th);
        }
    }

    public static void v(String str, Throwable th) {
        if (b() > CleverTapAPI.LogLevel.DEBUG.intValue()) {
            Log.v(Constants.CLEVERTAP_LOG_TAG, str, th);
        }
    }

    public void info(String str, Throwable th) {
        if (a() >= CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.i(Constants.CLEVERTAP_LOG_TAG, str, th);
        }
    }

    public void debug(String str, String str2, Throwable th) {
        if (b() > CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.d("CleverTap:" + str, str2, th);
        }
    }

    public void verbose(String str, String str2, Throwable th) {
        if (b() > CleverTapAPI.LogLevel.DEBUG.intValue()) {
            Log.v("CleverTap:" + str, str2, th);
        }
    }

    public void debug(String str, Throwable th) {
        if (b() > CleverTapAPI.LogLevel.INFO.intValue()) {
            Log.d(Constants.CLEVERTAP_LOG_TAG, str, th);
        }
    }

    public void verbose(String str, Throwable th) {
        if (b() > CleverTapAPI.LogLevel.DEBUG.intValue()) {
            Log.v(Constants.CLEVERTAP_LOG_TAG, str, th);
        }
    }
}
