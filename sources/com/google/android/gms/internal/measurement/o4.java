package com.google.android.gms.internal.measurement;

import java.util.Iterator;
/* loaded from: classes8.dex */
public final class o4 implements Iterator<String> {
    public final Iterator<String> h;
    public final /* synthetic */ zzmn i;

    public o4(zzmn zzmnVar) {
        zzko zzkoVar;
        this.i = zzmnVar;
        zzkoVar = zzmnVar.h;
        this.h = zzkoVar.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ String next() {
        return this.h.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
