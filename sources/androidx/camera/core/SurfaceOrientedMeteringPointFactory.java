package androidx.camera.core;

import android.graphics.PointF;
import android.util.Rational;
import android.util.Size;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
/* loaded from: classes.dex */
public class SurfaceOrientedMeteringPointFactory extends MeteringPointFactory {
    public final float b;
    public final float c;

    public SurfaceOrientedMeteringPointFactory(float f, float f2) {
        this.b = f;
        this.c = f2;
    }

    @Nullable
    public static Rational a(@Nullable UseCase useCase) {
        if (useCase == null) {
            return null;
        }
        Size attachedSurfaceResolution = useCase.getAttachedSurfaceResolution();
        if (attachedSurfaceResolution != null) {
            return new Rational(attachedSurfaceResolution.getWidth(), attachedSurfaceResolution.getHeight());
        }
        throw new IllegalStateException("UseCase " + useCase + " is not bound.");
    }

    @Override // androidx.camera.core.MeteringPointFactory
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public PointF convertPoint(float f, float f2) {
        return new PointF(f / this.b, f2 / this.c);
    }

    public SurfaceOrientedMeteringPointFactory(float f, float f2, @NonNull UseCase useCase) {
        super(a(useCase));
        this.b = f;
        this.c = f2;
    }
}
