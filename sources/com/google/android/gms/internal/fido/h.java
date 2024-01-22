package com.google.android.gms.internal.fido;

import java.util.NoSuchElementException;
/* loaded from: classes7.dex */
public final class h extends zzaz {
    public boolean h;
    public final /* synthetic */ Object i;

    public h(Object obj) {
        this.i = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return !this.h;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.h) {
            throw new NoSuchElementException();
        }
        this.h = true;
        return this.i;
    }
}
