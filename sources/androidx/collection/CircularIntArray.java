package androidx.collection;
/* loaded from: classes.dex */
public final class CircularIntArray {

    /* renamed from: a  reason: collision with root package name */
    public int[] f841a;
    public int b;
    public int c;
    public int d;

    public CircularIntArray() {
        this(8);
    }

    public final void a() {
        int[] iArr = this.f841a;
        int length = iArr.length;
        int i = this.b;
        int i2 = length - i;
        int i3 = length << 1;
        if (i3 >= 0) {
            int[] iArr2 = new int[i3];
            System.arraycopy(iArr, i, iArr2, 0, i2);
            System.arraycopy(this.f841a, 0, iArr2, i2, this.b);
            this.f841a = iArr2;
            this.b = 0;
            this.c = length;
            this.d = i3 - 1;
            return;
        }
        throw new RuntimeException("Max array capacity exceeded");
    }

    public void addFirst(int i) {
        int i2 = (this.b - 1) & this.d;
        this.b = i2;
        this.f841a[i2] = i;
        if (i2 == this.c) {
            a();
        }
    }

    public void addLast(int i) {
        int[] iArr = this.f841a;
        int i2 = this.c;
        iArr[i2] = i;
        int i3 = this.d & (i2 + 1);
        this.c = i3;
        if (i3 == this.b) {
            a();
        }
    }

    public void clear() {
        this.c = this.b;
    }

    public int get(int i) {
        if (i >= 0 && i < size()) {
            return this.f841a[this.d & (this.b + i)];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public int getFirst() {
        int i = this.b;
        if (i != this.c) {
            return this.f841a[i];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public int getLast() {
        int i = this.b;
        int i2 = this.c;
        if (i != i2) {
            return this.f841a[(i2 - 1) & this.d];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public boolean isEmpty() {
        return this.b == this.c;
    }

    public int popFirst() {
        int i = this.b;
        if (i != this.c) {
            int i2 = this.f841a[i];
            this.b = (i + 1) & this.d;
            return i2;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public int popLast() {
        int i = this.b;
        int i2 = this.c;
        if (i != i2) {
            int i3 = this.d & (i2 - 1);
            int i4 = this.f841a[i3];
            this.c = i3;
            return i4;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void removeFromEnd(int i) {
        if (i <= 0) {
            return;
        }
        if (i <= size()) {
            this.c = this.d & (this.c - i);
            return;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void removeFromStart(int i) {
        if (i <= 0) {
            return;
        }
        if (i <= size()) {
            this.b = this.d & (this.b + i);
            return;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public int size() {
        return (this.c - this.b) & this.d;
    }

    public CircularIntArray(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        if (i <= 1073741824) {
            i = Integer.bitCount(i) != 1 ? Integer.highestOneBit(i - 1) << 1 : i;
            this.d = i - 1;
            this.f841a = new int[i];
            return;
        }
        throw new IllegalArgumentException("capacity must be <= 2^30");
    }
}
