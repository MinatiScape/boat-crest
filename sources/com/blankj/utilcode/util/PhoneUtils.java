package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import java.util.Objects;
/* loaded from: classes.dex */
public final class PhoneUtils {
    public PhoneUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String a(String str, String str2) {
        boolean isEmpty = TextUtils.isEmpty(str);
        boolean isEmpty2 = TextUtils.isEmpty(str2);
        return (isEmpty && isEmpty2) ? "" : (isEmpty || isEmpty2) ? !isEmpty ? str : str2 : str.compareTo(str2) <= 0 ? str : str2;
    }

    public static String b(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, "");
        } catch (Exception unused) {
            return "";
        }
    }

    public static TelephonyManager c() {
        return (TelephonyManager) Utils.getApp().getSystemService("phone");
    }

    @RequiresPermission("android.permission.CALL_PHONE")
    public static void call(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'phoneNumber' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Utils.getApp().startActivity(b.M(str));
    }

    public static void dial(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'phoneNumber' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Utils.getApp().startActivity(b.O(str));
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    @SuppressLint({"HardwareIds"})
    public static String getDeviceId() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            return "";
        }
        TelephonyManager c = c();
        String deviceId = c.getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            if (i >= 26) {
                String imei = c.getImei();
                if (TextUtils.isEmpty(imei)) {
                    String meid = c.getMeid();
                    return TextUtils.isEmpty(meid) ? "" : meid;
                }
                return imei;
            }
            return "";
        }
        return deviceId;
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String getIMEI() {
        return getImeiOrMeid(true);
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    @SuppressLint({"HardwareIds"})
    public static String getIMSI() {
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                c().getSubscriberId();
            } catch (SecurityException e) {
                e.printStackTrace();
                return "";
            }
        }
        return c().getSubscriberId();
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a5, code lost:
        if (r2.length() < 15) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00b7, code lost:
        if (r2.length() == 14) goto L38;
     */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00a8  */
    @androidx.annotation.RequiresPermission("android.permission.READ_PHONE_STATE")
    @android.annotation.SuppressLint({"HardwareIds"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getImeiOrMeid(boolean r12) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            java.lang.String r1 = ""
            r2 = 29
            if (r0 < r2) goto L9
            return r1
        L9:
            android.telephony.TelephonyManager r2 = c()
            r3 = 26
            r4 = 1
            r5 = 0
            if (r0 < r3) goto L2f
            if (r12 == 0) goto L22
            java.lang.String r12 = r2.getImei(r5)
            java.lang.String r0 = r2.getImei(r4)
            java.lang.String r12 = a(r12, r0)
            return r12
        L22:
            java.lang.String r12 = r2.getMeid(r5)
            java.lang.String r0 = r2.getMeid(r4)
            java.lang.String r12 = a(r12, r0)
            return r12
        L2f:
            r3 = 21
            r6 = 15
            r7 = 14
            if (r0 < r3) goto Lc0
            if (r12 == 0) goto L3c
            java.lang.String r0 = "ril.gsm.imei"
            goto L3e
        L3c:
            java.lang.String r0 = "ril.cdma.meid"
        L3e:
            java.lang.String r0 = b(r0)
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            r8 = 2
            if (r3 != 0) goto L5e
            java.lang.String r12 = ","
            java.lang.String[] r12 = r0.split(r12)
            int r0 = r12.length
            if (r0 != r8) goto L5b
            r0 = r12[r5]
            r12 = r12[r4]
            java.lang.String r12 = a(r0, r12)
            return r12
        L5b:
            r12 = r12[r5]
            return r12
        L5e:
            java.lang.String r0 = r2.getDeviceId()
            java.lang.Class r3 = r2.getClass()     // Catch: java.lang.reflect.InvocationTargetException -> L85 java.lang.IllegalAccessException -> L8a java.lang.NoSuchMethodException -> L8f
            java.lang.String r9 = "getDeviceId"
            java.lang.Class[] r10 = new java.lang.Class[r4]     // Catch: java.lang.reflect.InvocationTargetException -> L85 java.lang.IllegalAccessException -> L8a java.lang.NoSuchMethodException -> L8f
            java.lang.Class r11 = java.lang.Integer.TYPE     // Catch: java.lang.reflect.InvocationTargetException -> L85 java.lang.IllegalAccessException -> L8a java.lang.NoSuchMethodException -> L8f
            r10[r5] = r11     // Catch: java.lang.reflect.InvocationTargetException -> L85 java.lang.IllegalAccessException -> L8a java.lang.NoSuchMethodException -> L8f
            java.lang.reflect.Method r3 = r3.getMethod(r9, r10)     // Catch: java.lang.reflect.InvocationTargetException -> L85 java.lang.IllegalAccessException -> L8a java.lang.NoSuchMethodException -> L8f
            java.lang.Object[] r9 = new java.lang.Object[r4]     // Catch: java.lang.reflect.InvocationTargetException -> L85 java.lang.IllegalAccessException -> L8a java.lang.NoSuchMethodException -> L8f
            if (r12 == 0) goto L77
            goto L78
        L77:
            r4 = r8
        L78:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.reflect.InvocationTargetException -> L85 java.lang.IllegalAccessException -> L8a java.lang.NoSuchMethodException -> L8f
            r9[r5] = r4     // Catch: java.lang.reflect.InvocationTargetException -> L85 java.lang.IllegalAccessException -> L8a java.lang.NoSuchMethodException -> L8f
            java.lang.Object r2 = r3.invoke(r2, r9)     // Catch: java.lang.reflect.InvocationTargetException -> L85 java.lang.IllegalAccessException -> L8a java.lang.NoSuchMethodException -> L8f
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.reflect.InvocationTargetException -> L85 java.lang.IllegalAccessException -> L8a java.lang.NoSuchMethodException -> L8f
            goto L94
        L85:
            r2 = move-exception
            r2.printStackTrace()
            goto L93
        L8a:
            r2 = move-exception
            r2.printStackTrace()
            goto L93
        L8f:
            r2 = move-exception
            r2.printStackTrace()
        L93:
            r2 = r1
        L94:
            if (r12 == 0) goto La8
            if (r0 == 0) goto L9f
            int r12 = r0.length()
            if (r12 >= r6) goto L9f
            r0 = r1
        L9f:
            if (r2 == 0) goto Lba
            int r12 = r2.length()
            if (r12 >= r6) goto Lba
            goto Lbb
        La8:
            if (r0 == 0) goto Lb1
            int r12 = r0.length()
            if (r12 != r7) goto Lb1
            r0 = r1
        Lb1:
            if (r2 == 0) goto Lba
            int r12 = r2.length()
            if (r12 != r7) goto Lba
            goto Lbb
        Lba:
            r1 = r2
        Lbb:
            java.lang.String r12 = a(r0, r1)
            return r12
        Lc0:
            java.lang.String r0 = r2.getDeviceId()
            if (r12 == 0) goto Lcf
            if (r0 == 0) goto Ld8
            int r12 = r0.length()
            if (r12 < r6) goto Ld8
            return r0
        Lcf:
            if (r0 == 0) goto Ld8
            int r12 = r0.length()
            if (r12 != r7) goto Ld8
            return r0
        Ld8:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.PhoneUtils.getImeiOrMeid(boolean):java.lang.String");
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String getMEID() {
        return getImeiOrMeid(false);
    }

    public static int getPhoneType() {
        return c().getPhoneType();
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    @SuppressLint({"HardwareIds"})
    public static String getSerial() {
        int i = Build.VERSION.SDK_INT;
        if (i < 29) {
            return i >= 26 ? Build.getSerial() : Build.SERIAL;
        }
        try {
            return Build.getSerial();
        } catch (SecurityException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getSimOperatorByMnc() {
        String simOperator = c().getSimOperator();
        if (simOperator == null) {
            return "";
        }
        char c = 65535;
        switch (simOperator.hashCode()) {
            case 49679470:
                if (simOperator.equals("46000")) {
                    c = 0;
                    break;
                }
                break;
            case 49679471:
                if (simOperator.equals("46001")) {
                    c = 1;
                    break;
                }
                break;
            case 49679472:
                if (simOperator.equals("46002")) {
                    c = 2;
                    break;
                }
                break;
            case 49679473:
                if (simOperator.equals("46003")) {
                    c = 3;
                    break;
                }
                break;
            case 49679475:
                if (simOperator.equals("46005")) {
                    c = 4;
                    break;
                }
                break;
            case 49679476:
                if (simOperator.equals("46006")) {
                    c = 5;
                    break;
                }
                break;
            case 49679477:
                if (simOperator.equals("46007")) {
                    c = 6;
                    break;
                }
                break;
            case 49679479:
                if (simOperator.equals("46009")) {
                    c = 7;
                    break;
                }
                break;
            case 49679502:
                if (simOperator.equals("46011")) {
                    c = '\b';
                    break;
                }
                break;
            case 49679532:
                if (simOperator.equals("46020")) {
                    c = '\t';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 2:
            case 6:
            case '\t':
                return "中国移动";
            case 1:
            case 5:
            case 7:
                return "中国联通";
            case 3:
            case 4:
            case '\b':
                return "中国电信";
            default:
                return simOperator;
        }
    }

    public static String getSimOperatorName() {
        return c().getSimOperatorName();
    }

    public static boolean isPhone() {
        return c().getPhoneType() != 0;
    }

    public static boolean isSimCardReady() {
        return c().getSimState() == 5;
    }

    public static void sendSms(@NonNull String str, String str2) {
        Objects.requireNonNull(str, "Argument 'phoneNumber' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Utils.getApp().startActivity(b.c0(str, str2));
    }
}
