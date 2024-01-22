package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes12.dex */
public final class SubSequence$iterator$1<T> implements Iterator<T>, KMappedMarker {
    @NotNull
    public final Iterator<T> h;
    public int i;
    public final /* synthetic */ SubSequence<T> j;

    public SubSequence$iterator$1(SubSequence<T> subSequence) {
        Sequence sequence;
        this.j = subSequence;
        sequence = subSequence.f14108a;
        this.h = sequence.iterator();
    }

    /* JADX WARN: Incorrect condition in loop: B:3:0x0008 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a() {
        /*
            r2 = this;
        L0:
            int r0 = r2.i
            kotlin.sequences.SubSequence<T> r1 = r2.j
            int r1 = kotlin.sequences.SubSequence.access$getStartIndex$p(r1)
            if (r0 >= r1) goto L1e
            java.util.Iterator<T> r0 = r2.h
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L1e
            java.util.Iterator<T> r0 = r2.h
            r0.next()
            int r0 = r2.i
            int r0 = r0 + 1
            r2.i = r0
            goto L0
        L1e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SubSequence$iterator$1.a():void");
    }

    @NotNull
    public final Iterator<T> getIterator() {
        return this.h;
    }

    public final int getPosition() {
        return this.i;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        int i;
        a();
        int i2 = this.i;
        i = this.j.c;
        return i2 < i && this.h.hasNext();
    }

    @Override // java.util.Iterator
    public T next() {
        int i;
        a();
        int i2 = this.i;
        i = this.j.c;
        if (i2 < i) {
            this.i++;
            return this.h.next();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setPosition(int i) {
        this.i = i;
    }
}
