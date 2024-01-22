package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes9.dex */
public class r implements Iterator {
    public final Iterator h;
    public final Collection i;
    public final /* synthetic */ s j;

    public r(s sVar) {
        Iterator it;
        this.j = sVar;
        Collection collection = sVar.i;
        this.i = collection;
        if (collection instanceof List) {
            it = ((List) collection).listIterator();
        } else {
            it = collection.iterator();
        }
        this.h = it;
    }

    public r(s sVar, Iterator it) {
        this.j = sVar;
        this.i = sVar.i;
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
        int i;
        this.h.remove();
        v vVar = this.j.l;
        i = vVar.zzb;
        vVar.zzb = i - 1;
        this.j.b();
    }
}
