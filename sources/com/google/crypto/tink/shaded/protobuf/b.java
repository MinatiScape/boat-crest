package com.google.crypto.tink.shaded.protobuf;
/* loaded from: classes10.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public static final Class<?> f10970a = a("libcore.io.Memory");
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

    public static Class<?> b() {
        return f10970a;
    }

    public static boolean c() {
        return (f10970a == null || b) ? false : true;
    }
}
