package androidx.camera.core.internal.compat;

import android.media.ImageWriter;
import android.os.Build;
import android.view.Surface;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
@RequiresApi(26)
/* loaded from: classes.dex */
public final class ImageWriterCompat {
    @NonNull
    public static ImageWriter newInstance(@NonNull Surface surface, @IntRange(from = 1) int i, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 26) {
            return a.a(surface, i, i2);
        }
        if (i3 >= 29) {
            return b.a(surface, i, i2);
        }
        throw new RuntimeException("Unable to call newInstance(Surface, int, int) on API " + i3 + ". Version 26 or higher required.");
    }
}
