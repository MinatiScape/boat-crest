package com.google.android.gms.internal.firebase_ml;

import java.util.Iterator;
/* loaded from: classes7.dex */
public final class c implements Iterator<String> {
    public Iterator<String> h;
    public final /* synthetic */ zzaab i;

    public c(zzaab zzaabVar) {
        zzxv zzxvVar;
        this.i = zzaabVar;
        zzxvVar = zzaabVar.h;
        this.h = zzxvVar.iterator();
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
