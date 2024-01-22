package com.ido.ble.common;

import android.bluetooth.BluetoothAdapter;
import android.location.LocationManager;
import android.os.Build;
import android.os.Environment;
import androidx.core.content.ContextCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
/* loaded from: classes11.dex */
public class m {
    public static String a() {
        return com.ido.ble.b.b().getResources().getConfiguration().locale.getLanguage();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0043 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String b() {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 31
            if (r0 < r1) goto L1f
            boolean r0 = k()
            if (r0 != 0) goto Ld
            goto L25
        Ld:
            boolean r0 = h()
            if (r0 != 0) goto L16
            java.lang.String r0 = "[bluetooth scan permission: no]"
            goto L45
        L16:
            boolean r0 = f()
            if (r0 != 0) goto L43
            java.lang.String r0 = "[bluetooth connect permission: no]"
            goto L45
        L1f:
            boolean r0 = k()
            if (r0 != 0) goto L28
        L25:
            java.lang.String r0 = "[bluetooth switch: off]"
            goto L45
        L28:
            boolean r0 = e()
            if (r0 != 0) goto L31
            java.lang.String r0 = "[bluetooth permission: no]"
            goto L45
        L31:
            boolean r0 = l()
            if (r0 != 0) goto L3a
            java.lang.String r0 = "[gps switch: off]"
            goto L45
        L3a:
            boolean r0 = i()
            if (r0 != 0) goto L43
            java.lang.String r0 = "[gps permission: no]"
            goto L45
        L43:
            java.lang.String r0 = ""
        L45:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.ble.common.m.b():java.lang.String");
    }

    public static int c() {
        return Build.VERSION.SDK_INT;
    }

    public static String d() {
        return Build.VERSION.RELEASE;
    }

    public static boolean e() {
        return ContextCompat.checkSelfPermission(com.ido.ble.b.b(), "android.permission.BLUETOOTH_ADMIN") == 0;
    }

    public static boolean f() {
        return ContextCompat.checkSelfPermission(com.ido.ble.b.b(), "android.permission.BLUETOOTH_CONNECT") == 0;
    }

    public static boolean g() {
        return ContextCompat.checkSelfPermission(com.ido.ble.b.b(), "android.permission.BLUETOOTH") == 0;
    }

    public static boolean h() {
        return ContextCompat.checkSelfPermission(com.ido.ble.b.b(), "android.permission.BLUETOOTH_SCAN") == 0;
    }

    public static boolean i() {
        return ContextCompat.checkSelfPermission(com.ido.ble.b.b(), "android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    public static boolean j() {
        return Build.VERSION.SDK_INT >= 31 ? k() && h() && f() : k() && e() && l() && i();
    }

    public static boolean k() {
        return BluetoothAdapter.getDefaultAdapter().isEnabled();
    }

    public static boolean l() {
        LocationManager locationManager = (LocationManager) com.ido.ble.b.b().getSystemService(FirebaseAnalytics.Param.LOCATION);
        return locationManager != null && locationManager.isProviderEnabled("gps");
    }

    public static boolean m() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
}
