package androidx.camera.view.transform;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.camera.core.Logger;
import androidx.camera.view.TransformExperimental;
import androidx.camera.view.TransformUtils;
import androidx.core.util.Preconditions;
@TransformExperimental
/* loaded from: classes.dex */
public final class CoordinateTransform {

    /* renamed from: a  reason: collision with root package name */
    public final Matrix f824a;

    public CoordinateTransform(@NonNull OutputTransform outputTransform, @NonNull OutputTransform outputTransform2) {
        if (!TransformUtils.isAspectRatioMatchingWithRoundingError(outputTransform.b(), false, outputTransform2.b(), false)) {
            Logger.w("CoordinateTransform", String.format("The source viewport (%s) does not match the target viewport (%s). Please make sure they are associated with the same Viewport.", outputTransform.b(), outputTransform2.b()));
        }
        Matrix matrix = new Matrix();
        this.f824a = matrix;
        Preconditions.checkState(outputTransform.a().invert(matrix), "The source transform cannot be inverted");
        matrix.postConcat(outputTransform2.a());
    }

    public void mapPoint(@NonNull PointF pointF) {
        float[] fArr = {pointF.x, pointF.y};
        this.f824a.mapPoints(fArr);
        pointF.x = fArr[0];
        pointF.y = fArr[1];
    }

    public void mapPoints(@NonNull float[] fArr) {
        this.f824a.mapPoints(fArr);
    }

    public void mapRect(@NonNull RectF rectF) {
        this.f824a.mapRect(rectF);
    }

    public void transform(@NonNull Matrix matrix) {
        matrix.set(this.f824a);
    }
}
