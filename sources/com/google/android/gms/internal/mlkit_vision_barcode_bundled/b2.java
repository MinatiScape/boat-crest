package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Iterator;
/* loaded from: classes8.dex */
public final class b2 implements Iterator {
    public final Iterator h;
    public final /* synthetic */ zzhd i;

    public b2(zzhd zzhdVar) {
        zzew zzewVar;
        this.i = zzhdVar;
        zzewVar = zzhdVar.h;
        this.h = zzewVar.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.h.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
