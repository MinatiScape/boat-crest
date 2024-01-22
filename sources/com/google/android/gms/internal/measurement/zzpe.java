package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class zzpe implements zzpd {
    public static final zzhu<Boolean> zza;
    public static final zzhu<Long> zzb;

    static {
        zzhr zzhrVar = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhrVar.zze("measurement.validation.internal_limits_internal_event_params", false);
        zzb = zzhrVar.zzc("measurement.id.validation.internal_limits_internal_event_params", 0L);
    }

    @Override // com.google.android.gms.internal.measurement.zzpd
    public final boolean zza() {
        return zza.zzb().booleanValue();
    }
}
