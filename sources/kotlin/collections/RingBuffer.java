package kotlin.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.RandomAccess;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class RingBuffer<T> extends AbstractList<T> implements RandomAccess {
    @NotNull
    public final Object[] h;
    public final int i;
    public int j;
    public int k;

    public RingBuffer(@NotNull Object[] buffer, int i) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        this.h = buffer;
        if (i >= 0) {
            if (i <= buffer.length) {
                this.i = buffer.length;
                this.k = i;
                return;
            }
            throw new IllegalArgumentException(("ring buffer filled size: " + i + " cannot be larger than the buffer size: " + buffer.length).toString());
        }
        throw new IllegalArgumentException(("ring buffer filled size should not be negative but it is " + i).toString());
    }

    public final void e(T t) {
        if (!g()) {
            this.h[(this.j + size()) % this.i] = t;
            this.k = size() + 1;
            return;
        }
        throw new IllegalStateException("ring buffer is full");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final RingBuffer<T> f(int i) {
        Object[] array;
        int i2 = this.i;
        int coerceAtMost = kotlin.ranges.h.coerceAtMost(i2 + (i2 >> 1) + 1, i);
        if (this.j == 0) {
            array = Arrays.copyOf(this.h, coerceAtMost);
            Intrinsics.checkNotNullExpressionValue(array, "copyOf(this, newSize)");
        } else {
            array = toArray(new Object[coerceAtMost]);
        }
        return new RingBuffer<>(array, size());
    }

    public final boolean g() {
        return size() == this.i;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public T get(int i) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, size());
        return (T) this.h[(this.j + i) % this.i];
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.k;
    }

    public final void h(int i) {
        if (i >= 0) {
            if (!(i <= size())) {
                throw new IllegalArgumentException(("n shouldn't be greater than the buffer size: n = " + i + ", size = " + size()).toString());
            } else if (i > 0) {
                int i2 = this.j;
                int i3 = (i2 + i) % this.i;
                if (i2 > i3) {
                    ArraysKt___ArraysJvmKt.fill(this.h, (Object) null, i2, this.i);
                    ArraysKt___ArraysJvmKt.fill(this.h, (Object) null, 0, i3);
                } else {
                    ArraysKt___ArraysJvmKt.fill(this.h, (Object) null, i2, i3);
                }
                this.j = i3;
                this.k = size() - i;
                return;
            } else {
                return;
            }
        }
        throw new IllegalArgumentException(("n shouldn't be negative but it is " + i).toString());
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection, java.util.Collection, java.lang.Iterable
    @NotNull
    public Iterator<T> iterator() {
        return new AbstractIterator<T>(this) { // from class: kotlin.collections.RingBuffer$iterator$1
            public int j;
            public int k;
            public final /* synthetic */ RingBuffer<T> l;

            {
                int i;
                this.l = this;
                this.j = this.size();
                i = this.j;
                this.k = i;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.collections.AbstractIterator
            public void computeNext() {
                Object[] objArr;
                if (this.j != 0) {
                    objArr = this.l.h;
                    setNext(objArr[this.k]);
                    this.k = (this.k + 1) % this.l.i;
                    this.j--;
                    return;
                }
                done();
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    @NotNull
    public <T> T[] toArray(@NotNull T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        if (array.length < size()) {
            array = (T[]) Arrays.copyOf(array, size());
            Intrinsics.checkNotNullExpressionValue(array, "copyOf(this, newSize)");
        }
        int size = size();
        int i = 0;
        int i2 = 0;
        for (int i3 = this.j; i2 < size && i3 < this.i; i3++) {
            array[i2] = this.h[i3];
            i2++;
        }
        while (i2 < size) {
            array[i2] = this.h[i];
            i2++;
            i++;
        }
        if (array.length > size()) {
            array[size()] = null;
        }
        return array;
    }

    public RingBuffer(int i) {
        this(new Object[i], 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    @NotNull
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }
}
