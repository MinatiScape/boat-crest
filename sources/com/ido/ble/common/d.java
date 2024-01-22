package com.ido.ble.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.ido.ble.custom.CustomConfig;
import java.util.Set;
/* loaded from: classes11.dex */
public class d {
    private static final String b = "A12cDAD5EF90";

    /* renamed from: a  reason: collision with root package name */
    public SharedPreferences f12146a;

    public float a(String str, float f) {
        return this.f12146a.getFloat(str, f);
    }

    public int a(String str, int i) {
        return this.f12146a.getInt(str, i);
    }

    public long a(String str, long j) {
        return this.f12146a.getLong(str, j);
    }

    public <T> T a(String str, Class<T> cls) {
        String a2 = a(str, "");
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return (T) new Gson().fromJson(a2, (Class<Object>) cls);
    }

    public String a(String str, String str2) {
        return CustomConfig.getConfig().isEncryptedSPData() ? a.a(this.f12146a.getString(str, str2), b) : this.f12146a.getString(str, str2);
    }

    public Set<String> a(String str, Set<String> set) {
        return this.f12146a.getStringSet(str, set);
    }

    public void a() {
        this.f12146a.edit().clear().commit();
    }

    public void a(Context context, String str) {
        this.f12146a = context.getSharedPreferences(str, 0);
    }

    public boolean a(String str) {
        return this.f12146a.contains(str);
    }

    public boolean a(String str, boolean z) {
        return this.f12146a.getBoolean(str, z);
    }

    public void b(String str, float f) {
        this.f12146a.edit().putFloat(str, f).commit();
    }

    public void b(String str, int i) {
        this.f12146a.edit().putInt(str, i).commit();
    }

    public void b(String str, long j) {
        this.f12146a.edit().putLong(str, j).commit();
    }

    public void b(String str, String str2) {
        if (CustomConfig.getConfig().isEncryptedSPData()) {
            str2 = a.b(str2, b);
        }
        this.f12146a.edit().putString(str, str2).commit();
    }

    public void b(String str, Set<String> set) {
        this.f12146a.edit().putStringSet(str, set).commit();
    }

    public void b(String str, boolean z) {
        this.f12146a.edit().putBoolean(str, z).commit();
    }

    public boolean b(String str) {
        return this.f12146a.edit().remove(str).commit();
    }
}
