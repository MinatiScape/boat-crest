package com.google.android.gms.internal.vision;

import java.io.IOException;
/* loaded from: classes10.dex */
public final class h4 extends g4<zzjm, zzjm> {
    public static void t(Object obj, zzjm zzjmVar) {
        ((zzgs) obj).zzwj = zzjmVar;
    }

    @Override // com.google.android.gms.internal.vision.g4
    public final /* synthetic */ void a(zzjm zzjmVar, int i, long j) {
        zzjmVar.d(i << 3, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.vision.g4
    public final /* synthetic */ void b(zzjm zzjmVar, int i, zzfh zzfhVar) {
        zzjmVar.d((i << 3) | 2, zzfhVar);
    }

    @Override // com.google.android.gms.internal.vision.g4
    public final /* synthetic */ void c(zzjm zzjmVar, int i, zzjm zzjmVar2) {
        zzjmVar.d((i << 3) | 3, zzjmVar2);
    }

    @Override // com.google.android.gms.internal.vision.g4
    public final /* synthetic */ void d(zzjm zzjmVar, x4 x4Var) throws IOException {
        zzjmVar.zzb(x4Var);
    }

    @Override // com.google.android.gms.internal.vision.g4
    public final boolean e(p3 p3Var) {
        return false;
    }

    @Override // com.google.android.gms.internal.vision.g4
    public final /* synthetic */ void g(zzjm zzjmVar, int i, long j) {
        zzjmVar.d((i << 3) | 1, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.vision.g4
    public final /* synthetic */ void h(zzjm zzjmVar, int i, int i2) {
        zzjmVar.d((i << 3) | 5, Integer.valueOf(i2));
    }

    @Override // com.google.android.gms.internal.vision.g4
    public final /* synthetic */ void i(zzjm zzjmVar, x4 x4Var) throws IOException {
        zzjmVar.b(x4Var);
    }

    @Override // com.google.android.gms.internal.vision.g4
    public final /* synthetic */ void j(Object obj, zzjm zzjmVar) {
        t(obj, zzjmVar);
    }

    @Override // com.google.android.gms.internal.vision.g4
    public final /* synthetic */ void k(Object obj, zzjm zzjmVar) {
        t(obj, zzjmVar);
    }

    @Override // com.google.android.gms.internal.vision.g4
    public final /* synthetic */ zzjm l(zzjm zzjmVar, zzjm zzjmVar2) {
        zzjm zzjmVar3 = zzjmVar;
        zzjm zzjmVar4 = zzjmVar2;
        return zzjmVar4.equals(zzjm.zzig()) ? zzjmVar3 : zzjm.a(zzjmVar3, zzjmVar4);
    }

    @Override // com.google.android.gms.internal.vision.g4
    public final void m(Object obj) {
        ((zzgs) obj).zzwj.zzdp();
    }

    @Override // com.google.android.gms.internal.vision.g4
    public final /* synthetic */ zzjm n() {
        return zzjm.f();
    }

    @Override // com.google.android.gms.internal.vision.g4
    public final /* synthetic */ zzjm o(zzjm zzjmVar) {
        zzjm zzjmVar2 = zzjmVar;
        zzjmVar2.zzdp();
        return zzjmVar2;
    }

    @Override // com.google.android.gms.internal.vision.g4
    public final /* synthetic */ int p(zzjm zzjmVar) {
        return zzjmVar.zzgf();
    }

    @Override // com.google.android.gms.internal.vision.g4
    public final /* synthetic */ zzjm q(Object obj) {
        return ((zzgs) obj).zzwj;
    }

    @Override // com.google.android.gms.internal.vision.g4
    public final /* synthetic */ zzjm r(Object obj) {
        zzjm zzjmVar = ((zzgs) obj).zzwj;
        if (zzjmVar == zzjm.zzig()) {
            zzjm f = zzjm.f();
            t(obj, f);
            return f;
        }
        return zzjmVar;
    }

    @Override // com.google.android.gms.internal.vision.g4
    public final /* synthetic */ int s(zzjm zzjmVar) {
        return zzjmVar.zzii();
    }
}
