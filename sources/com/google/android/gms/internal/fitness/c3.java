package com.google.android.gms.internal.fitness;

import java.util.Set;
/* loaded from: classes8.dex */
public final class c3 implements w3 {
    public static final k3 b = new f3();

    /* renamed from: a  reason: collision with root package name */
    public final k3 f8820a;

    public c3() {
        this(new e3(t2.a(), c()));
    }

    public static boolean b(l3 l3Var) {
        return l3Var.a() == zziw.zzaaw;
    }

    public static k3 c() {
        try {
            Set<String> set = com.google.protobuf.i.f11733a;
            return (k3) com.google.protobuf.i.class.getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return b;
        }
    }

    @Override // com.google.android.gms.internal.fitness.w3
    public final <T> x3<T> a(Class<T> cls) {
        z3.E(cls);
        l3 zzc = this.f8820a.zzc(cls);
        if (zzc.b()) {
            if (zzgy.class.isAssignableFrom(cls)) {
                return n3.f(z3.w(), n2.b(), zzc.c());
            }
            return n3.f(z3.u(), n2.c(), zzc.c());
        } else if (zzgy.class.isAssignableFrom(cls)) {
            if (b(zzc)) {
                return o3.g(cls, zzc, q3.b(), z2.d(), z3.w(), n2.b(), i3.b());
            }
            return o3.g(cls, zzc, q3.b(), z2.d(), z3.w(), null, i3.b());
        } else if (b(zzc)) {
            return o3.g(cls, zzc, q3.a(), z2.c(), z3.u(), n2.c(), i3.a());
        } else {
            return o3.g(cls, zzc, q3.a(), z2.c(), z3.v(), null, i3.a());
        }
    }

    public c3(k3 k3Var) {
        this.f8820a = (k3) zzhc.c(k3Var, "messageInfoFactory");
    }
}
