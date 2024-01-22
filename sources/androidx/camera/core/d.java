package androidx.camera.core;

import android.media.Image;
import android.media.ImageReader;
import android.view.Surface;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.utils.MainThreadAsyncHandler;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class d implements ImageReaderProxy {
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    public final ImageReader f677a;

    public d(ImageReader imageReader) {
        this.f677a = imageReader;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(ImageReaderProxy.OnImageAvailableListener onImageAvailableListener) {
        onImageAvailableListener.onImageAvailable(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(Executor executor, final ImageReaderProxy.OnImageAvailableListener onImageAvailableListener, ImageReader imageReader) {
        executor.execute(new Runnable() { // from class: androidx.camera.core.c
            @Override // java.lang.Runnable
            public final void run() {
                d.this.d(onImageAvailableListener);
            }
        });
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    @Nullable
    public synchronized ImageProxy acquireLatestImage() {
        Image image;
        try {
            image = this.f677a.acquireLatestImage();
        } catch (RuntimeException e) {
            if (!c(e)) {
                throw e;
            }
            image = null;
        }
        if (image == null) {
            return null;
        }
        return new a(image);
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    @Nullable
    public synchronized ImageProxy acquireNextImage() {
        Image image;
        try {
            image = this.f677a.acquireNextImage();
        } catch (RuntimeException e) {
            if (!c(e)) {
                throw e;
            }
            image = null;
        }
        if (image == null) {
            return null;
        }
        return new a(image);
    }

    public final boolean c(RuntimeException runtimeException) {
        return "ImageReaderContext is not initialized".equals(runtimeException.getMessage());
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public synchronized void clearOnImageAvailableListener() {
        this.f677a.setOnImageAvailableListener(null, null);
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public synchronized void close() {
        this.f677a.close();
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public synchronized int getHeight() {
        return this.f677a.getHeight();
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public synchronized int getImageFormat() {
        return this.f677a.getImageFormat();
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public synchronized int getMaxImages() {
        return this.f677a.getMaxImages();
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    @Nullable
    public synchronized Surface getSurface() {
        return this.f677a.getSurface();
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public synchronized int getWidth() {
        return this.f677a.getWidth();
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public synchronized void setOnImageAvailableListener(@NonNull final ImageReaderProxy.OnImageAvailableListener onImageAvailableListener, @NonNull final Executor executor) {
        this.f677a.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() { // from class: androidx.camera.core.b
            @Override // android.media.ImageReader.OnImageAvailableListener
            public final void onImageAvailable(ImageReader imageReader) {
                d.this.e(executor, onImageAvailableListener, imageReader);
            }
        }, MainThreadAsyncHandler.getInstance());
    }
}
