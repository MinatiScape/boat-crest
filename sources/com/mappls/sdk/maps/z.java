package com.mappls.sdk.maps;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
/* loaded from: classes11.dex */
public class z {

    /* renamed from: a  reason: collision with root package name */
    public final Context f12865a;

    public z(Context context) {
        this.f12865a = context;
    }

    public String a() {
        return this.f12865a.getSharedPreferences("com.mappls.sdk.maps_mappls_style", 0).getString("com.mappls.sdk.maps.key_last_selected_style", null);
    }

    public GetStylesResponse b() {
        String string = this.f12865a.getSharedPreferences("com.mappls.sdk.maps_mappls_style", 0).getString("com.mappls.sdk.maps.key_mappls_style_list", null);
        if (string == null) {
            return null;
        }
        return (GetStylesResponse) new Gson().fromJson(string, (Class<Object>) GetStylesResponse.class);
    }

    public void c(String str) {
        if (str == null) {
            return;
        }
        SharedPreferences.Editor edit = this.f12865a.getSharedPreferences("com.mappls.sdk.maps_mappls_style", 0).edit();
        edit.putString("com.mappls.sdk.maps.key_last_selected_style", str);
        edit.apply();
    }

    public void d(GetStylesResponse getStylesResponse) {
        if (getStylesResponse == null) {
            return;
        }
        SharedPreferences.Editor edit = this.f12865a.getSharedPreferences("com.mappls.sdk.maps_mappls_style", 0).edit();
        edit.putString("com.mappls.sdk.maps.key_mappls_style_list", new Gson().toJson(getStylesResponse));
        edit.apply();
    }
}
