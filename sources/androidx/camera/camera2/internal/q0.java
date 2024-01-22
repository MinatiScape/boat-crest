package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCaptureSession;
import androidx.annotation.NonNull;
import androidx.camera.core.impl.CameraCaptureCallback;
import java.util.Objects;
/* loaded from: classes.dex */
public final class q0 extends CameraCaptureCallback {

    /* renamed from: a  reason: collision with root package name */
    public final CameraCaptureSession.CaptureCallback f582a;

    public q0(CameraCaptureSession.CaptureCallback captureCallback) {
        Objects.requireNonNull(captureCallback, "captureCallback is null");
        this.f582a = captureCallback;
    }

    public static q0 a(CameraCaptureSession.CaptureCallback captureCallback) {
        return new q0(captureCallback);
    }

    @NonNull
    public CameraCaptureSession.CaptureCallback b() {
        return this.f582a;
    }
}
