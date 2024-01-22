package com.google.android.gms.internal.measurement;

import com.clevertap.android.sdk.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
/* loaded from: classes8.dex */
public final class zzae implements Iterable<zzap>, zzap, zzal {
    public final SortedMap<Integer, zzap> h;
    public final Map<String, zzap> i;

    public zzae() {
        this.h = new TreeMap();
        this.i = new TreeMap();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzae) {
            zzae zzaeVar = (zzae) obj;
            if (zzc() != zzaeVar.zzc()) {
                return false;
            }
            if (this.h.isEmpty()) {
                return zzaeVar.h.isEmpty();
            }
            for (int intValue = this.h.firstKey().intValue(); intValue <= this.h.lastKey().intValue(); intValue++) {
                if (!zze(intValue).equals(zzaeVar.zze(intValue))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.h.hashCode() * 31;
    }

    @Override // java.lang.Iterable
    public final Iterator<zzap> iterator() {
        return new b(this);
    }

    public final String toString() {
        return zzj(Constants.SEPARATOR_COMMA);
    }

    public final int zzb() {
        return this.h.size();
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzbK(String str, zzg zzgVar, List<zzap> list) {
        if (!"concat".equals(str) && !"every".equals(str) && !"filter".equals(str) && !"forEach".equals(str) && !"indexOf".equals(str) && !"join".equals(str) && !"lastIndexOf".equals(str) && !"map".equals(str) && !"pop".equals(str) && !"push".equals(str) && !"reduce".equals(str) && !"reduceRight".equals(str) && !"reverse".equals(str) && !"shift".equals(str) && !"slice".equals(str) && !"some".equals(str) && !"sort".equals(str) && !"splice".equals(str) && !"toString".equals(str) && !"unshift".equals(str)) {
            return zzaj.zza(this, new zzat(str), zzgVar, list);
        }
        return zzbb.zza(str, this, zzgVar, list);
    }

    public final int zzc() {
        if (this.h.isEmpty()) {
            return 0;
        }
        return this.h.lastKey().intValue() + 1;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        zzae zzaeVar = new zzae();
        for (Map.Entry<Integer, zzap> entry : this.h.entrySet()) {
            if (entry.getValue() instanceof zzal) {
                zzaeVar.h.put(entry.getKey(), entry.getValue());
            } else {
                zzaeVar.h.put(entry.getKey(), entry.getValue().zzd());
            }
        }
        return zzaeVar;
    }

    public final zzap zze(int i) {
        zzap zzapVar;
        if (i < zzc()) {
            return (!zzs(i) || (zzapVar = this.h.get(Integer.valueOf(i))) == null) ? zzap.zzf : zzapVar;
        }
        throw new IndexOutOfBoundsException("Attempting to get element outside of current array");
    }

    @Override // com.google.android.gms.internal.measurement.zzal
    public final zzap zzf(String str) {
        zzap zzapVar;
        if ("length".equals(str)) {
            return new zzah(Double.valueOf(zzc()));
        }
        return (!zzt(str) || (zzapVar = this.i.get(str)) == null) ? zzap.zzf : zzapVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Boolean zzg() {
        return Boolean.TRUE;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Double zzh() {
        if (this.h.size() == 1) {
            return zze(0).zzh();
        }
        if (this.h.size() <= 0) {
            return Double.valueOf(0.0d);
        }
        return Double.valueOf(Double.NaN);
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final String zzi() {
        return zzj(Constants.SEPARATOR_COMMA);
    }

    public final String zzj(String str) {
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder();
        if (!this.h.isEmpty()) {
            for (int i = 0; i < zzc(); i++) {
                zzap zze = zze(i);
                sb.append(str);
                if (!(zze instanceof zzau) && !(zze instanceof zzan)) {
                    sb.append(zze.zzi());
                }
            }
            sb.delete(0, str.length());
        }
        return sb.toString();
    }

    public final Iterator<Integer> zzk() {
        return this.h.keySet().iterator();
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Iterator<zzap> zzl() {
        return new a(this, this.h.keySet().iterator(), this.i.keySet().iterator());
    }

    public final List<zzap> zzm() {
        ArrayList arrayList = new ArrayList(zzc());
        for (int i = 0; i < zzc(); i++) {
            arrayList.add(zze(i));
        }
        return arrayList;
    }

    public final void zzn() {
        this.h.clear();
    }

    public final void zzo(int i, zzap zzapVar) {
        if (i >= 0) {
            if (i >= zzc()) {
                zzq(i, zzapVar);
                return;
            }
            for (int intValue = this.h.lastKey().intValue(); intValue >= i; intValue--) {
                SortedMap<Integer, zzap> sortedMap = this.h;
                Integer valueOf = Integer.valueOf(intValue);
                zzap zzapVar2 = sortedMap.get(valueOf);
                if (zzapVar2 != null) {
                    zzq(intValue + 1, zzapVar2);
                    this.h.remove(valueOf);
                }
            }
            zzq(i, zzapVar);
            return;
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append("Invalid value index: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    public final void zzp(int i) {
        int intValue = this.h.lastKey().intValue();
        if (i > intValue || i < 0) {
            return;
        }
        this.h.remove(Integer.valueOf(i));
        if (i == intValue) {
            SortedMap<Integer, zzap> sortedMap = this.h;
            int i2 = i - 1;
            Integer valueOf = Integer.valueOf(i2);
            if (sortedMap.containsKey(valueOf) || i2 < 0) {
                return;
            }
            this.h.put(valueOf, zzap.zzf);
            return;
        }
        while (true) {
            i++;
            if (i > this.h.lastKey().intValue()) {
                return;
            }
            SortedMap<Integer, zzap> sortedMap2 = this.h;
            Integer valueOf2 = Integer.valueOf(i);
            zzap zzapVar = sortedMap2.get(valueOf2);
            if (zzapVar != null) {
                this.h.put(Integer.valueOf(i - 1), zzapVar);
                this.h.remove(valueOf2);
            }
        }
    }

    @RequiresNonNull({"elements"})
    public final void zzq(int i, zzap zzapVar) {
        if (i > 32468) {
            throw new IllegalStateException("Array too large");
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Out of bounds index: ");
            sb.append(i);
            throw new IndexOutOfBoundsException(sb.toString());
        } else if (zzapVar == null) {
            this.h.remove(Integer.valueOf(i));
        } else {
            this.h.put(Integer.valueOf(i), zzapVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzal
    public final void zzr(String str, zzap zzapVar) {
        if (zzapVar == null) {
            this.i.remove(str);
        } else {
            this.i.put(str, zzapVar);
        }
    }

    public final boolean zzs(int i) {
        if (i >= 0 && i <= this.h.lastKey().intValue()) {
            return this.h.containsKey(Integer.valueOf(i));
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append("Out of bounds index: ");
        sb.append(i);
        throw new IndexOutOfBoundsException(sb.toString());
    }

    @Override // com.google.android.gms.internal.measurement.zzal
    public final boolean zzt(String str) {
        return "length".equals(str) || this.i.containsKey(str);
    }

    public zzae(List<zzap> list) {
        this();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                zzq(i, list.get(i));
            }
        }
    }
}
