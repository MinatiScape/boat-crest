package androidx.collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public class SparseArrayCompat<E> implements Cloneable {
    public static final Object l = new Object();
    public boolean h;
    public int[] i;
    public Object[] j;
    public int k;

    public SparseArrayCompat() {
        this(10);
    }

    public final void a() {
        int i = this.k;
        int[] iArr = this.i;
        Object[] objArr = this.j;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != l) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.h = false;
        this.k = i2;
    }

    public void append(int i, E e) {
        int i2 = this.k;
        if (i2 != 0 && i <= this.i[i2 - 1]) {
            put(i, e);
            return;
        }
        if (this.h && i2 >= this.i.length) {
            a();
        }
        int i3 = this.k;
        if (i3 >= this.i.length) {
            int e2 = a.e(i3 + 1);
            int[] iArr = new int[e2];
            Object[] objArr = new Object[e2];
            int[] iArr2 = this.i;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr2 = this.j;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.i = iArr;
            this.j = objArr;
        }
        this.i[i3] = i;
        this.j[i3] = e;
        this.k = i3 + 1;
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

    public boolean containsKey(int i) {
        return indexOfKey(i) >= 0;
    }

    public boolean containsValue(E e) {
        return indexOfValue(e) >= 0;
    }

    @Deprecated
    public void delete(int i) {
        remove(i);
    }

    @Nullable
    public E get(int i) {
        return get(i, null);
    }

    public int indexOfKey(int i) {
        if (this.h) {
            a();
        }
        return a.a(this.i, this.k, i);
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

    public int keyAt(int i) {
        if (this.h) {
            a();
        }
        return this.i[i];
    }

    public void put(int i, E e) {
        int a2 = a.a(this.i, this.k, i);
        if (a2 >= 0) {
            this.j[a2] = e;
            return;
        }
        int i2 = ~a2;
        int i3 = this.k;
        if (i2 < i3) {
            Object[] objArr = this.j;
            if (objArr[i2] == l) {
                this.i[i2] = i;
                objArr[i2] = e;
                return;
            }
        }
        if (this.h && i3 >= this.i.length) {
            a();
            i2 = ~a.a(this.i, this.k, i);
        }
        int i4 = this.k;
        if (i4 >= this.i.length) {
            int e2 = a.e(i4 + 1);
            int[] iArr = new int[e2];
            Object[] objArr2 = new Object[e2];
            int[] iArr2 = this.i;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            Object[] objArr3 = this.j;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.i = iArr;
            this.j = objArr2;
        }
        int i5 = this.k;
        if (i5 - i2 != 0) {
            int[] iArr3 = this.i;
            int i6 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i6, i5 - i2);
            Object[] objArr4 = this.j;
            System.arraycopy(objArr4, i2, objArr4, i6, this.k - i2);
        }
        this.i[i2] = i;
        this.j[i2] = e;
        this.k++;
    }

    public void putAll(@NonNull SparseArrayCompat<? extends E> sparseArrayCompat) {
        int size = sparseArrayCompat.size();
        for (int i = 0; i < size; i++) {
            put(sparseArrayCompat.keyAt(i), sparseArrayCompat.valueAt(i));
        }
    }

    @Nullable
    public E putIfAbsent(int i, E e) {
        E e2 = get(i);
        if (e2 == null) {
            put(i, e);
        }
        return e2;
    }

    public void remove(int i) {
        int a2 = a.a(this.i, this.k, i);
        if (a2 >= 0) {
            Object[] objArr = this.j;
            Object obj = objArr[a2];
            Object obj2 = l;
            if (obj != obj2) {
                objArr[a2] = obj2;
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

    public void removeAtRange(int i, int i2) {
        int min = Math.min(this.k, i2 + i);
        while (i < min) {
            removeAt(i);
            i++;
        }
    }

    @Nullable
    public E replace(int i, E e) {
        int indexOfKey = indexOfKey(i);
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

    public SparseArrayCompat(int i) {
        this.h = false;
        if (i == 0) {
            this.i = a.f843a;
            this.j = a.c;
            return;
        }
        int e = a.e(i);
        this.i = new int[e];
        this.j = new Object[e];
    }

    /* renamed from: clone */
    public SparseArrayCompat<E> m5clone() {
        try {
            SparseArrayCompat<E> sparseArrayCompat = (SparseArrayCompat) super.clone();
            sparseArrayCompat.i = (int[]) this.i.clone();
            sparseArrayCompat.j = (Object[]) this.j.clone();
            return sparseArrayCompat;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public E get(int i, E e) {
        int a2 = a.a(this.i, this.k, i);
        if (a2 >= 0) {
            Object[] objArr = this.j;
            if (objArr[a2] != l) {
                return (E) objArr[a2];
            }
        }
        return e;
    }

    public boolean replace(int i, E e, E e2) {
        int indexOfKey = indexOfKey(i);
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

    public boolean remove(int i, Object obj) {
        int indexOfKey = indexOfKey(i);
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
