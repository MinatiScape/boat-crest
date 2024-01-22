package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCharacteristics;
import android.util.Range;
import android.util.Rational;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.core.ExperimentalExposureCompensation;
import androidx.camera.core.ExposureState;
@ExperimentalExposureCompensation
/* loaded from: classes.dex */
public class c1 implements ExposureState {

    /* renamed from: a  reason: collision with root package name */
    public final Object f516a = new Object();
    public final CameraCharacteristicsCompat b;
    @GuardedBy("mLock")
    public int c;

    public c1(CameraCharacteristicsCompat cameraCharacteristicsCompat, int i) {
        this.b = cameraCharacteristicsCompat;
        this.c = i;
    }

    public void a(int i) {
        synchronized (this.f516a) {
            this.c = i;
        }
    }

    @Override // androidx.camera.core.ExposureState
    public int getExposureCompensationIndex() {
        int i;
        synchronized (this.f516a) {
            i = this.c;
        }
        return i;
    }

    @Override // androidx.camera.core.ExposureState
    @NonNull
    public Range<Integer> getExposureCompensationRange() {
        return (Range) this.b.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE);
    }

    @Override // androidx.camera.core.ExposureState
    @NonNull
    public Rational getExposureCompensationStep() {
        if (!isExposureCompensationSupported()) {
            return Rational.ZERO;
        }
        return (Rational) this.b.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP);
    }

    @Override // androidx.camera.core.ExposureState
    public boolean isExposureCompensationSupported() {
        Range range = (Range) this.b.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE);
        return (range == null || ((Integer) range.getLower()).intValue() == 0 || ((Integer) range.getUpper()).intValue() == 0) ? false : true;
    }
}
