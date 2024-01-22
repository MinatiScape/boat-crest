package androidx.camera.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import java.util.LinkedHashSet;
/* loaded from: classes.dex */
public interface Camera {
    @NonNull
    CameraControl getCameraControl();

    @NonNull
    CameraInfo getCameraInfo();

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    LinkedHashSet<CameraInternal> getCameraInternals();

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    CameraConfig getExtendedConfig();

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    void setExtendedConfig(@Nullable CameraConfig cameraConfig) throws CameraUseCaseAdapter.CameraException;
}
