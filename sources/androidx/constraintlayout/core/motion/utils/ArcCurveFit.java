package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;
/* loaded from: classes.dex */
public class ArcCurveFit extends CurveFit {
    public static final int ARC_START_FLIP = 3;
    public static final int ARC_START_HORIZONTAL = 2;
    public static final int ARC_START_LINEAR = 0;
    public static final int ARC_START_VERTICAL = 1;

    /* renamed from: a  reason: collision with root package name */
    public final double[] f867a;
    public a[] b;
    public boolean c = true;

    /* loaded from: classes.dex */
    public static class a {
        public static double[] s = new double[91];

        /* renamed from: a  reason: collision with root package name */
        public double[] f868a;
        public double b;
        public double c;
        public double d;
        public double e;
        public double f;
        public double g;
        public double h;
        public double i;
        public double j;
        public double k;
        public double l;
        public double m;
        public double n;
        public double o;
        public double p;
        public boolean q;
        public boolean r;

        public a(int i, double d, double d2, double d3, double d4, double d5, double d6) {
            this.r = false;
            this.q = i == 1;
            this.c = d;
            this.d = d2;
            this.i = 1.0d / (d2 - d);
            if (3 == i) {
                this.r = true;
            }
            double d7 = d5 - d3;
            double d8 = d6 - d4;
            if (!this.r && Math.abs(d7) >= 0.001d && Math.abs(d8) >= 0.001d) {
                this.f868a = new double[101];
                boolean z = this.q;
                this.j = d7 * (z ? -1 : 1);
                this.k = d8 * (z ? 1 : -1);
                this.l = z ? d5 : d3;
                this.m = z ? d4 : d6;
                a(d3, d4, d5, d6);
                this.n = this.b * this.i;
                return;
            }
            this.r = true;
            this.e = d3;
            this.f = d5;
            this.g = d4;
            this.h = d6;
            double hypot = Math.hypot(d8, d7);
            this.b = hypot;
            this.n = hypot * this.i;
            double d9 = this.d;
            double d10 = this.c;
            this.l = d7 / (d9 - d10);
            this.m = d8 / (d9 - d10);
        }

        public final void a(double d, double d2, double d3, double d4) {
            double[] dArr;
            double[] dArr2;
            double d5;
            double d6 = d3 - d;
            double d7 = d2 - d4;
            int i = 0;
            double d8 = 0.0d;
            double d9 = 0.0d;
            double d10 = 0.0d;
            while (true) {
                if (i >= s.length) {
                    break;
                }
                double d11 = d8;
                double radians = Math.toRadians((i * 90.0d) / (dArr.length - 1));
                double sin = Math.sin(radians) * d6;
                double cos = Math.cos(radians) * d7;
                if (i > 0) {
                    d5 = Math.hypot(sin - d9, cos - d10) + d11;
                    s[i] = d5;
                } else {
                    d5 = d11;
                }
                i++;
                d10 = cos;
                d8 = d5;
                d9 = sin;
            }
            double d12 = d8;
            this.b = d12;
            int i2 = 0;
            while (true) {
                double[] dArr3 = s;
                if (i2 >= dArr3.length) {
                    break;
                }
                dArr3[i2] = dArr3[i2] / d12;
                i2++;
            }
            int i3 = 0;
            while (true) {
                if (i3 >= this.f868a.length) {
                    return;
                }
                double length = i3 / (dArr2.length - 1);
                int binarySearch = Arrays.binarySearch(s, length);
                if (binarySearch >= 0) {
                    this.f868a[i3] = binarySearch / (s.length - 1);
                } else if (binarySearch == -1) {
                    this.f868a[i3] = 0.0d;
                } else {
                    int i4 = -binarySearch;
                    int i5 = i4 - 2;
                    double[] dArr4 = s;
                    this.f868a[i3] = (i5 + ((length - dArr4[i5]) / (dArr4[i4 - 1] - dArr4[i5]))) / (dArr4.length - 1);
                }
                i3++;
            }
        }

