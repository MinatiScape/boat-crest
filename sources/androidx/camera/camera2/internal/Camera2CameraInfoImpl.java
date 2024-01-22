package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCharacteristics;
import android.util.Pair;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.experimental.UseExperimental;
import androidx.camera.camera2.internal.Camera2CameraInfoImpl;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.quirk.CameraQuirks;
import androidx.camera.camera2.interop.Camera2CameraInfo;
import androidx.camera.camera2.interop.ExperimentalCamera2Interop;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.ExperimentalExposureCompensation;
import androidx.camera.core.ExposureState;
import androidx.camera.core.Logger;
import androidx.camera.core.ZoomState;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.utils.CameraOrientationUtil;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
@UseExperimental(markerClass = ExperimentalCamera2Interop.class)
/* loaded from: classes.dex */
public final class Camera2CameraInfoImpl implements CameraInfoInternal {

    /* renamed from: a  reason: collision with root package name */
    public final String f500a;
    public final CameraCharacteristicsCompat b;
    @Nullable
    @GuardedBy("mLock")
    public Camera2CameraControlImpl e;
    @NonNull
    public final Quirks i;
    public final Object d = new Object();
    @Nullable
    @GuardedBy("mLock")
    public a<Integer> f = null;
    @Nullable
    @GuardedBy("mLock")
    public a<ZoomState> g = null;
    @Nullable
    @GuardedBy("mLock")
    public List<Pair<CameraCaptureCallback, Executor>> h = null;
    public final Camera2CameraInfo c = new Camera2CameraInfo(this);

    /* loaded from: classes.dex */
    public static class a<T> extends MediatorLiveData<T> {
        public LiveData<T> m;
        public T n;

        public a(T t) {
            this.n = t;
        }

        @Override // androidx.lifecycle.MediatorLiveData
        public <S> void addSource(@NonNull LiveData<S> liveData, @NonNull Observer<? super S> observer) {
            throw new UnsupportedOperationException();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void f(@NonNull LiveData<T> liveData) {
            LiveData liveData2 = (LiveData<T>) this.m;
            if (liveData2 != null) {
                super.removeSource(liveData2);
            }
            this.m = liveData;
            super.addSource(liveData, new Observer() { // from class: androidx.camera.camera2.internal.i0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    Camera2CameraInfoImpl.a.this.setValue(obj);
                }
            });
        }

        @Override // androidx.lifecycle.LiveData
        public T getValue() {
            LiveData<T> liveData = this.m;
            return liveData == null ? this.n : liveData.getValue();
        }
    }

    public Camera2CameraInfoImpl(@NonNull String str, @NonNull CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        this.f500a = (String) Preconditions.checkNotNull(str);
        this.b = cameraCharacteristicsCompat;
        this.i = CameraQuirks.get(str, cameraCharacteristicsCompat);
    }

    public int a() {
        Integer num = (Integer) this.b.get(CameraCharacteristics.SENSOR_ORIENTATION);
        Preconditions.checkNotNull(num);
        return num.intValue();
    }

    @Override // androidx.camera.core.impl.CameraInfoInternal
    public void addSessionCaptureCallback(@NonNull Executor executor, @NonNull CameraCaptureCallback cameraCaptureCallback) {
        synchronized (this.d) {
            Camera2CameraControlImpl camera2CameraControlImpl = this.e;
            if (camera2CameraControlImpl == null) {
                if (this.h == null) {
                    this.h = new ArrayList();
                }
                this.h.add(new Pair<>(cameraCaptureCallback, executor));
                return;
            }
            camera2CameraControlImpl.m(executor, cameraCaptureCallback);
        }
    }

    public int b() {
        Integer num = (Integer) this.b.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        Preconditions.checkNotNull(num);
        return num.intValue();
    }

    public void c(@NonNull Camera2CameraControlImpl camera2CameraControlImpl) {
        synchronized (this.d) {
            this.e = camera2CameraControlImpl;
            a<ZoomState> aVar = this.g;
            if (aVar != null) {
                aVar.f(camera2CameraControlImpl.getZoomControl().i());
            }
            a<Integer> aVar2 = this.f;
            if (aVar2 != null) {
                aVar2.f(this.e.getTorchControl().e());
            }
            List<Pair<CameraCaptureCallback, Executor>> list = this.h;
            if (list != null) {
                for (Pair<CameraCaptureCallback, Executor> pair : list) {
                    this.e.m((Executor) pair.second, (CameraCaptureCallback) pair.first);
                }
                this.h = null;
            }
        }
        d();
    }

    public final void d() {
        e();
    }

