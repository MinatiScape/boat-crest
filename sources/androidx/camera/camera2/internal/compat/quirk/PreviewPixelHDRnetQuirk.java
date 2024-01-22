package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.impl.Quirk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public class PreviewPixelHDRnetQuirk implements Quirk {

    /* renamed from: a  reason: collision with root package name */
    public static final List<String> f546a = new ArrayList(Arrays.asList("sunfish", "bramble", "redfin"));

    public static boolean a() {
        return "Google".equals(Build.MANUFACTURER) && f546a.contains(Build.DEVICE.toLowerCase(Locale.getDefault()));
    }
}
