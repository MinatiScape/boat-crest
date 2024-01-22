package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Set;
/* loaded from: classes8.dex */
public final class s0 implements m1 {
    public static final w0 b = new q0();

    /* renamed from: a  reason: collision with root package name */
    public final w0 f9613a;

    public s0() {
        w0 w0Var;
        w0[] w0VarArr = new w0[2];
        w0VarArr[0] = h0.a();
        try {
            Set<String> set = com.google.protobuf.i.f11733a;
            w0Var = (w0) com.google.protobuf.i.class.getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            w0Var = b;
        }
        w0VarArr[1] = w0Var;
        r0 r0Var = new r0(w0VarArr);
        byte[] bArr = zzem.zzd;
        this.f9613a = r0Var;
    }

    public static boolean a(v0 v0Var) {
        return v0Var.zzc() == 1;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.m1
    public final l1 zza(Class cls) {
        n1.e(cls);
        v0 zzb = this.f9613a.zzb(cls);
        if (zzb.zzb()) {
            if (zzed.class.isAssignableFrom(cls)) {
                return z0.c(n1.a(), e0.b(), zzb.zza());
            }
            return z0.c(n1.X(), e0.a(), zzb.zza());
        } else if (zzed.class.isAssignableFrom(cls)) {
            if (a(zzb)) {
                return y0.A(cls, zzb, b1.b(), o0.d(), n1.a(), e0.b(), u0.b());
            }
            return y0.A(cls, zzb, b1.b(), o0.d(), n1.a(), null, u0.b());
        } else if (a(zzb)) {
            return y0.A(cls, zzb, b1.a(), o0.c(), n1.X(), e0.a(), u0.a());
        } else {
            return y0.A(cls, zzb, b1.a(), o0.c(), n1.Y(), null, u0.a());
        }
    }
}
