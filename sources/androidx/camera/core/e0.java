package androidx.camera.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
/* loaded from: classes.dex */
public final class e0 extends d0 {

    /* loaded from: classes.dex */
    public class a implements FutureCallback<Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ImageProxy f682a;

        public a(e0 e0Var, ImageProxy imageProxy) {
            this.f682a = imageProxy;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        /* renamed from: a */
        public void onSuccess(Void r1) {
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable th) {
            this.f682a.close();
        }
    }

    @Override // androidx.camera.core.d0
    @Nullable
    public ImageProxy c(@NonNull ImageReaderProxy imageReaderProxy) {
        return imageReaderProxy.acquireNextImage();
    }

    @Override // androidx.camera.core.d0
    public void f() {
    }

    @Override // androidx.camera.core.d0
    public void j(@NonNull ImageProxy imageProxy) {
        Futures.addCallback(d(imageProxy), new a(this, imageProxy), CameraXExecutors.directExecutor());
    }
}
