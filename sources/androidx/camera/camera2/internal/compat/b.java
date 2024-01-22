package androidx.camera.camera2.internal.compat;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat;
import androidx.core.util.Preconditions;
import java.util.List;
import java.util.concurrent.Executor;
@RequiresApi(21)
/* loaded from: classes.dex */
public class b implements CameraCaptureSessionCompat.a {

    /* renamed from: a  reason: collision with root package name */
    public final CameraCaptureSession f525a;
    public final Object b;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final Handler f526a;

        public a(@NonNull Handler handler) {
            this.f526a = handler;
        }
    }

    public b(@NonNull CameraCaptureSession cameraCaptureSession, @Nullable Object obj) {
        this.f525a = (CameraCaptureSession) Preconditions.checkNotNull(cameraCaptureSession);
        this.b = obj;
    }

    public static CameraCaptureSessionCompat.a f(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull Handler handler) {
        return new b(cameraCaptureSession, new a(handler));
    }

    @Override // androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat.a
    @NonNull
    public CameraCaptureSession a() {
        return this.f525a;
    }

    @Override // androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat.a
    public int b(@NonNull List<CaptureRequest> list, @NonNull Executor executor, @NonNull CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return this.f525a.captureBurst(list, new CameraCaptureSessionCompat.b(executor, captureCallback), ((a) this.b).f526a);
    }

    @Override // androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat.a
    public int c(@NonNull List<CaptureRequest> list, @NonNull Executor executor, @NonNull CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return this.f525a.setRepeatingBurst(list, new CameraCaptureSessionCompat.b(executor, captureCallback), ((a) this.b).f526a);
    }

    @Override // androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat.a
    public int d(@NonNull CaptureRequest captureRequest, @NonNull Executor executor, @NonNull CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return this.f525a.setRepeatingRequest(captureRequest, new CameraCaptureSessionCompat.b(executor, captureCallback), ((a) this.b).f526a);
    }

    @Override // androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat.a
    public int e(@NonNull CaptureRequest captureRequest, @NonNull Executor executor, @NonNull CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return this.f525a.capture(captureRequest, new CameraCaptureSessionCompat.b(executor, captureCallback), ((a) this.b).f526a);
    }
}
