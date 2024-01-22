package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import java.util.LinkedList;
/* loaded from: classes11.dex */
public final class c {
    public static final c e = new c(d.b, 0, 0, 0);

    /* renamed from: a  reason: collision with root package name */
    public final int f11776a;
    public final d b;
    public final int c;
    public final int d;

    public c(d dVar, int i, int i2, int i3) {
        this.b = dVar;
        this.f11776a = i;
        this.c = i2;
        this.d = i3;
    }

    public static int b(c cVar) {
        int i = cVar.c;
        if (i > 62) {
            return 21;
        }
        if (i > 31) {
            return 20;
        }
        return i > 0 ? 10 : 0;
    }

    public c a(int i) {
        d dVar = this.b;
        int i2 = this.f11776a;
        int i3 = this.d;
        if (i2 == 4 || i2 == 2) {
            int i4 = HighLevelEncoder.c[i2][0];
            int i5 = 65535 & i4;
            int i6 = i4 >> 16;
            dVar = dVar.a(i5, i6);
            i3 += i6;
            i2 = 0;
        }
        int i7 = this.c;
        c cVar = new c(dVar, i2, i7 + 1, i3 + ((i7 == 0 || i7 == 31) ? 18 : i7 == 62 ? 9 : 8));
        return cVar.c == 2078 ? cVar.c(i + 1) : cVar;
    }

    public c c(int i) {
        int i2 = this.c;
        return i2 == 0 ? this : new c(this.b.b(i - i2, i2), this.f11776a, 0, this.d);
    }

    public int d() {
        return this.c;
    }

    public int e() {
        return this.d;
    }

    public int f() {
        return this.f11776a;
    }

    public boolean g(c cVar) {
        int i = this.d + (HighLevelEncoder.c[this.f11776a][cVar.f11776a] >> 16);
        int i2 = this.c;
        int i3 = cVar.c;
        if (i2 < i3) {
            i += b(cVar) - b(this);
        } else if (i2 > i3 && i3 > 0) {
            i += 10;
        }
        return i <= cVar.d;
    }

    public c h(int i, int i2) {
        int i3 = this.d;
        d dVar = this.b;
        int i4 = this.f11776a;
        if (i != i4) {
            int i5 = HighLevelEncoder.c[i4][i];
            int i6 = 65535 & i5;
            int i7 = i5 >> 16;
            dVar = dVar.a(i6, i7);
            i3 += i7;
        }
        int i8 = i == 2 ? 4 : 5;
        return new c(dVar.a(i2, i8), i, 0, i3 + i8);
    }

    public c i(int i, int i2) {
        d dVar = this.b;
        int i3 = this.f11776a;
        int i4 = i3 == 2 ? 4 : 5;
        return new c(dVar.a(HighLevelEncoder.e[i3][i], i4).a(i2, 5), this.f11776a, 0, this.d + i4 + 5);
    }

    public BitArray j(byte[] bArr) {
        LinkedList<d> linkedList = new LinkedList();
        for (d dVar = c(bArr.length).b; dVar != null; dVar = dVar.d()) {
            linkedList.addFirst(dVar);
        }
        BitArray bitArray = new BitArray();
        for (d dVar2 : linkedList) {
            dVar2.c(bitArray, bArr);
        }
        return bitArray;
    }

    public String toString() {
        return String.format("%s bits=%d bytes=%d", HighLevelEncoder.b[this.f11776a], Integer.valueOf(this.d), Integer.valueOf(this.c));
    }
}
