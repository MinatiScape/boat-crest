package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;
/* loaded from: classes9.dex */
public final class k extends t0 {
    public final /* synthetic */ m h;

    public k(m mVar) {
        this.h = mVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.t0
    public final Map a() {
        return this.h;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.t0, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        Set entrySet = this.h.j.entrySet();
        Objects.requireNonNull(entrySet);
        try {
            return entrySet.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new l(this.h);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(@CheckForNull Object obj) {
        if (contains(obj)) {
            Map.Entry entry = (Map.Entry) obj;
            entry.getClass();
            v.zzr(this.h.k, entry.getKey());
            return true;
        }
        return false;
    }
}
