package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes6.dex */
public final class y extends AbstractCollection {
    public final /* synthetic */ z h;

    public y(z zVar) {
        this.h = zVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.h.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        z zVar = this.h;
        Map zzl = zVar.zzl();
        if (zzl != null) {
            return zzl.values().iterator();
        }
        return new t(zVar);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.h.size();
    }
}
