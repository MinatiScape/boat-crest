package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes7.dex */
public final class c1 implements b1 {
    @Override // com.google.android.gms.internal.clearcut.b1
    public final int a(int i, Object obj, Object obj2) {
        zzdi zzdiVar = (zzdi) obj;
        if (zzdiVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzdiVar.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            entry.getKey();
            entry.getValue();
            throw new NoSuchMethodError();
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.clearcut.b1
    public final Object b(Object obj) {
        ((zzdi) obj).zzv();
        return obj;
    }

    @Override // com.google.android.gms.internal.clearcut.b1
    public final boolean d(Object obj) {
        return !((zzdi) obj).isMutable();
    }

    @Override // com.google.android.gms.internal.clearcut.b1
    public final Map<?, ?> e(Object obj) {
        return (zzdi) obj;
    }

    @Override // com.google.android.gms.internal.clearcut.b1
    public final Map<?, ?> f(Object obj) {
        return (zzdi) obj;
    }

    @Override // com.google.android.gms.internal.clearcut.b1
    public final Object zzb(Object obj, Object obj2) {
        zzdi zzdiVar = (zzdi) obj;
        zzdi zzdiVar2 = (zzdi) obj2;
        if (!zzdiVar2.isEmpty()) {
            if (!zzdiVar.isMutable()) {
                zzdiVar = zzdiVar.zzca();
            }
            zzdiVar.zza(zzdiVar2);
        }
        return zzdiVar;
    }

    @Override // com.google.android.gms.internal.clearcut.b1
    public final Object zzk(Object obj) {
        return zzdi.zzbz().zzca();
    }

    @Override // com.google.android.gms.internal.clearcut.b1
    public final a1<?, ?> zzl(Object obj) {
        throw new NoSuchMethodError();
    }
}
