package com.google.android.gms.internal.firebase_ml;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
/* loaded from: classes7.dex */
public final class e1 implements Iterator<Map.Entry<String, Object>> {
    public int h = -1;
    public zzjd i;
    public Object j;
    public boolean k;
    public boolean l;
    public zzjd m;
    public final /* synthetic */ c1 n;

    public e1(c1 c1Var) {
        this.n = c1Var;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (!this.l) {
            this.l = true;
            this.j = null;
            while (this.j == null) {
                int i = this.h + 1;
                this.h = i;
                if (i >= this.n.i.d.size()) {
                    break;
                }
                zziv zzivVar = this.n.i;
                zzjd zzao = zzivVar.zzao(zzivVar.d.get(this.h));
                this.i = zzao;
                this.j = zzao.zzh(this.n.h);
            }
        }
        return this.j != null;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Map.Entry<String, Object> next() {
        if (hasNext()) {
            zzjd zzjdVar = this.i;
            this.m = zzjdVar;
            Object obj = this.j;
            this.l = false;
            this.k = false;
            this.i = null;
            this.j = null;
            return new b1(this.n, zzjdVar, obj);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        zzml.checkState((this.m == null || this.k) ? false : true);
        this.k = true;
        this.m.zzb(this.n.h, null);
    }
}
