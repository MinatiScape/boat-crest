package com.google.android.gms.internal.firebase_ml;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes7.dex */
public final class d8 implements Iterator<e6> {
    public final ArrayDeque<y7> h;
    public e6 i;

    public d8(zzvv zzvvVar) {
        zzvv zzvvVar2;
        if (zzvvVar instanceof y7) {
            y7 y7Var = (y7) zzvvVar;
            ArrayDeque<y7> arrayDeque = new ArrayDeque<>(y7Var.zztr());
            this.h = arrayDeque;
            arrayDeque.push(y7Var);
            zzvvVar2 = y7Var.zzcoz;
            this.i = a(zzvvVar2);
            return;
        }
        this.h = null;
        this.i = (e6) zzvvVar;
    }

    public final e6 a(zzvv zzvvVar) {
        while (zzvvVar instanceof y7) {
            y7 y7Var = (y7) zzvvVar;
            this.h.push(y7Var);
            zzvvVar = y7Var.zzcoz;
        }
        return (e6) zzvvVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.i != null;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ e6 next() {
        e6 e6Var;
        zzvv zzvvVar;
        boolean z;
        e6 e6Var2 = this.i;
        if (e6Var2 != null) {
            do {
                ArrayDeque<y7> arrayDeque = this.h;
                if (arrayDeque == null || arrayDeque.isEmpty()) {
                    e6Var = null;
                    break;
                }
                zzvvVar = this.h.pop().zzcpa;
                e6Var = a(zzvvVar);
                if (e6Var.size() == 0) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
            } while (z);
            this.i = e6Var;
            return e6Var2;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public /* synthetic */ d8(zzvv zzvvVar, b8 b8Var) {
        this(zzvvVar);
    }
}
