package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;
/* loaded from: classes6.dex */
public final class d implements Iterator {
    public final Iterator h;
    @CheckForNull
    public Collection i;
    public final /* synthetic */ e j;

    public d(e eVar) {
        this.j = eVar;
        this.h = eVar.j.entrySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        Map.Entry entry = (Map.Entry) this.h.next();
        this.i = (Collection) entry.getValue();
        e eVar = this.j;
        Object key = entry.getKey();
        return new d0(key, eVar.k.zzb(key, (Collection) entry.getValue()));
    }

    @Override // java.util.Iterator
    public final void remove() {
        zzaa.zzd(this.i != null, "no calls to next() since the last call to remove()");
        this.h.remove();
        m.zzg(this.j.k, this.i.size());
        this.i.clear();
        this.i = null;
    }
}
