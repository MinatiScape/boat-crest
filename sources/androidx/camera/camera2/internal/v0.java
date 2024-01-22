package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.view.Surface;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.experimental.UseExperimental;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.impl.CameraEventCallbacks;
import androidx.camera.camera2.internal.SynchronizedCaptureSession;
import androidx.camera.camera2.internal.a2;
import androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.camera.camera2.internal.compat.workaround.StillCaptureFlow;
import androidx.camera.camera2.internal.n0;
import androidx.camera.camera2.interop.ExperimentalCamera2Interop;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CancellationException;
/* loaded from: classes.dex */
public final class v0 {
    @Nullable
    public SynchronizedCaptureSessionOpener e;
    @Nullable
    public SynchronizedCaptureSession f;
    @Nullable
    public volatile SessionConfig g;
    @GuardedBy("mStateLock")
    public d l;
    @GuardedBy("mStateLock")
    public ListenableFuture<Void> m;
    @GuardedBy("mStateLock")
    public CallbackToFutureAdapter.Completer<Void> n;

    /* renamed from: a  reason: collision with root package name */
    public final Object f589a = new Object();
    public final List<CaptureConfig> b = new ArrayList();
    public final CameraCaptureSession.CaptureCallback c = new a(this);
    @NonNull
    public volatile Config h = OptionsBundle.emptyBundle();
    @NonNull
    public CameraEventCallbacks i = CameraEventCallbacks.createEmptyCallback();
    public Map<DeferrableSurface, Surface> j = new HashMap();
    @GuardedBy("mStateLock")
    public List<DeferrableSurface> k = Collections.emptyList();
    public final StillCaptureFlow o = new StillCaptureFlow();
    public final e d = new e();

