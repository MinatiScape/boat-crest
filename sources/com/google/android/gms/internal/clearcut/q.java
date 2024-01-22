package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes7.dex */
public final class q implements Iterator {
    public int h = 0;
    public final int i;
    public final /* synthetic */ zzbb j;

    public q(zzbb zzbbVar) {
        this.j = zzbbVar;
        this.i = zzbbVar.size();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h < this.i;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        return Byte.valueOf(nextByte());
    }

    public final byte nextByte() {
        try {
            zzbb zzbbVar = this.j;
            int i = this.h;
            this.h = i + 1;
            return zzbbVar.zzj(i);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
