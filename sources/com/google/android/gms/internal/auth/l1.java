package com.google.android.gms.internal.auth;

import java.util.Set;
/* loaded from: classes6.dex */
public final class l1 implements a2 {
    public static final p1 b = new j1();

    /* renamed from: a  reason: collision with root package name */
    public final p1 f8530a;

    public l1() {
        p1 p1Var;
        p1[] p1VarArr = new p1[2];
        p1VarArr[0] = d1.a();
        try {
            Set<String> set = com.google.protobuf.i.f11733a;
            p1Var = (p1) com.google.protobuf.i.class.getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            p1Var = b;
        }
        p1VarArr[1] = p1Var;
        k1 k1Var = new k1(p1VarArr);
        zzez.c(k1Var, "messageInfoFactory");
        this.f8530a = k1Var;
    }

    public static boolean a(o1 o1Var) {
        return o1Var.zzc() == 1;
    }

    @Override // com.google.android.gms.internal.auth.a2
    public final z1 zza(Class cls) {
        b2.g(cls);
        o1 zzb = this.f8530a.zzb(cls);
        if (zzb.zzb()) {
            if (zzeu.class.isAssignableFrom(cls)) {
                return s1.a(b2.c(), a1.b(), zzb.zza());
            }
            return s1.a(b2.a(), a1.a(), zzb.zza());
        } else if (zzeu.class.isAssignableFrom(cls)) {
            if (a(zzb)) {
                return r1.q(cls, zzb, u1.b(), h1.d(), b2.c(), a1.b(), n1.b());
            }
            return r1.q(cls, zzb, u1.b(), h1.d(), b2.c(), null, n1.b());
        } else if (a(zzb)) {
            return r1.q(cls, zzb, u1.a(), h1.c(), b2.a(), a1.a(), n1.a());
        } else {
            return r1.q(cls, zzb, u1.a(), h1.c(), b2.b(), null, n1.a());
        }
    }
}
