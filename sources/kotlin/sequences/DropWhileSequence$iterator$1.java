package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes12.dex */
public final class DropWhileSequence$iterator$1<T> implements Iterator<T>, KMappedMarker {
    @NotNull
    public final Iterator<T> h;
    public int i;
    @Nullable
    public T j;
    public final /* synthetic */ DropWhileSequence<T> k;

    public DropWhileSequence$iterator$1(DropWhileSequence<T> dropWhileSequence) {
        Sequence sequence;
        this.k = dropWhileSequence;
        sequence = dropWhileSequence.f14092a;
        this.h = sequence.iterator();
        this.i = -1;
    }

    public final void a() {
        Function1 function1;
        while (this.h.hasNext()) {
            T next = this.h.next();
            function1 = this.k.b;
            if (!((Boolean) function1.invoke(next)).booleanValue()) {
                this.j = next;
                this.i = 1;
                return;
            }
        }
        this.i = 0;
    }

    public final int getDropState() {
        return this.i;
    }

    @NotNull
    public final Iterator<T> getIterator() {
        return this.h;
    }

    @Nullable
    public final T getNextItem() {
        return this.j;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.i == -1) {
            a();
        }
        return this.i == 1 || this.h.hasNext();
    }

    @Override // java.util.Iterator
    public T next() {
        if (this.i == -1) {
            a();
        }
        if (this.i == 1) {
            T t = this.j;
            this.j = null;
            this.i = 0;
            return t;
        }
        return this.h.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setDropState(int i) {
        this.i = i;
    }

    public final void setNextItem(@Nullable T t) {
        this.j = t;
    }
}
