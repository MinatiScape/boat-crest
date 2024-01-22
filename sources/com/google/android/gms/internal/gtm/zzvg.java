package com.google.android.gms.internal.gtm;

import java.util.AbstractList;
import java.util.List;
/* loaded from: classes8.dex */
public final class zzvg<F, T> extends AbstractList<T> {
    public final List<F> zza;
    public final zzvf<F, T> zzb;

    public zzvg(List<F> list, zzvf<F, T> zzvfVar) {
        this.zza = list;
        this.zzb = zzvfVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final T get(int i) {
        return this.zzb.zzb(this.zza.get(i));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }
}
