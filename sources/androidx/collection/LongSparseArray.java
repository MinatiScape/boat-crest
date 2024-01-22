package androidx.collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public class LongSparseArray<E> implements Cloneable {
    public static final Object l = new Object();
    public boolean h;
    public long[] i;
    public Object[] j;
    public int k;

    public LongSparseArray() {
        this(10);
    }

    public final void a() {
        int i = this.k;
        long[] jArr = this.i;
        Object[] objArr = this.j;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != l) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.h = false;
        this.k = i2;
    }

    public void append(long j, E e) {
        int i = this.k;
        if (i != 0 && j <= this.i[i - 1]) {
            put(j, e);
            return;
        }
        if (this.h && i >= this.i.length) {
            a();
        }
        int i2 = this.k;
        if (i2 >= this.i.length) {
            int f = a.f(i2 + 1);
            long[] jArr = new long[f];
            Object[] objArr = new Object[f];
            long[] jArr2 = this.i;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.j;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.i = jArr;
            this.j = objArr;
        }
        this.i[i2] = j;
        this.j[i2] = e;
        this.k = i2 + 1;
    }

    public void clear() {
        int i = this.k;
        Object[] objArr = this.j;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.k = 0;
        this.h = false;
    }

    public boolean containsKey(long j) {
        return indexOfKey(j) >= 0;
    }

    public boolean containsValue(E e) {
        return indexOfValue(e) >= 0;
    }

    @Deprecated
    public void delete(long j) {
        remove(j);
    }

    @Nullable
    public E get(long j) {
        return get(j, null);
    }

    public int indexOfKey(long j) {
        if (this.h) {
            a();
        }
        return a.b(this.i, this.k, j);
    }

    public int indexOfValue(E e) {
        if (this.h) {
            a();
        }
        for (int i = 0; i < this.k; i++) {
            if (this.j[i] == e) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public long keyAt(int i) {
        if (this.h) {
            a();
        }
        return this.i[i];
    }

    public void put(long j, E e) {
        int b = a.b(this.i, this.k, j);
        if (b >= 0) {
            this.j[b] = e;
            return;
        }
        int i = ~b;
        int i2 = this.k;
        if (i < i2) {
            Object[] objArr = this.j;
            if (objArr[i] == l) {
                this.i[i] = j;
                objArr[i] = e;
                return;
            }
        }
        if (this.h && i2 >= this.i.length) {
            a();
            i = ~a.b(this.i, this.k, j);
        }
        int i3 = this.k;
        if (i3 >= this.i.length) {
            int f = a.f(i3 + 1);
            long[] jArr = new long[f];
            Object[] objArr2 = new Object[f];
            long[] jArr2 = this.i;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.j;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.i = jArr;
            this.j = objArr2;
        }
        int i4 = this.k;
        if (i4 - i != 0) {
            long[] jArr3 = this.i;
            int i5 = i + 1;
            System.arraycopy(jArr3, i, jArr3, i5, i4 - i);
            Object[] objArr4 = this.j;
            System.arraycopy(objArr4, i, objArr4, i5, this.k - i);
        }
        this.i[i] = j;
        this.j[i] = e;
        this.k++;
    }

    public void putAll(@NonNull LongSparseArray<? extends E> longSparseArray) {
        int size = longSparseArray.size();
        for (int i = 0; i < size; i++) {
            put(longSparseArray.keyAt(i), longSparseArray.valueAt(i));
        }
    }

    @Nullable
    public E putIfAbsent(long j, E e) {
        E e2 = get(j);
        if (e2 == null) {
            put(j, e);
        }
        return e2;
    }

    public void remove(long j) {
        int b = a.b(this.i, this.k, j);
        if (b >= 0) {
            Object[] objArr = this.j;
            Object obj = objArr[b];
            Object obj2 = l;
            if (obj != obj2) {
                objArr[b] = obj2;
                this.h = true;
            }
        }
    }

    public void removeAt(int i) {
        Object[] objArr = this.j;
        Object obj = objArr[i];
        Object obj2 = l;
        if (obj != obj2) {
            objArr[i] = obj2;
            this.h = true;
        }
    }

    @Nullable
    public E replace(long j, E e) {
        int indexOfKey = indexOfKey(j);
        if (indexOfKey >= 0) {
            Object[] objArr = this.j;
            E e2 = (E) objArr[indexOfKey];
            objArr[indexOfKey] = e;
            return e2;
        }
        return null;
    }

    public void setValueAt(int i, E e) {
        if (this.h) {
            a();
        }
        this.j[i] = e;
    }

    public int size() {
        if (this.h) {
            a();
        }
        return this.k;
    }

    public String toString() {
        if (size() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.k * 28);
        sb.append('{');
        for (int i = 0; i < this.k; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(keyAt(i));
            sb.append('=');
            E valueAt = valueAt(i);
            if (valueAt != this) {
                sb.append(valueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public E valueAt(int i) {
        if (this.h) {
            a();
        }
        return (E) this.j[i];
    }

    public LongSparseArray(int i) {
        this.h = false;
        if (i == 0) {
            this.i = a.b;
            this.j = a.c;
            return;
        }
        int f = a.f(i);
        this.i = new long[f];
        this.j = new Object[f];
    }

    /* renamed from: clone */
    public LongSparseArray<E> m4clone() {
        try {
            LongSparseArray<E> longSparseArray = (LongSparseArray) super.clone();
            longSparseArray.i = (long[]) this.i.clone();
            longSparseArray.j = (Object[]) this.j.clone();
            return longSparseArray;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public E get(long j, E e) {
        int b = a.b(this.i, this.k, j);
        if (b >= 0) {
            Object[] objArr = this.j;
            if (objArr[b] != l) {
                return (E) objArr[b];
            }
        }
        return e;
    }

    public boolean replace(long j, E e, E e2) {
        int indexOfKey = indexOfKey(j);
        if (indexOfKey >= 0) {
            Object obj = this.j[indexOfKey];
            if (obj == e || (e != null && e.equals(obj))) {
                this.j[indexOfKey] = e2;
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean remove(long j, Object obj) {
        int indexOfKey = indexOfKey(j);
        if (indexOfKey >= 0) {
            E valueAt = valueAt(indexOfKey);
            if (obj == valueAt || (obj != null && obj.equals(valueAt))) {
                removeAt(indexOfKey);
                return true;
            }
            return false;
        }
        return false;
    }
}
