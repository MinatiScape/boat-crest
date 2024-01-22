package kotlin.collections;

import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.markers.KMappedMarker;
/* JADX INFO: Add missing generic type declarations: [V] */
/* loaded from: classes12.dex */
public final class AbstractMap$values$1$iterator$1<V> implements Iterator<V>, KMappedMarker {
    public final /* synthetic */ Iterator<Map.Entry<K, V>> h;

    /* JADX WARN: Multi-variable type inference failed */
    public AbstractMap$values$1$iterator$1(Iterator<? extends Map.Entry<? extends K, ? extends V>> it) {
        this.h = it;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.h.hasNext();
    }

    @Override // java.util.Iterator
    public V next() {
        return (V) ((Map.Entry) this.h.next()).getValue();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
