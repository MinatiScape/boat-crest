package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;
/* loaded from: classes6.dex */
public final class c extends l0 {
    public final /* synthetic */ e h;

    public c(e eVar) {
        this.h = eVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.l0
    public final Map a() {
        return this.h;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.l0, java.util.AbstractCollection, java.util.Collection, java.util.Set
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
        return new d(this.h);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(@CheckForNull Object obj) {
        if (contains(obj)) {
            Map.Entry entry = (Map.Entry) obj;
            entry.getClass();
            m.zzm(this.h.k, entry.getKey());
            return true;
        }
        return false;
    }
}
