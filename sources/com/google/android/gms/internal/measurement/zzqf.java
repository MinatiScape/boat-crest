package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class zzqf implements zzqe {
    public static final zzhu<Boolean> zza = new zzhr(zzhk.zza("com.google.android.gms.measurement")).zze("measurement.integration.disable_firebase_instance_id", false);

    @Override // com.google.android.gms.internal.measurement.zzqe
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzqe
    public final boolean zzb() {
        return zza.zzb().booleanValue();
    }
}
