package androidx.camera.view;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.Logger;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.view.p;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.content.ContextCompat;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes.dex */
public final class d0 extends p {
    public TextureView e;
    public SurfaceTexture f;
    public ListenableFuture<SurfaceRequest.Result> g;
    public SurfaceRequest h;
    public boolean i;
    public SurfaceTexture j;
    public AtomicReference<CallbackToFutureAdapter.Completer<Void>> k;
    @Nullable
    public p.a l;

    /* loaded from: classes.dex */
    public class a implements TextureView.SurfaceTextureListener {

        /* renamed from: androidx.camera.view.d0$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0120a implements FutureCallback<SurfaceRequest.Result> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ SurfaceTexture f809a;

            public C0120a(SurfaceTexture surfaceTexture) {
                this.f809a = surfaceTexture;
            }

            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            /* renamed from: a */
            public void onSuccess(SurfaceRequest.Result result) {
                Preconditions.checkState(result.getResultCode() != 3, "Unexpected result from SurfaceRequest. Surface was provided twice.");
                Logger.d("TextureViewImpl", "SurfaceTexture about to manually be destroyed");
                this.f809a.release();
                d0 d0Var = d0.this;
                if (d0Var.j != null) {
                    d0Var.j = null;
                }
            }

            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onFailure(Throwable th) {
                throw new IllegalStateException("SurfaceReleaseFuture did not complete nicely.", th);
            }
        }

        public a() {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int i, int i2) {
            Logger.d("TextureViewImpl", "SurfaceTexture available. Size: " + i + "x" + i2);
            d0 d0Var = d0.this;
            d0Var.f = surfaceTexture;
            if (d0Var.g != null) {
                Preconditions.checkNotNull(d0Var.h);
                Logger.d("TextureViewImpl", "Surface invalidated " + d0.this.h);
                d0.this.h.getDeferrableSurface().close();
                return;
            }
            d0Var.u();
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surfaceTexture) {
            d0 d0Var = d0.this;
            d0Var.f = null;
            ListenableFuture<SurfaceRequest.Result> listenableFuture = d0Var.g;
            if (listenableFuture != null) {
                Futures.addCallback(listenableFuture, new C0120a(surfaceTexture), ContextCompat.getMainExecutor(d0.this.e.getContext()));
                d0.this.j = surfaceTexture;
                return false;
            }
            Logger.d("TextureViewImpl", "SurfaceTexture about to be destroyed");
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surfaceTexture, int i, int i2) {
            Logger.d("TextureViewImpl", "SurfaceTexture size changed: " + i + "x" + i2);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surfaceTexture) {
            CallbackToFutureAdapter.Completer<Void> andSet = d0.this.k.getAndSet(null);
            if (andSet != null) {
                andSet.set(null);
            }
        }
    }

    public d0(@NonNull FrameLayout frameLayout, @NonNull k kVar) {
        super(frameLayout, kVar);
        this.i = false;
        this.k = new AtomicReference<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(SurfaceRequest surfaceRequest) {
        SurfaceRequest surfaceRequest2 = this.h;
        if (surfaceRequest2 != null && surfaceRequest2 == surfaceRequest) {
            this.h = null;
            this.g = null;
        }
        s();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object p(Surface surface, final CallbackToFutureAdapter.Completer completer) throws Exception {
        Logger.d("TextureViewImpl", "Surface set on Preview.");
        SurfaceRequest surfaceRequest = this.h;
        Executor directExecutor = CameraXExecutors.directExecutor();
        Objects.requireNonNull(completer);
        surfaceRequest.provideSurface(surface, directExecutor, new Consumer() { // from class: androidx.camera.view.a0
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                CallbackToFutureAdapter.Completer.this.set((SurfaceRequest.Result) obj);
            }
        });
        return "provideSurface[request=" + this.h + " surface=" + surface + "]";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q(Surface surface, ListenableFuture listenableFuture, SurfaceRequest surfaceRequest) {
        Logger.d("TextureViewImpl", "Safe to release surface.");
        s();
        surface.release();
        if (this.g == listenableFuture) {
            this.g = null;
        }
        if (this.h == surfaceRequest) {
            this.h = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object r(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.k.set(completer);
        return "textureViewImpl_waitForNextFrame";
    }

    @Override // androidx.camera.view.p
    @Nullable
    public View b() {
        return this.e;
    }

    @Override // androidx.camera.view.p
    @Nullable
    public Bitmap c() {
        TextureView textureView = this.e;
        if (textureView == null || !textureView.isAvailable()) {
            return null;
        }
        return this.e.getBitmap();
    }

    @Override // androidx.camera.view.p
    public void d() {
        t();
    }

    @Override // androidx.camera.view.p
    public void e() {
        this.i = true;
    }

    @Override // androidx.camera.view.p
    public void g(@NonNull final SurfaceRequest surfaceRequest, @Nullable p.a aVar) {
        this.f822a = surfaceRequest.getResolution();
        this.l = aVar;
        n();
        SurfaceRequest surfaceRequest2 = this.h;
        if (surfaceRequest2 != null) {
            surfaceRequest2.willNotProvideSurface();
        }
        this.h = surfaceRequest;
        surfaceRequest.addRequestCancellationListener(ContextCompat.getMainExecutor(this.e.getContext()), new Runnable() { // from class: androidx.camera.view.c0
            @Override // java.lang.Runnable
            public final void run() {
                d0.this.o(surfaceRequest);
            }
        });
        u();
    }

    @Override // androidx.camera.view.p
    @NonNull
    public ListenableFuture<Void> i() {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.view.y
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object r;
                r = d0.this.r(completer);
                return r;
            }
        });
    }

    public void n() {
        Preconditions.checkNotNull(this.b);
        Preconditions.checkNotNull(this.f822a);
        TextureView textureView = new TextureView(this.b.getContext());
        this.e = textureView;
        textureView.setLayoutParams(new FrameLayout.LayoutParams(this.f822a.getWidth(), this.f822a.getHeight()));
        this.e.setSurfaceTextureListener(new a());
        this.b.removeAllViews();
        this.b.addView(this.e);
    }

    public final void s() {
        p.a aVar = this.l;
        if (aVar != null) {
            aVar.a();
            this.l = null;
        }
    }

    public final void t() {
        if (!this.i || this.j == null) {
            return;
        }
        SurfaceTexture surfaceTexture = this.e.getSurfaceTexture();
        SurfaceTexture surfaceTexture2 = this.j;
        if (surfaceTexture != surfaceTexture2) {
            this.e.setSurfaceTexture(surfaceTexture2);
            this.j = null;
            this.i = false;
        }
    }

    public void u() {
        SurfaceTexture surfaceTexture;
        Size size = this.f822a;
        if (size == null || (surfaceTexture = this.f) == null || this.h == null) {
            return;
        }
        surfaceTexture.setDefaultBufferSize(size.getWidth(), this.f822a.getHeight());
        final Surface surface = new Surface(this.f);
        final SurfaceRequest surfaceRequest = this.h;
        final ListenableFuture<SurfaceRequest.Result> future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.view.z
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object p;
                p = d0.this.p(surface, completer);
                return p;
            }
        });
        this.g = future;
        future.addListener(new Runnable() { // from class: androidx.camera.view.b0
            @Override // java.lang.Runnable
            public final void run() {
                d0.this.q(surface, future, surfaceRequest);
            }
        }, ContextCompat.getMainExecutor(this.e.getContext()));
        f();
    }
}
