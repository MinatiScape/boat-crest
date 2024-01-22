package androidx.camera.view;

import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Size;
import android.view.PixelCopy;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.UiThread;
import androidx.camera.core.Logger;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.view.p;
import androidx.camera.view.w;
import androidx.core.content.ContextCompat;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
/* loaded from: classes.dex */
public final class w extends p {
    public SurfaceView e;
    public final b f;
    @Nullable
    public p.a g;

    @RequiresApi(24)
    /* loaded from: classes.dex */
    public static class a {
        @DoNotInline
        public static void a(@NonNull SurfaceView surfaceView, @NonNull Bitmap bitmap, @NonNull PixelCopy.OnPixelCopyFinishedListener onPixelCopyFinishedListener, @NonNull Handler handler) {
            PixelCopy.request(surfaceView, bitmap, onPixelCopyFinishedListener, handler);
        }
    }

    /* loaded from: classes.dex */
    public class b implements SurfaceHolder.Callback {
        @Nullable
        public Size h;
        @Nullable
        public SurfaceRequest i;
        @Nullable
        public Size j;
        public boolean k = false;

        public b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void e(SurfaceRequest.Result result) {
            Logger.d("SurfaceViewImpl", "Safe to release surface.");
            w.this.o();
        }

        public final boolean b() {
            Size size;
            return (this.k || this.i == null || (size = this.h) == null || !size.equals(this.j)) ? false : true;
        }

        @UiThread
        public final void c() {
            if (this.i != null) {
                Logger.d("SurfaceViewImpl", "Request canceled: " + this.i);
                this.i.willNotProvideSurface();
            }
        }

        @UiThread
        public final void d() {
            if (this.i != null) {
                Logger.d("SurfaceViewImpl", "Surface invalidated " + this.i);
                this.i.getDeferrableSurface().close();
            }
        }

        @UiThread
        public void f(@NonNull SurfaceRequest surfaceRequest) {
            c();
            this.i = surfaceRequest;
            Size resolution = surfaceRequest.getResolution();
            this.h = resolution;
            this.k = false;
            if (g()) {
                return;
            }
            Logger.d("SurfaceViewImpl", "Wait for new Surface creation.");
            w.this.e.getHolder().setFixedSize(resolution.getWidth(), resolution.getHeight());
        }

        @UiThread
        public final boolean g() {
            Surface surface = w.this.e.getHolder().getSurface();
            if (b()) {
                Logger.d("SurfaceViewImpl", "Surface set on Preview.");
                this.i.provideSurface(surface, ContextCompat.getMainExecutor(w.this.e.getContext()), new Consumer() { // from class: androidx.camera.view.x
                    @Override // androidx.core.util.Consumer
                    public final void accept(Object obj) {
                        w.b.this.e((SurfaceRequest.Result) obj);
                    }
                });
                this.k = true;
                w.this.f();
                return true;
            }
            return false;
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            Logger.d("SurfaceViewImpl", "Surface changed. Size: " + i2 + "x" + i3);
            this.j = new Size(i2, i3);
            g();
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
            Logger.d("SurfaceViewImpl", "Surface created.");
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
            Logger.d("SurfaceViewImpl", "Surface destroyed.");
            if (this.k) {
                d();
            } else {
                c();
            }
            this.k = false;
            this.i = null;
            this.j = null;
            this.h = null;
        }
    }

    public w(@NonNull FrameLayout frameLayout, @NonNull k kVar) {
        super(frameLayout, kVar);
        this.f = new b();
    }

    public static /* synthetic */ void m(int i) {
        if (i == 0) {
            Logger.d("SurfaceViewImpl", "PreviewView.SurfaceViewImplementation.getBitmap() succeeded");
            return;
        }
        Logger.e("SurfaceViewImpl", "PreviewView.SurfaceViewImplementation.getBitmap() failed with error " + i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(SurfaceRequest surfaceRequest) {
        this.f.f(surfaceRequest);
    }

    @Override // androidx.camera.view.p
    @Nullable
    public View b() {
        return this.e;
    }

    @Override // androidx.camera.view.p
    @Nullable
    @RequiresApi(24)
    public Bitmap c() {
        SurfaceView surfaceView = this.e;
        if (surfaceView == null || surfaceView.getHolder().getSurface() == null || !this.e.getHolder().getSurface().isValid()) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(this.e.getWidth(), this.e.getHeight(), Bitmap.Config.ARGB_8888);
        SurfaceView surfaceView2 = this.e;
        a.a(surfaceView2, createBitmap, new PixelCopy.OnPixelCopyFinishedListener() { // from class: androidx.camera.view.t
            @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
            public final void onPixelCopyFinished(int i) {
                w.m(i);
            }
        }, surfaceView2.getHandler());
        return createBitmap;
    }

    @Override // androidx.camera.view.p
    public void d() {
    }

    @Override // androidx.camera.view.p
    public void e() {
    }

    @Override // androidx.camera.view.p
    public void g(@NonNull final SurfaceRequest surfaceRequest, @Nullable p.a aVar) {
        this.f822a = surfaceRequest.getResolution();
        this.g = aVar;
        l();
        surfaceRequest.addRequestCancellationListener(ContextCompat.getMainExecutor(this.e.getContext()), new Runnable() { // from class: androidx.camera.view.u
            @Override // java.lang.Runnable
            public final void run() {
                w.this.o();
            }
        });
        this.e.post(new Runnable() { // from class: androidx.camera.view.v
            @Override // java.lang.Runnable
            public final void run() {
                w.this.n(surfaceRequest);
            }
        });
    }

    @Override // androidx.camera.view.p
    @NonNull
    public ListenableFuture<Void> i() {
        return Futures.immediateFuture(null);
    }

    public void l() {
        Preconditions.checkNotNull(this.b);
        Preconditions.checkNotNull(this.f822a);
        SurfaceView surfaceView = new SurfaceView(this.b.getContext());
        this.e = surfaceView;
        surfaceView.setLayoutParams(new FrameLayout.LayoutParams(this.f822a.getWidth(), this.f822a.getHeight()));
        this.b.removeAllViews();
        this.b.addView(this.e);
        this.e.getHolder().addCallback(this.f);
    }

    public void o() {
        p.a aVar = this.g;
        if (aVar != null) {
            aVar.a();
            this.g = null;
        }
    }
}
