package com.mappls.sdk.services.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.provider.Settings;
import android.util.Base64;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.mappls.sdk.services.BuildConfig;
import java.util.Locale;
@Keep
/* loaded from: classes8.dex */
public final class ApiCallHelper {
    private static final String ONLY_PRINTABLE_CHARS = "[^\\p{ASCII}]";

    private ApiCallHelper() {
    }

    public static String getAnalyticsHeader(Location location, boolean z) {
        Context sDKContext = MapplsUtils.getSDKContext();
        String string = Settings.Secure.getString(sDKContext.getContentResolver(), "android_id");
        StringBuilder sb = new StringBuilder(string + "|");
        if (location != null) {
            sb.append(location.getLatitude());
        }
        sb.append("|");
        if (location != null) {
            sb.append(location.getLongitude());
        }
        sb.append("|");
        if (location != null && location.hasBearing()) {
            sb.append(location.getBearing());
        }
        sb.append("|");
        if (location != null && location.hasSpeed()) {
            sb.append(location.getSpeed());
        }
        sb.append("|");
        sb.append(System.currentTimeMillis());
        sb.append("|");
        sb.append(z);
        sb.append("|");
        try {
            PackageInfo packageInfo = sDKContext.getPackageManager().getPackageInfo(sDKContext.getPackageName(), 0);
            sb.append(packageInfo.packageName);
            sb.append("|");
            sb.append(packageInfo.versionName);
            sb.append("|");
        } catch (PackageManager.NameNotFoundException unused) {
            sb.append("|");
            sb.append("|");
        }
        sb.append(BuildConfig.VERSION_NAME);
        sb.append("|");
        sb.append(BuildConfig.VERSION_NAME);
        sb.append("|");
        sb.append(MapplsUtils.getText());
        sb.append("|");
        sb.append("ANDROID|");
        sb.append(Build.VERSION.SDK_INT);
        sb.append("|");
        sb.append(Build.MANUFACTURER);
        sb.append("|");
        sb.append(Build.MODEL);
        return Base64.encodeToString(sb.toString().getBytes(), 2);
    }

    public static String getHeaderUserAgent() {
        Context sDKContext = MapplsUtils.getSDKContext();
        UserAgent userAgent = new UserAgent();
        userAgent.setAndroidVersion(Build.VERSION.RELEASE);
        userAgent.setAndroidVersionAPI(Integer.valueOf(Build.VERSION.SDK_INT));
        userAgent.setDeviceBrand(Build.BRAND);
        userAgent.setModel(Build.MODEL);
        userAgent.setMapsSDKVersion(MapplsUtils.getText());
        if (sDKContext != null) {
            String charSequence = sDKContext.getApplicationInfo().loadLabel(sDKContext.getPackageManager()).toString();
            userAgent.setDeviceId(Settings.Secure.getString(sDKContext.getContentResolver(), "android_id"));
            userAgent.setAppName(charSequence);
            try {
                PackageInfo packageInfo = sDKContext.getPackageManager().getPackageInfo(sDKContext.getPackageName(), 0);
                userAgent.setAppPackageName(packageInfo.packageName);
                userAgent.setAppVersion(packageInfo.versionName);
                userAgent.setVersionCode(Integer.valueOf(packageInfo.versionCode));
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return String.format(new Gson().toJson(userAgent), "UTF-8");
    }

    @NonNull
    public static String getUserAgent(Context context) {
        UserAgent userAgent = new UserAgent();
        userAgent.setAndroidVersion(Build.VERSION.RELEASE);
        userAgent.setAndroidVersionAPI(Integer.valueOf(Build.VERSION.SDK_INT));
        userAgent.setDeviceBrand(Build.BRAND);
        userAgent.setModel(Build.MODEL);
        userAgent.setMapsSDKVersion(MapplsUtils.getText());
        if (context != null) {
            userAgent.setAppName(context.getApplicationInfo().loadLabel(context.getPackageManager()).toString());
            userAgent.setDeviceId(Settings.Secure.getString(context.getContentResolver(), "android_id"));
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                userAgent.setAppPackageName(packageInfo.packageName);
                userAgent.setAppVersion(packageInfo.versionName);
                userAgent.setVersionCode(Integer.valueOf(packageInfo.versionCode));
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return new Gson().toJson(userAgent);
    }

    public static String getHeaderUserAgent(@Nullable String str) {
        Context sDKContext = MapplsUtils.getSDKContext();
        if (sDKContext == null) {
            String property = System.getProperty("os.name");
            String property2 = System.getProperty("os.version");
            String property3 = System.getProperty("os.arch");
            if (!MapplsUtils.isEmpty(property) && !MapplsUtils.isEmpty(property2) && !MapplsUtils.isEmpty(property3)) {
                return getHeaderUserAgent(str, property, property2, property3);
            }
            return Constants.HEADER_USER_AGENT;
        }
        return getUserAgent(sDKContext);
    }

    public static String getHeaderUserAgent(@Nullable String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        String replaceAll = str2.replaceAll(ONLY_PRINTABLE_CHARS, "");
        String replaceAll2 = str3.replaceAll(ONLY_PRINTABLE_CHARS, "");
        String replaceAll3 = str4.replaceAll(ONLY_PRINTABLE_CHARS, "");
        Locale locale = Locale.US;
        String format = String.format(locale, "%s %s/%s (%s)", Constants.HEADER_USER_AGENT, replaceAll, replaceAll2, replaceAll3);
        return MapplsUtils.isEmpty(str) ? format : String.format(locale, "%s %s", str, format);
    }
}
