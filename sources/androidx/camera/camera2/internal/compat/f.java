package androidx.camera.camera2.internal.compat;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.os.Handler;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat;
import androidx.camera.camera2.internal.compat.CameraDeviceCompat;
import androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.camera.core.Logger;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.List;
@RequiresApi(21)
/* loaded from: classes.dex */
public class f implements CameraDeviceCompat.a {

    /* renamed from: a  reason: collision with root package name */
    public final CameraDevice f527a;
    public final Object b;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final Handler f528a;

        public a(@NonNull Handler handler) {
            this.f528a = handler;
        }
    }

    public f(@NonNull CameraDevice cameraDevice, @Nullable Object obj) {
        this.f527a = (CameraDevice) Preconditions.checkNotNull(cameraDevice);
        this.b = obj;
    }

    public static void c(CameraDevice cameraDevice, @NonNull List<OutputConfigurationCompat> list) {
        String id = cameraDevice.getId();
        for (OutputConfigurationCompat outputConfigurationCompat : list) {
            String physicalCameraId = outputConfigurationCompat.getPhysicalCameraId();
            if (physicalCameraId != null && !physicalCameraId.isEmpty()) {
                Logger.w("CameraDeviceCompat", "Camera " + id + ": Camera doesn't support physicalCameraId " + physicalCameraId + ". Ignoring.");
            }
        }
    }

    public static void d(CameraDevice cameraDevice, SessionConfigurationCompat sessionConfigurationCompat) {
        Preconditions.checkNotNull(cameraDevice);
        Preconditions.checkNotNull(sessionConfigurationCompat);
        Preconditions.checkNotNull(sessionConfigurationCompat.getStateCallback());
        List<OutputConfigurationCompat> outputConfigurations = sessionConfigurationCompat.getOutputConfigurations();
        if (outputConfigurations != null) {
            if (sessionConfigurationCompat.getExecutor() != null) {
                c(cameraDevice, outputConfigurations);
                return;
            }
            throw new IllegalArgumentException("Invalid executor");
        }
        throw new IllegalArgumentException("Invalid output configurations");
    }

    public static f e(@NonNull CameraDevice cameraDevice, @NonNull Handler handler) {
        return new f(cameraDevice, new a(handler));
    }

    public static List<Surface> g(@NonNull List<OutputConfigurationCompat> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (OutputConfigurationCompat outputConfigurationCompat : list) {
            arrayList.add(outputConfigurationCompat.getSurface());
        }
        return arrayList;
    }

    @Override // androidx.camera.camera2.internal.compat.CameraDeviceCompat.a
    @NonNull
    public CameraDevice a() {
        return this.f527a;
    }

    @Override // androidx.camera.camera2.internal.compat.CameraDeviceCompat.a
    public void b(@NonNull SessionConfigurationCompat sessionConfigurationCompat) throws CameraAccessException {
        d(this.f527a, sessionConfigurationCompat);
        if (sessionConfigurationCompat.getInputConfiguration() == null) {
            if (sessionConfigurationCompat.getSessionType() != 1) {
                CameraCaptureSessionCompat.c cVar = new CameraCaptureSessionCompat.c(sessionConfigurationCompat.getExecutor(), sessionConfigurationCompat.getStateCallback());
                f(this.f527a, g(sessionConfigurationCompat.getOutputConfigurations()), cVar, ((a) this.b).f528a);
                return;
            }
            throw new IllegalArgumentException("High speed capture sessions not supported until API 23");
        }
        throw new IllegalArgumentException("Reprocessing sessions not supported until API 23");
    }

    public void f(@NonNull CameraDevice cameraDevice, @NonNull List<Surface> list, @NonNull CameraCaptureSession.StateCallback stateCallback, @NonNull Handler handler) throws CameraAccessException {
        cameraDevice.createCaptureSession(list, stateCallback, handler);
    }
}
