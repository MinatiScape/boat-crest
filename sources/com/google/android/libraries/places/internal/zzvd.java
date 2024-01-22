package com.google.android.libraries.places.internal;

import java.util.Iterator;
/* loaded from: classes10.dex */
final class zzvd implements Iterator<String> {
    private Iterator<String> zza;
    private final /* synthetic */ zzvb zzb;

    public zzvd(zzvb zzvbVar) {
        zzsz zzszVar;
        this.zzb = zzvbVar;
        zzszVar = zzvbVar.zza;
        this.zza = zzszVar.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zza.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
