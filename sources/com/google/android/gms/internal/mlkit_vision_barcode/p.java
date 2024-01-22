package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;
/* loaded from: classes9.dex */
public final class p extends u0 {
    public final /* synthetic */ v i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(v vVar, Map map) {
        super(map);
        this.i = vVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        zzda.a(iterator());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        return this.h.keySet().containsAll(collection);
    }

    @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
    public final boolean equals(@CheckForNull Object obj) {
        return this == obj || this.h.keySet().equals(obj);
    }

    @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.h.keySet().hashCode();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.u0, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new o(this, this.h.entrySet().iterator());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(@CheckForNull Object obj) {
        int i;
        Collection collection = (Collection) this.h.remove(obj);
        if (collection != null) {
            int size = collection.size();
            collection.clear();
            v vVar = this.i;
            i = vVar.zzb;
            vVar.zzb = i - size;
            return size > 0;
        }
        return false;
    }
}
