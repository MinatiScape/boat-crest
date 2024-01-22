package com.google.android.gms.internal.measurement;

import java.util.List;
/* loaded from: classes8.dex */
public final class zzv extends zzai {
    public final zzz h;

    public zzv(zzz zzzVar) {
        super("internal.registerCallback");
        this.h = zzzVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzgVar, List<zzap> list) {
        zzh.zzh(this.zzd, 3, list);
        String zzi = zzgVar.zzb(list.get(0)).zzi();
        zzap zzb = zzgVar.zzb(list.get(1));
        if (zzb instanceof zzao) {
            zzap zzb2 = zzgVar.zzb(list.get(2));
            if (zzb2 instanceof zzam) {
                zzam zzamVar = (zzam) zzb2;
                if (zzamVar.zzt("type")) {
                    this.h.zza(zzi, zzamVar.zzt("priority") ? zzh.zzb(zzamVar.zzf("priority").zzh().doubleValue()) : 1000, (zzao) zzb, zzamVar.zzf("type").zzi());
                    return zzap.zzf;
                }
                throw new IllegalArgumentException("Undefined rule type");
            }
            throw new IllegalArgumentException("Invalid callback params");
        }
        throw new IllegalArgumentException("Invalid callback type");
    }
}
