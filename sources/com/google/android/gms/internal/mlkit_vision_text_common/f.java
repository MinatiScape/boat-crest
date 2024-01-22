package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;
/* loaded from: classes6.dex */
public final class f implements Iterator {
    @CheckForNull
    public Map.Entry h;
    public final /* synthetic */ Iterator i;
    public final /* synthetic */ g j;

    public f(g gVar, Iterator it) {
        this.j = gVar;
        this.i = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.i.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Map.Entry entry = (Map.Entry) this.i.next();
        this.h = entry;
        return entry.getKey();
    }

    @Override // java.util.Iterator
    public final void remove() {
        zzaa.zzd(this.h != null, "no calls to next() since the last call to remove()");
        Collection collection = (Collection) this.h.getValue();
        this.i.remove();
        m.zzg(this.j.i, collection.size());
        collection.clear();
        this.h = null;
    }
}
