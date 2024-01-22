package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.gtm.zzro;
import com.google.android.gms.internal.gtm.zzrw;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes10.dex */
public final class zzep implements zzer {
    public final /* synthetic */ Map zza;
    public final /* synthetic */ Map zzb;
    public final /* synthetic */ Map zzc;
    public final /* synthetic */ Map zzd;

    public zzep(zzeu zzeuVar, Map map, Map map2, Map map3, Map map4) {
        this.zza = map;
        this.zzb = map2;
        this.zzc = map3;
        this.zzd = map4;
    }

    @Override // com.google.android.gms.tagmanager.zzer
    public final void zza(zzrw zzrwVar, Set<zzro> set, Set<zzro> set2, zzdo zzdoVar) {
        List list = (List) this.zza.get(zzrwVar);
        List list2 = (List) this.zzb.get(zzrwVar);
        if (list != null) {
            set.addAll(list);
        }
        List list3 = (List) this.zzc.get(zzrwVar);
        List list4 = (List) this.zzd.get(zzrwVar);
        if (list3 != null) {
            set2.addAll(list3);
        }
    }
}
