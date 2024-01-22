package com.google.android.gms.internal.fitness;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
/* loaded from: classes8.dex */
public final class zzjt extends AbstractList<String> implements zzhr, RandomAccess {
    public final zzhr h;

    public zzjt(zzhr zzhrVar) {
        this.h = zzhrVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        return (String) this.h.get(i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator<String> iterator() {
        return new p4(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator<String> listIterator(int i) {
        return new q4(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.h.size();
    }

    @Override // com.google.android.gms.internal.fitness.zzhr
    public final Object zzaf(int i) {
        return this.h.zzaf(i);
    }

    @Override // com.google.android.gms.internal.fitness.zzhr
    public final List<?> zzch() {
        return this.h.zzch();
    }

    @Override // com.google.android.gms.internal.fitness.zzhr
    public final zzhr zzci() {
        return this;
    }
}
