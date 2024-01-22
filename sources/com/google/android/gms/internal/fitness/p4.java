package com.google.android.gms.internal.fitness;

import java.util.Iterator;
/* loaded from: classes8.dex */
public final class p4 implements Iterator<String> {
    public Iterator<String> h;
    public final /* synthetic */ zzjt i;

    public p4(zzjt zzjtVar) {
        zzhr zzhrVar;
        this.i = zzjtVar;
        zzhrVar = zzjtVar.h;
        this.h = zzhrVar.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h.hasNext();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.h.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
