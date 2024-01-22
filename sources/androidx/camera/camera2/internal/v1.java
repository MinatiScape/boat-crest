package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.os.Handler;
import android.view.Surface;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.camera.camera2.internal.SynchronizedCaptureSession;
import androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener;
import androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat;
import androidx.camera.camera2.internal.compat.CameraDeviceCompat;
import androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.DeferrableSurfaces;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
/* loaded from: classes.dex */
public class v1 extends SynchronizedCaptureSession.StateCallback implements SynchronizedCaptureSession, SynchronizedCaptureSessionOpener.b {
    @NonNull
    public final x0 b;
    @NonNull
    public final Handler c;
    @NonNull
    public final Executor d;
    @NonNull
    public final ScheduledExecutorService e;
    @Nullable
    public SynchronizedCaptureSession.StateCallback f;
    @Nullable
    public CameraCaptureSessionCompat g;
    @Nullable
    @GuardedBy("mLock")
    public ListenableFuture<Void> h;
    @Nullable
    @GuardedBy("mLock")
    public CallbackToFutureAdapter.Completer<Void> i;
    @Nullable
    @GuardedBy("mLock")
    public ListenableFuture<List<Surface>> j;

    /* renamed from: a  reason: collision with root package name */
    public final Object f593a = new Object();
    @Nullable
    @GuardedBy("mLock")
    public List<DeferrableSurface> k = null;
    @GuardedBy("mLock")
    public boolean l = false;
    @GuardedBy("mLock")
    public boolean m = false;
    @GuardedBy("mLock")
    public boolean n = false;

    /* loaded from: classes.dex */
    public class a implements FutureCallback<Void> {
        public a() {
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        /* renamed from: a */
        public void onSuccess(@Nullable Void r1) {
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable th) {
            v1.this.c();
            v1 v1Var = v1.this;
            v1Var.b.j(v1Var);
        }
    }

