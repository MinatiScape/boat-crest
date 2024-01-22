package com.sifli.ezip;

import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes12.dex */
public class LZWEncoder {

    /* renamed from: a  reason: collision with root package name */
    public int f13695a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int h;
    public int l;
    public int m;
    public int n;
    public int q;
    public byte[] u;
    public int g = 12;
    public int i = 4096;
    public int j = 5003;
    public int k = 0;
    public int o = 0;
    public int p = 0;
    public int[] r = new int[5003];
    public int[] s = new int[5003];
    public int[] t = {0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535};
    public byte[] v = new byte[256];
    public boolean w = false;

    public LZWEncoder(int i, int i2, byte[] bArr, int i3) {
        this.f13695a = i;
        this.b = i2;
        this.u = bArr;
        this.c = Math.max(2, i3);
    }

    public final int a(int i) {
        return (1 << i) - 1;
    }

    public void b(byte b, OutputStream outputStream) throws IOException {
        byte[] bArr = this.v;
        int i = this.q;
        int i2 = i + 1;
        this.q = i2;
        bArr[i] = b;
        if (i2 >= 254) {
            g(outputStream);
        }
    }

    public void c(OutputStream outputStream) throws IOException {
        d(this.j);
        int i = this.m;
        this.k = i + 2;
        this.w = true;
        i(i, outputStream);
    }

    public void d(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            this.r[i2] = -1;
        }
    }

    public void e(int i, OutputStream outputStream) throws IOException {
        int[] iArr;
        this.l = i;
        int i2 = 0;
        this.w = false;
        this.f = i;
        this.h = a(i);
        int i3 = 1 << (i - 1);
        this.m = i3;
        this.n = i3 + 1;
        this.k = i3 + 2;
        this.q = 0;
        int h = h();
        for (int i4 = this.j; i4 < 65536; i4 *= 2) {
            i2++;
        }
        int i5 = 8 - i2;
        int i6 = this.j;
        d(i6);
        i(this.m, outputStream);
        while (true) {
            int h2 = h();
            if (h2 != -1) {
                int i7 = (h2 << this.g) + h;
                int i8 = (h2 << i5) ^ h;
                int[] iArr2 = this.r;
                if (iArr2[i8] == i7) {
                    h = this.s[i8];
                } else {
                    if (iArr2[i8] >= 0) {
                        int i9 = i6 - i8;
                        if (i8 == 0) {
                            i9 = 1;
                        }
                        do {
                            i8 -= i9;
                            if (i8 < 0) {
                                i8 += i6;
                            }
                            iArr = this.r;
                            if (iArr[i8] == i7) {
                                h = this.s[i8];
                                break;
                            }
                        } while (iArr[i8] >= 0);
                    }
                    i(h, outputStream);
                    int i10 = this.k;
                    if (i10 < this.i) {
                        int[] iArr3 = this.s;
                        this.k = i10 + 1;
                        iArr3[i8] = i10;
                        this.r[i8] = i7;
                    } else {
                        c(outputStream);
                    }
                    h = h2;
                }
            } else {
                i(h, outputStream);
                i(this.n, outputStream);
                return;
            }
        }
    }

    public void f(OutputStream outputStream) throws IOException {
        outputStream.write(this.c);
        this.d = this.f13695a * this.b;
        this.e = 0;
        e(this.c + 1, outputStream);
        outputStream.write(0);
    }

    public void g(OutputStream outputStream) throws IOException {
        int i = this.q;
        if (i > 0) {
            outputStream.write(i);
            outputStream.write(this.v, 0, this.q);
            this.q = 0;
        }
    }

    public final int h() {
        int i = this.d;
        if (i == 0) {
            return -1;
        }
        this.d = i - 1;
        byte[] bArr = this.u;
        int i2 = this.e;
        this.e = i2 + 1;
        return bArr[i2] & 255;
    }

    public void i(int i, OutputStream outputStream) throws IOException {
        int i2 = this.o;
        int[] iArr = this.t;
        int i3 = this.p;
        int i4 = i2 & iArr[i3];
        this.o = i4;
        if (i3 > 0) {
            this.o = i4 | (i << i3);
        } else {
            this.o = i;
        }
        this.p = i3 + this.f;
        while (this.p >= 8) {
            b((byte) (this.o & 255), outputStream);
            this.o >>= 8;
            this.p -= 8;
        }
        if (this.k > this.h || this.w) {
            if (this.w) {
                int i5 = this.l;
                this.f = i5;
                this.h = a(i5);
                this.w = false;
            } else {
                int i6 = this.f + 1;
                this.f = i6;
                if (i6 == this.g) {
                    this.h = this.i;
                } else {
                    this.h = a(i6);
                }
            }
        }
        if (i == this.n) {
            while (this.p > 0) {
                b((byte) (this.o & 255), outputStream);
                this.o >>= 8;
                this.p -= 8;
            }
            g(outputStream);
        }
    }
}
