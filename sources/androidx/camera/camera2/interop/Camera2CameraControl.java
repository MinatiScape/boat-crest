package androidx.camera.camera2.interop;

import android.hardware.camera2.TotalCaptureResult;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.camera2.interop.CaptureRequestOptions;
import androidx.camera.core.CameraControl;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
@ExperimentalCamera2Interop
/* loaded from: classes.dex */
public final class Camera2CameraControl {
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String TAG_KEY = "Camera2CameraControl";
    public final Camera2CameraControlImpl c;
    public final Executor d;
    public CallbackToFutureAdapter.Completer<Void> g;

    /* renamed from: a  reason: collision with root package name */
    public boolean f604a = false;
    public boolean b = false;
    public final Object e = new Object();
    @GuardedBy("mLock")
    public Camera2ImplConfig.Builder f = new Camera2ImplConfig.Builder();
    public final Camera2CameraControlImpl.CaptureResultListener h = new Camera2CameraControlImpl.CaptureResultListener() { // from class: androidx.camera.camera2.interop.a
        @Override // androidx.camera.camera2.internal.Camera2CameraControlImpl.CaptureResultListener
        public final boolean onCaptureResult(TotalCaptureResult totalCaptureResult) {
            boolean o;
            o = Camera2CameraControl.this.o(totalCaptureResult);
            return o;
        }
    };

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Camera2CameraControl(@NonNull Camera2CameraControlImpl camera2CameraControlImpl, @NonNull Executor executor) {
        this.c = camera2CameraControlImpl;
        this.d = executor;
    }

    @NonNull
    public static Camera2CameraControl from(@NonNull CameraControl cameraControl) {
        Preconditions.checkArgument(cameraControl instanceof Camera2CameraControlImpl, "CameraControl doesn't contain Camera2 implementation.");
        return ((Camera2CameraControlImpl) cameraControl).getCamera2CameraControl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object l(final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.d.execute(new Runnable() { // from class: androidx.camera.camera2.interop.g
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControl.this.k(completer);
            }
        });
        return "addCaptureRequestOptions";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object n(final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.d.execute(new Runnable() { // from class: androidx.camera.camera2.interop.e
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControl.this.m(completer);
            }
        });
        return "clearCaptureRequestOptions";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ boolean o(android.hardware.camera2.TotalCaptureResult r3) {
        /*
            r2 = this;
            androidx.concurrent.futures.CallbackToFutureAdapter$Completer<java.lang.Void> r0 = r2.g
            r1 = 0
            if (r0 == 0) goto L30
            android.hardware.camera2.CaptureRequest r3 = r3.getRequest()
            java.lang.Object r3 = r3.getTag()
            boolean r0 = r3 instanceof androidx.camera.core.impl.TagBundle
            if (r0 == 0) goto L30
            androidx.camera.core.impl.TagBundle r3 = (androidx.camera.core.impl.TagBundle) r3
            java.lang.String r0 = "Camera2CameraControl"
            java.lang.Integer r3 = r3.getTag(r0)
            if (r3 == 0) goto L30
            androidx.concurrent.futures.CallbackToFutureAdapter$Completer<java.lang.Void> r0 = r2.g
            int r0 = r0.hashCode()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L30
            androidx.concurrent.futures.CallbackToFutureAdapter$Completer<java.lang.Void> r3 = r2.g
            r2.g = r1
            goto L31
        L30:
            r3 = r1
        L31:
            if (r3 == 0) goto L36
            r3.set(r1)
        L36:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.interop.Camera2CameraControl.o(android.hardware.camera2.TotalCaptureResult):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object r(final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.d.execute(new Runnable() { // from class: androidx.camera.camera2.interop.f
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControl.this.q(completer);
            }
        });
        return "setCaptureRequestOptions";
    }

    @NonNull
    public ListenableFuture<Void> addCaptureRequestOptions(@NonNull CaptureRequestOptions captureRequestOptions) {
        i(captureRequestOptions);
        return Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.interop.d
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object l;
                l = Camera2CameraControl.this.l(completer);
                return l;
            }
        }));
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public ListenableFuture<Void> clearCaptureRequestOptions() {
        j();
        return Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.interop.b
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object n;
                n = Camera2CameraControl.this.n(completer);
                return n;
            }
        }));
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Camera2ImplConfig getCamera2ImplConfig() {
        Camera2ImplConfig build;
        synchronized (this.e) {
            if (this.g != null) {
                this.f.getMutableConfig().insertOption(Camera2ImplConfig.CAPTURE_REQUEST_TAG_OPTION, Integer.valueOf(this.g.hashCode()));
            }
            build = this.f.build();
        }
        return build;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Camera2CameraControlImpl.CaptureResultListener getCaptureRequestListener() {
        return this.h;
    }

    @NonNull
    public CaptureRequestOptions getCaptureRequestOptions() {
        CaptureRequestOptions build;
        synchronized (this.e) {
            build = CaptureRequestOptions.Builder.from(this.f.build()).build();
        }
        return build;
    }

    public final void i(@NonNull CaptureRequestOptions captureRequestOptions) {
        synchronized (this.e) {
            for (Config.Option<?> option : captureRequestOptions.listOptions()) {
                this.f.getMutableConfig().insertOption(option, captureRequestOptions.retrieveOption(option));
            }
        }
    }

    public final void j() {
        synchronized (this.e) {
            this.f = new Camera2ImplConfig.Builder();
        }
    }

    /* renamed from: s */
    public final void p(boolean z) {
        if (this.f604a == z) {
            return;
        }
        this.f604a = z;
        if (z) {
            if (this.b) {
                u();
                return;
            }
            return;
        }
        j();
        CallbackToFutureAdapter.Completer<Void> completer = this.g;
        if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException("The camera control has became inactive."));
            this.g = null;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void setActive(final boolean z) {
        this.d.execute(new Runnable() { // from class: androidx.camera.camera2.interop.h
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControl.this.p(z);
            }
        });
    }

    @NonNull
    public ListenableFuture<Void> setCaptureRequestOptions(@NonNull CaptureRequestOptions captureRequestOptions) {
        j();
        i(captureRequestOptions);
        return Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.interop.c
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object r;
                r = Camera2CameraControl.this.r(completer);
                return r;
            }
        }));
    }

    /* renamed from: t */
    public final void q(CallbackToFutureAdapter.Completer<Void> completer) {
        this.b = true;
        CallbackToFutureAdapter.Completer<Void> completer2 = this.g;
        if (completer2 == null) {
            completer2 = null;
        }
        this.g = completer;
        if (this.f604a) {
            u();
        }
        if (completer2 != null) {
            completer2.setException(new CameraControl.OperationCanceledException("Camera2CameraControl was updated with new options."));
        }
    }

    public final void u() {
        this.c.updateSessionConfig();
        this.b = false;
    }
}