    /* loaded from: classes.dex */
    public class b extends CameraCaptureSession.StateCallback {
        public b() {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onActive(@NonNull CameraCaptureSession cameraCaptureSession) {
            v1.this.z(cameraCaptureSession);
            v1 v1Var = v1.this;
            v1Var.m(v1Var);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        @RequiresApi(api = 26)
        public void onCaptureQueueEmpty(@NonNull CameraCaptureSession cameraCaptureSession) {
            v1.this.z(cameraCaptureSession);
            v1 v1Var = v1.this;
            v1Var.n(v1Var);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onClosed(@NonNull CameraCaptureSession cameraCaptureSession) {
            v1.this.z(cameraCaptureSession);
            v1 v1Var = v1.this;
            v1Var.o(v1Var);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
            CallbackToFutureAdapter.Completer<Void> completer;
            try {
                v1.this.z(cameraCaptureSession);
                v1 v1Var = v1.this;
                v1Var.p(v1Var);
                synchronized (v1.this.f593a) {
                    Preconditions.checkNotNull(v1.this.i, "OpenCaptureSession completer should not null");
                    v1 v1Var2 = v1.this;
                    completer = v1Var2.i;
                    v1Var2.i = null;
                }
                completer.setException(new IllegalStateException("onConfigureFailed"));
            } catch (Throwable th) {
                synchronized (v1.this.f593a) {
                    Preconditions.checkNotNull(v1.this.i, "OpenCaptureSession completer should not null");
                    v1 v1Var3 = v1.this;
                    CallbackToFutureAdapter.Completer<Void> completer2 = v1Var3.i;
                    v1Var3.i = null;
                    completer2.setException(new IllegalStateException("onConfigureFailed"));
                    throw th;
                }
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
            CallbackToFutureAdapter.Completer<Void> completer;
            try {
                v1.this.z(cameraCaptureSession);
                v1 v1Var = v1.this;
                v1Var.q(v1Var);
                synchronized (v1.this.f593a) {
                    Preconditions.checkNotNull(v1.this.i, "OpenCaptureSession completer should not null");
                    v1 v1Var2 = v1.this;
                    completer = v1Var2.i;
                    v1Var2.i = null;
                }
                completer.set(null);
            } catch (Throwable th) {
                synchronized (v1.this.f593a) {
                    Preconditions.checkNotNull(v1.this.i, "OpenCaptureSession completer should not null");
                    v1 v1Var3 = v1.this;
                    CallbackToFutureAdapter.Completer<Void> completer2 = v1Var3.i;
                    v1Var3.i = null;
                    completer2.set(null);
                    throw th;
                }
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onReady(@NonNull CameraCaptureSession cameraCaptureSession) {
            v1.this.z(cameraCaptureSession);
            v1 v1Var = v1.this;
            v1Var.r(v1Var);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        @RequiresApi(api = 23)
        public void onSurfacePrepared(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull Surface surface) {
            v1.this.z(cameraCaptureSession);
            v1 v1Var = v1.this;
            v1Var.t(v1Var, surface);
        }
    }

    public v1(@NonNull x0 x0Var, @NonNull Executor executor, @NonNull ScheduledExecutorService scheduledExecutorService, @NonNull Handler handler) {
        this.b = x0Var;
        this.c = handler;
        this.d = executor;
        this.e = scheduledExecutorService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void C() {
        s(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void D(SynchronizedCaptureSession synchronizedCaptureSession) {
        this.b.h(this);
        s(synchronizedCaptureSession);
        this.f.o(synchronizedCaptureSession);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E(SynchronizedCaptureSession synchronizedCaptureSession) {
        this.f.s(synchronizedCaptureSession);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object F(List list, CameraDeviceCompat cameraDeviceCompat, SessionConfigurationCompat sessionConfigurationCompat, CallbackToFutureAdapter.Completer completer) throws Exception {
        String str;
        synchronized (this.f593a) {
            A(list);
            Preconditions.checkState(this.i == null, "The openCaptureSessionCompleter can only set once!");
            this.i = completer;
            cameraDeviceCompat.createCaptureSession(sessionConfigurationCompat);
            str = "openCaptureSession[session=" + this + "]";
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ListenableFuture G(List list, List list2) throws Exception {
        Logger.d("SyncCaptureSessionBase", "[" + this + "] getSurface...done");
        if (list2.contains(null)) {
            return Futures.immediateFailedFuture(new DeferrableSurface.SurfaceClosedException("Surface closed", (DeferrableSurface) list.get(list2.indexOf(null))));
        }
        if (list2.isEmpty()) {
            return Futures.immediateFailedFuture(new IllegalArgumentException("Unable to open capture session without surfaces"));
        }
        return Futures.immediateFuture(list2);
    }

    public void A(@NonNull List<DeferrableSurface> list) throws DeferrableSurface.SurfaceClosedException {
        synchronized (this.f593a) {
            H();
            DeferrableSurfaces.incrementAll(list);
            this.k = list;
        }
    }

    public boolean B() {
        boolean z;
        synchronized (this.f593a) {
            z = this.h != null;
        }
        return z;
    }

    public void H() {
        synchronized (this.f593a) {
            List<DeferrableSurface> list = this.k;
            if (list != null) {
                DeferrableSurfaces.decrementAll(list);
                this.k = null;
            }
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.b
    @NonNull
    public ListenableFuture<Void> a(@NonNull CameraDevice cameraDevice, @NonNull final SessionConfigurationCompat sessionConfigurationCompat, @NonNull final List<DeferrableSurface> list) {
        synchronized (this.f593a) {
            if (this.m) {
                return Futures.immediateFailedFuture(new CancellationException("Opener is disabled"));
            }
            this.b.l(this);
            final CameraDeviceCompat cameraDeviceCompat = CameraDeviceCompat.toCameraDeviceCompat(cameraDevice, this.c);
            ListenableFuture<Void> future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.r1
                @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                    Object F;
                    F = v1.this.F(list, cameraDeviceCompat, sessionConfigurationCompat, completer);
                    return F;
                }
            });
            this.h = future;
            Futures.addCallback(future, new a(), CameraXExecutors.directExecutor());
            return Futures.nonCancellationPropagating(this.h);
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    @NonNull
    public SynchronizedCaptureSession.StateCallback b() {
        return this;
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public void c() {
        H();
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public void close() {
        Preconditions.checkNotNull(this.g, "Need to call openCaptureSession before using this API.");
        this.b.i(this);
        this.g.toCameraCaptureSession().close();
        getExecutor().execute(new Runnable() { // from class: androidx.camera.camera2.internal.s1
            @Override // java.lang.Runnable
            public final void run() {
                v1.this.C();
            }
        });
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public void d() throws CameraAccessException {
        Preconditions.checkNotNull(this.g, "Need to call openCaptureSession before using this API.");
        this.g.toCameraCaptureSession().abortCaptures();
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    @NonNull
    public CameraDevice e() {
        Preconditions.checkNotNull(this.g);
        return this.g.toCameraCaptureSession().getDevice();
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public int f(@NonNull CaptureRequest captureRequest, @NonNull CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        Preconditions.checkNotNull(this.g, "Need to call openCaptureSession before using this API.");
        return this.g.setSingleRepeatingRequest(captureRequest, getExecutor(), captureCallback);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.b
    @NonNull
    public SessionConfigurationCompat g(int i, @NonNull List<OutputConfigurationCompat> list, @NonNull SynchronizedCaptureSession.StateCallback stateCallback) {
        this.f = stateCallback;
        return new SessionConfigurationCompat(i, list, getExecutor(), new b());
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.b
    @NonNull
    public Executor getExecutor() {
        return this.d;
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.b
    @NonNull
    public ListenableFuture<List<Surface>> h(@NonNull final List<DeferrableSurface> list, long j) {
        synchronized (this.f593a) {
            if (this.m) {
                return Futures.immediateFailedFuture(new CancellationException("Opener is disabled"));
            }
            FutureChain transformAsync = FutureChain.from(DeferrableSurfaces.surfaceListWithTimeout(list, false, j, getExecutor(), this.e)).transformAsync(new AsyncFunction() { // from class: androidx.camera.camera2.internal.q1
                @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
                public final ListenableFuture apply(Object obj) {
                    ListenableFuture G;
                    G = v1.this.G(list, (List) obj);
                    return G;
                }
            }, getExecutor());
            this.j = transformAsync;
            return Futures.nonCancellationPropagating(transformAsync);
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    @NonNull
    public ListenableFuture<Void> i(@NonNull String str) {
        return Futures.immediateFuture(null);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public int j(@NonNull List<CaptureRequest> list, @NonNull CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        Preconditions.checkNotNull(this.g, "Need to call openCaptureSession before using this API.");
        return this.g.captureBurstRequests(list, getExecutor(), captureCallback);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    @NonNull
    public CameraCaptureSessionCompat k() {
        Preconditions.checkNotNull(this.g);
        return this.g;
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public void l() throws CameraAccessException {
        Preconditions.checkNotNull(this.g, "Need to call openCaptureSession before using this API.");
        this.g.toCameraCaptureSession().stopRepeating();
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void m(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        this.f.m(synchronizedCaptureSession);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    @RequiresApi(api = 26)
    public void n(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        this.f.n(synchronizedCaptureSession);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void o(@NonNull final SynchronizedCaptureSession synchronizedCaptureSession) {
        ListenableFuture<Void> listenableFuture;
        synchronized (this.f593a) {
            if (this.l) {
                listenableFuture = null;
            } else {
                this.l = true;
                Preconditions.checkNotNull(this.h, "Need to call openCaptureSession before using this API.");
                listenableFuture = this.h;
            }
        }
        c();
        if (listenableFuture != null) {
            listenableFuture.addListener(new Runnable() { // from class: androidx.camera.camera2.internal.u1
                @Override // java.lang.Runnable
                public final void run() {
                    v1.this.D(synchronizedCaptureSession);
                }
            }, CameraXExecutors.directExecutor());
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void p(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        c();
        this.b.j(this);
        this.f.p(synchronizedCaptureSession);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void q(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        this.b.k(this);
        this.f.q(synchronizedCaptureSession);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void r(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        this.f.r(synchronizedCaptureSession);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void s(@NonNull final SynchronizedCaptureSession synchronizedCaptureSession) {
        ListenableFuture<Void> listenableFuture;
        synchronized (this.f593a) {
            if (this.n) {
                listenableFuture = null;
            } else {
                this.n = true;
                Preconditions.checkNotNull(this.h, "Need to call openCaptureSession before using this API.");
                listenableFuture = this.h;
            }
        }
        if (listenableFuture != null) {
            listenableFuture.addListener(new Runnable() { // from class: androidx.camera.camera2.internal.t1
                @Override // java.lang.Runnable
                public final void run() {
                    v1.this.E(synchronizedCaptureSession);
                }
            }, CameraXExecutors.directExecutor());
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.b
    public boolean stop() {
        boolean z;
        try {
            synchronized (this.f593a) {
                if (!this.m) {
                    ListenableFuture<List<Surface>> listenableFuture = this.j;
                    r1 = listenableFuture != null ? listenableFuture : null;
                    this.m = true;
                }
                z = !B();
            }
            return z;
        } finally {
            if (r1 != null) {
                r1.cancel(true);
            }
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    @RequiresApi(api = 23)
    public void t(@NonNull SynchronizedCaptureSession synchronizedCaptureSession, @NonNull Surface surface) {
        this.f.t(synchronizedCaptureSession, surface);
    }

    public void z(@NonNull CameraCaptureSession cameraCaptureSession) {
        if (this.g == null) {
            this.g = CameraCaptureSessionCompat.toCameraCaptureSessionCompat(cameraCaptureSession, this.c);
        }
    }
}
