package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Add missing generic type declarations: [V] */
/* loaded from: classes12.dex */
public final class MergingSequence$iterator$1<V> implements Iterator<V>, KMappedMarker {
    @NotNull
    public final Iterator<T1> h;
    @NotNull
    public final Iterator<T2> i;
    public final /* synthetic */ MergingSequence<T1, T2, V> j;

    public MergingSequence$iterator$1(MergingSequence<T1, T2, V> mergingSequence) {
        Sequence sequence;
        Sequence sequence2;
        this.j = mergingSequence;
        sequence = mergingSequence.f14097a;
        this.h = sequence.iterator();
        sequence2 = mergingSequence.b;
        this.i = sequence2.iterator();
    }

    @NotNull
    public final Iterator<T1> getIterator1() {
        return this.h;
    }

    @NotNull
    public final Iterator<T2> getIterator2() {
        return this.i;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.h.hasNext() && this.i.hasNext();
    }

    @Override // java.util.Iterator
    public V next() {
        Function2 function2;
        function2 = this.j.c;
        return (V) function2.invoke(this.h.next(), this.i.next());
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
