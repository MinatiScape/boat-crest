package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;
/* loaded from: classes9.dex */
public final class h0 extends AbstractSet {
    public final /* synthetic */ k0 h;

    public h0(k0 k0Var) {
        this.h = k0Var;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.h.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        return this.h.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        k0 k0Var = this.h;
        Map zzj = k0Var.zzj();
        if (zzj != null) {
            return zzj.keySet().iterator();
        }
        return new c0(k0Var);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(@CheckForNull Object obj) {
        Object zzs;
        Object obj2;
        Map zzj = this.h.zzj();
        if (zzj != null) {
            return zzj.keySet().remove(obj);
        }
        zzs = this.h.zzs(obj);
        obj2 = k0.zzd;
        return zzs != obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.h.size();
    }
}
