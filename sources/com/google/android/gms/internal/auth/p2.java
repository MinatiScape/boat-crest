package com.google.android.gms.internal.auth;

import java.util.Iterator;
/* loaded from: classes6.dex */
public final class p2 implements Iterator {
    public final Iterator h;
    public final /* synthetic */ zzhd i;

    public p2(zzhd zzhdVar) {
        zzfe zzfeVar;
        this.i = zzhdVar;
        zzfeVar = zzhdVar.h;
        this.h = zzfeVar.iterator();
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
