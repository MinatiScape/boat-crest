package com.google.android.gms.internal.vision;

import java.util.Iterator;
/* loaded from: classes10.dex */
public final class k4 implements Iterator<String> {
    public Iterator<String> h;
    public final /* synthetic */ zzjo i;

    public k4(zzjo zzjoVar) {
        zzhj zzhjVar;
        this.i = zzjoVar;
        zzhjVar = zzjoVar.h;
        this.h = zzhjVar.iterator();
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
