package com.google.android.gms.internal.firebase_ml;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes7.dex */
public final class g1<T> implements Iterator<T> {
    public final int h;
    public int i = 0;
    public final /* synthetic */ h1 j;

    public g1(h1 h1Var) {
        this.j = h1Var;
        this.h = Array.getLength(h1Var.h);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.i < this.h;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (hasNext()) {
            Object obj = this.j.h;
            int i = this.i;
            this.i = i + 1;
            return (T) Array.get(obj, i);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
