package com.google.android.libraries.places.internal;

import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
final class zzgx<K> extends zzgp<K> {
    private final transient zzgn<K, ?> zza;
    private final transient zzgi<K> zzb;

    public zzgx(zzgn<K, ?> zzgnVar, zzgi<K> zzgiVar) {
        this.zza = zzgnVar;
        this.zzb = zzgiVar;
    }

    @Override // com.google.android.libraries.places.internal.zzgj, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(@NullableDecl Object obj) {
        return this.zza.get(obj) != null;
    }

    @Override // com.google.android.libraries.places.internal.zzgp, com.google.android.libraries.places.internal.zzgj, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.libraries.places.internal.zzgj
    public final int zza(Object[] objArr, int i) {
        return zzc().zza(objArr, i);
    }

    @Override // com.google.android.libraries.places.internal.zzgj
    public final zzgy<K> zzb() {
        return (zzgy) zzc().iterator();
    }

    @Override // com.google.android.libraries.places.internal.zzgp, com.google.android.libraries.places.internal.zzgj
    public final zzgi<K> zzc() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.internal.zzgj
    public final boolean zzg() {
        return true;
    }
}
