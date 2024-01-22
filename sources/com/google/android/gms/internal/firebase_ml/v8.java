package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
/* loaded from: classes7.dex */
public final class v8 extends u8<zzzz, zzzz> {
    public static void m(Object obj, zzzz zzzzVar) {
        ((zzwz) obj).zzclj = zzzzVar;
    }

    @Override // com.google.android.gms.internal.firebase_ml.u8
    public final /* synthetic */ void a(zzzz zzzzVar, int i, long j) {
        zzzzVar.c(i << 3, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.firebase_ml.u8
    public final /* synthetic */ void b(zzzz zzzzVar, int i, zzvv zzvvVar) {
        zzzzVar.c((i << 3) | 2, zzvvVar);
    }

    @Override // com.google.android.gms.internal.firebase_ml.u8
    public final /* synthetic */ void c(zzzz zzzzVar, p pVar) throws IOException {
        zzzzVar.zzb(pVar);
    }

    @Override // com.google.android.gms.internal.firebase_ml.u8
    public final /* synthetic */ int d(zzzz zzzzVar) {
        return zzzzVar.zzuo();
    }

    @Override // com.google.android.gms.internal.firebase_ml.u8
    public final /* synthetic */ zzzz e(Object obj) {
        return ((zzwz) obj).zzclj;
    }

    @Override // com.google.android.gms.internal.firebase_ml.u8
    public final /* synthetic */ int f(zzzz zzzzVar) {
        return zzzzVar.zzxb();
    }

    @Override // com.google.android.gms.internal.firebase_ml.u8
    public final /* synthetic */ void g(zzzz zzzzVar, p pVar) throws IOException {
        zzzzVar.b(pVar);
    }

    @Override // com.google.android.gms.internal.firebase_ml.u8
    public final /* synthetic */ void h(Object obj, zzzz zzzzVar) {
        m(obj, zzzzVar);
    }

    @Override // com.google.android.gms.internal.firebase_ml.u8
    public final /* synthetic */ void i(Object obj, zzzz zzzzVar) {
        m(obj, zzzzVar);
    }

    @Override // com.google.android.gms.internal.firebase_ml.u8
    public final /* synthetic */ zzzz j(zzzz zzzzVar, zzzz zzzzVar2) {
        zzzz zzzzVar3 = zzzzVar;
        zzzz zzzzVar4 = zzzzVar2;
        return zzzzVar4.equals(zzzz.zzwz()) ? zzzzVar3 : zzzz.a(zzzzVar3, zzzzVar4);
    }

    @Override // com.google.android.gms.internal.firebase_ml.u8
    public final void k(Object obj) {
        ((zzwz) obj).zzclj.zztm();
    }

    @Override // com.google.android.gms.internal.firebase_ml.u8
    public final /* synthetic */ zzzz l() {
        return zzzz.f();
    }
}
