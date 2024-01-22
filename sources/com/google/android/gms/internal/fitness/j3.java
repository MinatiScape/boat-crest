package com.google.android.gms.internal.fitness;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes8.dex */
public final class j3 implements h3 {
    @Override // com.google.android.gms.internal.fitness.h3
    public final int a(int i, Object obj, Object obj2) {
        zzie zzieVar = (zzie) obj;
        zzic zzicVar = (zzic) obj2;
        if (zzieVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzieVar.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            entry.getKey();
            entry.getValue();
            throw new NoSuchMethodError();
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.fitness.h3
    public final g3<?, ?> b(Object obj) {
        zzic zzicVar = (zzic) obj;
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.fitness.h3
    public final Object c(Object obj, Object obj2) {
        zzie zzieVar = (zzie) obj;
        zzie zzieVar2 = (zzie) obj2;
        if (!zzieVar2.isEmpty()) {
            if (!zzieVar.isMutable()) {
                zzieVar = zzieVar.zzcn();
            }
            zzieVar.zza(zzieVar2);
        }
        return zzieVar;
    }

    @Override // com.google.android.gms.internal.fitness.h3
    public final Object d(Object obj) {
        ((zzie) obj).zzar();
        return obj;
    }

    @Override // com.google.android.gms.internal.fitness.h3
    public final Map<?, ?> e(Object obj) {
        return (zzie) obj;
    }
}
