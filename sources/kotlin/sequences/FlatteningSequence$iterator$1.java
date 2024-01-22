package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Add missing generic type declarations: [E] */
/* loaded from: classes12.dex */
public final class FlatteningSequence$iterator$1<E> implements Iterator<E>, KMappedMarker {
    @NotNull
    public final Iterator<T> h;
    @Nullable
    public Iterator<? extends E> i;
    public final /* synthetic */ FlatteningSequence<T, R, E> j;

    public FlatteningSequence$iterator$1(FlatteningSequence<T, R, E> flatteningSequence) {
        Sequence sequence;
        this.j = flatteningSequence;
        sequence = flatteningSequence.f14094a;
        this.h = sequence.iterator();
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0045, code lost:
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean a() {
        /*
            r5 = this;
            java.util.Iterator<? extends E> r0 = r5.i
            r1 = 1
            r2 = 0
            if (r0 == 0) goto Le
            boolean r0 = r0.hasNext()
            if (r0 != 0) goto Le
            r0 = r1
            goto Lf
        Le:
            r0 = r2
        Lf:
            if (r0 == 0) goto L14
            r0 = 0
            r5.i = r0
        L14:
            java.util.Iterator<? extends E> r0 = r5.i
            if (r0 != 0) goto L45
            java.util.Iterator<T> r0 = r5.h
            boolean r0 = r0.hasNext()
            if (r0 != 0) goto L21
            return r2
        L21:
            java.util.Iterator<T> r0 = r5.h
            java.lang.Object r0 = r0.next()
            kotlin.sequences.FlatteningSequence<T, R, E> r3 = r5.j
            kotlin.jvm.functions.Function1 r3 = kotlin.sequences.FlatteningSequence.access$getIterator$p(r3)
            kotlin.sequences.FlatteningSequence<T, R, E> r4 = r5.j
            kotlin.jvm.functions.Function1 r4 = kotlin.sequences.FlatteningSequence.access$getTransformer$p(r4)
            java.lang.Object r0 = r4.invoke(r0)
            java.lang.Object r0 = r3.invoke(r0)
            java.util.Iterator r0 = (java.util.Iterator) r0
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L14
            r5.i = r0
        L45:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.FlatteningSequence$iterator$1.a():boolean");
    }

    @Nullable
    public final Iterator<E> getItemIterator() {
        return (Iterator<? extends E>) this.i;
    }

    @NotNull
    public final Iterator<T> getIterator() {
        return this.h;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return a();
    }

    @Override // java.util.Iterator
    public E next() {
        if (a()) {
            Iterator<? extends E> it = this.i;
            Intrinsics.checkNotNull(it);
            return it.next();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setItemIterator(@Nullable Iterator<? extends E> it) {
        this.i = it;
    }
}
