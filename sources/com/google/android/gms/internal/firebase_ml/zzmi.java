package com.google.android.gms.internal.firebase_ml;

import java.io.Serializable;
/* loaded from: classes7.dex */
public abstract class zzmi<T> implements Serializable {
    public static <T> zzmi<T> zzj(T t) {
        return new x1(zzml.checkNotNull(t));
    }

    public static <T> zzmi<T> zzjf() {
        return l1.zzajo;
    }

    public abstract T get();

    public abstract boolean isPresent();
}
