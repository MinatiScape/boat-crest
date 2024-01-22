package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes10.dex */
public final class b3 implements z2 {
    @Override // com.google.android.gms.internal.vision.z2
    public final int a(int i, Object obj, Object obj2) {
        zzhw zzhwVar = (zzhw) obj;
        zzhu zzhuVar = (zzhu) obj2;
        if (zzhwVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzhwVar.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            entry.getKey();
            entry.getValue();
            throw new NoSuchMethodError();
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.vision.z2
    public final Object b(Object obj) {
        return zzhw.zzhc().zzhd();
    }

    @Override // com.google.android.gms.internal.vision.z2
    public final Object c(Object obj, Object obj2) {
        zzhw zzhwVar = (zzhw) obj;
        zzhw zzhwVar2 = (zzhw) obj2;
        if (!zzhwVar2.isEmpty()) {
            if (!zzhwVar.isMutable()) {
                zzhwVar = zzhwVar.zzhd();
            }
            zzhwVar.zza(zzhwVar2);
        }
        return zzhwVar;
    }

    @Override // com.google.android.gms.internal.vision.z2
    public final Map<?, ?> d(Object obj) {
        return (zzhw) obj;
    }

    @Override // com.google.android.gms.internal.vision.z2
    public final y2<?, ?> e(Object obj) {
        zzhu zzhuVar = (zzhu) obj;
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.vision.z2
    public final Object f(Object obj) {
        ((zzhw) obj).zzdp();
        return obj;
    }

    @Override // com.google.android.gms.internal.vision.z2
    public final boolean g(Object obj) {
        return !((zzhw) obj).isMutable();
    }

    @Override // com.google.android.gms.internal.vision.z2
    public final Map<?, ?> zzl(Object obj) {
        return (zzhw) obj;
    }
}
