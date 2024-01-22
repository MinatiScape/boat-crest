package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes8.dex */
public final class a implements Iterator<zzap> {
    public final /* synthetic */ Iterator h;
    public final /* synthetic */ Iterator i;

    public a(zzae zzaeVar, Iterator it, Iterator it2) {
        this.h = it;
        this.i = it2;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.h.hasNext()) {
            return true;
        }
        return this.i.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ zzap next() {
        if (this.h.hasNext()) {
            return new zzat(((Integer) this.h.next()).toString());
        }
        if (this.i.hasNext()) {
            return new zzat((String) this.i.next());
        }
        throw new NoSuchElementException();
    }
}
