package androidx.camera.core.internal;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageInfo;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.TagBundle;
import androidx.camera.core.impl.utils.ExifData;
/* loaded from: classes.dex */
public final class CameraCaptureResultImageInfo implements ImageInfo {

    /* renamed from: a  reason: collision with root package name */
    public final CameraCaptureResult f752a;

    public CameraCaptureResultImageInfo(@NonNull CameraCaptureResult cameraCaptureResult) {
        this.f752a = cameraCaptureResult;
    }

    @NonNull
    public CameraCaptureResult getCameraCaptureResult() {
        return this.f752a;
    }

    @Override // androidx.camera.core.ImageInfo
    public int getRotationDegrees() {
        return 0;
    }

    @Override // androidx.camera.core.ImageInfo
    @NonNull
    public TagBundle getTagBundle() {
        return this.f752a.getTagBundle();
    }

    @Override // androidx.camera.core.ImageInfo
    public long getTimestamp() {
        return this.f752a.getTimestamp();
    }

    @Override // androidx.camera.core.ImageInfo
    public void populateExifData(@NonNull ExifData.Builder builder) {
        this.f752a.populateExifData(builder);
    }
}
