package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class zznf implements zzne {
    public static final zzhu<Boolean> zza;
    public static final zzhu<Boolean> zzb;

    static {
        zzhr zzhrVar = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhrVar.zze("measurement.androidId.delete_feature", true);
        zzb = zzhrVar.zze("measurement.log_androidId_enabled", false);
    }

    @Override // com.google.android.gms.internal.measurement.zzne
    public final boolean zza() {
        return zza.zzb().booleanValue();
    }
}
