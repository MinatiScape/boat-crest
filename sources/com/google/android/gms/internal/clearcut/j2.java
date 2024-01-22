package com.google.android.gms.internal.clearcut;

import java.io.IOException;
/* loaded from: classes7.dex */
public final class j2 extends i2<zzey, zzey> {
    public static void m(Object obj, zzey zzeyVar) {
        ((zzcg) obj).zzjp = zzeyVar;
    }

    @Override // com.google.android.gms.internal.clearcut.i2
    public final /* synthetic */ void a(zzey zzeyVar, int i, long j) {
        zzeyVar.d(i << 3, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.clearcut.i2
    public final /* synthetic */ void b(zzey zzeyVar, int i, zzbb zzbbVar) {
        zzeyVar.d((i << 3) | 2, zzbbVar);
    }

    @Override // com.google.android.gms.internal.clearcut.i2
    public final /* synthetic */ void c(zzey zzeyVar, z2 z2Var) throws IOException {
        zzeyVar.zzb(z2Var);
    }

    @Override // com.google.android.gms.internal.clearcut.i2
    public final void d(Object obj) {
        ((zzcg) obj).zzjp.zzv();
    }

    @Override // com.google.android.gms.internal.clearcut.i2
    public final /* synthetic */ void e(zzey zzeyVar, z2 z2Var) throws IOException {
        zzeyVar.b(z2Var);
    }

    @Override // com.google.android.gms.internal.clearcut.i2
    public final /* synthetic */ zzey f() {
        return zzey.f();
    }

    @Override // com.google.android.gms.internal.clearcut.i2
    public final /* synthetic */ void g(Object obj, zzey zzeyVar) {
        m(obj, zzeyVar);
    }

    @Override // com.google.android.gms.internal.clearcut.i2
    public final /* synthetic */ void h(Object obj, zzey zzeyVar) {
        m(obj, zzeyVar);
    }

    @Override // com.google.android.gms.internal.clearcut.i2
    public final /* synthetic */ zzey i(zzey zzeyVar, zzey zzeyVar2) {
        zzey zzeyVar3 = zzeyVar;
        zzey zzeyVar4 = zzeyVar2;
        return zzeyVar4.equals(zzey.zzea()) ? zzeyVar3 : zzey.a(zzeyVar3, zzeyVar4);
    }

    @Override // com.google.android.gms.internal.clearcut.i2
    public final /* synthetic */ int j(zzey zzeyVar) {
        return zzeyVar.zzas();
    }

    @Override // com.google.android.gms.internal.clearcut.i2
    public final /* synthetic */ zzey k(Object obj) {
        return ((zzcg) obj).zzjp;
    }

    @Override // com.google.android.gms.internal.clearcut.i2
    public final /* synthetic */ int l(zzey zzeyVar) {
        return zzeyVar.zzec();
    }
}
