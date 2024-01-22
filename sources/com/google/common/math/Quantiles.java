package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
@Beta
@GwtIncompatible
/* loaded from: classes10.dex */
public final class Quantiles {

    /* loaded from: classes10.dex */
    public static final class Scale {

        /* renamed from: a  reason: collision with root package name */
        public final int f10704a;

        public ScaleAndIndex index(int i) {
            return new ScaleAndIndex(this.f10704a, i);
        }

        public ScaleAndIndexes indexes(int... iArr) {
            return new ScaleAndIndexes(this.f10704a, (int[]) iArr.clone());
        }

        public Scale(int i) {
            Preconditions.checkArgument(i > 0, "Quantile scale must be positive");
            this.f10704a = i;
        }

        public ScaleAndIndexes indexes(Collection<Integer> collection) {
            return new ScaleAndIndexes(this.f10704a, Ints.toArray(collection));
        }
    }

    /* loaded from: classes10.dex */
    public static final class ScaleAndIndex {

        /* renamed from: a  reason: collision with root package name */
        public final int f10705a;
        public final int b;

        public double compute(Collection<? extends Number> collection) {
            return computeInPlace(Doubles.toArray(collection));
        }

        public double computeInPlace(double... dArr) {
            Preconditions.checkArgument(dArr.length > 0, "Cannot calculate quantiles of an empty dataset");
            if (Quantiles.j(dArr)) {
                return Double.NaN;
            }
            long length = this.b * (dArr.length - 1);
            int divide = (int) LongMath.divide(length, this.f10705a, RoundingMode.DOWN);
            int i = (int) (length - (divide * this.f10705a));
            Quantiles.q(divide, dArr, 0, dArr.length - 1);
            if (i != 0) {
                int i2 = divide + 1;
                Quantiles.q(i2, dArr, i2, dArr.length - 1);
                return Quantiles.k(dArr[divide], dArr[i2], i, this.f10705a);
            }
            return dArr[divide];
        }

        public ScaleAndIndex(int i, int i2) {
            Quantiles.h(i2, i);
            this.f10705a = i;
            this.b = i2;
        }

        public double compute(double... dArr) {
            return computeInPlace((double[]) dArr.clone());
        }

        public double compute(long... jArr) {
            return computeInPlace(Quantiles.m(jArr));
        }

        public double compute(int... iArr) {
            return computeInPlace(Quantiles.l(iArr));
        }
    }

    /* loaded from: classes10.dex */
    public static final class ScaleAndIndexes {

        /* renamed from: a  reason: collision with root package name */
        public final int f10706a;
        public final int[] b;

        public Map<Integer, Double> compute(Collection<? extends Number> collection) {
            return computeInPlace(Doubles.toArray(collection));
        }

        public Map<Integer, Double> computeInPlace(double... dArr) {
            int[] iArr;
            int i = 0;
            Preconditions.checkArgument(dArr.length > 0, "Cannot calculate quantiles of an empty dataset");
            if (Quantiles.j(dArr)) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                int[] iArr2 = this.b;
                int length = iArr2.length;
                while (i < length) {
                    linkedHashMap.put(Integer.valueOf(iArr2[i]), Double.valueOf(Double.NaN));
                    i++;
                }
                return Collections.unmodifiableMap(linkedHashMap);
            }
            int[] iArr3 = this.b;
            int[] iArr4 = new int[iArr3.length];
            int[] iArr5 = new int[iArr3.length];
            int[] iArr6 = new int[iArr3.length * 2];
            int i2 = 0;
            int i3 = 0;
            while (true) {
                if (i2 >= this.b.length) {
                    break;
                }
                long length2 = iArr[i2] * (dArr.length - 1);
                int divide = (int) LongMath.divide(length2, this.f10706a, RoundingMode.DOWN);
                int i4 = (int) (length2 - (divide * this.f10706a));
                iArr4[i2] = divide;
                iArr5[i2] = i4;
                iArr6[i3] = divide;
                i3++;
                if (i4 != 0) {
                    iArr6[i3] = divide + 1;
                    i3++;
                }
                i2++;
            }
            Arrays.sort(iArr6, 0, i3);
            Quantiles.p(iArr6, 0, i3 - 1, dArr, 0, dArr.length - 1);
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            while (true) {
                int[] iArr7 = this.b;
                if (i < iArr7.length) {
                    int i5 = iArr4[i];
                    int i6 = iArr5[i];
                    if (i6 == 0) {
                        linkedHashMap2.put(Integer.valueOf(iArr7[i]), Double.valueOf(dArr[i5]));
                    } else {
                        linkedHashMap2.put(Integer.valueOf(iArr7[i]), Double.valueOf(Quantiles.k(dArr[i5], dArr[i5 + 1], i6, this.f10706a)));
                    }
                    i++;
                } else {
                    return Collections.unmodifiableMap(linkedHashMap2);
                }
            }
        }

