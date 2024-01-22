package com.google.android.gms.internal.firebase_ml;

import java.util.NoSuchElementException;
/* loaded from: classes7.dex */
public final class y5 extends z5 {
    public int h = 0;
    public final int i;
    public final /* synthetic */ zzvv j;

    public y5(zzvv zzvvVar) {
        this.j = zzvvVar;
        this.i = zzvvVar.size();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h < this.i;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzwa
    public final byte nextByte() {
        int i = this.h;
        if (i < this.i) {
            this.h = i + 1;
            return this.j.zzcx(i);
        }
        throw new NoSuchElementException();
    }
}
