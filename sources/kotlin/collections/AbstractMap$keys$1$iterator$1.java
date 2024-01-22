package kotlin.collections;

import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.markers.KMappedMarker;
/* JADX INFO: Add missing generic type declarations: [K] */
/* loaded from: classes12.dex */
public final class AbstractMap$keys$1$iterator$1<K> implements Iterator<K>, KMappedMarker {
    public final /* synthetic */ Iterator<Map.Entry<K, V>> h;

    /* JADX WARN: Multi-variable type inference failed */
    public AbstractMap$keys$1$iterator$1(Iterator<? extends Map.Entry<? extends K, ? extends V>> it) {
        this.h = it;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.h.hasNext();
    }

    @Override // java.util.Iterator
    public K next() {
        return (K) ((Map.Entry) this.h.next()).getKey();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
