package androidx.camera.core.impl;

import androidx.annotation.NonNull;
import androidx.camera.core.impl.CameraCaptureMetaData;
import androidx.camera.core.impl.utils.ExifData;
/* loaded from: classes.dex */
public interface CameraCaptureResult {

    /* loaded from: classes.dex */
    public static final class EmptyCameraCaptureResult implements CameraCaptureResult {
        @NonNull
        public static CameraCaptureResult create() {
            return new EmptyCameraCaptureResult();
        }

        @Override // androidx.camera.core.impl.CameraCaptureResult
        @NonNull
        public CameraCaptureMetaData.AeState getAeState() {
            return CameraCaptureMetaData.AeState.UNKNOWN;
        }

        @Override // androidx.camera.core.impl.CameraCaptureResult
        @NonNull
        public CameraCaptureMetaData.AfMode getAfMode() {
            return CameraCaptureMetaData.AfMode.UNKNOWN;
        }

        @Override // androidx.camera.core.impl.CameraCaptureResult
        @NonNull
        public CameraCaptureMetaData.AfState getAfState() {
            return CameraCaptureMetaData.AfState.UNKNOWN;
        }

        @Override // androidx.camera.core.impl.CameraCaptureResult
        @NonNull
        public CameraCaptureMetaData.AwbState getAwbState() {
            return CameraCaptureMetaData.AwbState.UNKNOWN;
        }

        @Override // androidx.camera.core.impl.CameraCaptureResult
        @NonNull
        public CameraCaptureMetaData.FlashState getFlashState() {
            return CameraCaptureMetaData.FlashState.UNKNOWN;
        }

        @Override // androidx.camera.core.impl.CameraCaptureResult
        @NonNull
        public TagBundle getTagBundle() {
            return TagBundle.emptyBundle();
        }

        @Override // androidx.camera.core.impl.CameraCaptureResult
        public long getTimestamp() {
            return -1L;
        }
    }

    @NonNull
    CameraCaptureMetaData.AeState getAeState();

    @NonNull
    CameraCaptureMetaData.AfMode getAfMode();

    @NonNull
    CameraCaptureMetaData.AfState getAfState();

    @NonNull
    CameraCaptureMetaData.AwbState getAwbState();

    @NonNull
    CameraCaptureMetaData.FlashState getFlashState();

    @NonNull
    TagBundle getTagBundle();

    long getTimestamp();

    default void populateExifData(@NonNull ExifData.Builder builder) {
        builder.setFlashState(getFlashState());
    }
}
