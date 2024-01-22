package com.google.android.recaptcha.internal;

import java.io.IOException;
/* loaded from: classes10.dex */
final class zzjy extends zzjw {
    @Override // com.google.android.recaptcha.internal.zzjw
    public final /* synthetic */ int zza(Object obj) {
        return ((zzjx) obj).zza();
    }

    @Override // com.google.android.recaptcha.internal.zzjw
    public final /* synthetic */ int zzb(Object obj) {
        return ((zzjx) obj).zzb();
    }

    @Override // com.google.android.recaptcha.internal.zzjw
    public final /* bridge */ /* synthetic */ Object zzc(Object obj) {
        zzhf zzhfVar = (zzhf) obj;
        zzjx zzjxVar = zzhfVar.zzc;
        if (zzjxVar == zzjx.zzc()) {
            zzjx zzf = zzjx.zzf();
            zzhfVar.zzc = zzf;
            return zzf;
        }
        return zzjxVar;
    }

    @Override // com.google.android.recaptcha.internal.zzjw
    public final /* synthetic */ Object zzd(Object obj) {
        return ((zzhf) obj).zzc;
    }

    @Override // com.google.android.recaptcha.internal.zzjw
    public final /* bridge */ /* synthetic */ Object zze(Object obj, Object obj2) {
        if (zzjx.zzc().equals(obj2)) {
            return obj;
        }
        if (zzjx.zzc().equals(obj)) {
            return zzjx.zze((zzjx) obj, (zzjx) obj2);
        }
        ((zzjx) obj).zzd((zzjx) obj2);
        return obj;
    }

    @Override // com.google.android.recaptcha.internal.zzjw
    public final /* synthetic */ Object zzf() {
        return zzjx.zzf();
    }

    @Override // com.google.android.recaptcha.internal.zzjw
    public final /* synthetic */ Object zzg(Object obj) {
        ((zzjx) obj).zzh();
        return obj;
    }

    @Override // com.google.android.recaptcha.internal.zzjw
    public final /* bridge */ /* synthetic */ void zzh(Object obj, int i, int i2) {
        ((zzjx) obj).zzj((i << 3) | 5, Integer.valueOf(i2));
    }

    @Override // com.google.android.recaptcha.internal.zzjw
    public final /* bridge */ /* synthetic */ void zzi(Object obj, int i, long j) {
        ((zzjx) obj).zzj((i << 3) | 1, Long.valueOf(j));
    }

    @Override // com.google.android.recaptcha.internal.zzjw
    public final /* bridge */ /* synthetic */ void zzj(Object obj, int i, Object obj2) {
        ((zzjx) obj).zzj((i << 3) | 3, obj2);
    }

    @Override // com.google.android.recaptcha.internal.zzjw
    public final /* bridge */ /* synthetic */ void zzk(Object obj, int i, zzfi zzfiVar) {
        ((zzjx) obj).zzj((i << 3) | 2, zzfiVar);
    }

    @Override // com.google.android.recaptcha.internal.zzjw
    public final /* bridge */ /* synthetic */ void zzl(Object obj, int i, long j) {
        ((zzjx) obj).zzj(i << 3, Long.valueOf(j));
    }

    @Override // com.google.android.recaptcha.internal.zzjw
    public final void zzm(Object obj) {
        ((zzhf) obj).zzc.zzh();
    }

    @Override // com.google.android.recaptcha.internal.zzjw
    public final /* synthetic */ void zzn(Object obj, Object obj2) {
        ((zzhf) obj).zzc = (zzjx) obj2;
    }

    @Override // com.google.android.recaptcha.internal.zzjw
    public final /* synthetic */ void zzo(Object obj, Object obj2) {
        ((zzhf) obj).zzc = (zzjx) obj2;
    }

    @Override // com.google.android.recaptcha.internal.zzjw
    public final /* synthetic */ void zzp(Object obj, zzko zzkoVar) throws IOException {
        ((zzjx) obj).zzk(zzkoVar);
    }

    @Override // com.google.android.recaptcha.internal.zzjw
    public final /* synthetic */ void zzq(Object obj, zzko zzkoVar) throws IOException {
        ((zzjx) obj).zzl(zzkoVar);
    }

    @Override // com.google.android.recaptcha.internal.zzjw
    public final boolean zzs(zzjb zzjbVar) {
        return false;
    }
}
