package com.google.android.recaptcha.internal;

import java.util.Iterator;
/* loaded from: classes10.dex */
final class zzka implements Iterator {
    public final Iterator zza;
    public final /* synthetic */ zzkb zzb;

    public zzka(zzkb zzkbVar) {
        zzhx zzhxVar;
        this.zzb = zzkbVar;
        zzhxVar = zzkbVar.zza;
        this.zza = zzhxVar.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.zza.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
