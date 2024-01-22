package com.blankj.utilcode.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.blankj.utilcode.util.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
/* loaded from: classes.dex */
public final class AppUtils {

    /* loaded from: classes.dex */
    public static class AppInfo {

        /* renamed from: a  reason: collision with root package name */
        public String f2233a;
        public String b;
        public Drawable c;
        public String d;
        public String e;
        public int f;
        public boolean g;

        public AppInfo(String str, String str2, Drawable drawable, String str3, String str4, int i, boolean z) {
            setName(str2);
            setIcon(drawable);
            setPackageName(str);
            setPackagePath(str3);
            setVersionName(str4);
            setVersionCode(i);
            setSystem(z);
        }

        public Drawable getIcon() {
            return this.c;
        }

        public String getName() {
            return this.b;
        }

        public String getPackageName() {
            return this.f2233a;
        }

        public String getPackagePath() {
            return this.d;
        }

        public int getVersionCode() {
            return this.f;
        }

        public String getVersionName() {
            return this.e;
        }

        public boolean isSystem() {
            return this.g;
        }

        public void setIcon(Drawable drawable) {
            this.c = drawable;
        }

        public void setName(String str) {
            this.b = str;
        }

        public void setPackageName(String str) {
            this.f2233a = str;
        }

        public void setPackagePath(String str) {
            this.d = str;
        }

        public void setSystem(boolean z) {
            this.g = z;
        }

        public void setVersionCode(int i) {
            this.f = i;
        }

        public void setVersionName(String str) {
            this.e = str;
        }

        @NonNull
        public String toString() {
            String str = "{\n    pkg name: " + getPackageName() + "\n    app icon: " + getIcon() + "\n    app name: " + getName() + "\n    app path: " + getPackagePath() + "\n    app v name: " + getVersionName() + "\n    app v code: " + getVersionCode() + "\n    is system: " + isSystem() + "\n}";
            Objects.requireNonNull(str, "Detected an attempt to return null from a method com.blankj.utilcode.util.AppUtils.AppInfo.toString() marked by @androidx.annotation.NonNull");
            return str;
        }
    }

    public AppUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static List<String> a(String str, String str2) {
        Signature[] appSignatures;
        ArrayList arrayList = new ArrayList();
        if (!b.C0(str) && (appSignatures = getAppSignatures(str)) != null && appSignatures.length > 0) {
            for (Signature signature : appSignatures) {
                arrayList.add(b.l(b.j0(signature.toByteArray(), str2)).replaceAll("(?<=[0-9A-F]{2})[0-9A-F]{2}", ":$0"));
            }
        }
        return arrayList;
    }

    public static AppInfo b(PackageManager packageManager, PackageInfo packageInfo) {
        if (packageInfo == null) {
            return null;
        }
        String str = packageInfo.versionName;
        int i = packageInfo.versionCode;
        String str2 = packageInfo.packageName;
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        if (applicationInfo == null) {
            return new AppInfo(str2, "", null, "", str, i, false);
        }
        return new AppInfo(str2, applicationInfo.loadLabel(packageManager).toString(), applicationInfo.loadIcon(packageManager), applicationInfo.sourceDir, str, i, (applicationInfo.flags & 1) != 0);
    }

    public static void exitApp() {
        b.D();
        System.exit(0);
    }

    @Nullable
    public static AppInfo getApkInfo(File file) {
        if (file != null && file.isFile() && file.exists()) {
            return getApkInfo(file.getAbsolutePath());
        }
        return null;
    }

    @Nullable
    public static Drawable getAppIcon() {
        return getAppIcon(Utils.getApp().getPackageName());
    }

    public static int getAppIconId() {
        return getAppIconId(Utils.getApp().getPackageName());
    }

    @Nullable
    public static AppInfo getAppInfo() {
        return getAppInfo(Utils.getApp().getPackageName());
    }

    @NonNull
    public static String getAppName() {
        String appName = getAppName(Utils.getApp().getPackageName());
        Objects.requireNonNull(appName, "Detected an attempt to return null from a method com.blankj.utilcode.util.AppUtils.getAppName() marked by @androidx.annotation.NonNull");
        return appName;
    }

    @NonNull
    public static String getAppPackageName() {
        String packageName = Utils.getApp().getPackageName();
        Objects.requireNonNull(packageName, "Detected an attempt to return null from a method com.blankj.utilcode.util.AppUtils.getAppPackageName() marked by @androidx.annotation.NonNull");
        return packageName;
    }

