package com.google.android.gms.internal.vision;

import java.io.Serializable;
/* loaded from: classes10.dex */
public abstract class zzcn<T> implements Serializable {
    public static <T> zzcn<T> zzbx() {
        return m.zzlj;
    }

    public static <T> zzcn<T> zzc(T t) {
        return new q(zzct.checkNotNull(t));
    }

    public abstract T get();

    public abstract boolean isPresent();
}
