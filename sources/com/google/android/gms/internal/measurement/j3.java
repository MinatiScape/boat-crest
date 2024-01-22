package com.google.android.gms.internal.measurement;

import java.util.Set;
/* loaded from: classes8.dex */
public final class j3 implements y3 {
    public static final n3 b = new h3();

    /* renamed from: a  reason: collision with root package name */
    public final n3 f8916a;

    public j3() {
        n3 n3Var;
        n3[] n3VarArr = new n3[2];
        n3VarArr[0] = b3.a();
        try {
            Set<String> set = com.google.protobuf.i.f11733a;
            n3Var = (n3) com.google.protobuf.i.class.getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            n3Var = b;
        }
        n3VarArr[1] = n3Var;
        i3 i3Var = new i3(n3VarArr);
        zzkh.c(i3Var, "messageInfoFactory");
        this.f8916a = i3Var;
    }

    public static boolean a(m3 m3Var) {
        return m3Var.zzc() == 1;
    }

    @Override // com.google.android.gms.internal.measurement.y3
    public final <T> x3<T> zza(Class<T> cls) {
        z3.g(cls);
        m3 zzb = this.f8916a.zzb(cls);
        if (zzb.zzb()) {
            if (zzjz.class.isAssignableFrom(cls)) {
                return q3.e(z3.b(), x2.b(), zzb.zza());
            }
            return q3.e(z3.b0(), x2.a(), zzb.zza());
        } else if (zzjz.class.isAssignableFrom(cls)) {
            if (a(zzb)) {
                return p3.A(cls, zzb, s3.b(), f3.d(), z3.b(), x2.b(), l3.b());
            }
            return p3.A(cls, zzb, s3.b(), f3.d(), z3.b(), null, l3.b());
        } else if (a(zzb)) {
            return p3.A(cls, zzb, s3.a(), f3.c(), z3.b0(), x2.a(), l3.a());
        } else {
            return p3.A(cls, zzb, s3.a(), f3.c(), z3.a(), null, l3.a());
        }
    }
}
