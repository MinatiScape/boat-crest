package androidx.camera.core;

import android.graphics.PointF;
import android.util.Rational;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
/* loaded from: classes.dex */
public abstract class MeteringPointFactory {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Rational f648a;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public MeteringPointFactory() {
        this(null);
    }

    public static float getDefaultPointSize() {
        return 0.15f;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public abstract PointF convertPoint(float f, float f2);

    @NonNull
    public final MeteringPoint createPoint(float f, float f2) {
        return createPoint(f, f2, getDefaultPointSize());
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public MeteringPointFactory(@Nullable Rational rational) {
        this.f648a = rational;
    }

    @NonNull
    public final MeteringPoint createPoint(float f, float f2, float f3) {
        PointF convertPoint = convertPoint(f, f2);
        return new MeteringPoint(convertPoint.x, convertPoint.y, f3, this.f648a);
    }
}
