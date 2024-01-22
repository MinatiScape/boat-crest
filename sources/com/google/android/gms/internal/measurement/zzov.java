package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class zzov implements zzou {
    public static final zzhu<Boolean> zza;
    public static final zzhu<Boolean> zzb;
    public static final zzhu<Boolean> zzc;
    public static final zzhu<Long> zzd;

    static {
        zzhr zzhrVar = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhrVar.zze("measurement.sdk.collection.enable_extend_user_property_size", true);
        zzb = zzhrVar.zze("measurement.sdk.collection.last_deep_link_referrer2", true);
        zzc = zzhrVar.zze("measurement.sdk.collection.last_deep_link_referrer_campaign2", false);
        zzd = zzhrVar.zzc("measurement.id.sdk.collection.last_deep_link_referrer2", 0L);
    }

    @Override // com.google.android.gms.internal.measurement.zzou
    public final boolean zza() {
        return zza.zzb().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzou
    public final boolean zzb() {
        return zzc.zzb().booleanValue();
    }
}
