package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes9.dex */
public final class j0 extends AbstractCollection {
    public final /* synthetic */ k0 h;

    public j0(k0 k0Var) {
        this.h = k0Var;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.h.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        k0 k0Var = this.h;
        Map zzj = k0Var.zzj();
        if (zzj != null) {
            return zzj.values().iterator();
        }
        return new e0(k0Var);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.h.size();
    }
}
