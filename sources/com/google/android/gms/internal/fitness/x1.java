package com.google.android.gms.internal.fitness;

import java.util.NoSuchElementException;
/* loaded from: classes8.dex */
public final class x1 extends y1 {
    public int h = 0;
    public final int i;
    public final /* synthetic */ zzfx j;

    public x1(zzfx zzfxVar) {
        this.j = zzfxVar;
        this.i = zzfxVar.size();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h < this.i;
    }

    @Override // com.google.android.gms.internal.fitness.zzgc
    public final byte nextByte() {
        int i = this.h;
        if (i < this.i) {
            this.h = i + 1;
            return this.j.zzk(i);
        }
        throw new NoSuchElementException();
    }
}
