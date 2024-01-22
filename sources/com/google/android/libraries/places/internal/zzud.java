package com.google.android.libraries.places.internal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzud {
    private static final zzud zza = new zzud();
    private final ConcurrentMap<Class<?>, zzue<?>> zzc = new ConcurrentHashMap();
    private final zzuh zzb = new zztf();

    private zzud() {
    }

    public static zzud zza() {
        return zza;
    }

    public final <T> zzue<T> zza(Class<T> cls) {
        zzsg.zza(cls, "messageType");
        zzue<T> zzueVar = (zzue<T>) this.zzc.get(cls);
        if (zzueVar == null) {
            zzue<T> zza2 = this.zzb.zza(cls);
            zzsg.zza(cls, "messageType");
            zzsg.zza(zza2, "schema");
            zzue<T> zzueVar2 = (zzue<T>) this.zzc.putIfAbsent(cls, zza2);
            return zzueVar2 != null ? zzueVar2 : zza2;
        }
        return zzueVar;
    }

    public final <T> zzue<T> zza(T t) {
        return zza((Class) t.getClass());
    }
}
