package com.mappls.sdk.maps.http;

import android.content.Context;
import android.content.pm.PackageInfo;
import androidx.annotation.NonNull;
import com.mappls.sdk.maps.MapStrictMode;
import com.mappls.sdk.maps.Mappls;
/* loaded from: classes11.dex */
public class HttpIdentifier {
    public static String a(@NonNull Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return String.format("%s/%s (%s)", context.getPackageName(), packageInfo.versionName, Integer.valueOf(packageInfo.versionCode));
        } catch (Exception e) {
            MapStrictMode.strictModeViolation(e);
            return "";
        }
    }

    public static String getIdentifier() {
        return a(Mappls.getApplicationContext());
    }
}
