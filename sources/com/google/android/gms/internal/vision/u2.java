package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs;
import java.util.Set;
/* loaded from: classes10.dex */
public final class u2 implements r3 {
    public static final c3 b = new x2();

    /* renamed from: a  reason: collision with root package name */
    public final c3 f10001a;

    public u2() {
        this(new w2(k2.a(), c()));
    }

    public static boolean b(d3 d3Var) {
        return d3Var.c() == zzgs.zzf.zzwz;
    }

    public static c3 c() {
        try {
            Set<String> set = com.google.protobuf.i.f11733a;
            return (c3) com.google.protobuf.i.class.getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return b;
        }
    }

    @Override // com.google.android.gms.internal.vision.r3
    public final <T> o3<T> a(Class<T> cls) {
        q3.x(cls);
        d3 zzb = this.f10001a.zzb(cls);
        if (zzb.a()) {
            if (zzgs.class.isAssignableFrom(cls)) {
                return g3.j(q3.B(), f2.b(), zzb.b());
            }
            return g3.j(q3.z(), f2.c(), zzb.b());
        } else if (zzgs.class.isAssignableFrom(cls)) {
            if (b(zzb)) {
                return f3.o(cls, zzb, j3.b(), r2.e(), q3.B(), f2.b(), a3.b());
            }
            return f3.o(cls, zzb, j3.b(), r2.e(), q3.B(), null, a3.b());
        } else if (b(zzb)) {
            return f3.o(cls, zzb, j3.a(), r2.d(), q3.z(), f2.c(), a3.a());
        } else {
            return f3.o(cls, zzb, j3.a(), r2.d(), q3.A(), null, a3.a());
        }
    }

    public u2(c3 c3Var) {
        this.f10001a = (c3) zzgt.c(c3Var, "messageInfoFactory");
    }
}
