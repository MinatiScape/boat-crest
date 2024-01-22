package androidx.camera.camera2.internal.compat.quirk;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.core.internal.compat.quirk.SoftwareJpegEncodingPreferredQuirk;
import androidx.core.util.Preconditions;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes.dex */
public final class JpegHalCorruptImageQuirk implements SoftwareJpegEncodingPreferredQuirk {

    /* renamed from: a  reason: collision with root package name */
    public static final Set<String> f544a = new HashSet(Arrays.asList("heroqltevzw", "heroqltetmo"));
    public static final Set<Integer> b;

    static {
        HashSet hashSet = new HashSet();
        b = hashSet;
        hashSet.add(1);
        if (Build.VERSION.SDK_INT >= 24) {
            hashSet.add(3);
        }
    }

    public static boolean a(@NonNull CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        return f544a.contains(Build.DEVICE.toLowerCase(Locale.US)) && b.contains(Integer.valueOf(((Integer) Preconditions.checkNotNull((Integer) cameraCharacteristicsCompat.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL))).intValue()));
    }
}
