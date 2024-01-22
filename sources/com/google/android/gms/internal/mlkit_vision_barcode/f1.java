package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Iterator;
import java.util.Objects;
/* loaded from: classes9.dex */
public abstract class f1 implements Iterator {
    public final Iterator h;

    public f1(Iterator it) {
        Objects.requireNonNull(it);
        this.h = it;
    }

    public abstract Object a(Object obj);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return a(this.h.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.h.remove();
    }
}
