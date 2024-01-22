package com.google.android.gms.internal.measurement;

import java.util.ListIterator;
/* loaded from: classes8.dex */
public final class n4 implements ListIterator<String> {
    public final ListIterator<String> h;
    public final /* synthetic */ int i;
    public final /* synthetic */ zzmn j;

    public n4(zzmn zzmnVar, int i) {
        zzko zzkoVar;
        this.j = zzmnVar;
        this.i = i;
        zzkoVar = zzmnVar.h;
        this.h = zzkoVar.listIterator(i);
    }

    @Override // java.util.ListIterator
    public final /* bridge */ /* synthetic */ void add(String str) {
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
        return this.h.next();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.h.nextIndex();
    }

    @Override // java.util.ListIterator
    public final /* bridge */ /* synthetic */ String previous() {
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
    public final /* bridge */ /* synthetic */ void set(String str) {
        throw new UnsupportedOperationException();
    }
}
