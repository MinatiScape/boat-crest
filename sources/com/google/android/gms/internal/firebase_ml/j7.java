package com.google.android.gms.internal.firebase_ml;

import java.util.Map;
/* loaded from: classes7.dex */
public final class j7 implements k7 {
    @Override // com.google.android.gms.internal.firebase_ml.k7
    public final Object c(Object obj, Object obj2) {
        zzye zzyeVar = (zzye) obj;
        zzye zzyeVar2 = (zzye) obj2;
        if (!zzyeVar2.isEmpty()) {
            if (!zzyeVar.isMutable()) {
                zzyeVar = zzyeVar.zzvv();
            }
            zzyeVar.zza(zzyeVar2);
        }
        return zzyeVar;
    }

    @Override // com.google.android.gms.internal.firebase_ml.k7
    public final Map<?, ?> d(Object obj) {
        return (zzye) obj;
    }

    @Override // com.google.android.gms.internal.firebase_ml.k7
    public final Map<?, ?> e(Object obj) {
        return (zzye) obj;
    }

    @Override // com.google.android.gms.internal.firebase_ml.k7
    public final i7<?, ?> f(Object obj) {
        return ((zzyc) obj).c();
    }

    @Override // com.google.android.gms.internal.firebase_ml.k7
    public final int g(int i, Object obj, Object obj2) {
        zzye zzyeVar = (zzye) obj;
        zzyc zzycVar = (zzyc) obj2;
        int i2 = 0;
        if (zzyeVar.isEmpty()) {
            return 0;
        }
        for (Map.Entry entry : zzyeVar.entrySet()) {
            i2 += zzycVar.zzc(i, entry.getKey(), entry.getValue());
        }
        return i2;
    }

    @Override // com.google.android.gms.internal.firebase_ml.k7
    public final Object h(Object obj) {
        return zzye.zzvu().zzvv();
    }

    @Override // com.google.android.gms.internal.firebase_ml.k7
    public final Object i(Object obj) {
        ((zzye) obj).zztm();
        return obj;
    }

    @Override // com.google.android.gms.internal.firebase_ml.k7
    public final boolean j(Object obj) {
        return !((zzye) obj).isMutable();
    }
}
