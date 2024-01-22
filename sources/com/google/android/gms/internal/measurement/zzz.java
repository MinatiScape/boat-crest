package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.TreeMap;
/* loaded from: classes8.dex */
public final class zzz {

    /* renamed from: a  reason: collision with root package name */
    public final TreeMap<Integer, zzao> f8966a = new TreeMap<>();
    public final TreeMap<Integer, zzao> b = new TreeMap<>();

    public static final int a(zzg zzgVar, zzao zzaoVar, zzap zzapVar) {
        zzap zza = zzaoVar.zza(zzgVar, Collections.singletonList(zzapVar));
        if (zza instanceof zzah) {
            return zzh.zzb(zza.zzh().doubleValue());
        }
        return -1;
    }

    public final void zza(String str, int i, zzao zzaoVar, String str2) {
        TreeMap<Integer, zzao> treeMap;
        if ("create".equals(str2)) {
            treeMap = this.b;
        } else if (!"edit".equals(str2)) {
            String valueOf = String.valueOf(str2);
            throw new IllegalStateException(valueOf.length() != 0 ? "Unknown callback type: ".concat(valueOf) : new String("Unknown callback type: "));
        } else {
            treeMap = this.f8966a;
        }
        if (treeMap.containsKey(Integer.valueOf(i))) {
            i = treeMap.lastKey().intValue() + 1;
        }
        treeMap.put(Integer.valueOf(i), zzaoVar);
    }

    public final void zzb(zzg zzgVar, zzab zzabVar) {
        zzl zzlVar = new zzl(zzabVar);
        for (Integer num : this.f8966a.keySet()) {
            zzaa clone = zzabVar.zzb().clone();
            int a2 = a(zzgVar, this.f8966a.get(num), zzlVar);
            if (a2 == 2 || a2 == -1) {
                zzabVar.zzf(clone);
            }
        }
        for (Integer num2 : this.b.keySet()) {
            a(zzgVar, this.b.get(num2), zzlVar);
        }
    }
}
