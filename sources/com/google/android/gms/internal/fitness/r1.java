package com.google.android.gms.internal.fitness;

import java.util.NoSuchElementException;
/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes8.dex */
public final class r1<T> extends zzfm<T> {
    public boolean h;
    public final /* synthetic */ Object i;

    public r1(Object obj) {
        this.i = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return !this.h;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (!this.h) {
            this.h = true;
            return (T) this.i;
        }
        throw new NoSuchElementException();
    }
}
