package com.google.android.gms.internal.measurement;

import java.util.List;
/* loaded from: classes8.dex */
public final class zzn extends zzai {
    public final zzo h;

    public zzn(String str, zzo zzoVar) {
        super("internal.remoteConfig");
        this.h = zzoVar;
        this.zze.put("getValue", new f4(this, "getValue", zzoVar));
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzgVar, List<zzap> list) {
        return zzap.zzf;
    }
}
