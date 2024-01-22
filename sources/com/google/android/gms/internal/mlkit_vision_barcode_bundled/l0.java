package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes8.dex */
public final class l0 implements Iterator {
    public final Iterator h;

    public l0(Iterator it) {
        this.h = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        Map.Entry entry = (Map.Entry) this.h.next();
        return entry.getValue() instanceof zzet ? new k0(entry, null) : entry;
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.h.remove();
    }
}
