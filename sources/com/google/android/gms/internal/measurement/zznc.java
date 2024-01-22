package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class zznc implements zznb {
    public static final zzhu<Boolean> zza;
    public static final zzhu<Boolean> zzb;

    static {
        zzhr zza2 = new zzhr(zzhk.zza("com.google.android.gms.measurement")).zza();
        zza = zza2.zze("measurement.adid_zero.service", false);
        zzb = zza2.zze("measurement.adid_zero.remove_lair_if_adidzero_false", true);
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final boolean zzb() {
        return zza.zzb().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznb
    public final boolean zzc() {
        return zzb.zzb().booleanValue();
    }
}
