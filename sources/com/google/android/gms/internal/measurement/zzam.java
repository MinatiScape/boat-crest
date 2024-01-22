package com.google.android.gms.internal.measurement;

import com.clevertap.android.sdk.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes8.dex */
public class zzam implements zzap, zzal {
    public final Map<String, zzap> h = new HashMap();

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzam) {
            return this.h.equals(((zzam) obj).h);
        }
        return false;
    }

    public final int hashCode() {
        return this.h.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("{");
        if (!this.h.isEmpty()) {
            for (String str : this.h.keySet()) {
                sb.append(String.format("%s: %s,", str, this.h.get(str)));
            }
            sb.deleteCharAt(sb.lastIndexOf(Constants.SEPARATOR_COMMA));
        }
        sb.append("}");
        return sb.toString();
    }

    public final List<String> zzb() {
        return new ArrayList(this.h.keySet());
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public zzap zzbK(String str, zzg zzgVar, List<zzap> list) {
        if ("toString".equals(str)) {
            return new zzat(toString());
        }
        return zzaj.zza(this, new zzat(str), zzgVar, list);
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        zzam zzamVar = new zzam();
        for (Map.Entry<String, zzap> entry : this.h.entrySet()) {
            if (entry.getValue() instanceof zzal) {
                zzamVar.h.put(entry.getKey(), entry.getValue());
            } else {
                zzamVar.h.put(entry.getKey(), entry.getValue().zzd());
            }
        }
        return zzamVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzal
    public final zzap zzf(String str) {
        return this.h.containsKey(str) ? this.h.get(str) : zzap.zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Boolean zzg() {
        return Boolean.TRUE;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Double zzh() {
        return Double.valueOf(Double.NaN);
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final String zzi() {
        return "[object Object]";
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Iterator<zzap> zzl() {
        return zzaj.zzb(this.h);
    }

    @Override // com.google.android.gms.internal.measurement.zzal
    public final void zzr(String str, zzap zzapVar) {
        if (zzapVar == null) {
            this.h.remove(str);
        } else {
            this.h.put(str, zzapVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzal
    public final boolean zzt(String str) {
        return this.h.containsKey(str);
    }
}
