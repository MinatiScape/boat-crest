package com.google.android.libraries.places.internal;

import java.util.Comparator;
/* loaded from: classes10.dex */
public abstract class zzgo<T> implements Comparator<T> {
    public static <T> zzgo<T> zza(Comparator<T> comparator) {
        if (comparator instanceof zzgo) {
            return (zzgo) comparator;
        }
        return new zzga(comparator);
    }
}
