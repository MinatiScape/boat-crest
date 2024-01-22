package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class zzpn implements zzpm {
    public static final zzhu<Boolean> zza;
    public static final zzhu<Long> zzb;

    static {
        zzhr zzhrVar = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhrVar.zze("measurement.module.pixie.ees", false);
        zzb = zzhrVar.zzc("measurement.id.module.pixie.ees", 0L);
    }

    @Override // com.google.android.gms.internal.measurement.zzpm
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzpm
    public final boolean zzb() {
        return zza.zzb().booleanValue();
    }
}
