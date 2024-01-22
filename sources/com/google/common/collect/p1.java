package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes10.dex */
public class p1<K> {

    /* renamed from: a  reason: collision with root package name */
    public transient Object[] f10598a;
    public transient int[] b;
    public transient int c;
    public transient int d;
    public transient int[] e;
    @VisibleForTesting
    public transient long[] f;
    public transient float g;
    public transient int h;

    /* loaded from: classes10.dex */
    public class a extends Multisets.f<K> {
        @NullableDecl
        public final K h;
        public int i;

        public a(int i) {
            this.h = (K) p1.this.f10598a[i];
            this.i = i;
        }

        public void a() {
            int i = this.i;
            if (i == -1 || i >= p1.this.C() || !Objects.equal(this.h, p1.this.f10598a[this.i])) {
                this.i = p1.this.m(this.h);
            }
        }

        @Override // com.google.common.collect.Multiset.Entry
        public int getCount() {
            a();
            int i = this.i;
            if (i == -1) {
                return 0;
            }
            return p1.this.b[i];
        }

        @Override // com.google.common.collect.Multiset.Entry
        public K getElement() {
            return this.h;
        }
    }

    public p1() {
        n(3, 1.0f);
    }

    public static long D(long j, int i) {
        return (j & (-4294967296L)) | (i & 4294967295L);
    }

    public static <K> p1<K> b() {
        return new p1<>();
    }

    public static <K> p1<K> c(int i) {
        return new p1<>(i);
    }

    public static int h(long j) {
        return (int) (j >>> 32);
    }

    public static int j(long j) {
        return (int) j;
    }

    public static long[] q(int i) {
        long[] jArr = new long[i];
        Arrays.fill(jArr, -1L);
        return jArr;
    }

