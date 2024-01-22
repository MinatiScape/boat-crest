package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* JADX INFO: Add missing generic type declarations: [V, K] */
/* loaded from: classes7.dex */
public final class w1<K, V> implements Iterator<Map.Entry<K, V>> {
    public int h;
    public Iterator<Map.Entry<K, V>> i;
    public final /* synthetic */ u1 j;

    public w1(u1 u1Var) {
        List list;
        this.j = u1Var;
        list = u1Var.i;
        this.h = list.size();
    }

    public /* synthetic */ w1(u1 u1Var, v1 v1Var) {
        this(u1Var);
    }

    public final Iterator<Map.Entry<K, V>> a() {
        Map map;
        if (this.i == null) {
            map = this.j.m;
            this.i = map.entrySet().iterator();
        }
        return this.i;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        List list;
        int i = this.h;
        if (i > 0) {
            list = this.j.i;
            if (i <= list.size()) {
                return true;
            }
        }
        return a().hasNext();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        List list;
        Map.Entry<K, V> entry;
        if (a().hasNext()) {
            entry = a().next();
        } else {
            list = this.j.i;
            int i = this.h - 1;
            this.h = i;
            entry = (Map.Entry<K, V>) list.get(i);
        }
        return entry;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
