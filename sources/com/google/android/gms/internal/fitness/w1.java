package com.google.android.gms.internal.fitness;
/* loaded from: classes8.dex */
public final class w1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Class<?> f8856a = c("libcore.io.Memory");
    public static final boolean b;

    static {
        b = c("org.robolectric.Robolectric") != null;
    }

    public static boolean a() {
        return (f8856a == null || b) ? false : true;
    }

    public static Class<?> b() {
        return f8856a;
    }

    public static <T> Class<T> c(String str) {
        try {
            return (Class<T>) Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }
}
