package androidx.camera.camera2.internal;

import android.graphics.Rect;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.util.Range;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.i2;
import androidx.camera.core.CameraControl;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
@RequiresApi(30)
/* loaded from: classes.dex */
public final class a implements i2.b {

    /* renamed from: a  reason: collision with root package name */
    public final CameraCharacteristicsCompat f510a;
    public final Range<Float> b;
    public CallbackToFutureAdapter.Completer<Void> d;
    public float c = 1.0f;
    public float e = 1.0f;

    public a(@NonNull CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        this.f510a = cameraCharacteristicsCompat;
        this.b = (Range) cameraCharacteristicsCompat.get(CameraCharacteristics.CONTROL_ZOOM_RATIO_RANGE);
    }

    @Override // androidx.camera.camera2.internal.i2.b
    public void a(@NonNull Camera2ImplConfig.Builder builder) {
        builder.setCaptureRequestOption(CaptureRequest.CONTROL_ZOOM_RATIO, Float.valueOf(this.c));
    }

    @Override // androidx.camera.camera2.internal.i2.b
    public void b(float f, @NonNull CallbackToFutureAdapter.Completer<Void> completer) {
        this.c = f;
        CallbackToFutureAdapter.Completer<Void> completer2 = this.d;
        if (completer2 != null) {
            completer2.setException(new CameraControl.OperationCanceledException("There is a new zoomRatio being set"));
        }
        this.e = this.c;
        this.d = completer;
    }

    @Override // androidx.camera.camera2.internal.i2.b
    public void c() {
        this.c = 1.0f;
        CallbackToFutureAdapter.Completer<Void> completer = this.d;
        if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
            this.d = null;
        }
    }

    @Override // androidx.camera.camera2.internal.i2.b
    @NonNull
    public Rect d() {
        return (Rect) Preconditions.checkNotNull((Rect) this.f510a.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE));
    }

    @Override // androidx.camera.camera2.internal.i2.b
    public float getMaxZoom() {
        return this.b.getUpper().floatValue();
    }

    @Override // androidx.camera.camera2.internal.i2.b
    public float getMinZoom() {
        return this.b.getLower().floatValue();
    }

    @Override // androidx.camera.camera2.internal.i2.b
    public void onCaptureResult(@NonNull TotalCaptureResult totalCaptureResult) {
        if (this.d != null) {
            CaptureRequest request = totalCaptureResult.getRequest();
            Float f = request == null ? null : (Float) request.get(CaptureRequest.CONTROL_ZOOM_RATIO);
            if (f == null) {
                return;
            }
            if (this.e == f.floatValue()) {
                this.d.set(null);
                this.d = null;
            }
        }
    }
}
