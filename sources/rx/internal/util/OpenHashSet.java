package rx.internal.util;

import java.util.Arrays;
import rx.functions.Action1;
import rx.internal.util.unsafe.Pow2;
/* loaded from: classes13.dex */
public final class OpenHashSet<T> {

    /* renamed from: a  reason: collision with root package name */
    public final float f15685a;
    public int b;
    public int c;
    public int d;
    public T[] e;

    public OpenHashSet() {
        this(16, 0.75f);
    }

    public static int a(int i) {
        int i2 = i * (-1640531527);
        return i2 ^ (i2 >>> 16);
    }

    public boolean add(T t) {
        T t2;
        T[] tArr = this.e;
        int i = this.b;
        int a2 = a(t.hashCode()) & i;
        T t3 = tArr[a2];
        if (t3 != null) {
            if (t3.equals(t)) {
                return false;
            }
            do {
                a2 = (a2 + 1) & i;
                t2 = tArr[a2];
                if (t2 == null) {
                }
            } while (!t2.equals(t));
            return false;
        }
        tArr[a2] = t;
        int i2 = this.c + 1;
        this.c = i2;
        if (i2 >= this.d) {
            b();
        }
        return true;
    }

    public void b() {
        T[] tArr = this.e;
        int length = tArr.length;
        int i = length << 1;
        int i2 = i - 1;
        T[] tArr2 = (T[]) new Object[i];
        int i3 = this.c;
        while (true) {
            int i4 = i3 - 1;
            if (i3 != 0) {
                do {
                    length--;
                } while (tArr[length] == null);
                int a2 = a(tArr[length].hashCode()) & i2;
                if (tArr2[a2] != null) {
                    do {
                        a2 = (a2 + 1) & i2;
                    } while (tArr2[a2] != null);
                }
                tArr2[a2] = tArr[length];
                i3 = i4;
            } else {
                this.b = i2;
                this.d = (int) (i * this.f15685a);
                this.e = tArr2;
                return;
            }
        }
    }

    public boolean c(int i, T[] tArr, int i2) {
        int i3;
        T t;
        this.c--;
        while (true) {
            int i4 = i + 1;
            while (true) {
                i3 = i4 & i2;
                t = tArr[i3];
                if (t == null) {
                    tArr[i] = null;
                    return true;
                }
                int a2 = a(t.hashCode()) & i2;
                if (i > i3) {
                    if (i >= a2 && a2 > i3) {
                        break;
                    }
                    i4 = i3 + 1;
                } else if (i < a2 && a2 <= i3) {
                    i4 = i3 + 1;
                }
            }
            tArr[i] = t;
            i = i3;
        }
    }

    public void clear(Action1<? super T> action1) {
        if (this.c == 0) {
            return;
        }
        T[] tArr = this.e;
        for (T t : tArr) {
            Object obj = (Object) t;
            if (obj != 0) {
                action1.call(obj);
            }
        }
        Arrays.fill(tArr, (Object) null);
        this.c = 0;
    }

    public boolean isEmpty() {
        return this.c == 0;
    }

    public boolean remove(T t) {
        T t2;
        T[] tArr = this.e;
        int i = this.b;
        int a2 = a(t.hashCode()) & i;
        T t3 = tArr[a2];
        if (t3 == null) {
            return false;
        }
        if (t3.equals(t)) {
            return c(a2, tArr, i);
        }
        do {
            a2 = (a2 + 1) & i;
            t2 = tArr[a2];
            if (t2 == null) {
                return false;
            }
        } while (!t2.equals(t));
        return c(a2, tArr, i);
    }

    public void terminate() {
        this.c = 0;
        this.e = (T[]) new Object[0];
    }

    public T[] values() {
        return this.e;
    }

    public OpenHashSet(int i) {
        this(i, 0.75f);
    }

    public OpenHashSet(int i, float f) {
        this.f15685a = f;
        int roundToPowerOfTwo = Pow2.roundToPowerOfTwo(i);
        this.b = roundToPowerOfTwo - 1;
        this.d = (int) (f * roundToPowerOfTwo);
        this.e = (T[]) new Object[roundToPowerOfTwo];
    }
}
