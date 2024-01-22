package com.google.android.gms.internal.vision;

import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public final class c0<K> extends zzdj<K> {
    private final transient zzdf<K> zzlq;
    private final transient zzdg<K, ?> zzma;

    public c0(zzdg<K, ?> zzdgVar, zzdf<K> zzdfVar) {
        this.zzma = zzdgVar;
        this.zzlq = zzdfVar;
    }

    @Override // com.google.android.gms.internal.vision.zzdc, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@NullableDecl Object obj) {
        return this.zzma.get(obj) != null;
    }

    @Override // com.google.android.gms.internal.vision.zzdj, com.google.android.gms.internal.vision.zzdc, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzma.size();
    }

    @Override // com.google.android.gms.internal.vision.zzdc
    public final int zza(Object[] objArr, int i) {
        return zzcc().zza(objArr, i);
    }

    @Override // com.google.android.gms.internal.vision.zzdc
    public final zzdr<K> zzby() {
        return (zzdr) zzcc().iterator();
    }

    @Override // com.google.android.gms.internal.vision.zzdj, com.google.android.gms.internal.vision.zzdc
    public final zzdf<K> zzcc() {
        return this.zzlq;
    }
}
