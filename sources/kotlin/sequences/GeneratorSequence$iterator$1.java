package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes12.dex */
public final class GeneratorSequence$iterator$1<T> implements Iterator<T>, KMappedMarker {
    @Nullable
    public T h;
    public int i = -2;
    public final /* synthetic */ GeneratorSequence<T> j;

    public GeneratorSequence$iterator$1(GeneratorSequence<T> generatorSequence) {
        this.j = generatorSequence;
    }

    public final void a() {
        Function1 function1;
        T t;
        Function0 function0;
        if (this.i == -2) {
            function0 = this.j.f14095a;
            t = (T) function0.invoke();
        } else {
            function1 = this.j.b;
            T t2 = this.h;
            Intrinsics.checkNotNull(t2);
            t = (T) function1.invoke(t2);
        }
        this.h = t;
        this.i = t == null ? 0 : 1;
    }

    @Nullable
    public final T getNextItem() {
        return this.h;
    }

    public final int getNextState() {
        return this.i;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.i < 0) {
            a();
        }
        return this.i == 1;
    }

    @Override // java.util.Iterator
    @NotNull
    public T next() {
        if (this.i < 0) {
            a();
        }
        if (this.i != 0) {
            T t = this.h;
            Intrinsics.checkNotNull(t, "null cannot be cast to non-null type T of kotlin.sequences.GeneratorSequence");
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
        this.h = t;
    }

    public final void setNextState(int i) {
        this.i = i;
    }
}
