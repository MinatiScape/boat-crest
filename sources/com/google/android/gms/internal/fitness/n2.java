package com.google.android.gms.internal.fitness;
/* loaded from: classes8.dex */
public final class n2 {

    /* renamed from: a  reason: collision with root package name */
    public static final m2<?> f8838a = new o2();
    public static final m2<?> b = a();

    public static m2<?> a() {
        try {
            int i = com.google.protobuf.m.b;
            return (m2) com.google.protobuf.m.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public static m2<?> b() {
        return f8838a;
    }

    public static m2<?> c() {
        m2<?> m2Var = b;
        if (m2Var != null) {
            return m2Var;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
