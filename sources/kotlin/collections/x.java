package kotlin.collections;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class x<T> extends AbstractMutableList<T> {
    @NotNull
    public final List<T> h;

    public x(@NotNull List<T> delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.h = delegate;
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public void add(int i, T t) {
        int f;
        List<T> list = this.h;
        f = j.f(this, i);
        list.add(f, t);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        this.h.clear();
    }

    @Override // java.util.AbstractList, java.util.List
    public T get(int i) {
        int e;
        List<T> list = this.h;
        e = j.e(this, i);
        return list.get(e);
    }

    @Override // kotlin.collections.AbstractMutableList
    public int getSize() {
        return this.h.size();
    }

    @Override // kotlin.collections.AbstractMutableList
    public T removeAt(int i) {
        int e;
        List<T> list = this.h;
        e = j.e(this, i);
        return list.remove(e);
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public T set(int i, T t) {
        int e;
        List<T> list = this.h;
        e = j.e(this, i);
        return list.set(e, t);
    }
}
