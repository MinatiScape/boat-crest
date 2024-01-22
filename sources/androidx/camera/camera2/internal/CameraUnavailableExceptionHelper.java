package androidx.camera.camera2.internal;

import androidx.annotation.NonNull;
import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import androidx.camera.core.CameraUnavailableException;
/* loaded from: classes.dex */
public final class CameraUnavailableExceptionHelper {
    @NonNull
    public static CameraUnavailableException createFrom(@NonNull CameraAccessExceptionCompat cameraAccessExceptionCompat) {
        int reason = cameraAccessExceptionCompat.getReason();
        int i = 5;
        if (reason == 1) {
            i = 1;
        } else if (reason == 2) {
            i = 2;
        } else if (reason == 3) {
            i = 3;
        } else if (reason == 4) {
            i = 4;
        } else if (reason != 5) {
            i = reason != 10001 ? 0 : 6;
        }
        return new CameraUnavailableException(i, cameraAccessExceptionCompat);
    }
}
