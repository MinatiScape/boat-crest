package androidx.camera.camera2.interop;

import android.hardware.camera2.CameraCharacteristics;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.camera.camera2.internal.Camera2CameraInfoImpl;
import androidx.camera.core.CameraInfo;
import androidx.core.util.Preconditions;
@ExperimentalCamera2Interop
/* loaded from: classes.dex */
public final class Camera2CameraInfo {

    /* renamed from: a  reason: collision with root package name */
    public final Camera2CameraInfoImpl f605a;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Camera2CameraInfo(@NonNull Camera2CameraInfoImpl camera2CameraInfoImpl) {
        this.f605a = camera2CameraInfoImpl;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static CameraCharacteristics extractCameraCharacteristics(@NonNull CameraInfo cameraInfo) {
        Preconditions.checkState(cameraInfo instanceof Camera2CameraInfoImpl, "CameraInfo does not contain any Camera2 information.");
        return ((Camera2CameraInfoImpl) cameraInfo).getCameraCharacteristicsCompat().toCameraCharacteristics();
    }

    @NonNull
    public static Camera2CameraInfo from(@NonNull CameraInfo cameraInfo) {
        Preconditions.checkArgument(cameraInfo instanceof Camera2CameraInfoImpl, "CameraInfo doesn't contain Camera2 implementation.");
        return ((Camera2CameraInfoImpl) cameraInfo).getCamera2CameraInfo();
    }

    @Nullable
    public <T> T getCameraCharacteristic(@NonNull CameraCharacteristics.Key<T> key) {
        return (T) this.f605a.getCameraCharacteristicsCompat().get(key);
    }

    @NonNull
    public String getCameraId() {
        return this.f605a.getCameraId();
    }
}
