package com.google.android.gms.internal.firebase_ml;

import java.util.NoSuchElementException;
/* loaded from: classes7.dex */
public abstract class c2<E> extends zznc<E> {
    public final int h;
    public int i;

    public c2(int i, int i2) {
        zzml.zzc(i2, i);
        this.h = i;
        this.i = i2;
    }

    public abstract E a(int i);

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.i < this.h;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.i > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final E next() {
        if (hasNext()) {
            int i = this.i;
            this.i = i + 1;
            return a(i);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.i;
    }

    @Override // java.util.ListIterator
    public final E previous() {
        if (hasPrevious()) {
            int i = this.i - 1;
            this.i = i;
            return a(i);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.i - 1;
    }
}
