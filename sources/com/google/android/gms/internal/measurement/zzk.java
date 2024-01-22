package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes8.dex */
public final class zzk extends zzai {
    public final zzab h;

    public zzk(zzab zzabVar) {
        super("internal.eventLogger");
        this.h = zzabVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzgVar, List<zzap> list) {
        Map<String, Object> hashMap;
        zzh.zzh(this.zzd, 3, list);
        String zzi = zzgVar.zzb(list.get(0)).zzi();
        long zza = (long) zzh.zza(zzgVar.zzb(list.get(1)).zzh().doubleValue());
        zzap zzb = zzgVar.zzb(list.get(2));
        if (zzb instanceof zzam) {
            hashMap = zzh.zzg((zzam) zzb);
        } else {
            hashMap = new HashMap<>();
        }
        this.h.zze(zzi, zza, hashMap);
        return zzap.zzf;
    }
}
