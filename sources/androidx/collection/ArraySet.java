package androidx.collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class ArraySet<E> implements Collection<E>, Set<E> {
    public static final int[] l = new int[0];
    public static final Object[] m = new Object[0];
    @Nullable
    public static Object[] n;
    public static int o;
    @Nullable
    public static Object[] p;
    public static int q;
    public int[] h;
    public Object[] i;
    public int j;
    public b<E, E> k;

    /* loaded from: classes.dex */
    public class a extends b<E, E> {
        public a() {
        }

        @Override // androidx.collection.b
        public void a() {
            ArraySet.this.clear();
        }

        @Override // androidx.collection.b
        public Object b(int i, int i2) {
            return ArraySet.this.i[i];
        }

        @Override // androidx.collection.b
        public Map<E, E> c() {
            throw new UnsupportedOperationException("not a map");
        }

        @Override // androidx.collection.b
        public int d() {
            return ArraySet.this.j;
        }

        @Override // androidx.collection.b
        public int e(Object obj) {
            return ArraySet.this.indexOf(obj);
        }

        @Override // androidx.collection.b
        public int f(Object obj) {
            return ArraySet.this.indexOf(obj);
        }

        @Override // androidx.collection.b
        public void g(E e, E e2) {
            ArraySet.this.add(e);
        }

        @Override // androidx.collection.b
        public void h(int i) {
            ArraySet.this.removeAt(i);
        }

        @Override // androidx.collection.b
        public E i(int i, E e) {
            throw new UnsupportedOperationException("not a map");
        }
    }

    public ArraySet() {
        this(0);
    }

    public static void b(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (ArraySet.class) {
                if (q < 10) {
                    objArr[0] = p;
                    objArr[1] = iArr;
                    for (int i2 = i - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    p = objArr;
                    q++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (ArraySet.class) {
                if (o < 10) {
                    objArr[0] = n;
                    objArr[1] = iArr;
                    for (int i3 = i - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    n = objArr;
                    o++;
                }
            }
        }
    }

    public final void a(int i) {
        if (i == 8) {
            synchronized (ArraySet.class) {
                Object[] objArr = p;
                if (objArr != null) {
                    this.i = objArr;
                    p = (Object[]) objArr[0];
                    this.h = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    q--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (ArraySet.class) {
                Object[] objArr2 = n;
                if (objArr2 != null) {
                    this.i = objArr2;
                    n = (Object[]) objArr2[0];
                    this.h = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    o--;
                    return;
                }
            }
        }
        this.h = new int[i];
        this.i = new Object[i];
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(@Nullable E e) {
        int i;
        int d;
        if (e == null) {
            d = e();
            i = 0;
        } else {
            int hashCode = e.hashCode();
            i = hashCode;
            d = d(e, hashCode);
        }
        if (d >= 0) {
            return false;
        }
        int i2 = ~d;
        int i3 = this.j;
        int[] iArr = this.h;
        if (i3 >= iArr.length) {
            int i4 = 4;
            if (i3 >= 8) {
                i4 = (i3 >> 1) + i3;
            } else if (i3 >= 4) {
                i4 = 8;
            }
            Object[] objArr = this.i;
            a(i4);
            int[] iArr2 = this.h;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.i, 0, objArr.length);
            }
            b(iArr, objArr, this.j);
        }
        int i5 = this.j;
        if (i2 < i5) {
            int[] iArr3 = this.h;
            int i6 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i6, i5 - i2);
            Object[] objArr2 = this.i;
            System.arraycopy(objArr2, i2, objArr2, i6, this.j - i2);
        }
        this.h[i2] = i;
        this.i[i2] = e;
        this.j++;
        return true;
    }

    public void addAll(@NonNull ArraySet<? extends E> arraySet) {
        int i = arraySet.j;
        ensureCapacity(this.j + i);
        if (this.j != 0) {
            for (int i2 = 0; i2 < i; i2++) {
                add(arraySet.valueAt(i2));
            }
        } else if (i > 0) {
            System.arraycopy(arraySet.h, 0, this.h, 0, i);
            System.arraycopy(arraySet.i, 0, this.i, 0, i);
            this.j = i;
        }
    }

    public final b<E, E> c() {
        if (this.k == null) {
            this.k = new a();
        }
        return this.k;
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        int i = this.j;
        if (i != 0) {
            b(this.h, this.i, i);
            this.h = l;
            this.i = m;
            this.j = 0;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(@Nullable Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(@NonNull Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final int d(Object obj, int i) {
        int i2 = this.j;
        if (i2 == 0) {
            return -1;
        }
        int a2 = androidx.collection.a.a(this.h, i2, i);
        if (a2 >= 0 && !obj.equals(this.i[a2])) {
            int i3 = a2 + 1;
            while (i3 < i2 && this.h[i3] == i) {
                if (obj.equals(this.i[i3])) {
                    return i3;
                }
                i3++;
            }
            for (int i4 = a2 - 1; i4 >= 0 && this.h[i4] == i; i4--) {
                if (obj.equals(this.i[i4])) {
                    return i4;
                }
            }
            return ~i3;
        }
        return a2;
    }

    public final int e() {
        int i = this.j;
        if (i == 0) {
            return -1;
        }
        int a2 = androidx.collection.a.a(this.h, i, 0);
        if (a2 >= 0 && this.i[a2] != null) {
            int i2 = a2 + 1;
            while (i2 < i && this.h[i2] == 0) {
                if (this.i[i2] == null) {
                    return i2;
                }
                i2++;
            }
            for (int i3 = a2 - 1; i3 >= 0 && this.h[i3] == 0; i3--) {
                if (this.i[i3] == null) {
                    return i3;
                }
            }
            return ~i2;
        }
        return a2;
    }

    public void ensureCapacity(int i) {
        int[] iArr = this.h;
        if (iArr.length < i) {
            Object[] objArr = this.i;
            a(i);
            int i2 = this.j;
            if (i2 > 0) {
                System.arraycopy(iArr, 0, this.h, 0, i2);
                System.arraycopy(objArr, 0, this.i, 0, this.j);
            }
            b(iArr, objArr, this.j);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (size() != set.size()) {
                return false;
            }
            for (int i = 0; i < this.j; i++) {
                try {
                    if (!set.contains(valueAt(i))) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] iArr = this.h;
        int i = this.j;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += iArr[i3];
        }
        return i2;
    }

    public int indexOf(@Nullable Object obj) {
        return obj == null ? e() : d(obj, obj.hashCode());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.j <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return c().m().iterator();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(@Nullable Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf >= 0) {
            removeAt(indexOf);
            return true;
        }
        return false;
    }

    public boolean removeAll(@NonNull ArraySet<? extends E> arraySet) {
        int i = arraySet.j;
        int i2 = this.j;
        for (int i3 = 0; i3 < i; i3++) {
            remove(arraySet.valueAt(i3));
        }
        return i2 != this.j;
    }

    public E removeAt(int i) {
        Object[] objArr = this.i;
        E e = (E) objArr[i];
        int i2 = this.j;
        if (i2 <= 1) {
            b(this.h, objArr, i2);
            this.h = l;
            this.i = m;
            this.j = 0;
        } else {
            int[] iArr = this.h;
            if (iArr.length > 8 && i2 < iArr.length / 3) {
                a(i2 > 8 ? i2 + (i2 >> 1) : 8);
                this.j--;
                if (i > 0) {
                    System.arraycopy(iArr, 0, this.h, 0, i);
                    System.arraycopy(objArr, 0, this.i, 0, i);
                }
                int i3 = this.j;
                if (i < i3) {
                    int i4 = i + 1;
                    System.arraycopy(iArr, i4, this.h, i, i3 - i);
                    System.arraycopy(objArr, i4, this.i, i, this.j - i);
                }
            } else {
                int i5 = i2 - 1;
                this.j = i5;
                if (i < i5) {
                    int i6 = i + 1;
                    System.arraycopy(iArr, i6, iArr, i, i5 - i);
                    Object[] objArr2 = this.i;
                    System.arraycopy(objArr2, i6, objArr2, i, this.j - i);
                }
                this.i[this.j] = null;
            }
        }
        return e;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(@NonNull Collection<?> collection) {
        boolean z = false;
        for (int i = this.j - 1; i >= 0; i--) {
            if (!collection.contains(this.i[i])) {
                removeAt(i);
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public int size() {
        return this.j;
    }

    @Override // java.util.Collection, java.util.Set
    @NonNull
    public Object[] toArray() {
        int i = this.j;
        Object[] objArr = new Object[i];
        System.arraycopy(this.i, 0, objArr, 0, i);
        return objArr;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.j * 14);
        sb.append('{');
        for (int i = 0; i < this.j; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            E valueAt = valueAt(i);
            if (valueAt != this) {
                sb.append(valueAt);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    @Nullable
    public E valueAt(int i) {
        return (E) this.i[i];
    }

    public ArraySet(int i) {
        if (i == 0) {
            this.h = l;
            this.i = m;
        } else {
            a(i);
        }
        this.j = 0;
    }

    @Override // java.util.Collection, java.util.Set
    @NonNull
    public <T> T[] toArray(@NonNull T[] tArr) {
        if (tArr.length < this.j) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.j));
        }
        System.arraycopy(this.i, 0, tArr, 0, this.j);
        int length = tArr.length;
        int i = this.j;
        if (length > i) {
            tArr[i] = null;
        }
        return tArr;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(@NonNull Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z |= remove(it.next());
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ArraySet(@Nullable ArraySet<E> arraySet) {
        this();
        if (arraySet != 0) {
            addAll((ArraySet) arraySet);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(@NonNull Collection<? extends E> collection) {
        ensureCapacity(this.j + collection.size());
        boolean z = false;
        for (E e : collection) {
            z |= add(e);
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ArraySet(@Nullable Collection<E> collection) {
        this();
        if (collection != 0) {
            addAll(collection);
        }
    }
}
