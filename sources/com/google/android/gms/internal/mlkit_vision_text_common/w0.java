package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Iterator;
import java.util.Objects;
/* loaded from: classes6.dex */
public abstract class w0 implements Iterator {
    public final Iterator h;

    public w0(Iterator it) {
        Objects.requireNonNull(it);
        this.h = it;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
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
