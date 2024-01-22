package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.NoSuchElementException;
/* loaded from: classes8.dex */
public final class h1 extends r {
    public final j1 h;
    public zzcw i = a();
    public final /* synthetic */ k1 j;

    public h1(k1 k1Var) {
        this.j = k1Var;
        this.h = new j1(k1Var, null);
    }

    public final zzcw a() {
        j1 j1Var = this.h;
        if (j1Var.hasNext()) {
            return j1Var.next().iterator();
        }
        return null;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.i != null;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcw
    public final byte zza() {
        zzcw zzcwVar = this.i;
        if (zzcwVar != null) {
            byte zza = zzcwVar.zza();
            if (!this.i.hasNext()) {
                this.i = a();
            }
            return zza;
        }
        throw new NoSuchElementException();
    }
}
