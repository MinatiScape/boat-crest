package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.Map;
/* loaded from: classes8.dex */
public final class zzl extends zzam {
    public final zzab i;

    public zzl(zzab zzabVar) {
        this.i = zzabVar;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.gms.internal.measurement.zzam, com.google.android.gms.internal.measurement.zzap
    public final zzap zzbK(String str, zzg zzgVar, List<zzap> list) {
        char c;
        switch (str.hashCode()) {
            case 21624207:
                if (str.equals("getEventName")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 45521504:
                if (str.equals("getTimestamp")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 146575578:
                if (str.equals("getParamValue")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 700587132:
                if (str.equals("getParams")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 920706790:
                if (str.equals("setParamValue")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1570616835:
                if (str.equals("setEventName")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            zzh.zzh("getEventName", 0, list);
            return new zzat(this.i.zzb().zzd());
        } else if (c == 1) {
            zzh.zzh("getParamValue", 1, list);
            return zzi.zzb(this.i.zzb().zzc(zzgVar.zzb(list.get(0)).zzi()));
        } else if (c == 2) {
            zzh.zzh("getParams", 0, list);
            Map<String, Object> zze = this.i.zzb().zze();
            zzam zzamVar = new zzam();
            for (String str2 : zze.keySet()) {
                zzamVar.zzr(str2, zzi.zzb(zze.get(str2)));
            }
            return zzamVar;
        } else if (c == 3) {
            zzh.zzh("getTimestamp", 0, list);
            return new zzah(Double.valueOf(this.i.zzb().zza()));
        } else if (c != 4) {
            if (c != 5) {
                return super.zzbK(str, zzgVar, list);
            }
            zzh.zzh("setParamValue", 2, list);
            String zzi = zzgVar.zzb(list.get(0)).zzi();
            zzap zzb = zzgVar.zzb(list.get(1));
            this.i.zzb().zzg(zzi, zzh.zzf(zzb));
            return zzb;
        } else {
            zzh.zzh("setEventName", 1, list);
            zzap zzb2 = zzgVar.zzb(list.get(0));
            if (!zzap.zzf.equals(zzb2) && !zzap.zzg.equals(zzb2)) {
                this.i.zzb().zzf(zzb2.zzi());
                return new zzat(zzb2.zzi());
            }
            throw new IllegalArgumentException("Illegal event name");
        }
    }
}
