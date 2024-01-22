package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes9.dex */
public abstract class g0 implements Iterator {
    public int h;
    public int i;
    public int j;
    public final /* synthetic */ k0 k;

    public /* synthetic */ g0(k0 k0Var, zzcg zzcgVar) {
        int i;
        this.k = k0Var;
        i = k0Var.zzf;
        this.h = i;
        this.i = k0Var.zze();
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
        zzbc.zze(this.j >= 0, "no calls to next() since the last call to remove()");
        this.h += 32;
        k0 k0Var = this.k;
        int i = this.j;
        Object[] objArr = k0Var.zzb;
        objArr.getClass();
        k0Var.remove(objArr[i]);
        this.i--;
        this.j = -1;
    }
}
