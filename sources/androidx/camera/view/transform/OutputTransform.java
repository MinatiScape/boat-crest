package androidx.camera.view.transform;

import android.graphics.Matrix;
import android.util.Size;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.camera.view.TransformExperimental;
@TransformExperimental
/* loaded from: classes.dex */
public final class OutputTransform {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final Matrix f827a;
    @NonNull
    public final Size b;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public OutputTransform(@NonNull Matrix matrix, @NonNull Size size) {
        this.f827a = matrix;
        this.b = size;
    }

    @NonNull
    public Matrix a() {
        return this.f827a;
    }

    @NonNull
    public Size b() {
        return this.b;
    }
}
