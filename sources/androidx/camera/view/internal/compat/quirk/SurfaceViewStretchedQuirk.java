package androidx.camera.view.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.impl.Quirk;
/* loaded from: classes.dex */
public class SurfaceViewStretchedQuirk implements Quirk {
    public static boolean a() {
        return "SAMSUNG".equals(Build.MANUFACTURER.toUpperCase()) && "F2Q".equals(Build.DEVICE.toUpperCase());
    }
}
