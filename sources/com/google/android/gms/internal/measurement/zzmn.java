package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
/* loaded from: classes8.dex */
public final class zzmn extends AbstractList<String> implements RandomAccess, zzko {
    public final zzko h;

    public zzmn(zzko zzkoVar) {
        this.h = zzkoVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        return ((zzkn) this.h).get(i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator<String> iterator() {
        return new o4(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator<String> listIterator(int i) {
        return new n4(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.h.size();
    }

    @Override // com.google.android.gms.internal.measurement.zzko
    public final zzko zze() {
        return this;
    }

    @Override // com.google.android.gms.internal.measurement.zzko
    public final Object zzf(int i) {
        return this.h.zzf(i);
    }

    @Override // com.google.android.gms.internal.measurement.zzko
    public final List<?> zzh() {
        return this.h.zzh();
    }

    @Override // com.google.android.gms.internal.measurement.zzko
    public final void zzi(zziy zziyVar) {
        throw new UnsupportedOperationException();
    }
}
