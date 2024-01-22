package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes12.dex */
public final class TakeWhileSequence$iterator$1<T> implements Iterator<T>, KMappedMarker {
    @NotNull
    public final Iterator<T> h;
    public int i;
    @Nullable
    public T j;
    public final /* synthetic */ TakeWhileSequence<T> k;

    public TakeWhileSequence$iterator$1(TakeWhileSequence<T> takeWhileSequence) {
        Sequence sequence;
        this.k = takeWhileSequence;
        sequence = takeWhileSequence.f14110a;
        this.h = sequence.iterator();
        this.i = -1;
    }

    public final void a() {
        Function1 function1;
        if (this.h.hasNext()) {
            T next = this.h.next();
            function1 = this.k.b;
            if (((Boolean) function1.invoke(next)).booleanValue()) {
                this.i = 1;
                this.j = next;
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
