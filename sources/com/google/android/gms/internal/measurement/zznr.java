package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class zznr implements zznq {
    public static final zzhu<Boolean> zza;
    public static final zzhu<Boolean> zzb;
    public static final zzhu<Boolean> zzc;
    public static final zzhu<Long> zzd;
    public static final zzhu<Long> zze;

    static {
        zzhr zzhrVar = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhrVar.zze("measurement.client.consent_state_v1", true);
        zzb = zzhrVar.zze("measurement.client.3p_consent_state_v1", true);
        zzc = zzhrVar.zze("measurement.service.consent_state_v1_W36", true);
        zzd = zzhrVar.zzc("measurement.id.service.consent_state_v1_W36", 0L);
        zze = zzhrVar.zzc("measurement.service.storage_consent_support_version", 203600L);
    }

    @Override // com.google.android.gms.internal.measurement.zznq
    public final long zza() {
        return zze.zzb().longValue();
    }
}
