package com.mappls.android.lms;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class SystemInformation {
    private static final String LOGTAG = "MapplsAnalyticsAPI.SysInfo";
    private static SystemInformation sInstance;
    private static final Object sInstanceLock = new Object();
    private final String mAppName;
    private final Integer mAppVersionCode;
    private final String mAppVersionName;
    private final Context mContext;
    private final DisplayMetrics mDisplayMetrics;
    private final Boolean mHasNFC;
    private final Boolean mHasTelephony;

    /* JADX WARN: Removed duplicated region for block: B:10:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0058 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private SystemInformation(android.content.Context r9) {
        /*
            r8 = this;
            java.lang.String r0 = "MapplsAnalyticsAPI.SysInfo"
            r8.<init>()
            r8.mContext = r9
            android.content.pm.PackageManager r1 = r9.getPackageManager()
            r2 = 0
            r3 = 0
            java.lang.String r4 = r9.getPackageName()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L1e
            android.content.pm.PackageInfo r4 = r1.getPackageInfo(r4, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L1e
            java.lang.String r5 = r4.versionName     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L1e
            int r4 = r4.versionCode     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L1f
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L1f
            goto L25
        L1e:
            r5 = r3
        L1f:
            java.lang.String r4 = "System information constructed with a context that apparently doesn't exist."
            com.mappls.android.util.MPLog.w(r0, r4)
            r4 = r3
        L25:
            android.content.pm.ApplicationInfo r6 = r9.getApplicationInfo()
            int r7 = r6.labelRes
            r8.mAppVersionName = r5
            r8.mAppVersionCode = r4
            if (r7 != 0) goto L3d
            java.lang.CharSequence r9 = r6.nonLocalizedLabel
            if (r9 != 0) goto L38
            java.lang.String r9 = "Misc"
            goto L41
        L38:
            java.lang.String r9 = r9.toString()
            goto L41
        L3d:
            java.lang.String r9 = r9.getString(r7)
        L41:
            r8.mAppName = r9
            java.lang.Class r9 = r1.getClass()
            r4 = 1
            java.lang.String r5 = "hasSystemFeature"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch: java.lang.NoSuchMethodException -> L55
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r2] = r7     // Catch: java.lang.NoSuchMethodException -> L55
            java.lang.reflect.Method r9 = r9.getMethod(r5, r6)     // Catch: java.lang.NoSuchMethodException -> L55
            goto L56
        L55:
            r9 = r3
        L56:
            if (r9 == 0) goto L79
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L72
            java.lang.String r6 = "android.hardware.nfc"
            r5[r2] = r6     // Catch: java.lang.Throwable -> L72
            java.lang.Object r5 = r9.invoke(r1, r5)     // Catch: java.lang.Throwable -> L72
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch: java.lang.Throwable -> L72
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L73
            java.lang.String r6 = "android.hardware.telephony"
            r4[r2] = r6     // Catch: java.lang.Throwable -> L73
            java.lang.Object r9 = r9.invoke(r1, r4)     // Catch: java.lang.Throwable -> L73
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.Throwable -> L73
            r3 = r9
            goto L7a
        L72:
            r5 = r3
        L73:
            java.lang.String r9 = "System version appeared to support PackageManager.hasSystemFeature, but we were unable to call it."
            com.mappls.android.util.MPLog.w(r0, r9)
            goto L7a
        L79:
            r5 = r3
        L7a:
            r8.mHasNFC = r5
            r8.mHasTelephony = r3
            android.util.DisplayMetrics r9 = new android.util.DisplayMetrics
            r9.<init>()
            r8.mDisplayMetrics = r9
            android.content.Context r0 = r8.mContext
            java.lang.String r1 = "window"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.view.WindowManager r0 = (android.view.WindowManager) r0
            android.view.Display r0 = r0.getDefaultDisplay()
            r0.getMetrics(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.android.lms.SystemInformation.<init>(android.content.Context):void");
    }

    public static SystemInformation getInstance(Context context) {
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                sInstance = new SystemInformation(context.getApplicationContext());
            }
        }
        return sInstance;
    }

    public String getAppName() {
        return this.mAppName;
    }

    public Integer getAppVersionCode() {
        return this.mAppVersionCode;
    }

    public String getAppVersionName() {
        return this.mAppVersionName;
    }

    public String getBluetoothVersion() {
        return (Build.VERSION.SDK_INT < 18 || !this.mContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) ? this.mContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth") ? "classic" : "none" : "ble";
    }

    public String getCurrentNetworkOperator() {
        TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getNetworkOperatorName();
        }
        return null;
    }

    public DisplayMetrics getDisplayMetrics() {
        return this.mDisplayMetrics;
    }

    public boolean hasNFC() {
        return this.mHasNFC.booleanValue();
    }

    public boolean hasTelephony() {
        return this.mHasTelephony.booleanValue();
    }

    public Boolean isBluetoothEnabled() {
        BluetoothAdapter defaultAdapter;
        try {
            if (this.mContext.getPackageManager().checkPermission("android.permission.BLUETOOTH", this.mContext.getPackageName()) == 0 && (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) != null) {
                return Boolean.valueOf(defaultAdapter.isEnabled());
            }
        } catch (NoClassDefFoundError | SecurityException unused) {
        }
        return null;
    }

    public Boolean isWifiConnected() {
        if (this.mContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
            boolean z = true;
            return Boolean.valueOf((activeNetworkInfo != null && activeNetworkInfo.getType() == 1 && activeNetworkInfo.isConnected()) ? false : false);
        }
        return null;
    }
}
