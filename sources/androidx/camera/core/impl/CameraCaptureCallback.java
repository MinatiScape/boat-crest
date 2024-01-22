package androidx.camera.core.impl;

import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public abstract class CameraCaptureCallback {
    public void onCaptureCancelled() {
    }

    public void onCaptureCompleted(@NonNull CameraCaptureResult cameraCaptureResult) {
    }

    public void onCaptureFailed(@NonNull CameraCaptureFailure cameraCaptureFailure) {
    }
}
