package com.google.android.recaptcha.internal;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
@Deprecated
/* loaded from: classes10.dex */
public final class zzkb extends AbstractList implements RandomAccess, zzhx {
    private final zzhx zza;

    public zzkb(zzhx zzhxVar) {
        this.zza = zzhxVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        return ((zzhw) this.zza).get(i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new zzka(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        return new zzjz(this, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.recaptcha.internal.zzhx
    public final zzhx zze() {
        return this;
    }

    @Override // com.google.android.recaptcha.internal.zzhx
    public final Object zzf(int i) {
        return this.zza.zzf(i);
    }

    @Override // com.google.android.recaptcha.internal.zzhx
    public final List zzh() {
        return this.zza.zzh();
    }

    @Override // com.google.android.recaptcha.internal.zzhx
    public final void zzi(zzfi zzfiVar) {
        throw new UnsupportedOperationException();
    }
}
