package com.google.android.gms.internal.fitness;

import java.util.ListIterator;
/* loaded from: classes8.dex */
public final class q4 implements ListIterator<String> {
    public ListIterator<String> h;
    public final /* synthetic */ int i;
    public final /* synthetic */ zzjt j;

    public q4(zzjt zzjtVar, int i) {
        zzhr zzhrVar;
        this.j = zzjtVar;
        this.i = i;
        zzhrVar = zzjtVar.h;
        this.h = zzhrVar.listIterator(i);
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
