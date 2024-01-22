package com.google.android.gms.internal.gtm;

import com.google.android.gms.common.util.VisibleForTesting;
@VisibleForTesting
/* loaded from: classes8.dex */
public final class zzcx extends zzbs {
    @VisibleForTesting
    public zzcx(zzbv zzbvVar) {
        super(zzbvVar);
    }

    public final zzba zza() {
        zzW();
        return zzq().zzd();
    }

    public final String zzb() {
        zzW();
        zzba zza = zza();
        int i = zza.zza;
        int i2 = zza.zzb;
        StringBuilder sb = new StringBuilder(23);
        sb.append(i);
        sb.append("x");
        sb.append(i2);
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.gtm.zzbs
    public final void zzd() {
    }
}
