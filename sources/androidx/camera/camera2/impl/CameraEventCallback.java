package androidx.camera.camera2.impl;

import androidx.annotation.Nullable;
import androidx.camera.core.impl.CaptureConfig;
/* loaded from: classes.dex */
public abstract class CameraEventCallback {
    @Nullable
    public CaptureConfig onDisableSession() {
        return null;
    }

    @Nullable
    public CaptureConfig onEnableSession() {
        return null;
    }

    @Nullable
    public CaptureConfig onPresetSession() {
        return null;
    }

    @Nullable
    public CaptureConfig onRepeating() {
        return null;
    }
}
