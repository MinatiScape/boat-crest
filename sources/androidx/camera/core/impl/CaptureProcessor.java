package androidx.camera.core.impl;

import android.util.Size;
import android.view.Surface;
/* loaded from: classes.dex */
public interface CaptureProcessor {
    void onOutputSurface(Surface surface, int i);

    void onResolutionUpdate(Size size);

    void process(ImageProxyBundle imageProxyBundle);
}
