package com.jieli.bluetooth_connect.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import androidx.core.content.ContextCompat;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Objects;
/* loaded from: classes11.dex */
public class ConnectUtil {
    public static final boolean isSupportAndroid12 = true;
    @SuppressLint({"StaticFieldLeak"})
    private static WeakReference<Context> sContextWeak;

    public static void checkAllNotNull(Object... objArr) {
        for (Object obj : objArr) {
            Objects.requireNonNull(obj);
        }
    }

    public static <T> T checkNotNull(T t) {
        Objects.requireNonNull(t);
        return t;
    }

    public static Context getContext() {
        WeakReference<Context> weakReference = sContextWeak;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public static long getCurrentTime() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public static boolean isHasConnectPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 31) {
            return isHasPermission(context, "android.permission.BLUETOOTH_CONNECT");
        }
        return true;
    }

    public static boolean isHasLocationPermission(Context context) {
        return isHasPermission(context, "android.permission.ACCESS_COARSE_LOCATION");
    }

    public static boolean isHasPermission(Context context, String str) {
        return context != null && ContextCompat.checkSelfPermission(context, str) == 0;
    }

    public static boolean isHasScanPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 31) {
            return isHasPermission(context, "android.permission.BLUETOOTH_SCAN");
        }
        return true;
    }

    public static boolean isHasStoragePermission(Context context) {
        if (Build.VERSION.SDK_INT >= 28) {
            return isHasPermission(context, "android.permission.READ_EXTERNAL_STORAGE");
        }
        return isHasPermission(context, "android.permission.READ_EXTERNAL_STORAGE") && isHasPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    public static void setContext(Context context) {
        sContextWeak = new WeakReference<>(context);
    }

    public static <T> T checkNotNull(T t, String str) {
        Objects.requireNonNull(t, str);
        return t;
    }
}
