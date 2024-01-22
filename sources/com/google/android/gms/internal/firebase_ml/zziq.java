package com.google.android.gms.internal.firebase_ml;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
/* loaded from: classes7.dex */
public class zziq<K, V> extends AbstractMap<K, V> implements Cloneable {
    public int h;
    public Object[] i;

    /* loaded from: classes7.dex */
    public final class a implements Iterator<Map.Entry<K, V>> {
        public boolean h;
        public int i;

        public a() {
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.i < zziq.this.h;
        }

        @Override // java.util.Iterator
        public final /* synthetic */ Object next() {
            int i = this.i;
            zziq zziqVar = zziq.this;
            if (i != zziqVar.h) {
                this.i = i + 1;
                this.h = false;
                return new b(i);
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public final void remove() {
            int i = this.i - 1;
            if (!this.h && i >= 0) {
                zziq.this.remove(i);
                this.i--;
                this.h = true;
                return;
            }
            throw new IllegalArgumentException();
        }
    }

    /* loaded from: classes7.dex */
    public final class b implements Map.Entry<K, V> {
        public int h;

        public b(int i) {
            this.h = i;
        }

        @Override // java.util.Map.Entry
        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                return zzmf.equal(getKey(), entry.getKey()) && zzmf.equal(getValue(), entry.getValue());
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public final K getKey() {
            return (K) zziq.this.zzai(this.h);
        }

        @Override // java.util.Map.Entry
        public final V getValue() {
            return (V) zziq.this.zzaj(this.h);
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            Object key = getKey();
            Object value = getValue();
            return (key != null ? key.hashCode() : 0) ^ (value != null ? value.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public final V setValue(V v) {
            return (V) zziq.this.set(this.h, v);
        }
    }

    /* loaded from: classes7.dex */
    public final class c extends AbstractSet<Map.Entry<K, V>> {
        public c() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public final Iterator<Map.Entry<K, V>> iterator() {
            return new a();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return zziq.this.h;
        }
    }

    public final V a(int i) {
        if (i < 0) {
            return null;
        }
        return (V) this.i[i];
    }

    public final V b(int i) {
        int i2 = this.h << 1;
        if (i < 0 || i >= i2) {
            return null;
        }
        V a2 = a(i + 1);
        Object[] objArr = this.i;
        int i3 = (i2 - i) - 2;
        if (i3 != 0) {
            System.arraycopy(objArr, i + 2, objArr, i, i3);
        }
        this.h--;
        d(i2 - 2, null, null);
        return a2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.h = 0;
        this.i = null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        return -2 != e(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(Object obj) {
        int i = this.h << 1;
        Object[] objArr = this.i;
        for (int i2 = 1; i2 < i; i2 += 2) {
            Object obj2 = objArr[i2];
            if (obj == null) {
                if (obj2 == null) {
                    return true;
                }
            } else if (obj.equals(obj2)) {
                return true;
            }
        }
        return false;
    }

    public final void d(int i, K k, V v) {
        Object[] objArr = this.i;
        objArr[i] = k;
        objArr[i + 1] = v;
    }

    public final int e(Object obj) {
        int i = this.h << 1;
        Object[] objArr = this.i;
        for (int i2 = 0; i2 < i; i2 += 2) {
            Object obj2 = objArr[i2];
            if (obj == null) {
                if (obj2 == null) {
                    return i2;
                }
            } else if (obj.equals(obj2)) {
                return i2;
            }
        }
        return -2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return new c();
    }

    @Override // java.util.AbstractMap
    /* renamed from: f */
    public final zziq<K, V> clone() {
        try {
            zziq<K, V> zziqVar = (zziq) super.clone();
            Object[] objArr = this.i;
            if (objArr != null) {
                int length = objArr.length;
                Object[] objArr2 = new Object[length];
                zziqVar.i = objArr2;
                System.arraycopy(objArr, 0, objArr2, 0, length);
            }
            return zziqVar;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V get(Object obj) {
        return a(e(obj) + 1);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V put(K k, V v) {
        int e = e(k) >> 1;
        if (e == -1) {
            e = this.h;
        }
        if (e >= 0) {
            int i = e + 1;
            if (i >= 0) {
                Object[] objArr = this.i;
                int i2 = i << 1;
                int length = objArr == null ? 0 : objArr.length;
                if (i2 > length) {
                    int i3 = ((length / 2) * 3) + 1;
                    if (i3 % 2 != 0) {
                        i3++;
                    }
                    if (i3 >= i2) {
                        i2 = i3;
                    }
                    if (i2 == 0) {
                        this.i = null;
                    } else {
                        int i4 = this.h;
                        if (i4 == 0 || i2 != objArr.length) {
                            Object[] objArr2 = new Object[i2];
                            this.i = objArr2;
                            if (i4 != 0) {
                                System.arraycopy(objArr, 0, objArr2, 0, i4 << 1);
                            }
                        }
                    }
                }
                int i5 = e << 1;
                V a2 = a(i5 + 1);
                d(i5, k, v);
                if (i > this.h) {
                    this.h = i;
                }
                return a2;
            }
            throw new IndexOutOfBoundsException();
        }
        throw new IndexOutOfBoundsException();
    }

    public final V remove(int i) {
        return b(i << 1);
    }

    public final V set(int i, V v) {
        int i2 = this.h;
        if (i >= 0 && i < i2) {
            int i3 = (i << 1) + 1;
            V a2 = a(i3);
            this.i[i3] = v;
            return a2;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.h;
    }

    public final K zzai(int i) {
        if (i < 0 || i >= this.h) {
            return null;
        }
        return (K) this.i[i << 1];
    }

    public final V zzaj(int i) {
        if (i < 0 || i >= this.h) {
            return null;
        }
        return a((i << 1) + 1);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        return b(e(obj));
    }
}
