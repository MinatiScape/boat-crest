package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes6.dex */
public abstract class v implements Iterator {
    public int h;
    public int i;
    public int j;
    public final /* synthetic */ z k;

    public /* synthetic */ v(z zVar, r rVar) {
        int i;
        this.k = zVar;
        i = zVar.zzf;
        this.h = i;
        this.i = zVar.zze();
        this.j = -1;
    }

    public abstract Object a(int i);

    public final void b() {
        int i;
        i = this.k.zzf;
        if (i != this.h) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.i >= 0;
    }

    @Override // java.util.Iterator
    public final Object next() {
        b();
        if (hasNext()) {
            int i = this.i;
            this.j = i;
            Object a2 = a(i);
            this.i = this.k.zzf(this.i);
            return a2;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        b();
        zzaa.zzd(this.j >= 0, "no calls to next() since the last call to remove()");
        this.h += 32;
        z zVar = this.k;
        zVar.remove(z.zzg(zVar, this.j));
        this.i--;
        this.j = -1;
    }
}
