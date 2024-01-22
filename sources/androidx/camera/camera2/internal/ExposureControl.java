package androidx.camera.camera2.internal;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.util.Range;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.experimental.UseExperimental;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.core.CameraControl;
import androidx.camera.core.ExperimentalExposureCompensation;
import androidx.camera.core.ExposureState;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
@UseExperimental(markerClass = ExperimentalExposureCompensation.class)
/* loaded from: classes.dex */
public class ExposureControl {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final Camera2CameraControlImpl f507a;
    @NonNull
    public final c1 b;
    @NonNull
    public final Executor c;
    public boolean d = false;
    @Nullable
    public CallbackToFutureAdapter.Completer<Integer> e;
    @Nullable
    public Camera2CameraControlImpl.CaptureResultListener f;

    public ExposureControl(@NonNull Camera2CameraControlImpl camera2CameraControlImpl, @NonNull CameraCharacteristicsCompat cameraCharacteristicsCompat, @NonNull Executor executor) {
        this.f507a = camera2CameraControlImpl;
        this.b = new c1(cameraCharacteristicsCompat, 0);
        this.c = executor;
    }

    public static ExposureState e(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        return new c1(cameraCharacteristicsCompat, 0);
    }

    public static /* synthetic */ boolean g(int i, CallbackToFutureAdapter.Completer completer, TotalCaptureResult totalCaptureResult) {
        Integer num = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AE_STATE);
        Integer num2 = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AE_EXPOSURE_COMPENSATION);
        if (num != null && num2 != null) {
            int intValue = num.intValue();
            if ((intValue == 2 || intValue == 3 || intValue == 4) && num2.intValue() == i) {
                completer.set(Integer.valueOf(i));
                return true;
            }
            return false;
        } else if (num2 == null || num2.intValue() != i) {
            return false;
        } else {
            completer.set(Integer.valueOf(i));
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(final CallbackToFutureAdapter.Completer completer, final int i) {
        if (!this.d) {
            this.b.a(0);
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
            return;
        }
        d();
        Preconditions.checkState(this.e == null, "mRunningCompleter should be null when starting set a new exposure compensation value");
        Preconditions.checkState(this.f == null, "mRunningCaptureResultListener should be null when starting set a new exposure compensation value");
        Camera2CameraControlImpl.CaptureResultListener captureResultListener = new Camera2CameraControlImpl.CaptureResultListener() { // from class: androidx.camera.camera2.internal.z0
            @Override // androidx.camera.camera2.internal.Camera2CameraControlImpl.CaptureResultListener
            public final boolean onCaptureResult(TotalCaptureResult totalCaptureResult) {
                boolean g;
                g = ExposureControl.g(i, completer, totalCaptureResult);
                return g;
            }
        };
        this.f = captureResultListener;
        this.e = completer;
        this.f507a.l(captureResultListener);
        this.f507a.R();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object i(final int i, final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.c.execute(new Runnable() { // from class: androidx.camera.camera2.internal.b1
            @Override // java.lang.Runnable
            public final void run() {
                ExposureControl.this.h(completer, i);
            }
        });
        return "setExposureCompensationIndex[" + i + "]";
    }

    public final void d() {
        CallbackToFutureAdapter.Completer<Integer> completer = this.e;
        if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException("Cancelled by another setExposureCompensationIndex()"));
            this.e = null;
        }
        Camera2CameraControlImpl.CaptureResultListener captureResultListener = this.f;
        if (captureResultListener != null) {
            this.f507a.N(captureResultListener);
            this.f = null;
        }
    }

    @NonNull
    public ExposureState f() {
        return this.b;
    }

    public void j(boolean z) {
        if (z == this.d) {
            return;
        }
        this.d = z;
        if (z) {
            return;
        }
        this.b.a(0);
        d();
    }

    public void k(@NonNull Camera2ImplConfig.Builder builder) {
        builder.setCaptureRequestOption(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(this.b.getExposureCompensationIndex()));
    }

    @NonNull
    public ListenableFuture<Integer> l(final int i) {
        if (!this.b.isExposureCompensationSupported()) {
            return Futures.immediateFailedFuture(new IllegalArgumentException("ExposureCompensation is not supported"));
        }
        Range<Integer> exposureCompensationRange = this.b.getExposureCompensationRange();
        if (!exposureCompensationRange.contains((Range<Integer>) Integer.valueOf(i))) {
            return Futures.immediateFailedFuture(new IllegalArgumentException("Requested ExposureCompensation " + i + " is not within valid range [" + exposureCompensationRange.getUpper() + ".." + exposureCompensationRange.getLower() + "]"));
        }
        this.b.a(i);
        return Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.a1
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object i2;
                i2 = ExposureControl.this.i(i, completer);
                return i2;
            }
        }));
    }
}
