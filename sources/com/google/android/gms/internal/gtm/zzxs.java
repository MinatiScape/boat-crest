package com.google.android.gms.internal.gtm;

import java.util.Iterator;
/* loaded from: classes8.dex */
public final class zzxs implements Iterator<String> {
    public final Iterator<String> zza;
    public final /* synthetic */ zzxt zzb;

    public zzxs(zzxt zzxtVar) {
        zzvs zzvsVar;
        this.zzb = zzxtVar;
        zzvsVar = zzxtVar.zza;
        this.zza = zzvsVar.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ String next() {
        return this.zza.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
