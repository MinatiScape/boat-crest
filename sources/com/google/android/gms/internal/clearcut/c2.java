package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* JADX INFO: Add missing generic type declarations: [V, K] */
/* loaded from: classes7.dex */
public final class c2<K, V> implements Iterator<Map.Entry<K, V>> {
    public int h;
    public boolean i;
    public Iterator<Map.Entry<K, V>> j;
    public final /* synthetic */ u1 k;

    public c2(u1 u1Var) {
        this.k = u1Var;
        this.h = -1;
    }

    public /* synthetic */ c2(u1 u1Var, v1 v1Var) {
        this(u1Var);
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
        Map.Entry<K, V> next;
        List list2;
        this.i = true;
        int i = this.h + 1;
        this.h = i;
        list = this.k.i;
        if (i < list.size()) {
            list2 = this.k.i;
            next = (Map.Entry<K, V>) list2.get(this.h);
        } else {
            next = a().next();
        }
        return next;
    }

    @Override // java.util.Iterator
    public final void remove() {
        List list;
        if (!this.i) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.i = false;
        this.k.p();
        int i = this.h;
        list = this.k.i;
        if (i >= list.size()) {
            a().remove();
            return;
        }
        u1 u1Var = this.k;
        int i2 = this.h;
        this.h = i2 - 1;
        u1Var.i(i2);
    }
}
