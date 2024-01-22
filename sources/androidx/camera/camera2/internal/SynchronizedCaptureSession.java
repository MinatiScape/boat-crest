package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
/* loaded from: classes.dex */
public interface SynchronizedCaptureSession {

    /* loaded from: classes.dex */
    public static abstract class StateCallback {
        public void m(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        }

        @RequiresApi(api = 26)
        public void n(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        }

        public void o(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        }

        public void p(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        }

        public void q(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        }

        public void r(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        }

        public void s(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        }

        @RequiresApi(api = 23)
        public void t(@NonNull SynchronizedCaptureSession synchronizedCaptureSession, @NonNull Surface surface) {
        }
    }

    @NonNull
    StateCallback b();

    void c();

    void close();

    void d() throws CameraAccessException;

    @NonNull
    CameraDevice e();

    int f(@NonNull CaptureRequest captureRequest, @NonNull CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException;

    @NonNull
    ListenableFuture<Void> i(@NonNull String str);

    int j(@NonNull List<CaptureRequest> list, @NonNull CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException;

    @NonNull
    CameraCaptureSessionCompat k();

    void l() throws CameraAccessException;
}
