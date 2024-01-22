package androidx.camera.camera2.internal;

import android.annotation.SuppressLint;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Rational;
import android.util.Size;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener;
import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.camera.camera2.internal.g0;
import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.Logger;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CameraStateRegistry;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImmediateSurface;
import androidx.camera.core.impl.LiveDataObservable;
import androidx.camera.core.impl.Observable;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.UseCaseAttachState;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public final class g0 implements CameraInternal {
    public o1 A;
    @NonNull
    public final x0 B;
    @NonNull
    public final SynchronizedCaptureSessionOpener.a C;
    public final Set<String> D;
    public final UseCaseAttachState h;
    public final CameraManagerCompat i;
    public final Executor j;
    public volatile f k = f.INITIALIZED;
    public final LiveDataObservable<CameraInternal.State> l;
    public final Camera2CameraControlImpl m;
    public final g n;
    @NonNull
    public final Camera2CameraInfoImpl o;
    @Nullable
    public CameraDevice p;
    public int q;
    public v0 r;
    public SessionConfig s;
    public final AtomicInteger t;
    public ListenableFuture<Void> u;
    public CallbackToFutureAdapter.Completer<Void> v;
    public final Map<v0, ListenableFuture<Void>> w;
    public final d x;
    public final CameraStateRegistry y;
    public final Set<v0> z;

    /* loaded from: classes.dex */
    public class a implements FutureCallback<Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ v0 f559a;

        public a(v0 v0Var) {
            this.f559a = v0Var;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        /* renamed from: a */
        public void onSuccess(@Nullable Void r2) {
            CameraDevice cameraDevice;
            g0.this.w.remove(this.f559a);
            int i = c.f561a[g0.this.k.ordinal()];
            if (i != 2) {
                if (i != 5) {
                    if (i != 7) {
                        return;
                    }
                } else if (g0.this.q == 0) {
                    return;
                }
            }
            if (!g0.this.D() || (cameraDevice = g0.this.p) == null) {
                return;
            }
            cameraDevice.close();
            g0.this.p = null;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable th) {
        }
    }

    /* loaded from: classes.dex */
    public class b implements FutureCallback<Void> {
        public b() {
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        /* renamed from: a */
        public void onSuccess(@Nullable Void r1) {
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable th) {
            if (th instanceof CameraAccessException) {
                g0 g0Var = g0.this;
                g0Var.w("Unable to configure camera due to " + th.getMessage());
            } else if (th instanceof CancellationException) {
                g0.this.w("Unable to configure camera cancelled");
            } else if (th instanceof DeferrableSurface.SurfaceClosedException) {
                SessionConfig y = g0.this.y(((DeferrableSurface.SurfaceClosedException) th).getDeferrableSurface());
                if (y != null) {
                    g0.this.V(y);
                }
            } else if (th instanceof TimeoutException) {
                Logger.e("Camera2CameraImpl", "Unable to configure camera " + g0.this.o.getCameraId() + ", timeout!");
            } else {
                throw new RuntimeException(th);
            }
        }
    }

    /* loaded from: classes.dex */
    public static /* synthetic */ class c {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f561a;

        static {
            int[] iArr = new int[f.values().length];
            f561a = iArr;
            try {
                iArr[f.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f561a[f.CLOSING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f561a[f.OPENED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f561a[f.OPENING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f561a[f.REOPENING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f561a[f.PENDING_OPEN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f561a[f.RELEASING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f561a[f.RELEASED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* loaded from: classes.dex */
    public final class d extends CameraManager.AvailabilityCallback implements CameraStateRegistry.OnOpenAvailableListener {

        /* renamed from: a  reason: collision with root package name */
        public final String f562a;
        public boolean b = true;

        public d(String str) {
            this.f562a = str;
        }

        public boolean a() {
            return this.b;
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraAvailable(@NonNull String str) {
            if (this.f562a.equals(str)) {
                this.b = true;
                if (g0.this.k == f.PENDING_OPEN) {
                    g0.this.S(false);
                }
            }
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraUnavailable(@NonNull String str) {
            if (this.f562a.equals(str)) {
                this.b = false;
            }
        }

        @Override // androidx.camera.core.impl.CameraStateRegistry.OnOpenAvailableListener
        public void onOpenAvailable() {
            if (g0.this.k == f.PENDING_OPEN) {
                g0.this.S(false);
            }
        }
    }

    /* loaded from: classes.dex */
    public final class e implements CameraControlInternal.ControlUpdateCallback {
        public e() {
        }

        @Override // androidx.camera.core.impl.CameraControlInternal.ControlUpdateCallback
        public void onCameraControlCaptureRequests(@NonNull List<CaptureConfig> list) {
            g0.this.c0((List) Preconditions.checkNotNull(list));
        }

        @Override // androidx.camera.core.impl.CameraControlInternal.ControlUpdateCallback
        public void onCameraControlUpdateSessionConfig(@NonNull SessionConfig sessionConfig) {
            g0.this.s = (SessionConfig) Preconditions.checkNotNull(sessionConfig);
            g0.this.g0();
        }
    }

    /* loaded from: classes.dex */
    public enum f {
        INITIALIZED,
        PENDING_OPEN,
        OPENING,
        OPENED,
        CLOSING,
        REOPENING,
        RELEASING,
        RELEASED
    }

    /* loaded from: classes.dex */
    public final class g extends CameraDevice.StateCallback {

        /* renamed from: a  reason: collision with root package name */
        public final Executor f564a;
        public final ScheduledExecutorService b;
        public b c;
        public ScheduledFuture<?> d;
        @NonNull
        public final a e = new a(this);

        /* loaded from: classes.dex */
        public class a {

            /* renamed from: a  reason: collision with root package name */
            public long f565a = -1;

            public a(g gVar) {
            }

            public boolean a() {
                long uptimeMillis = SystemClock.uptimeMillis();
                long j = this.f565a;
                if (j == -1) {
                    this.f565a = uptimeMillis;
                    return true;
                }
                if (uptimeMillis - j >= 10000) {
                    b();
                    return false;
                }
                return true;
            }

            public void b() {
                this.f565a = -1L;
            }
        }

        /* loaded from: classes.dex */
        public class b implements Runnable {
            public Executor h;
            public boolean i = false;

            public b(@NonNull Executor executor) {
                this.h = executor;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void c() {
                if (this.i) {
                    return;
                }
                Preconditions.checkState(g0.this.k == f.REOPENING);
                g0.this.S(true);
            }

            public void b() {
                this.i = true;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.execute(new Runnable() { // from class: androidx.camera.camera2.internal.h0
                    @Override // java.lang.Runnable
                    public final void run() {
                        g0.g.b.this.c();
                    }
                });
            }
        }

        public g(@NonNull Executor executor, @NonNull ScheduledExecutorService scheduledExecutorService) {
            this.f564a = executor;
            this.b = scheduledExecutorService;
        }

        public boolean a() {
            if (this.d != null) {
                g0 g0Var = g0.this;
                g0Var.w("Cancelling scheduled re-open: " + this.c);
                this.c.b();
                this.c = null;
                this.d.cancel(false);
                this.d = null;
                return true;
            }
            return false;
        }

        public final void b(@NonNull CameraDevice cameraDevice, int i) {
            boolean z = g0.this.k == f.OPENING || g0.this.k == f.OPENED || g0.this.k == f.REOPENING;
            Preconditions.checkState(z, "Attempt to handle open error from non open state: " + g0.this.k);
            if (i != 1 && i != 2 && i != 4) {
                Logger.e("Camera2CameraImpl", "Error observed on open (or opening) camera device " + cameraDevice.getId() + ": " + g0.A(i) + " closing camera.");
                g0.this.b0(f.CLOSING);
                g0.this.s(false);
                return;
            }
            Logger.d("Camera2CameraImpl", String.format("Attempt to reopen camera[%s] after error[%s]", cameraDevice.getId(), g0.A(i)));
            c();
        }

        public final void c() {
            Preconditions.checkState(g0.this.q != 0, "Can only reopen camera device after error if the camera device is actually in an error state.");
            g0.this.b0(f.REOPENING);
            g0.this.s(false);
        }

        public void d() {
            this.e.b();
        }

        public void e() {
            Preconditions.checkState(this.c == null);
            Preconditions.checkState(this.d == null);
            if (this.e.a()) {
                this.c = new b(this.f564a);
                g0 g0Var = g0.this;
                g0Var.w("Attempting camera re-open in 700ms: " + this.c);
                this.d = this.b.schedule(this.c, 700L, TimeUnit.MILLISECONDS);
                return;
            }
            Logger.e("Camera2CameraImpl", "Camera reopening attempted for 10000ms without success.");
            g0.this.b0(f.INITIALIZED);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(@NonNull CameraDevice cameraDevice) {
            g0.this.w("CameraDevice.onClosed()");
            boolean z = g0.this.p == null;
            Preconditions.checkState(z, "Unexpected onClose callback on camera device: " + cameraDevice);
            int i = c.f561a[g0.this.k.ordinal()];
            if (i != 2) {
                if (i == 5) {
                    g0 g0Var = g0.this;
                    if (g0Var.q != 0) {
                        g0Var.w("Camera closed due to error: " + g0.A(g0.this.q));
                        e();
                        return;
                    }
                    g0Var.S(false);
                    return;
                } else if (i != 7) {
                    throw new IllegalStateException("Camera closed while in state: " + g0.this.k);
                }
            }
            Preconditions.checkState(g0.this.D());
            g0.this.z();
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            g0.this.w("CameraDevice.onDisconnected()");
            onError(cameraDevice, 1);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(@NonNull CameraDevice cameraDevice, int i) {
            g0 g0Var = g0.this;
            g0Var.p = cameraDevice;
            g0Var.q = i;
            int i2 = c.f561a[g0Var.k.ordinal()];
            if (i2 != 2) {
                if (i2 == 3 || i2 == 4 || i2 == 5) {
                    Logger.d("Camera2CameraImpl", String.format("CameraDevice.onError(): %s failed with %s while in %s state. Will attempt recovering from error.", cameraDevice.getId(), g0.A(i), g0.this.k.name()));
                    b(cameraDevice, i);
                    return;
                } else if (i2 != 7) {
                    throw new IllegalStateException("onError() should not be possible from state: " + g0.this.k);
                }
            }
            Logger.e("Camera2CameraImpl", String.format("CameraDevice.onError(): %s failed with %s while in %s state. Will finish closing camera.", cameraDevice.getId(), g0.A(i), g0.this.k.name()));
            g0.this.s(false);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(@NonNull CameraDevice cameraDevice) {
            g0.this.w("CameraDevice.onOpened()");
            g0 g0Var = g0.this;
            g0Var.p = cameraDevice;
            g0Var.h0(cameraDevice);
            g0 g0Var2 = g0.this;
            g0Var2.q = 0;
            int i = c.f561a[g0Var2.k.ordinal()];
            if (i == 2 || i == 7) {
                Preconditions.checkState(g0.this.D());
                g0.this.p.close();
                g0.this.p = null;
            } else if (i != 4 && i != 5) {
                throw new IllegalStateException("onOpened() should not be possible from state: " + g0.this.k);
            } else {
                g0.this.b0(f.OPENED);
                g0.this.T();
            }
        }
    }

    public g0(@NonNull CameraManagerCompat cameraManagerCompat, @NonNull String str, @NonNull Camera2CameraInfoImpl camera2CameraInfoImpl, @NonNull CameraStateRegistry cameraStateRegistry, @NonNull Executor executor, @NonNull Handler handler) throws CameraUnavailableException {
        LiveDataObservable<CameraInternal.State> liveDataObservable = new LiveDataObservable<>();
        this.l = liveDataObservable;
        this.q = 0;
        this.s = SessionConfig.defaultEmptySessionConfig();
        this.t = new AtomicInteger(0);
        this.w = new LinkedHashMap();
        this.z = new HashSet();
        this.D = new HashSet();
        this.i = cameraManagerCompat;
        this.y = cameraStateRegistry;
        ScheduledExecutorService newHandlerExecutor = CameraXExecutors.newHandlerExecutor(handler);
        Executor newSequentialExecutor = CameraXExecutors.newSequentialExecutor(executor);
        this.j = newSequentialExecutor;
        this.n = new g(newSequentialExecutor, newHandlerExecutor);
        this.h = new UseCaseAttachState(str);
        liveDataObservable.postValue(CameraInternal.State.CLOSED);
        x0 x0Var = new x0(newSequentialExecutor);
        this.B = x0Var;
        this.r = new v0();
        try {
            Camera2CameraControlImpl camera2CameraControlImpl = new Camera2CameraControlImpl(cameraManagerCompat.getCameraCharacteristicsCompat(str), newHandlerExecutor, newSequentialExecutor, new e(), camera2CameraInfoImpl.getCameraQuirks());
            this.m = camera2CameraControlImpl;
            this.o = camera2CameraInfoImpl;
            camera2CameraInfoImpl.c(camera2CameraControlImpl);
            this.C = new SynchronizedCaptureSessionOpener.a(newSequentialExecutor, newHandlerExecutor, handler, x0Var, camera2CameraInfoImpl.b());
            d dVar = new d(str);
            this.x = dVar;
            cameraStateRegistry.registerCamera(this, newSequentialExecutor, dVar);
            cameraManagerCompat.registerAvailabilityCallback(newSequentialExecutor, dVar);
        } catch (CameraAccessExceptionCompat e2) {
            throw CameraUnavailableExceptionHelper.createFrom(e2);
        }
    }

    public static String A(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "UNKNOWN ERROR" : "ERROR_CAMERA_SERVICE" : "ERROR_CAMERA_DEVICE" : "ERROR_CAMERA_DISABLED" : "ERROR_MAX_CAMERAS_IN_USE" : "ERROR_CAMERA_IN_USE" : "ERROR_NONE";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E(Collection collection) {
        try {
            d0(collection);
        } finally {
            this.m.n();
        }
    }

    public static /* synthetic */ void F(Surface surface, SurfaceTexture surfaceTexture) {
        surface.release();
        surfaceTexture.release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object I(CallbackToFutureAdapter.Completer completer) throws Exception {
        Preconditions.checkState(this.v == null, "Camera can only be released once, so release completer should be null on creation.");
        this.v = completer;
        return "Release[camera=" + this + "]";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void J(UseCase useCase) {
        w("Use case " + useCase + " ACTIVE");
        try {
            UseCaseAttachState useCaseAttachState = this.h;
            useCaseAttachState.setUseCaseActive(useCase.getName() + useCase.hashCode(), useCase.getSessionConfig());
            UseCaseAttachState useCaseAttachState2 = this.h;
            useCaseAttachState2.updateUseCase(useCase.getName() + useCase.hashCode(), useCase.getSessionConfig());
            g0();
        } catch (NullPointerException unused) {
            w("Failed to set already detached use case active");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void K(UseCase useCase) {
        w("Use case " + useCase + " INACTIVE");
        UseCaseAttachState useCaseAttachState = this.h;
        useCaseAttachState.setUseCaseInactive(useCase.getName() + useCase.hashCode());
        g0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void L(UseCase useCase) {
        w("Use case " + useCase + " RESET");
        UseCaseAttachState useCaseAttachState = this.h;
        useCaseAttachState.updateUseCase(useCase.getName() + useCase.hashCode(), useCase.getSessionConfig());
        a0(false);
        g0();
        if (this.k == f.OPENED) {
            T();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void M(UseCase useCase) {
        w("Use case " + useCase + " UPDATED");
        UseCaseAttachState useCaseAttachState = this.h;
        useCaseAttachState.updateUseCase(useCase.getName() + useCase.hashCode(), useCase.getSessionConfig());
        g0();
    }

    public static /* synthetic */ void N(SessionConfig.ErrorListener errorListener, SessionConfig sessionConfig) {
        errorListener.onError(sessionConfig, SessionConfig.SessionError.SESSION_ERROR_SURFACE_NEEDS_RESET);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void O(CallbackToFutureAdapter.Completer completer) {
        Futures.propagate(W(), completer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object P(final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.j.execute(new Runnable() { // from class: androidx.camera.camera2.internal.t
            @Override // java.lang.Runnable
            public final void run() {
                g0.this.O(completer);
            }
        });
        return "Release[request=" + this.t.getAndIncrement() + "]";
    }

    public final ListenableFuture<Void> B() {
        if (this.u == null) {
            if (this.k != f.RELEASED) {
                this.u = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.s
                    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                        Object I;
                        I = g0.this.I(completer);
                        return I;
                    }
                });
            } else {
                this.u = Futures.immediateFuture(null);
            }
        }
        return this.u;
    }

    public final boolean C() {
        return ((Camera2CameraInfoImpl) getCameraInfoInternal()).b() == 2;
    }

    public boolean D() {
        return this.w.isEmpty() && this.z.isEmpty();
    }

    public final void Q(List<UseCase> list) {
        for (UseCase useCase : list) {
            Set<String> set = this.D;
            if (!set.contains(useCase.getName() + useCase.hashCode())) {
                Set<String> set2 = this.D;
                set2.add(useCase.getName() + useCase.hashCode());
                useCase.onStateAttached();
            }
        }
    }

    public final void R(List<UseCase> list) {
        for (UseCase useCase : list) {
            Set<String> set = this.D;
            if (set.contains(useCase.getName() + useCase.hashCode())) {
                useCase.onStateDetached();
                Set<String> set2 = this.D;
                set2.remove(useCase.getName() + useCase.hashCode());
            }
        }
    }

    @SuppressLint({"MissingPermission"})
    public void S(boolean z) {
        if (!z) {
            this.n.d();
        }
        this.n.a();
        if (this.x.a() && this.y.tryOpenCamera(this)) {
            b0(f.OPENING);
            w("Opening camera.");
            try {
                this.i.openCamera(this.o.getCameraId(), this.j, v());
                return;
            } catch (CameraAccessExceptionCompat e2) {
                w("Unable to open camera due to " + e2.getMessage());
                if (e2.getReason() != 10001) {
                    return;
                }
                b0(f.INITIALIZED);
                return;
            } catch (SecurityException e3) {
                w("Unable to open camera due to " + e3.getMessage());
                b0(f.REOPENING);
                this.n.e();
                return;
            }
        }
        w("No cameras available. Waiting for available camera before opening camera.");
        b0(f.PENDING_OPEN);
    }

    public void T() {
        Preconditions.checkState(this.k == f.OPENED);
        SessionConfig.ValidatingBuilder attachedBuilder = this.h.getAttachedBuilder();
        if (!attachedBuilder.isValid()) {
            w("Unable to create capture session due to conflicting configurations");
        } else {
            Futures.addCallback(this.r.r(attachedBuilder.build(), (CameraDevice) Preconditions.checkNotNull(this.p), this.C.a()), new b(), this.j);
        }
    }

    public final void U() {
        int i = c.f561a[this.k.ordinal()];
        if (i == 1) {
            S(false);
        } else if (i != 2) {
            w("open() ignored due to being in state: " + this.k);
        } else {
            b0(f.REOPENING);
            if (D() || this.q != 0) {
                return;
            }
            Preconditions.checkState(this.p != null, "Camera Device should be open if session close is not complete");
            b0(f.OPENED);
            T();
        }
    }

    public void V(@NonNull final SessionConfig sessionConfig) {
        ScheduledExecutorService mainThreadExecutor = CameraXExecutors.mainThreadExecutor();
        List<SessionConfig.ErrorListener> errorListeners = sessionConfig.getErrorListeners();
        if (errorListeners.isEmpty()) {
            return;
        }
        final SessionConfig.ErrorListener errorListener = errorListeners.get(0);
        x("Posting surface closed", new Throwable());
        mainThreadExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.w
            @Override // java.lang.Runnable
            public final void run() {
                g0.N(SessionConfig.ErrorListener.this, sessionConfig);
            }
        });
    }

    public final ListenableFuture<Void> W() {
        ListenableFuture<Void> B = B();
        switch (c.f561a[this.k.ordinal()]) {
            case 1:
            case 6:
                Preconditions.checkState(this.p == null);
                b0(f.RELEASING);
                Preconditions.checkState(D());
                z();
                break;
            case 2:
            case 4:
            case 5:
            case 7:
                boolean a2 = this.n.a();
                b0(f.RELEASING);
                if (a2) {
                    Preconditions.checkState(D());
                    z();
                    break;
                }
                break;
            case 3:
                b0(f.RELEASING);
                s(false);
                break;
            default:
                w("release() ignored due to being in state: " + this.k);
                break;
        }
        return B;
    }

    /* renamed from: X */
    public void G(@NonNull v0 v0Var, @NonNull DeferrableSurface deferrableSurface, @NonNull Runnable runnable) {
        this.z.remove(v0Var);
        ListenableFuture<Void> Y = Y(v0Var, false);
        deferrableSurface.close();
        Futures.successfulAsList(Arrays.asList(Y, deferrableSurface.getTerminationFuture())).addListener(runnable, CameraXExecutors.directExecutor());
    }

    public ListenableFuture<Void> Y(@NonNull v0 v0Var, boolean z) {
        v0Var.e();
        ListenableFuture<Void> t = v0Var.t(z);
        w("Releasing session in state " + this.k.name());
        this.w.put(v0Var, t);
        Futures.addCallback(t, new a(v0Var), CameraXExecutors.directExecutor());
        return t;
    }

    public final void Z() {
        if (this.A != null) {
            UseCaseAttachState useCaseAttachState = this.h;
            useCaseAttachState.setUseCaseDetached(this.A.d() + this.A.hashCode());
            UseCaseAttachState useCaseAttachState2 = this.h;
            useCaseAttachState2.setUseCaseInactive(this.A.d() + this.A.hashCode());
            this.A.b();
            this.A = null;
        }
    }

    public void a0(boolean z) {
        Preconditions.checkState(this.r != null);
        w("Resetting Capture Session");
        v0 v0Var = this.r;
        SessionConfig i = v0Var.i();
        List<CaptureConfig> h = v0Var.h();
        v0 v0Var2 = new v0();
        this.r = v0Var2;
        v0Var2.u(i);
        this.r.k(h);
        Y(v0Var, z);
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void attachUseCases(@NonNull final Collection<UseCase> collection) {
        if (collection.isEmpty()) {
            return;
        }
        this.m.z();
        Q(new ArrayList(collection));
        try {
            this.j.execute(new Runnable() { // from class: androidx.camera.camera2.internal.v
                @Override // java.lang.Runnable
                public final void run() {
                    g0.this.E(collection);
                }
            });
        } catch (RejectedExecutionException e2) {
            x("Unable to attach use cases.", e2);
            this.m.n();
        }
    }

    public void b0(@NonNull f fVar) {
        CameraInternal.State state;
        w("Transitioning camera internal state: " + this.k + " --> " + fVar);
        this.k = fVar;
        switch (c.f561a[fVar.ordinal()]) {
            case 1:
                state = CameraInternal.State.CLOSED;
                break;
            case 2:
                state = CameraInternal.State.CLOSING;
                break;
            case 3:
                state = CameraInternal.State.OPEN;
                break;
            case 4:
            case 5:
                state = CameraInternal.State.OPENING;
                break;
            case 6:
                state = CameraInternal.State.PENDING_OPEN;
                break;
            case 7:
                state = CameraInternal.State.RELEASING;
                break;
            case 8:
                state = CameraInternal.State.RELEASED;
                break;
            default:
                throw new IllegalStateException("Unknown state: " + fVar);
        }
        this.y.markCameraState(this, state);
        this.l.postValue(state);
    }

    public void c0(@NonNull List<CaptureConfig> list) {
        ArrayList arrayList = new ArrayList();
        for (CaptureConfig captureConfig : list) {
            CaptureConfig.Builder from = CaptureConfig.Builder.from(captureConfig);
            if (!captureConfig.getSurfaces().isEmpty() || !captureConfig.isUseRepeatingSurface() || q(from)) {
                arrayList.add(from.build());
            }
        }
        w("Issue capture request");
        this.r.k(arrayList);
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void close() {
        this.j.execute(new Runnable() { // from class: androidx.camera.camera2.internal.a0
            @Override // java.lang.Runnable
            public final void run() {
                g0.this.t();
            }
        });
    }

    public final void d0(@NonNull Collection<UseCase> collection) {
        boolean isEmpty = this.h.getAttachedSessionConfigs().isEmpty();
        ArrayList arrayList = new ArrayList();
        for (UseCase useCase : collection) {
            UseCaseAttachState useCaseAttachState = this.h;
            if (!useCaseAttachState.isUseCaseAttached(useCase.getName() + useCase.hashCode())) {
                try {
                    UseCaseAttachState useCaseAttachState2 = this.h;
                    useCaseAttachState2.setUseCaseAttached(useCase.getName() + useCase.hashCode(), useCase.getSessionConfig());
                    arrayList.add(useCase);
                } catch (NullPointerException unused) {
                    w("Failed to attach a detached use case");
                }
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        w("Use cases [" + TextUtils.join(", ", arrayList) + "] now ATTACHED");
        if (isEmpty) {
            this.m.P(true);
            this.m.z();
        }
        p();
        g0();
        a0(false);
        if (this.k == f.OPENED) {
            T();
        } else {
            U();
        }
        f0(arrayList);
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void detachUseCases(@NonNull final Collection<UseCase> collection) {
        if (collection.isEmpty()) {
            return;
        }
        R(new ArrayList(collection));
        this.j.execute(new Runnable() { // from class: androidx.camera.camera2.internal.u
            @Override // java.lang.Runnable
            public final void run() {
                g0.this.H(collection);
            }
        });
    }

    /* renamed from: e0 */
    public final void H(@NonNull Collection<UseCase> collection) {
        ArrayList arrayList = new ArrayList();
        for (UseCase useCase : collection) {
            UseCaseAttachState useCaseAttachState = this.h;
            if (useCaseAttachState.isUseCaseAttached(useCase.getName() + useCase.hashCode())) {
                UseCaseAttachState useCaseAttachState2 = this.h;
                useCaseAttachState2.removeUseCase(useCase.getName() + useCase.hashCode());
                arrayList.add(useCase);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        w("Use cases [" + TextUtils.join(", ", arrayList) + "] now DETACHED for camera");
        r(arrayList);
        p();
        if (this.h.getAttachedSessionConfigs().isEmpty()) {
            this.m.n();
            a0(false);
            this.m.P(false);
            this.r = new v0();
            t();
            return;
        }
        g0();
        a0(false);
        if (this.k == f.OPENED) {
            T();
        }
    }

    public final void f0(Collection<UseCase> collection) {
        for (UseCase useCase : collection) {
            if (useCase instanceof Preview) {
                Size attachedSurfaceResolution = useCase.getAttachedSurfaceResolution();
                if (attachedSurfaceResolution != null) {
                    this.m.setPreviewAspectRatio(new Rational(attachedSurfaceResolution.getWidth(), attachedSurfaceResolution.getHeight()));
                    return;
                }
                return;
            }
        }
    }

    public void g0() {
        SessionConfig.ValidatingBuilder activeAndAttachedBuilder = this.h.getActiveAndAttachedBuilder();
        if (activeAndAttachedBuilder.isValid()) {
            activeAndAttachedBuilder.add(this.s);
            this.r.u(activeAndAttachedBuilder.build());
            return;
        }
        this.r.u(this.s);
    }

    @Override // androidx.camera.core.impl.CameraInternal
    @NonNull
    public CameraControlInternal getCameraControlInternal() {
        return this.m;
    }

    @Override // androidx.camera.core.impl.CameraInternal
    @NonNull
    public CameraInfoInternal getCameraInfoInternal() {
        return this.o;
    }

    @Override // androidx.camera.core.impl.CameraInternal
    @NonNull
    public Observable<CameraInternal.State> getCameraState() {
        return this.l;
    }

    public void h0(@NonNull CameraDevice cameraDevice) {
        try {
            this.m.setDefaultRequestBuilder(cameraDevice.createCaptureRequest(this.m.q()));
        } catch (CameraAccessException e2) {
            Logger.e("Camera2CameraImpl", "fail to create capture request.", e2);
        }
    }

    public final void o() {
        if (this.A != null) {
            UseCaseAttachState useCaseAttachState = this.h;
            useCaseAttachState.setUseCaseAttached(this.A.d() + this.A.hashCode(), this.A.e());
            UseCaseAttachState useCaseAttachState2 = this.h;
            useCaseAttachState2.setUseCaseActive(this.A.d() + this.A.hashCode(), this.A.e());
        }
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseActive(@NonNull final UseCase useCase) {
        Preconditions.checkNotNull(useCase);
        this.j.execute(new Runnable() { // from class: androidx.camera.camera2.internal.c0
            @Override // java.lang.Runnable
            public final void run() {
                g0.this.J(useCase);
            }
        });
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseInactive(@NonNull final UseCase useCase) {
        Preconditions.checkNotNull(useCase);
        this.j.execute(new Runnable() { // from class: androidx.camera.camera2.internal.e0
            @Override // java.lang.Runnable
            public final void run() {
                g0.this.K(useCase);
            }
        });
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseReset(@NonNull final UseCase useCase) {
        Preconditions.checkNotNull(useCase);
        this.j.execute(new Runnable() { // from class: androidx.camera.camera2.internal.f0
            @Override // java.lang.Runnable
            public final void run() {
                g0.this.L(useCase);
            }
        });
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseUpdated(@NonNull final UseCase useCase) {
        Preconditions.checkNotNull(useCase);
        this.j.execute(new Runnable() { // from class: androidx.camera.camera2.internal.d0
            @Override // java.lang.Runnable
            public final void run() {
                g0.this.M(useCase);
            }
        });
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void open() {
        this.j.execute(new Runnable() { // from class: androidx.camera.camera2.internal.z
            @Override // java.lang.Runnable
            public final void run() {
                g0.this.U();
            }
        });
    }

    public final void p() {
        SessionConfig build = this.h.getAttachedBuilder().build();
        CaptureConfig repeatingCaptureConfig = build.getRepeatingCaptureConfig();
        int size = repeatingCaptureConfig.getSurfaces().size();
        int size2 = build.getSurfaces().size();
        if (build.getSurfaces().isEmpty()) {
            return;
        }
        if (repeatingCaptureConfig.getSurfaces().isEmpty()) {
            if (this.A == null) {
                this.A = new o1(this.o.getCameraCharacteristicsCompat());
            }
            o();
        } else if (size2 == 1 && size == 1) {
            Z();
        } else if (size >= 2) {
            Z();
        } else {
            Logger.d("Camera2CameraImpl", "mMeteringRepeating is ATTACHED, SessionConfig Surfaces: " + size2 + ", CaptureConfig Surfaces: " + size);
        }
    }

    public final boolean q(CaptureConfig.Builder builder) {
        if (!builder.getSurfaces().isEmpty()) {
            Logger.w("Camera2CameraImpl", "The capture config builder already has surface inside.");
            return false;
        }
        for (SessionConfig sessionConfig : this.h.getActiveAndAttachedSessionConfigs()) {
            List<DeferrableSurface> surfaces = sessionConfig.getRepeatingCaptureConfig().getSurfaces();
            if (!surfaces.isEmpty()) {
                for (DeferrableSurface deferrableSurface : surfaces) {
                    builder.addSurface(deferrableSurface);
                }
            }
        }
        if (builder.getSurfaces().isEmpty()) {
            Logger.w("Camera2CameraImpl", "Unable to find a repeating surface to attach to CaptureConfig");
            return false;
        }
        return true;
    }

    public final void r(Collection<UseCase> collection) {
        for (UseCase useCase : collection) {
            if (useCase instanceof Preview) {
                this.m.setPreviewAspectRatio(null);
                return;
            }
        }
    }

    @Override // androidx.camera.core.impl.CameraInternal
    @NonNull
    public ListenableFuture<Void> release() {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.x
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object P;
                P = g0.this.P(completer);
                return P;
            }
        });
    }

    public void s(boolean z) {
        boolean z2 = this.k == f.CLOSING || this.k == f.RELEASING || (this.k == f.REOPENING && this.q != 0);
        Preconditions.checkState(z2, "closeCamera should only be called in a CLOSING, RELEASING or REOPENING (with error) state. Current state: " + this.k + " (error: " + A(this.q) + ")");
        int i = Build.VERSION.SDK_INT;
        if (i > 23 && i < 29 && C() && this.q == 0) {
            u(z);
        } else {
            a0(z);
        }
        this.r.d();
    }

    public final void t() {
        w("Closing camera.");
        int i = c.f561a[this.k.ordinal()];
        if (i == 3) {
            b0(f.CLOSING);
            s(false);
        } else if (i == 4 || i == 5) {
            boolean a2 = this.n.a();
            b0(f.CLOSING);
            if (a2) {
                Preconditions.checkState(D());
                z();
            }
        } else if (i != 6) {
            w("close() ignored due to being in state: " + this.k);
        } else {
            Preconditions.checkState(this.p == null);
            b0(f.INITIALIZED);
        }
    }

    @NonNull
    public String toString() {
        return String.format(Locale.US, "Camera@%x[id=%s]", Integer.valueOf(hashCode()), this.o.getCameraId());
    }

    public final void u(boolean z) {
        final v0 v0Var = new v0();
        this.z.add(v0Var);
        a0(z);
        final SurfaceTexture surfaceTexture = new SurfaceTexture(0);
        surfaceTexture.setDefaultBufferSize(640, 480);
        final Surface surface = new Surface(surfaceTexture);
        final Runnable runnable = new Runnable() { // from class: androidx.camera.camera2.internal.y
            @Override // java.lang.Runnable
            public final void run() {
                g0.F(surface, surfaceTexture);
            }
        };
        SessionConfig.Builder builder = new SessionConfig.Builder();
        final ImmediateSurface immediateSurface = new ImmediateSurface(surface);
        builder.addNonRepeatingSurface(immediateSurface);
        builder.setTemplateType(1);
        w("Start configAndClose.");
        v0Var.r(builder.build(), (CameraDevice) Preconditions.checkNotNull(this.p), this.C.a()).addListener(new Runnable() { // from class: androidx.camera.camera2.internal.b0
            @Override // java.lang.Runnable
            public final void run() {
                g0.this.G(v0Var, immediateSurface, runnable);
            }
        }, this.j);
    }

    public final CameraDevice.StateCallback v() {
        ArrayList arrayList = new ArrayList(this.h.getAttachedBuilder().build().getDeviceStateCallbacks());
        arrayList.add(this.B.c());
        arrayList.add(this.n);
        return CameraDeviceStateCallbacks.createComboCallback(arrayList);
    }

    public void w(@NonNull String str) {
        x(str, null);
    }

    public final void x(@NonNull String str, @Nullable Throwable th) {
        Logger.d("Camera2CameraImpl", String.format("{%s} %s", toString(), str), th);
    }

    @Nullable
    public SessionConfig y(@NonNull DeferrableSurface deferrableSurface) {
        for (SessionConfig sessionConfig : this.h.getAttachedSessionConfigs()) {
            if (sessionConfig.getSurfaces().contains(deferrableSurface)) {
                return sessionConfig;
            }
        }
        return null;
    }

    public void z() {
        Preconditions.checkState(this.k == f.RELEASING || this.k == f.CLOSING);
        Preconditions.checkState(this.w.isEmpty());
        this.p = null;
        if (this.k == f.CLOSING) {
            b0(f.INITIALIZED);
            return;
        }
        this.i.unregisterAvailabilityCallback(this.x);
        b0(f.RELEASED);
        CallbackToFutureAdapter.Completer<Void> completer = this.v;
        if (completer != null) {
            completer.set(null);
            this.v = null;
        }
    }
}
