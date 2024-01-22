package com.google.android.libraries.places.internal;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
/* loaded from: classes10.dex */
public final class zzvb extends AbstractList<String> implements zzsz, RandomAccess {
    private final zzsz zza;

    public zzvb(zzsz zzszVar) {
        this.zza = zzszVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        return (String) this.zza.get(i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator<String> iterator() {
        return new zzvd(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator<String> listIterator(int i) {
        return new zzva(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.libraries.places.internal.zzsz
    public final Object zza(int i) {
        return this.zza.zza(i);
    }

    @Override // com.google.android.libraries.places.internal.zzsz
    public final List<?> zzd() {
        return this.zza.zzd();
    }

    @Override // com.google.android.libraries.places.internal.zzsz
    public final zzsz zze() {
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzsz
    public final void zza(zzrb zzrbVar) {
        throw new UnsupportedOperationException();
    }
}
