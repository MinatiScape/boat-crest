package com.crrepa.i0;

import android.annotation.SuppressLint;
import android.content.Context;
/* loaded from: classes9.dex */
public class f {
    @SuppressLint({"StaticFieldLeak"})

    /* renamed from: a  reason: collision with root package name */
    public static Context f7729a;
    @SuppressLint({"StaticFieldLeak"})
    public static String b;
    @SuppressLint({"StaticFieldLeak"})
    public static String c;

    public static Context a() {
        return f7729a;
    }

    public static void a(Context context) {
        f7729a = context;
    }

    public static void a(String str) {
        c = str;
    }

    public static String b() {
        return c;
    }

    public static void b(String str) {
        b = str;
    }

    public static String c() {
        return b;
    }
}
