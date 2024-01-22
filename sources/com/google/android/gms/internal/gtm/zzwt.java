package com.google.android.gms.internal.gtm;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes8.dex */
public final class zzwt {
    public static final zzwt zza = new zzwt();
    public final ConcurrentMap<Class<?>, zzwx<?>> zzc = new ConcurrentHashMap();
    public final zzwy zzb = new zzwc();

    public static zzwt zza() {
        return zza;
    }

    public final <T> zzwx<T> zzb(Class<T> cls) {
        zzvi.zzf(cls, "messageType");
        zzwx<T> zzwxVar = (zzwx<T>) this.zzc.get(cls);
        if (zzwxVar == null) {
            zzwxVar = this.zzb.zza(cls);
            zzvi.zzf(cls, "messageType");
            zzvi.zzf(zzwxVar, "schema");
            zzwx putIfAbsent = this.zzc.putIfAbsent(cls, zzwxVar);
            if (putIfAbsent != null) {
                return putIfAbsent;
            }
        }
        return zzwxVar;
    }
}
