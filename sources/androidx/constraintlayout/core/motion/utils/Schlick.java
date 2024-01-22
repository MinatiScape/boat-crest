package androidx.constraintlayout.core.motion.utils;
/* loaded from: classes.dex */
public class Schlick extends Easing {
    public double c;
    public double d;

    public Schlick(String str) {
        this.f870a = str;
        int indexOf = str.indexOf(40);
        int indexOf2 = str.indexOf(44, indexOf);
        this.c = Double.parseDouble(str.substring(indexOf + 1, indexOf2).trim());
        int i = indexOf2 + 1;
        this.d = Double.parseDouble(str.substring(i, str.indexOf(44, i)).trim());
    }

    public final double a(double d) {
        double d2 = this.d;
        if (d < d2) {
            double d3 = this.c;
            return ((d3 * d2) * d2) / ((((d2 - d) * d3) + d) * ((d3 * (d2 - d)) + d));
        }
        double d4 = this.c;
        return (((d2 - 1.0d) * d4) * (d2 - 1.0d)) / (((((-d4) * (d2 - d)) - d) + 1.0d) * ((((-d4) * (d2 - d)) - d) + 1.0d));
    }

    public final double b(double d) {
        double d2 = this.d;
        if (d < d2) {
            return (d2 * d) / (d + (this.c * (d2 - d)));
        }
        return ((1.0d - d2) * (d - 1.0d)) / ((1.0d - d) - (this.c * (d2 - d)));
    }

    @Override // androidx.constraintlayout.core.motion.utils.Easing
    public double get(double d) {
        return b(d);
    }

    @Override // androidx.constraintlayout.core.motion.utils.Easing
    public double getDiff(double d) {
        return a(d);
    }
}
