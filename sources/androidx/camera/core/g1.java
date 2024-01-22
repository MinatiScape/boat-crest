package androidx.camera.core;

import android.media.ImageReader;
import androidx.annotation.NonNull;
import androidx.camera.core.impl.ImageReaderProxy;
/* loaded from: classes.dex */
public final class g1 {
    @NonNull
    public static ImageReaderProxy a(int i, int i2, int i3, int i4) {
        return new d(ImageReader.newInstance(i, i2, i3, i4));
    }
}
