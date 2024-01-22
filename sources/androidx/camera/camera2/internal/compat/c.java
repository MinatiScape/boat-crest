package androidx.camera.camera2.internal.compat;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.params.InputConfiguration;
import android.os.Handler;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat;
import androidx.camera.camera2.internal.compat.f;
import androidx.camera.camera2.internal.compat.params.InputConfigurationCompat;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.core.util.Preconditions;
import java.util.List;
@RequiresApi(23)
/* loaded from: classes.dex */
public class c extends f {
    public c(@NonNull CameraDevice cameraDevice, @Nullable Object obj) {
        super(cameraDevice, obj);
    }

    public static c h(@NonNull CameraDevice cameraDevice, @NonNull Handler handler) {
        return new c(cameraDevice, new f.a(handler));
    }

    @Override // androidx.camera.camera2.internal.compat.f, androidx.camera.camera2.internal.compat.CameraDeviceCompat.a
    public void b(@NonNull SessionConfigurationCompat sessionConfigurationCompat) throws CameraAccessException {
        f.d(this.f527a, sessionConfigurationCompat);
        CameraCaptureSessionCompat.c cVar = new CameraCaptureSessionCompat.c(sessionConfigurationCompat.getExecutor(), sessionConfigurationCompat.getStateCallback());
        List<Surface> g = f.g(sessionConfigurationCompat.getOutputConfigurations());
        Handler handler = ((f.a) Preconditions.checkNotNull((f.a) this.b)).f528a;
        InputConfigurationCompat inputConfiguration = sessionConfigurationCompat.getInputConfiguration();
        if (inputConfiguration != null) {
            InputConfiguration inputConfiguration2 = (InputConfiguration) inputConfiguration.unwrap();
            Preconditions.checkNotNull(inputConfiguration2);
            this.f527a.createReprocessableCaptureSession(inputConfiguration2, g, cVar, handler);
        } else if (sessionConfigurationCompat.getSessionType() == 1) {
            this.f527a.createConstrainedHighSpeedCaptureSession(g, cVar, handler);
        } else {
            f(this.f527a, g, cVar, handler);
        }
    }
}
