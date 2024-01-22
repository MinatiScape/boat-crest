package kotlin.collections;

import com.coveiot.android.leonardo.performance.Constants;
import com.goodix.ble.gr.libdfu.BuildConfig;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.ExperimentalStdlibApi;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SinceKotlin(version = BuildConfig.VERSION_NAME)
@WasExperimental(markerClass = {ExperimentalStdlibApi.class})
/* loaded from: classes12.dex */
public final class ArrayDeque<E> extends AbstractMutableList<E> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final Object[] k = new Object[0];
    public int h;
    @NotNull
    public Object[] i;
    public int j;

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int newCapacity$kotlin_stdlib(int i, int i2) {
            int i3 = i + (i >> 1);
            if (i3 - i2 < 0) {
                i3 = i2;
            }
            if (i3 - Constants.SINGLE_BEST_ACTIVITY_NOTIFICATION_ID > 0) {
                if (i2 > 2147483639) {
                    return Integer.MAX_VALUE;
                }
                return Constants.SINGLE_BEST_ACTIVITY_NOTIFICATION_ID;
            }
            return i3;
        }
    }

    public ArrayDeque(int i) {
        Object[] objArr;
        if (i == 0) {
            objArr = k;
        } else if (i > 0) {
            objArr = new Object[i];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + i);
        }
        this.i = objArr;
    }

    public final void a(int i, Collection<? extends E> collection) {
        Iterator<? extends E> it = collection.iterator();
        int length = this.i.length;
        while (i < length && it.hasNext()) {
            this.i[i] = it.next();
            i++;
        }
        int i2 = this.h;
        for (int i3 = 0; i3 < i2 && it.hasNext(); i3++) {
            this.i[i3] = it.next();
        }
        this.j = size() + collection.size();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(@NotNull Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements.isEmpty()) {
            return false;
        }
        ensureCapacity(size() + elements.size());
        a(g(this.h + size()), elements);
        return true;
    }

    public final void addFirst(E e) {
        ensureCapacity(size() + 1);
        int d = d(this.h);
        this.h = d;
        this.i[d] = e;
        this.j = size() + 1;
    }

    public final void addLast(E e) {
        ensureCapacity(size() + 1);
        this.i[g(this.h + size())] = e;
        this.j = size() + 1;
    }

    public final void b(int i) {
        Object[] objArr = new Object[i];
        Object[] objArr2 = this.i;
        ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr, 0, this.h, objArr2.length);
        Object[] objArr3 = this.i;
        int length = objArr3.length;
        int i2 = this.h;
        ArraysKt___ArraysJvmKt.copyInto(objArr3, objArr, length - i2, 0, i2);
        this.h = 0;
        this.i = objArr;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        int g = g(this.h + size());
        int i = this.h;
        if (i < g) {
            ArraysKt___ArraysJvmKt.fill(this.i, (Object) null, i, g);
        } else if (!isEmpty()) {
            Object[] objArr = this.i;
            ArraysKt___ArraysJvmKt.fill(objArr, (Object) null, this.h, objArr.length);
            ArraysKt___ArraysJvmKt.fill(this.i, (Object) null, 0, g);
        }
        this.h = 0;
        this.j = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final int d(int i) {
        return i == 0 ? ArraysKt___ArraysKt.getLastIndex(this.i) : i - 1;
    }

    public final int e(int i) {
        if (i == ArraysKt___ArraysKt.getLastIndex(this.i)) {
            return 0;
        }
        return i + 1;
    }

    public final void ensureCapacity(int i) {
        if (i >= 0) {
            Object[] objArr = this.i;
            if (i <= objArr.length) {
                return;
            }
            if (objArr == k) {
                this.i = new Object[kotlin.ranges.h.coerceAtLeast(i, 10)];
                return;
            } else {
                b(Companion.newCapacity$kotlin_stdlib(objArr.length, i));
                return;
            }
        }
        throw new IllegalStateException("Deque is too big.");
    }

    public final int f(int i) {
        return i < 0 ? i + this.i.length : i;
    }

    public final E first() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        return (E) this.i[this.h];
    }

    @Nullable
    public final E firstOrNull() {
        if (isEmpty()) {
            return null;
        }
        return (E) this.i[this.h];
    }

    public final int g(int i) {
        Object[] objArr = this.i;
        return i >= objArr.length ? i - objArr.length : i;
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, size());
        return (E) this.i[g(this.h + i)];
    }

    @Override // kotlin.collections.AbstractMutableList
    public int getSize() {
        return this.j;
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        int i;
        int g = g(this.h + size());
        int i2 = this.h;
        if (i2 < g) {
            while (i2 < g) {
                if (Intrinsics.areEqual(obj, this.i[i2])) {
                    i = this.h;
                } else {
                    i2++;
                }
            }
            return -1;
        } else if (i2 < g) {
            return -1;
        } else {
            int length = this.i.length;
            while (true) {
                if (i2 >= length) {
                    for (int i3 = 0; i3 < g; i3++) {
                        if (Intrinsics.areEqual(obj, this.i[i3])) {
                            i2 = i3 + this.i.length;
                            i = this.h;
                        }
                    }
                    return -1;
                } else if (Intrinsics.areEqual(obj, this.i[i2])) {
                    i = this.h;
                    break;
                } else {
                    i2++;
                }
            }
        }
        return i2 - i;
    }

    public final void internalStructure$kotlin_stdlib(@NotNull Function2<? super Integer, ? super Object[], Unit> structure) {
        int i;
        Intrinsics.checkNotNullParameter(structure, "structure");
        structure.invoke(Integer.valueOf((isEmpty() || (i = this.h) < g(this.h + size())) ? this.h : i - this.i.length), toArray());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return size() == 0;
    }

    public final E last() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        return (E) this.i[g(this.h + CollectionsKt__CollectionsKt.getLastIndex(this))];
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object obj) {
        int lastIndex;
        int i;
        int g = g(this.h + size());
        int i2 = this.h;
        if (i2 < g) {
            lastIndex = g - 1;
            if (i2 <= lastIndex) {
                while (!Intrinsics.areEqual(obj, this.i[lastIndex])) {
                    if (lastIndex != i2) {
                        lastIndex--;
                    }
                }
                i = this.h;
                return lastIndex - i;
            }
            return -1;
        }
        if (i2 > g) {
            int i3 = g - 1;
            while (true) {
                if (-1 < i3) {
                    if (Intrinsics.areEqual(obj, this.i[i3])) {
                        lastIndex = i3 + this.i.length;
                        i = this.h;
                        break;
                    }
                    i3--;
                } else {
                    lastIndex = ArraysKt___ArraysKt.getLastIndex(this.i);
                    int i4 = this.h;
                    if (i4 <= lastIndex) {
                        while (!Intrinsics.areEqual(obj, this.i[lastIndex])) {
                            if (lastIndex != i4) {
                                lastIndex--;
                            }
                        }
                        i = this.h;
                    }
                }
            }
        }
        return -1;
    }

    @Nullable
    public final E lastOrNull() {
        if (isEmpty()) {
            return null;
        }
        return (E) this.i[g(this.h + CollectionsKt__CollectionsKt.getLastIndex(this))];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return false;
        }
        remove(indexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(@NotNull Collection<? extends Object> elements) {
        int g;
        Intrinsics.checkNotNullParameter(elements, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty()) {
            if ((this.i.length == 0 ? 1 : null) == null) {
                int g2 = g(this.h + size());
                int i = this.h;
                if (i < g2) {
                    g = i;
                    while (i < g2) {
                        Object obj = this.i[i];
                        if (!elements.contains(obj)) {
                            this.i[g] = obj;
                            g++;
                        } else {
                            z = true;
                        }
                        i++;
                    }
                    ArraysKt___ArraysJvmKt.fill(this.i, (Object) null, g, g2);
                } else {
                    int length = this.i.length;
                    boolean z2 = false;
                    int i2 = i;
                    while (i < length) {
                        Object[] objArr = this.i;
                        Object obj2 = objArr[i];
                        objArr[i] = null;
                        if (!elements.contains(obj2)) {
                            this.i[i2] = obj2;
                            i2++;
                        } else {
                            z2 = true;
                        }
                        i++;
                    }
                    g = g(i2);
                    for (int i3 = 0; i3 < g2; i3++) {
                        Object[] objArr2 = this.i;
                        Object obj3 = objArr2[i3];
                        objArr2[i3] = null;
                        if (!elements.contains(obj3)) {
                            this.i[g] = obj3;
                            g = e(g);
                        } else {
                            z2 = true;
                        }
                    }
                    z = z2;
                }
                if (z) {
                    this.j = f(g - this.h);
                }
            }
        }
        return z;
    }

    @Override // kotlin.collections.AbstractMutableList
    public E removeAt(int i) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, size());
        if (i == CollectionsKt__CollectionsKt.getLastIndex(this)) {
            return removeLast();
        }
        if (i == 0) {
            return removeFirst();
        }
        int g = g(this.h + i);
        E e = (E) this.i[g];
        if (i < (size() >> 1)) {
            int i2 = this.h;
            if (g >= i2) {
                Object[] objArr = this.i;
                ArraysKt___ArraysJvmKt.copyInto(objArr, objArr, i2 + 1, i2, g);
            } else {
                Object[] objArr2 = this.i;
                ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, 1, 0, g);
                Object[] objArr3 = this.i;
                objArr3[0] = objArr3[objArr3.length - 1];
                int i3 = this.h;
                ArraysKt___ArraysJvmKt.copyInto(objArr3, objArr3, i3 + 1, i3, objArr3.length - 1);
            }
            Object[] objArr4 = this.i;
            int i4 = this.h;
            objArr4[i4] = null;
            this.h = e(i4);
        } else {
            int g2 = g(this.h + CollectionsKt__CollectionsKt.getLastIndex(this));
            if (g <= g2) {
                Object[] objArr5 = this.i;
                ArraysKt___ArraysJvmKt.copyInto(objArr5, objArr5, g, g + 1, g2 + 1);
            } else {
                Object[] objArr6 = this.i;
                ArraysKt___ArraysJvmKt.copyInto(objArr6, objArr6, g, g + 1, objArr6.length);
                Object[] objArr7 = this.i;
                objArr7[objArr7.length - 1] = objArr7[0];
                ArraysKt___ArraysJvmKt.copyInto(objArr7, objArr7, 0, 1, g2 + 1);
            }
            this.i[g2] = null;
        }
        this.j = size() - 1;
        return e;
    }

    public final E removeFirst() {
        if (!isEmpty()) {
            Object[] objArr = this.i;
            int i = this.h;
            E e = (E) objArr[i];
            objArr[i] = null;
            this.h = e(i);
            this.j = size() - 1;
            return e;
        }
        throw new NoSuchElementException("ArrayDeque is empty.");
    }

    @Nullable
    public final E removeFirstOrNull() {
        if (isEmpty()) {
            return null;
        }
        return removeFirst();
    }

    public final E removeLast() {
        if (!isEmpty()) {
            int g = g(this.h + CollectionsKt__CollectionsKt.getLastIndex(this));
            Object[] objArr = this.i;
            E e = (E) objArr[g];
            objArr[g] = null;
            this.j = size() - 1;
            return e;
        }
        throw new NoSuchElementException("ArrayDeque is empty.");
    }

    @Nullable
    public final E removeLastOrNull() {
        if (isEmpty()) {
            return null;
        }
        return removeLast();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(@NotNull Collection<? extends Object> elements) {
        int g;
        Intrinsics.checkNotNullParameter(elements, "elements");
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty()) {
            if ((this.i.length == 0 ? 1 : null) == null) {
                int g2 = g(this.h + size());
                int i = this.h;
                if (i < g2) {
                    g = i;
                    while (i < g2) {
                        Object obj = this.i[i];
                        if (elements.contains(obj)) {
                            this.i[g] = obj;
                            g++;
                        } else {
                            z = true;
                        }
                        i++;
                    }
                    ArraysKt___ArraysJvmKt.fill(this.i, (Object) null, g, g2);
                } else {
                    int length = this.i.length;
                    boolean z2 = false;
                    int i2 = i;
                    while (i < length) {
                        Object[] objArr = this.i;
                        Object obj2 = objArr[i];
                        objArr[i] = null;
                        if (elements.contains(obj2)) {
                            this.i[i2] = obj2;
                            i2++;
                        } else {
                            z2 = true;
                        }
                        i++;
                    }
                    g = g(i2);
                    for (int i3 = 0; i3 < g2; i3++) {
                        Object[] objArr2 = this.i;
                        Object obj3 = objArr2[i3];
                        objArr2[i3] = null;
                        if (elements.contains(obj3)) {
                            this.i[g] = obj3;
                            g = e(g);
                        } else {
                            z2 = true;
                        }
                    }
                    z = z2;
                }
                if (z) {
                    this.j = f(g - this.h);
                }
            }
        }
        return z;
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public E set(int i, E e) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, size());
        int g = g(this.h + i);
        Object[] objArr = this.i;
        E e2 = (E) objArr[g];
        objArr[g] = e;
        return e2;
    }

    @NotNull
    public final <T> T[] testToArray$kotlin_stdlib(@NotNull T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return (T[]) toArray(array);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    @NotNull
    public <T> T[] toArray(@NotNull T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        if (array.length < size()) {
            array = (T[]) b.arrayOfNulls(array, size());
        }
        int g = g(this.h + size());
        int i = this.h;
        if (i < g) {
            ArraysKt___ArraysJvmKt.copyInto$default(this.i, array, 0, i, g, 2, (Object) null);
        } else if (!isEmpty()) {
            Object[] objArr = this.i;
            ArraysKt___ArraysJvmKt.copyInto(objArr, array, 0, this.h, objArr.length);
            Object[] objArr2 = this.i;
            ArraysKt___ArraysJvmKt.copyInto(objArr2, array, objArr2.length - this.h, 0, g);
        }
        if (array.length > size()) {
            array[size()] = null;
        }
        return array;
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public void add(int i, E e) {
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, size());
        if (i == size()) {
            addLast(e);
        } else if (i == 0) {
            addFirst(e);
        } else {
            ensureCapacity(size() + 1);
            int g = g(this.h + i);
            if (i < ((size() + 1) >> 1)) {
                int d = d(g);
                int d2 = d(this.h);
                int i2 = this.h;
                if (d >= i2) {
                    Object[] objArr = this.i;
                    objArr[d2] = objArr[i2];
                    ArraysKt___ArraysJvmKt.copyInto(objArr, objArr, i2, i2 + 1, d + 1);
                } else {
                    Object[] objArr2 = this.i;
                    ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, i2 - 1, i2, objArr2.length);
                    Object[] objArr3 = this.i;
                    objArr3[objArr3.length - 1] = objArr3[0];
                    ArraysKt___ArraysJvmKt.copyInto(objArr3, objArr3, 0, 1, d + 1);
                }
                this.i[d] = e;
                this.h = d2;
            } else {
                int g2 = g(this.h + size());
                if (g < g2) {
                    Object[] objArr4 = this.i;
                    ArraysKt___ArraysJvmKt.copyInto(objArr4, objArr4, g + 1, g, g2);
                } else {
                    Object[] objArr5 = this.i;
                    ArraysKt___ArraysJvmKt.copyInto(objArr5, objArr5, 1, 0, g2);
                    Object[] objArr6 = this.i;
                    objArr6[0] = objArr6[objArr6.length - 1];
                    ArraysKt___ArraysJvmKt.copyInto(objArr6, objArr6, g + 1, g, objArr6.length - 1);
                }
                this.i[g] = e;
            }
            this.j = size() + 1;
        }
    }

    @NotNull
    public final Object[] testToArray$kotlin_stdlib() {
        return toArray();
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int i, @NotNull Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, size());
        if (elements.isEmpty()) {
            return false;
        }
        if (i == size()) {
            return addAll(elements);
        }
        ensureCapacity(size() + elements.size());
        int g = g(this.h + size());
        int g2 = g(this.h + i);
        int size = elements.size();
        if (i < ((size() + 1) >> 1)) {
            int i2 = this.h;
            int i3 = i2 - size;
            if (g2 < i2) {
                Object[] objArr = this.i;
                ArraysKt___ArraysJvmKt.copyInto(objArr, objArr, i3, i2, objArr.length);
                if (size >= g2) {
                    Object[] objArr2 = this.i;
                    ArraysKt___ArraysJvmKt.copyInto(objArr2, objArr2, objArr2.length - size, 0, g2);
                } else {
                    Object[] objArr3 = this.i;
                    ArraysKt___ArraysJvmKt.copyInto(objArr3, objArr3, objArr3.length - size, 0, size);
                    Object[] objArr4 = this.i;
                    ArraysKt___ArraysJvmKt.copyInto(objArr4, objArr4, 0, size, g2);
                }
            } else if (i3 >= 0) {
                Object[] objArr5 = this.i;
                ArraysKt___ArraysJvmKt.copyInto(objArr5, objArr5, i3, i2, g2);
            } else {
                Object[] objArr6 = this.i;
                i3 += objArr6.length;
                int i4 = g2 - i2;
                int length = objArr6.length - i3;
                if (length >= i4) {
                    ArraysKt___ArraysJvmKt.copyInto(objArr6, objArr6, i3, i2, g2);
                } else {
                    ArraysKt___ArraysJvmKt.copyInto(objArr6, objArr6, i3, i2, i2 + length);
                    Object[] objArr7 = this.i;
                    ArraysKt___ArraysJvmKt.copyInto(objArr7, objArr7, 0, this.h + length, g2);
                }
            }
            this.h = i3;
            a(f(g2 - size), elements);
        } else {
            int i5 = g2 + size;
            if (g2 < g) {
                int i6 = size + g;
                Object[] objArr8 = this.i;
                if (i6 <= objArr8.length) {
                    ArraysKt___ArraysJvmKt.copyInto(objArr8, objArr8, i5, g2, g);
                } else if (i5 >= objArr8.length) {
                    ArraysKt___ArraysJvmKt.copyInto(objArr8, objArr8, i5 - objArr8.length, g2, g);
                } else {
                    int length2 = g - (i6 - objArr8.length);
                    ArraysKt___ArraysJvmKt.copyInto(objArr8, objArr8, 0, length2, g);
                    Object[] objArr9 = this.i;
                    ArraysKt___ArraysJvmKt.copyInto(objArr9, objArr9, i5, g2, length2);
                }
            } else {
                Object[] objArr10 = this.i;
                ArraysKt___ArraysJvmKt.copyInto(objArr10, objArr10, size, 0, g);
                Object[] objArr11 = this.i;
                if (i5 >= objArr11.length) {
                    ArraysKt___ArraysJvmKt.copyInto(objArr11, objArr11, i5 - objArr11.length, g2, objArr11.length);
                } else {
                    ArraysKt___ArraysJvmKt.copyInto(objArr11, objArr11, 0, objArr11.length - size, objArr11.length);
                    Object[] objArr12 = this.i;
                    ArraysKt___ArraysJvmKt.copyInto(objArr12, objArr12, i5, g2, objArr12.length - size);
                }
            }
            a(g2, elements);
        }
        return true;
    }

    public ArrayDeque() {
        this.i = k;
    }

    public ArrayDeque(@NotNull Collection<? extends E> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Object[] array = elements.toArray(new Object[0]);
        this.i = array;
        this.j = array.length;
        if (array.length == 0) {
            this.i = k;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    @NotNull
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }
}
