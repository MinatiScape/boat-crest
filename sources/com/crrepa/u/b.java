package com.crrepa.u;

import android.content.Context;
import android.content.SharedPreferences;
import com.crrepa.i0.f;
/* loaded from: classes9.dex */
public class b {
    public static b c;

    /* renamed from: a  reason: collision with root package name */
    public SharedPreferences f7843a;
    public SharedPreferences.Editor b;

    public b(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("crp", 0);
        this.f7843a = sharedPreferences;
        this.b = sharedPreferences.edit();
    }

    public static b b() {
        if (c == null) {
            b(f.a());
        }
        return c;
    }

    public static void b(Context context) {
        if (c == null) {
            synchronized (b.class) {
                if (c == null) {
                    c = new b(context);
                }
            }
        }
    }

    public int a(String str, int i) {
        return this.f7843a.getInt(str, i);
    }

    public String a(String str, String str2) {
        return this.f7843a.getString(str, str2);
    }

    public void a() {
        this.b.clear();
        this.b.commit();
    }

    public boolean a(String str, boolean z) {
        return this.f7843a.getBoolean(str, z);
    }

    public void b(String str, int i) {
        this.b.putInt(str, i);
        this.b.commit();
    }

    public void b(String str, String str2) {
        this.b.putString(str, str2);
        this.b.commit();
    }

    public void b(String str, boolean z) {
        this.b.putBoolean(str, z);
        this.b.commit();
    }
}
