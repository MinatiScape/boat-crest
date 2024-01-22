package com.google.android.gms.internal.auth;
/* loaded from: classes6.dex */
public final class a1 {

    /* renamed from: a  reason: collision with root package name */
    public static final y0 f8516a = new z0();
    public static final y0 b;

    static {
        y0 y0Var;
        try {
            int i = com.google.protobuf.m.b;
            y0Var = (y0) com.google.protobuf.m.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            y0Var = null;
        }
        b = y0Var;
    }

    public static y0 a() {
        y0 y0Var = b;
        if (y0Var != null) {
            return y0Var;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    public static y0 b() {
        return f8516a;
    }
}
