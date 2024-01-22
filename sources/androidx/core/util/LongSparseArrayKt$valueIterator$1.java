package androidx.core.util;

import android.annotation.SuppressLint;
import android.util.LongSparseArray;
import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;
/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes.dex */
public final class LongSparseArrayKt$valueIterator$1<T> implements Iterator<T>, KMappedMarker {
    public int h;
    public final /* synthetic */ LongSparseArray<T> i;

    public LongSparseArrayKt$valueIterator$1(LongSparseArray<T> longSparseArray) {
        this.i = longSparseArray;
    }

    public final int getIndex() {
        return this.h;
    }

    @Override // java.util.Iterator
    @SuppressLint({"ClassVerificationFailure"})
    public boolean hasNext() {
        return this.h < this.i.size();
    }

    @Override // java.util.Iterator
    @SuppressLint({"ClassVerificationFailure"})
    public T next() {
        LongSparseArray<T> longSparseArray = this.i;
        int i = this.h;
        this.h = i + 1;
        return longSparseArray.valueAt(i);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setIndex(int i) {
        this.h = i;
    }
}
