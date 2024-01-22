package com.google.android.libraries.places.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
abstract class zzfc<T> implements Iterator<T> {
    private int zza = zzfe.zzb;
    @NullableDecl
    private T zzb;

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i = this.zza;
        int i2 = zzfe.zzd;
        if (i != i2) {
            int i3 = zzfb.zza[i - 1];
            if (i3 != 1) {
                if (i3 != 2) {
                    this.zza = i2;
                    this.zzb = zza();
                    if (this.zza != zzfe.zzc) {
                        this.zza = zzfe.zza;
                        return true;
                    }
                    return false;
                }
                return true;
            }
            return false;
        }
        throw new IllegalStateException();
    }

    @Override // java.util.Iterator
    public final T next() {
        if (hasNext()) {
            this.zza = zzfe.zzb;
            T t = this.zzb;
            this.zzb = null;
            return t;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public abstract T zza();

    @NullableDecl
    public final T zzb() {
        this.zza = zzfe.zzc;
        return null;
    }
}
