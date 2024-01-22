package com.google.android.gms.internal.fitness;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes8.dex */
public abstract class zzfd<E> extends AbstractCollection<E> implements Serializable {
    private static final Object[] zztg = new Object[0];

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public abstract boolean contains(@NullableDecl Object obj);

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @Deprecated
    public final boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final Object[] toArray() {
        return toArray(zztg);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    /* renamed from: zzae */
    public abstract zzfm<E> iterator();

    public zzfc<E> zzaf() {
        return isEmpty() ? zzfc.zzad() : zzfc.zza(toArray());
    }

    @NullableDecl
    public Object[] zzag() {
        return null;
    }

    public int zzah() {
        throw new UnsupportedOperationException();
    }

    public int zzai() {
        throw new UnsupportedOperationException();
    }

    public abstract boolean zzaj();

    public int zzb(Object[] objArr, int i) {
        zzfm zzfmVar = (zzfm) iterator();
        while (zzfmVar.hasNext()) {
            objArr[i] = zzfmVar.next();
            i++;
        }
        return i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final <T> T[] toArray(T[] tArr) {
        zzez.checkNotNull(tArr);
        int size = size();
        if (tArr.length < size) {
            Object[] zzag = zzag();
            if (zzag != null) {
                return (T[]) Arrays.copyOfRange(zzag, zzah(), zzai(), tArr.getClass());
            }
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size));
        } else if (tArr.length > size) {
            tArr[size] = null;
        }
        zzb(tArr, 0);
        return tArr;
    }
}
