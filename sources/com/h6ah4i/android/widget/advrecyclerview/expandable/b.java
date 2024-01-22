package com.h6ah4i.android.widget.advrecyclerview.expandable;

import androidx.core.view.InputDeviceCompat;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public long[] f11917a;
    public int[] b;
    public int c;
    public int d;
    public int e;
    public int f = -1;
    public ExpandableItemAdapter g;

    public static int a(long[] jArr, int i, int i2) {
        int i3 = 0;
        if (i <= 0) {
            return 0;
        }
        int i4 = (int) (jArr[0] >>> 32);
        int i5 = (int) (jArr[i] >>> 32);
        if (i2 <= i4) {
            return 0;
        }
        if (i2 >= i5) {
            return i;
        }
        int i6 = 0;
        while (i3 < i) {
            int i7 = (i3 + i) >>> 1;
            if (((int) (jArr[i7] >>> 32)) < i2) {
                i6 = i3;
                i3 = i7 + 1;
            } else {
                i = i7;
            }
        }
        return i6;
    }

    public int A(int i, int i2) {
        int i3;
        if (i2 <= 0) {
            return 0;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            long j = this.f11917a[i + i5];
            if ((2147483648L & j) != 0) {
                int i6 = (int) (j & 2147483647L);
                i4 += i6;
                this.e -= i6;
                this.d--;
            }
        }
        int i7 = i4 + i2;
        this.c -= i2;
        int i8 = i;
        while (true) {
            i3 = this.c;
            if (i8 >= i3) {
                break;
            }
            long[] jArr = this.f11917a;
            int i9 = i8 + i2;
            jArr[i8] = jArr[i9];
            int[] iArr = this.b;
            iArr[i8] = iArr[i9];
            i8++;
        }
        this.f = Math.min(this.f, i3 == 0 ? -1 : i - 1);
        return i7;
    }

    public void B(long[] jArr, ExpandableItemAdapter expandableItemAdapter, RecyclerViewExpandableItemManager.OnGroupExpandListener onGroupExpandListener, RecyclerViewExpandableItemManager.OnGroupCollapseListener onGroupCollapseListener) {
        int i;
        if (jArr == null || jArr.length == 0 || this.f11917a == null) {
            return;
        }
        int i2 = this.c;
        long[] jArr2 = new long[i2];
        for (int i3 = 0; i3 < this.c; i3++) {
            jArr2[i3] = (this.b[i3] << 32) | i3;
        }
        Arrays.sort(jArr2);
        int i4 = 0;
        int i5 = 0;
        while (i4 < jArr.length) {
            int i6 = (int) (jArr[i4] >>> 32);
            boolean z = (jArr[i4] & 2147483648L) != 0;
            int i7 = i5;
            while (true) {
                if (i5 >= i2) {
                    i = i4;
                    break;
                }
                int i8 = (int) (jArr2[i5] >>> 32);
                i = i4;
                int i9 = (int) (jArr2[i5] & 2147483647L);
                if (i8 < i6) {
                    i7 = i5;
                } else if (i8 == i6) {
                    int i10 = i5 + 1;
                    if (z) {
                        if ((expandableItemAdapter == null || expandableItemAdapter.onHookGroupExpand(i9, false, null)) && e(i9) && onGroupExpandListener != null) {
                            onGroupExpandListener.onGroupExpand(i9, false, null);
                        }
                    } else if ((expandableItemAdapter == null || expandableItemAdapter.onHookGroupCollapse(i9, false, null)) && c(i9) && onGroupCollapseListener != null) {
                        onGroupCollapseListener.onGroupCollapse(i9, false, null);
                    }
                    i7 = i10;
                }
                i5++;
                i4 = i;
            }
            i4 = i + 1;
            i5 = i7;
        }
    }

    public void b(ExpandableItemAdapter expandableItemAdapter, int i, boolean z) {
        int groupCount = expandableItemAdapter.getGroupCount();
        boolean z2 = false;
        d(groupCount, false);
        long[] jArr = this.f11917a;
        int[] iArr = this.b;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            boolean z3 = true;
            if (i2 < groupCount) {
                long groupId = expandableItemAdapter.getGroupId(i2);
                int childCount = expandableItemAdapter.getChildCount(i2);
                if (i != 1 && (i == 2 || (!z && !expandableItemAdapter.getInitialGroupExpandedState(i2)))) {
                    z3 = z2;
                }
                long[] jArr2 = jArr;
                jArr2[i2] = childCount | ((i2 + i4) << 32) | (z3 ? 2147483648L : 0L);
                iArr[i2] = (int) (4294967295L & groupId);
                if (z3) {
                    i3++;
                    i4 += childCount;
                }
                i2++;
                jArr = jArr2;
                z2 = false;
            } else {
                this.g = expandableItemAdapter;
                this.c = groupCount;
                this.d = i3;
                this.e = i4;
                this.f = Math.max(0, groupCount - 1);
                return;
            }
        }
    }

    public boolean c(int i) {
        long[] jArr = this.f11917a;
        if ((jArr[i] & 2147483648L) == 0) {
            return false;
        }
        jArr[i] = jArr[i] & (-2147483649L);
        this.d--;
        this.e -= (int) (jArr[i] & 2147483647L);
        this.f = Math.min(this.f, i);
        return true;
    }

    public final void d(int i, boolean z) {
        int i2 = (i + 511) & InputDeviceCompat.SOURCE_ANY;
        long[] jArr = this.f11917a;
        int[] iArr = this.b;
        long[] jArr2 = (jArr == null || jArr.length < i) ? new long[i2] : jArr;
        int[] iArr2 = (iArr == null || iArr.length < i) ? new int[i2] : iArr;
        if (z) {
            if (jArr != null && jArr != jArr2) {
                System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
            }
            if (iArr != null && iArr != iArr2) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            }
        }
        this.f11917a = jArr2;
        this.b = iArr2;
    }

    public boolean e(int i) {
        long[] jArr = this.f11917a;
        if ((jArr[i] & 2147483648L) != 0) {
            return false;
        }
        jArr[i] = jArr[i] | 2147483648L;
        this.d++;
        this.e += (int) (jArr[i] & 2147483647L);
        this.f = Math.min(this.f, i);
        return true;
    }

    public int f(int i) {
        return (int) (this.f11917a[i] & 2147483647L);
    }

    public int g() {
        return this.c - this.d;
    }

    public long h(int i) {
        long j = -1;
        if (i == -1) {
            return -1L;
        }
        int i2 = this.c;
        int a2 = a(this.f11917a, this.f, i);
        int i3 = this.f;
        int i4 = a2 == 0 ? 0 : (int) (this.f11917a[a2] >>> 32);
        while (true) {
            if (a2 >= i2) {
                a2 = i3;
                break;
            }
            long[] jArr = this.f11917a;
            long j2 = jArr[a2];
            jArr[a2] = (i4 << 32) | (4294967295L & j2);
            if (i4 >= i) {
                j = a.c(a2);
                break;
            }
            i4++;
            if ((2147483648L & j2) != 0) {
                int i5 = (int) (j2 & 2147483647L);
                if (i5 > 0 && (i4 + i5) - 1 >= i) {
                    j = a.b(a2, i - i4);
                    break;
                }
                i4 += i5;
            }
            i3 = a2;
            a2++;
        }
        this.f = Math.max(this.f, a2);
        return j;
    }

    public int i() {
        return this.d;
    }

    public int j(long j) {
        int i = -1;
        if (j == -1) {
            return -1;
        }
        int d = a.d(j);
        int a2 = a.a(j);
        int i2 = this.c;
        if (d >= 0 && d < i2) {
            if (a2 != -1 && !u(d)) {
                return -1;
            }
            int max = Math.max(0, Math.min(d, this.f));
            int i3 = this.f;
            int i4 = (int) (this.f11917a[max] >>> 32);
            while (true) {
                if (max >= i2) {
                    max = i3;
                    break;
                }
                long[] jArr = this.f11917a;
                long j2 = jArr[max];
                jArr[max] = (i4 << 32) | (4294967295L & j2);
                int i5 = (int) (2147483647L & j2);
                if (max != d) {
                    i4++;
                    if ((j2 & 2147483648L) != 0) {
                        i4 += i5;
                    }
                    i3 = max;
                    max++;
                } else if (a2 == -1) {
                    i = i4;
                } else if (a2 < i5) {
                    i = i4 + 1 + a2;
                }
            }
            this.f = Math.max(this.f, max);
        }
        return i;
    }

    public int k() {
        return this.c + this.e;
    }

    public long[] l() {
        long[] jArr = new long[this.c];
        for (int i = 0; i < this.c; i++) {
            jArr[i] = (this.f11917a[i] & 2147483648L) | (this.b[i] << 32);
        }
        Arrays.sort(jArr);
        return jArr;
    }

    public int m(int i) {
        if (u(i)) {
            return f(i);
        }
        return 0;
    }

    public void n(int i, int i2) {
        o(i, i2, 1);
    }

    public void o(int i, int i2, int i3) {
        long[] jArr = this.f11917a;
        long j = jArr[i];
        int i4 = (int) (2147483647L & j);
        if (i2 >= 0 && i2 <= i4) {
            if ((2147483648L & j) != 0) {
                this.e += i3;
            }
            jArr[i] = (i4 + i3) | (j & (-2147483648L));
            this.f = Math.min(this.f, i);
            return;
        }
        throw new IllegalStateException("Invalid child position insertChildItems(groupPosition = " + i + ", childPositionStart = " + i2 + ", count = " + i3 + ")");
    }

    public int p(int i, boolean z) {
        return q(i, 1, z);
    }

    public int q(int i, int i2, boolean z) {
        if (i2 <= 0) {
            return 0;
        }
        d(this.c + i2, true);
        ExpandableItemAdapter expandableItemAdapter = this.g;
        long[] jArr = this.f11917a;
        int[] iArr = this.b;
        int i3 = i - 1;
        int i4 = i3 + i2;
        for (int i5 = (this.c - 1) + i2; i5 > i4; i5--) {
            int i6 = i5 - i2;
            jArr[i5] = jArr[i6];
            iArr[i5] = iArr[i6];
        }
        long j = z ? 2147483648L : 0L;
        int i7 = i + i2;
        int i8 = 0;
        int i9 = i;
        while (i9 < i7) {
            long groupId = expandableItemAdapter.getGroupId(i9);
            int childCount = expandableItemAdapter.getChildCount(i9);
            jArr[i9] = childCount | (i9 << 32) | j;
            iArr[i9] = (int) (4294967295L & groupId);
            i8 += childCount;
            i9++;
            expandableItemAdapter = expandableItemAdapter;
            i3 = i3;
        }
        int i10 = i3;
        int i11 = this.c + i2;
        this.c = i11;
        if (z) {
            this.d += i2;
            this.e += i8;
        }
        this.f = Math.min(this.f, i11 == 0 ? -1 : i10);
        return z ? i2 + i8 : i2;
    }

    public boolean r() {
        return t() || this.d == 0;
    }

    public boolean s() {
        return !t() && this.d == this.c;
    }

    public boolean t() {
        return this.c == 0;
    }

    public boolean u(int i) {
        return (this.f11917a[i] & 2147483648L) != 0;
    }

    public void v(int i, int i2, int i3, int i4) {
        if (i == i3) {
            return;
        }
        long[] jArr = this.f11917a;
        int i5 = (int) (jArr[i] & 2147483647L);
        int i6 = (int) (jArr[i3] & 2147483647L);
        if (i5 != 0) {
            jArr[i] = (jArr[i] & (-2147483648L)) | (i5 - 1);
            jArr[i3] = (i6 + 1) | (jArr[i3] & (-2147483648L));
            if ((jArr[i] & 2147483648L) != 0) {
                this.e--;
            }
            if ((jArr[i3] & 2147483648L) != 0) {
                this.e++;
            }
            int min = Math.min(i, i3);
            if (min > 0) {
                this.f = Math.min(this.f, min - 1);
                return;
            } else {
                this.f = -1;
                return;
            }
        }
        throw new IllegalStateException("moveChildItem(fromGroupPosition = " + i + ", fromChildPosition = " + i2 + ", toGroupPosition = " + i3 + ", toChildPosition = " + i4 + ")  --- may be a bug.");
    }

    public void w(int i, int i2) {
        if (i == i2) {
            return;
        }
        long j = this.f11917a[i];
        int i3 = this.b[i];
        if (i2 < i) {
            for (int i4 = i; i4 > i2; i4--) {
                long[] jArr = this.f11917a;
                int i5 = i4 - 1;
                jArr[i4] = jArr[i5];
                int[] iArr = this.b;
                iArr[i4] = iArr[i5];
            }
        } else {
            int i6 = i;
            while (i6 < i2) {
                long[] jArr2 = this.f11917a;
                int i7 = i6 + 1;
                jArr2[i6] = jArr2[i7];
                int[] iArr2 = this.b;
                iArr2[i6] = iArr2[i7];
                i6 = i7;
            }
        }
        this.f11917a[i2] = j;
        this.b[i2] = i3;
        int min = Math.min(i, i2);
        if (min > 0) {
            this.f = Math.min(this.f, min - 1);
        } else {
            this.f = -1;
        }
    }

    public void x(int i, int i2) {
        y(i, i2, 1);
    }

    public void y(int i, int i2, int i3) {
        long[] jArr = this.f11917a;
        long j = jArr[i];
        int i4 = (int) (2147483647L & j);
        if (i2 >= 0 && i2 + i3 <= i4) {
            if ((2147483648L & j) != 0) {
                this.e -= i3;
            }
            jArr[i] = (i4 - i3) | (j & (-2147483648L));
            this.f = Math.min(this.f, i - 1);
            return;
        }
        throw new IllegalStateException("Invalid child position removeChildItems(groupPosition = " + i + ", childPosition = " + i2 + ", count = " + i3 + ")");
    }

    public int z(int i) {
        return A(i, 1);
    }
}
