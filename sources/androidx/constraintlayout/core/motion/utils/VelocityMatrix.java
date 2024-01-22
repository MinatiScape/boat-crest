package androidx.constraintlayout.core.motion.utils;
/* loaded from: classes.dex */
public class VelocityMatrix {

    /* renamed from: a  reason: collision with root package name */
    public float f890a;
    public float b;
    public float c;
    public float d;
    public float e;
    public float f;

    public void applyTransform(float f, float f2, int i, int i2, float[] fArr) {
        float f3;
        float f4 = fArr[0];
        float f5 = fArr[1];
        float f6 = (f2 - 0.5f) * 2.0f;
        float f7 = f4 + this.c;
        float f8 = f5 + this.d;
        float f9 = f7 + (this.f890a * (f - 0.5f) * 2.0f);
        float f10 = f8 + (this.b * f6);
        float radians = (float) Math.toRadians(this.e);
        double radians2 = (float) Math.toRadians(this.f);
        double d = i2 * f6;
        fArr[0] = f9 + (((float) ((((-i) * f3) * Math.sin(radians2)) - (Math.cos(radians2) * d))) * radians);
        fArr[1] = f10 + (radians * ((float) (((i * f3) * Math.cos(radians2)) - (d * Math.sin(radians2)))));
    }

    public void clear() {
        this.e = 0.0f;
        this.d = 0.0f;
        this.c = 0.0f;
        this.b = 0.0f;
        this.f890a = 0.0f;
    }

    public void setRotationVelocity(SplineSet splineSet, float f) {
        if (splineSet != null) {
            this.e = splineSet.getSlope(f);
            this.f = splineSet.get(f);
        }
    }

    public void setScaleVelocity(SplineSet splineSet, SplineSet splineSet2, float f) {
        if (splineSet != null) {
            this.f890a = splineSet.getSlope(f);
        }
        if (splineSet2 != null) {
            this.b = splineSet2.getSlope(f);
        }
    }

    public void setTranslationVelocity(SplineSet splineSet, SplineSet splineSet2, float f) {
        if (splineSet != null) {
            this.c = splineSet.getSlope(f);
        }
        if (splineSet2 != null) {
            this.d = splineSet2.getSlope(f);
        }
    }

    public void setRotationVelocity(KeyCycleOscillator keyCycleOscillator, float f) {
        if (keyCycleOscillator != null) {
            this.e = keyCycleOscillator.getSlope(f);
        }
    }

    public void setScaleVelocity(KeyCycleOscillator keyCycleOscillator, KeyCycleOscillator keyCycleOscillator2, float f) {
        if (keyCycleOscillator != null) {
            this.f890a = keyCycleOscillator.getSlope(f);
        }
        if (keyCycleOscillator2 != null) {
            this.b = keyCycleOscillator2.getSlope(f);
        }
    }

    public void setTranslationVelocity(KeyCycleOscillator keyCycleOscillator, KeyCycleOscillator keyCycleOscillator2, float f) {
        if (keyCycleOscillator != null) {
            this.c = keyCycleOscillator.getSlope(f);
        }
        if (keyCycleOscillator2 != null) {
            this.d = keyCycleOscillator2.getSlope(f);
        }
    }
}
