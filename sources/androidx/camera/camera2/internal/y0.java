package androidx.camera.camera2.internal;

import android.graphics.Rect;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import androidx.annotation.NonNull;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.i2;
import androidx.camera.core.CameraControl;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
/* loaded from: classes.dex */
public final class y0 implements i2.b {

    /* renamed from: a  reason: collision with root package name */
    public final CameraCharacteristicsCompat f601a;
    public CallbackToFutureAdapter.Completer<Void> c;
    public Rect b = null;
    public Rect d = null;

    public y0(@NonNull CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        this.f601a = cameraCharacteristicsCompat;
    }

    @NonNull
    public static Rect e(@NonNull Rect rect, float f) {
        float width = rect.width() / f;
        float height = rect.height() / f;
        float width2 = (rect.width() - width) / 2.0f;
        float height2 = (rect.height() - height) / 2.0f;
        return new Rect((int) width2, (int) height2, (int) (width2 + width), (int) (height2 + height));
    }

    @Override // androidx.camera.camera2.internal.i2.b
    public void a(@NonNull Camera2ImplConfig.Builder builder) {
        Rect rect = this.b;
        if (rect != null) {
            builder.setCaptureRequestOption(CaptureRequest.SCALER_CROP_REGION, rect);
        }
    }

    @Override // androidx.camera.camera2.internal.i2.b
    public void b(float f, @NonNull CallbackToFutureAdapter.Completer<Void> completer) {
        this.b = e(f(), f);
        CallbackToFutureAdapter.Completer<Void> completer2 = this.c;
        if (completer2 != null) {
            completer2.setException(new CameraControl.OperationCanceledException("There is a new zoomRatio being set"));
        }
        this.d = this.b;
        this.c = completer;
    }

    @Override // androidx.camera.camera2.internal.i2.b
    public void c() {
        this.d = null;
        this.b = null;
        CallbackToFutureAdapter.Completer<Void> completer = this.c;
        if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
            this.c = null;
        }
    }

    @Override // androidx.camera.camera2.internal.i2.b
    @NonNull
    public Rect d() {
        Rect rect = this.b;
        return rect != null ? rect : f();
    }

    public final Rect f() {
        return (Rect) Preconditions.checkNotNull((Rect) this.f601a.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE));
    }

    @Override // androidx.camera.camera2.internal.i2.b
    public float getMaxZoom() {
        Float f = (Float) this.f601a.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM);
        if (f == null) {
            return 1.0f;
        }
        if (f.floatValue() < getMinZoom()) {
            return getMinZoom();
        }
        return f.floatValue();
    }

    @Override // androidx.camera.camera2.internal.i2.b
    public float getMinZoom() {
        return 1.0f;
    }

    @Override // androidx.camera.camera2.internal.i2.b
    public void onCaptureResult(@NonNull TotalCaptureResult totalCaptureResult) {
        if (this.c != null) {
            CaptureRequest request = totalCaptureResult.getRequest();
            Rect rect = request == null ? null : (Rect) request.get(CaptureRequest.SCALER_CROP_REGION);
            Rect rect2 = this.d;
            if (rect2 == null || !rect2.equals(rect)) {
                return;
            }
            this.c.set(null);
            this.c = null;
            this.d = null;
        }
    }
}
