package androidx.camera.core.impl;

import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.camera.core.impl.utils.futures.Futures;
import com.google.common.util.concurrent.ListenableFuture;
/* loaded from: classes.dex */
public final class ImmediateSurface extends DeferrableSurface {
    public final Surface i;

    public ImmediateSurface(@NonNull Surface surface) {
        this.i = surface;
    }

    @Override // androidx.camera.core.impl.DeferrableSurface
    @NonNull
    public ListenableFuture<Surface> provideSurface() {
        return Futures.immediateFuture(this.i);
    }
}