    public static int[] r(int i) {
        int[] iArr = new int[i];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    public final void A(int i) {
        if (this.e.length >= 1073741824) {
            this.h = Integer.MAX_VALUE;
            return;
        }
        int i2 = ((int) (i * this.g)) + 1;
        int[] r = r(i);
        long[] jArr = this.f;
        int length = r.length - 1;
        for (int i3 = 0; i3 < this.c; i3++) {
            int h = h(jArr[i3]);
            int i4 = h & length;
            int i5 = r[i4];
            r[i4] = i3;
            jArr[i3] = (h << 32) | (i5 & 4294967295L);
        }
        this.h = i2;
        this.e = r;
    }

    public void B(int i, int i2) {
        Preconditions.checkElementIndex(i, this.c);
        this.b[i] = i2;
    }

    public int C() {
        return this.c;
    }

    public void a() {
        this.d++;
        Arrays.fill(this.f10598a, 0, this.c, (Object) null);
        Arrays.fill(this.b, 0, this.c, 0);
        Arrays.fill(this.e, -1);
        Arrays.fill(this.f, -1L);
        this.c = 0;
    }

    public void d(int i) {
        if (i > this.f.length) {
            y(i);
        }
        if (i >= this.h) {
            A(Math.max(2, Integer.highestOneBit(i - 1) << 1));
        }
    }

    public int e() {
        return this.c == 0 ? -1 : 0;
    }

    public int f(@NullableDecl Object obj) {
        int m = m(obj);
        if (m == -1) {
            return 0;
        }
        return this.b[m];
    }

    public Multiset.Entry<K> g(int i) {
        Preconditions.checkElementIndex(i, this.c);
        return new a(i);
    }

    public K i(int i) {
        Preconditions.checkElementIndex(i, this.c);
        return (K) this.f10598a[i];
    }

    public int k(int i) {
        Preconditions.checkElementIndex(i, this.c);
        return this.b[i];
    }

    public final int l() {
        return this.e.length - 1;
    }

    public int m(@NullableDecl Object obj) {
        int d = w0.d(obj);
        int i = this.e[l() & d];
        while (i != -1) {
            long j = this.f[i];
            if (h(j) == d && Objects.equal(obj, this.f10598a[i])) {
                return i;
            }
            i = j(j);
        }
        return -1;
    }

    public void n(int i, float f) {
        Preconditions.checkArgument(i >= 0, "Initial capacity must be non-negative");
        Preconditions.checkArgument(f > 0.0f, "Illegal load factor");
        int a2 = w0.a(i, f);
        this.e = r(a2);
        this.g = f;
        this.f10598a = new Object[i];
        this.b = new int[i];
        this.f = q(i);
        this.h = Math.max(1, (int) (a2 * f));
    }

    public void o(int i, @NullableDecl K k, int i2, int i3) {
        this.f[i] = (i3 << 32) | 4294967295L;
        this.f10598a[i] = k;
        this.b[i] = i2;
    }

    public void p(int i) {
        int C = C() - 1;
        if (i < C) {
            Object[] objArr = this.f10598a;
            objArr[i] = objArr[C];
            int[] iArr = this.b;
            iArr[i] = iArr[C];
            objArr[C] = null;
            iArr[C] = 0;
            long[] jArr = this.f;
            long j = jArr[C];
            jArr[i] = j;
            jArr[C] = -1;
            int h = h(j) & l();
            int[] iArr2 = this.e;
            int i2 = iArr2[h];
            if (i2 == C) {
                iArr2[h] = i;
                return;
            }
            while (true) {
                long j2 = this.f[i2];
                int j3 = j(j2);
                if (j3 == C) {
                    this.f[i2] = D(j2, i);
                    return;
                }
                i2 = j3;
            }
        } else {
            this.f10598a[i] = null;
            this.b[i] = 0;
            this.f[i] = -1;
        }
    }

    public int s(int i) {
        int i2 = i + 1;
        if (i2 < this.c) {
            return i2;
        }
        return -1;
    }

    public int t(int i, int i2) {
        return i - 1;
    }

    @CanIgnoreReturnValue
    public int u(@NullableDecl K k, int i) {
        u.d(i, "count");
        long[] jArr = this.f;
        Object[] objArr = this.f10598a;
        int[] iArr = this.b;
        int d = w0.d(k);
        int l = l() & d;
        int i2 = this.c;
        int[] iArr2 = this.e;
        int i3 = iArr2[l];
        if (i3 == -1) {
            iArr2[l] = i2;
        } else {
            while (true) {
                long j = jArr[i3];
                if (h(j) == d && Objects.equal(k, objArr[i3])) {
                    int i4 = iArr[i3];
                    iArr[i3] = i;
                    return i4;
                }
                int j2 = j(j);
                if (j2 == -1) {
                    jArr[i3] = D(j, i2);
                    break;
                }
                i3 = j2;
            }
        }
        if (i2 != Integer.MAX_VALUE) {
            int i5 = i2 + 1;
            z(i5);
            o(i2, k, i, d);
            this.c = i5;
            if (i2 >= this.h) {
                A(this.e.length * 2);
            }
            this.d++;
            return 0;
        }
        throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
    }

    @CanIgnoreReturnValue
    public int v(@NullableDecl Object obj) {
        return w(obj, w0.d(obj));
    }

    public final int w(@NullableDecl Object obj, int i) {
        int l = l() & i;
        int i2 = this.e[l];
        if (i2 == -1) {
            return 0;
        }
        int i3 = -1;
        while (true) {
            if (h(this.f[i2]) == i && Objects.equal(obj, this.f10598a[i2])) {
                int i4 = this.b[i2];
                if (i3 == -1) {
                    this.e[l] = j(this.f[i2]);
                } else {
                    long[] jArr = this.f;
                    jArr[i3] = D(jArr[i3], j(jArr[i2]));
                }
                p(i2);
                this.c--;
                this.d++;
                return i4;
            }
            int j = j(this.f[i2]);
            if (j == -1) {
                return 0;
            }
            i3 = i2;
            i2 = j;
        }
    }

    @CanIgnoreReturnValue
    public int x(int i) {
        return w(this.f10598a[i], h(this.f[i]));
    }

    public void y(int i) {
        this.f10598a = Arrays.copyOf(this.f10598a, i);
        this.b = Arrays.copyOf(this.b, i);
        long[] jArr = this.f;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, i);
        if (i > length) {
            Arrays.fill(copyOf, length, i, -1L);
        }
        this.f = copyOf;
    }

    public final void z(int i) {
        int length = this.f.length;
        if (i > length) {
            int max = Math.max(1, length >>> 1) + length;
            if (max < 0) {
                max = Integer.MAX_VALUE;
            }
            if (max != length) {
                y(max);
            }
        }
    }

    public p1(p1<? extends K> p1Var) {
        n(p1Var.C(), 1.0f);
        int e = p1Var.e();
        while (e != -1) {
            u(p1Var.i(e), p1Var.k(e));
            e = p1Var.s(e);
        }
    }

    public p1(int i) {
        this(i, 1.0f);
    }

    public p1(int i, float f) {
        n(i, f);
    }
}
