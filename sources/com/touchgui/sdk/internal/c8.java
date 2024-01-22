package com.touchgui.sdk.internal;

import android.content.Context;
import android.os.Build;
import androidx.core.content.ContextCompat;
/* loaded from: classes12.dex */
public abstract class c8 {
    public static boolean a(Context context) {
        return Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(context, "android.permission.BLUETOOTH_CONNECT") == 0;
    }

    public static boolean b(Context context) {
        if (Build.VERSION.SDK_INT >= 31) {
            return ContextCompat.checkSelfPermission(context, "android.permission.BLUETOOTH_SCAN") == 0;
        }
        if (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            return true;
        }
        return ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0;
    }
}
