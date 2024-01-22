package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes7.dex */
public final class r0<K> implements Iterator<Map.Entry<K, Object>> {
    public Iterator<Map.Entry<K, Object>> h;

    public r0(Iterator<Map.Entry<K, Object>> it) {
        this.h = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h.hasNext();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        Map.Entry<K, Object> next = this.h.next();
        return next.getValue() instanceof zzcr ? new q0(next) : next;
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.h.remove();
    }
}
