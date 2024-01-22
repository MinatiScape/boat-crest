package androidx.camera.core.impl;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.InitializationException;
/* loaded from: classes.dex */
public interface UseCaseConfigFactory {

    /* loaded from: classes.dex */
    public enum CaptureType {
        IMAGE_CAPTURE,
        PREVIEW,
        IMAGE_ANALYSIS,
        VIDEO_CAPTURE
    }

    /* loaded from: classes.dex */
    public interface Provider {
        @NonNull
        UseCaseConfigFactory newInstance(@NonNull Context context) throws InitializationException;
    }

    @Nullable
    Config getConfig(@NonNull CaptureType captureType);
}
