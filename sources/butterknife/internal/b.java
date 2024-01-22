package butterknife.internal;

import java.util.AbstractList;
import java.util.RandomAccess;
/* loaded from: classes.dex */
public final class b<T> extends AbstractList<T> implements RandomAccess {
    public final T[] h;

    public b(T[] tArr) {
        this.h = tArr;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object obj) {
        for (T t : this.h) {
            if (t == obj) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractList, java.util.List
    public T get(int i) {
        return this.h[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.h.length;
    }
}
