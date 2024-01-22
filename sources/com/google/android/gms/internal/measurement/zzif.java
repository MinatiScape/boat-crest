package com.google.android.gms.internal.measurement;

import java.io.Serializable;
/* loaded from: classes8.dex */
public final class zzif {
    public static <T> zzib<T> zza(zzib<T> zzibVar) {
        if ((zzibVar instanceof c2) || (zzibVar instanceof b2)) {
            return zzibVar;
        }
        if (zzibVar instanceof Serializable) {
            return new b2(zzibVar);
        }
        return new c2(zzibVar);
    }

    public static <T> zzib<T> zzb(T t) {
        return new d2(t);
    }
}
