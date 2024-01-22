package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class zzom implements zzol {
    public static final zzhu<Boolean> zza;
    public static final zzhu<Boolean> zzb;
    public static final zzhu<Long> zzc;

    static {
        zzhr zzhrVar = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhrVar.zze("measurement.collection.efficient_engagement_reporting_enabled_2", true);
        zzb = zzhrVar.zze("measurement.collection.redundant_engagement_removal_enabled", false);
        zzc = zzhrVar.zzc("measurement.id.collection.redundant_engagement_removal_enabled", 0L);
    }

    @Override // com.google.android.gms.internal.measurement.zzol
    public final boolean zza() {
        return zzb.zzb().booleanValue();
    }
}
