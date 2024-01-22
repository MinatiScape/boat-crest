package androidx.camera.camera2.impl;

import android.hardware.camera2.CaptureResult;
import androidx.annotation.Nullable;
import androidx.camera.camera2.internal.Camera2CameraCaptureResult;
import androidx.camera.core.impl.CameraCaptureResult;
/* loaded from: classes.dex */
public final class Camera2CameraCaptureResultConverter {
    @Nullable
    public static CaptureResult getCaptureResult(@Nullable CameraCaptureResult cameraCaptureResult) {
        if (cameraCaptureResult instanceof Camera2CameraCaptureResult) {
            return ((Camera2CameraCaptureResult) cameraCaptureResult).getCaptureResult();
        }
        return null;
    }
}
