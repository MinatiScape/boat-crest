package androidx.constraintlayout.core.motion.utils;

import com.mappls.sdk.maps.style.layers.Property;
import java.io.PrintStream;
import java.util.Arrays;
/* loaded from: classes.dex */
public class Easing {

    /* renamed from: a  reason: collision with root package name */
    public String f870a = "identity";
    public static Easing b = new Easing();
    public static String[] NAMED_EASING = {"standard", "accelerate", "decelerate", Property.RASTER_RESAMPLING_LINEAR};

    /* loaded from: classes.dex */
    public static class a extends Easing {
        public static double g = 0.01d;
        public static double h = 1.0E-4d;
        public double c;
        public double d;
        public double e;
        public double f;

        public a(String str) {
            this.f870a = str;
            int indexOf = str.indexOf(40);
            int indexOf2 = str.indexOf(44, indexOf);
            this.c = Double.parseDouble(str.substring(indexOf + 1, indexOf2).trim());
            int i = indexOf2 + 1;
            int indexOf3 = str.indexOf(44, i);
            this.d = Double.parseDouble(str.substring(i, indexOf3).trim());
            int i2 = indexOf3 + 1;
            int indexOf4 = str.indexOf(44, i2);
            this.e = Double.parseDouble(str.substring(i2, indexOf4).trim());
            int i3 = indexOf4 + 1;
            this.f = Double.parseDouble(str.substring(i3, str.indexOf(41, i3)).trim());
        }

        public final double a(double d) {
            double d2 = 1.0d - d;
            double d3 = 3.0d * d2;
            return (this.c * d2 * d3 * d) + (this.e * d3 * d * d) + (d * d * d);
        }

        public final double b(double d) {
            double d2 = 1.0d - d;
            double d3 = 3.0d * d2;
            return (this.d * d2 * d3 * d) + (this.f * d3 * d * d) + (d * d * d);
        }

        @Override // androidx.constraintlayout.core.motion.utils.Easing
        public double get(double d) {
            if (d <= 0.0d) {
                return 0.0d;
            }
            if (d >= 1.0d) {
                return 1.0d;
            }
            double d2 = 0.5d;
            double d3 = 0.5d;
            while (d2 > g) {
                d2 *= 0.5d;
                d3 = a(d3) < d ? d3 + d2 : d3 - d2;
            }
            double d4 = d3 - d2;
            double a2 = a(d4);
            double d5 = d3 + d2;
            double a3 = a(d5);
            double b = b(d4);
            return (((b(d5) - b) * (d - a2)) / (a3 - a2)) + b;
        }

        @Override // androidx.constraintlayout.core.motion.utils.Easing
        public double getDiff(double d) {
            double d2 = 0.5d;
            double d3 = 0.5d;
            while (d2 > h) {
                d2 *= 0.5d;
                d3 = a(d3) < d ? d3 + d2 : d3 - d2;
            }
            double d4 = d3 - d2;
            double d5 = d3 + d2;
            return (b(d5) - b(d4)) / (a(d5) - a(d4));
        }
    }

    public static Easing getInterpolator(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith("cubic")) {
            return new a(str);
        }
        if (str.startsWith("spline")) {
            return new StepCurve(str);
        }
        if (str.startsWith("Schlick")) {
            return new Schlick(str);
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1354466595:
                if (str.equals("accelerate")) {
                    c = 0;
                    break;
                }
                break;
            case -1263948740:
                if (str.equals("decelerate")) {
                    c = 1;
                    break;
                }
                break;
            case -1197605014:
                if (str.equals("anticipate")) {
                    c = 2;
                    break;
                }
                break;
            case -1102672091:
                if (str.equals(Property.RASTER_RESAMPLING_LINEAR)) {
                    c = 3;
                    break;
                }
                break;
            case -749065269:
                if (str.equals("overshoot")) {
                    c = 4;
                    break;
                }
                break;
            case 1312628413:
                if (str.equals("standard")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return new a("cubic(0.4, 0.05, 0.8, 0.7)");
            case 1:
                return new a("cubic(0.0, 0.0, 0.2, 0.95)");
            case 2:
                return new a("cubic(0.36, 0, 0.66, -0.56)");
            case 3:
                return new a("cubic(1, 1, 0, 0)");
            case 4:
                return new a("cubic(0.34, 1.56, 0.64, 1)");
            case 5:
                return new a("cubic(0.4, 0.0, 0.2, 1)");
            default:
                PrintStream printStream = System.err;
                printStream.println("transitionEasing syntax error syntax:transitionEasing=\"cubic(1.0,0.5,0.0,0.6)\" or " + Arrays.toString(NAMED_EASING));
                return b;
        }
    }

    public double get(double d) {
        return d;
    }

    public double getDiff(double d) {
        return 1.0d;
    }

    public String toString() {
        return this.f870a;
    }
}
