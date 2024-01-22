package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Add missing generic type declarations: [R] */
/* loaded from: classes12.dex */
public final class TransformingSequence$iterator$1<R> implements Iterator<R>, KMappedMarker {
    @NotNull
    public final Iterator<T> h;
    public final /* synthetic */ TransformingSequence<T, R> i;

    public TransformingSequence$iterator$1(TransformingSequence<T, R> transformingSequence) {
        Sequence sequence;
        this.i = transformingSequence;
        sequence = transformingSequence.f14112a;
        this.h = sequence.iterator();
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
        Function1 function1;
        function1 = this.i.b;
        return (R) function1.invoke(this.h.next());
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
