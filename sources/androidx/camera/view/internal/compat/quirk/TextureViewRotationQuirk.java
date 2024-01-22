package androidx.camera.view.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.impl.Quirk;
/* loaded from: classes.dex */
public class TextureViewRotationQuirk implements Quirk {
    public static boolean a() {
        return "Fairphone".equalsIgnoreCase(Build.MANUFACTURER) && "FP2".equalsIgnoreCase(Build.MODEL);
    }

    public static boolean b() {
        return a();
    }

    public int getCorrectionRotation(boolean z) {
        return (a() && z) ? 180 : 0;
    }
}
