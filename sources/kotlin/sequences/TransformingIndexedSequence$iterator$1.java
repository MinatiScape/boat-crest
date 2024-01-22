package kotlin.sequences;

import java.util.Iterator;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Add missing generic type declarations: [R] */
/* loaded from: classes12.dex */
public final class TransformingIndexedSequence$iterator$1<R> implements Iterator<R>, KMappedMarker {
    @NotNull
    public final Iterator<T> h;
    public int i;
    public final /* synthetic */ TransformingIndexedSequence<T, R> j;

    public TransformingIndexedSequence$iterator$1(TransformingIndexedSequence<T, R> transformingIndexedSequence) {
        Sequence sequence;
        this.j = transformingIndexedSequence;
        sequence = transformingIndexedSequence.f14111a;
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
    public R next() {
        Function2 function2;
        function2 = this.j.b;
        int i = this.i;
        this.i = i + 1;
        if (i < 0) {
            CollectionsKt__CollectionsKt.throwIndexOverflow();
        }
        return (R) function2.invoke(Integer.valueOf(i), this.h.next());
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setIndex(int i) {
        this.i = i;
    }
}
