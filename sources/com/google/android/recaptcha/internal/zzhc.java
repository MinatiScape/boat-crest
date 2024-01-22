package com.google.android.recaptcha.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzhc implements zzgu {
    public final int zza;
    public final zzkm zzb;

    public zzhc(zzhi zzhiVar, int i, zzkm zzkmVar, boolean z, boolean z2) {
        this.zza = i;
        this.zzb = zzkmVar;
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object obj) {
        return this.zza - ((zzhc) obj).zza;
    }

    @Override // com.google.android.recaptcha.internal.zzgu
    public final int zza() {
        return this.zza;
    }

    @Override // com.google.android.recaptcha.internal.zzgu
    public final zzio zzb(zzio zzioVar, zzip zzipVar) {
        ((zzgz) zzioVar).zzg((zzhf) zzipVar);
        return zzioVar;
    }

    @Override // com.google.android.recaptcha.internal.zzgu
    public final zziu zzc(zziu zziuVar, zziu zziuVar2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.recaptcha.internal.zzgu
    public final zzkm zzd() {
        return this.zzb;
    }

    @Override // com.google.android.recaptcha.internal.zzgu
    public final zzkn zze() {
        return this.zzb.zza();
    }

    @Override // com.google.android.recaptcha.internal.zzgu
    public final boolean zzf() {
        return false;
    }

    @Override // com.google.android.recaptcha.internal.zzgu
    public final boolean zzg() {
        return false;
    }
}
