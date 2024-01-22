package com.google.android.gms.internal.firebase_ml;

import java.util.NoSuchElementException;
/* loaded from: classes7.dex */
public final class b8 extends z5 {
    public final d8 h;
    public zzwa i = a();
    public final /* synthetic */ y7 j;

    public b8(y7 y7Var) {
        this.j = y7Var;
        this.h = new d8(y7Var, null);
    }

    public final zzwa a() {
        if (this.h.hasNext()) {
            return (zzwa) ((e6) this.h.next()).iterator();
        }
        return null;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.i != null;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzwa
    public final byte nextByte() {
        zzwa zzwaVar = this.i;
        if (zzwaVar != null) {
            byte nextByte = zzwaVar.nextByte();
            if (!this.i.hasNext()) {
                this.i = a();
            }
            return nextByte;
        }
        throw new NoSuchElementException();
    }
}
