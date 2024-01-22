package androidx.camera.core;

import android.media.ImageReader;
import android.util.LongSparseArray;
import android.view.Surface;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.internal.CameraCaptureResultImageInfo;
import androidx.camera.core.x;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class n1 implements ImageReaderProxy, x.a {

    /* renamed from: a  reason: collision with root package name */
    public final Object f767a;
    public CameraCaptureCallback b;
    public ImageReaderProxy.OnImageAvailableListener c;
    @GuardedBy("mLock")
    public boolean d;
    @GuardedBy("mLock")
    public final ImageReaderProxy e;
    @Nullable
    @GuardedBy("mLock")
    public ImageReaderProxy.OnImageAvailableListener f;
    @Nullable
    @GuardedBy("mLock")
    public Executor g;
    @GuardedBy("mLock")
    public final LongSparseArray<ImageInfo> h;
    @GuardedBy("mLock")
    public final LongSparseArray<ImageProxy> i;
    @GuardedBy("mLock")
    public int j;
    @GuardedBy("mLock")
    public final List<ImageProxy> k;
    @GuardedBy("mLock")
    public final List<ImageProxy> l;

    /* loaded from: classes.dex */
    public class a extends CameraCaptureCallback {
        public a() {
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCompleted(@NonNull CameraCaptureResult cameraCaptureResult) {
            super.onCaptureCompleted(cameraCaptureResult);
            n1.this.m(cameraCaptureResult);
        }
    }

    public n1(int i, int i2, int i3, int i4) {
        this(d(i, i2, i3, i4));
    }

    public static ImageReaderProxy d(int i, int i2, int i3, int i4) {
        return new d(ImageReader.newInstance(i, i2, i3, i4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(ImageReaderProxy.OnImageAvailableListener onImageAvailableListener) {
        onImageAvailableListener.onImageAvailable(this);
    }

    @Override // androidx.camera.core.x.a
    public void a(ImageProxy imageProxy) {
        synchronized (this.f767a) {
            e(imageProxy);
        }
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    @Nullable
    public ImageProxy acquireLatestImage() {
        synchronized (this.f767a) {
            if (this.k.isEmpty()) {
                return null;
            }
            if (this.j < this.k.size()) {
                ArrayList<ImageProxy> arrayList = new ArrayList();
                for (int i = 0; i < this.k.size() - 1; i++) {
                    if (!this.l.contains(this.k.get(i))) {
                        arrayList.add(this.k.get(i));
                    }
                }
                for (ImageProxy imageProxy : arrayList) {
                    imageProxy.close();
                }
                int size = this.k.size() - 1;
                this.j = size;
                List<ImageProxy> list = this.k;
                this.j = size + 1;
                ImageProxy imageProxy2 = list.get(size);
                this.l.add(imageProxy2);
                return imageProxy2;
            }
            throw new IllegalStateException("Maximum image number reached.");
        }
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    @Nullable
    public ImageProxy acquireNextImage() {
        synchronized (this.f767a) {
            if (this.k.isEmpty()) {
                return null;
            }
            if (this.j < this.k.size()) {
                List<ImageProxy> list = this.k;
                int i = this.j;
                this.j = i + 1;
                ImageProxy imageProxy = list.get(i);
                this.l.add(imageProxy);
                return imageProxy;
            }
            throw new IllegalStateException("Maximum image number reached.");
        }
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void clearOnImageAvailableListener() {
        synchronized (this.f767a) {
            this.f = null;
            this.g = null;
        }
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void close() {
        synchronized (this.f767a) {
            if (this.d) {
                return;
            }
            for (ImageProxy imageProxy : new ArrayList(this.k)) {
                imageProxy.close();
            }
            this.k.clear();
            this.e.close();
            this.d = true;
        }
    }

    public final void e(ImageProxy imageProxy) {
        synchronized (this.f767a) {
            int indexOf = this.k.indexOf(imageProxy);
            if (indexOf >= 0) {
                this.k.remove(indexOf);
                int i = this.j;
                if (indexOf <= i) {
                    this.j = i - 1;
                }
            }
            this.l.remove(imageProxy);
        }
    }

    public final void f(a2 a2Var) {
        final ImageReaderProxy.OnImageAvailableListener onImageAvailableListener;
        Executor executor;
        synchronized (this.f767a) {
            onImageAvailableListener = null;
            if (this.k.size() < getMaxImages()) {
                a2Var.a(this);
                this.k.add(a2Var);
                onImageAvailableListener = this.f;
                executor = this.g;
            } else {
                Logger.d("TAG", "Maximum image number reached.");
                a2Var.close();
                executor = null;
            }
        }
        if (onImageAvailableListener != null) {
            if (executor != null) {
                executor.execute(new Runnable() { // from class: androidx.camera.core.m1
                    @Override // java.lang.Runnable
                    public final void run() {
                        n1.this.i(onImageAvailableListener);
                    }
                });
            } else {
                onImageAvailableListener.onImageAvailable(this);
            }
        }
    }

    public CameraCaptureCallback g() {
        return this.b;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getHeight() {
        int height;
        synchronized (this.f767a) {
            height = this.e.getHeight();
        }
        return height;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getImageFormat() {
        int imageFormat;
        synchronized (this.f767a) {
            imageFormat = this.e.getImageFormat();
        }
        return imageFormat;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getMaxImages() {
        int maxImages;
        synchronized (this.f767a) {
            maxImages = this.e.getMaxImages();
        }
        return maxImages;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    @Nullable
    public Surface getSurface() {
        Surface surface;
        synchronized (this.f767a) {
            surface = this.e.getSurface();
        }
        return surface;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getWidth() {
        int width;
        synchronized (this.f767a) {
            width = this.e.getWidth();
        }
        return width;
    }

    /* renamed from: h */
    public void j(ImageReaderProxy imageReaderProxy) {
        synchronized (this.f767a) {
            if (this.d) {
                return;
            }
            int i = 0;
            do {
                ImageProxy imageProxy = null;
                try {
                    imageProxy = imageReaderProxy.acquireNextImage();
                    if (imageProxy != null) {
                        i++;
                        this.i.put(imageProxy.getImageInfo().getTimestamp(), imageProxy);
                        k();
                    }
                } catch (IllegalStateException e) {
                    Logger.d("MetadataImageReader", "Failed to acquire next image.", e);
                }
                if (imageProxy == null) {
                    break;
                }
            } while (i < imageReaderProxy.getMaxImages());
        }
    }

    public final void k() {
        synchronized (this.f767a) {
            for (int size = this.h.size() - 1; size >= 0; size--) {
                ImageInfo valueAt = this.h.valueAt(size);
                long timestamp = valueAt.getTimestamp();
                ImageProxy imageProxy = this.i.get(timestamp);
                if (imageProxy != null) {
                    this.i.remove(timestamp);
                    this.h.removeAt(size);
                    f(new a2(imageProxy, valueAt));
                }
            }
            l();
        }
    }

    public final void l() {
        synchronized (this.f767a) {
            if (this.i.size() != 0 && this.h.size() != 0) {
                Long valueOf = Long.valueOf(this.i.keyAt(0));
                Long valueOf2 = Long.valueOf(this.h.keyAt(0));
                Preconditions.checkArgument(valueOf2.equals(valueOf) ? false : true);
                if (valueOf2.longValue() > valueOf.longValue()) {
                    for (int size = this.i.size() - 1; size >= 0; size--) {
                        if (this.i.keyAt(size) < valueOf2.longValue()) {
                            this.i.valueAt(size).close();
                            this.i.removeAt(size);
                        }
                    }
                } else {
                    for (int size2 = this.h.size() - 1; size2 >= 0; size2--) {
                        if (this.h.keyAt(size2) < valueOf.longValue()) {
                            this.h.removeAt(size2);
                        }
                    }
                }
            }
        }
    }

    public void m(CameraCaptureResult cameraCaptureResult) {
        synchronized (this.f767a) {
            if (this.d) {
                return;
            }
            this.h.put(cameraCaptureResult.getTimestamp(), new CameraCaptureResultImageInfo(cameraCaptureResult));
            k();
        }
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void setOnImageAvailableListener(@NonNull ImageReaderProxy.OnImageAvailableListener onImageAvailableListener, @NonNull Executor executor) {
        synchronized (this.f767a) {
            this.f = (ImageReaderProxy.OnImageAvailableListener) Preconditions.checkNotNull(onImageAvailableListener);
            this.g = (Executor) Preconditions.checkNotNull(executor);
            this.e.setOnImageAvailableListener(this.c, executor);
        }
    }

    public n1(@NonNull ImageReaderProxy imageReaderProxy) {
        this.f767a = new Object();
        this.b = new a();
        this.c = new ImageReaderProxy.OnImageAvailableListener() { // from class: androidx.camera.core.l1
            @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
            public final void onImageAvailable(ImageReaderProxy imageReaderProxy2) {
                n1.this.j(imageReaderProxy2);
            }
        };
        this.d = false;
        this.h = new LongSparseArray<>();
        this.i = new LongSparseArray<>();
        this.l = new ArrayList();
        this.e = imageReaderProxy;
        this.j = 0;
        this.k = new ArrayList(getMaxImages());
    }
}
