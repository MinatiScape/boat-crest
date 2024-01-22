package androidx.camera.view.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.impl.Quirk;
/* loaded from: classes.dex */
public class PreviewOneThirdWiderQuirk implements Quirk {
    public static boolean a() {
        String str = Build.DEVICE;
        return ("ON5XELTE".equals(str.toUpperCase()) && Build.VERSION.SDK_INT >= 26) || "A3Y17LTE".equals(str.toUpperCase());
    }

    public float getCropRectScaleX() {
        return 0.75f;
    }
}
