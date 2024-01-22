package androidx.camera.camera2.internal.compat;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.params.InputConfiguration;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat;
import androidx.camera.camera2.internal.compat.f;
import androidx.camera.camera2.internal.compat.params.InputConfigurationCompat;
import androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.core.util.Preconditions;
import java.util.List;
@RequiresApi(24)
/* loaded from: classes.dex */
public class d extends c {
    public d(@NonNull CameraDevice cameraDevice, @Nullable Object obj) {
        super(cameraDevice, obj);
    }

    public static d i(@NonNull CameraDevice cameraDevice, @NonNull Handler handler) {
        return new d(cameraDevice, new f.a(handler));
    }

    @Override // androidx.camera.camera2.internal.compat.c, androidx.camera.camera2.internal.compat.f, androidx.camera.camera2.internal.compat.CameraDeviceCompat.a
    public void b(@NonNull SessionConfigurationCompat sessionConfigurationCompat) throws CameraAccessException {
        f.d(this.f527a, sessionConfigurationCompat);
        CameraCaptureSessionCompat.c cVar = new CameraCaptureSessionCompat.c(sessionConfigurationCompat.getExecutor(), sessionConfigurationCompat.getStateCallback());
        List<OutputConfigurationCompat> outputConfigurations = sessionConfigurationCompat.getOutputConfigurations();
        Handler handler = ((f.a) Preconditions.checkNotNull((f.a) this.b)).f528a;
        InputConfigurationCompat inputConfiguration = sessionConfigurationCompat.getInputConfiguration();
        if (inputConfiguration != null) {
            InputConfiguration inputConfiguration2 = (InputConfiguration) inputConfiguration.unwrap();
            Preconditions.checkNotNull(inputConfiguration2);
            this.f527a.createReprocessableCaptureSessionByConfigurations(inputConfiguration2, SessionConfigurationCompat.transformFromCompat(outputConfigurations), cVar, handler);
        } else if (sessionConfigurationCompat.getSessionType() == 1) {
            this.f527a.createConstrainedHighSpeedCaptureSession(f.g(outputConfigurations), cVar, handler);
        } else {
            this.f527a.createCaptureSessionByOutputConfigurations(SessionConfigurationCompat.transformFromCompat(outputConfigurations), cVar, handler);
        }
    }
}
