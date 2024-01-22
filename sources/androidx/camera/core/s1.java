package androidx.camera.core;

import android.media.ImageReader;
import android.util.Size;
import android.view.Surface;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CaptureBundle;
import androidx.camera.core.impl.CaptureProcessor;
import androidx.camera.core.impl.CaptureStage;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.s1;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class s1 implements ImageReaderProxy {

    /* renamed from: a  reason: collision with root package name */
    public final Object f775a;
    public ImageReaderProxy.OnImageAvailableListener b;
    public ImageReaderProxy.OnImageAvailableListener c;
    public FutureCallback<List<ImageProxy>> d;
    @GuardedBy("mLock")
    public boolean e;
    @GuardedBy("mLock")
    public boolean f;
    @GuardedBy("mLock")
    public final n1 g;
    @GuardedBy("mLock")
    public final ImageReaderProxy h;
    @Nullable
    @GuardedBy("mLock")
    public ImageReaderProxy.OnImageAvailableListener i;
    @Nullable
    @GuardedBy("mLock")
    public Executor j;
    @GuardedBy("mLock")
    public CallbackToFutureAdapter.Completer<Void> k;
    @GuardedBy("mLock")
    public ListenableFuture<Void> l;
    @NonNull
    public final Executor m;
    @NonNull
    public final CaptureProcessor n;
    public String o;
    @NonNull
    @GuardedBy("mLock")
    public b2 p;
    public final List<Integer> q;

    /* loaded from: classes.dex */
    public class a implements ImageReaderProxy.OnImageAvailableListener {
        public a() {
        }

        @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
        public void onImageAvailable(@NonNull ImageReaderProxy imageReaderProxy) {
            s1.this.e(imageReaderProxy);
        }
    }

    /* loaded from: classes.dex */
    public class b implements ImageReaderProxy.OnImageAvailableListener {
        public b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b(ImageReaderProxy.OnImageAvailableListener onImageAvailableListener) {
            onImageAvailableListener.onImageAvailable(s1.this);
        }

        @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
        public void onImageAvailable(@NonNull ImageReaderProxy imageReaderProxy) {
            final ImageReaderProxy.OnImageAvailableListener onImageAvailableListener;
            Executor executor;
            synchronized (s1.this.f775a) {
                s1 s1Var = s1.this;
                onImageAvailableListener = s1Var.i;
                executor = s1Var.j;
                s1Var.p.c();
                s1.this.h();
            }
            if (onImageAvailableListener != null) {
                if (executor != null) {
                    executor.execute(new Runnable() { // from class: androidx.camera.core.t1
                        @Override // java.lang.Runnable
                        public final void run() {
                            s1.b.this.b(onImageAvailableListener);
                        }
                    });
                } else {
                    onImageAvailableListener.onImageAvailable(s1.this);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class c implements FutureCallback<List<ImageProxy>> {
        public c() {
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        /* renamed from: a */
        public void onSuccess(@Nullable List<ImageProxy> list) {
            synchronized (s1.this.f775a) {
                s1 s1Var = s1.this;
                if (s1Var.e) {
                    return;
                }
                s1Var.f = true;
                s1Var.n.process(s1Var.p);
                synchronized (s1.this.f775a) {
                    s1 s1Var2 = s1.this;
                    s1Var2.f = false;
                    if (s1Var2.e) {
                        s1Var2.g.close();
                        s1.this.p.b();
                        s1.this.h.close();
                        CallbackToFutureAdapter.Completer<Void> completer = s1.this.k;
                        if (completer != null) {
                            completer.set(null);
                        }
                    }
                }
            }
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable th) {
        }
    }

    public s1(int i, int i2, int i3, int i4, @NonNull Executor executor, @NonNull CaptureBundle captureBundle, @NonNull CaptureProcessor captureProcessor, int i5) {
        this(new n1(i, i2, i3, i4), executor, captureBundle, captureProcessor, i5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object f(CallbackToFutureAdapter.Completer completer) throws Exception {
        synchronized (this.f775a) {
            this.k = completer;
        }
        return "ProcessingImageReader-close";
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    @Nullable
    public ImageProxy acquireLatestImage() {
        ImageProxy acquireLatestImage;
        synchronized (this.f775a) {
            acquireLatestImage = this.h.acquireLatestImage();
        }
        return acquireLatestImage;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    @Nullable
    public ImageProxy acquireNextImage() {
        ImageProxy acquireNextImage;
        synchronized (this.f775a) {
            acquireNextImage = this.h.acquireNextImage();
        }
        return acquireNextImage;
    }

    @Nullable
    public CameraCaptureCallback b() {
        CameraCaptureCallback g;
        synchronized (this.f775a) {
            g = this.g.g();
        }
        return g;
    }

    @NonNull
    public ListenableFuture<Void> c() {
        ListenableFuture<Void> nonCancellationPropagating;
        synchronized (this.f775a) {
            if (this.e && !this.f) {
                nonCancellationPropagating = Futures.immediateFuture(null);
            } else {
                if (this.l == null) {
                    this.l = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.r1
                        @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                        public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                            Object f;
                            f = s1.this.f(completer);
                            return f;
                        }
                    });
                }
                nonCancellationPropagating = Futures.nonCancellationPropagating(this.l);
            }
        }
        return nonCancellationPropagating;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void clearOnImageAvailableListener() {
        synchronized (this.f775a) {
            this.i = null;
            this.j = null;
            this.g.clearOnImageAvailableListener();
            this.h.clearOnImageAvailableListener();
            if (!this.f) {
                this.p.b();
            }
        }
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void close() {
        synchronized (this.f775a) {
            if (this.e) {
                return;
            }
            this.h.clearOnImageAvailableListener();
            if (!this.f) {
                this.g.close();
                this.p.b();
                this.h.close();
                CallbackToFutureAdapter.Completer<Void> completer = this.k;
                if (completer != null) {
                    completer.set(null);
                }
            }
            this.e = true;
        }
    }

    @NonNull
    public String d() {
        return this.o;
    }

    public void e(ImageReaderProxy imageReaderProxy) {
        synchronized (this.f775a) {
            if (this.e) {
                return;
            }
            try {
                ImageProxy acquireNextImage = imageReaderProxy.acquireNextImage();
                if (acquireNextImage != null) {
                    Integer tag = acquireNextImage.getImageInfo().getTagBundle().getTag(this.o);
                    if (!this.q.contains(tag)) {
                        Logger.w("ProcessingImageReader", "ImageProxyBundle does not contain this id: " + tag);
                        acquireNextImage.close();
                    } else {
                        this.p.a(acquireNextImage);
                    }
                }
            } catch (IllegalStateException e) {
                Logger.e("ProcessingImageReader", "Failed to acquire latest image.", e);
            }
        }
    }

    public void g(@NonNull CaptureBundle captureBundle) {
        synchronized (this.f775a) {
            if (captureBundle.getCaptureStages() != null) {
                if (this.g.getMaxImages() >= captureBundle.getCaptureStages().size()) {
                    this.q.clear();
                    for (CaptureStage captureStage : captureBundle.getCaptureStages()) {
                        if (captureStage != null) {
                            this.q.add(Integer.valueOf(captureStage.getId()));
                        }
                    }
                } else {
                    throw new IllegalArgumentException("CaptureBundle is larger than InputImageReader.");
                }
            }
            String num = Integer.toString(captureBundle.hashCode());
            this.o = num;
            this.p = new b2(this.q, num);
            h();
        }
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getHeight() {
        int height;
        synchronized (this.f775a) {
            height = this.g.getHeight();
        }
        return height;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getImageFormat() {
        int imageFormat;
        synchronized (this.f775a) {
            imageFormat = this.h.getImageFormat();
        }
        return imageFormat;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getMaxImages() {
        int maxImages;
        synchronized (this.f775a) {
            maxImages = this.g.getMaxImages();
        }
        return maxImages;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    @Nullable
    public Surface getSurface() {
        Surface surface;
        synchronized (this.f775a) {
            surface = this.g.getSurface();
        }
        return surface;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getWidth() {
        int width;
        synchronized (this.f775a) {
            width = this.g.getWidth();
        }
        return width;
    }

    @GuardedBy("mLock")
    public void h() {
        ArrayList arrayList = new ArrayList();
        for (Integer num : this.q) {
            arrayList.add(this.p.getImageProxy(num.intValue()));
        }
        Futures.addCallback(Futures.allAsList(arrayList), this.d, this.m);
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void setOnImageAvailableListener(@NonNull ImageReaderProxy.OnImageAvailableListener onImageAvailableListener, @NonNull Executor executor) {
        synchronized (this.f775a) {
            this.i = (ImageReaderProxy.OnImageAvailableListener) Preconditions.checkNotNull(onImageAvailableListener);
            this.j = (Executor) Preconditions.checkNotNull(executor);
            this.g.setOnImageAvailableListener(this.b, executor);
            this.h.setOnImageAvailableListener(this.c, executor);
        }
    }

    public s1(@NonNull n1 n1Var, @NonNull Executor executor, @NonNull CaptureBundle captureBundle, @NonNull CaptureProcessor captureProcessor, int i) {
        this.f775a = new Object();
        this.b = new a();
        this.c = new b();
        this.d = new c();
        this.e = false;
        this.f = false;
        this.o = new String();
        this.p = new b2(Collections.emptyList(), this.o);
        this.q = new ArrayList();
        if (n1Var.getMaxImages() >= captureBundle.getCaptureStages().size()) {
            this.g = n1Var;
            int width = n1Var.getWidth();
            int height = n1Var.getHeight();
            if (i == 256) {
                width = n1Var.getWidth() * n1Var.getHeight();
                height = 1;
            }
            d dVar = new d(ImageReader.newInstance(width, height, i, n1Var.getMaxImages()));
            this.h = dVar;
            this.m = executor;
            this.n = captureProcessor;
            captureProcessor.onOutputSurface(dVar.getSurface(), i);
            captureProcessor.onResolutionUpdate(new Size(n1Var.getWidth(), n1Var.getHeight()));
            g(captureBundle);
            return;
        }
        throw new IllegalArgumentException("MetadataImageReader is smaller than CaptureBundle.");
    }
}
