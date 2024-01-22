package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes8.dex */
public final class b implements Iterator<zzap> {
    public int h = 0;
    public final /* synthetic */ zzae i;

    public b(zzae zzaeVar) {
        this.i = zzaeVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h < this.i.zzc();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ zzap next() {
        if (this.h < this.i.zzc()) {
            zzae zzaeVar = this.i;
            int i = this.h;
            this.h = i + 1;
            return zzaeVar.zze(i);
        }
        int i2 = this.h;
        StringBuilder sb = new StringBuilder(32);
        sb.append("Out of bounds index: ");
        sb.append(i2);
        throw new NoSuchElementException(sb.toString());
    }
}
