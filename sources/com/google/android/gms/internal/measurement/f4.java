package com.google.android.gms.internal.measurement;

import java.util.List;
/* loaded from: classes8.dex */
public final class f4 extends zzai {
    public final /* synthetic */ zzo h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f4(zzn zznVar, String str, zzo zzoVar) {
        super("getValue");
        this.h = zzoVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzgVar, List<zzap> list) {
        zzh.zzh("getValue", 2, list);
        zzap zzb = zzgVar.zzb(list.get(0));
        zzap zzb2 = zzgVar.zzb(list.get(1));
        String zza = this.h.zza(zzb.zzi());
        return zza != null ? new zzat(zza) : zzb2;
    }
}
