package androidx.collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ConcurrentModificationException;
import java.util.Map;
/* loaded from: classes.dex */
public class SimpleArrayMap<K, V> {
    @Nullable
    public static Object[] k;
    public static int l;
    @Nullable
    public static Object[] m;
    public static int n;
    public int[] h;
    public Object[] i;
    public int j;

    public SimpleArrayMap() {
        this.h = a.f843a;
        this.i = a.c;
        this.j = 0;
    }

    private void a(int i) {
        if (i == 8) {
            synchronized (SimpleArrayMap.class) {
                Object[] objArr = m;
                if (objArr != null) {
                    this.i = objArr;
                    m = (Object[]) objArr[0];
                    this.h = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    n--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (SimpleArrayMap.class) {
                Object[] objArr2 = k;
                if (objArr2 != null) {
                    this.i = objArr2;
                    k = (Object[]) objArr2[0];
                    this.h = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    l--;
                    return;
                }
            }
        }
        this.h = new int[i];
        this.i = new Object[i << 1];
    }

    public static int b(int[] iArr, int i, int i2) {
        try {
            return a.a(iArr, i, i2);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public static void d(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (SimpleArrayMap.class) {
                if (n < 10) {
                    objArr[0] = m;
                    objArr[1] = iArr;
                    for (int i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    m = objArr;
                    n++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (SimpleArrayMap.class) {
                if (l < 10) {
                    objArr[0] = k;
                    objArr[1] = iArr;
                    for (int i3 = (i << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    k = objArr;
                    l++;
                }
            }
        }
    }

    public void clear() {
        int i = this.j;
        if (i > 0) {
            int[] iArr = this.h;
            Object[] objArr = this.i;
            this.h = a.f843a;
            this.i = a.c;
            this.j = 0;
            d(iArr, objArr, i);
        }
        if (this.j > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(@Nullable Object obj) {
        return indexOfKey(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return g(obj) >= 0;
    }

    int e(Object obj, int i) {
        int i2 = this.j;
        if (i2 == 0) {
            return -1;
        }
        int b = b(this.h, i2, i);
        if (b >= 0 && !obj.equals(this.i[b << 1])) {
            int i3 = b + 1;
            while (i3 < i2 && this.h[i3] == i) {
                if (obj.equals(this.i[i3 << 1])) {
                    return i3;
                }
                i3++;
            }
            for (int i4 = b - 1; i4 >= 0 && this.h[i4] == i; i4--) {
                if (obj.equals(this.i[i4 << 1])) {
                    return i4;
                }
            }
            return ~i3;
        }
        return b;
    }

    public void ensureCapacity(int i) {
        int i2 = this.j;
        int[] iArr = this.h;
        if (iArr.length < i) {
            Object[] objArr = this.i;
            a(i);
            if (this.j > 0) {
                System.arraycopy(iArr, 0, this.h, 0, i2);
                System.arraycopy(objArr, 0, this.i, 0, i2 << 1);
            }
            d(iArr, objArr, i2);
        }
        if (this.j != i2) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SimpleArrayMap) {
            SimpleArrayMap simpleArrayMap = (SimpleArrayMap) obj;
            if (size() != simpleArrayMap.size()) {
                return false;
            }
            for (int i = 0; i < this.j; i++) {
                try {
                    K keyAt = keyAt(i);
                    V valueAt = valueAt(i);
                    Object obj2 = simpleArrayMap.get(keyAt);
                    if (valueAt == null) {
                        if (obj2 != null || !simpleArrayMap.containsKey(keyAt)) {
                            return false;
                        }
                    } else if (!valueAt.equals(obj2)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (size() != map.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.j; i2++) {
                try {
                    K keyAt2 = keyAt(i2);
                    V valueAt2 = valueAt(i2);
                    Object obj3 = map.get(keyAt2);
                    if (valueAt2 == null) {
                        if (obj3 != null || !map.containsKey(keyAt2)) {
                            return false;
                        }
                    } else if (!valueAt2.equals(obj3)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    public int f() {
        int i = this.j;
        if (i == 0) {
            return -1;
        }
        int b = b(this.h, i, 0);
        if (b >= 0 && this.i[b << 1] != null) {
            int i2 = b + 1;
            while (i2 < i && this.h[i2] == 0) {
                if (this.i[i2 << 1] == null) {
                    return i2;
                }
                i2++;
            }
            for (int i3 = b - 1; i3 >= 0 && this.h[i3] == 0; i3--) {
                if (this.i[i3 << 1] == null) {
                    return i3;
                }
            }
            return ~i2;
        }
        return b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g(Object obj) {
        int i = this.j * 2;
        Object[] objArr = this.i;
        if (obj == null) {
            for (int i2 = 1; i2 < i; i2 += 2) {
                if (objArr[i2] == null) {
                    return i2 >> 1;
                }
            }
            return -1;
        }
        for (int i3 = 1; i3 < i; i3 += 2) {
            if (obj.equals(objArr[i3])) {
                return i3 >> 1;
            }
        }
        return -1;
    }

    @Nullable
    public V get(Object obj) {
        return getOrDefault(obj, null);
    }

    public V getOrDefault(Object obj, V v) {
        int indexOfKey = indexOfKey(obj);
        return indexOfKey >= 0 ? (V) this.i[(indexOfKey << 1) + 1] : v;
    }

    public int hashCode() {
        int[] iArr = this.h;
        Object[] objArr = this.i;
        int i = this.j;
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            Object obj = objArr[i2];
            i4 += (obj == null ? 0 : obj.hashCode()) ^ iArr[i3];
            i3++;
            i2 += 2;
        }
        return i4;
    }

    public int indexOfKey(@Nullable Object obj) {
        return obj == null ? f() : e(obj, obj.hashCode());
    }

    public boolean isEmpty() {
        return this.j <= 0;
    }

    public K keyAt(int i) {
        return (K) this.i[i << 1];
    }

    @Nullable
    public V put(K k2, V v) {
        int i;
        int e;
        int i2 = this.j;
        if (k2 == null) {
            e = f();
            i = 0;
        } else {
            int hashCode = k2.hashCode();
            i = hashCode;
            e = e(k2, hashCode);
        }
        if (e >= 0) {
            int i3 = (e << 1) + 1;
            Object[] objArr = this.i;
            V v2 = (V) objArr[i3];
            objArr[i3] = v;
            return v2;
        }
        int i4 = ~e;
        int[] iArr = this.h;
        if (i2 >= iArr.length) {
            int i5 = 4;
            if (i2 >= 8) {
                i5 = (i2 >> 1) + i2;
            } else if (i2 >= 4) {
                i5 = 8;
            }
            Object[] objArr2 = this.i;
            a(i5);
            if (i2 == this.j) {
                int[] iArr2 = this.h;
                if (iArr2.length > 0) {
                    System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                    System.arraycopy(objArr2, 0, this.i, 0, objArr2.length);
                }
                d(iArr, objArr2, i2);
            } else {
                throw new ConcurrentModificationException();
            }
        }
        if (i4 < i2) {
            int[] iArr3 = this.h;
            int i6 = i4 + 1;
            System.arraycopy(iArr3, i4, iArr3, i6, i2 - i4);
            Object[] objArr3 = this.i;
            System.arraycopy(objArr3, i4 << 1, objArr3, i6 << 1, (this.j - i4) << 1);
        }
        int i7 = this.j;
        if (i2 == i7) {
            int[] iArr4 = this.h;
            if (i4 < iArr4.length) {
                iArr4[i4] = i;
                Object[] objArr4 = this.i;
                int i8 = i4 << 1;
                objArr4[i8] = k2;
                objArr4[i8 + 1] = v;
                this.j = i7 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public void putAll(@NonNull SimpleArrayMap<? extends K, ? extends V> simpleArrayMap) {
        int i = simpleArrayMap.j;
        ensureCapacity(this.j + i);
        if (this.j != 0) {
            for (int i2 = 0; i2 < i; i2++) {
                put(simpleArrayMap.keyAt(i2), simpleArrayMap.valueAt(i2));
            }
        } else if (i > 0) {
            System.arraycopy(simpleArrayMap.h, 0, this.h, 0, i);
            System.arraycopy(simpleArrayMap.i, 0, this.i, 0, i << 1);
            this.j = i;
        }
    }

    @Nullable
    public V putIfAbsent(K k2, V v) {
        V v2 = get(k2);
        return v2 == null ? put(k2, v) : v2;
    }

    @Nullable
    public V remove(Object obj) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey >= 0) {
            return removeAt(indexOfKey);
        }
        return null;
    }

    public V removeAt(int i) {
        Object[] objArr = this.i;
        int i2 = i << 1;
        V v = (V) objArr[i2 + 1];
        int i3 = this.j;
        int i4 = 0;
        if (i3 <= 1) {
            d(this.h, objArr, i3);
            this.h = a.f843a;
            this.i = a.c;
        } else {
            int i5 = i3 - 1;
            int[] iArr = this.h;
            if (iArr.length > 8 && i3 < iArr.length / 3) {
                a(i3 > 8 ? i3 + (i3 >> 1) : 8);
                if (i3 != this.j) {
                    throw new ConcurrentModificationException();
                }
                if (i > 0) {
                    System.arraycopy(iArr, 0, this.h, 0, i);
                    System.arraycopy(objArr, 0, this.i, 0, i2);
                }
                if (i < i5) {
                    int i6 = i + 1;
                    int i7 = i5 - i;
                    System.arraycopy(iArr, i6, this.h, i, i7);
                    System.arraycopy(objArr, i6 << 1, this.i, i2, i7 << 1);
                }
            } else {
                if (i < i5) {
                    int i8 = i + 1;
                    int i9 = i5 - i;
                    System.arraycopy(iArr, i8, iArr, i, i9);
                    Object[] objArr2 = this.i;
                    System.arraycopy(objArr2, i8 << 1, objArr2, i2, i9 << 1);
                }
                Object[] objArr3 = this.i;
                int i10 = i5 << 1;
                objArr3[i10] = null;
                objArr3[i10 + 1] = null;
            }
            i4 = i5;
        }
        if (i3 == this.j) {
            this.j = i4;
            return v;
        }
        throw new ConcurrentModificationException();
    }

    @Nullable
    public V replace(K k2, V v) {
        int indexOfKey = indexOfKey(k2);
        if (indexOfKey >= 0) {
            return setValueAt(indexOfKey, v);
        }
        return null;
    }

    public V setValueAt(int i, V v) {
        int i2 = (i << 1) + 1;
        Object[] objArr = this.i;
        V v2 = (V) objArr[i2];
        objArr[i2] = v;
        return v2;
    }

    public int size() {
        return this.j;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.j * 28);
        sb.append('{');
        for (int i = 0; i < this.j; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            K keyAt = keyAt(i);
            if (keyAt != this) {
                sb.append(keyAt);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            V valueAt = valueAt(i);
            if (valueAt != this) {
                sb.append(valueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public V valueAt(int i) {
        return (V) this.i[(i << 1) + 1];
    }

    public boolean remove(Object obj, Object obj2) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey >= 0) {
            V valueAt = valueAt(indexOfKey);
            if (obj2 == valueAt || (obj2 != null && obj2.equals(valueAt))) {
                removeAt(indexOfKey);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean replace(K k2, V v, V v2) {
        int indexOfKey = indexOfKey(k2);
        if (indexOfKey >= 0) {
            V valueAt = valueAt(indexOfKey);
            if (valueAt == v || (v != null && v.equals(valueAt))) {
                setValueAt(indexOfKey, v2);
                return true;
            }
            return false;
        }
        return false;
    }

    public SimpleArrayMap(int i) {
        if (i == 0) {
            this.h = a.f843a;
            this.i = a.c;
        } else {
            a(i);
        }
        this.j = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SimpleArrayMap(SimpleArrayMap<K, V> simpleArrayMap) {
        this();
        if (simpleArrayMap != 0) {
            putAll(simpleArrayMap);
        }
    }
}
