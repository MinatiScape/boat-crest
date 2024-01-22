package com.google.zxing.pdf417.decoder.ec;

import com.google.zxing.ChecksumException;
/* loaded from: classes11.dex */
public final class ErrorCorrection {

    /* renamed from: a  reason: collision with root package name */
    public final ModulusGF f11853a = ModulusGF.PDF417_GF;

    public final int[] a(a aVar) throws ChecksumException {
        int d = aVar.d();
        int[] iArr = new int[d];
        int i = 0;
        for (int i2 = 1; i2 < this.f11853a.e() && i < d; i2++) {
            if (aVar.b(i2) == 0) {
                iArr[i] = this.f11853a.g(i2);
                i++;
            }
        }
        if (i == d) {
            return iArr;
        }
        throw ChecksumException.getChecksumInstance();
    }

    public final int[] b(a aVar, a aVar2, int[] iArr) {
        int d = aVar2.d();
        int[] iArr2 = new int[d];
        for (int i = 1; i <= d; i++) {
            iArr2[d - i] = this.f11853a.i(i, aVar2.c(i));
        }
        a aVar3 = new a(this.f11853a, iArr2);
        int length = iArr.length;
        int[] iArr3 = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            int g = this.f11853a.g(iArr[i2]);
            iArr3[i2] = this.f11853a.i(this.f11853a.j(0, aVar.b(g)), this.f11853a.g(aVar3.b(g)));
        }
        return iArr3;
    }

    public final a[] c(a aVar, a aVar2, int i) throws ChecksumException {
        if (aVar.d() < aVar2.d()) {
            aVar2 = aVar;
            aVar = aVar2;
        }
        a f = this.f11853a.f();
        a d = this.f11853a.d();
        while (true) {
            a aVar3 = aVar2;
            aVar2 = aVar;
            aVar = aVar3;
            a aVar4 = d;
            a aVar5 = f;
            f = aVar4;
            if (aVar.d() >= i / 2) {
                if (!aVar.e()) {
                    a f2 = this.f11853a.f();
                    int g = this.f11853a.g(aVar.c(aVar.d()));
                    while (aVar2.d() >= aVar.d() && !aVar2.e()) {
                        int d2 = aVar2.d() - aVar.d();
                        int i2 = this.f11853a.i(aVar2.c(aVar2.d()), g);
                        f2 = f2.a(this.f11853a.b(d2, i2));
                        aVar2 = aVar2.j(aVar.h(d2, i2));
                    }
                    d = f2.g(f).j(aVar5).i();
                } else {
                    throw ChecksumException.getChecksumInstance();
                }
            } else {
                int c = f.c(0);
                if (c != 0) {
                    int g2 = this.f11853a.g(c);
                    return new a[]{f.f(g2), aVar.f(g2)};
                }
                throw ChecksumException.getChecksumInstance();
            }
        }
    }

    public int decode(int[] iArr, int i, int[] iArr2) throws ChecksumException {
        a aVar = new a(this.f11853a, iArr);
        int[] iArr3 = new int[i];
        boolean z = false;
        for (int i2 = i; i2 > 0; i2--) {
            int b = aVar.b(this.f11853a.c(i2));
            iArr3[i - i2] = b;
            if (b != 0) {
                z = true;
            }
        }
        if (z) {
            a d = this.f11853a.d();
            if (iArr2 != null) {
                for (int i3 : iArr2) {
                    int c = this.f11853a.c((iArr.length - 1) - i3);
                    ModulusGF modulusGF = this.f11853a;
                    d = d.g(new a(modulusGF, new int[]{modulusGF.j(0, c), 1}));
                }
            }
            a[] c2 = c(this.f11853a.b(i, 1), new a(this.f11853a, iArr3), i);
            a aVar2 = c2[0];
            a aVar3 = c2[1];
            int[] a2 = a(aVar2);
            int[] b2 = b(aVar3, aVar2, a2);
            for (int i4 = 0; i4 < a2.length; i4++) {
                int length = (iArr.length - 1) - this.f11853a.h(a2[i4]);
                if (length >= 0) {
                    iArr[length] = this.f11853a.j(iArr[length], b2[i4]);
                } else {
                    throw ChecksumException.getChecksumInstance();
                }
            }
            return a2.length;
        }
        return 0;
    }
}
