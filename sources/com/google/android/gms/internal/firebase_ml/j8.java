package com.google.android.gms.internal.firebase_ml;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* JADX INFO: Add missing generic type declarations: [V, K] */
/* loaded from: classes7.dex */
public final class j8<K, V> implements Iterator<Map.Entry<K, V>> {
    public int h;
    public Iterator<Map.Entry<K, V>> i;
    public final /* synthetic */ h8 j;

    public j8(h8 h8Var) {
        List list;
        this.j = h8Var;
        list = h8Var.i;
        this.h = list.size();
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
        if (!a().hasNext()) {
            list = this.j.i;
            int i = this.h - 1;
            this.h = i;
            return (Map.Entry) list.get(i);
        }
        return a().next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public /* synthetic */ j8(h8 h8Var, g8 g8Var) {
        this(h8Var);
    }
}
