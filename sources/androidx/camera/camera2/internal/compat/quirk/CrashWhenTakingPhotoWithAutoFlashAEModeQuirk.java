package androidx.camera.camera2.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.impl.Quirk;
/* loaded from: classes.dex */
public class CrashWhenTakingPhotoWithAutoFlashAEModeQuirk implements Quirk {
    public static boolean a() {
        return "SAMSUNG".equals(Build.MANUFACTURER.toUpperCase()) && Build.MODEL.toUpperCase().startsWith("SM-A300");
    }
}
