package com.mappls.sdk.services.api;

import android.content.Context;
import android.content.SharedPreferences;
/* loaded from: classes11.dex */
public class SDKPreferenceHelper {
    public static SDKPreferenceHelper b;

    /* renamed from: a  reason: collision with root package name */
    public final SharedPreferences f13149a;

    public SDKPreferenceHelper(Context context) {
        this.f13149a = context.getSharedPreferences("com.mappls.sdk.services.api.STORAGE_SHARED_PREFERENCE", 0);
    }

    public static SDKPreferenceHelper getInstance(Context context) {
        if (b == null) {
            b = new SDKPreferenceHelper(context);
        }
        return b;
    }

    public void a() {
        SharedPreferences.Editor edit = this.f13149a.edit();
        edit.clear();
        edit.apply();
    }

    public String b() {
        return this.f13149a.getString("com.mappls.sdk.services.api.API_DETAIL_KEY", null);
    }

    public String c() {
        return this.f13149a.getString("com.mappls.sdk.services.api.SDK_CONFIG_KEY", null);
    }

    public void d(String str) {
        SharedPreferences.Editor edit = this.f13149a.edit();
        edit.putString("com.mappls.sdk.services.api.SDK_CONFIG_KEY", str);
        edit.apply();
    }

    public void setApiDetail(String str) {
        SharedPreferences.Editor edit = this.f13149a.edit();
        edit.putString("com.mappls.sdk.services.api.API_DETAIL_KEY", str);
        edit.apply();
    }
}
