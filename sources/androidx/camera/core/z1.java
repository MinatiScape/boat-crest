package androidx.camera.core;

import android.view.Surface;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.x;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class z1 implements ImageReaderProxy {
    @GuardedBy("mLock")
    public final ImageReaderProxy d;
    @Nullable
    public final Surface e;

    /* renamed from: a  reason: collision with root package name */
    public final Object f793a = new Object();
    @GuardedBy("mLock")
    public volatile int b = 0;
    @GuardedBy("mLock")
    public volatile boolean c = false;
    public x.a f = new x.a() { // from class: androidx.camera.core.x1
        @Override // androidx.camera.core.x.a
        public final void a(ImageProxy imageProxy) {
            z1.this.c(imageProxy);
        }
    };

    public z1(@NonNull ImageReaderProxy imageReaderProxy) {
        this.d = imageReaderProxy;
        this.e = imageReaderProxy.getSurface();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(ImageProxy imageProxy) {
        synchronized (this.f793a) {
            this.b--;
            if (this.c && this.b == 0) {
                close();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(ImageReaderProxy.OnImageAvailableListener onImageAvailableListener, ImageReaderProxy imageReaderProxy) {
        onImageAvailableListener.onImageAvailable(this);
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    @Nullable
    public ImageProxy acquireLatestImage() {
        ImageProxy f;
        synchronized (this.f793a) {
            f = f(this.d.acquireLatestImage());
        }
        return f;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    @Nullable
    public ImageProxy acquireNextImage() {
        ImageProxy f;
        synchronized (this.f793a) {
            f = f(this.d.acquireNextImage());
        }
        return f;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void clearOnImageAvailableListener() {
        synchronized (this.f793a) {
            this.d.clearOnImageAvailableListener();
        }
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void close() {
        synchronized (this.f793a) {
            Surface surface = this.e;
            if (surface != null) {
                surface.release();
            }
            this.d.close();
        }
    }

    @GuardedBy("mLock")
    public void e() {
        synchronized (this.f793a) {
            this.c = true;
            this.d.clearOnImageAvailableListener();
            if (this.b == 0) {
                close();
            }
        }
    }

    @Nullable
    @GuardedBy("mLock")
    public final ImageProxy f(@Nullable ImageProxy imageProxy) {
        synchronized (this.f793a) {
            if (imageProxy != null) {
                this.b++;
                c2 c2Var = new c2(imageProxy);
                c2Var.a(this.f);
                return c2Var;
            }
            return null;
        }
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getHeight() {
        int height;
        synchronized (this.f793a) {
            height = this.d.getHeight();
        }
        return height;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getImageFormat() {
        int imageFormat;
        synchronized (this.f793a) {
            imageFormat = this.d.getImageFormat();
        }
        return imageFormat;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getMaxImages() {
        int maxImages;
        synchronized (this.f793a) {
            maxImages = this.d.getMaxImages();
        }
        return maxImages;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    @Nullable
    public Surface getSurface() {
        Surface surface;
        synchronized (this.f793a) {
            surface = this.d.getSurface();
        }
        return surface;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getWidth() {
        int width;
        synchronized (this.f793a) {
            width = this.d.getWidth();
        }
        return width;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void setOnImageAvailableListener(@NonNull final ImageReaderProxy.OnImageAvailableListener onImageAvailableListener, @NonNull Executor executor) {
        synchronized (this.f793a) {
            this.d.setOnImageAvailableListener(new ImageReaderProxy.OnImageAvailableListener() { // from class: androidx.camera.core.y1
                @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
                public final void onImageAvailable(ImageReaderProxy imageReaderProxy) {
                    z1.this.d(onImageAvailableListener, imageReaderProxy);
                }
            }, executor);
        }
    }
}
