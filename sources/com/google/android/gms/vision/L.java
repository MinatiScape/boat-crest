package com.google.android.gms.vision;

import android.annotation.SuppressLint;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
@KeepForSdk
/* loaded from: classes10.dex */
public class L {
    @KeepForSdk
    public static final String TAG = "Vision";

    @KeepForSdk
    public static int d(String str, Object... objArr) {
        if (Log.isLoggable(TAG, 3)) {
            return Log.d(TAG, String.format(str, objArr));
        }
        return 0;
    }

    @KeepForSdk
    public static int e(String str, Object... objArr) {
        if (Log.isLoggable(TAG, 6)) {
            return Log.e(TAG, String.format(str, objArr));
        }
        return 0;
    }

    @KeepForSdk
    public static int i(String str, Object... objArr) {
        if (Log.isLoggable(TAG, 4)) {
            return Log.i(TAG, String.format(str, objArr));
        }
        return 0;
    }

    @KeepForSdk
    public static int v(String str, Object... objArr) {
        if (Log.isLoggable(TAG, 2)) {
            return Log.v(TAG, String.format(str, objArr));
        }
        return 0;
    }

    @KeepForSdk
    public static int w(String str, Object... objArr) {
        if (Log.isLoggable(TAG, 5)) {
            return Log.w(TAG, String.format(str, objArr));
        }
        return 0;
    }

    @KeepForSdk
    public static int d(Throwable th, String str, Object... objArr) {
        if (Log.isLoggable(TAG, 3)) {
            return Log.d(TAG, String.format(str, objArr), th);
        }
        return 0;
    }

    @KeepForSdk
    @SuppressLint({"LogTagMismatch"})
    public static int e(Throwable th, String str, Object... objArr) {
        if (Log.isLoggable(TAG, 6)) {
            if (Log.isLoggable(TAG, 3)) {
                return Log.e(TAG, String.format(str, objArr), th);
            }
            String format = String.format(str, objArr);
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(format).length() + 2 + valueOf.length());
            sb.append(format);
            sb.append(": ");
            sb.append(valueOf);
            return Log.e(TAG, sb.toString());
        }
        return 0;
    }

    @KeepForSdk
    @SuppressLint({"LogTagMismatch"})
    public static int w(Throwable th, String str, Object... objArr) {
        if (Log.isLoggable(TAG, 5)) {
            if (Log.isLoggable(TAG, 3)) {
                return Log.w(TAG, String.format(str, objArr), th);
            }
            String format = String.format(str, objArr);
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(format).length() + 2 + valueOf.length());
            sb.append(format);
            sb.append(": ");
            sb.append(valueOf);
            return Log.w(TAG, sb.toString());
        }
        return 0;
    }
}
