package com.google.android.gms.internal.firebase_ml;

import java.util.AbstractMap;
import java.util.Set;
/* loaded from: classes7.dex */
public final class c1 extends AbstractMap<String, Object> {
    public final Object h;
    public final zziv i;

    public c1(Object obj, boolean z) {
        this.h = obj;
        zziv zza = zziv.zza(obj.getClass(), z);
        this.i = zza;
        zzml.checkArgument(!zza.isEnum());
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* synthetic */ Set entrySet() {
        return new d1(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        zzjd zzao;
        if ((obj instanceof String) && (zzao = this.i.zzao((String) obj)) != null) {
            return zzao.zzh(this.h);
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* synthetic */ Object put(Object obj, Object obj2) {
        String str = (String) obj;
        zzjd zzao = this.i.zzao(str);
        String valueOf = String.valueOf(str);
        zzml.checkNotNull(zzao, valueOf.length() != 0 ? "no field of key ".concat(valueOf) : new String("no field of key "));
        Object zzh = zzao.zzh(this.h);
        zzao.zzb(this.h, zzml.checkNotNull(obj2));
        return zzh;
    }
}
