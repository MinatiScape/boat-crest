package androidx.camera.camera2.internal.compat;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.util.List;
import java.util.concurrent.Executor;
@RequiresApi(28)
/* loaded from: classes.dex */
public class a extends b {
    public a(@NonNull CameraCaptureSession cameraCaptureSession) {
        super(cameraCaptureSession, null);
    }

    @Override // androidx.camera.camera2.internal.compat.b, androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat.a
    public int b(@NonNull List<CaptureRequest> list, @NonNull Executor executor, @NonNull CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return this.f525a.captureBurstRequests(list, executor, captureCallback);
    }

    @Override // androidx.camera.camera2.internal.compat.b, androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat.a
    public int c(@NonNull List<CaptureRequest> list, @NonNull Executor executor, @NonNull CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return this.f525a.setRepeatingBurstRequests(list, executor, captureCallback);
    }

    @Override // androidx.camera.camera2.internal.compat.b, androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat.a
    public int d(@NonNull CaptureRequest captureRequest, @NonNull Executor executor, @NonNull CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return this.f525a.setSingleRepeatingRequest(captureRequest, executor, captureCallback);
    }

    @Override // androidx.camera.camera2.internal.compat.b, androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat.a
    public int e(@NonNull CaptureRequest captureRequest, @NonNull Executor executor, @NonNull CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return this.f525a.captureSingleRequest(captureRequest, executor, captureCallback);
    }
}
