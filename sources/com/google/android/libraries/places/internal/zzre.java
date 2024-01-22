package com.google.android.libraries.places.internal;

import java.util.NoSuchElementException;
/* loaded from: classes10.dex */
final class zzre extends zzrg {
    private int zza = 0;
    private final int zzb;
    private final /* synthetic */ zzrb zzc;

    public zzre(zzrb zzrbVar) {
        this.zzc = zzrbVar;
        this.zzb = zzrbVar.zza();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza < this.zzb;
    }

    @Override // com.google.android.libraries.places.internal.zzrk
    public final byte zza() {
        int i = this.zza;
        if (i < this.zzb) {
            this.zza = i + 1;
            return this.zzc.zzb(i);
        }
        throw new NoSuchElementException();
    }
}
