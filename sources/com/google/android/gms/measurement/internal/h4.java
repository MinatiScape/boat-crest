package com.google.android.gms.measurement.internal;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import com.google.android.gms.internal.measurement.zzfl;
import com.google.android.gms.internal.measurement.zzfm;
import com.google.android.gms.internal.measurement.zzgc;
import com.google.android.gms.internal.measurement.zzgd;
import com.google.android.gms.internal.measurement.zzge;
import com.google.android.gms.internal.measurement.zzgf;
import com.google.android.gms.internal.measurement.zzoe;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public final class h4 {

    /* renamed from: a  reason: collision with root package name */
    public String f10118a;
    public boolean b;
    public zzgd c;
    public BitSet d;
    public BitSet e;
    public Map<Integer, Long> f;
    public Map<Integer, List<Long>> g;
    public final /* synthetic */ m4 h;

    public /* synthetic */ h4(m4 m4Var, String str, zzs zzsVar) {
        this.h = m4Var;
        this.f10118a = str;
        this.b = true;
        this.d = new BitSet();
        this.e = new BitSet();
        this.f = new ArrayMap();
        this.g = new ArrayMap();
    }

    public static /* bridge */ /* synthetic */ BitSet b(h4 h4Var) {
        return h4Var.d;
    }

    @NonNull
    public final com.google.android.gms.internal.measurement.zzfk a(int i) {
        ArrayList arrayList;
        List list;
        com.google.android.gms.internal.measurement.zzfj zzb = com.google.android.gms.internal.measurement.zzfk.zzb();
        zzb.zza(i);
        zzb.zzc(this.b);
        zzgd zzgdVar = this.c;
        if (zzgdVar != null) {
            zzb.zzd(zzgdVar);
        }
        zzgc zzf = zzgd.zzf();
        zzf.zzb(zzkp.y(this.d));
        zzf.zzd(zzkp.y(this.e));
        Map<Integer, Long> map = this.f;
        if (map == null) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList(map.size());
            for (Integer num : this.f.keySet()) {
                int intValue = num.intValue();
                Long l = this.f.get(Integer.valueOf(intValue));
                if (l != null) {
                    zzfl zzc = zzfm.zzc();
                    zzc.zzb(intValue);
                    zzc.zza(l.longValue());
                    arrayList2.add(zzc.zzaA());
                }
            }
            arrayList = arrayList2;
        }
        if (arrayList != null) {
            zzf.zza(arrayList);
        }
        Map<Integer, List<Long>> map2 = this.g;
        if (map2 == null) {
            list = Collections.emptyList();
        } else {
            ArrayList arrayList3 = new ArrayList(map2.size());
            for (Integer num2 : this.g.keySet()) {
                zzge zzd = zzgf.zzd();
                zzd.zzb(num2.intValue());
                List<Long> list2 = this.g.get(num2);
                if (list2 != null) {
                    Collections.sort(list2);
                    zzd.zza(list2);
                }
                arrayList3.add((zzgf) zzd.zzaA());
            }
            list = arrayList3;
        }
        zzf.zzc(list);
        zzb.zzb(zzf);
        return zzb.zzaA();
    }

    public final void c(@NonNull k4 k4Var) {
        int a2 = k4Var.a();
        Boolean bool = k4Var.c;
        if (bool != null) {
            this.e.set(a2, bool.booleanValue());
        }
        Boolean bool2 = k4Var.d;
        if (bool2 != null) {
            this.d.set(a2, bool2.booleanValue());
        }
        if (k4Var.e != null) {
            Map<Integer, Long> map = this.f;
            Integer valueOf = Integer.valueOf(a2);
            Long l = map.get(valueOf);
            long longValue = k4Var.e.longValue() / 1000;
            if (l == null || longValue > l.longValue()) {
                this.f.put(valueOf, Long.valueOf(longValue));
            }
        }
        if (k4Var.f != null) {
            Map<Integer, List<Long>> map2 = this.g;
            Integer valueOf2 = Integer.valueOf(a2);
            List<Long> list = map2.get(valueOf2);
            if (list == null) {
                list = new ArrayList<>();
                this.g.put(valueOf2, list);
            }
            if (k4Var.c()) {
                list.clear();
            }
            zzoe.zzc();
            zzaf zzf = this.h.zzs.zzf();
            String str = this.f10118a;
            zzdv<Boolean> zzdvVar = zzdw.zzY;
            if (zzf.zzs(str, zzdvVar) && k4Var.b()) {
                list.clear();
            }
            zzoe.zzc();
            if (this.h.zzs.zzf().zzs(this.f10118a, zzdvVar)) {
                Long valueOf3 = Long.valueOf(k4Var.f.longValue() / 1000);
                if (list.contains(valueOf3)) {
                    return;
                }
                list.add(valueOf3);
                return;
            }
            list.add(Long.valueOf(k4Var.f.longValue() / 1000));
        }
    }

    public /* synthetic */ h4(m4 m4Var, String str, zzgd zzgdVar, BitSet bitSet, BitSet bitSet2, Map map, Map map2, zzs zzsVar) {
        this.h = m4Var;
        this.f10118a = str;
        this.d = bitSet;
        this.e = bitSet2;
        this.f = map;
        this.g = new ArrayMap();
        for (Integer num : map2.keySet()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add((Long) map2.get(num));
            this.g.put(num, arrayList);
        }
        this.b = false;
        this.c = zzgdVar;
    }
}
