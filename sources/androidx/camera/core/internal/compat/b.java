package androidx.camera.core.internal.compat;

import android.media.ImageWriter;
import android.view.Surface;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
@RequiresApi(29)
/* loaded from: classes.dex */
public final class b {
    @NonNull
    public static ImageWriter a(@NonNull Surface surface, @IntRange(from = 1) int i, int i2) {
        return ImageWriter.newInstance(surface, i, i2);
    }
}
