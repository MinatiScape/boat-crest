package com.google.android.material.bottomappbar;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.ShapePath;
/* loaded from: classes10.dex */
public class BottomAppBarTopEdgeTreatment extends EdgeTreatment implements Cloneable {
    public float h;
    public float i;
    public float j;
    public float k;
    public float l;
    public float m = -1.0f;

    public BottomAppBarTopEdgeTreatment(float f, float f2, float f3) {
        this.i = f;
        this.h = f2;
        e(f3);
        this.l = 0.0f;
    }

    public float b() {
        return this.k;
    }

    public float c() {
        return this.i;
    }

    public float d() {
        return this.h;
    }

    public void e(@FloatRange(from = 0.0d) float f) {
        if (f >= 0.0f) {
            this.k = f;
            return;
        }
        throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
    }

    public void f(float f) {
        this.i = f;
    }

    public void g(float f) {
        this.h = f;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f, float f2, float f3, @NonNull ShapePath shapePath) {
        float f4;
        float f5;
        float f6 = this.j;
        if (f6 == 0.0f) {
            shapePath.lineTo(f, 0.0f);
            return;
        }
        float f7 = ((this.i * 2.0f) + f6) / 2.0f;
        float f8 = f3 * this.h;
        float f9 = f2 + this.l;
        float f10 = (this.k * f3) + ((1.0f - f3) * f7);
        if (f10 / f7 >= 1.0f) {
            shapePath.lineTo(f, 0.0f);
            return;
        }
        float f11 = this.m;
        float f12 = f11 * f3;
        boolean z = f11 == -1.0f || Math.abs((f11 * 2.0f) - f6) < 0.1f;
        if (z) {
            f4 = f10;
            f5 = 0.0f;
        } else {
            f5 = 1.75f;
            f4 = 0.0f;
        }
        float f13 = f7 + f8;
        float f14 = f4 + f8;
        float sqrt = (float) Math.sqrt((f13 * f13) - (f14 * f14));
        float f15 = f9 - sqrt;
        float f16 = f9 + sqrt;
        float degrees = (float) Math.toDegrees(Math.atan(sqrt / f14));
        float f17 = (90.0f - degrees) + f5;
        shapePath.lineTo(f15, 0.0f);
        float f18 = f8 * 2.0f;
        shapePath.addArc(f15 - f8, 0.0f, f15 + f8, f18, 270.0f, degrees);
        if (z) {
            shapePath.addArc(f9 - f7, (-f7) - f4, f9 + f7, f7 - f4, 180.0f - f17, (f17 * 2.0f) - 180.0f);
        } else {
            float f19 = this.i;
            float f20 = f12 * 2.0f;
            float f21 = f9 - f7;
            shapePath.addArc(f21, -(f12 + f19), f21 + f19 + f20, f19 + f12, 180.0f - f17, ((f17 * 2.0f) - 180.0f) / 2.0f);
            float f22 = f9 + f7;
            float f23 = this.i;
            shapePath.lineTo(f22 - ((f23 / 2.0f) + f12), f23 + f12);
            float f24 = this.i;
            shapePath.addArc(f22 - (f20 + f24), -(f12 + f24), f22, f24 + f12, 90.0f, f17 - 90.0f);
        }
        shapePath.addArc(f16 - f8, 0.0f, f16 + f8, f18, 270.0f - degrees, degrees);
        shapePath.lineTo(f, 0.0f);
    }

    public float getFabCornerRadius() {
        return this.m;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public float getFabDiameter() {
        return this.j;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public float getHorizontalOffset() {
        return this.l;
    }

    public void h(float f) {
        this.l = f;
    }

    public void setFabCornerSize(float f) {
        this.m = f;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setFabDiameter(float f) {
        this.j = f;
    }
}
