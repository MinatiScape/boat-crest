package com.h6ah4i.android.widget.advrecyclerview.composedadapter;

import java.util.Arrays;
/* loaded from: classes11.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public a f11895a;
    public int[] c;
    public int b = 0;
    public int e = -1;
    public int[] d = new int[128];

    public c(a aVar) {
        this.f11895a = aVar;
        int[] iArr = new int[128];
        this.c = iArr;
        Arrays.fill(iArr, -1);
    }

    public final int a() {
        int g = this.f11895a.g();
        if (g == 0) {
            return 0;
        }
        int i = g - 1;
        return d(i) + c(i);
    }

    public int b(int i, int i2) {
        return d(i) + i2;
    }

    public int c(int i) {
        int[] iArr = this.c;
        if (iArr[i] != -1) {
            return iArr[i];
        }
        int itemCount = this.f11895a.e(i).getItemCount();
        this.c[i] = itemCount;
        if (i == this.b) {
            int[] iArr2 = this.d;
            int i2 = i + 1;
            iArr2[i2] = iArr2[i] + itemCount;
            this.b = i2;
        }
        return itemCount;
    }

    public int d(int i) {
        if (i <= this.b) {
            return this.d[i];
        }
        this.f11895a.g();
        int i2 = this.b;
        int i3 = this.d[i2];
        while (i2 < i) {
            i3 += c(i2);
            i2++;
        }
        return i3;
    }

    public long e(int i) {
        int i2 = -1;
        if (i == -1) {
            return -1L;
        }
        int i3 = 0;
        int binarySearch = Arrays.binarySearch(this.d, 0, this.b, i);
        if (binarySearch >= 0) {
            i2 = binarySearch;
        } else {
            binarySearch = Math.max(0, (~binarySearch) - 1);
            i3 = -1;
        }
        int g = this.f11895a.g();
        int i4 = this.d[binarySearch];
        while (true) {
            if (binarySearch >= g) {
                break;
            }
            int c = c(binarySearch) + i4;
            if (c > i) {
                i3 = i - i4;
                i2 = binarySearch;
                break;
            }
            binarySearch++;
            i4 = c;
        }
        if (i2 >= 0) {
            return a.b(i2, i3);
        }
        return a.f;
    }

    public int f() {
        if (this.e == -1) {
            this.e = a();
        }
        return this.e;
    }

    public void g() {
        this.e = -1;
        this.b = 0;
        Arrays.fill(this.c, -1);
    }

    public void h(int i) {
        this.e = -1;
        this.b = Math.min(this.b, i);
        this.c[i] = -1;
    }

    public void i() {
        this.f11895a = null;
        this.c = null;
        this.d = null;
    }
}
