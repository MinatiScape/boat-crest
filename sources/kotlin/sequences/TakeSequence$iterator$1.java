package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes12.dex */
public final class TakeSequence$iterator$1<T> implements Iterator<T>, KMappedMarker {
    public int h;
    @NotNull
    public final Iterator<T> i;

    public TakeSequence$iterator$1(TakeSequence<T> takeSequence) {
        int i;
        Sequence sequence;
        i = takeSequence.b;
        this.h = i;
        sequence = takeSequence.f14109a;
        this.i = sequence.iterator();
    }

    @NotNull
    public final Iterator<T> getIterator() {
        return this.i;
    }

    public final int getLeft() {
        return this.h;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.h > 0 && this.i.hasNext();
    }

    @Override // java.util.Iterator
    public T next() {
        int i = this.h;
        if (i != 0) {
            this.h = i - 1;
            return this.i.next();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setLeft(int i) {
        this.h = i;
    }
}
