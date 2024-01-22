package com.google.android.gms.internal.fitness;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes8.dex */
public abstract class zzfc<E> extends zzfd<E> implements List<E>, RandomAccess {
    private static final zzfp<Object> zztf = new q1(s1.zztm, 0);

    public static <E> zzfc<E> zza(Collection<? extends E> collection) {
        if (collection instanceof zzfd) {
            zzfc<E> zzaf = ((zzfd) collection).zzaf();
            if (zzaf.zzaj()) {
                Object[] array = zzaf.toArray();
                return zza(array, array.length);
            }
            return zzaf;
        }
        Object[] array2 = collection.toArray();
        Object[] b = zzfj.b(array2, array2.length);
        return zza(b, b.length);
    }

    public static <E> zzfc<E> zzad() {
        return (zzfc<E>) s1.zztm;
    }

    public static <E> zzfc<E> zzb(E e) {
        Object[] b = zzfj.b(new Object[]{e}, 1);
        return zza(b, b.length);
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.fitness.zzfd, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(@NullableDecl Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(@NullableDecl Object obj) {
        if (obj == zzez.checkNotNull(this)) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    for (int i = 0; i < size; i++) {
                        if (zzew.equal(get(i), list.get(i))) {
                        }
                    }
                    return true;
                }
                int size2 = size();
                Iterator<E> it = list.iterator();
                int i2 = 0;
                while (true) {
                    if (i2 < size2) {
                        if (!it.hasNext()) {
                            break;
                        }
                        E e = get(i2);
                        i2++;
                        if (!zzew.equal(e, it.next())) {
                            break;
                        }
                    } else if (!it.hasNext()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = ~(~((i * 31) + get(i2).hashCode()));
        }
        return i;
    }

    @Override // java.util.List
    public int indexOf(@NullableDecl Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (obj.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.fitness.zzfd, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public /* synthetic */ Iterator iterator() {
        return iterator();
    }

    @Override // java.util.List
    public int lastIndexOf(@NullableDecl Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public /* synthetic */ ListIterator listIterator(int i) {
        zzez.zzb(i, size());
        if (isEmpty()) {
            return zztf;
        }
        return new q1(this, i);
    }

    @Override // java.util.List
    @Deprecated
    public final E remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final E set(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.fitness.zzfd
    public final zzfm<E> zzae() {
        return (zzfp) listIterator();
    }

    @Override // com.google.android.gms.internal.fitness.zzfd
    public final zzfc<E> zzaf() {
        return this;
    }

    @Override // java.util.List
    /* renamed from: zzc */
    public zzfc<E> subList(int i, int i2) {
        zzez.zza(i, i2, size());
        int i3 = i2 - i;
        if (i3 == size()) {
            return this;
        }
        if (i3 == 0) {
            return (zzfc<E>) s1.zztm;
        }
        return new p1(this, i, i3);
    }

    @Override // com.google.android.gms.internal.fitness.zzfd
    public int zzb(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i2] = get(i2);
        }
        return size;
    }

    @Override // java.util.List
    public /* synthetic */ ListIterator listIterator() {
        return (zzfp) listIterator(0);
    }

    public static <E> zzfc<E> zza(Object[] objArr) {
        return zza(objArr, objArr.length);
    }

    public static <E> zzfc<E> zza(Object[] objArr, int i) {
        if (i == 0) {
            return (zzfc<E>) s1.zztm;
        }
        return new s1(objArr, i);
    }
}
