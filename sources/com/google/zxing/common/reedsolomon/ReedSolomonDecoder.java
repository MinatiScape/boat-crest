package com.google.zxing.common.reedsolomon;
/* loaded from: classes11.dex */
public final class ReedSolomonDecoder {

    /* renamed from: a  reason: collision with root package name */
    public final GenericGF f11795a;

    public ReedSolomonDecoder(GenericGF genericGF) {
        this.f11795a = genericGF;
    }

    public final int[] a(a aVar) throws ReedSolomonException {
        int f = aVar.f();
        int i = 0;
        if (f == 1) {
            return new int[]{aVar.d(1)};
        }
        int[] iArr = new int[f];
        for (int i2 = 1; i2 < this.f11795a.getSize() && i < f; i2++) {
            if (aVar.c(i2) == 0) {
                iArr[i] = this.f11795a.f(i2);
                i++;
            }
        }
        if (i == f) {
            return iArr;
        }
        throw new ReedSolomonException("Error locator degree does not match number of roots");
    }

    public final int[] b(a aVar, int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            int f = this.f11795a.f(iArr[i]);
            int i2 = 1;
            for (int i3 = 0; i3 < length; i3++) {
                if (i != i3) {
                    int h = this.f11795a.h(iArr[i3], f);
                    i2 = this.f11795a.h(i2, (h & 1) == 0 ? h | 1 : h & (-2));
                }
            }
            iArr2[i] = this.f11795a.h(aVar.c(f), this.f11795a.f(i2));
            if (this.f11795a.getGeneratorBase() != 0) {
                iArr2[i] = this.f11795a.h(iArr2[i], f);
            }
        }
        return iArr2;
    }

    public final a[] c(a aVar, a aVar2, int i) throws ReedSolomonException {
        if (aVar.f() < aVar2.f()) {
            aVar2 = aVar;
            aVar = aVar2;
        }
        a e = this.f11795a.e();
        a d = this.f11795a.d();
        do {
            a aVar3 = aVar2;
            aVar2 = aVar;
            aVar = aVar3;
            a aVar4 = d;
            a aVar5 = e;
            e = aVar4;
            if (aVar.f() >= i / 2) {
                if (!aVar.g()) {
                    a e2 = this.f11795a.e();
                    int f = this.f11795a.f(aVar.d(aVar.f()));
                    while (aVar2.f() >= aVar.f() && !aVar2.g()) {
                        int f2 = aVar2.f() - aVar.f();
                        int h = this.f11795a.h(aVar2.d(aVar2.f()), f);
                        e2 = e2.a(this.f11795a.b(f2, h));
                        aVar2 = aVar2.a(aVar.j(f2, h));
                    }
                    d = e2.i(e).a(aVar5);
                } else {
                    throw new ReedSolomonException("r_{i-1} was zero");
                }
            } else {
                int d2 = e.d(0);
                if (d2 != 0) {
                    int f3 = this.f11795a.f(d2);
                    return new a[]{e.h(f3), aVar.h(f3)};
                }
                throw new ReedSolomonException("sigmaTilde(0) was zero");
            }
        } while (aVar2.f() < aVar.f());
        throw new IllegalStateException("Division algorithm failed to reduce polynomial?");
    }

    public void decode(int[] iArr, int i) throws ReedSolomonException {
        a aVar = new a(this.f11795a, iArr);
        int[] iArr2 = new int[i];
        boolean z = true;
        for (int i2 = 0; i2 < i; i2++) {
            GenericGF genericGF = this.f11795a;
            int c = aVar.c(genericGF.c(genericGF.getGeneratorBase() + i2));
            iArr2[(i - 1) - i2] = c;
            if (c != 0) {
                z = false;
            }
        }
        if (z) {
            return;
        }
        a[] c2 = c(this.f11795a.b(i, 1), new a(this.f11795a, iArr2), i);
        a aVar2 = c2[0];
        a aVar3 = c2[1];
        int[] a2 = a(aVar2);
        int[] b = b(aVar3, a2);
        for (int i3 = 0; i3 < a2.length; i3++) {
            int length = (iArr.length - 1) - this.f11795a.g(a2[i3]);
            if (length >= 0) {
                iArr[length] = GenericGF.a(iArr[length], b[i3]);
            } else {
                throw new ReedSolomonException("Bad error location");
            }
        }
    }
}
