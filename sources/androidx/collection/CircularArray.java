package androidx.collection;
/* loaded from: classes.dex */
public final class CircularArray<E> {

    /* renamed from: a  reason: collision with root package name */
    public E[] f840a;
    public int b;
    public int c;
    public int d;

    public CircularArray() {
        this(8);
    }

    public final void a() {
        E[] eArr = this.f840a;
        int length = eArr.length;
        int i = this.b;
        int i2 = length - i;
        int i3 = length << 1;
        if (i3 >= 0) {
            E[] eArr2 = (E[]) new Object[i3];
            System.arraycopy(eArr, i, eArr2, 0, i2);
            System.arraycopy(this.f840a, 0, eArr2, i2, this.b);
            this.f840a = eArr2;
            this.b = 0;
            this.c = length;
            this.d = i3 - 1;
            return;
        }
        throw new RuntimeException("Max array capacity exceeded");
    }

    public void addFirst(E e) {
        int i = (this.b - 1) & this.d;
        this.b = i;
        this.f840a[i] = e;
        if (i == this.c) {
            a();
        }
    }

    public void addLast(E e) {
        E[] eArr = this.f840a;
        int i = this.c;
        eArr[i] = e;
        int i2 = this.d & (i + 1);
        this.c = i2;
        if (i2 == this.b) {
            a();
        }
    }

    public void clear() {
        removeFromStart(size());
    }

    public E get(int i) {
        if (i >= 0 && i < size()) {
            return this.f840a[this.d & (this.b + i)];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public E getFirst() {
        int i = this.b;
        if (i != this.c) {
            return this.f840a[i];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public E getLast() {
        int i = this.b;
        int i2 = this.c;
        if (i != i2) {
            return this.f840a[(i2 - 1) & this.d];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public boolean isEmpty() {
        return this.b == this.c;
    }

    public E popFirst() {
        int i = this.b;
        if (i != this.c) {
            E[] eArr = this.f840a;
            E e = eArr[i];
            eArr[i] = null;
            this.b = (i + 1) & this.d;
            return e;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public E popLast() {
        int i = this.b;
        int i2 = this.c;
        if (i != i2) {
            int i3 = this.d & (i2 - 1);
            E[] eArr = this.f840a;
            E e = eArr[i3];
            eArr[i3] = null;
            this.c = i3;
            return e;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void removeFromEnd(int i) {
        int i2;
        if (i <= 0) {
            return;
        }
        if (i <= size()) {
            int i3 = this.c;
            int i4 = i < i3 ? i3 - i : 0;
            int i5 = i4;
            while (true) {
                i2 = this.c;
                if (i5 >= i2) {
                    break;
                }
                this.f840a[i5] = null;
                i5++;
            }
            int i6 = i2 - i4;
            int i7 = i - i6;
            this.c = i2 - i6;
            if (i7 > 0) {
                int length = this.f840a.length;
                this.c = length;
                int i8 = length - i7;
                for (int i9 = i8; i9 < this.c; i9++) {
                    this.f840a[i9] = null;
                }
                this.c = i8;
                return;
            }
            return;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void removeFromStart(int i) {
        if (i <= 0) {
            return;
        }
        if (i <= size()) {
            int length = this.f840a.length;
            int i2 = this.b;
            if (i < length - i2) {
                length = i2 + i;
            }
            while (i2 < length) {
                this.f840a[i2] = null;
                i2++;
            }
            int i3 = this.b;
            int i4 = length - i3;
            int i5 = i - i4;
            this.b = this.d & (i3 + i4);
            if (i5 > 0) {
                for (int i6 = 0; i6 < i5; i6++) {
                    this.f840a[i6] = null;
                }
                this.b = i5;
                return;
            }
            return;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public int size() {
        return (this.c - this.b) & this.d;
    }

    public CircularArray(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        if (i <= 1073741824) {
            i = Integer.bitCount(i) != 1 ? Integer.highestOneBit(i - 1) << 1 : i;
            this.d = i - 1;
            this.f840a = (E[]) new Object[i];
            return;
        }
        throw new IllegalArgumentException("capacity must be <= 2^30");
    }
}
