package com.google.android.gms.internal.auth;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
/* loaded from: classes6.dex */
public final class zzhd extends AbstractList implements RandomAccess, zzfe {
    public final zzfe h;

    public zzhd(zzfe zzfeVar) {
        this.h = zzfeVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        return ((zzfd) this.h).get(i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new p2(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        return new o2(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.h.size();
    }

    @Override // com.google.android.gms.internal.auth.zzfe
    public final zzfe zze() {
        return this;
    }

    @Override // com.google.android.gms.internal.auth.zzfe
    public final List zzg() {
        return this.h.zzg();
    }
}
