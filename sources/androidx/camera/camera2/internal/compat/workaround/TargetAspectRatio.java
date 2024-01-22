package androidx.camera.camera2.internal.compat.workaround;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.quirk.AspectRatioLegacyApi21Quirk;
import androidx.camera.camera2.internal.compat.quirk.CameraQuirks;
import androidx.camera.camera2.internal.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.internal.compat.quirk.Nexus4AndroidLTargetAspectRatioQuirk;
import androidx.camera.camera2.internal.compat.quirk.SamsungPreviewTargetAspectRatioQuirk;
import androidx.camera.core.impl.ImageOutputConfig;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public class TargetAspectRatio {
    public static final int RATIO_16_9 = 1;
    public static final int RATIO_4_3 = 0;
    public static final int RATIO_MAX_JPEG = 2;
    public static final int RATIO_ORIGINAL = 3;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface Ratio {
    }

    public int get(@NonNull ImageOutputConfig imageOutputConfig, @NonNull String str, @NonNull CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        SamsungPreviewTargetAspectRatioQuirk samsungPreviewTargetAspectRatioQuirk = (SamsungPreviewTargetAspectRatioQuirk) DeviceQuirks.get(SamsungPreviewTargetAspectRatioQuirk.class);
        if (samsungPreviewTargetAspectRatioQuirk == null || !samsungPreviewTargetAspectRatioQuirk.require16_9(imageOutputConfig)) {
            Nexus4AndroidLTargetAspectRatioQuirk nexus4AndroidLTargetAspectRatioQuirk = (Nexus4AndroidLTargetAspectRatioQuirk) DeviceQuirks.get(Nexus4AndroidLTargetAspectRatioQuirk.class);
            if (nexus4AndroidLTargetAspectRatioQuirk != null) {
                return nexus4AndroidLTargetAspectRatioQuirk.getCorrectedAspectRatio();
            }
            AspectRatioLegacyApi21Quirk aspectRatioLegacyApi21Quirk = (AspectRatioLegacyApi21Quirk) CameraQuirks.get(str, cameraCharacteristicsCompat).get(AspectRatioLegacyApi21Quirk.class);
            if (aspectRatioLegacyApi21Quirk != null) {
                return aspectRatioLegacyApi21Quirk.getCorrectedAspectRatio();
            }
            return 3;
        }
        return 1;
    }
}
