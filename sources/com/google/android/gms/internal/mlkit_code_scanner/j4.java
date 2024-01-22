package com.google.android.gms.internal.mlkit_code_scanner;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.NoSuchElementException;
/* loaded from: classes8.dex */
public abstract class j4 extends zzab {
    public final int h;
    public int i;

    public j4(int i, int i2) {
        zzf.zzb(i2, i, FirebaseAnalytics.Param.INDEX);
        this.h = i;
        this.i = i2;
    }

    public abstract Object a(int i);

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.i < this.h;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.i > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
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
    public final Object previous() {
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
