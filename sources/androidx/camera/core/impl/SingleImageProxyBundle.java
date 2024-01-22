package androidx.camera.core.impl;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageInfo;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.utils.futures.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public final class SingleImageProxyBundle implements ImageProxyBundle {

    /* renamed from: a  reason: collision with root package name */
    public final int f714a;
    public final ImageProxy b;

    public SingleImageProxyBundle(@NonNull ImageProxy imageProxy, @NonNull String str) {
        ImageInfo imageInfo = imageProxy.getImageInfo();
        if (imageInfo != null) {
            Integer tag = imageInfo.getTagBundle().getTag(str);
            if (tag != null) {
                this.f714a = tag.intValue();
                this.b = imageProxy;
                return;
            }
            throw new IllegalArgumentException("ImageProxy has no associated tag");
        }
        throw new IllegalArgumentException("ImageProxy has no associated ImageInfo");
    }

    public void close() {
        this.b.close();
    }

    @Override // androidx.camera.core.impl.ImageProxyBundle
    @NonNull
    public List<Integer> getCaptureIds() {
        return Collections.singletonList(Integer.valueOf(this.f714a));
    }

    @Override // androidx.camera.core.impl.ImageProxyBundle
    @NonNull
    public ListenableFuture<ImageProxy> getImageProxy(int i) {
        if (i != this.f714a) {
            return Futures.immediateFailedFuture(new IllegalArgumentException("Capture id does not exist in the bundle"));
        }
        return Futures.immediateFuture(this.b);
    }
}
