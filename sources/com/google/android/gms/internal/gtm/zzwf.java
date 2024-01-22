package com.google.android.gms.internal.gtm;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes8.dex */
public final class zzwf {
    public static final int zza(int i, Object obj, Object obj2) {
        zzwe zzweVar = (zzwe) obj;
        zzwd zzwdVar = (zzwd) obj2;
        if (zzweVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzweVar.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            entry.getKey();
            entry.getValue();
            throw null;
        }
        return 0;
    }

    public static final boolean zzb(Object obj) {
        return !((zzwe) obj).zze();
    }

    public static final Object zzc(Object obj, Object obj2) {
        zzwe zzweVar = (zzwe) obj;
        zzwe zzweVar2 = (zzwe) obj2;
        if (!zzweVar2.isEmpty()) {
            if (!zzweVar.zze()) {
                zzweVar = zzweVar.zzb();
            }
            zzweVar.zzd(zzweVar2);
        }
        return zzweVar;
    }
}
