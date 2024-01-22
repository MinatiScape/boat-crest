package com.google.android.gms.internal.gtm;

import java.io.IOException;
/* loaded from: classes8.dex */
public final class zzxq extends zzxo<zzxp, zzxp> {
    @Override // com.google.android.gms.internal.gtm.zzxo
    public final /* bridge */ /* synthetic */ int zza(zzxp zzxpVar) {
        return zzxpVar.zza();
    }

    @Override // com.google.android.gms.internal.gtm.zzxo
    public final /* bridge */ /* synthetic */ int zzb(zzxp zzxpVar) {
        return zzxpVar.zzb();
    }

    @Override // com.google.android.gms.internal.gtm.zzxo
    public final /* bridge */ /* synthetic */ zzxp zzc(Object obj) {
        zzuz zzuzVar = (zzuz) obj;
        zzxp zzxpVar = zzuzVar.zzc;
        if (zzxpVar == zzxp.zzc()) {
            zzxp zze = zzxp.zze();
            zzuzVar.zzc = zze;
            return zze;
        }
        return zzxpVar;
    }

    @Override // com.google.android.gms.internal.gtm.zzxo
    public final /* bridge */ /* synthetic */ zzxp zzd(Object obj) {
        return ((zzuz) obj).zzc;
    }

    @Override // com.google.android.gms.internal.gtm.zzxo
    public final /* bridge */ /* synthetic */ zzxp zze(zzxp zzxpVar, zzxp zzxpVar2) {
        zzxp zzxpVar3 = zzxpVar2;
        return zzxpVar3.equals(zzxp.zzc()) ? zzxpVar : zzxp.zzd(zzxpVar, zzxpVar3);
    }

    @Override // com.google.android.gms.internal.gtm.zzxo
    public final /* bridge */ /* synthetic */ zzxp zzf() {
        return zzxp.zze();
    }

    @Override // com.google.android.gms.internal.gtm.zzxo
    public final /* bridge */ /* synthetic */ zzxp zzg(zzxp zzxpVar) {
        zzxp zzxpVar2 = zzxpVar;
        zzxpVar2.zzf();
        return zzxpVar2;
    }

    @Override // com.google.android.gms.internal.gtm.zzxo
    public final /* bridge */ /* synthetic */ void zzh(zzxp zzxpVar, int i, int i2) {
        zzxpVar.zzh((i << 3) | 5, Integer.valueOf(i2));
    }

    @Override // com.google.android.gms.internal.gtm.zzxo
    public final /* bridge */ /* synthetic */ void zzi(zzxp zzxpVar, int i, long j) {
        zzxpVar.zzh((i << 3) | 1, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.gtm.zzxo
    public final /* bridge */ /* synthetic */ void zzj(zzxp zzxpVar, int i, zzxp zzxpVar2) {
        zzxpVar.zzh((i << 3) | 3, zzxpVar2);
    }

    @Override // com.google.android.gms.internal.gtm.zzxo
    public final /* bridge */ /* synthetic */ void zzk(zzxp zzxpVar, int i, zztd zztdVar) {
        zzxpVar.zzh((i << 3) | 2, zztdVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzxo
    public final /* bridge */ /* synthetic */ void zzl(zzxp zzxpVar, int i, long j) {
        zzxpVar.zzh(i << 3, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.gtm.zzxo
    public final void zzm(Object obj) {
        ((zzuz) obj).zzc.zzf();
    }

    @Override // com.google.android.gms.internal.gtm.zzxo
    public final /* bridge */ /* synthetic */ void zzn(Object obj, zzxp zzxpVar) {
        ((zzuz) obj).zzc = zzxpVar;
    }

    @Override // com.google.android.gms.internal.gtm.zzxo
    public final /* bridge */ /* synthetic */ void zzo(Object obj, zzxp zzxpVar) {
        ((zzuz) obj).zzc = zzxpVar;
    }

    @Override // com.google.android.gms.internal.gtm.zzxo
    public final boolean zzq(zzww zzwwVar) {
        return false;
    }

    @Override // com.google.android.gms.internal.gtm.zzxo
    public final /* bridge */ /* synthetic */ void zzr(zzxp zzxpVar, zztp zztpVar) throws IOException {
        zzxpVar.zzi(zztpVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzxo
    public final /* bridge */ /* synthetic */ void zzs(zzxp zzxpVar, zztp zztpVar) throws IOException {
        zzxpVar.zzj(zztpVar);
    }
}