        public double b() {
            double d = this.j * this.p;
            double hypot = this.n / Math.hypot(d, (-this.k) * this.o);
            if (this.q) {
                d = -d;
            }
            return d * hypot;
        }

        public double c() {
            double d = this.j * this.p;
            double d2 = (-this.k) * this.o;
            double hypot = this.n / Math.hypot(d, d2);
            return this.q ? (-d2) * hypot : d2 * hypot;
        }

        public double d(double d) {
            return this.l;
        }

        public double e(double d) {
            return this.m;
        }

        public double f(double d) {
            double d2 = (d - this.c) * this.i;
            double d3 = this.e;
            return d3 + (d2 * (this.f - d3));
        }

        public double g(double d) {
            double d2 = (d - this.c) * this.i;
            double d3 = this.g;
            return d3 + (d2 * (this.h - d3));
        }

        public double h() {
            return this.l + (this.j * this.o);
        }

        public double i() {
            return this.m + (this.k * this.p);
        }

        public double j(double d) {
            if (d <= 0.0d) {
                return 0.0d;
            }
            if (d >= 1.0d) {
                return 1.0d;
            }
            double[] dArr = this.f868a;
            double length = d * (dArr.length - 1);
            int i = (int) length;
            return dArr[i] + ((length - i) * (dArr[i + 1] - dArr[i]));
        }

