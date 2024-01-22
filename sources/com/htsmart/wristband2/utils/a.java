package com.htsmart.wristband2.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.htsmart.wristband2.WristbandApplication;
/* loaded from: classes11.dex */
public class a {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f12035a;
    @NonNull
    public String b;
    @NonNull
    public String c;

    public a() {
    }

    public a(@Nullable String str, @NonNull String str2, @NonNull String str3) {
        this.f12035a = str;
        this.b = str2;
        this.c = str3;
    }

    @Nullable
    public static String a(@NonNull String str) {
        SharedPreferences sharedPreferences = WristbandApplication.getContext().getSharedPreferences("wristband_sdk_cache", 0);
        String replace = str.replace(":", "");
        return sharedPreferences.getString("ad" + replace, null);
    }

    public static void a(@NonNull a aVar) {
        WristbandLog.i("setLastConnectDevice name=%s , address=%s , version=%s", aVar.f12035a, aVar.b, aVar.c);
        WristbandApplication.getContext().getSharedPreferences("wristband_sdk_cache", 0).edit().putString("last_device_name", aVar.f12035a).putString("last_device_address", aVar.b).putString("last_device_version", aVar.c).apply();
    }

    public static void a(@NonNull String str, @NonNull String str2) {
        SharedPreferences sharedPreferences = WristbandApplication.getContext().getSharedPreferences("wristband_sdk_cache", 0);
        String replace = str.replace(":", "");
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("ad" + replace, str2).apply();
    }

    @Nullable
    public static a b() {
        SharedPreferences sharedPreferences = WristbandApplication.getContext().getSharedPreferences("wristband_sdk_cache", 0);
        String string = sharedPreferences.getString("last_device_name", "");
        String string2 = sharedPreferences.getString("last_device_address", "");
        String string3 = sharedPreferences.getString("last_device_version", "");
        WristbandLog.i("getLastConnectDevice name=%s , address=%s , version=%s", string, string2, string3);
        if (TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
            return null;
        }
        return new a(string, string2, string3);
    }

    @NonNull
    public String a() {
        return this.b;
    }

    @Nullable
    public String c() {
        return this.f12035a;
    }

    @NonNull
    public String d() {
        return this.c;
    }
}
