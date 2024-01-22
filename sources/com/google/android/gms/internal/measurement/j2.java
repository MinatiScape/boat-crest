package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;
/* loaded from: classes8.dex */
public final class j2 extends l2 {
    public int h = 0;
    public final int i;
    public final /* synthetic */ zziy j;

    public j2(zziy zziyVar) {
        this.j = zziyVar;
        this.i = zziyVar.zzd();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h < this.i;
    }

    @Override // com.google.android.gms.internal.measurement.zzit
    public final byte zza() {
        int i = this.h;
        if (i < this.i) {
            this.h = i + 1;
            return this.j.zzb(i);
        }
        throw new NoSuchElementException();
    }
}
