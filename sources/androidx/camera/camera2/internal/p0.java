package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import androidx.annotation.NonNull;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureFailure;
import androidx.camera.core.impl.TagBundle;
import androidx.core.util.Preconditions;
import java.util.Objects;
/* loaded from: classes.dex */
public final class p0 extends CameraCaptureSession.CaptureCallback {

    /* renamed from: a  reason: collision with root package name */
    public final CameraCaptureCallback f580a;

    public p0(CameraCaptureCallback cameraCaptureCallback) {
        Objects.requireNonNull(cameraCaptureCallback, "cameraCaptureCallback is null");
        this.f580a = cameraCaptureCallback;
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureCompleted(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull TotalCaptureResult totalCaptureResult) {
        TagBundle emptyBundle;
        super.onCaptureCompleted(cameraCaptureSession, captureRequest, totalCaptureResult);
        Object tag = captureRequest.getTag();
        if (tag != null) {
            Preconditions.checkArgument(tag instanceof TagBundle, "The tagBundle object from the CaptureResult is not a TagBundle object.");
            emptyBundle = (TagBundle) tag;
        } else {
            emptyBundle = TagBundle.emptyBundle();
        }
        this.f580a.onCaptureCompleted(new Camera2CameraCaptureResult(emptyBundle, totalCaptureResult));
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureFailed(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull CaptureFailure captureFailure) {
        super.onCaptureFailed(cameraCaptureSession, captureRequest, captureFailure);
        this.f580a.onCaptureFailed(new CameraCaptureFailure(CameraCaptureFailure.Reason.ERROR));
    }
}
