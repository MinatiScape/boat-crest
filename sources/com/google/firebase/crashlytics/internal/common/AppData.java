package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.firebase.crashlytics.internal.unity.UnityVersionProvider;
/* loaded from: classes10.dex */
public class AppData {
    public final String buildId;
    public final String googleAppId;
    public final String installerPackageName;
    public final String packageName;
    public final UnityVersionProvider unityVersionProvider;
    public final String versionCode;
    public final String versionName;

    public AppData(String str, String str2, String str3, String str4, String str5, String str6, UnityVersionProvider unityVersionProvider) {
        this.googleAppId = str;
        this.buildId = str2;
        this.installerPackageName = str3;
        this.packageName = str4;
        this.versionCode = str5;
        this.versionName = str6;
        this.unityVersionProvider = unityVersionProvider;
    }

    public static AppData create(Context context, IdManager idManager, String str, String str2, UnityVersionProvider unityVersionProvider) throws PackageManager.NameNotFoundException {
        String packageName = context.getPackageName();
        String installerPackageName = idManager.getInstallerPackageName();
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        String num = Integer.toString(packageInfo.versionCode);
        String str3 = packageInfo.versionName;
        if (str3 == null) {
            str3 = IdManager.DEFAULT_VERSION_NAME;
        }
        return new AppData(str, str2, installerPackageName, packageName, num, str3, unityVersionProvider);
    }
}
