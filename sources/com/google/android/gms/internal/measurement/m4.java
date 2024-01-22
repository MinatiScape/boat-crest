package com.google.android.gms.internal.measurement;

import java.io.IOException;
/* loaded from: classes8.dex */
public final class m4 extends l4<zzmj, zzmj> {
    @Override // com.google.android.gms.internal.measurement.l4
    public final /* bridge */ /* synthetic */ int a(zzmj zzmjVar) {
        return zzmjVar.zza();
    }

    @Override // com.google.android.gms.internal.measurement.l4
    public final /* bridge */ /* synthetic */ int b(zzmj zzmjVar) {
        return zzmjVar.zzb();
    }

    @Override // com.google.android.gms.internal.measurement.l4
    public final /* bridge */ /* synthetic */ zzmj c(Object obj) {
        return ((zzjz) obj).zzc;
    }

    @Override // com.google.android.gms.internal.measurement.l4
    public final /* bridge */ /* synthetic */ zzmj d(zzmj zzmjVar, zzmj zzmjVar2) {
        zzmj zzmjVar3 = zzmjVar2;
        return zzmjVar3.equals(zzmj.zzc()) ? zzmjVar : zzmj.a(zzmjVar, zzmjVar3);
    }

    @Override // com.google.android.gms.internal.measurement.l4
    public final /* bridge */ /* synthetic */ zzmj e() {
        return zzmj.b();
    }

    @Override // com.google.android.gms.internal.measurement.l4
    public final /* bridge */ /* synthetic */ void f(zzmj zzmjVar, int i, long j) {
        zzmjVar.d(i << 3, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.measurement.l4
    public final void g(Object obj) {
        ((zzjz) obj).zzc.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.l4
    public final /* bridge */ /* synthetic */ void h(Object obj, zzmj zzmjVar) {
        ((zzjz) obj).zzc = zzmjVar;
    }

    @Override // com.google.android.gms.internal.measurement.l4
    public final /* bridge */ /* synthetic */ void i(zzmj zzmjVar, s2 s2Var) throws IOException {
        zzmjVar.zzi(s2Var);
    }
}
