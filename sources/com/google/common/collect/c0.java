package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Queue;
@GwtCompatible
/* loaded from: classes10.dex */
public class c0<T> extends AbstractIterator<T> {
    public final Queue<T> j;

    public c0(Queue<T> queue) {
        this.j = (Queue) Preconditions.checkNotNull(queue);
    }

    @Override // com.google.common.collect.AbstractIterator
    public T computeNext() {
        return this.j.isEmpty() ? endOfData() : this.j.remove();
    }
}
