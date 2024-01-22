package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class x2 {

    /* renamed from: a  reason: collision with root package name */
    public static final v2<?> f8935a = new w2();
    public static final v2<?> b;

    static {
        v2<?> v2Var;
        try {
            int i = com.google.protobuf.m.b;
            v2Var = (v2) com.google.protobuf.m.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            v2Var = null;
        }
        b = v2Var;
    }

    public static v2<?> a() {
        v2<?> v2Var = b;
        if (v2Var != null) {
            return v2Var;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    public static v2<?> b() {
        return f8935a;
    }
}
