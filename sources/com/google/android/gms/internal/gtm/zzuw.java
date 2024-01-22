package com.google.android.gms.internal.gtm;
/* loaded from: classes8.dex */
public final class zzuw implements zzun<zzuw> {
    public final zzvc<?> zza;
    public final int zzb;
    public final zzye zzc;
    public final boolean zzd = false;

    public zzuw(zzvc<?> zzvcVar, int i, zzye zzyeVar, boolean z, boolean z2) {
        this.zza = zzvcVar;
        this.zzb = i;
        this.zzc = zzyeVar;
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return this.zzb - ((zzuw) obj).zzb;
    }

    @Override // com.google.android.gms.internal.gtm.zzun
    public final int zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.gtm.zzun
    public final zzwj zzb(zzwj zzwjVar, zzwk zzwkVar) {
        ((zzut) zzwjVar).zzz((zzuz) zzwkVar);
        return zzwjVar;
    }

    @Override // com.google.android.gms.internal.gtm.zzun
    public final zzwp zzc(zzwp zzwpVar, zzwp zzwpVar2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.gtm.zzun
    public final zzye zzd() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.gtm.zzun
    public final zzyf zze() {
        return this.zzc.zza();
    }

    @Override // com.google.android.gms.internal.gtm.zzun
    public final boolean zzf() {
        return false;
    }

    @Override // com.google.android.gms.internal.gtm.zzun
    public final boolean zzg() {
        return false;
    }
}
