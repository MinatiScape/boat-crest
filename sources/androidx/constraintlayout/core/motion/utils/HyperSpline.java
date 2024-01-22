package androidx.constraintlayout.core.motion.utils;

import java.lang.reflect.Array;
/* loaded from: classes.dex */
public class HyperSpline {

    /* renamed from: a  reason: collision with root package name */
    public int f871a;
    public Cubic[][] b;
    public int c;
    public double[] d;
    public double e;
    public double[][] f;

    /* loaded from: classes.dex */
    public static class Cubic {

        /* renamed from: a  reason: collision with root package name */
        public double f872a;
        public double b;
        public double c;
        public double d;

        public Cubic(double d, double d2, double d3, double d4) {
            this.f872a = d;
            this.b = d2;
            this.c = d3;
            this.d = d4;
        }

        public double eval(double d) {
            return (((((this.d * d) + this.c) * d) + this.b) * d) + this.f872a;
        }

        public double vel(double d) {
            return (((this.d * 3.0d * d) + (this.c * 2.0d)) * d) + this.b;
        }
    }

    public HyperSpline(double[][] dArr) {
        setup(dArr);
    }

    public static Cubic[] a(int i, double[] dArr) {
        double[] dArr2 = new double[i];
        double[] dArr3 = new double[i];
        double[] dArr4 = new double[i];
        int i2 = i - 1;
        int i3 = 0;
        dArr2[0] = 0.5d;
        int i4 = 1;
        for (int i5 = 1; i5 < i2; i5++) {
            dArr2[i5] = 1.0d / (4.0d - dArr2[i5 - 1]);
        }
        int i6 = i2 - 1;
        dArr2[i2] = 1.0d / (2.0d - dArr2[i6]);
        dArr3[0] = (dArr[1] - dArr[0]) * 3.0d * dArr2[0];
        while (i4 < i2) {
            int i7 = i4 + 1;
            int i8 = i4 - 1;
            dArr3[i4] = (((dArr[i7] - dArr[i8]) * 3.0d) - dArr3[i8]) * dArr2[i4];
            i4 = i7;
        }
        dArr3[i2] = (((dArr[i2] - dArr[i6]) * 3.0d) - dArr3[i6]) * dArr2[i2];
        dArr4[i2] = dArr3[i2];
        while (i6 >= 0) {
            dArr4[i6] = dArr3[i6] - (dArr2[i6] * dArr4[i6 + 1]);
            i6--;
        }
        Cubic[] cubicArr = new Cubic[i2];
        while (i3 < i2) {
            int i9 = i3 + 1;
            cubicArr[i3] = new Cubic((float) dArr[i3], dArr4[i3], (((dArr[i9] - dArr[i3]) * 3.0d) - (dArr4[i3] * 2.0d)) - dArr4[i9], ((dArr[i3] - dArr[i9]) * 2.0d) + dArr4[i3] + dArr4[i9]);
            i3 = i9;
        }
        return cubicArr;
    }

    public double approxLength(Cubic[] cubicArr) {
        int i;
        int length = cubicArr.length;
        double[] dArr = new double[cubicArr.length];
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        while (true) {
            i = 0;
            if (d2 >= 1.0d) {
                break;
            }
            double d4 = 0.0d;
            while (i < cubicArr.length) {
                double d5 = dArr[i];
                double eval = cubicArr[i].eval(d2);
                dArr[i] = eval;
                double d6 = d5 - eval;
                d4 += d6 * d6;
                i++;
            }
            if (d2 > 0.0d) {
                d3 += Math.sqrt(d4);
            }
            d2 += 0.1d;
        }
        while (i < cubicArr.length) {
            double d7 = dArr[i];
            double eval2 = cubicArr[i].eval(1.0d);
            dArr[i] = eval2;
            double d8 = d7 - eval2;
            d += d8 * d8;
            i++;
        }
        return d3 + Math.sqrt(d);
    }

    public void getPos(double d, double[] dArr) {
        double d2 = d * this.e;
        int i = 0;
        while (true) {
            double[] dArr2 = this.d;
            if (i >= dArr2.length - 1 || dArr2[i] >= d2) {
                break;
            }
            d2 -= dArr2[i];
            i++;
        }
        for (int i2 = 0; i2 < dArr.length; i2++) {
            dArr[i2] = this.b[i2][i].eval(d2 / this.d[i]);
        }
    }

    public void getVelocity(double d, double[] dArr) {
        double d2 = d * this.e;
        int i = 0;
        while (true) {
            double[] dArr2 = this.d;
            if (i >= dArr2.length - 1 || dArr2[i] >= d2) {
                break;
            }
            d2 -= dArr2[i];
            i++;
        }
        for (int i2 = 0; i2 < dArr.length; i2++) {
            dArr[i2] = this.b[i2][i].vel(d2 / this.d[i]);
        }
    }

    public void setup(double[][] dArr) {
        int i;
        int length = dArr[0].length;
        this.c = length;
        int length2 = dArr.length;
        this.f871a = length2;
        this.f = (double[][]) Array.newInstance(double.class, length, length2);
        this.b = new Cubic[this.c];
        for (int i2 = 0; i2 < this.c; i2++) {
            for (int i3 = 0; i3 < this.f871a; i3++) {
                this.f[i2][i3] = dArr[i3][i2];
            }
        }
        int i4 = 0;
        while (true) {
            i = this.c;
            if (i4 >= i) {
                break;
            }
            Cubic[][] cubicArr = this.b;
            double[][] dArr2 = this.f;
            cubicArr[i4] = a(dArr2[i4].length, dArr2[i4]);
            i4++;
        }
        this.d = new double[this.f871a - 1];
        this.e = 0.0d;
        Cubic[] cubicArr2 = new Cubic[i];
        for (int i5 = 0; i5 < this.d.length; i5++) {
            for (int i6 = 0; i6 < this.c; i6++) {
                cubicArr2[i6] = this.b[i6][i5];
            }
            double d = this.e;
            double[] dArr3 = this.d;
            double approxLength = approxLength(cubicArr2);
            dArr3[i5] = approxLength;
            this.e = d + approxLength;
        }
    }

    public HyperSpline() {
    }

    public void getPos(double d, float[] fArr) {
        double d2 = d * this.e;
        int i = 0;
        while (true) {
            double[] dArr = this.d;
            if (i >= dArr.length - 1 || dArr[i] >= d2) {
                break;
            }
            d2 -= dArr[i];
            i++;
        }
        for (int i2 = 0; i2 < fArr.length; i2++) {
            fArr[i2] = (float) this.b[i2][i].eval(d2 / this.d[i]);
        }
    }

    public double getPos(double d, int i) {
        double[] dArr;
        double d2 = d * this.e;
        int i2 = 0;
        while (true) {
            dArr = this.d;
            if (i2 >= dArr.length - 1 || dArr[i2] >= d2) {
                break;
            }
            d2 -= dArr[i2];
            i2++;
        }
        return this.b[i][i2].eval(d2 / dArr[i2]);
    }
}
