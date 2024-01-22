package com.google.android.gms.internal.vision;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
/* loaded from: classes10.dex */
public final class zzjo extends AbstractList<String> implements zzhj, RandomAccess {
    public final zzhj h;

    public zzjo(zzhj zzhjVar) {
        this.h = zzhjVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        return (String) this.h.get(i);
    }

    @Override // com.google.android.gms.internal.vision.zzhj
    public final Object getRaw(int i) {
        return this.h.getRaw(i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator<String> iterator() {
        return new k4(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator<String> listIterator(int i) {
        return new i4(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.h.size();
    }

    @Override // com.google.android.gms.internal.vision.zzhj
    public final void zzc(zzfh zzfhVar) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.vision.zzhj
    public final List<?> zzgx() {
        return this.h.zzgx();
    }

    @Override // com.google.android.gms.internal.vision.zzhj
    public final zzhj zzgy() {
        return this;
    }
}
