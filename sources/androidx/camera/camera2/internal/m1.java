package androidx.camera.camera2.internal;

import androidx.annotation.NonNull;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.compat.workaround.ImageCapturePixelHDRPlus;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.ImageCaptureConfig;
import androidx.camera.core.impl.UseCaseConfig;
/* loaded from: classes.dex */
public final class m1 extends j0 {
    public static final m1 c = new m1(new ImageCapturePixelHDRPlus());
    @NonNull
    public final ImageCapturePixelHDRPlus b;

    public m1(@NonNull ImageCapturePixelHDRPlus imageCapturePixelHDRPlus) {
        this.b = imageCapturePixelHDRPlus;
    }

    @Override // androidx.camera.camera2.internal.j0, androidx.camera.core.impl.CaptureConfig.OptionUnpacker
    public void unpack(@NonNull UseCaseConfig<?> useCaseConfig, @NonNull CaptureConfig.Builder builder) {
        super.unpack(useCaseConfig, builder);
        if (useCaseConfig instanceof ImageCaptureConfig) {
            ImageCaptureConfig imageCaptureConfig = (ImageCaptureConfig) useCaseConfig;
            Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
            if (imageCaptureConfig.hasCaptureMode()) {
                this.b.toggleHDRPlus(imageCaptureConfig.getCaptureMode(), builder2);
            }
            builder.addImplementationOptions(builder2.build());
            return;
        }
        throw new IllegalArgumentException("config is not ImageCaptureConfig");
    }
}
