package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.NoSuchElementException;
/* loaded from: classes8.dex */
public final class p extends r {
    public int h = 0;
    public final int i;
    public final /* synthetic */ zzdb j;

    public p(zzdb zzdbVar) {
        this.j = zzdbVar;
        this.i = zzdbVar.zzd();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h < this.i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcw
    public final byte zza() {
        int i = this.h;
        if (i < this.i) {
            this.h = i + 1;
            return this.j.zzb(i);
        }
        throw new NoSuchElementException();
    }
}
