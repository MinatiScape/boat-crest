package androidx.camera.camera2.internal;

import android.graphics.Rect;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.util.ArrayMap;
import android.util.Rational;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.experimental.UseExperimental;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.workaround.AeFpsRange;
import androidx.camera.camera2.internal.compat.workaround.AutoFlashAEModeDisabler;
import androidx.camera.camera2.interop.Camera2CameraControl;
import androidx.camera.camera2.interop.CaptureRequestOptions;
import androidx.camera.camera2.interop.ExperimentalCamera2Interop;
import androidx.camera.core.CameraControl;
import androidx.camera.core.ExperimentalExposureCompensation;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureFailure;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
@UseExperimental(markerClass = ExperimentalCamera2Interop.class)
/* loaded from: classes.dex */
public class Camera2CameraControlImpl implements CameraControlInternal {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public final b f496a;
    public final Executor b;
    public final Object c = new Object();
    public final CameraCharacteristicsCompat d;
    public final CameraControlInternal.ControlUpdateCallback e;
    public final SessionConfig.Builder f;
    public volatile Rational g;
    public final l1 h;
    public final i2 i;
    public final d2 j;
    public final ExposureControl k;
    public final Camera2CameraControl l;
    public final AeFpsRange m;
    @GuardedBy("mLock")
    public int n;
    public volatile boolean o;
    public volatile int p;
    public final AutoFlashAEModeDisabler q;
    public final a r;

    /* loaded from: classes.dex */
    public interface CaptureResultListener {
        boolean onCaptureResult(@NonNull TotalCaptureResult totalCaptureResult);
    }

    /* loaded from: classes.dex */
    public static final class a extends CameraCaptureCallback {

        /* renamed from: a  reason: collision with root package name */
        public Set<CameraCaptureCallback> f497a = new HashSet();
        public Map<CameraCaptureCallback, Executor> b = new ArrayMap();

        public void d(@NonNull Executor executor, @NonNull CameraCaptureCallback cameraCaptureCallback) {
            this.f497a.add(cameraCaptureCallback);
            this.b.put(cameraCaptureCallback, executor);
        }

