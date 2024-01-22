package androidx.camera.camera2.internal.compat;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.params.SessionConfiguration;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.core.util.Preconditions;
@RequiresApi(28)
/* loaded from: classes.dex */
public class e extends d {
    public e(@NonNull CameraDevice cameraDevice) {
        super((CameraDevice) Preconditions.checkNotNull(cameraDevice), null);
    }

    @Override // androidx.camera.camera2.internal.compat.d, androidx.camera.camera2.internal.compat.c, androidx.camera.camera2.internal.compat.f, androidx.camera.camera2.internal.compat.CameraDeviceCompat.a
    public void b(@NonNull SessionConfigurationCompat sessionConfigurationCompat) throws CameraAccessException {
        SessionConfiguration sessionConfiguration = (SessionConfiguration) sessionConfigurationCompat.unwrap();
        Preconditions.checkNotNull(sessionConfiguration);
        this.f527a.createCaptureSession(sessionConfiguration);
    }
}
