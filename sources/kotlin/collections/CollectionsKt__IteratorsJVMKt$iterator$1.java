package kotlin.collections;

import java.util.Enumeration;
import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;
/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes12.dex */
public final class CollectionsKt__IteratorsJVMKt$iterator$1<T> implements Iterator<T>, KMappedMarker {
    public final /* synthetic */ Enumeration<T> h;

    public CollectionsKt__IteratorsJVMKt$iterator$1(Enumeration<T> enumeration) {
        this.h = enumeration;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.h.hasMoreElements();
    }

    @Override // java.util.Iterator
    public T next() {
        return this.h.nextElement();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
