package com.google.android.libraries.places.internal;
/* loaded from: classes10.dex */
public final class zzf {
    private static zzf zza;
    private final zze zzb;

    private zzf(zze zzeVar) {
        this.zzb = zzeVar;
    }

    public static zzf zza() {
        zzf zzfVar = zza;
        if (!((zzfVar == null || (zzfVar.zzb instanceof zzc)) ? false : true)) {
            zza = new zzf(new zzc());
        }
        return zza;
    }

    public final zzg zzb() {
        return this.zzb.zza();
    }

    public final void zzb(zzh zzhVar) {
        this.zzb.zzb(zzhVar);
    }

    public final void zza(zzg zzgVar, zzh zzhVar) {
        this.zzb.zza(zzgVar, zzhVar);
    }

    public final void zza(zzh zzhVar) {
        this.zzb.zza(zzhVar);
    }
}
