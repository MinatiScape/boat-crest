package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes8.dex */
public final /* synthetic */ class zzaj {
    public static zzap zza(zzal zzalVar, zzap zzapVar, zzg zzgVar, List<zzap> list) {
        if (zzalVar.zzt(zzapVar.zzi())) {
            zzap zzf = zzalVar.zzf(zzapVar.zzi());
            if (zzf instanceof zzai) {
                return ((zzai) zzf).zza(zzgVar, list);
            }
            throw new IllegalArgumentException(String.format("%s is not a function", zzapVar.zzi()));
        } else if ("hasOwnProperty".equals(zzapVar.zzi())) {
            zzh.zzh("hasOwnProperty", 1, list);
            return zzalVar.zzt(zzgVar.zzb(list.get(0)).zzi()) ? zzap.zzk : zzap.zzl;
        } else {
            throw new IllegalArgumentException(String.format("Object has no function %s", zzapVar.zzi()));
        }
    }

    public static Iterator<zzap> zzb(Map<String, zzap> map) {
        return new c(map.keySet().iterator());
    }
}
