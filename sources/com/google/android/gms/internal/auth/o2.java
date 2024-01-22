package com.google.android.gms.internal.auth;

import java.util.ListIterator;
/* loaded from: classes6.dex */
public final class o2 implements ListIterator {
    public final ListIterator h;
    public final /* synthetic */ int i;
    public final /* synthetic */ zzhd j;

    public o2(zzhd zzhdVar, int i) {
        zzfe zzfeVar;
        this.j = zzhdVar;
        this.i = i;
        zzfeVar = zzhdVar.h;
        this.h = zzfeVar.listIterator(i);
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void add(Object obj) {
        String str = (String) obj;
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.h.hasNext();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.h.hasPrevious();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.h.next();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.h.nextIndex();
    }

    @Override // java.util.ListIterator
    public final /* bridge */ /* synthetic */ Object previous() {
        return (String) this.h.previous();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.h.previousIndex();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void set(Object obj) {
        String str = (String) obj;
        throw new UnsupportedOperationException();
    }
}
