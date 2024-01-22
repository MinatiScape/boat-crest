package com.google.android.gms.internal.firebase_ml;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes7.dex */
public final class y6<K> implements Iterator<Map.Entry<K, Object>> {
    public Iterator<Map.Entry<K, Object>> h;

    public y6(Iterator<Map.Entry<K, Object>> it) {
        this.h = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h.hasNext();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        Map.Entry<K, Object> next = this.h.next();
        return next.getValue() instanceof zzxp ? new z6(next) : next;
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.h.remove();
    }
}
