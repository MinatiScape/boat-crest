package com.google.android.gms.internal.auth;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes6.dex */
public final class h2 implements Iterator {
    public int h = -1;
    public boolean i;
    public Iterator j;
    public final /* synthetic */ j2 k;

    public /* synthetic */ h2(j2 j2Var, zzgp zzgpVar) {
        this.k = j2Var;
    }

    public final Iterator a() {
        Map map;
        if (this.j == null) {
            map = this.k.j;
            this.j = map.entrySet().iterator();
        }
        return this.j;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        List list;
        Map map;
        int i = this.h + 1;
        list = this.k.i;
        if (i >= list.size()) {
            map = this.k.j;
            return !map.isEmpty() && a().hasNext();
        }
        return true;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        List list;
        List list2;
        this.i = true;
        int i = this.h + 1;
        this.h = i;
        list = this.k.i;
        if (i < list.size()) {
            list2 = this.k.i;
            return (Map.Entry) list2.get(this.h);
        }
        return (Map.Entry) a().next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        List list;
        if (this.i) {
            this.i = false;
            this.k.o();
            int i = this.h;
            list = this.k.i;
            if (i < list.size()) {
                j2 j2Var = this.k;
                int i2 = this.h;
                this.h = i2 - 1;
                j2Var.m(i2);
                return;
            }
            a().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}
