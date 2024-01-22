package com.google.android.gms.internal.auth;

import java.util.NoSuchElementException;
/* loaded from: classes6.dex */
public final class p0 extends r0 {
    public int h = 0;
    public final int i;
    public final /* synthetic */ zzee j;

    public p0(zzee zzeeVar) {
        this.j = zzeeVar;
        this.i = zzeeVar.zzd();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h < this.i;
    }

    @Override // com.google.android.gms.internal.auth.zzdz
    public final byte zza() {
        int i = this.h;
        if (i < this.i) {
            this.h = i + 1;
            return this.j.zzb(i);
        }
        throw new NoSuchElementException();
    }
}