    /* loaded from: classes.dex */
    public class a extends CameraCaptureSession.CaptureCallback {
        public a(v0 v0Var) {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull TotalCaptureResult totalCaptureResult) {
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
            v0.this.e.e();
            synchronized (v0.this.f589a) {
                int i = c.f591a[v0.this.l.ordinal()];
                if ((i == 4 || i == 6 || i == 7) && !(th instanceof CancellationException)) {
                    Logger.w("CaptureSession", "Opening session with fail " + v0.this.l, th);
                    v0.this.g();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static /* synthetic */ class c {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f591a;

        static {
            int[] iArr = new int[d.values().length];
            f591a = iArr;
            try {
                iArr[d.UNINITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f591a[d.INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f591a[d.GET_SURFACE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f591a[d.OPENING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f591a[d.OPENED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f591a[d.CLOSED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f591a[d.RELEASING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f591a[d.RELEASED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum d {
        UNINITIALIZED,
        INITIALIZED,
        GET_SURFACE,
        OPENING,
        OPENED,
        CLOSED,
        RELEASING,
        RELEASED
    }

    /* loaded from: classes.dex */
    public final class e extends SynchronizedCaptureSession.StateCallback {
        public e() {
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void p(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
            synchronized (v0.this.f589a) {
                switch (c.f591a[v0.this.l.ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                    case 8:
                        throw new IllegalStateException("onConfigureFailed() should not be possible in state: " + v0.this.l);
                    case 4:
                    case 6:
                    case 7:
                        v0.this.g();
                        break;
                }
                Logger.e("CaptureSession", "CameraCaptureSession.onConfigureFailed() " + v0.this.l);
            }
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void q(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
            synchronized (v0.this.f589a) {
                switch (c.f591a[v0.this.l.ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                    case 8:
                        throw new IllegalStateException("onConfigured() should not be possible in state: " + v0.this.l);
                    case 4:
                        v0 v0Var = v0.this;
                        v0Var.l = d.OPENED;
                        v0Var.f = synchronizedCaptureSession;
                        if (v0Var.g != null) {
                            List<CaptureConfig> onEnableSession = v0.this.i.createComboCallback().onEnableSession();
                            if (!onEnableSession.isEmpty()) {
                                v0 v0Var2 = v0.this;
                                v0Var2.j(v0Var2.v(onEnableSession));
                            }
                        }
                        Logger.d("CaptureSession", "Attempting to send capture request onConfigured");
                        v0.this.m();
                        v0.this.l();
                        break;
                    case 6:
                        v0.this.f = synchronizedCaptureSession;
                        break;
                    case 7:
                        synchronizedCaptureSession.close();
                        break;
                }
                Logger.d("CaptureSession", "CameraCaptureSession.onConfigured() mState=" + v0.this.l);
            }
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void r(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
            synchronized (v0.this.f589a) {
                if (c.f591a[v0.this.l.ordinal()] != 1) {
                    Logger.d("CaptureSession", "CameraCaptureSession.onReady() " + v0.this.l);
                } else {
                    throw new IllegalStateException("onReady() should not be possible in state: " + v0.this.l);
                }
            }
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void s(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
            synchronized (v0.this.f589a) {
                if (v0.this.l != d.UNINITIALIZED) {
                    Logger.d("CaptureSession", "onSessionFinished()");
                    v0.this.g();
                } else {
                    throw new IllegalStateException("onSessionFinished() should not be possible in state: " + v0.this.l);
                }
            }
        }
    }

    public v0() {
        this.l = d.UNINITIALIZED;
        this.l = d.INITIALIZED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(CameraCaptureSession cameraCaptureSession, int i, boolean z) {
        synchronized (this.f589a) {
            if (this.l == d.OPENED) {
                m();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object p(CallbackToFutureAdapter.Completer completer) throws Exception {
        String str;
        synchronized (this.f589a) {
            Preconditions.checkState(this.n == null, "Release completer expected to be null");
            this.n = completer;
            str = "Release[session=" + this + "]";
        }
        return str;
    }

    @NonNull
    public static Config q(List<CaptureConfig> list) {
        MutableOptionsBundle create = MutableOptionsBundle.create();
        for (CaptureConfig captureConfig : list) {
            Config implementationOptions = captureConfig.getImplementationOptions();
            for (Config.Option<?> option : implementationOptions.listOptions()) {
                Object retrieveOption = implementationOptions.retrieveOption(option, null);
                if (create.containsOption(option)) {
                    Object retrieveOption2 = create.retrieveOption(option, null);
                    if (!Objects.equals(retrieveOption2, retrieveOption)) {
                        Logger.d("CaptureSession", "Detect conflicting option " + option.getId() + " : " + retrieveOption + " != " + retrieveOption2);
                    }
                } else {
                    create.insertOption(option, retrieveOption);
                }
            }
        }
        return create;
    }

    public void d() {
        if (this.b.isEmpty()) {
            return;
        }
        for (CaptureConfig captureConfig : this.b) {
            for (CameraCaptureCallback cameraCaptureCallback : captureConfig.getCameraCaptureCallbacks()) {
                cameraCaptureCallback.onCaptureCancelled();
            }
        }
        this.b.clear();
    }

    public void e() {
        synchronized (this.f589a) {
            int i = c.f591a[this.l.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i == 5) {
                                if (this.g != null) {
                                    List<CaptureConfig> onDisableSession = this.i.createComboCallback().onDisableSession();
                                    if (!onDisableSession.isEmpty()) {
                                        try {
                                            k(v(onDisableSession));
                                        } catch (IllegalStateException e2) {
                                            Logger.e("CaptureSession", "Unable to issue the request before close the capture session", e2);
                                        }
                                    }
                                }
                            }
                        }
                        SynchronizedCaptureSessionOpener synchronizedCaptureSessionOpener = this.e;
                        Preconditions.checkNotNull(synchronizedCaptureSessionOpener, "The Opener shouldn't null in state:" + this.l);
                        this.e.e();
                        this.l = d.CLOSED;
                        this.g = null;
                    } else {
                        SynchronizedCaptureSessionOpener synchronizedCaptureSessionOpener2 = this.e;
                        Preconditions.checkNotNull(synchronizedCaptureSessionOpener2, "The Opener shouldn't null in state:" + this.l);
                        this.e.e();
                    }
                }
                this.l = d.RELEASED;
            } else {
                throw new IllegalStateException("close() should not be possible in state: " + this.l);
            }
        }
    }

    public final CameraCaptureSession.CaptureCallback f(List<CameraCaptureCallback> list, CameraCaptureSession.CaptureCallback... captureCallbackArr) {
        ArrayList arrayList = new ArrayList(list.size() + captureCallbackArr.length);
        for (CameraCaptureCallback cameraCaptureCallback : list) {
            arrayList.add(r0.a(cameraCaptureCallback));
        }
        Collections.addAll(arrayList, captureCallbackArr);
        return Camera2CaptureCallbacks.a(arrayList);
    }

    @GuardedBy("mStateLock")
    public void g() {
        d dVar = this.l;
        d dVar2 = d.RELEASED;
        if (dVar == dVar2) {
            Logger.d("CaptureSession", "Skipping finishClose due to being state RELEASED.");
            return;
        }
        this.l = dVar2;
        this.f = null;
        CallbackToFutureAdapter.Completer<Void> completer = this.n;
        if (completer != null) {
            completer.set(null);
            this.n = null;
        }
    }

    public List<CaptureConfig> h() {
        List<CaptureConfig> unmodifiableList;
        synchronized (this.f589a) {
            unmodifiableList = Collections.unmodifiableList(this.b);
        }
        return unmodifiableList;
    }

    @Nullable
    public SessionConfig i() {
        SessionConfig sessionConfig;
        synchronized (this.f589a) {
            sessionConfig = this.g;
        }
        return sessionConfig;
    }

    public void j(List<CaptureConfig> list) {
        boolean z;
        if (list.isEmpty()) {
            return;
        }
        try {
            n0 n0Var = new n0();
            ArrayList arrayList = new ArrayList();
            Logger.d("CaptureSession", "Issuing capture request.");
            boolean z2 = false;
            for (CaptureConfig captureConfig : list) {
                if (captureConfig.getSurfaces().isEmpty()) {
                    Logger.d("CaptureSession", "Skipping issuing empty capture request.");
                } else {
                    Iterator<DeferrableSurface> it = captureConfig.getSurfaces().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = true;
                            break;
                        }
                        DeferrableSurface next = it.next();
                        if (!this.j.containsKey(next)) {
                            Logger.d("CaptureSession", "Skipping capture request with invalid surface: " + next);
                            z = false;
                            break;
                        }
                    }
                    if (z) {
                        if (captureConfig.getTemplateType() == 2) {
                            z2 = true;
                        }
                        CaptureConfig.Builder from = CaptureConfig.Builder.from(captureConfig);
                        if (this.g != null) {
                            from.addImplementationOptions(this.g.getRepeatingCaptureConfig().getImplementationOptions());
                        }
                        from.addImplementationOptions(this.h);
                        from.addImplementationOptions(captureConfig.getImplementationOptions());
                        CaptureRequest b2 = k0.b(from.build(), this.f.e(), this.j);
                        if (b2 == null) {
                            Logger.d("CaptureSession", "Skipping issuing request without surface.");
                            return;
                        }
                        ArrayList arrayList2 = new ArrayList();
                        for (CameraCaptureCallback cameraCaptureCallback : captureConfig.getCameraCaptureCallbacks()) {
                            r0.b(cameraCaptureCallback, arrayList2);
                        }
                        n0Var.a(b2, arrayList2);
                        arrayList.add(b2);
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                if (this.o.shouldStopRepeatingBeforeCapture(arrayList, z2)) {
                    this.f.l();
                    n0Var.c(new n0.a() { // from class: androidx.camera.camera2.internal.s0
                        @Override // androidx.camera.camera2.internal.n0.a
                        public final void a(CameraCaptureSession cameraCaptureSession, int i, boolean z3) {
                            v0.this.n(cameraCaptureSession, i, z3);
                        }
                    });
                }
                this.f.j(arrayList, n0Var);
                return;
            }
            Logger.d("CaptureSession", "Skipping issuing burst request due to no valid request elements");
        } catch (CameraAccessException e2) {
            Logger.e("CaptureSession", "Unable to access camera: " + e2.getMessage());
            Thread.dumpStack();
        }
    }

    public void k(List<CaptureConfig> list) {
        synchronized (this.f589a) {
            switch (c.f591a[this.l.ordinal()]) {
                case 1:
                    throw new IllegalStateException("issueCaptureRequests() should not be possible in state: " + this.l);
                case 2:
                case 3:
                case 4:
                    this.b.addAll(list);
                    break;
                case 5:
                    this.b.addAll(list);
                    l();
                    break;
                case 6:
                case 7:
                case 8:
                    throw new IllegalStateException("Cannot issue capture request on a closed/released session.");
            }
        }
    }

    public void l() {
        if (this.b.isEmpty()) {
            return;
        }
        try {
            j(this.b);
        } finally {
            this.b.clear();
        }
    }

    @GuardedBy("mStateLock")
    public void m() {
        if (this.g == null) {
            Logger.d("CaptureSession", "Skipping issueRepeatingCaptureRequests for no configuration case.");
            return;
        }
        CaptureConfig repeatingCaptureConfig = this.g.getRepeatingCaptureConfig();
        if (repeatingCaptureConfig.getSurfaces().isEmpty()) {
            Logger.d("CaptureSession", "Skipping issueRepeatingCaptureRequests for no surface.");
            try {
                this.f.l();
                return;
            } catch (CameraAccessException e2) {
                Logger.e("CaptureSession", "Unable to access camera: " + e2.getMessage());
                Thread.dumpStack();
                return;
            }
        }
        try {
            Logger.d("CaptureSession", "Issuing request for session.");
            CaptureConfig.Builder from = CaptureConfig.Builder.from(repeatingCaptureConfig);
            this.h = q(this.i.createComboCallback().onRepeating());
            from.addImplementationOptions(this.h);
            CaptureRequest b2 = k0.b(from.build(), this.f.e(), this.j);
            if (b2 == null) {
                Logger.d("CaptureSession", "Skipping issuing empty request for session.");
            } else {
                this.f.f(b2, f(repeatingCaptureConfig.getCameraCaptureCallbacks(), this.c));
            }
        } catch (CameraAccessException e3) {
            Logger.e("CaptureSession", "Unable to access camera: " + e3.getMessage());
            Thread.dumpStack();
        }
    }

    @NonNull
    public ListenableFuture<Void> r(@NonNull final SessionConfig sessionConfig, @NonNull final CameraDevice cameraDevice, @NonNull SynchronizedCaptureSessionOpener synchronizedCaptureSessionOpener) {
        synchronized (this.f589a) {
            if (c.f591a[this.l.ordinal()] != 2) {
                Logger.e("CaptureSession", "Open not allowed in state: " + this.l);
                return Futures.immediateFailedFuture(new IllegalStateException("open() should not allow the state: " + this.l));
            }
            this.l = d.GET_SURFACE;
            ArrayList arrayList = new ArrayList(sessionConfig.getSurfaces());
            this.k = arrayList;
            this.e = synchronizedCaptureSessionOpener;
            FutureChain transformAsync = FutureChain.from(synchronizedCaptureSessionOpener.d(arrayList, 5000L)).transformAsync(new AsyncFunction() { // from class: androidx.camera.camera2.internal.t0
                @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
                public final ListenableFuture apply(Object obj) {
                    ListenableFuture o;
                    o = v0.this.o(sessionConfig, cameraDevice, (List) obj);
                    return o;
                }
            }, this.e.b());
            Futures.addCallback(transformAsync, new b(), this.e.b());
            return Futures.nonCancellationPropagating(transformAsync);
        }
    }

    @NonNull
    @UseExperimental(markerClass = ExperimentalCamera2Interop.class)
    /* renamed from: s */
    public final ListenableFuture<Void> o(@NonNull List<Surface> list, @NonNull SessionConfig sessionConfig, @NonNull CameraDevice cameraDevice) {
        synchronized (this.f589a) {
            int i = c.f591a[this.l.ordinal()];
            if (i != 1 && i != 2) {
                if (i == 3) {
                    this.j.clear();
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        this.j.put(this.k.get(i2), list.get(i2));
                    }
                    ArrayList<Surface> arrayList = new ArrayList(new HashSet(list));
                    this.l = d.OPENING;
                    Logger.d("CaptureSession", "Opening capture session.");
                    SynchronizedCaptureSession.StateCallback u = a2.u(this.d, new a2.a(sessionConfig.getSessionStateCallbacks()));
                    CameraEventCallbacks cameraEventCallback = new Camera2ImplConfig(sessionConfig.getImplementationOptions()).getCameraEventCallback(CameraEventCallbacks.createEmptyCallback());
                    this.i = cameraEventCallback;
                    List<CaptureConfig> onPresetSession = cameraEventCallback.createComboCallback().onPresetSession();
                    CaptureConfig.Builder from = CaptureConfig.Builder.from(sessionConfig.getRepeatingCaptureConfig());
                    for (CaptureConfig captureConfig : onPresetSession) {
                        from.addImplementationOptions(captureConfig.getImplementationOptions());
                    }
                    ArrayList arrayList2 = new ArrayList();
                    for (Surface surface : arrayList) {
                        arrayList2.add(new OutputConfigurationCompat(surface));
                    }
                    SessionConfigurationCompat a2 = this.e.a(0, arrayList2, u);
                    try {
                        CaptureRequest c2 = k0.c(from.build(), cameraDevice);
                        if (c2 != null) {
                            a2.setSessionParameters(c2);
                        }
                        return this.e.c(cameraDevice, a2, this.k);
                    } catch (CameraAccessException e2) {
                        return Futures.immediateFailedFuture(e2);
                    }
                } else if (i != 5) {
                    return Futures.immediateFailedFuture(new CancellationException("openCaptureSession() not execute in state: " + this.l));
                }
            }
            return Futures.immediateFailedFuture(new IllegalStateException("openCaptureSession() should not be possible in state: " + this.l));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004d A[Catch: all -> 0x00a6, TryCatch #1 {, blocks: (B:4:0x0003, B:5:0x000d, B:28:0x009f, B:7:0x0012, B:10:0x0018, B:14:0x0024, B:13:0x001d, B:15:0x0029, B:17:0x004d, B:18:0x0051, B:20:0x0055, B:21:0x0060, B:22:0x0062, B:24:0x0064, B:25:0x0081, B:26:0x0086, B:27:0x009e), top: B:36:0x0003, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0055 A[Catch: all -> 0x00a6, TryCatch #1 {, blocks: (B:4:0x0003, B:5:0x000d, B:28:0x009f, B:7:0x0012, B:10:0x0018, B:14:0x0024, B:13:0x001d, B:15:0x0029, B:17:0x004d, B:18:0x0051, B:20:0x0055, B:21:0x0060, B:22:0x0062, B:24:0x0064, B:25:0x0081, B:26:0x0086, B:27:0x009e), top: B:36:0x0003, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.google.common.util.concurrent.ListenableFuture<java.lang.Void> t(boolean r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.f589a
            monitor-enter(r0)
            int[] r1 = androidx.camera.camera2.internal.v0.c.f591a     // Catch: java.lang.Throwable -> La6
            androidx.camera.camera2.internal.v0$d r2 = r3.l     // Catch: java.lang.Throwable -> La6
            int r2 = r2.ordinal()     // Catch: java.lang.Throwable -> La6
            r1 = r1[r2]     // Catch: java.lang.Throwable -> La6
            switch(r1) {
                case 1: goto L86;
                case 2: goto L81;
                case 3: goto L64;
                case 4: goto L29;
                case 5: goto L12;
                case 6: goto L12;
                case 7: goto L51;
                default: goto L10;
            }     // Catch: java.lang.Throwable -> La6
        L10:
            goto L9f
        L12:
            androidx.camera.camera2.internal.SynchronizedCaptureSession r1 = r3.f     // Catch: java.lang.Throwable -> La6
            if (r1 == 0) goto L29
            if (r4 == 0) goto L24
            r1.d()     // Catch: android.hardware.camera2.CameraAccessException -> L1c java.lang.Throwable -> La6
            goto L24
        L1c:
            r4 = move-exception
            java.lang.String r1 = "CaptureSession"
            java.lang.String r2 = "Unable to abort captures."
            androidx.camera.core.Logger.e(r1, r2, r4)     // Catch: java.lang.Throwable -> La6
        L24:
            androidx.camera.camera2.internal.SynchronizedCaptureSession r4 = r3.f     // Catch: java.lang.Throwable -> La6
            r4.close()     // Catch: java.lang.Throwable -> La6
        L29:
            androidx.camera.camera2.internal.v0$d r4 = androidx.camera.camera2.internal.v0.d.RELEASING     // Catch: java.lang.Throwable -> La6
            r3.l = r4     // Catch: java.lang.Throwable -> La6
            androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener r4 = r3.e     // Catch: java.lang.Throwable -> La6
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La6
            r1.<init>()     // Catch: java.lang.Throwable -> La6
            java.lang.String r2 = "The Opener shouldn't null in state:"
            r1.append(r2)     // Catch: java.lang.Throwable -> La6
            androidx.camera.camera2.internal.v0$d r2 = r3.l     // Catch: java.lang.Throwable -> La6
            r1.append(r2)     // Catch: java.lang.Throwable -> La6
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> La6
            androidx.core.util.Preconditions.checkNotNull(r4, r1)     // Catch: java.lang.Throwable -> La6
            androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener r4 = r3.e     // Catch: java.lang.Throwable -> La6
            boolean r4 = r4.e()     // Catch: java.lang.Throwable -> La6
            if (r4 == 0) goto L51
            r3.g()     // Catch: java.lang.Throwable -> La6
            goto L9f
        L51:
            com.google.common.util.concurrent.ListenableFuture<java.lang.Void> r4 = r3.m     // Catch: java.lang.Throwable -> La6
            if (r4 != 0) goto L60
            androidx.camera.camera2.internal.u0 r4 = new androidx.camera.camera2.internal.u0     // Catch: java.lang.Throwable -> La6
            r4.<init>()     // Catch: java.lang.Throwable -> La6
            com.google.common.util.concurrent.ListenableFuture r4 = androidx.concurrent.futures.CallbackToFutureAdapter.getFuture(r4)     // Catch: java.lang.Throwable -> La6
            r3.m = r4     // Catch: java.lang.Throwable -> La6
        L60:
            com.google.common.util.concurrent.ListenableFuture<java.lang.Void> r4 = r3.m     // Catch: java.lang.Throwable -> La6
            monitor-exit(r0)     // Catch: java.lang.Throwable -> La6
            return r4
        L64:
            androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener r4 = r3.e     // Catch: java.lang.Throwable -> La6
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La6
            r1.<init>()     // Catch: java.lang.Throwable -> La6
            java.lang.String r2 = "The Opener shouldn't null in state:"
            r1.append(r2)     // Catch: java.lang.Throwable -> La6
            androidx.camera.camera2.internal.v0$d r2 = r3.l     // Catch: java.lang.Throwable -> La6
            r1.append(r2)     // Catch: java.lang.Throwable -> La6
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> La6
            androidx.core.util.Preconditions.checkNotNull(r4, r1)     // Catch: java.lang.Throwable -> La6
            androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener r4 = r3.e     // Catch: java.lang.Throwable -> La6
            r4.e()     // Catch: java.lang.Throwable -> La6
        L81:
            androidx.camera.camera2.internal.v0$d r4 = androidx.camera.camera2.internal.v0.d.RELEASED     // Catch: java.lang.Throwable -> La6
            r3.l = r4     // Catch: java.lang.Throwable -> La6
            goto L9f
        L86:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> La6
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La6
            r1.<init>()     // Catch: java.lang.Throwable -> La6
            java.lang.String r2 = "release() should not be possible in state: "
            r1.append(r2)     // Catch: java.lang.Throwable -> La6
            androidx.camera.camera2.internal.v0$d r2 = r3.l     // Catch: java.lang.Throwable -> La6
            r1.append(r2)     // Catch: java.lang.Throwable -> La6
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> La6
            r4.<init>(r1)     // Catch: java.lang.Throwable -> La6
            throw r4     // Catch: java.lang.Throwable -> La6
        L9f:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> La6
            r4 = 0
            com.google.common.util.concurrent.ListenableFuture r4 = androidx.camera.core.impl.utils.futures.Futures.immediateFuture(r4)
            return r4
        La6:
            r4 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> La6
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.v0.t(boolean):com.google.common.util.concurrent.ListenableFuture");
    }

    public void u(SessionConfig sessionConfig) {
        synchronized (this.f589a) {
            switch (c.f591a[this.l.ordinal()]) {
                case 1:
                    throw new IllegalStateException("setSessionConfig() should not be possible in state: " + this.l);
                case 2:
                case 3:
                case 4:
                    this.g = sessionConfig;
                    break;
                case 5:
                    this.g = sessionConfig;
                    if (!this.j.keySet().containsAll(sessionConfig.getSurfaces())) {
                        Logger.e("CaptureSession", "Does not have the proper configured lists");
                        return;
                    }
                    Logger.d("CaptureSession", "Attempting to submit CaptureRequest after setting");
                    m();
                    break;
                case 6:
                case 7:
                case 8:
                    throw new IllegalStateException("Session configuration cannot be set on a closed/released session.");
            }
        }
    }

    public List<CaptureConfig> v(List<CaptureConfig> list) {
        ArrayList arrayList = new ArrayList();
        for (CaptureConfig captureConfig : list) {
            CaptureConfig.Builder from = CaptureConfig.Builder.from(captureConfig);
            from.setTemplateType(1);
            for (DeferrableSurface deferrableSurface : this.g.getRepeatingCaptureConfig().getSurfaces()) {
                from.addSurface(deferrableSurface);
            }
            arrayList.add(from.build());
        }
        return arrayList;
    }
}
