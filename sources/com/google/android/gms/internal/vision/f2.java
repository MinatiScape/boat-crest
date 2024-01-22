package com.google.android.gms.internal.vision;
/* loaded from: classes10.dex */
public final class f2 {

    /* renamed from: a  reason: collision with root package name */
    public static final b2<?> f9972a = new d2();
    public static final b2<?> b = a();

    public static b2<?> a() {
        try {
            int i = com.google.protobuf.m.b;
            return (b2) com.google.protobuf.m.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public static b2<?> b() {
        return f9972a;
    }

    public static b2<?> c() {
        b2<?> b2Var = b;
        if (b2Var != null) {
            return b2Var;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
