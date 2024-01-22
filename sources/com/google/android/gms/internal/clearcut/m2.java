package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
/* loaded from: classes7.dex */
public final class m2 implements Iterator<String> {
    public Iterator<String> h;
    public final /* synthetic */ zzfa i;

    public m2(zzfa zzfaVar) {
        zzcx zzcxVar;
        this.i = zzfaVar;
        zzcxVar = zzfaVar.h;
        this.h = zzcxVar.iterator();
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
