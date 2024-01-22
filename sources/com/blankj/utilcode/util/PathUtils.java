package com.blankj.utilcode.util;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
/* loaded from: classes.dex */
public final class PathUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final char f2274a = File.separatorChar;

    public PathUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String a(File file) {
        return file == null ? "" : file.getAbsolutePath();
    }

    public static String b(String str) {
        char[] charArray = str.toCharArray();
        int i = -1;
        int i2 = -1;
        for (int i3 = 0; i3 < charArray.length; i3++) {
            if (charArray[i3] != f2274a) {
                if (i == -1) {
                    i = i3;
                }
                i2 = i3;
            }
        }
        if (i >= 0 && i2 >= i) {
            return str.substring(i, i2 + 1);
        }
        throw new IllegalArgumentException("segment of <" + str + "> is illegal");
    }

    public static String getAppDataPathExternalFirst() {
        String externalAppDataPath = getExternalAppDataPath();
        return TextUtils.isEmpty(externalAppDataPath) ? getInternalAppDataPath() : externalAppDataPath;
    }

    public static String getCachePathExternalFirst() {
        String externalAppCachePath = getExternalAppCachePath();
        return TextUtils.isEmpty(externalAppCachePath) ? getInternalAppCachePath() : externalAppCachePath;
    }

    public static String getDataPath() {
        return a(Environment.getDataDirectory());
    }

    public static String getDownloadCachePath() {
        return a(Environment.getDownloadCacheDirectory());
    }

    public static String getExternalAlarmsPath() {
        return !b.z0() ? "" : a(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS));
    }

    public static String getExternalAppAlarmsPath() {
        return !b.z0() ? "" : a(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_ALARMS));
    }

    public static String getExternalAppCachePath() {
        return !b.z0() ? "" : a(Utils.getApp().getExternalCacheDir());
    }

    public static String getExternalAppDataPath() {
        File externalCacheDir;
        return (b.z0() && (externalCacheDir = Utils.getApp().getExternalCacheDir()) != null) ? a(externalCacheDir.getParentFile()) : "";
    }

    public static String getExternalAppDcimPath() {
        return !b.z0() ? "" : a(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_DCIM));
    }

    public static String getExternalAppDocumentsPath() {
        if (b.z0()) {
            if (Build.VERSION.SDK_INT < 19) {
                return a(Utils.getApp().getExternalFilesDir(null)) + "/Documents";
            }
            return a(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS));
        }
        return "";
    }

    public static String getExternalAppDownloadPath() {
        return !b.z0() ? "" : a(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS));
    }

    public static String getExternalAppFilesPath() {
        return !b.z0() ? "" : a(Utils.getApp().getExternalFilesDir(null));
    }

    public static String getExternalAppMoviesPath() {
        return !b.z0() ? "" : a(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_MOVIES));
    }

    public static String getExternalAppMusicPath() {
        return !b.z0() ? "" : a(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_MUSIC));
    }

    public static String getExternalAppNotificationsPath() {
        return !b.z0() ? "" : a(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_NOTIFICATIONS));
    }

    public static String getExternalAppObbPath() {
        return !b.z0() ? "" : a(Utils.getApp().getObbDir());
    }

    public static String getExternalAppPicturesPath() {
        return !b.z0() ? "" : a(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_PICTURES));
    }

    public static String getExternalAppPodcastsPath() {
        return !b.z0() ? "" : a(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_PODCASTS));
    }

    public static String getExternalAppRingtonesPath() {
        return !b.z0() ? "" : a(Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_RINGTONES));
    }

    public static String getExternalDcimPath() {
        return !b.z0() ? "" : a(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
    }

    public static String getExternalDocumentsPath() {
        if (b.z0()) {
            if (Build.VERSION.SDK_INT < 19) {
                return a(Environment.getExternalStorageDirectory()) + "/Documents";
            }
            return a(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS));
        }
        return "";
    }

    public static String getExternalDownloadsPath() {
        return !b.z0() ? "" : a(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
    }

    public static String getExternalMoviesPath() {
        return !b.z0() ? "" : a(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES));
    }

    public static String getExternalMusicPath() {
        return !b.z0() ? "" : a(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC));
    }

    public static String getExternalNotificationsPath() {
        return !b.z0() ? "" : a(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_NOTIFICATIONS));
    }

    public static String getExternalPicturesPath() {
        return !b.z0() ? "" : a(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
    }

    public static String getExternalPodcastsPath() {
        return !b.z0() ? "" : a(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS));
    }

    public static String getExternalRingtonesPath() {
        return !b.z0() ? "" : a(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_RINGTONES));
    }

    public static String getExternalStoragePath() {
        return !b.z0() ? "" : a(Environment.getExternalStorageDirectory());
    }

    public static String getFilesPathExternalFirst() {
        String externalAppFilesPath = getExternalAppFilesPath();
        return TextUtils.isEmpty(externalAppFilesPath) ? getInternalAppFilesPath() : externalAppFilesPath;
    }

    public static String getInternalAppCachePath() {
        return a(Utils.getApp().getCacheDir());
    }

    public static String getInternalAppCodeCacheDir() {
        if (Build.VERSION.SDK_INT < 21) {
            return Utils.getApp().getApplicationInfo().dataDir + "/code_cache";
        }
        return a(Utils.getApp().getCodeCacheDir());
    }

    public static String getInternalAppDataPath() {
        if (Build.VERSION.SDK_INT < 24) {
            return Utils.getApp().getApplicationInfo().dataDir;
        }
        return a(Utils.getApp().getDataDir());
    }

    public static String getInternalAppDbPath(String str) {
        return a(Utils.getApp().getDatabasePath(str));
    }

    public static String getInternalAppDbsPath() {
        return Utils.getApp().getApplicationInfo().dataDir + "/databases";
    }

    public static String getInternalAppFilesPath() {
        return a(Utils.getApp().getFilesDir());
    }

    public static String getInternalAppNoBackupFilesPath() {
        if (Build.VERSION.SDK_INT < 21) {
            return Utils.getApp().getApplicationInfo().dataDir + "/no_backup";
        }
        return a(Utils.getApp().getNoBackupFilesDir());
    }

    public static String getInternalAppSpPath() {
        return Utils.getApp().getApplicationInfo().dataDir + "/shared_prefs";
    }

    public static String getRootPath() {
        return a(Environment.getRootDirectory());
    }

    public static String getRootPathExternalFirst() {
        String externalStoragePath = getExternalStoragePath();
        return TextUtils.isEmpty(externalStoragePath) ? getRootPath() : externalStoragePath;
    }

    public static String join(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        if (str == null) {
            str = "";
        }
        int length = str.length();
        String b = b(str2);
        if (length == 0) {
            return f2274a + b;
        }
        char charAt = str.charAt(length - 1);
        char c = f2274a;
        if (charAt == c) {
            return str + b;
        }
        return str + c + b;
    }
}
