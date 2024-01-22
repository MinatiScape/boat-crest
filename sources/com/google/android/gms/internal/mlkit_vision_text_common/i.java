package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes6.dex */
public class i implements Iterator {
    public final Iterator h;
    public final Collection i;
    public final /* synthetic */ j j;

    public i(j jVar) {
        Iterator it;
        this.j = jVar;
        Collection collection = jVar.i;
        this.i = collection;
        if (collection instanceof List) {
            it = ((List) collection).listIterator();
        } else {
            it = collection.iterator();
        }
        this.h = it;
    }

    public i(j jVar, Iterator it) {
        this.j = jVar;
        this.i = jVar.i;
        this.h = it;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a() {
        this.j.zzb();
        if (this.j.i != this.i) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        a();
        return this.h.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        a();
        return this.h.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.h.remove();
        m.zze(this.j.l);
        this.j.b();
    }
}
