package androidx.constraintlayout.core.motion.utils;
/* loaded from: classes.dex */
public class StopLogicEngine implements StopEngine {

    /* renamed from: a  reason: collision with root package name */
    public float f885a;
    public float b;
    public float c;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;
    public float i;
    public int j;
    public String k;
    public boolean l = false;
    public float m;
    public float n;

    public final float a(float f) {
        float f2 = this.d;
        if (f <= f2) {
            float f3 = this.f885a;
            return (f3 * f) + ((((this.b - f3) * f) * f) / (f2 * 2.0f));
        }
        int i = this.j;
        if (i == 1) {
            return this.g;
        }
        float f4 = f - f2;
        float f5 = this.e;
        if (f4 < f5) {
            float f6 = this.g;
            float f7 = this.b;
            return f6 + (f7 * f4) + ((((this.c - f7) * f4) * f4) / (f5 * 2.0f));
        } else if (i == 2) {
            return this.h;
        } else {
            float f8 = f4 - f5;
            float f9 = this.f;
            if (f8 <= f9) {
                float f10 = this.h;
                float f11 = this.c;
                return (f10 + (f11 * f8)) - (((f11 * f8) * f8) / (f9 * 2.0f));
            }
            return this.i;
        }
    }

    public final void b(float f, float f2, float f3, float f4, float f5) {
        if (f == 0.0f) {
            f = 1.0E-4f;
        }
        this.f885a = f;
        float f6 = f / f3;
        float f7 = (f6 * f) / 2.0f;
        if (f < 0.0f) {
            float sqrt = (float) Math.sqrt((f2 - ((((-f) / f3) * f) / 2.0f)) * f3);
            if (sqrt < f4) {
                this.k = "backward accelerate, decelerate";
                this.j = 2;
                this.f885a = f;
                this.b = sqrt;
                this.c = 0.0f;
                float f8 = (sqrt - f) / f3;
                this.d = f8;
                this.e = sqrt / f3;
                this.g = ((f + sqrt) * f8) / 2.0f;
                this.h = f2;
                this.i = f2;
                return;
            }
            this.k = "backward accelerate cruse decelerate";
            this.j = 3;
            this.f885a = f;
            this.b = f4;
            this.c = f4;
            float f9 = (f4 - f) / f3;
            this.d = f9;
            float f10 = f4 / f3;
            this.f = f10;
            float f11 = ((f + f4) * f9) / 2.0f;
            float f12 = (f10 * f4) / 2.0f;
            this.e = ((f2 - f11) - f12) / f4;
            this.g = f11;
            this.h = f2 - f12;
            this.i = f2;
        } else if (f7 >= f2) {
            this.k = "hard stop";
            this.j = 1;
            this.f885a = f;
            this.b = 0.0f;
            this.g = f2;
            this.d = (2.0f * f2) / f;
        } else {
            float f13 = f2 - f7;
            float f14 = f13 / f;
            if (f14 + f6 < f5) {
                this.k = "cruse decelerate";
                this.j = 2;
                this.f885a = f;
                this.b = f;
                this.c = 0.0f;
                this.g = f13;
                this.h = f2;
                this.d = f14;
                this.e = f6;
                return;
            }
            float sqrt2 = (float) Math.sqrt((f3 * f2) + ((f * f) / 2.0f));
            float f15 = (sqrt2 - f) / f3;
            this.d = f15;
            float f16 = sqrt2 / f3;
            this.e = f16;
            if (sqrt2 < f4) {
                this.k = "accelerate decelerate";
                this.j = 2;
                this.f885a = f;
                this.b = sqrt2;
                this.c = 0.0f;
                this.d = f15;
                this.e = f16;
                this.g = ((f + sqrt2) * f15) / 2.0f;
                this.h = f2;
                return;
            }
            this.k = "accelerate cruse decelerate";
            this.j = 3;
            this.f885a = f;
            this.b = f4;
            this.c = f4;
            float f17 = (f4 - f) / f3;
            this.d = f17;
            float f18 = f4 / f3;
            this.f = f18;
            float f19 = ((f + f4) * f17) / 2.0f;
            float f20 = (f18 * f4) / 2.0f;
            this.e = ((f2 - f19) - f20) / f4;
            this.g = f19;
            this.h = f2 - f20;
            this.i = f2;
        }
    }

    public void config(float f, float f2, float f3, float f4, float f5, float f6) {
        this.m = f;
        boolean z = f > f2;
        this.l = z;
        if (z) {
            b(-f3, f - f2, f5, f6, f4);
        } else {
            b(f3, f2 - f, f5, f6, f4);
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public String debug(String str, float f) {
        StringBuilder sb = new StringBuilder();
        sb.append(str + " ===== " + this.k + "\n");
        sb.append(str);
        sb.append(this.l ? "backwards" : "forward ");
        sb.append(" time = ");
        sb.append(f);
        sb.append("  stages ");
        sb.append(this.j);
        sb.append("\n");
        String str2 = sb.toString() + str + " dur " + this.d + " vel " + this.f885a + " pos " + this.g + "\n";
        if (this.j > 1) {
            str2 = str2 + str + " dur " + this.e + " vel " + this.b + " pos " + this.h + "\n";
        }
        if (this.j > 2) {
            str2 = str2 + str + " dur " + this.f + " vel " + this.c + " pos " + this.i + "\n";
        }
        float f2 = this.d;
        if (f <= f2) {
            return str2 + str + "stage 0\n";
        }
        int i = this.j;
        if (i == 1) {
            return str2 + str + "end stage 0\n";
        }
        float f3 = f - f2;
        float f4 = this.e;
        if (f3 < f4) {
            return str2 + str + " stage 1\n";
        } else if (i == 2) {
            return str2 + str + "end stage 1\n";
        } else if (f3 - f4 < this.f) {
            return str2 + str + " stage 2\n";
        } else {
            return str2 + str + " end stage 2\n";
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getInterpolation(float f) {
        float a2 = a(f);
        this.n = f;
        return this.l ? this.m - a2 : this.m + a2;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getVelocity(float f) {
        float f2 = this.d;
        if (f <= f2) {
            float f3 = this.f885a;
            return f3 + (((this.b - f3) * f) / f2);
        }
        int i = this.j;
        if (i == 1) {
            return 0.0f;
        }
        float f4 = f - f2;
        float f5 = this.e;
        if (f4 < f5) {
            float f6 = this.b;
            return f6 + (((this.c - f6) * f4) / f5);
        } else if (i == 2) {
            return this.h;
        } else {
            float f7 = f4 - f5;
            float f8 = this.f;
            if (f7 < f8) {
                float f9 = this.c;
                return f9 - ((f7 * f9) / f8);
            }
            return this.i;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public boolean isStopped() {
        return getVelocity() < 1.0E-5f && Math.abs(this.i - this.n) < 1.0E-5f;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getVelocity() {
        return this.l ? -getVelocity(this.n) : getVelocity(this.n);
    }
}
