package com.google.zxing.common.reedsolomon;

import com.jstyle.blesdk1860.constant.BleConst;
/* loaded from: classes11.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public final GenericGF f11797a;
    public final int[] b;

    public a(GenericGF genericGF, int[] iArr) {
        if (iArr.length != 0) {
            this.f11797a = genericGF;
            int length = iArr.length;
            if (length > 1 && iArr[0] == 0) {
                int i = 1;
                while (i < length && iArr[i] == 0) {
                    i++;
                }
                if (i == length) {
                    this.b = new int[]{0};
                    return;
                }
                int[] iArr2 = new int[length - i];
                this.b = iArr2;
                System.arraycopy(iArr, i, iArr2, 0, iArr2.length);
                return;
            }
            this.b = iArr;
            return;
        }
        throw new IllegalArgumentException();
    }

    public a a(a aVar) {
        if (this.f11797a.equals(aVar.f11797a)) {
            if (g()) {
                return aVar;
            }
            if (aVar.g()) {
                return this;
            }
            int[] iArr = this.b;
            int[] iArr2 = aVar.b;
            if (iArr.length <= iArr2.length) {
                iArr = iArr2;
                iArr2 = iArr;
            }
            int[] iArr3 = new int[iArr.length];
            int length = iArr.length - iArr2.length;
            System.arraycopy(iArr, 0, iArr3, 0, length);
            for (int i = length; i < iArr.length; i++) {
                iArr3[i] = GenericGF.a(iArr2[i - length], iArr[i]);
            }
            return new a(this.f11797a, iArr3);
        }
        throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
    }

    public a[] b(a aVar) {
        if (this.f11797a.equals(aVar.f11797a)) {
            if (!aVar.g()) {
                a e = this.f11797a.e();
                int f = this.f11797a.f(aVar.d(aVar.f()));
                a aVar2 = this;
                while (aVar2.f() >= aVar.f() && !aVar2.g()) {
                    int f2 = aVar2.f() - aVar.f();
                    int h = this.f11797a.h(aVar2.d(aVar2.f()), f);
                    a j = aVar.j(f2, h);
                    e = e.a(this.f11797a.b(f2, h));
                    aVar2 = aVar2.a(j);
                }
                return new a[]{e, aVar2};
            }
            throw new IllegalArgumentException("Divide by 0");
        }
        throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
    }

    public int c(int i) {
        if (i == 0) {
            return d(0);
        }
        if (i == 1) {
            int i2 = 0;
            for (int i3 : this.b) {
                i2 = GenericGF.a(i2, i3);
            }
            return i2;
        }
        int[] iArr = this.b;
        int i4 = iArr[0];
        int length = iArr.length;
        for (int i5 = 1; i5 < length; i5++) {
            i4 = GenericGF.a(this.f11797a.h(i, i4), this.b[i5]);
        }
        return i4;
    }

    public int d(int i) {
        int[] iArr = this.b;
        return iArr[(iArr.length - 1) - i];
    }

    public int[] e() {
        return this.b;
    }

    public int f() {
        return this.b.length - 1;
    }

    public boolean g() {
        return this.b[0] == 0;
    }

    public a h(int i) {
        if (i == 0) {
            return this.f11797a.e();
        }
        if (i == 1) {
            return this;
        }
        int length = this.b.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.f11797a.h(this.b[i2], i);
        }
        return new a(this.f11797a, iArr);
    }

    public a i(a aVar) {
        if (this.f11797a.equals(aVar.f11797a)) {
            if (!g() && !aVar.g()) {
                int[] iArr = this.b;
                int length = iArr.length;
                int[] iArr2 = aVar.b;
                int length2 = iArr2.length;
                int[] iArr3 = new int[(length + length2) - 1];
                for (int i = 0; i < length; i++) {
                    int i2 = iArr[i];
                    for (int i3 = 0; i3 < length2; i3++) {
                        int i4 = i + i3;
                        iArr3[i4] = GenericGF.a(iArr3[i4], this.f11797a.h(i2, iArr2[i3]));
                    }
                }
                return new a(this.f11797a, iArr3);
            }
            return this.f11797a.e();
        }
        throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
    }

    public a j(int i, int i2) {
        if (i >= 0) {
            if (i2 == 0) {
                return this.f11797a.e();
            }
            int length = this.b.length;
            int[] iArr = new int[i + length];
            for (int i3 = 0; i3 < length; i3++) {
                iArr[i3] = this.f11797a.h(this.b[i3], i2);
            }
            return new a(this.f11797a, iArr);
        }
        throw new IllegalArgumentException();
    }

    public String toString() {
        if (g()) {
            return BleConst.GetDeviceTime;
        }
        StringBuilder sb = new StringBuilder(f() * 8);
        for (int f = f(); f >= 0; f--) {
            int d = d(f);
            if (d != 0) {
                if (d < 0) {
                    if (f == f()) {
                        sb.append("-");
                    } else {
                        sb.append(" - ");
                    }
                    d = -d;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (f == 0 || d != 1) {
                    int g = this.f11797a.g(d);
                    if (g == 0) {
                        sb.append('1');
                    } else if (g == 1) {
                        sb.append('a');
                    } else {
                        sb.append("a^");
                        sb.append(g);
                    }
                }
                if (f != 0) {
                    if (f == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(f);
                    }
                }
            }
        }
        return sb.toString();
    }
}
