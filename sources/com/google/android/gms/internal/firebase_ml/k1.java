package com.google.android.gms.internal.firebase_ml;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes7.dex */
public abstract class k1<T> implements Iterator<T> {
    public int h = m1.b;
    @NullableDecl
    public T i;

    public abstract T a();

    @NullableDecl
    public final T b() {
        this.h = m1.c;
        return null;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i = this.h;
        int i2 = m1.d;
        zzml.checkState(i != i2);
        int i3 = n1.f8708a[this.h - 1];
        if (i3 != 1) {
            if (i3 != 2) {
                this.h = i2;
                this.i = a();
                if (this.h != m1.c) {
                    this.h = m1.f8703a;
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (hasNext()) {
            this.h = m1.b;
            T t = this.i;
            this.i = null;
            return t;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
