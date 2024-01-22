package androidx.camera.core;

import android.os.Handler;
import android.os.Looper;
import android.util.Size;
import android.view.Surface;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CaptureProcessor;
import androidx.camera.core.impl.CaptureStage;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.SingleImageProxyBundle;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ScheduledExecutorService;
/* loaded from: classes.dex */
public final class w1 extends DeferrableSurface {
    public final Object i = new Object();
    public final ImageReaderProxy.OnImageAvailableListener j;
    @GuardedBy("mLock")
    public boolean k;
    @NonNull
    public final Size l;
    @GuardedBy("mLock")
    public final n1 m;
    @GuardedBy("mLock")
    public final Surface n;
    public final Handler o;
    public final CaptureStage p;
    @NonNull
    @GuardedBy("mLock")
    public final CaptureProcessor q;
    public final CameraCaptureCallback r;
    public final DeferrableSurface s;
    public String t;

    /* loaded from: classes.dex */
    public class a implements FutureCallback<Surface> {
        public a() {
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        /* renamed from: a */
        public void onSuccess(@Nullable Surface surface) {
            synchronized (w1.this.i) {
                w1.this.q.onOutputSurface(surface, 1);
            }
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable th) {
            Logger.e("ProcessingSurfaceTextur", "Failed to extract Listenable<Surface>.", th);
        }
    }

    public w1(int i, int i2, int i3, @Nullable Handler handler, @NonNull CaptureStage captureStage, @NonNull CaptureProcessor captureProcessor, @NonNull DeferrableSurface deferrableSurface, @NonNull String str) {
        ImageReaderProxy.OnImageAvailableListener onImageAvailableListener = new ImageReaderProxy.OnImageAvailableListener() { // from class: androidx.camera.core.u1
            @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
            public final void onImageAvailable(ImageReaderProxy imageReaderProxy) {
                w1.this.j(imageReaderProxy);
            }
        };
        this.j = onImageAvailableListener;
        this.k = false;
        Size size = new Size(i, i2);
        this.l = size;
        if (handler != null) {
            this.o = handler;
        } else {
            Looper myLooper = Looper.myLooper();
            if (myLooper != null) {
                this.o = new Handler(myLooper);
            } else {
                throw new IllegalStateException("Creating a ProcessingSurface requires a non-null Handler, or be created  on a thread with a Looper.");
            }
        }
        ScheduledExecutorService newHandlerExecutor = CameraXExecutors.newHandlerExecutor(this.o);
        n1 n1Var = new n1(i, i2, i3, 2);
        this.m = n1Var;
        n1Var.setOnImageAvailableListener(onImageAvailableListener, newHandlerExecutor);
        this.n = n1Var.getSurface();
        this.r = n1Var.g();
        this.q = captureProcessor;
        captureProcessor.onResolutionUpdate(size);
        this.p = captureStage;
        this.s = deferrableSurface;
        this.t = str;
        Futures.addCallback(deferrableSurface.getSurface(), new a(), CameraXExecutors.directExecutor());
        getTerminationFuture().addListener(new Runnable() { // from class: androidx.camera.core.v1
            @Override // java.lang.Runnable
            public final void run() {
                w1.this.k();
            }
        }, CameraXExecutors.directExecutor());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(ImageReaderProxy imageReaderProxy) {
        synchronized (this.i) {
            i(imageReaderProxy);
        }
    }

    @Nullable
    public CameraCaptureCallback h() {
        CameraCaptureCallback cameraCaptureCallback;
        synchronized (this.i) {
            if (!this.k) {
                cameraCaptureCallback = this.r;
            } else {
                throw new IllegalStateException("ProcessingSurface already released!");
            }
        }
        return cameraCaptureCallback;
    }

    @GuardedBy("mLock")
    public void i(ImageReaderProxy imageReaderProxy) {
        if (this.k) {
            return;
        }
        ImageProxy imageProxy = null;
        try {
            imageProxy = imageReaderProxy.acquireNextImage();
        } catch (IllegalStateException e) {
            Logger.e("ProcessingSurfaceTextur", "Failed to acquire next image.", e);
        }
        if (imageProxy == null) {
            return;
        }
        ImageInfo imageInfo = imageProxy.getImageInfo();
        if (imageInfo == null) {
            imageProxy.close();
            return;
        }
        Integer tag = imageInfo.getTagBundle().getTag(this.t);
        if (tag == null) {
            imageProxy.close();
        } else if (this.p.getId() != tag.intValue()) {
            Logger.w("ProcessingSurfaceTextur", "ImageProxyBundle does not contain this id: " + tag);
            imageProxy.close();
        } else {
            SingleImageProxyBundle singleImageProxyBundle = new SingleImageProxyBundle(imageProxy, this.t);
            this.q.process(singleImageProxyBundle);
            singleImageProxyBundle.close();
        }
    }

    public final void k() {
        synchronized (this.i) {
            if (this.k) {
                return;
            }
            this.m.close();
            this.n.release();
            this.s.close();
            this.k = true;
        }
    }

    @Override // androidx.camera.core.impl.DeferrableSurface
    @NonNull
    public ListenableFuture<Surface> provideSurface() {
        ListenableFuture<Surface> immediateFuture;
        synchronized (this.i) {
            immediateFuture = Futures.immediateFuture(this.n);
        }
        return immediateFuture;
    }
}
