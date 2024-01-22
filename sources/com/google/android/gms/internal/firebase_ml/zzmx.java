package com.google.android.gms.internal.firebase_ml;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes7.dex */
public abstract class zzmx<E> extends AbstractCollection<E> implements Serializable {
    private static final Object[] zzalq = new Object[0];

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
        return toArray(zzalq);
    }

    int zza(Object[] objArr, int i) {
        zznd zzndVar = (zznd) iterator();
        while (zzndVar.hasNext()) {
            objArr[i] = zzndVar.next();
            i++;
        }
        return i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    /* renamed from: zzjj */
    public abstract zznd<E> iterator();

    public zzmw<E> zzjk() {
        return isEmpty() ? zzmw.zzji() : zzmw.zza(toArray());
    }

    @NullableDecl
    public Object[] zzjl() {
        return null;
    }

    public int zzjm() {
        throw new UnsupportedOperationException();
    }

    public int zzjn() {
        throw new UnsupportedOperationException();
    }

    public abstract boolean zzjo();

    @Override // java.util.AbstractCollection, java.util.Collection
    public final <T> T[] toArray(T[] tArr) {
        zzml.checkNotNull(tArr);
        int size = size();
        if (tArr.length < size) {
            Object[] zzjl = zzjl();
            if (zzjl != null) {
                return (T[]) Arrays.copyOfRange(zzjl, zzjm(), zzjn(), tArr.getClass());
            }
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size));
        } else if (tArr.length > size) {
            tArr[size] = null;
        }
        zza(tArr, 0);
        return tArr;
    }
}