        public void h(@NonNull CameraCaptureCallback cameraCaptureCallback) {
            this.f497a.remove(cameraCaptureCallback);
            this.b.remove(cameraCaptureCallback);
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCancelled() {
            for (final CameraCaptureCallback cameraCaptureCallback : this.f497a) {
                try {
                    this.b.get(cameraCaptureCallback).execute(new Runnable() { // from class: androidx.camera.camera2.internal.o
                        @Override // java.lang.Runnable
                        public final void run() {
                            CameraCaptureCallback.this.onCaptureCancelled();
                        }
                    });
                } catch (RejectedExecutionException e) {
                    Logger.e("Camera2CameraControlImp", "Executor rejected to invoke onCaptureCancelled.", e);
                }
            }
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCompleted(@NonNull final CameraCaptureResult cameraCaptureResult) {
            for (final CameraCaptureCallback cameraCaptureCallback : this.f497a) {
                try {
                    this.b.get(cameraCaptureCallback).execute(new Runnable() { // from class: androidx.camera.camera2.internal.q
                        @Override // java.lang.Runnable
                        public final void run() {
                            CameraCaptureCallback.this.onCaptureCompleted(cameraCaptureResult);
                        }
                    });
                } catch (RejectedExecutionException e) {
                    Logger.e("Camera2CameraControlImp", "Executor rejected to invoke onCaptureCompleted.", e);
                }
            }
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureFailed(@NonNull final CameraCaptureFailure cameraCaptureFailure) {
            for (final CameraCaptureCallback cameraCaptureCallback : this.f497a) {
                try {
                    this.b.get(cameraCaptureCallback).execute(new Runnable() { // from class: androidx.camera.camera2.internal.p
                        @Override // java.lang.Runnable
                        public final void run() {
                            CameraCaptureCallback.this.onCaptureFailed(cameraCaptureFailure);
                        }
                    });
                } catch (RejectedExecutionException e) {
                    Logger.e("Camera2CameraControlImp", "Executor rejected to invoke onCaptureFailed.", e);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends CameraCaptureSession.CaptureCallback {

        /* renamed from: a  reason: collision with root package name */
        public final Set<CaptureResultListener> f498a = new HashSet();
        public final Executor b;

        public b(@NonNull Executor executor) {
            this.b = executor;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void c(TotalCaptureResult totalCaptureResult) {
            HashSet hashSet = new HashSet();
            for (CaptureResultListener captureResultListener : this.f498a) {
                if (captureResultListener.onCaptureResult(totalCaptureResult)) {
                    hashSet.add(captureResultListener);
                }
            }
            if (hashSet.isEmpty()) {
                return;
            }
            this.f498a.removeAll(hashSet);
        }

        public void b(@NonNull CaptureResultListener captureResultListener) {
            this.f498a.add(captureResultListener);
        }

        public void d(@NonNull CaptureResultListener captureResultListener) {
            this.f498a.remove(captureResultListener);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull final TotalCaptureResult totalCaptureResult) {
            this.b.execute(new Runnable() { // from class: androidx.camera.camera2.internal.r
                @Override // java.lang.Runnable
                public final void run() {
                    Camera2CameraControlImpl.b.this.c(totalCaptureResult);
                }
            });
        }
    }

    public Camera2CameraControlImpl(@NonNull CameraCharacteristicsCompat cameraCharacteristicsCompat, @NonNull ScheduledExecutorService scheduledExecutorService, @NonNull Executor executor, @NonNull CameraControlInternal.ControlUpdateCallback controlUpdateCallback, @NonNull Quirks quirks) {
        SessionConfig.Builder builder = new SessionConfig.Builder();
        this.f = builder;
        this.g = null;
        this.n = 0;
        this.o = false;
        this.p = 2;
        this.q = new AutoFlashAEModeDisabler();
        a aVar = new a();
        this.r = aVar;
        this.d = cameraCharacteristicsCompat;
        this.e = controlUpdateCallback;
        this.b = executor;
        b bVar = new b(executor);
        this.f496a = bVar;
        builder.setTemplateType(q());
        builder.addRepeatingCameraCaptureCallback(q0.a(bVar));
        builder.addRepeatingCameraCaptureCallback(aVar);
        this.k = new ExposureControl(this, cameraCharacteristicsCompat, executor);
        this.h = new l1(this, scheduledExecutorService, executor);
        this.i = new i2(this, cameraCharacteristicsCompat, executor);
        this.j = new d2(this, cameraCharacteristicsCompat, executor);
        this.m = new AeFpsRange(quirks);
        this.l = new Camera2CameraControl(this, executor);
        executor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.h
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControlImpl.this.G();
            }
        });
        updateSessionConfig();
    }

    public static /* synthetic */ void C() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void D(Executor executor, CameraCaptureCallback cameraCaptureCallback) {
        this.r.d(executor, cameraCaptureCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E(boolean z, boolean z2) {
        this.h.j(z, z2);
    }

    public static /* synthetic */ void F() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void G() {
        l(this.l.getCaptureRequestListener());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void H(CameraCaptureCallback cameraCaptureCallback) {
        this.r.h(cameraCaptureCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void J(CallbackToFutureAdapter.Completer completer) {
        this.h.O(completer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object K(final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.b.execute(new Runnable() { // from class: androidx.camera.camera2.internal.k
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControlImpl.this.J(completer);
            }
        });
        return "triggerAePrecapture";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void L(CallbackToFutureAdapter.Completer completer) {
        this.h.P(completer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object M(final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.b.execute(new Runnable() { // from class: androidx.camera.camera2.internal.j
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControlImpl.this.L(completer);
            }
        });
        return "triggerAf";
    }

    public final boolean A() {
        return y() > 0;
    }

    public final boolean B(int i, int[] iArr) {
        for (int i2 : iArr) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    public void N(@NonNull CaptureResultListener captureResultListener) {
        this.f496a.d(captureResultListener);
    }

    public void O(@NonNull final CameraCaptureCallback cameraCaptureCallback) {
        this.b.execute(new Runnable() { // from class: androidx.camera.camera2.internal.i
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControlImpl.this.H(cameraCaptureCallback);
            }
        });
    }

    public void P(boolean z) {
        this.h.J(z);
        this.i.o(z);
        this.j.h(z);
        this.k.j(z);
        this.l.setActive(z);
    }

    /* renamed from: Q */
    public void I(List<CaptureConfig> list) {
        this.e.onCameraControlCaptureRequests(list);
    }

    public void R() {
        this.f.setImplementationOptions(u());
        Object captureRequestTag = this.l.getCamera2ImplConfig().getCaptureRequestTag(null);
        if (captureRequestTag != null && (captureRequestTag instanceof Integer)) {
            this.f.addTag(Camera2CameraControl.TAG_KEY, (Integer) captureRequestTag);
        }
        this.e.onCameraControlUpdateSessionConfig(this.f.build());
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public void addInteropConfig(@NonNull Config config) {
        this.l.addCaptureRequestOptions(CaptureRequestOptions.Builder.from(config).build()).addListener(new Runnable() { // from class: androidx.camera.camera2.internal.e
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControlImpl.C();
            }
        }, CameraXExecutors.directExecutor());
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public void cancelAfAeTrigger(final boolean z, final boolean z2) {
        if (!A()) {
            Logger.w("Camera2CameraControlImp", "Camera is not active.");
        } else {
            this.b.execute(new Runnable() { // from class: androidx.camera.camera2.internal.n
                @Override // java.lang.Runnable
                public final void run() {
                    Camera2CameraControlImpl.this.E(z, z2);
                }
            });
        }
    }

    @Override // androidx.camera.core.CameraControl
    @NonNull
    public ListenableFuture<Void> cancelFocusAndMetering() {
        if (!A()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(this.h.k());
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public void clearInteropConfig() {
        this.l.clearCaptureRequestOptions().addListener(new Runnable() { // from class: androidx.camera.camera2.internal.d
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControlImpl.F();
            }
        }, CameraXExecutors.directExecutor());
    }

    @Override // androidx.camera.core.CameraControl
    @NonNull
    public ListenableFuture<Void> enableTorch(boolean z) {
        if (!A()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(this.j.c(z));
    }

    @NonNull
    public Camera2CameraControl getCamera2CameraControl() {
        return this.l;
    }

    @NonNull
    public ExposureControl getExposureControl() {
        return this.k;
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public int getFlashMode() {
        return this.p;
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    @NonNull
    public Config getInteropConfig() {
        return this.l.getCamera2ImplConfig();
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    @NonNull
    public Rect getSensorRect() {
        return (Rect) Preconditions.checkNotNull((Rect) this.d.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE));
    }

    @NonNull
    public d2 getTorchControl() {
        return this.j;
    }

    @NonNull
    public i2 getZoomControl() {
        return this.i;
    }

    public void l(@NonNull CaptureResultListener captureResultListener) {
        this.f496a.b(captureResultListener);
    }

    public void m(@NonNull final Executor executor, @NonNull final CameraCaptureCallback cameraCaptureCallback) {
        this.b.execute(new Runnable() { // from class: androidx.camera.camera2.internal.m
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControlImpl.this.D(executor, cameraCaptureCallback);
            }
        });
    }

    public void n() {
        synchronized (this.c) {
            int i = this.n;
            if (i != 0) {
                this.n = i - 1;
            } else {
                throw new IllegalStateException("Decrementing use count occurs more times than incrementing");
            }
        }
    }

    public void o(boolean z) {
        this.o = z;
        if (!z) {
            CaptureConfig.Builder builder = new CaptureConfig.Builder();
            builder.setTemplateType(q());
            builder.setUseRepeatingSurface(true);
            Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
            builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(v(1)));
            builder2.setCaptureRequestOption(CaptureRequest.FLASH_MODE, 0);
            builder.addImplementationOptions(builder2.build());
            I(Collections.singletonList(builder.build()));
        }
        R();
    }

    @NonNull
    public Rect p() {
        return this.i.g();
    }

    public int q() {
        return 1;
    }

    public int r() {
        Integer num = (Integer) this.d.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AE);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public int s() {
        Integer num = (Integer) this.d.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public void setDefaultRequestBuilder(@NonNull CaptureRequest.Builder builder) {
        this.h.K(builder);
    }

    @Override // androidx.camera.core.impl.CameraControlInternal, androidx.camera.core.CameraControl
    @NonNull
    @ExperimentalExposureCompensation
    public ListenableFuture<Integer> setExposureCompensationIndex(int i) {
        if (!A()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return this.k.l(i);
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public void setFlashMode(int i) {
        if (!A()) {
            Logger.w("Camera2CameraControlImp", "Camera is not active.");
            return;
        }
        this.p = i;
        updateSessionConfig();
    }

    @Override // androidx.camera.core.CameraControl
    @NonNull
    public ListenableFuture<Void> setLinearZoom(float f) {
        if (!A()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(this.i.p(f));
    }

    public void setPreviewAspectRatio(@Nullable Rational rational) {
        this.g = rational;
    }

    @Override // androidx.camera.core.CameraControl
    @NonNull
    public ListenableFuture<Void> setZoomRatio(float f) {
        if (!A()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(this.i.q(f));
    }

    @Override // androidx.camera.core.CameraControl
    @NonNull
    public ListenableFuture<FocusMeteringResult> startFocusAndMetering(@NonNull FocusMeteringAction focusMeteringAction) {
        if (!A()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(this.h.M(focusMeteringAction, this.g));
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public void submitCaptureRequests(@NonNull final List<CaptureConfig> list) {
        if (!A()) {
            Logger.w("Camera2CameraControlImp", "Camera is not active.");
        } else {
            this.b.execute(new Runnable() { // from class: androidx.camera.camera2.internal.l
                @Override // java.lang.Runnable
                public final void run() {
                    Camera2CameraControlImpl.this.I(list);
                }
            });
        }
    }

    public int t() {
        Integer num = (Integer) this.d.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    @NonNull
    public ListenableFuture<CameraCaptureResult> triggerAePrecapture() {
        if (!A()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.f
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object K;
                K = Camera2CameraControlImpl.this.K(completer);
                return K;
            }
        }));
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    @NonNull
    public ListenableFuture<CameraCaptureResult> triggerAf() {
        if (!A()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.c
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object M;
                M = Camera2CameraControlImpl.this.M(completer);
                return M;
            }
        }));
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0070 A[LOOP:0: B:12:0x006a->B:14:0x0070, LOOP_END] */
    @androidx.annotation.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.camera.core.impl.Config u() {
        /*
            r7 = this;
            androidx.camera.camera2.impl.Camera2ImplConfig$Builder r0 = new androidx.camera.camera2.impl.Camera2ImplConfig$Builder
            r0.<init>()
            android.hardware.camera2.CaptureRequest$Key r1 = android.hardware.camera2.CaptureRequest.CONTROL_MODE
            r2 = 1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            r0.setCaptureRequestOption(r1, r3)
            androidx.camera.camera2.internal.l1 r1 = r7.h
            r1.i(r0)
            androidx.camera.camera2.internal.compat.workaround.AeFpsRange r1 = r7.m
            r1.addAeFpsRangeOptions(r0)
            androidx.camera.camera2.internal.i2 r1 = r7.i
            r1.e(r0)
            boolean r1 = r7.o
            r3 = 2
            if (r1 == 0) goto L2d
            android.hardware.camera2.CaptureRequest$Key r1 = android.hardware.camera2.CaptureRequest.FLASH_MODE
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r0.setCaptureRequestOption(r1, r3)
            goto L33
        L2d:
            int r1 = r7.p
            if (r1 == 0) goto L37
            if (r1 == r2) goto L35
        L33:
            r1 = r2
            goto L3d
        L35:
            r1 = 3
            goto L3d
        L37:
            androidx.camera.camera2.internal.compat.workaround.AutoFlashAEModeDisabler r1 = r7.q
            int r1 = r1.getCorrectedAeMode(r3)
        L3d:
            android.hardware.camera2.CaptureRequest$Key r3 = android.hardware.camera2.CaptureRequest.CONTROL_AE_MODE
            int r1 = r7.v(r1)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r0.setCaptureRequestOption(r3, r1)
            android.hardware.camera2.CaptureRequest$Key r1 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_MODE
            int r2 = r7.x(r2)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.setCaptureRequestOption(r1, r2)
            androidx.camera.camera2.internal.ExposureControl r1 = r7.k
            r1.k(r0)
            androidx.camera.camera2.interop.Camera2CameraControl r1 = r7.l
            androidx.camera.camera2.impl.Camera2ImplConfig r1 = r1.getCamera2ImplConfig()
            java.util.Set r2 = r1.listOptions()
            java.util.Iterator r2 = r2.iterator()
        L6a:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L84
            java.lang.Object r3 = r2.next()
            androidx.camera.core.impl.Config$Option r3 = (androidx.camera.core.impl.Config.Option) r3
            androidx.camera.core.impl.MutableConfig r4 = r0.getMutableConfig()
            androidx.camera.core.impl.Config$OptionPriority r5 = androidx.camera.core.impl.Config.OptionPriority.ALWAYS_OVERRIDE
            java.lang.Object r6 = r1.retrieveOption(r3)
            r4.insertOption(r3, r5, r6)
            goto L6a
        L84:
            androidx.camera.camera2.impl.Camera2ImplConfig r0 = r0.build()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.Camera2CameraControlImpl.u():androidx.camera.core.impl.Config");
    }

    public void updateSessionConfig() {
        this.b.execute(new Runnable() { // from class: androidx.camera.camera2.internal.g
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControlImpl.this.R();
            }
        });
    }

    public final int v(int i) {
        int[] iArr = (int[]) this.d.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES);
        if (iArr == null) {
            return 0;
        }
        return B(i, iArr) ? i : B(1, iArr) ? 1 : 0;
    }

    public int w(int i) {
        int[] iArr = (int[]) this.d.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
        if (iArr == null) {
            return 0;
        }
        if (B(i, iArr)) {
            return i;
        }
        if (B(4, iArr)) {
            return 4;
        }
        return B(1, iArr) ? 1 : 0;
    }

    public final int x(int i) {
        int[] iArr = (int[]) this.d.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES);
        if (iArr == null) {
            return 0;
        }
        return B(i, iArr) ? i : B(1, iArr) ? 1 : 0;
    }

    @VisibleForTesting
    public int y() {
        int i;
        synchronized (this.c) {
            i = this.n;
        }
        return i;
    }

    public void z() {
        synchronized (this.c) {
            this.n++;
        }
    }
}
