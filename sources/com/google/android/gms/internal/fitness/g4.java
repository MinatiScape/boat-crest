package com.google.android.gms.internal.fitness;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* JADX INFO: Add missing generic type declarations: [V, K] */
/* loaded from: classes8.dex */
public final class g4<K, V> implements Iterator<Map.Entry<K, V>> {
    public int h;
    public boolean i;
    public Iterator<Map.Entry<K, V>> j;
    public final /* synthetic */ y3 k;

    public g4(y3 y3Var) {
        this.k = y3Var;
        this.h = -1;
    }

    public final Iterator<Map.Entry<K, V>> a() {
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
            if (map.isEmpty() || !a().hasNext()) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
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
            this.k.q();
            int i = this.h;
            list = this.k.i;
            if (i < list.size()) {
                y3 y3Var = this.k;
                int i2 = this.h;
                this.h = i2 - 1;
                y3Var.i(i2);
                return;
            }
            a().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }

    public /* synthetic */ g4(y3 y3Var, b4 b4Var) {
        this(y3Var);
    }
}
