package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes8.dex */
public final class j1 implements Iterator {
    public final ArrayDeque h;
    public t i;

    public /* synthetic */ j1(zzdb zzdbVar, zzgd zzgdVar) {
        zzdb zzdbVar2;
        if (zzdbVar instanceof k1) {
            k1 k1Var = (k1) zzdbVar;
            ArrayDeque arrayDeque = new ArrayDeque(k1Var.zzf());
            this.h = arrayDeque;
            arrayDeque.push(k1Var);
            zzdbVar2 = k1Var.zzd;
            this.i = b(zzdbVar2);
            return;
        }
        this.h = null;
        this.i = (t) zzdbVar;
    }

    @Override // java.util.Iterator
    /* renamed from: a */
    public final t next() {
        t tVar;
        zzdb zzdbVar;
        t tVar2 = this.i;
        if (tVar2 != null) {
            do {
                ArrayDeque arrayDeque = this.h;
                tVar = null;
                if (arrayDeque == null || arrayDeque.isEmpty()) {
                    break;
                }
                zzdbVar = ((k1) this.h.pop()).zze;
                tVar = b(zzdbVar);
            } while (tVar.zzd() == 0);
            this.i = tVar;
            return tVar2;
        }
        throw new NoSuchElementException();
    }

    public final t b(zzdb zzdbVar) {
        while (zzdbVar instanceof k1) {
            k1 k1Var = (k1) zzdbVar;
            this.h.push(k1Var);
            zzdbVar = k1Var.zzd;
        }
        return (t) zzdbVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.i != null;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
