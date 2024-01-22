package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public final class t5 {

    /* renamed from: a  reason: collision with root package name */
    public static final Class<?> f8732a = a("libcore.io.Memory");
    public static final boolean b;

    static {
        b = a("org.robolectric.Robolectric") != null;
    }

    public static <T> Class<T> a(String str) {
        try {
            return (Class<T>) Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean b() {
        return (f8732a == null || b) ? false : true;
    }

    public static Class<?> c() {
        return f8732a;
    }
}
