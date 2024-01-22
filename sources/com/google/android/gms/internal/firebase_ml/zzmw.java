package com.google.android.gms.internal.firebase_ml;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes7.dex */
public abstract class zzmw<E> extends zzmx<E> implements List<E>, RandomAccess {
    private static final zznc<Object> zzalp = new e2(f2.zzalt, 0);

    public static <E> zzmw<E> zza(E e, E e2, E e3) {
        Object[] objArr = {e, e2, e3};
        for (int i = 0; i < 3; i++) {
            if (objArr[i] == null) {
                StringBuilder sb = new StringBuilder(20);
                sb.append("at index ");
                sb.append(i);
                throw new NullPointerException(sb.toString());
            }
        }
        return new f2(objArr, 3);
    }

    public static <E> zzmw<E> zzb(Collection<? extends E> collection) {
        if (collection instanceof zzmx) {
            zzmw<E> zzjk = ((zzmx) collection).zzjk();
            if (zzjk.zzjo()) {
                Object[] array = zzjk.toArray();
                int length = array.length;
                if (length == 0) {
                    return (zzmw<E>) f2.zzalt;
                }
                return new f2(array, length);
            }
            return zzjk;
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
            return (zzmw<E>) f2.zzalt;
        }
        return new f2(array2, length3);
    }

    public static <E> zzmw<E> zzji() {
        return (zzmw<E>) f2.zzalt;
    }

    public static <E> zzmw<E> zzl(E e) {
        Object[] objArr = {e};
        for (int i = 0; i <= 0; i++) {
            if (objArr[0] == null) {
                StringBuilder sb = new StringBuilder(20);
                sb.append("at index 0");
                throw new NullPointerException(sb.toString());
            }
        }
        return new f2(objArr, 1);
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

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(@NullableDecl Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(@NullableDecl Object obj) {
        if (obj == zzml.checkNotNull(this)) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    for (int i = 0; i < size; i++) {
                        if (zzmf.equal(get(i), list.get(i))) {
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
                        if (!zzmf.equal(e, it.next())) {
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

    @Override // com.google.android.gms.internal.firebase_ml.zzmx, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
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
        zzml.zzc(i, size());
        if (isEmpty()) {
            return zzalp;
        }
        return new e2(this, i);
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

    @Override // java.util.List
    /* renamed from: zzd */
    public zzmw<E> subList(int i, int i2) {
        zzml.zza(i, i2, size());
        int i3 = i2 - i;
        if (i3 == size()) {
            return this;
        }
        if (i3 == 0) {
            return (zzmw<E>) f2.zzalt;
        }
        return new d2(this, i, i3);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzmx
    public final zznd<E> zzjj() {
        return (zznc) listIterator();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzmx
    public final zzmw<E> zzjk() {
        return this;
    }

    public static <E> zzmw<E> zza(E e, E e2, E e3, E e4, E e5, E e6, E e7) {
        Object[] objArr = {e, e2, e3, e4, e5, e6, e7};
        for (int i = 0; i < 7; i++) {
            if (objArr[i] == null) {
                StringBuilder sb = new StringBuilder(20);
                sb.append("at index ");
                sb.append(i);
                throw new NullPointerException(sb.toString());
            }
        }
        return new f2(objArr, 7);
    }

    @Override // java.util.List
    public /* synthetic */ ListIterator listIterator() {
        return (zznc) listIterator(0);
    }

    public static <E> zzmw<E> zza(Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            return (zzmw<E>) f2.zzalt;
        }
        return new f2(objArr, length);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzmx
    public int zza(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i2] = get(i2);
        }
        return size;
    }
}
