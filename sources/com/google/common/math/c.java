package com.google.common.math;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.lang.Comparable;
import java.lang.Number;
import java.math.RoundingMode;
@GwtIncompatible
/* loaded from: classes10.dex */
public abstract class c<X extends Number & Comparable<X>> {

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10708a;

        static {
            int[] iArr = new int[RoundingMode.values().length];
            f10708a = iArr;
            try {
                iArr[RoundingMode.DOWN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10708a[RoundingMode.HALF_EVEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10708a[RoundingMode.HALF_DOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10708a[RoundingMode.HALF_UP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f10708a[RoundingMode.FLOOR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f10708a[RoundingMode.CEILING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f10708a[RoundingMode.UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f10708a[RoundingMode.UNNECESSARY.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public abstract X a(X x, X x2);

    public final double b(X x, RoundingMode roundingMode) {
        X x2;
        double d;
        Preconditions.checkNotNull(x, "x");
        Preconditions.checkNotNull(roundingMode, "mode");
        double c = c(x);
        if (Double.isInfinite(c)) {
            switch (a.f10708a[roundingMode.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                    return d(x) * Double.MAX_VALUE;
                case 5:
                    return c == Double.POSITIVE_INFINITY ? Double.MAX_VALUE : Double.NEGATIVE_INFINITY;
                case 6:
                    return c == Double.POSITIVE_INFINITY ? Double.POSITIVE_INFINITY : -1.7976931348623157E308d;
                case 7:
                    return c;
                case 8:
                    String valueOf = String.valueOf(x);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 44);
                    sb.append(valueOf);
                    sb.append(" cannot be represented precisely as a double");
                    throw new ArithmeticException(sb.toString());
            }
        }
        X e = e(c, RoundingMode.UNNECESSARY);
        int compareTo = ((Comparable) x).compareTo(e);
        int[] iArr = a.f10708a;
        switch (iArr[roundingMode.ordinal()]) {
            case 1:
                return d(x) >= 0 ? compareTo >= 0 ? c : com.google.common.math.a.f(c) : compareTo <= 0 ? c : Math.nextUp(c);
            case 2:
            case 3:
            case 4:
                if (compareTo >= 0) {
                    d = Math.nextUp(c);
                    if (d == Double.POSITIVE_INFINITY) {
                        return c;
                    }
                    x2 = e(d, RoundingMode.CEILING);
                } else {
                    double f = com.google.common.math.a.f(c);
                    if (f == Double.NEGATIVE_INFINITY) {
                        return c;
                    }
                    X e2 = e(f, RoundingMode.FLOOR);
                    x2 = e;
                    e = e2;
                    d = c;
                    c = f;
                }
                int compareTo2 = ((Comparable) a(x, e)).compareTo(a(x2, x));
                if (compareTo2 < 0) {
                    return c;
                }
                if (compareTo2 > 0) {
                    return d;
                }
                int i = iArr[roundingMode.ordinal()];
                if (i == 2) {
                    return (Double.doubleToRawLongBits(c) & 1) == 0 ? c : d;
                } else if (i == 3) {
                    return d(x) >= 0 ? c : d;
                } else if (i == 4) {
                    return d(x) >= 0 ? d : c;
                } else {
                    throw new AssertionError("impossible");
                }
            case 5:
                return compareTo >= 0 ? c : com.google.common.math.a.f(c);
            case 6:
                return compareTo <= 0 ? c : Math.nextUp(c);
            case 7:
                return d(x) >= 0 ? compareTo <= 0 ? c : Math.nextUp(c) : compareTo >= 0 ? c : com.google.common.math.a.f(c);
            case 8:
                b.k(compareTo == 0);
                return c;
            default:
                throw new AssertionError("impossible");
        }
    }

    public abstract double c(X x);

    public abstract int d(X x);

    public abstract X e(double d, RoundingMode roundingMode);
}
