package com.google.android.gms.internal.vision;
/* loaded from: classes10.dex */
public final class e1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Class<?> f9968a = c("libcore.io.Memory");
    public static final boolean b;

    static {
        b = c("org.robolectric.Robolectric") != null;
    }

    public static boolean a() {
        return (f9968a == null || b) ? false : true;
    }

    public static Class<?> b() {
        return f9968a;
    }

    public static <T> Class<T> c(String str) {
        try {
            return (Class<T>) Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }
}
