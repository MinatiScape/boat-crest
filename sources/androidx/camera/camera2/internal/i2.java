package androidx.camera.camera2.internal;

import android.graphics.Rect;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Build;
import android.os.Looper;
import androidx.annotation.FloatRange;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.core.CameraControl;
import androidx.camera.core.ZoomState;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.internal.ImmutableZoomState;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class i2 {

    /* renamed from: a  reason: collision with root package name */
    public final Camera2CameraControlImpl f567a;
    public final Executor b;
    @GuardedBy("mCurrentZoomState")
    public final j2 c;
    public final MutableLiveData<ZoomState> d;
    @NonNull
    public final b e;
    public boolean f = false;
    public Camera2CameraControlImpl.CaptureResultListener g = new a();

    /* loaded from: classes.dex */
    public class a implements Camera2CameraControlImpl.CaptureResultListener {
        public a() {
        }

        @Override // androidx.camera.camera2.internal.Camera2CameraControlImpl.CaptureResultListener
        public boolean onCaptureResult(@NonNull TotalCaptureResult totalCaptureResult) {
            i2.this.e.onCaptureResult(totalCaptureResult);
            return false;
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        void a(@NonNull Camera2ImplConfig.Builder builder);

        void b(float f, @NonNull CallbackToFutureAdapter.Completer<Void> completer);

        void c();

        @NonNull
        Rect d();

        float getMaxZoom();

        float getMinZoom();

        void onCaptureResult(@NonNull TotalCaptureResult totalCaptureResult);
    }

    public i2(@NonNull Camera2CameraControlImpl camera2CameraControlImpl, @NonNull CameraCharacteristicsCompat cameraCharacteristicsCompat, @NonNull Executor executor) {
        this.f567a = camera2CameraControlImpl;
        this.b = executor;
        b f = f(cameraCharacteristicsCompat);
        this.e = f;
        j2 j2Var = new j2(f.getMaxZoom(), f.getMinZoom());
        this.c = j2Var;
        j2Var.d(1.0f);
        this.d = new MutableLiveData<>(ImmutableZoomState.create(j2Var));
        camera2CameraControlImpl.l(this.g);
    }

    public static b f(@NonNull CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        if (j(cameraCharacteristicsCompat)) {
            return new androidx.camera.camera2.internal.a(cameraCharacteristicsCompat);
        }
        return new y0(cameraCharacteristicsCompat);
    }

    public static ZoomState h(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        b f = f(cameraCharacteristicsCompat);
        j2 j2Var = new j2(f.getMaxZoom(), f.getMinZoom());
        j2Var.d(1.0f);
        return ImmutableZoomState.create(j2Var);
    }

    public static boolean j(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        return Build.VERSION.SDK_INT >= 30 && cameraCharacteristicsCompat.get(CameraCharacteristics.CONTROL_ZOOM_RATIO_RANGE) != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object l(final ZoomState zoomState, final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.b.execute(new Runnable() { // from class: androidx.camera.camera2.internal.h2
            @Override // java.lang.Runnable
            public final void run() {
                i2.this.k(completer, zoomState);
            }
        });
        return "setLinearZoom";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object n(final ZoomState zoomState, final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.b.execute(new Runnable() { // from class: androidx.camera.camera2.internal.g2
            @Override // java.lang.Runnable
            public final void run() {
                i2.this.m(completer, zoomState);
            }
        });
        return "setZoomRatio";
    }

    public void e(@NonNull Camera2ImplConfig.Builder builder) {
        this.e.a(builder);
    }

    @NonNull
    public Rect g() {
        return this.e.d();
    }

    public LiveData<ZoomState> i() {
        return this.d;
    }

    public void o(boolean z) {
        ZoomState create;
        if (this.f == z) {
            return;
        }
        this.f = z;
        if (z) {
            return;
        }
        synchronized (this.c) {
            this.c.d(1.0f);
            create = ImmutableZoomState.create(this.c);
        }
        s(create);
        this.e.c();
        this.f567a.R();
    }

    @NonNull
    public ListenableFuture<Void> p(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        final ZoomState create;
        synchronized (this.c) {
            try {
                this.c.c(f);
                create = ImmutableZoomState.create(this.c);
            } catch (IllegalArgumentException e) {
                return Futures.immediateFailedFuture(e);
            }
        }
        s(create);
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.f2
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object l;
                l = i2.this.l(create, completer);
                return l;
            }
        });
    }

    @NonNull
    public ListenableFuture<Void> q(float f) {
        final ZoomState create;
        synchronized (this.c) {
            try {
                this.c.d(f);
                create = ImmutableZoomState.create(this.c);
            } catch (IllegalArgumentException e) {
                return Futures.immediateFailedFuture(e);
            }
        }
        s(create);
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.e2
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object n;
                n = i2.this.n(create, completer);
                return n;
            }
        });
    }

    /* renamed from: r */
    public final void m(@NonNull CallbackToFutureAdapter.Completer<Void> completer, @NonNull ZoomState zoomState) {
        ZoomState create;
        if (!this.f) {
            synchronized (this.c) {
                this.c.d(1.0f);
                create = ImmutableZoomState.create(this.c);
            }
            s(create);
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
            return;
        }
        s(zoomState);
        this.e.b(zoomState.getZoomRatio(), completer);
        this.f567a.R();
    }

    public final void s(ZoomState zoomState) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.d.setValue(zoomState);
        } else {
            this.d.postValue(zoomState);
        }
    }
}
