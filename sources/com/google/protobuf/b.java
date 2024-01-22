package com.google.protobuf;
/* loaded from: classes11.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f11718a;
    public static final Class<?> b = a("libcore.io.Memory");
    public static final boolean c;

    static {
        c = (f11718a || a("org.robolectric.Robolectric") == null) ? false : true;
    }

    public static <T> Class<T> a(String str) {
        try {
            return (Class<T>) Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Class<?> b() {
        return b;
    }

    public static boolean c() {
        return f11718a || !(b == null || c);
    }
}
