package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.impl.Quirk;
/* loaded from: classes.dex */
public class ImageCapturePixelHDRPlusQuirk implements Quirk {
    public static boolean a() {
        return "Google".equals(Build.MANUFACTURER) && "Pixel 2".equals(Build.MODEL);
    }

    public static boolean b() {
        return "Google".equals(Build.MANUFACTURER) && "Pixel 3".equals(Build.MODEL);
    }

    public static boolean c() {
        return (a() || b()) && Build.VERSION.SDK_INT >= 26;
    }
}
