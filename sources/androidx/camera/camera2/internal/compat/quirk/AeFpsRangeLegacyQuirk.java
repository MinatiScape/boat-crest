package androidx.camera.camera2.internal.compat.quirk;

import android.hardware.camera2.CameraCharacteristics;
import android.util.Range;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.core.impl.Quirk;
/* loaded from: classes.dex */
public class AeFpsRangeLegacyQuirk implements Quirk {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Range<Integer> f542a;

    public AeFpsRangeLegacyQuirk(@NonNull CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        this.f542a = c((Range[]) cameraCharacteristicsCompat.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES));
    }

    public static boolean b(@NonNull CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        Integer num = (Integer) cameraCharacteristicsCompat.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        return num != null && num.intValue() == 2;
    }

    @NonNull
    public final Range<Integer> a(@NonNull Range<Integer> range) {
        int intValue = range.getUpper().intValue();
        int intValue2 = range.getLower().intValue();
        if (range.getUpper().intValue() >= 1000) {
            intValue = range.getUpper().intValue() / 1000;
        }
        if (range.getLower().intValue() >= 1000) {
            intValue2 = range.getLower().intValue() / 1000;
        }
        return new Range<>(Integer.valueOf(intValue2), Integer.valueOf(intValue));
    }

    @Nullable
    public final Range<Integer> c(@Nullable Range<Integer>[] rangeArr) {
        Range<Integer> range = null;
        if (rangeArr != null && rangeArr.length != 0) {
            for (Range<Integer> range2 : rangeArr) {
                Range<Integer> a2 = a(range2);
                if (a2.getUpper().intValue() == 30 && (range == null || a2.getLower().intValue() < range.getLower().intValue())) {
                    range = a2;
                }
            }
        }
        return range;
    }

    @Nullable
    public Range<Integer> getRange() {
        return this.f542a;
    }
}
