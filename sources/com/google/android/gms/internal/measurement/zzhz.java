package com.google.android.gms.internal.measurement;

import java.io.Serializable;
/* loaded from: classes8.dex */
public abstract class zzhz<T> implements Serializable {
    public static <T> zzhz<T> zzc() {
        return z1.zza;
    }

    public static <T> zzhz<T> zzd(T t) {
        return new a2(t);
    }

    public abstract T zza();

    public abstract boolean zzb();
}
