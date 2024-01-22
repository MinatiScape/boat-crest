package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import java.util.Set;
/* loaded from: classes7.dex */
public final class x0 implements s1 {
    public static final f1 b = new y0();

    /* renamed from: a  reason: collision with root package name */
    public final f1 f8604a;

    public x0() {
        this(new z0(n0.a(), c()));
    }

    public x0(f1 f1Var) {
        this.f8604a = (f1) zzci.d(f1Var, "messageInfoFactory");
    }

    public static boolean b(e1 e1Var) {
        return e1Var.a() == zzcg.zzg.zzkl;
    }

    public static f1 c() {
        try {
            Set<String> set = com.google.protobuf.i.f11733a;
            return (f1) com.google.protobuf.i.class.getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return b;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.s1
    public final <T> r1<T> a(Class<T> cls) {
        t1.I(cls);
        e1 zzb = this.f8604a.zzb(cls);
        if (zzb.b()) {
            return zzcg.class.isAssignableFrom(cls) ? i1.e(t1.B(), h0.b(), zzb.zzch()) : i1.e(t1.z(), h0.c(), zzb.zzch());
        } else if (!zzcg.class.isAssignableFrom(cls)) {
            boolean b2 = b(zzb);
            j1 a2 = l1.a();
            s0 c = s0.c();
            return b2 ? h1.q(cls, zzb, a2, c, t1.z(), h0.c(), d1.a()) : h1.q(cls, zzb, a2, c, t1.A(), null, d1.a());
        } else {
            boolean b3 = b(zzb);
            j1 b4 = l1.b();
            s0 d = s0.d();
            i2<?, ?> B = t1.B();
            return b3 ? h1.q(cls, zzb, b4, d, B, h0.b(), d1.b()) : h1.q(cls, zzb, b4, d, B, null, d1.b());
        }
    }
}
