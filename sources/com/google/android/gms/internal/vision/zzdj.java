package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public abstract class zzdj<E> extends zzdc<E> implements Set<E> {
    @NullableDecl
    private transient zzdf<E> zzlz;

    @Override // java.util.Collection, java.util.Set
    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        return zzdo.b(this, obj);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return zzdo.a(this);
    }

    @Override // com.google.android.gms.internal.vision.zzdc, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public /* synthetic */ Iterator iterator() {
        return iterator();
    }

    @Override // com.google.android.gms.internal.vision.zzdc
    public zzdf<E> zzcc() {
        zzdf<E> zzdfVar = this.zzlz;
        if (zzdfVar == null) {
            zzdf<E> zzch = zzch();
            this.zzlz = zzch;
            return zzch;
        }
        return zzdfVar;
    }

    public zzdf<E> zzch() {
        return zzdf.zza(toArray());
    }
}