    @NonNull
    public static String getAppPath() {
        String appPath = getAppPath(Utils.getApp().getPackageName());
        Objects.requireNonNull(appPath, "Detected an attempt to return null from a method com.blankj.utilcode.util.AppUtils.getAppPath() marked by @androidx.annotation.NonNull");
        return appPath;
    }

    @Nullable
    public static Signature[] getAppSignatures() {
        return getAppSignatures(Utils.getApp().getPackageName());
    }

    @NonNull
    public static List<String> getAppSignaturesMD5() {
        List<String> appSignaturesMD5 = getAppSignaturesMD5(Utils.getApp().getPackageName());
        Objects.requireNonNull(appSignaturesMD5, "Detected an attempt to return null from a method com.blankj.utilcode.util.AppUtils.getAppSignaturesMD5() marked by @androidx.annotation.NonNull");
        return appSignaturesMD5;
    }

    @NonNull
    public static List<String> getAppSignaturesSHA1() {
        List<String> appSignaturesSHA1 = getAppSignaturesSHA1(Utils.getApp().getPackageName());
        Objects.requireNonNull(appSignaturesSHA1, "Detected an attempt to return null from a method com.blankj.utilcode.util.AppUtils.getAppSignaturesSHA1() marked by @androidx.annotation.NonNull");
        return appSignaturesSHA1;
    }

    @NonNull
    public static List<String> getAppSignaturesSHA256() {
        List<String> appSignaturesSHA256 = getAppSignaturesSHA256(Utils.getApp().getPackageName());
        Objects.requireNonNull(appSignaturesSHA256, "Detected an attempt to return null from a method com.blankj.utilcode.util.AppUtils.getAppSignaturesSHA256() marked by @androidx.annotation.NonNull");
        return appSignaturesSHA256;
    }

    public static int getAppUid() {
        return getAppUid(Utils.getApp().getPackageName());
    }

    public static int getAppVersionCode() {
        return getAppVersionCode(Utils.getApp().getPackageName());
    }

    @NonNull
    public static String getAppVersionName() {
        String appVersionName = getAppVersionName(Utils.getApp().getPackageName());
        Objects.requireNonNull(appVersionName, "Detected an attempt to return null from a method com.blankj.utilcode.util.AppUtils.getAppVersionName() marked by @androidx.annotation.NonNull");
        return appVersionName;
    }

    @NonNull
    public static List<AppInfo> getAppsInfo() {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = Utils.getApp().getPackageManager();
        if (packageManager == null) {
            return arrayList;
        }
        for (PackageInfo packageInfo : packageManager.getInstalledPackages(0)) {
            AppInfo b = b(packageManager, packageInfo);
            if (b != null) {
                arrayList.add(b);
            }
        }
        return arrayList;
    }

    public static void installApp(String str) {
        installApp(b.P(str));
    }

    public static boolean isAppDebug() {
        return isAppDebug(Utils.getApp().getPackageName());
    }

    public static boolean isAppForeground() {
        return b.q0();
    }

