package com.google.android.libraries.places.internal;

import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public abstract class zzgp<E> extends zzgj<E> implements Set<E> {
    @NullableDecl
    private transient zzgi<E> zza;

    @Override // java.util.Collection, java.util.Set
    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        return zzgz.zza(this, obj);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return zzgz.zza(this);
    }

    @Override // com.google.android.libraries.places.internal.zzgj, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public /* synthetic */ Iterator iterator() {
        return iterator();
    }

    public zzgi<E> zza() {
        return zzgi.zzb(toArray());
    }

    @Override // com.google.android.libraries.places.internal.zzgj
    public zzgi<E> zzc() {
        zzgi<E> zzgiVar = this.zza;
        if (zzgiVar == null) {
            zzgi<E> zza = zza();
            this.zza = zza;
            return zza;
        }
        return zzgiVar;
    }
}
