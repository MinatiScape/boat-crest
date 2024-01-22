package androidx.camera.core;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.camera.core.f0;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.x;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class f0 extends d0 {
    public final Executor f;
    public final Object g = new Object();
    @Nullable
    @GuardedBy("mLock")
    @VisibleForTesting
    public ImageProxy h;
    @Nullable
    @GuardedBy("mLock")
    public b i;

    /* loaded from: classes.dex */
    public class a implements FutureCallback<Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ b f685a;

        public a(f0 f0Var, b bVar) {
            this.f685a = bVar;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        /* renamed from: a */
        public void onSuccess(Void r1) {
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable th) {
            this.f685a.close();
        }
    }

    /* loaded from: classes.dex */
    public static class b extends x {
        public final WeakReference<f0> j;

        public b(ImageProxy imageProxy, f0 f0Var) {
            super(imageProxy);
            this.j = new WeakReference<>(f0Var);
            a(new x.a() { // from class: androidx.camera.core.g0
                @Override // androidx.camera.core.x.a
                public final void a(ImageProxy imageProxy2) {
                    f0.b.this.d(imageProxy2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void d(ImageProxy imageProxy) {
            final f0 f0Var = this.j.get();
            if (f0Var != null) {
                f0Var.f.execute(new Runnable() { // from class: androidx.camera.core.h0
                    @Override // java.lang.Runnable
                    public final void run() {
                        f0.this.m();
                    }
                });
            }
        }
    }

    public f0(Executor executor) {
        this.f = executor;
    }

    @Override // androidx.camera.core.d0
    @Nullable
    public ImageProxy c(@NonNull ImageReaderProxy imageReaderProxy) {
        return imageReaderProxy.acquireLatestImage();
    }

    @Override // androidx.camera.core.d0
    public void f() {
        synchronized (this.g) {
            ImageProxy imageProxy = this.h;
            if (imageProxy != null) {
                imageProxy.close();
                this.h = null;
            }
        }
    }

    @Override // androidx.camera.core.d0
    public void j(@NonNull ImageProxy imageProxy) {
        synchronized (this.g) {
            if (!this.e) {
                imageProxy.close();
            } else if (this.i != null) {
                if (imageProxy.getImageInfo().getTimestamp() <= this.i.getImageInfo().getTimestamp()) {
                    imageProxy.close();
                } else {
                    ImageProxy imageProxy2 = this.h;
                    if (imageProxy2 != null) {
                        imageProxy2.close();
                    }
                    this.h = imageProxy;
                }
            } else {
                b bVar = new b(imageProxy, this);
                this.i = bVar;
                Futures.addCallback(d(bVar), new a(this, bVar), CameraXExecutors.directExecutor());
            }
        }
    }

    public void m() {
        synchronized (this.g) {
            this.i = null;
            ImageProxy imageProxy = this.h;
            if (imageProxy != null) {
                this.h = null;
                j(imageProxy);
            }
        }
    }
}