    public static boolean isAppInstalled(String str) {
        if (b.C0(str)) {
            return false;
        }
        try {
            return Utils.getApp().getPackageManager().getApplicationInfo(str, 0).enabled;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean isAppRoot() {
        return b.B("echo root", true).result == 0;
    }

    public static boolean isAppRunning(String str) {
        ActivityManager activityManager;
        if (!b.C0(str) && (activityManager = (ActivityManager) Utils.getApp().getSystemService("activity")) != null) {
            List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
            if (runningTasks != null && runningTasks.size() > 0) {
                for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTasks) {
                    ComponentName componentName = runningTaskInfo.baseActivity;
                    if (componentName != null && str.equals(componentName.getPackageName())) {
                        return true;
                    }
                }
            }
            List<ActivityManager.RunningServiceInfo> runningServices = activityManager.getRunningServices(Integer.MAX_VALUE);
            if (runningServices != null && runningServices.size() > 0) {
                for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
                    if (str.equals(runningServiceInfo.service.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isAppSystem() {
        return isAppSystem(Utils.getApp().getPackageName());
    }

    public static void launchApp(String str) {
        if (b.C0(str)) {
            return;
        }
        Intent Y = b.Y(str);
        if (Y == null) {
            Log.e("AppUtils", "Didn't exist launcher activity.");
        } else {
            Utils.getApp().startActivity(Y);
        }
    }

    public static void launchAppDetailsSettings() {
        launchAppDetailsSettings(Utils.getApp().getPackageName());
    }

    public static void registerAppStatusChangedListener(@NonNull Utils.OnAppStatusChangedListener onAppStatusChangedListener) {
        Objects.requireNonNull(onAppStatusChangedListener, "Argument 'listener' of type Utils.OnAppStatusChangedListener (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        b.c(onAppStatusChangedListener);
    }

    public static void relaunchApp() {
        relaunchApp(false);
    }

    public static void uninstallApp(String str) {
        if (b.C0(str)) {
            return;
        }
        Utils.getApp().startActivity(b.i0(str));
    }

    public static void unregisterAppStatusChangedListener(@NonNull Utils.OnAppStatusChangedListener onAppStatusChangedListener) {
        Objects.requireNonNull(onAppStatusChangedListener, "Argument 'listener' of type Utils.OnAppStatusChangedListener (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        b.T0(onAppStatusChangedListener);
    }

    @Nullable
    public static Drawable getAppIcon(String str) {
        if (b.C0(str)) {
            return null;
        }
        try {
            PackageManager packageManager = Utils.getApp().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            if (packageInfo == null) {
                return null;
            }
            return packageInfo.applicationInfo.loadIcon(packageManager);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getAppIconId(String str) {
        if (b.C0(str)) {
            return 0;
        }
        try {
            PackageInfo packageInfo = Utils.getApp().getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null) {
                return 0;
            }
            return packageInfo.applicationInfo.icon;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Nullable
    public static AppInfo getAppInfo(String str) {
        try {
            PackageManager packageManager = Utils.getApp().getPackageManager();
            if (packageManager == null) {
                return null;
            }
            return b(packageManager, packageManager.getPackageInfo(str, 0));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @NonNull
    public static String getAppName(String str) {
        if (b.C0(str)) {
            return "";
        }
        try {
            PackageManager packageManager = Utils.getApp().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            String charSequence = packageInfo == null ? "" : packageInfo.applicationInfo.loadLabel(packageManager).toString();
            if (charSequence != null) {
                return charSequence;
            }
            throw new NullPointerException("Detected an attempt to return null from a method com.blankj.utilcode.util.AppUtils.getAppName() marked by @androidx.annotation.NonNull");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    @NonNull
    public static String getAppPath(String str) {
        if (b.C0(str)) {
            return "";
        }
        try {
            PackageInfo packageInfo = Utils.getApp().getPackageManager().getPackageInfo(str, 0);
            String str2 = packageInfo == null ? "" : packageInfo.applicationInfo.sourceDir;
            if (str2 != null) {
                return str2;
            }
            throw new NullPointerException("Detected an attempt to return null from a method com.blankj.utilcode.util.AppUtils.getAppPath() marked by @androidx.annotation.NonNull");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Nullable
    public static Signature[] getAppSignatures(String str) {
        if (b.C0(str)) {
            return null;
        }
        try {
            PackageManager packageManager = Utils.getApp().getPackageManager();
            if (Build.VERSION.SDK_INT >= 28) {
                PackageInfo packageInfo = packageManager.getPackageInfo(str, 134217728);
                if (packageInfo == null) {
                    return null;
                }
                SigningInfo signingInfo = packageInfo.signingInfo;
                if (signingInfo.hasMultipleSigners()) {
                    return signingInfo.getApkContentsSigners();
                }
                return signingInfo.getSigningCertificateHistory();
            }
            PackageInfo packageInfo2 = packageManager.getPackageInfo(str, 64);
            if (packageInfo2 == null) {
                return null;
            }
            return packageInfo2.signatures;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @NonNull
    public static List<String> getAppSignaturesMD5(String str) {
        List<String> a2 = a(str, MessageDigestAlgorithms.MD5);
        Objects.requireNonNull(a2, "Detected an attempt to return null from a method com.blankj.utilcode.util.AppUtils.getAppSignaturesMD5() marked by @androidx.annotation.NonNull");
        return a2;
    }

    @NonNull
    public static List<String> getAppSignaturesSHA1(String str) {
        List<String> a2 = a(str, "SHA1");
        Objects.requireNonNull(a2, "Detected an attempt to return null from a method com.blankj.utilcode.util.AppUtils.getAppSignaturesSHA1() marked by @androidx.annotation.NonNull");
        return a2;
    }

    @NonNull
    public static List<String> getAppSignaturesSHA256(String str) {
        List<String> a2 = a(str, "SHA256");
        Objects.requireNonNull(a2, "Detected an attempt to return null from a method com.blankj.utilcode.util.AppUtils.getAppSignaturesSHA256() marked by @androidx.annotation.NonNull");
        return a2;
    }

    public static int getAppUid(String str) {
        try {
            return Utils.getApp().getPackageManager().getApplicationInfo(str, 0).uid;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getAppVersionCode(String str) {
        if (b.C0(str)) {
            return -1;
        }
        try {
            PackageInfo packageInfo = Utils.getApp().getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null) {
                return -1;
            }
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @NonNull
    public static String getAppVersionName(String str) {
        if (b.C0(str)) {
            return "";
        }
        try {
            PackageInfo packageInfo = Utils.getApp().getPackageManager().getPackageInfo(str, 0);
            String str2 = packageInfo == null ? "" : packageInfo.versionName;
            if (str2 != null) {
                return str2;
            }
            throw new NullPointerException("Detected an attempt to return null from a method com.blankj.utilcode.util.AppUtils.getAppVersionName() marked by @androidx.annotation.NonNull");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void installApp(File file) {
        Intent W = b.W(file);
        if (W == null) {
            return;
        }
        Utils.getApp().startActivity(W);
    }

    public static boolean isAppDebug(String str) {
        if (b.C0(str)) {
            return false;
        }
        try {
            return (Utils.getApp().getPackageManager().getApplicationInfo(str, 0).flags & 2) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isAppForeground(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'pkgName' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return !b.C0(str) && str.equals(b.Q());
    }

    public static boolean isAppSystem(String str) {
        if (b.C0(str)) {
            return false;
        }
        try {
            return (Utils.getApp().getPackageManager().getApplicationInfo(str, 0).flags & 1) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void launchAppDetailsSettings(String str) {
        if (b.C0(str)) {
            return;
        }
        Intent X = b.X(str, true);
        if (b.w0(X)) {
            Utils.getApp().startActivity(X);
        }
    }

    public static void relaunchApp(boolean z) {
        Intent Y = b.Y(Utils.getApp().getPackageName());
        if (Y == null) {
            Log.e("AppUtils", "Didn't exist launcher activity.");
            return;
        }
        Y.addFlags(335577088);
        Utils.getApp().startActivity(Y);
        if (z) {
            Process.killProcess(Process.myPid());
            System.exit(0);
        }
    }

    @Nullable
    public static AppInfo getApkInfo(String str) {
        PackageManager packageManager;
        PackageInfo packageArchiveInfo;
        if (b.C0(str) || (packageManager = Utils.getApp().getPackageManager()) == null || (packageArchiveInfo = packageManager.getPackageArchiveInfo(str, 0)) == null) {
            return null;
        }
        ApplicationInfo applicationInfo = packageArchiveInfo.applicationInfo;
        applicationInfo.sourceDir = str;
        applicationInfo.publicSourceDir = str;
        return b(packageManager, packageArchiveInfo);
    }

    public static void installApp(Uri uri) {
        Intent V = b.V(uri);
        if (V == null) {
            return;
        }
        Utils.getApp().startActivity(V);
    }

    public static void launchAppDetailsSettings(Activity activity, int i) {
        launchAppDetailsSettings(activity, i, Utils.getApp().getPackageName());
    }

    public static void launchAppDetailsSettings(Activity activity, int i, String str) {
        if (activity == null || b.C0(str)) {
            return;
        }
        Intent X = b.X(str, false);
        if (b.w0(X)) {
            activity.startActivityForResult(X, i);
        }
    }

    @Nullable
    public static Signature[] getAppSignatures(File file) {
        if (file == null) {
            return null;
        }
        PackageManager packageManager = Utils.getApp().getPackageManager();
        if (Build.VERSION.SDK_INT >= 28) {
            PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(file.getAbsolutePath(), 134217728);
            if (packageArchiveInfo == null) {
                return null;
            }
            SigningInfo signingInfo = packageArchiveInfo.signingInfo;
            if (signingInfo.hasMultipleSigners()) {
                return signingInfo.getApkContentsSigners();
            }
            return signingInfo.getSigningCertificateHistory();
        }
        PackageInfo packageArchiveInfo2 = packageManager.getPackageArchiveInfo(file.getAbsolutePath(), 64);
        if (packageArchiveInfo2 == null) {
            return null;
        }
        return packageArchiveInfo2.signatures;
    }
}
