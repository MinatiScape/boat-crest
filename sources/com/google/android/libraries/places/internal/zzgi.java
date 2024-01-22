package com.google.android.libraries.places.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public abstract class zzgi<E> extends zzgj<E> implements List<E>, RandomAccess {
    private static final zzhb<Object> zza = new zzgl(zzgt.zza, 0);

    public static <E> zzgi<E> zza() {
        return (zzgi<E>) zzgt.zza;
    }

    public static <E> zzgi<E> zzb(Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            return (zzgi<E>) zzgt.zza;
        }
        return new zzgt(objArr, length);
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

    @Override // com.google.android.libraries.places.internal.zzgj, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(@NullableDecl Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(@NullableDecl Object obj) {
        if (obj == zzft.zza(this)) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    for (int i = 0; i < size; i++) {
                        if (zzfn.zza(get(i), list.get(i))) {
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
                        if (!zzfn.zza(e, it.next())) {
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

    @Override // com.google.android.libraries.places.internal.zzgj, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
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
        zzft.zzb(i, size());
        if (isEmpty()) {
            return zza;
        }
        return new zzgl(this, i);
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

    @Override // com.google.android.libraries.places.internal.zzgj
    public final zzgi<E> zzc() {
        return this;
    }

    public static <E> zzgi<E> zza(E e) {
        Object[] objArr = {e};
        for (int i = 0; i <= 0; i++) {
            if (objArr[0] == null) {
                StringBuilder sb = new StringBuilder(20);
                sb.append("at index 0");
                throw new NullPointerException(sb.toString());
            }
        }
        return new zzgt(objArr, 1);
    }

    @Override // com.google.android.libraries.places.internal.zzgj
    public final zzgy<E> zzb() {
        return (zzhb) listIterator();
    }

    public static <E> zzgi<E> zza(E e, E e2) {
        Object[] objArr = {e, e2};
        for (int i = 0; i < 2; i++) {
            if (objArr[i] == null) {
                StringBuilder sb = new StringBuilder(20);
                sb.append("at index ");
                sb.append(i);
                throw new NullPointerException(sb.toString());
            }
        }
        return new zzgt(objArr, 2);
    }

    @Override // java.util.List
    public /* synthetic */ ListIterator listIterator() {
        return (zzhb) listIterator(0);
    }

    public static <E> zzgi<E> zza(Collection<? extends E> collection) {
        if (collection instanceof zzgj) {
            zzgi<E> zzc = ((zzgj) collection).zzc();
            if (zzc.zzg()) {
                Object[] array = zzc.toArray();
                int length = array.length;
                if (length == 0) {
                    return (zzgi<E>) zzgt.zza;
                }
                return new zzgt(array, length);
            }
            return zzc;
        }
        Object[] array2 = collection.toArray();
        int length2 = array2.length;
        for (int i = 0; i < length2; i++) {
            if (array2[i] == null) {
                StringBuilder sb = new StringBuilder(20);
                sb.append("at index ");
                sb.append(i);
                throw new NullPointerException(sb.toString());
            }
        }
        int length3 = array2.length;
        if (length3 == 0) {
            return (zzgi<E>) zzgt.zza;
        }
        return new zzgt(array2, length3);
    }

    public static <E> zzgi<E> zza(E[] eArr) {
        if (eArr.length == 0) {
            return (zzgi<E>) zzgt.zza;
        }
        Object[] objArr = (Object[]) eArr.clone();
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (objArr[i] == null) {
                StringBuilder sb = new StringBuilder(20);
                sb.append("at index ");
                sb.append(i);
                throw new NullPointerException(sb.toString());
            }
        }
        int length2 = objArr.length;
        if (length2 == 0) {
            return (zzgi<E>) zzgt.zza;
        }
        return new zzgt(objArr, length2);
    }

    public static <E> zzgi<E> zza(Comparator<? super E> comparator, Iterable<? extends E> iterable) {
        Collection collection;
        zzft.zza(comparator);
        if (iterable instanceof Collection) {
            collection = (Collection) iterable;
        } else {
            Iterator<? extends E> it = iterable.iterator();
            ArrayList arrayList = new ArrayList();
            zzft.zza(arrayList);
            zzft.zza(it);
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            collection = arrayList;
        }
        Object[] array = collection.toArray();
        int length = array.length;
        for (int i = 0; i < length; i++) {
            if (array[i] == null) {
                StringBuilder sb = new StringBuilder(20);
                sb.append("at index ");
                sb.append(i);
                throw new NullPointerException(sb.toString());
            }
        }
        Arrays.sort(array, comparator);
        int length2 = array.length;
        if (length2 == 0) {
            return (zzgi<E>) zzgt.zza;
        }
        return new zzgt(array, length2);
    }

    @Override // java.util.List
    /* renamed from: zza */
    public zzgi<E> subList(int i, int i2) {
        zzft.zza(i, i2, size());
        int i3 = i2 - i;
        if (i3 == size()) {
            return this;
        }
        if (i3 == 0) {
            return (zzgi<E>) zzgt.zza;
        }
        return new zzgk(this, i, i3);
    }

    @Override // com.google.android.libraries.places.internal.zzgj
    public int zza(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i + i2] = get(i2);
        }
        return i + size;
    }
}
