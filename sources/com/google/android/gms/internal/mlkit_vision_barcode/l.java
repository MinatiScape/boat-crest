package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;
/* loaded from: classes9.dex */
public final class l implements Iterator {
    public final Iterator h;
    @CheckForNull
    public Collection i;
    public final /* synthetic */ m j;

    public l(m mVar) {
        this.j = mVar;
        this.h = mVar.j.entrySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        Map.Entry entry = (Map.Entry) this.h.next();
        this.i = (Collection) entry.getValue();
        m mVar = this.j;
        Object key = entry.getKey();
        return new o0(key, mVar.k.zzd(key, (Collection) entry.getValue()));
    }

    @Override // java.util.Iterator
    public final void remove() {
        int i;
        zzbc.zze(this.i != null, "no calls to next() since the last call to remove()");
        this.h.remove();
        v vVar = this.j.k;
        i = vVar.zzb;
        vVar.zzb = i - this.i.size();
        this.i.clear();
        this.i = null;
    }
}
