package androidx.camera.core;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.camera.core.SurfaceRequest;
import java.util.Objects;
/* loaded from: classes.dex */
public final class g extends SurfaceRequest.TransformationInfo {

    /* renamed from: a  reason: collision with root package name */
    public final Rect f687a;
    public final int b;
    public final int c;

    public g(Rect rect, int i, int i2) {
        Objects.requireNonNull(rect, "Null cropRect");
        this.f687a = rect;
        this.b = i;
        this.c = i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SurfaceRequest.TransformationInfo) {
            SurfaceRequest.TransformationInfo transformationInfo = (SurfaceRequest.TransformationInfo) obj;
            return this.f687a.equals(transformationInfo.getCropRect()) && this.b == transformationInfo.getRotationDegrees() && this.c == transformationInfo.getTargetRotation();
        }
        return false;
    }

    @Override // androidx.camera.core.SurfaceRequest.TransformationInfo
    @NonNull
    public Rect getCropRect() {
        return this.f687a;
    }

    @Override // androidx.camera.core.SurfaceRequest.TransformationInfo
    public int getRotationDegrees() {
        return this.b;
    }

    @Override // androidx.camera.core.SurfaceRequest.TransformationInfo
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getTargetRotation() {
        return this.c;
    }

    public int hashCode() {
        return ((((this.f687a.hashCode() ^ 1000003) * 1000003) ^ this.b) * 1000003) ^ this.c;
    }

    public String toString() {
        return "TransformationInfo{cropRect=" + this.f687a + ", rotationDegrees=" + this.b + ", targetRotation=" + this.c + "}";
    }
}
