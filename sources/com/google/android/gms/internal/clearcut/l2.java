package com.google.android.gms.internal.clearcut;

import java.util.ListIterator;
/* loaded from: classes7.dex */
public final class l2 implements ListIterator<String> {
    public ListIterator<String> h;
    public final /* synthetic */ int i;
    public final /* synthetic */ zzfa j;

    public l2(zzfa zzfaVar, int i) {
        zzcx zzcxVar;
        this.j = zzfaVar;
        this.i = i;
        zzcxVar = zzfaVar.h;
        this.h = zzcxVar.listIterator(i);
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void add(String str) {
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
    public final /* synthetic */ Object next() {
        return this.h.next();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.h.nextIndex();
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ String previous() {
        return this.h.previous();
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
    public final /* synthetic */ void set(String str) {
        throw new UnsupportedOperationException();
    }
}
