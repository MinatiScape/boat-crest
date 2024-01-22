package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Iterator;
@GwtCompatible
/* loaded from: classes10.dex */
public abstract class q2<F, T> implements Iterator<T> {
    public final Iterator<? extends F> h;

    public q2(Iterator<? extends F> it) {
        this.h = (Iterator) Preconditions.checkNotNull(it);
    }

    public abstract T a(F f);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h.hasNext();
    }

    @Override // java.util.Iterator
    public final T next() {
        return a(this.h.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.h.remove();
    }
}
