package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;
/* loaded from: classes9.dex */
public abstract class n implements Iterator {
    public final Iterator h;
    @CheckForNull
    public Object i;
    @CheckForNull
    public Collection j;
    public Iterator k;
    public final /* synthetic */ v l;

    public n(v vVar) {
        Map map;
        this.l = vVar;
        map = vVar.zza;
        this.h = map.entrySet().iterator();
        this.i = null;
        this.j = null;
        this.k = r0.INSTANCE;
    }

    public abstract Object a(Object obj, Object obj2);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.h.hasNext() || this.k.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!this.k.hasNext()) {
            Map.Entry entry = (Map.Entry) this.h.next();
            this.i = entry.getKey();
            Collection collection = (Collection) entry.getValue();
            this.j = collection;
            this.k = collection.iterator();
        }
        return a(this.i, this.k.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        int i;
        this.k.remove();
        Collection collection = this.j;
        collection.getClass();
        if (collection.isEmpty()) {
            this.h.remove();
        }
        v vVar = this.l;
        i = vVar.zzb;
        vVar.zzb = i - 1;
    }
}
