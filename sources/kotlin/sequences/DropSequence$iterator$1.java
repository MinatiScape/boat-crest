package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes12.dex */
public final class DropSequence$iterator$1<T> implements Iterator<T>, KMappedMarker {
    @NotNull
    public final Iterator<T> h;
    public int i;

    public DropSequence$iterator$1(DropSequence<T> dropSequence) {
        Sequence sequence;
        int i;
        sequence = dropSequence.f14091a;
        this.h = sequence.iterator();
        i = dropSequence.b;
        this.i = i;
    }

    public final void a() {
        while (this.i > 0 && this.h.hasNext()) {
            this.h.next();
            this.i--;
        }
    }

    @NotNull
    public final Iterator<T> getIterator() {
        return this.h;
    }

    public final int getLeft() {
        return this.i;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        a();
        return this.h.hasNext();
    }

    @Override // java.util.Iterator
    public T next() {
        a();
        return this.h.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setLeft(int i) {
        this.i = i;
    }
}
