package com.google.android.gms.internal.firebase_ml;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
/* loaded from: classes7.dex */
public final class zzaab extends AbstractList<String> implements zzxv, RandomAccess {
    public final zzxv h;

    public zzaab(zzxv zzxvVar) {
        this.h = zzxvVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        return (String) this.h.get(i);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzxv
    public final Object getRaw(int i) {
        return this.h.getRaw(i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator<String> iterator() {
        return new c(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator<String> listIterator(int i) {
        return new a(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.h.size();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzxv
    public final void zze(zzvv zzvvVar) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzxv
    public final List<?> zzvn() {
        return this.h.zzvn();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzxv
    public final zzxv zzvo() {
        return this;
    }
}
