package com.google.android.gms.internal.firebase_ml;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes7.dex */
public final class d1 extends AbstractSet<Map.Entry<String, Object>> {
    public final /* synthetic */ c1 h;

    public d1(c1 c1Var) {
        this.h = c1Var;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        for (String str : this.h.i.d) {
            this.h.i.zzao(str).zzb(this.h.h, null);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        for (String str : this.h.i.d) {
            if (this.h.i.zzao(str).zzh(this.h.h) != null) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return new e1(this.h);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        int i = 0;
        for (String str : this.h.i.d) {
            if (this.h.i.zzao(str).zzh(this.h.h) != null) {
                i++;
            }
        }
        return i;
    }
}
