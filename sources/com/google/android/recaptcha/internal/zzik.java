package com.google.android.recaptcha.internal;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes10.dex */
final class zzik {
    public static final int zza(int i, Object obj, Object obj2) {
        zzij zzijVar = (zzij) obj;
        zzii zziiVar = (zzii) obj2;
        if (zzijVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzijVar.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            entry.getKey();
            entry.getValue();
            throw null;
        }
        return 0;
    }

    public static final boolean zzb(Object obj) {
        return !((zzij) obj).zze();
    }

    public static final Object zzc(Object obj, Object obj2) {
        zzij zzijVar = (zzij) obj;
        zzij zzijVar2 = (zzij) obj2;
        if (!zzijVar2.isEmpty()) {
            if (!zzijVar.zze()) {
                zzijVar = zzijVar.zzb();
            }
            zzijVar.zzd(zzijVar2);
        }
        return zzijVar;
    }
}
