package com.clevertap.android.sdk.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesUtil;
/* loaded from: classes2.dex */
public class PackageUtils {
    public static boolean a(Context context, Intent intent) {
        return (intent == null || context.getPackageManager().resolveActivity(intent, 65536) == null) ? false : true;
    }

    public static boolean b(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean isGooglePlayServicesAvailable(@NonNull Context context) {
        try {
            String str = GooglePlayServicesUtil.GMS_ERROR_DIALOG;
            return GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context) == 0;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean isGooglePlayStoreAvailable(@NonNull Context context) {
        return b(context, "com.android.vending") || b(context, "com.google.market");
    }

    public static boolean isXiaomiDeviceRunningMiui(Context context) {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if ("xiaomi".equalsIgnoreCase(Build.MANUFACTURER)) {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getMethod("get", String.class).invoke(cls, "ro.miui.ui.version.code");
            if (str != null) {
                if (!TextUtils.isEmpty(str.trim())) {
                    return true;
                }
            }
            return a(context, new Intent("miui.intent.action.OP_AUTO_START").addCategory("android.intent.category.DEFAULT")) || a(context, new Intent().setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"))) || a(context, new Intent("miui.intent.action.POWER_HIDE_MODE_APP_LIST").addCategory("android.intent.category.DEFAULT")) || a(context, new Intent().setComponent(new ComponentName("com.miui.securitycenter", "com.miui.powercenter.PowerSettings")));
        }
        return false;
    }
}
