package androidx.camera.core;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import androidx.annotation.GuardedBy;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.experimental.UseExperimental;
import androidx.arch.core.util.Function;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.CameraFactory;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CameraRepository;
import androidx.camera.core.impl.CameraThreadConfig;
import androidx.camera.core.impl.CameraValidator;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.internal.compat.quirk.DeviceQuirks;
import androidx.camera.core.internal.compat.quirk.IncompleteCameraListQuirk;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.os.HandlerCompat;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
@MainThread
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public final class CameraX {
    @GuardedBy("INSTANCE_LOCK")
    public static CameraX n;
    @GuardedBy("INSTANCE_LOCK")
    public static CameraXConfig.Provider o;
    public final CameraXConfig c;
    public final Executor d;
    public final Handler e;
    @Nullable
    public final HandlerThread f;
    public CameraFactory g;
    public CameraDeviceSurfaceManager h;
    public UseCaseConfigFactory i;
    public Context j;
    public static final Object m = new Object();
    @GuardedBy("INSTANCE_LOCK")
    public static ListenableFuture<Void> p = Futures.immediateFailedFuture(new IllegalStateException("CameraX is not initialized."));
    @GuardedBy("INSTANCE_LOCK")
    public static ListenableFuture<Void> q = Futures.immediateFuture(null);

    /* renamed from: a  reason: collision with root package name */
    public final CameraRepository f617a = new CameraRepository();
    public final Object b = new Object();
    @GuardedBy("mInitializeLock")
    public c k = c.UNINITIALIZED;
    @GuardedBy("mInitializeLock")
    public ListenableFuture<Void> l = Futures.immediateFuture(null);

    /* loaded from: classes.dex */
    public class a implements FutureCallback<Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CallbackToFutureAdapter.Completer f618a;
        public final /* synthetic */ CameraX b;

        public a(CallbackToFutureAdapter.Completer completer, CameraX cameraX) {
            this.f618a = completer;
            this.b = cameraX;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        /* renamed from: a */
        public void onSuccess(@Nullable Void r2) {
            this.f618a.set(null);
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable th) {
            Logger.w("CameraX", "CameraX initialize() failed", th);
            synchronized (CameraX.m) {
                if (CameraX.n == this.b) {
                    CameraX.K();
                }
            }
            this.f618a.setException(th);
        }
    }

    /* loaded from: classes.dex */
    public static /* synthetic */ class b {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f619a;

        static {
            int[] iArr = new int[c.values().length];
            f619a = iArr;
            try {
                iArr[c.UNINITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f619a[c.INITIALIZING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f619a[c.INITIALIZED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f619a[c.SHUTDOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum c {
        UNINITIALIZED,
        INITIALIZING,
        INITIALIZED,
        SHUTDOWN
    }

    public CameraX(@NonNull CameraXConfig cameraXConfig) {
        this.c = (CameraXConfig) Preconditions.checkNotNull(cameraXConfig);
        Executor cameraExecutor = cameraXConfig.getCameraExecutor(null);
        Handler schedulerHandler = cameraXConfig.getSchedulerHandler(null);
        this.d = cameraExecutor == null ? new i() : cameraExecutor;
        if (schedulerHandler == null) {
            HandlerThread handlerThread = new HandlerThread("CameraX-scheduler", 10);
            this.f = handlerThread;
            handlerThread.start();
            this.e = HandlerCompat.createAsync(handlerThread.getLooper());
            return;
        }
        this.f = null;
        this.e = schedulerHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object A(Context context, CallbackToFutureAdapter.Completer completer) throws Exception {
        s(this.d, SystemClock.elapsedRealtime(), context, completer);
        return "CameraX initInternal";
    }

    public static /* synthetic */ CameraXConfig B(CameraXConfig cameraXConfig) {
        return cameraXConfig;
    }

    public static /* synthetic */ Object D(final CameraX cameraX, final Context context, CallbackToFutureAdapter.Completer completer) throws Exception {
        synchronized (m) {
            Futures.addCallback(FutureChain.from(q).transformAsync(new AsyncFunction() { // from class: androidx.camera.core.o
                @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
                public final ListenableFuture apply(Object obj) {
                    ListenableFuture t;
                    Void r3 = (Void) obj;
                    t = CameraX.this.t(context);
                    return t;
                }
            }, CameraXExecutors.directExecutor()), new a(completer, cameraX), CameraXExecutors.directExecutor());
        }
        return "CameraX-initialize";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E(CallbackToFutureAdapter.Completer completer) {
        if (this.f != null) {
            Executor executor = this.d;
            if (executor instanceof i) {
                ((i) executor).b();
            }
            this.f.quit();
            completer.set(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object F(final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.f617a.deinit().addListener(new Runnable() { // from class: androidx.camera.core.v
            @Override // java.lang.Runnable
            public final void run() {
                CameraX.this.E(completer);
            }
        }, this.d);
        return "CameraX shutdownInternal";
    }

    public static /* synthetic */ void G(CameraX cameraX, CallbackToFutureAdapter.Completer completer) {
        Futures.propagate(cameraX.J(), completer);
    }

    public static /* synthetic */ Object H(final CameraX cameraX, final CallbackToFutureAdapter.Completer completer) throws Exception {
        synchronized (m) {
            p.addListener(new Runnable() { // from class: androidx.camera.core.k
                @Override // java.lang.Runnable
                public final void run() {
                    CameraX.G(CameraX.this, completer);
                }
            }, CameraXExecutors.directExecutor());
        }
        return "CameraX shutdown";
    }

    @NonNull
    @GuardedBy("INSTANCE_LOCK")
    public static ListenableFuture<Void> K() {
        final CameraX cameraX = n;
        if (cameraX == null) {
            return q;
        }
        n = null;
        ListenableFuture<Void> future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.p
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object H;
                H = CameraX.H(CameraX.this, completer);
                return H;
            }
        });
        q = future;
        return future;
    }

    @NonNull
    public static CameraX L() {
        try {
            return q().get(3000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void configureInstance(@NonNull final CameraXConfig cameraXConfig) {
        synchronized (m) {
            n(new CameraXConfig.Provider() { // from class: androidx.camera.core.m
                @Override // androidx.camera.core.CameraXConfig.Provider
                public final CameraXConfig getCameraXConfig() {
                    CameraXConfig w;
                    w = CameraX.w(CameraXConfig.this);
                    return w;
                }
            });
        }
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static CameraInternal getCameraWithCameraSelector(@NonNull CameraSelector cameraSelector) {
        return cameraSelector.select(m().getCameraRepository().getCameras());
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Deprecated
    public static Context getContext() {
        return m().j;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static ListenableFuture<CameraX> getOrCreateInstance(@NonNull Context context) {
        ListenableFuture<CameraX> r;
        Preconditions.checkNotNull(context, "Context must not be null.");
        synchronized (m) {
            boolean z = o != null;
            r = r();
            if (r.isDone()) {
                try {
                    r.get();
                } catch (InterruptedException e) {
                    throw new RuntimeException("Unexpected thread interrupt. Should not be possible since future is already complete.", e);
                } catch (ExecutionException unused) {
                    K();
                    r = null;
                }
            }
            if (r == null) {
                if (!z) {
                    CameraXConfig.Provider p2 = p(context);
                    if (p2 != null) {
                        n(p2);
                    } else {
                        throw new IllegalStateException("CameraX is not configured properly. The most likely cause is you did not include a default implementation in your build such as 'camera-camera2'.");
                    }
                }
                u(context);
                r = r();
            }
        }
        return r;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.TESTS})
    public static ListenableFuture<Void> initialize(@NonNull Context context, @NonNull final CameraXConfig cameraXConfig) {
        ListenableFuture<Void> listenableFuture;
        synchronized (m) {
            Preconditions.checkNotNull(context);
            n(new CameraXConfig.Provider() { // from class: androidx.camera.core.n
                @Override // androidx.camera.core.CameraXConfig.Provider
                public final CameraXConfig getCameraXConfig() {
                    CameraXConfig B;
                    B = CameraX.B(CameraXConfig.this);
                    return B;
                }
            });
            u(context);
            listenableFuture = p;
        }
        return listenableFuture;
    }

    @RestrictTo({RestrictTo.Scope.TESTS})
    public static boolean isInitialized() {
        boolean z;
        synchronized (m) {
            CameraX cameraX = n;
            z = cameraX != null && cameraX.v();
        }
        return z;
    }

    @NonNull
    public static CameraX m() {
        CameraX L = L();
        Preconditions.checkState(L.v(), "Must call CameraX.initialize() first");
        return L;
    }

    @GuardedBy("INSTANCE_LOCK")
    public static void n(@NonNull CameraXConfig.Provider provider) {
        Preconditions.checkNotNull(provider);
        Preconditions.checkState(o == null, "CameraX has already been configured. To use a different configuration, shutdown() must be called.");
        o = provider;
        Integer num = (Integer) provider.getCameraXConfig().retrieveOption(CameraXConfig.g, null);
        if (num != null) {
            Logger.b(num.intValue());
        }
    }

    @Nullable
    public static Application o(@NonNull Context context) {
        for (Context applicationContext = context.getApplicationContext(); applicationContext instanceof ContextWrapper; applicationContext = ((ContextWrapper) applicationContext).getBaseContext()) {
            if (applicationContext instanceof Application) {
                return (Application) applicationContext;
            }
        }
        return null;
    }

    @Nullable
    public static CameraXConfig.Provider p(@NonNull Context context) {
        Application o2 = o(context);
        if (o2 instanceof CameraXConfig.Provider) {
            return (CameraXConfig.Provider) o2;
        }
        try {
            return (CameraXConfig.Provider) Class.forName(context.getApplicationContext().getResources().getString(R.string.androidx_camera_default_config_provider)).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Resources.NotFoundException | ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | NullPointerException | InvocationTargetException e) {
            Logger.e("CameraX", "Failed to retrieve default CameraXConfig.Provider from resources", e);
            return null;
        }
    }

    @NonNull
    public static ListenableFuture<CameraX> q() {
        ListenableFuture<CameraX> r;
        synchronized (m) {
            r = r();
        }
        return r;
    }

    @NonNull
    @GuardedBy("INSTANCE_LOCK")
    public static ListenableFuture<CameraX> r() {
        final CameraX cameraX = n;
        if (cameraX == null) {
            return Futures.immediateFailedFuture(new IllegalStateException("Must call CameraX.initialize() first"));
        }
        return Futures.transform(p, new Function() { // from class: androidx.camera.core.j
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                CameraX x;
                x = CameraX.x(CameraX.this, (Void) obj);
                return x;
            }
        }, CameraXExecutors.directExecutor());
    }

    @NonNull
    public static ListenableFuture<Void> shutdown() {
        ListenableFuture<Void> K;
        synchronized (m) {
            o = null;
            Logger.a();
            K = K();
        }
        return K;
    }

    @GuardedBy("INSTANCE_LOCK")
    public static void u(@NonNull final Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkState(n == null, "CameraX already initialized.");
        Preconditions.checkNotNull(o);
        final CameraX cameraX = new CameraX(o.getCameraXConfig());
        n = cameraX;
        p = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.t
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object D;
                D = CameraX.D(CameraX.this, context, completer);
                return D;
            }
        });
    }

    public static /* synthetic */ CameraXConfig w(CameraXConfig cameraXConfig) {
        return cameraXConfig;
    }

    public static /* synthetic */ CameraX x(CameraX cameraX, Void r1) {
        return cameraX;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void y(Executor executor, long j, CallbackToFutureAdapter.Completer completer) {
        s(executor, j, this.j, completer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void z(Context context, final Executor executor, final CallbackToFutureAdapter.Completer completer, final long j) {
        try {
            Application o2 = o(context);
            this.j = o2;
            if (o2 == null) {
                this.j = context.getApplicationContext();
            }
            CameraFactory.Provider cameraFactoryProvider = this.c.getCameraFactoryProvider(null);
            if (cameraFactoryProvider != null) {
                CameraThreadConfig create = CameraThreadConfig.create(this.d, this.e);
                CameraSelector availableCamerasLimiter = this.c.getAvailableCamerasLimiter(null);
                this.g = cameraFactoryProvider.newInstance(this.j, create, availableCamerasLimiter);
                CameraDeviceSurfaceManager.Provider deviceSurfaceManagerProvider = this.c.getDeviceSurfaceManagerProvider(null);
                if (deviceSurfaceManagerProvider != null) {
                    this.h = deviceSurfaceManagerProvider.newInstance(this.j, this.g.getCameraManager(), this.g.getAvailableCameraIds());
                    UseCaseConfigFactory.Provider useCaseConfigFactoryProvider = this.c.getUseCaseConfigFactoryProvider(null);
                    if (useCaseConfigFactoryProvider != null) {
                        this.i = useCaseConfigFactoryProvider.newInstance(this.j);
                        if (executor instanceof i) {
                            ((i) executor).c(this.g);
                        }
                        this.f617a.init(this.g);
                        if (DeviceQuirks.get(IncompleteCameraListQuirk.class) != null) {
                            CameraValidator.validateCameras(this.j, this.f617a, availableCamerasLimiter);
                        }
                        I();
                        completer.set(null);
                        return;
                    }
                    throw new InitializationException(new IllegalArgumentException("Invalid app configuration provided. Missing UseCaseConfigFactory."));
                }
                throw new InitializationException(new IllegalArgumentException("Invalid app configuration provided. Missing CameraDeviceSurfaceManager."));
            }
            throw new InitializationException(new IllegalArgumentException("Invalid app configuration provided. Missing CameraFactory."));
        } catch (InitializationException | CameraValidator.CameraIdListIncorrectException | RuntimeException e) {
            if (SystemClock.elapsedRealtime() - j < 2500) {
                Logger.w("CameraX", "Retry init. Start time " + j + " current time " + SystemClock.elapsedRealtime(), e);
                HandlerCompat.postDelayed(this.e, new Runnable() { // from class: androidx.camera.core.l
                    @Override // java.lang.Runnable
                    public final void run() {
                        CameraX.this.y(executor, j, completer);
                    }
                }, "retry_token", 500L);
                return;
            }
            I();
            if (e instanceof CameraValidator.CameraIdListIncorrectException) {
                Logger.e("CameraX", "The device might underreport the amount of the cameras. Finish the initialize task since we are already reaching the maximum number of retries.");
                completer.set(null);
            } else if (e instanceof InitializationException) {
                completer.setException(e);
            } else {
                completer.setException(new InitializationException(e));
            }
        }
    }

    public final void I() {
        synchronized (this.b) {
            this.k = c.INITIALIZED;
        }
    }

    @NonNull
    public final ListenableFuture<Void> J() {
        synchronized (this.b) {
            this.e.removeCallbacksAndMessages("retry_token");
            int i = b.f619a[this.k.ordinal()];
            if (i == 1) {
                this.k = c.SHUTDOWN;
                return Futures.immediateFuture(null);
            } else if (i != 2) {
                if (i == 3) {
                    this.k = c.SHUTDOWN;
                    this.l = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.q
                        @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                        public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                            Object F;
                            F = CameraX.this.F(completer);
                            return F;
                        }
                    });
                }
                return this.l;
            } else {
                throw new IllegalStateException("CameraX could not be shutdown when it is initializing.");
            }
        }
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CameraDeviceSurfaceManager getCameraDeviceSurfaceManager() {
        CameraDeviceSurfaceManager cameraDeviceSurfaceManager = this.h;
        if (cameraDeviceSurfaceManager != null) {
            return cameraDeviceSurfaceManager;
        }
        throw new IllegalStateException("CameraX not initialized yet.");
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CameraFactory getCameraFactory() {
        CameraFactory cameraFactory = this.g;
        if (cameraFactory != null) {
            return cameraFactory;
        }
        throw new IllegalStateException("CameraX not initialized yet.");
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CameraRepository getCameraRepository() {
        return this.f617a;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public UseCaseConfigFactory getDefaultConfigFactory() {
        UseCaseConfigFactory useCaseConfigFactory = this.i;
        if (useCaseConfigFactory != null) {
            return useCaseConfigFactory;
        }
        throw new IllegalStateException("CameraX not initialized yet.");
    }

    @UseExperimental(markerClass = ExperimentalAvailableCamerasLimiter.class)
    public final void s(@NonNull final Executor executor, final long j, @NonNull final Context context, @NonNull final CallbackToFutureAdapter.Completer<Void> completer) {
        executor.execute(new Runnable() { // from class: androidx.camera.core.u
            @Override // java.lang.Runnable
            public final void run() {
                CameraX.this.z(context, executor, completer, j);
            }
        });
    }

    public final ListenableFuture<Void> t(@NonNull final Context context) {
        ListenableFuture<Void> future;
        synchronized (this.b) {
            Preconditions.checkState(this.k == c.UNINITIALIZED, "CameraX.initInternal() should only be called once per instance");
            this.k = c.INITIALIZING;
            future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.s
                @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                    Object A;
                    A = CameraX.this.A(context, completer);
                    return A;
                }
            });
        }
        return future;
    }

    public final boolean v() {
        boolean z;
        synchronized (this.b) {
            z = this.k == c.INITIALIZED;
        }
        return z;
    }
}
