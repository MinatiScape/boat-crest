package com.google.android.gms.internal.measurement;

import java.util.Iterator;
/* loaded from: classes8.dex */
public final class c implements Iterator<zzap> {
    public final /* synthetic */ Iterator h;

    public c(Iterator it) {
        this.h = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ zzap next() {
        return new zzat((String) this.h.next());
    }
}
