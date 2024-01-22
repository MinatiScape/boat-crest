package com.google.android.gms.internal.clearcut;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes7.dex */
public final class n1<E> extends l<E> {
    public static final n1<Object> j;
    public final List<E> i;

    static {
        n1<Object> n1Var = new n1<>();
        j = n1Var;
        n1Var.zzv();
    }

    public n1() {
        this(new ArrayList(10));
    }

    public n1(List<E> list) {
        this.i = list;
    }

    public static <E> n1<E> a() {
        return (n1<E>) j;
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.List
    public final void add(int i, E e) {
        zzw();
        this.i.add(i, e);
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final E get(int i) {
        return this.i.get(i);
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.List
    public final E remove(int i) {
        zzw();
        E remove = this.i.remove(i);
        ((AbstractList) this).modCount++;
        return remove;
    }

    @Override // com.google.android.gms.internal.clearcut.l, java.util.AbstractList, java.util.List
    public final E set(int i, E e) {
        zzw();
        E e2 = this.i.set(i, e);
        ((AbstractList) this).modCount++;
        return e2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.i.size();
    }

    @Override // com.google.android.gms.internal.clearcut.zzcn
    public final /* synthetic */ zzcn zzi(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.i);
            return new n1(arrayList);
        }
        throw new IllegalArgumentException();
    }
}
