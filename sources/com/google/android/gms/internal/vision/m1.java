package com.google.android.gms.internal.vision;

import java.util.NoSuchElementException;
/* loaded from: classes10.dex */
public final class m1 extends o1 {
    public int h = 0;
    public final int i;
    public final /* synthetic */ zzfh j;

    public m1(zzfh zzfhVar) {
        this.j = zzfhVar;
        this.i = zzfhVar.size();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h < this.i;
    }

    @Override // com.google.android.gms.internal.vision.zzfq
    public final byte nextByte() {
        int i = this.h;
        if (i < this.i) {
            this.h = i + 1;
            return this.j.zzap(i);
        }
        throw new NoSuchElementException();
    }
}
