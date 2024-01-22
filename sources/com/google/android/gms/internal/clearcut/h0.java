package com.google.android.gms.internal.clearcut;
/* loaded from: classes7.dex */
public final class h0 {

    /* renamed from: a  reason: collision with root package name */
    public static final e0<?> f8579a = new f0();
    public static final e0<?> b = a();

    public static e0<?> a() {
        try {
            int i = com.google.protobuf.m.b;
            return (e0) com.google.protobuf.m.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public static e0<?> b() {
        return f8579a;
    }

    public static e0<?> c() {
        e0<?> e0Var = b;
        if (e0Var != null) {
            return e0Var;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
