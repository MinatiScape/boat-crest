package org.bouncycastle.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
/* loaded from: classes13.dex */
public class CollectionStore<T> implements Store<T>, Iterable<T> {
    public Collection<T> h;

    public CollectionStore(Collection<T> collection) {
        this.h = new ArrayList(collection);
    }

    @Override // org.bouncycastle.util.Store
    public Collection<T> getMatches(Selector<T> selector) {
        if (selector == null) {
            return new ArrayList(this.h);
        }
        ArrayList arrayList = new ArrayList();
        for (T t : this.h) {
            if (selector.match(t)) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    @Override // org.bouncycastle.util.Iterable, java.lang.Iterable
    public Iterator<T> iterator() {
        return getMatches(null).iterator();
    }
}
