package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;
/* loaded from: classes.dex */
public class Oscillator {
    public static final int BOUNCE = 6;
    public static final int COS_WAVE = 5;
    public static final int CUSTOM = 7;
    public static final int REVERSE_SAW_WAVE = 4;
    public static final int SAW_WAVE = 3;
    public static final int SIN_WAVE = 0;
    public static final int SQUARE_WAVE = 1;
    public static String TAG = "Oscillator";
    public static final int TRIANGLE_WAVE = 2;
    public double[] c;
    public String d;
    public MonotonicCurveFit e;
    public int f;

    /* renamed from: a  reason: collision with root package name */
    public float[] f882a = new float[0];
    public double[] b = new double[0];
    public double g = 6.283185307179586d;

    public double a(double d) {
        if (d <= 0.0d) {
            d = 1.0E-5d;
        } else if (d >= 1.0d) {
            d = 0.999999d;
        }
        int binarySearch = Arrays.binarySearch(this.b, d);
        if (binarySearch <= 0 && binarySearch != 0) {
            int i = (-binarySearch) - 1;
            float[] fArr = this.f882a;
            int i2 = i - 1;
            double d2 = fArr[i] - fArr[i2];
            double[] dArr = this.b;
            double d3 = d2 / (dArr[i] - dArr[i2]);
            return (fArr[i2] - (d3 * dArr[i2])) + (d * d3);
        }
        return 0.0d;
    }

    public void addPoint(double d, float f) {
        int length = this.f882a.length + 1;
        int binarySearch = Arrays.binarySearch(this.b, d);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 1;
        }
        this.b = Arrays.copyOf(this.b, length);
        this.f882a = Arrays.copyOf(this.f882a, length);
        this.c = new double[length];
        double[] dArr = this.b;
        System.arraycopy(dArr, binarySearch, dArr, binarySearch + 1, (length - binarySearch) - 1);
        this.b[binarySearch] = d;
        this.f882a[binarySearch] = f;
    }

    public double b(double d) {
        if (d < 0.0d) {
            d = 0.0d;
        } else if (d > 1.0d) {
            d = 1.0d;
        }
        int binarySearch = Arrays.binarySearch(this.b, d);
        if (binarySearch > 0) {
            return 1.0d;
        }
        if (binarySearch != 0) {
            int i = (-binarySearch) - 1;
            float[] fArr = this.f882a;
            int i2 = i - 1;
            double d2 = fArr[i] - fArr[i2];
            double[] dArr = this.b;
            double d3 = d2 / (dArr[i] - dArr[i2]);
            return this.c[i2] + ((fArr[i2] - (dArr[i2] * d3)) * (d - dArr[i2])) + ((d3 * ((d * d) - (dArr[i2] * dArr[i2]))) / 2.0d);
        }
        return 0.0d;
    }

    public double getSlope(double d, double d2, double d3) {
        double b = d2 + b(d);
        double a2 = a(d) + d3;
        switch (this.f) {
            case 1:
                return 0.0d;
            case 2:
                return a2 * 4.0d * Math.signum((((b * 4.0d) + 3.0d) % 4.0d) - 2.0d);
            case 3:
                return a2 * 2.0d;
            case 4:
                return (-a2) * 2.0d;
            case 5:
                double d4 = this.g;
                return (-d4) * a2 * Math.sin(d4 * b);
            case 6:
                return a2 * 4.0d * ((((b * 4.0d) + 2.0d) % 4.0d) - 2.0d);
            case 7:
                return this.e.getSlope(b % 1.0d, 0);
            default:
                double d5 = this.g;
                return a2 * d5 * Math.cos(d5 * b);
        }
    }

    public double getValue(double d, double d2) {
        double abs;
        double b = b(d) + d2;
        switch (this.f) {
            case 1:
                return Math.signum(0.5d - (b % 1.0d));
            case 2:
                abs = Math.abs((((b * 4.0d) + 1.0d) % 4.0d) - 2.0d);
                break;
            case 3:
                return (((b * 2.0d) + 1.0d) % 2.0d) - 1.0d;
            case 4:
                abs = ((b * 2.0d) + 1.0d) % 2.0d;
                break;
            case 5:
                return Math.cos(this.g * (d2 + b));
            case 6:
                double abs2 = 1.0d - Math.abs(((b * 4.0d) % 4.0d) - 2.0d);
                abs = abs2 * abs2;
                break;
            case 7:
                return this.e.getPos(b % 1.0d, 0);
            default:
                return Math.sin(this.g * b);
        }
        return 1.0d - abs;
    }

    public void normalize() {
        float[] fArr;
        float[] fArr2;
        float[] fArr3;
        int i;
        int i2 = 0;
        double d = 0.0d;
        while (true) {
            if (i2 >= this.f882a.length) {
                break;
            }
            d += fArr[i2];
            i2++;
        }
        int i3 = 1;
        double d2 = 0.0d;
        int i4 = 1;
        while (true) {
            if (i4 >= this.f882a.length) {
                break;
            }
            double[] dArr = this.b;
            d2 += (dArr[i4] - dArr[i4 - 1]) * ((fArr2[i] + fArr2[i4]) / 2.0f);
            i4++;
        }
        int i5 = 0;
        while (true) {
            float[] fArr4 = this.f882a;
            if (i5 >= fArr4.length) {
                break;
            }
            fArr4[i5] = (float) (fArr4[i5] * (d / d2));
            i5++;
        }
        this.c[0] = 0.0d;
        while (true) {
            if (i3 >= this.f882a.length) {
                return;
            }
            int i6 = i3 - 1;
            double[] dArr2 = this.b;
            double d3 = dArr2[i3] - dArr2[i6];
            double[] dArr3 = this.c;
            dArr3[i3] = dArr3[i6] + (d3 * ((fArr3[i6] + fArr3[i3]) / 2.0f));
            i3++;
        }
    }

    public void setType(int i, String str) {
        this.f = i;
        this.d = str;
        if (str != null) {
            this.e = MonotonicCurveFit.buildWave(str);
        }
    }

    public String toString() {
        return "pos =" + Arrays.toString(this.b) + " period=" + Arrays.toString(this.f882a);
    }
}
