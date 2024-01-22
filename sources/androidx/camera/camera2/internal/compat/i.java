package androidx.camera.camera2.internal.compat;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Handler;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.camera.camera2.internal.compat.CameraDeviceCompat;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.core.util.Preconditions;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
@RequiresApi(21)
/* loaded from: classes.dex */
public class i implements CameraManagerCompat.CameraManagerCompatImpl {

    /* renamed from: a  reason: collision with root package name */
    public final CameraManager f529a;
    public final Object b;

    /* loaded from: classes.dex */
    public static final class a {
        @GuardedBy("mWrapperMap")

        /* renamed from: a  reason: collision with root package name */
        public final Map<CameraManager.AvailabilityCallback, CameraManagerCompat.a> f530a = new HashMap();
        public final Handler b;

        public a(@NonNull Handler handler) {
            this.b = handler;
        }
    }

    public i(@NonNull Context context, @Nullable Object obj) {
        this.f529a = (CameraManager) context.getSystemService("camera");
        this.b = obj;
    }

    public static i a(@NonNull Context context, @NonNull Handler handler) {
        return new i(context, new a(handler));
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    @NonNull
    public CameraCharacteristics getCameraCharacteristics(@NonNull String str) throws CameraAccessExceptionCompat {
        try {
            return this.f529a.getCameraCharacteristics(str);
        } catch (CameraAccessException e) {
            throw CameraAccessExceptionCompat.toCameraAccessExceptionCompat(e);
        }
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    @NonNull
    public String[] getCameraIdList() throws CameraAccessExceptionCompat {
        try {
            return this.f529a.getCameraIdList();
        } catch (CameraAccessException e) {
            throw CameraAccessExceptionCompat.toCameraAccessExceptionCompat(e);
        }
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    @NonNull
    public CameraManager getCameraManager() {
        return this.f529a;
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    @RequiresPermission("android.permission.CAMERA")
    public void openCamera(@NonNull String str, @NonNull Executor executor, @NonNull CameraDevice.StateCallback stateCallback) throws CameraAccessExceptionCompat {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(stateCallback);
        try {
            this.f529a.openCamera(str, new CameraDeviceCompat.b(executor, stateCallback), ((a) this.b).b);
        } catch (CameraAccessException e) {
            throw CameraAccessExceptionCompat.toCameraAccessExceptionCompat(e);
        }
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    public void registerAvailabilityCallback(@NonNull Executor executor, @NonNull CameraManager.AvailabilityCallback availabilityCallback) {
        if (executor != null) {
            CameraManagerCompat.a aVar = null;
            a aVar2 = (a) this.b;
            if (availabilityCallback != null) {
                synchronized (aVar2.f530a) {
                    aVar = aVar2.f530a.get(availabilityCallback);
                    if (aVar == null) {
                        aVar = new CameraManagerCompat.a(executor, availabilityCallback);
                        aVar2.f530a.put(availabilityCallback, aVar);
                    }
                }
            }
            this.f529a.registerAvailabilityCallback(aVar, aVar2.b);
            return;
        }
        throw new IllegalArgumentException("executor was null");
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    public void unregisterAvailabilityCallback(@NonNull CameraManager.AvailabilityCallback availabilityCallback) {
        CameraManagerCompat.a aVar;
        if (availabilityCallback != null) {
            a aVar2 = (a) this.b;
            synchronized (aVar2.f530a) {
                aVar = aVar2.f530a.remove(availabilityCallback);
            }
        } else {
            aVar = null;
        }
        if (aVar != null) {
            aVar.a();
        }
        this.f529a.unregisterAvailabilityCallback(aVar);
    }
}
