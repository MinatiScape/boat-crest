package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.impl.Quirk;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public class Nexus4AndroidLTargetAspectRatioQuirk implements Quirk {

    /* renamed from: a  reason: collision with root package name */
    public static final List<String> f545a = Arrays.asList("NEXUS 4");

    public static boolean a() {
        return "GOOGLE".equals(Build.BRAND.toUpperCase()) && Build.VERSION.SDK_INT < 23 && f545a.contains(Build.MODEL.toUpperCase());
    }

    public int getCorrectedAspectRatio() {
        return 2;
    }
}
