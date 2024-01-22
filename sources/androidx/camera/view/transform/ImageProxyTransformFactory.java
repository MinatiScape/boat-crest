package androidx.camera.view.transform;

import android.graphics.Matrix;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.camera.core.ImageProxy;
import androidx.camera.view.TransformExperimental;
import androidx.camera.view.TransformUtils;
@TransformExperimental
/* loaded from: classes.dex */
public final class ImageProxyTransformFactory {

    /* renamed from: a  reason: collision with root package name */
    public boolean f826a;
    public boolean b;

    public static RectF b(RectF rectF, int i) {
        if (TransformUtils.is90or270(i)) {
            return new RectF(0.0f, 0.0f, rectF.height(), rectF.width());
        }
        return new RectF(0.0f, 0.0f, rectF.width(), rectF.height());
    }

    public final RectF a(@NonNull ImageProxy imageProxy) {
        if (this.f826a) {
            return new RectF(imageProxy.getCropRect());
        }
        return new RectF(0.0f, 0.0f, imageProxy.getWidth(), imageProxy.getHeight());
    }

    public final int c(@NonNull ImageProxy imageProxy) {
        if (this.b) {
            return imageProxy.getImageInfo().getRotationDegrees();
        }
        return 0;
    }

    @NonNull
    public OutputTransform getOutputTransform(@NonNull ImageProxy imageProxy) {
        int c = c(imageProxy);
        RectF a2 = a(imageProxy);
        Matrix rectToRect = TransformUtils.getRectToRect(a2, b(a2, c), c);
        rectToRect.preConcat(TransformUtils.getNormalizedToBuffer(imageProxy.getCropRect()));
        return new OutputTransform(rectToRect, TransformUtils.rectToSize(imageProxy.getCropRect()));
    }

    public boolean isUsingCropRect() {
        return this.f826a;
    }

    public boolean isUsingRotationDegrees() {
        return this.b;
    }

    public void setUsingCropRect(boolean z) {
        this.f826a = z;
    }

    public void setUsingRotationDegrees(boolean z) {
        this.b = z;
    }
}
