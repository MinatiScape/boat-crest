package androidx.constraintlayout.core.motion.utils;
/* loaded from: classes.dex */
public class SpringStopEngine implements StopEngine {
    public double b;
    public double c;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;

    /* renamed from: a  reason: collision with root package name */
    public double f884a = 0.5d;
    public int i = 0;

    public final void a(double d) {
        double d2 = this.b;
        double d3 = this.f884a;
        int sqrt = (int) ((9.0d / ((Math.sqrt(d2 / this.g) * d) * 4.0d)) + 1.0d);
        double d4 = d / sqrt;
        int i = 0;
        while (i < sqrt) {
            float f = this.e;
            double d5 = this.c;
            float f2 = this.f;
            double d6 = d2;
            float f3 = this.g;
            double d7 = d3;
            double d8 = f2 + ((((((-d2) * (f - d5)) - (f2 * d3)) / f3) * d4) / 2.0d);
            double d9 = ((((-((f + ((d4 * d8) / 2.0d)) - d5)) * d6) - (d8 * d7)) / f3) * d4;
            float f4 = (float) (f2 + d9);
            this.f = f4;
            float f5 = (float) (f + ((f2 + (d9 / 2.0d)) * d4));
            this.e = f5;
            int i2 = this.i;
            if (i2 > 0) {
                if (f5 < 0.0f && (i2 & 1) == 1) {
                    this.e = -f5;
                    this.f = -f4;
                }
                float f6 = this.e;
                if (f6 > 1.0f && (i2 & 2) == 2) {
                    this.e = 2.0f - f6;
                    this.f = -this.f;
                }
            }
            i++;
            d2 = d6;
            d3 = d7;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public String debug(String str, float f) {
        return null;
    }

    public float getAcceleration() {
        double d = this.b;
        return ((float) (((-d) * (this.e - this.c)) - (this.f884a * this.f))) / this.g;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getInterpolation(float f) {
        a(f - this.d);
        this.d = f;
        return this.e;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getVelocity() {
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getVelocity(float f) {
        return this.f;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public boolean isStopped() {
        double d = this.e - this.c;
        double d2 = this.b;
        double d3 = this.f;
        return Math.sqrt((((d3 * d3) * ((double) this.g)) + ((d2 * d) * d)) / d2) <= ((double) this.h);
    }

    public void springConfig(float f, float f2, float f3, float f4, float f5, float f6, float f7, int i) {
        this.c = f2;
        this.f884a = f6;
        this.e = f;
        this.b = f5;
        this.g = f4;
        this.h = f7;
        this.i = i;
        this.d = 0.0f;
    }
}
