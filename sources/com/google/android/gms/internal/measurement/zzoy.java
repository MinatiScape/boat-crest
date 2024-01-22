package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class zzoy implements zzox {
    public static final zzhu<Long> zza;
    public static final zzhu<Boolean> zzb;
    public static final zzhu<Boolean> zzc;
    public static final zzhu<Boolean> zzd;
    public static final zzhu<Long> zze;

    static {
        zzhr zzhrVar = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhrVar.zzc("measurement.id.lifecycle.app_in_background_parameter", 0L);
        zzb = zzhrVar.zze("measurement.lifecycle.app_backgrounded_engagement", false);
        zzc = zzhrVar.zze("measurement.lifecycle.app_backgrounded_tracking", true);
        zzd = zzhrVar.zze("measurement.lifecycle.app_in_background_parameter", false);
        zze = zzhrVar.zzc("measurement.id.lifecycle.app_backgrounded_tracking", 0L);
    }

    @Override // com.google.android.gms.internal.measurement.zzox
    public final boolean zza() {
        return zzb.zzb().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzox
    public final boolean zzb() {
        return zzd.zzb().booleanValue();
    }
}
