package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;
/* loaded from: classes9.dex */
public final class o implements Iterator {
    @CheckForNull
    public Map.Entry h;
    public final /* synthetic */ Iterator i;
    public final /* synthetic */ p j;

    public o(p pVar, Iterator it) {
        this.j = pVar;
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
        int i;
        zzbc.zze(this.h != null, "no calls to next() since the last call to remove()");
        Collection collection = (Collection) this.h.getValue();
        this.i.remove();
        v vVar = this.j.i;
        i = vVar.zzb;
        vVar.zzb = i - collection.size();
        collection.clear();
        this.h = null;
    }
}
