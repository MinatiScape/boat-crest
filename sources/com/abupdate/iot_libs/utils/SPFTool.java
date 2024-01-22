package com.abupdate.iot_libs.utils;

import android.content.Context;
import android.content.SharedPreferences;
/* loaded from: classes.dex */
public class SPFTool {
    public static final String KEY_DELTAID = "KEY_DELTAID";
    public static String KEY_DEVICE_ID = "key_device_id";
    public static final String KEY_DEVICE_SECRET = "deviceSecret";
    public static final String KEY_LAST_RECOVERY_TIME = "key_last_recovery_time";
    public static String KEY_PRODUCT_ID = "key_product_id";
    public static final String KEY_PRODUCT_SECRET = "key_product_secret";
    public static final String KEY_SHOULD_REPORT = "key_should_report";
    public static final String KEY_UPDATE_FILE_PATH = "key_update_file_path";
    public static final String KEY_VERSION_INFO = "key_version_info";
    public static final String KEY_VERSION_NAME = "key_version_name";

    /* renamed from: a  reason: collision with root package name */
    public static SharedPreferences.Editor f1917a;
    public static Context b;

    public static boolean getBoolean(String str, boolean z) {
        return b.getSharedPreferences("iport_sp", 0).getBoolean(str, z);
    }

    public static int getInt(String str, int i) {
        return b.getSharedPreferences("iport_sp", 0).getInt(str, i);
    }

    public static long getLong(String str, long j) {
        return b.getSharedPreferences("iport_sp", 0).getLong(str, j);
    }

    public static String getString(String str, String str2) {
        return b.getSharedPreferences("iport_sp", 0).getString(str, str2);
    }

    public static void initContext(Context context) {
        b = context;
    }

    public static void putBoolean(String str, boolean z) {
        SharedPreferences.Editor edit = b.getSharedPreferences("iport_sp", 0).edit();
        f1917a = edit;
        edit.putBoolean(str, z);
        f1917a.commit();
    }

    public static void putInt(String str, int i) {
        SharedPreferences.Editor edit = b.getSharedPreferences("iport_sp", 0).edit();
        f1917a = edit;
        edit.putInt(str, i);
        f1917a.commit();
    }

    public static void putLong(String str, long j) {
        SharedPreferences.Editor edit = b.getSharedPreferences("iport_sp", 0).edit();
        f1917a = edit;
        edit.putLong(str, j);
        f1917a.commit();
    }

    public static void putString(String str, String str2) {
        SharedPreferences.Editor edit = b.getSharedPreferences("iport_sp", 0).edit();
        f1917a = edit;
        edit.putString(str, str2);
        f1917a.commit();
    }
}
