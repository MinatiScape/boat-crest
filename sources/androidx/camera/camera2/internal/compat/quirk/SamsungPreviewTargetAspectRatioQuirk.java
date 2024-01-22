package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.PreviewConfig;
import androidx.camera.core.impl.Quirk;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public class SamsungPreviewTargetAspectRatioQuirk implements Quirk {

    /* renamed from: a  reason: collision with root package name */
    public static final List<String> f547a = Arrays.asList("SM-J710MN", "SM-T580");

    public static boolean a() {
        return "SAMSUNG".equals(Build.BRAND.toUpperCase()) && f547a.contains(Build.MODEL.toUpperCase());
    }

    public boolean require16_9(@NonNull Config config) {
        return config instanceof PreviewConfig;
    }
}
