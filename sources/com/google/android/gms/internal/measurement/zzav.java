package com.google.android.gms.internal.measurement;

import java.util.List;
/* loaded from: classes8.dex */
public final class zzav extends zzaw {
    public zzav() {
        this.f8941a.add(zzbl.BITWISE_AND);
        this.f8941a.add(zzbl.BITWISE_LEFT_SHIFT);
        this.f8941a.add(zzbl.BITWISE_NOT);
        this.f8941a.add(zzbl.BITWISE_OR);
        this.f8941a.add(zzbl.BITWISE_RIGHT_SHIFT);
        this.f8941a.add(zzbl.BITWISE_UNSIGNED_RIGHT_SHIFT);
        this.f8941a.add(zzbl.BITWISE_XOR);
    }

    @Override // com.google.android.gms.internal.measurement.zzaw
    public final zzap zza(String str, zzg zzgVar, List<zzap> list) {
        zzbl zzblVar = zzbl.ADD;
        switch (zzh.zze(str).ordinal()) {
            case 4:
                zzh.zzh(zzbl.BITWISE_AND.name(), 2, list);
                return new zzah(Double.valueOf(zzh.zzb(zzgVar.zzb(list.get(0)).zzh().doubleValue()) & zzh.zzb(zzgVar.zzb(list.get(1)).zzh().doubleValue())));
            case 5:
                zzh.zzh(zzbl.BITWISE_LEFT_SHIFT.name(), 2, list);
                return new zzah(Double.valueOf(zzh.zzb(zzgVar.zzb(list.get(0)).zzh().doubleValue()) << ((int) (zzh.zzd(zzgVar.zzb(list.get(1)).zzh().doubleValue()) & 31))));
            case 6:
                zzh.zzh(zzbl.BITWISE_NOT.name(), 1, list);
                return new zzah(Double.valueOf(~zzh.zzb(zzgVar.zzb(list.get(0)).zzh().doubleValue())));
            case 7:
                zzh.zzh(zzbl.BITWISE_OR.name(), 2, list);
                return new zzah(Double.valueOf(zzh.zzb(zzgVar.zzb(list.get(0)).zzh().doubleValue()) | zzh.zzb(zzgVar.zzb(list.get(1)).zzh().doubleValue())));
            case 8:
                zzh.zzh(zzbl.BITWISE_RIGHT_SHIFT.name(), 2, list);
                return new zzah(Double.valueOf(zzh.zzb(zzgVar.zzb(list.get(0)).zzh().doubleValue()) >> ((int) (zzh.zzd(zzgVar.zzb(list.get(1)).zzh().doubleValue()) & 31))));
            case 9:
                zzh.zzh(zzbl.BITWISE_UNSIGNED_RIGHT_SHIFT.name(), 2, list);
                return new zzah(Double.valueOf(zzh.zzd(zzgVar.zzb(list.get(0)).zzh().doubleValue()) >>> ((int) (zzh.zzd(zzgVar.zzb(list.get(1)).zzh().doubleValue()) & 31))));
            case 10:
                zzh.zzh(zzbl.BITWISE_XOR.name(), 2, list);
                return new zzah(Double.valueOf(zzh.zzb(zzgVar.zzb(list.get(0)).zzh().doubleValue()) ^ zzh.zzb(zzgVar.zzb(list.get(1)).zzh().doubleValue())));
            default:
                return super.a(str);
        }
    }
}
