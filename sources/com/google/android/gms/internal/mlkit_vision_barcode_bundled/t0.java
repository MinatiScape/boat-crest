package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes8.dex */
public final class t0 {
    public static final int a(int i, Object obj, Object obj2) {
        zzfi zzfiVar = (zzfi) obj;
        zzfh zzfhVar = (zzfh) obj2;
        if (zzfiVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzfiVar.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            entry.getKey();
            entry.getValue();
            throw null;
        }
        return 0;
    }

    public static final Object b(Object obj, Object obj2) {
        zzfi zzfiVar = (zzfi) obj;
        zzfi zzfiVar2 = (zzfi) obj2;
        if (!zzfiVar2.isEmpty()) {
            if (!zzfiVar.zze()) {
                zzfiVar = zzfiVar.zzb();
            }
            zzfiVar.zzd(zzfiVar2);
        }
        return zzfiVar;
    }
}
