package com.blankj.utilcode.util;

import android.app.ActivityManager;
import android.os.Environment;
import androidx.annotation.RequiresApi;
import java.io.File;
/* loaded from: classes.dex */
public final class CleanUtils {
    public CleanUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    @RequiresApi(api = 19)
    public static void cleanAppUserData() {
        ((ActivityManager) Utils.getApp().getSystemService("activity")).clearApplicationUserData();
    }

    public static boolean cleanCustomDir(String str) {
        return b.u(b.P(str));
    }

    public static boolean cleanExternalCache() {
        return "mounted".equals(Environment.getExternalStorageState()) && b.u(Utils.getApp().getExternalCacheDir());
    }

    public static boolean cleanInternalCache() {
        return b.u(Utils.getApp().getCacheDir());
    }

    public static boolean cleanInternalDbByName(String str) {
        return Utils.getApp().deleteDatabase(str);
    }

    public static boolean cleanInternalDbs() {
        return b.u(new File(Utils.getApp().getFilesDir().getParent(), "databases"));
    }

    public static boolean cleanInternalFiles() {
        return b.u(Utils.getApp().getFilesDir());
    }

    public static boolean cleanInternalSp() {
        return b.u(new File(Utils.getApp().getFilesDir().getParent(), "shared_prefs"));
    }
}