        public void k(double d) {
            double j = j((this.q ? this.d - d : d - this.c) * this.i) * 1.5707963267948966d;
            this.o = Math.sin(j);
            this.p = Math.cos(j);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0028, code lost:
        if (r5 == 1) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ArcCurveFit(int[] r25, double[] r26, double[][] r27) {
        /*
            r24 = this;
            r0 = r24
            r1 = r26
            r24.<init>()
            r2 = 1
            r0.c = r2
            r0.f867a = r1
            int r3 = r1.length
            int r3 = r3 - r2
            androidx.constraintlayout.core.motion.utils.ArcCurveFit$a[] r3 = new androidx.constraintlayout.core.motion.utils.ArcCurveFit.a[r3]
            r0.b = r3
            r3 = 0
            r5 = r2
            r6 = r5
            r4 = r3
        L16:
            androidx.constraintlayout.core.motion.utils.ArcCurveFit$a[] r7 = r0.b
            int r8 = r7.length
            if (r4 >= r8) goto L53
            r8 = r25[r4]
            r9 = 3
            r10 = 2
            if (r8 == 0) goto L2f
            if (r8 == r2) goto L2c
            if (r8 == r10) goto L2a
            if (r8 == r9) goto L28
            goto L30
        L28:
            if (r5 != r2) goto L2c
        L2a:
            r5 = r10
            goto L2d
        L2c:
            r5 = r2
        L2d:
            r6 = r5
            goto L30
        L2f:
            r6 = r9
        L30:
            androidx.constraintlayout.core.motion.utils.ArcCurveFit$a r22 = new androidx.constraintlayout.core.motion.utils.ArcCurveFit$a
            r10 = r1[r4]
            int r23 = r4 + 1
            r12 = r1[r23]
            r8 = r27[r4]
            r14 = r8[r3]
            r8 = r27[r4]
            r16 = r8[r2]
            r8 = r27[r23]
            r18 = r8[r3]
            r8 = r27[r23]
            r20 = r8[r2]
            r8 = r22
            r9 = r6
            r8.<init>(r9, r10, r12, r14, r16, r18, r20)
            r7[r4] = r22
            r4 = r23
            goto L16
        L53:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.utils.ArcCurveFit.<init>(int[], double[], double[][]):void");
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double d, double[] dArr) {
        if (this.c) {
            a[] aVarArr = this.b;
            if (d < aVarArr[0].c) {
                double d2 = aVarArr[0].c;
                double d3 = d - aVarArr[0].c;
                if (aVarArr[0].r) {
                    dArr[0] = aVarArr[0].f(d2) + (this.b[0].d(d2) * d3);
                    dArr[1] = this.b[0].g(d2) + (d3 * this.b[0].e(d2));
                    return;
                }
                aVarArr[0].k(d2);
                dArr[0] = this.b[0].h() + (this.b[0].b() * d3);
                dArr[1] = this.b[0].i() + (d3 * this.b[0].c());
                return;
            } else if (d > aVarArr[aVarArr.length - 1].d) {
                double d4 = aVarArr[aVarArr.length - 1].d;
                double d5 = d - d4;
                int length = aVarArr.length - 1;
                if (aVarArr[length].r) {
                    dArr[0] = aVarArr[length].f(d4) + (this.b[length].d(d4) * d5);
                    dArr[1] = this.b[length].g(d4) + (d5 * this.b[length].e(d4));
                    return;
                }
                aVarArr[length].k(d);
                dArr[0] = this.b[length].h() + (this.b[length].b() * d5);
                dArr[1] = this.b[length].i() + (d5 * this.b[length].c());
                return;
            }
        } else {
            a[] aVarArr2 = this.b;
            if (d < aVarArr2[0].c) {
                d = aVarArr2[0].c;
            }
            if (d > aVarArr2[aVarArr2.length - 1].d) {
                d = aVarArr2[aVarArr2.length - 1].d;
            }
        }
        int i = 0;
        while (true) {
            a[] aVarArr3 = this.b;
            if (i >= aVarArr3.length) {
                return;
            }
            if (d <= aVarArr3[i].d) {
                if (aVarArr3[i].r) {
                    dArr[0] = aVarArr3[i].f(d);
                    dArr[1] = this.b[i].g(d);
                    return;
                }
                aVarArr3[i].k(d);
                dArr[0] = this.b[i].h();
                dArr[1] = this.b[i].i();
                return;
            }
            i++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getSlope(double d, double[] dArr) {
        a[] aVarArr = this.b;
        if (d < aVarArr[0].c) {
            d = aVarArr[0].c;
        } else if (d > aVarArr[aVarArr.length - 1].d) {
            d = aVarArr[aVarArr.length - 1].d;
        }
        int i = 0;
        while (true) {
            a[] aVarArr2 = this.b;
            if (i >= aVarArr2.length) {
                return;
            }
            if (d <= aVarArr2[i].d) {
                if (aVarArr2[i].r) {
                    dArr[0] = aVarArr2[i].d(d);
                    dArr[1] = this.b[i].e(d);
                    return;
                }
                aVarArr2[i].k(d);
                dArr[0] = this.b[i].b();
                dArr[1] = this.b[i].c();
                return;
            }
            i++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double[] getTimePoints() {
        return this.f867a;
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getSlope(double d, int i) {
        a[] aVarArr = this.b;
        int i2 = 0;
        if (d < aVarArr[0].c) {
            d = aVarArr[0].c;
        }
        if (d > aVarArr[aVarArr.length - 1].d) {
            d = aVarArr[aVarArr.length - 1].d;
        }
        while (true) {
            a[] aVarArr2 = this.b;
            if (i2 >= aVarArr2.length) {
                return Double.NaN;
            }
            if (d <= aVarArr2[i2].d) {
                if (aVarArr2[i2].r) {
                    if (i == 0) {
                        return aVarArr2[i2].d(d);
                    }
                    return aVarArr2[i2].e(d);
                }
                aVarArr2[i2].k(d);
                if (i == 0) {
                    return this.b[i2].b();
                }
                return this.b[i2].c();
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double d, float[] fArr) {
        if (this.c) {
            a[] aVarArr = this.b;
            if (d < aVarArr[0].c) {
                double d2 = aVarArr[0].c;
                double d3 = d - aVarArr[0].c;
                if (aVarArr[0].r) {
                    fArr[0] = (float) (aVarArr[0].f(d2) + (this.b[0].d(d2) * d3));
                    fArr[1] = (float) (this.b[0].g(d2) + (d3 * this.b[0].e(d2)));
                    return;
                }
                aVarArr[0].k(d2);
                fArr[0] = (float) (this.b[0].h() + (this.b[0].b() * d3));
                fArr[1] = (float) (this.b[0].i() + (d3 * this.b[0].c()));
                return;
            } else if (d > aVarArr[aVarArr.length - 1].d) {
                double d4 = aVarArr[aVarArr.length - 1].d;
                double d5 = d - d4;
                int length = aVarArr.length - 1;
                if (aVarArr[length].r) {
                    fArr[0] = (float) (aVarArr[length].f(d4) + (this.b[length].d(d4) * d5));
                    fArr[1] = (float) (this.b[length].g(d4) + (d5 * this.b[length].e(d4)));
                    return;
                }
                aVarArr[length].k(d);
                fArr[0] = (float) this.b[length].h();
                fArr[1] = (float) this.b[length].i();
                return;
            }
        } else {
            a[] aVarArr2 = this.b;
            if (d < aVarArr2[0].c) {
                d = aVarArr2[0].c;
            } else if (d > aVarArr2[aVarArr2.length - 1].d) {
                d = aVarArr2[aVarArr2.length - 1].d;
            }
        }
        int i = 0;
        while (true) {
            a[] aVarArr3 = this.b;
            if (i >= aVarArr3.length) {
                return;
            }
            if (d <= aVarArr3[i].d) {
                if (aVarArr3[i].r) {
                    fArr[0] = (float) aVarArr3[i].f(d);
                    fArr[1] = (float) this.b[i].g(d);
                    return;
                }
                aVarArr3[i].k(d);
                fArr[0] = (float) this.b[i].h();
                fArr[1] = (float) this.b[i].i();
                return;
            }
            i++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getPos(double d, int i) {
        double d2;
        double g;
        double e;
        double i2;
        double c;
        int i3 = 0;
        if (this.c) {
            a[] aVarArr = this.b;
            if (d < aVarArr[0].c) {
                double d3 = aVarArr[0].c;
                d2 = d - aVarArr[0].c;
                if (!aVarArr[0].r) {
                    aVarArr[0].k(d3);
                    if (i == 0) {
                        i2 = this.b[0].h();
                        c = this.b[0].b();
                    } else {
                        i2 = this.b[0].i();
                        c = this.b[0].c();
                    }
                    return i2 + (d2 * c);
                } else if (i == 0) {
                    g = aVarArr[0].f(d3);
                    e = this.b[0].d(d3);
                } else {
                    g = aVarArr[0].g(d3);
                    e = this.b[0].e(d3);
                }
            } else if (d > aVarArr[aVarArr.length - 1].d) {
                double d4 = aVarArr[aVarArr.length - 1].d;
                d2 = d - d4;
                int length = aVarArr.length - 1;
                if (i == 0) {
                    g = aVarArr[length].f(d4);
                    e = this.b[length].d(d4);
                } else {
                    g = aVarArr[length].g(d4);
                    e = this.b[length].e(d4);
                }
            }
            return g + (d2 * e);
        }
        a[] aVarArr2 = this.b;
        if (d < aVarArr2[0].c) {
            d = aVarArr2[0].c;
        } else if (d > aVarArr2[aVarArr2.length - 1].d) {
            d = aVarArr2[aVarArr2.length - 1].d;
        }
        while (true) {
            a[] aVarArr3 = this.b;
            if (i3 >= aVarArr3.length) {
                return Double.NaN;
            }
            if (d <= aVarArr3[i3].d) {
                if (aVarArr3[i3].r) {
                    if (i == 0) {
                        return aVarArr3[i3].f(d);
                    }
                    return aVarArr3[i3].g(d);
                }
                aVarArr3[i3].k(d);
                if (i == 0) {
                    return this.b[i3].h();
                }
                return this.b[i3].i();
            }
            i3++;
        }
    }
}