        public ScaleAndIndexes(int i, int[] iArr) {
            for (int i2 : iArr) {
                Quantiles.h(i2, i);
            }
            Preconditions.checkArgument(iArr.length > 0, "Indexes must be a non empty array");
            this.f10706a = i;
            this.b = iArr;
        }

        public Map<Integer, Double> compute(double... dArr) {
            return computeInPlace((double[]) dArr.clone());
        }

        public Map<Integer, Double> compute(long... jArr) {
            return computeInPlace(Quantiles.m(jArr));
        }

        public Map<Integer, Double> compute(int... iArr) {
            return computeInPlace(Quantiles.l(iArr));
        }
    }

    public static void h(int i, int i2) {
        if (i < 0 || i > i2) {
            StringBuilder sb = new StringBuilder(70);
            sb.append("Quantile indexes must be between 0 and the scale, which is ");
            sb.append(i2);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public static int i(int[] iArr, int i, int i2, int i3, int i4) {
        if (i == i2) {
            return i;
        }
        int i5 = i3 + i4;
        int i6 = i5 >>> 1;
        while (i2 > i + 1) {
            int i7 = (i + i2) >>> 1;
            if (iArr[i7] > i6) {
                i2 = i7;
            } else if (iArr[i7] >= i6) {
                return i7;
            } else {
                i = i7;
            }
        }
        return (i5 - iArr[i]) - iArr[i2] > 0 ? i2 : i;
    }

    public static boolean j(double... dArr) {
        for (double d : dArr) {
            if (Double.isNaN(d)) {
                return true;
            }
        }
        return false;
    }

    public static double k(double d, double d2, double d3, double d4) {
        if (d == Double.NEGATIVE_INFINITY) {
            return d2 == Double.POSITIVE_INFINITY ? Double.NaN : Double.NEGATIVE_INFINITY;
        } else if (d2 == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        } else {
            return d + (((d2 - d) * d3) / d4);
        }
    }

    public static double[] l(int[] iArr) {
        int length = iArr.length;
        double[] dArr = new double[length];
        for (int i = 0; i < length; i++) {
            dArr[i] = iArr[i];
        }
        return dArr;
    }

    public static double[] m(long[] jArr) {
        int length = jArr.length;
        double[] dArr = new double[length];
        for (int i = 0; i < length; i++) {
            dArr[i] = jArr[i];
        }
        return dArr;
    }

    public static ScaleAndIndex median() {
        return scale(2).index(1);
    }

    public static void n(double[] dArr, int i, int i2) {
        int i3 = (i + i2) >>> 1;
        boolean z = dArr[i2] < dArr[i3];
        boolean z2 = dArr[i3] < dArr[i];
        boolean z3 = dArr[i2] < dArr[i];
        if (z == z2) {
            r(dArr, i3, i);
        } else if (z != z3) {
            r(dArr, i, i2);
        }
    }

    public static int o(double[] dArr, int i, int i2) {
        n(dArr, i, i2);
        double d = dArr[i];
        int i3 = i2;
        while (i2 > i) {
            if (dArr[i2] > d) {
                r(dArr, i3, i2);
                i3--;
            }
            i2--;
        }
        r(dArr, i, i3);
        return i3;
    }

    public static void p(int[] iArr, int i, int i2, double[] dArr, int i3, int i4) {
        int i5 = i(iArr, i, i2, i3, i4);
        int i6 = iArr[i5];
        q(i6, dArr, i3, i4);
        int i7 = i5 - 1;
        while (i7 >= i && iArr[i7] == i6) {
            i7--;
        }
        if (i7 >= i) {
            p(iArr, i, i7, dArr, i3, i6 - 1);
        }
        int i8 = i5 + 1;
        while (i8 <= i2 && iArr[i8] == i6) {
            i8++;
        }
        if (i8 <= i2) {
            p(iArr, i8, i2, dArr, i6 + 1, i4);
        }
    }

    public static Scale percentiles() {
        return scale(100);
    }

    public static void q(int i, double[] dArr, int i2, int i3) {
        if (i != i2) {
            while (i3 > i2) {
                int o = o(dArr, i2, i3);
                if (o >= i) {
                    i3 = o - 1;
                }
                if (o <= i) {
                    i2 = o + 1;
                }
            }
            return;
        }
        int i4 = i2;
        for (int i5 = i2 + 1; i5 <= i3; i5++) {
            if (dArr[i4] > dArr[i5]) {
                i4 = i5;
            }
        }
        if (i4 != i2) {
            r(dArr, i4, i2);
        }
    }

    public static Scale quartiles() {
        return scale(4);
    }

    public static void r(double[] dArr, int i, int i2) {
        double d = dArr[i];
        dArr[i] = dArr[i2];
        dArr[i2] = d;
    }

    public static Scale scale(int i) {
        return new Scale(i);
    }
}
