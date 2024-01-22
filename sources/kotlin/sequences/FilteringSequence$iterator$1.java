package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes12.dex */
public final class FilteringSequence$iterator$1<T> implements Iterator<T>, KMappedMarker {
    @NotNull
    public final Iterator<T> h;
    public int i;
    @Nullable
    public T j;
    public final /* synthetic */ FilteringSequence<T> k;

    public FilteringSequence$iterator$1(FilteringSequence<T> filteringSequence) {
        Sequence sequence;
        this.k = filteringSequence;
        sequence = filteringSequence.f14093a;
        this.h = sequence.iterator();
        this.i = -1;
    }

    public final void a() {
        Function1 function1;
        boolean z;
        while (this.h.hasNext()) {
            T next = this.h.next();
            function1 = this.k.c;
            boolean booleanValue = ((Boolean) function1.invoke(next)).booleanValue();
            z = this.k.b;
            if (booleanValue == z) {
                this.j = next;
                this.i = 1;
                return;
            }
        }
        this.i = 0;
    }

    @NotNull
    public final Iterator<T> getIterator() {
        return this.h;
    }

    @Nullable
    public final T getNextItem() {
        return this.j;
    }

    public final int getNextState() {
        return this.i;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.i == -1) {
            a();
        }
        return this.i == 1;
    }

    @Override // java.util.Iterator
    public T next() {
        if (this.i == -1) {
            a();
        }
        if (this.i != 0) {
            T t = this.j;
            this.j = null;
            this.i = -1;
            return t;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setNextItem(@Nullable T t) {
        this.j = t;
    }

    public final void setNextState(int i) {
        this.i = i;
    }
}
