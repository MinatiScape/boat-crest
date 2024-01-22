package com.google.zxing.datamatrix.encoder;

import java.util.Arrays;
/* loaded from: classes11.dex */
public class DefaultPlacement {

    /* renamed from: a  reason: collision with root package name */
    public final CharSequence f11808a;
    public final int b;
    public final int c;
    public final byte[] d;

    public DefaultPlacement(CharSequence charSequence, int i, int i2) {
        this.f11808a = charSequence;
        this.c = i;
        this.b = i2;
        byte[] bArr = new byte[i * i2];
        this.d = bArr;
        Arrays.fill(bArr, (byte) -1);
    }

    public final void a(int i) {
        e(this.b - 1, 0, i, 1);
        e(this.b - 1, 1, i, 2);
        e(this.b - 1, 2, i, 3);
        e(0, this.c - 2, i, 4);
        e(0, this.c - 1, i, 5);
        e(1, this.c - 1, i, 6);
        e(2, this.c - 1, i, 7);
        e(3, this.c - 1, i, 8);
    }

    public final void b(int i) {
        e(this.b - 3, 0, i, 1);
        e(this.b - 2, 0, i, 2);
        e(this.b - 1, 0, i, 3);
        e(0, this.c - 4, i, 4);
        e(0, this.c - 3, i, 5);
        e(0, this.c - 2, i, 6);
        e(0, this.c - 1, i, 7);
        e(1, this.c - 1, i, 8);
    }

    public final void c(int i) {
        e(this.b - 3, 0, i, 1);
        e(this.b - 2, 0, i, 2);
        e(this.b - 1, 0, i, 3);
        e(0, this.c - 2, i, 4);
        e(0, this.c - 1, i, 5);
        e(1, this.c - 1, i, 6);
        e(2, this.c - 1, i, 7);
        e(3, this.c - 1, i, 8);
    }

    public final void d(int i) {
        e(this.b - 1, 0, i, 1);
        e(this.b - 1, this.c - 1, i, 2);
        e(0, this.c - 3, i, 3);
        e(0, this.c - 2, i, 4);
        e(0, this.c - 1, i, 5);
        e(1, this.c - 3, i, 6);
        e(1, this.c - 2, i, 7);
        e(1, this.c - 1, i, 8);
    }

    public final void e(int i, int i2, int i3, int i4) {
        if (i < 0) {
            int i5 = this.b;
            i += i5;
            i2 += 4 - ((i5 + 4) % 8);
        }
        if (i2 < 0) {
            int i6 = this.c;
            i2 += i6;
            i += 4 - ((i6 + 4) % 8);
        }
        g(i2, i, (this.f11808a.charAt(i3) & (1 << (8 - i4))) != 0);
    }

    public final boolean f(int i, int i2) {
        return this.d[(i2 * this.c) + i] < 0;
    }

    public final void g(int i, int i2, boolean z) {
        this.d[(i2 * this.c) + i] = z ? (byte) 1 : (byte) 0;
    }

    public final boolean getBit(int i, int i2) {
        return this.d[(i2 * this.c) + i] == 1;
    }

    public final void h(int i, int i2, int i3) {
        int i4 = i - 2;
        int i5 = i2 - 2;
        e(i4, i5, i3, 1);
        int i6 = i2 - 1;
        e(i4, i6, i3, 2);
        int i7 = i - 1;
        e(i7, i5, i3, 3);
        e(i7, i6, i3, 4);
        e(i7, i2, i3, 5);
        e(i, i5, i3, 6);
        e(i, i6, i3, 7);
        e(i, i2, i3, 8);
    }

    public final void place() {
        int i;
        int i2;
        int i3 = 0;
        int i4 = 0;
        int i5 = 4;
        while (true) {
            if (i5 == this.b && i3 == 0) {
                a(i4);
                i4++;
            }
            if (i5 == this.b - 2 && i3 == 0 && this.c % 4 != 0) {
                b(i4);
                i4++;
            }
            if (i5 == this.b - 2 && i3 == 0 && this.c % 8 == 4) {
                c(i4);
                i4++;
            }
            if (i5 == this.b + 4 && i3 == 2 && this.c % 8 == 0) {
                d(i4);
                i4++;
            }
            do {
                if (i5 < this.b && i3 >= 0 && f(i3, i5)) {
                    h(i5, i3, i4);
                    i4++;
                }
                i5 -= 2;
                i3 += 2;
                if (i5 < 0) {
                    break;
                }
            } while (i3 < this.c);
            int i6 = i5 + 1;
            int i7 = i3 + 3;
            do {
                if (i6 >= 0 && i7 < this.c && f(i7, i6)) {
                    h(i6, i7, i4);
                    i4++;
                }
                i6 += 2;
                i7 -= 2;
                i = this.b;
                if (i6 >= i) {
                    break;
                }
            } while (i7 >= 0);
            i5 = i6 + 3;
            i3 = i7 + 1;
            if (i5 >= i && i3 >= (i2 = this.c)) {
                break;
            }
        }
        if (f(i2 - 1, i - 1)) {
            g(this.c - 1, this.b - 1, true);
            g(this.c - 2, this.b - 2, true);
        }
    }
}
