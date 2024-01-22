package androidx.dynamicanimation.animation;

import androidx.annotation.FloatRange;
import androidx.annotation.RestrictTo;
import androidx.dynamicanimation.animation.DynamicAnimation;
/* loaded from: classes.dex */
public final class SpringForce {
    public static final float DAMPING_RATIO_HIGH_BOUNCY = 0.2f;
    public static final float DAMPING_RATIO_LOW_BOUNCY = 0.75f;
    public static final float DAMPING_RATIO_MEDIUM_BOUNCY = 0.5f;
    public static final float DAMPING_RATIO_NO_BOUNCY = 1.0f;
    public static final float STIFFNESS_HIGH = 10000.0f;
    public static final float STIFFNESS_LOW = 200.0f;
    public static final float STIFFNESS_MEDIUM = 1500.0f;
    public static final float STIFFNESS_VERY_LOW = 50.0f;

    /* renamed from: a  reason: collision with root package name */
    public double f1246a;
    public double b;
    public boolean c;
    public double d;
    public double e;
    public double f;
    public double g;
    public double h;
    public double i;
    public final DynamicAnimation.p j;

    public SpringForce() {
        this.f1246a = Math.sqrt(1500.0d);
        this.b = 0.5d;
        this.c = false;
        this.i = Double.MAX_VALUE;
        this.j = new DynamicAnimation.p();
    }

    public final void a() {
        if (this.c) {
            return;
        }
        if (this.i != Double.MAX_VALUE) {
            double d = this.b;
            if (d > 1.0d) {
                double d2 = this.f1246a;
                this.f = ((-d) * d2) + (d2 * Math.sqrt((d * d) - 1.0d));
                double d3 = this.b;
                double d4 = this.f1246a;
                this.g = ((-d3) * d4) - (d4 * Math.sqrt((d3 * d3) - 1.0d));
            } else if (d >= 0.0d && d < 1.0d) {
                this.h = this.f1246a * Math.sqrt(1.0d - (d * d));
            }
            this.c = true;
            return;
        }
        throw new IllegalStateException("Error: Final position of the spring must be set before the animation starts");
    }

    public void b(double d) {
        double abs = Math.abs(d);
        this.d = abs;
        this.e = abs * 62.5d;
    }

    public DynamicAnimation.p c(double d, double d2, long j) {
        double cos;
        double d3;
        a();
        double d4 = j / 1000.0d;
        double d5 = d - this.i;
        double d6 = this.b;
        if (d6 > 1.0d) {
            double d7 = this.g;
            double d8 = this.f;
            double d9 = d5 - (((d7 * d5) - d2) / (d7 - d8));
            double d10 = ((d5 * d7) - d2) / (d7 - d8);
            d3 = (Math.pow(2.718281828459045d, d7 * d4) * d9) + (Math.pow(2.718281828459045d, this.f * d4) * d10);
            double d11 = this.g;
            double pow = d9 * d11 * Math.pow(2.718281828459045d, d11 * d4);
            double d12 = this.f;
            cos = pow + (d10 * d12 * Math.pow(2.718281828459045d, d12 * d4));
        } else if (d6 == 1.0d) {
            double d13 = this.f1246a;
            double d14 = d2 + (d13 * d5);
            double d15 = d5 + (d14 * d4);
            d3 = Math.pow(2.718281828459045d, (-d13) * d4) * d15;
            double pow2 = d15 * Math.pow(2.718281828459045d, (-this.f1246a) * d4);
            double d16 = this.f1246a;
            cos = (d14 * Math.pow(2.718281828459045d, (-d16) * d4)) + (pow2 * (-d16));
        } else {
            double d17 = 1.0d / this.h;
            double d18 = this.f1246a;
            double d19 = d17 * ((d6 * d18 * d5) + d2);
            double pow3 = Math.pow(2.718281828459045d, (-d6) * d18 * d4) * ((Math.cos(this.h * d4) * d5) + (Math.sin(this.h * d4) * d19));
            double d20 = this.f1246a;
            double d21 = this.b;
            double pow4 = Math.pow(2.718281828459045d, (-d21) * d20 * d4);
            double d22 = this.h;
            double sin = (-d22) * d5 * Math.sin(d22 * d4);
            double d23 = this.h;
            cos = ((-d20) * pow3 * d21) + (pow4 * (sin + (d19 * d23 * Math.cos(d23 * d4))));
            d3 = pow3;
        }
        DynamicAnimation.p pVar = this.j;
        pVar.f1242a = (float) (d3 + this.i);
        pVar.b = (float) cos;
        return pVar;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public float getAcceleration(float f, float f2) {
        float finalPosition = f - getFinalPosition();
        double d = this.f1246a;
        return (float) (((-(d * d)) * finalPosition) - (((d * 2.0d) * this.b) * f2));
    }

    public float getDampingRatio() {
        return (float) this.b;
    }

    public float getFinalPosition() {
        return (float) this.i;
    }

    public float getStiffness() {
        double d = this.f1246a;
        return (float) (d * d);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean isAtEquilibrium(float f, float f2) {
        return ((double) Math.abs(f2)) < this.e && ((double) Math.abs(f - getFinalPosition())) < this.d;
    }

    public SpringForce setDampingRatio(@FloatRange(from = 0.0d) float f) {
        if (f >= 0.0f) {
            this.b = f;
            this.c = false;
            return this;
        }
        throw new IllegalArgumentException("Damping ratio must be non-negative");
    }

    public SpringForce setFinalPosition(float f) {
        this.i = f;
        return this;
    }

    public SpringForce setStiffness(@FloatRange(from = 0.0d, fromInclusive = false) float f) {
        if (f > 0.0f) {
            this.f1246a = Math.sqrt(f);
            this.c = false;
            return this;
        }
        throw new IllegalArgumentException("Spring stiffness constant must be positive.");
    }

    public SpringForce(float f) {
        this.f1246a = Math.sqrt(1500.0d);
        this.b = 0.5d;
        this.c = false;
        this.i = Double.MAX_VALUE;
        this.j = new DynamicAnimation.p();
        this.i = f;
    }
}
