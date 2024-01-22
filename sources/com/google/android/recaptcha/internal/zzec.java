package com.google.android.recaptcha.internal;

import java.util.Iterator;
/* loaded from: classes10.dex */
final class zzec implements Iterator {
    public boolean zza = true;
    public final /* synthetic */ Iterator zzb;

    public zzec(zzed zzedVar, Iterator it) {
        this.zzb = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object next = this.zzb.next();
        this.zza = false;
        return next;
    }

    @Override // java.util.Iterator
    public final void remove() {
        zzdr.zze(!this.zza, "no calls to next() since the last call to remove()");
        this.zzb.remove();
    }
}
