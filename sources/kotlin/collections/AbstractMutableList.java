package kotlin.collections;

import java.util.List;
import kotlin.SinceKotlin;
@SinceKotlin(version = "1.1")
/* loaded from: classes12.dex */
public abstract class AbstractMutableList<E> extends java.util.AbstractList<E> implements List<E> {
    @Override // java.util.AbstractList, java.util.List
    public abstract void add(int i, E e);

    public abstract int getSize();

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ E remove(int i) {
        return removeAt(i);
    }

    public abstract E removeAt(int i);

    @Override // java.util.AbstractList, java.util.List
    public abstract E set(int i, E e);

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }
}
