package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public final class q6 {

    /* renamed from: a  reason: collision with root package name */
    public static final o6<?> f8720a = new n6();
    public static final o6<?> b = a();

    public static o6<?> a() {
        try {
            int i = com.google.protobuf.m.b;
            return (o6) com.google.protobuf.m.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public static o6<?> b() {
        return f8720a;
    }

    public static o6<?> c() {
        o6<?> o6Var = b;
        if (o6Var != null) {
            return o6Var;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
