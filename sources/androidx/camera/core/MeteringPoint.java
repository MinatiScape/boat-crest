package androidx.camera.core;

import android.util.Rational;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
/* loaded from: classes.dex */
public class MeteringPoint {

    /* renamed from: a  reason: collision with root package name */
    public float f647a;
    public float b;
    public float c;
    @Nullable
    public Rational d;

    public MeteringPoint(float f, float f2, float f3, @Nullable Rational rational) {
        this.f647a = f;
        this.b = f2;
        this.c = f3;
        this.d = rational;
    }

    public float getSize() {
        return this.c;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Rational getSurfaceAspectRatio() {
        return this.d;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public float getX() {
        return this.f647a;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public float getY() {
        return this.b;
    }
}
