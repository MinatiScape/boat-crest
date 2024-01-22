package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.util.Iterator;
/* loaded from: classes10.dex */
public final class h implements Iterator<String> {
    public final Iterator<String> h;
    public final /* synthetic */ zzar i;

    public h(zzar zzarVar) {
        Bundle bundle;
        this.i = zzarVar;
        bundle = zzarVar.h;
        this.h = bundle.keySet().iterator();
    }

    @Override // java.util.Iterator
    /* renamed from: a */
    public final String next() {
        return this.h.next();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h.hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }
}
