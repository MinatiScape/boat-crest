package kotlin.sequences;

import java.util.Iterator;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes12.dex */
public final class IndexingSequence$iterator$1<T> implements Iterator<IndexedValue<? extends T>>, KMappedMarker {
    @NotNull
    public final Iterator<T> h;
    public int i;

    public IndexingSequence$iterator$1(IndexingSequence<T> indexingSequence) {
        Sequence sequence;
        sequence = indexingSequence.f14096a;
        this.h = sequence.iterator();
    }

    public final int getIndex() {
        return this.i;
    }

    @NotNull
    public final Iterator<T> getIterator() {
        return this.h;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.h.hasNext();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setIndex(int i) {
        this.i = i;
    }

    @Override // java.util.Iterator
    @NotNull
    public IndexedValue<T> next() {
        int i = this.i;
        this.i = i + 1;
        if (i < 0) {
            CollectionsKt__CollectionsKt.throwIndexOverflow();
        }
        return new IndexedValue<>(i, this.h.next());
    }
}
