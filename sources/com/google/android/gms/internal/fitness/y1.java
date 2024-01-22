package com.google.android.gms.internal.fitness;
/* loaded from: classes8.dex */
public abstract class y1 implements zzgc {
    @Override // java.util.Iterator
    public /* synthetic */ Byte next() {
        return Byte.valueOf(nextByte());
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
