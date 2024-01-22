package com.google.android.libraries.places.internal;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes10.dex */
final class zztk implements zztl {
    @Override // com.google.android.libraries.places.internal.zztl
    public final zztj<?, ?> zza(Object obj) {
        zztg zztgVar = (zztg) obj;
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.libraries.places.internal.zztl
    public final Map<?, ?> zzb(Object obj) {
        return (zzti) obj;
    }

    @Override // com.google.android.libraries.places.internal.zztl
    public final Object zzc(Object obj) {
        ((zzti) obj).zzb();
        return obj;
    }

    @Override // com.google.android.libraries.places.internal.zztl
    public final Object zza(Object obj, Object obj2) {
        zzti zztiVar = (zzti) obj;
        zzti zztiVar2 = (zzti) obj2;
        if (!zztiVar2.isEmpty()) {
            if (!zztiVar.zzc()) {
                zztiVar = zztiVar.zza();
            }
            zztiVar.zza(zztiVar2);
        }
        return zztiVar;
    }

    @Override // com.google.android.libraries.places.internal.zztl
    public final int zza(int i, Object obj, Object obj2) {
        zzti zztiVar = (zzti) obj;
        zztg zztgVar = (zztg) obj2;
        if (zztiVar.isEmpty()) {
            return 0;
        }
        Iterator it = zztiVar.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            entry.getKey();
            entry.getValue();
            throw new NoSuchMethodError();
        }
        return 0;
    }
}
