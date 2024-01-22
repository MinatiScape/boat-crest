package androidx.core.util;

import android.util.SparseArray;
import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;
/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes.dex */
public final class SparseArrayKt$valueIterator$1<T> implements Iterator<T>, KMappedMarker {
    public int h;
    public final /* synthetic */ SparseArray<T> i;

    public SparseArrayKt$valueIterator$1(SparseArray<T> sparseArray) {
        this.i = sparseArray;
    }

    public final int getIndex() {
        return this.h;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.h < this.i.size();
    }

    @Override // java.util.Iterator
    public T next() {
        SparseArray<T> sparseArray = this.i;
        int i = this.h;
        this.h = i + 1;
        return sparseArray.valueAt(i);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setIndex(int i) {
        this.h = i;
    }
}