    public final void e() {
        String str;
        int b = b();
        if (b == 0) {
            str = "INFO_SUPPORTED_HARDWARE_LEVEL_LIMITED";
        } else if (b == 1) {
            str = "INFO_SUPPORTED_HARDWARE_LEVEL_FULL";
        } else if (b == 2) {
            str = "INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY";
        } else if (b == 3) {
            str = "INFO_SUPPORTED_HARDWARE_LEVEL_3";
        } else if (b != 4) {
            str = "Unknown value: " + b;
        } else {
            str = "INFO_SUPPORTED_HARDWARE_LEVEL_EXTERNAL";
        }
        Logger.i("Camera2CameraInfo", "Device Level: " + str);
    }

    @NonNull
    public Camera2CameraInfo getCamera2CameraInfo() {
        return this.c;
    }

    @NonNull
    public CameraCharacteristicsCompat getCameraCharacteristicsCompat() {
        return this.b;
    }

    @Override // androidx.camera.core.impl.CameraInfoInternal
    @NonNull
    public String getCameraId() {
        return this.f500a;
    }

    @Override // androidx.camera.core.impl.CameraInfoInternal
    @NonNull
    public Quirks getCameraQuirks() {
        return this.i;
    }

    @Override // androidx.camera.core.CameraInfo
    @NonNull
    @ExperimentalExposureCompensation
    public ExposureState getExposureState() {
        synchronized (this.d) {
            Camera2CameraControlImpl camera2CameraControlImpl = this.e;
            if (camera2CameraControlImpl == null) {
                return ExposureControl.e(this.b);
            }
            return camera2CameraControlImpl.getExposureControl().f();
        }
    }

    @Override // androidx.camera.core.CameraInfo
    @NonNull
    public String getImplementationType() {
        return b() == 2 ? CameraInfo.IMPLEMENTATION_TYPE_CAMERA2_LEGACY : CameraInfo.IMPLEMENTATION_TYPE_CAMERA2;
    }

    @Override // androidx.camera.core.impl.CameraInfoInternal
    @Nullable
    public Integer getLensFacing() {
        Integer num = (Integer) this.b.get(CameraCharacteristics.LENS_FACING);
        Preconditions.checkNotNull(num);
        int intValue = num.intValue();
        if (intValue != 0) {
            return intValue != 1 ? null : 1;
        }
        return 0;
    }

    @Override // androidx.camera.core.CameraInfo
    public int getSensorRotationDegrees(int i) {
        Integer valueOf = Integer.valueOf(a());
        int surfaceRotationToDegrees = CameraOrientationUtil.surfaceRotationToDegrees(i);
        Integer lensFacing = getLensFacing();
        boolean z = true;
        return CameraOrientationUtil.getRelativeImageRotation(surfaceRotationToDegrees, valueOf.intValue(), (lensFacing == null || 1 != lensFacing.intValue()) ? false : false);
    }

    @Override // androidx.camera.core.CameraInfo
    @NonNull
    public LiveData<Integer> getTorchState() {
        synchronized (this.d) {
            Camera2CameraControlImpl camera2CameraControlImpl = this.e;
            if (camera2CameraControlImpl == null) {
                if (this.f == null) {
                    this.f = new a<>(0);
                }
                return this.f;
            }
            a<Integer> aVar = this.f;
            if (aVar != null) {
                return aVar;
            }
            return camera2CameraControlImpl.getTorchControl().e();
        }
    }

    @Override // androidx.camera.core.CameraInfo
    @NonNull
    public LiveData<ZoomState> getZoomState() {
        synchronized (this.d) {
            Camera2CameraControlImpl camera2CameraControlImpl = this.e;
            if (camera2CameraControlImpl == null) {
                if (this.g == null) {
                    this.g = new a<>(i2.h(this.b));
                }
                return this.g;
            }
            a<ZoomState> aVar = this.g;
            if (aVar != null) {
                return aVar;
            }
            return camera2CameraControlImpl.getZoomControl().i();
        }
    }

    @Override // androidx.camera.core.CameraInfo
    public boolean hasFlashUnit() {
        Boolean bool = (Boolean) this.b.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
        Preconditions.checkNotNull(bool);
        return bool.booleanValue();
    }

    @Override // androidx.camera.core.impl.CameraInfoInternal
    public void removeSessionCaptureCallback(@NonNull CameraCaptureCallback cameraCaptureCallback) {
        synchronized (this.d) {
            Camera2CameraControlImpl camera2CameraControlImpl = this.e;
            if (camera2CameraControlImpl == null) {
                List<Pair<CameraCaptureCallback, Executor>> list = this.h;
                if (list == null) {
                    return;
                }
                Iterator<Pair<CameraCaptureCallback, Executor>> it = list.iterator();
                while (it.hasNext()) {
                    if (it.next().first == cameraCaptureCallback) {
                        it.remove();
                    }
                }
                return;
            }
            camera2CameraControlImpl.O(cameraCaptureCallback);
        }
    }

    @Override // androidx.camera.core.CameraInfo
    public int getSensorRotationDegrees() {
        return getSensorRotationDegrees(0);
    }
}
