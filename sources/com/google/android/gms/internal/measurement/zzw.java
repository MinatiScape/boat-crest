package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes8.dex */
public final class zzw extends zzai {
    public final zzj h;
    public final Map<String, zzai> i;

    public zzw(zzj zzjVar) {
        super("require");
        this.i = new HashMap();
        this.h = zzjVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzgVar, List<zzap> list) {
        zzai zzaiVar;
        zzh.zzh("require", 1, list);
        String zzi = zzgVar.zzb(list.get(0)).zzi();
        if (this.i.containsKey(zzi)) {
            return this.i.get(zzi);
        }
        zzj zzjVar = this.h;
        if (zzjVar.f8960a.containsKey(zzi)) {
            try {
                zzaiVar = zzjVar.f8960a.get(zzi).call();
            } catch (Exception unused) {
                String valueOf = String.valueOf(zzi);
                throw new IllegalStateException(valueOf.length() != 0 ? "Failed to create API implementation: ".concat(valueOf) : new String("Failed to create API implementation: "));
            }
        } else {
            zzaiVar = zzap.zzf;
        }
        if (zzaiVar instanceof zzai) {
            this.i.put(zzi, (zzai) zzaiVar);
        }
        return zzaiVar;
    }
}
