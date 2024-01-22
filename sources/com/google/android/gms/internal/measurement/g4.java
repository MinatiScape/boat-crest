package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes8.dex */
public final class g4 implements Iterator<Map.Entry> {
    public int h = -1;
    public boolean i;
    public Iterator<Map.Entry> j;
    public final /* synthetic */ i4 k;

    public /* synthetic */ g4(i4 i4Var, zzlz zzlzVar) {
        this.k = i4Var;
    }

    public final Iterator<Map.Entry> a() {
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
    public final /* bridge */ /* synthetic */ Map.Entry next() {
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
        return a().next();
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
                i4 i4Var = this.k;
                int i2 = this.h;
                this.h = i2 - 1;
                i4Var.m(i2);
                return;
            }
            a().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}
