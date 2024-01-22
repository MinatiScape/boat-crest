package androidx.camera.camera2.internal.compat;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Handler;
import android.util.ArrayMap;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.annotation.RestrictTo;
import androidx.camera.core.impl.utils.MainThreadAsyncHandler;
import java.util.Map;
import java.util.concurrent.Executor;
@RequiresApi(21)
/* loaded from: classes.dex */
public final class CameraManagerCompat {

    /* renamed from: a  reason: collision with root package name */
    public final CameraManagerCompatImpl f523a;
    @GuardedBy("mCameraCharacteristicsMap")
    public final Map<String, CameraCharacteristicsCompat> b = new ArrayMap(4);

    /* loaded from: classes.dex */
    public interface CameraManagerCompatImpl {
        @NonNull
        static CameraManagerCompatImpl from(@NonNull Context context, @NonNull Handler handler) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 29) {
                return new h(context);
            }
            if (i >= 28) {
                return g.b(context);
            }
            return i.a(context, handler);
        }

        @NonNull
        CameraCharacteristics getCameraCharacteristics(@NonNull String str) throws CameraAccessExceptionCompat;

        @NonNull
        String[] getCameraIdList() throws CameraAccessExceptionCompat;

        @NonNull
        CameraManager getCameraManager();

        @RequiresPermission("android.permission.CAMERA")
        void openCamera(@NonNull String str, @NonNull Executor executor, @NonNull CameraDevice.StateCallback stateCallback) throws CameraAccessExceptionCompat;

        void registerAvailabilityCallback(@NonNull Executor executor, @NonNull CameraManager.AvailabilityCallback availabilityCallback);

        void unregisterAvailabilityCallback(@NonNull CameraManager.AvailabilityCallback availabilityCallback);
    }

    /* loaded from: classes.dex */
    public static final class a extends CameraManager.AvailabilityCallback {

        /* renamed from: a  reason: collision with root package name */
        public final Executor f524a;
        public final CameraManager.AvailabilityCallback b;
        public final Object c = new Object();
        @GuardedBy("mLock")
        public boolean d = false;

        /* renamed from: androidx.camera.camera2.internal.compat.CameraManagerCompat$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class RunnableC0111a implements Runnable {
            public RunnableC0111a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.onCameraAccessPrioritiesChanged();
            }
        }

        /* loaded from: classes.dex */
        public class b implements Runnable {
            public final /* synthetic */ String h;

            public b(String str) {
                this.h = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.onCameraAvailable(this.h);
            }
        }

        /* loaded from: classes.dex */
        public class c implements Runnable {
            public final /* synthetic */ String h;

            public c(String str) {
                this.h = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.b.onCameraUnavailable(this.h);
            }
        }

        public a(@NonNull Executor executor, @NonNull CameraManager.AvailabilityCallback availabilityCallback) {
            this.f524a = executor;
            this.b = availabilityCallback;
        }

        public void a() {
            synchronized (this.c) {
                this.d = true;
            }
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        @RequiresApi(29)
        public void onCameraAccessPrioritiesChanged() {
            synchronized (this.c) {
                if (!this.d) {
                    this.f524a.execute(new RunnableC0111a());
                }
            }
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraAvailable(@NonNull String str) {
            synchronized (this.c) {
                if (!this.d) {
                    this.f524a.execute(new b(str));
                }
            }
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraUnavailable(@NonNull String str) {
            synchronized (this.c) {
                if (!this.d) {
                    this.f524a.execute(new c(str));
                }
            }
        }
    }

    public CameraManagerCompat(CameraManagerCompatImpl cameraManagerCompatImpl) {
        this.f523a = cameraManagerCompatImpl;
    }

    @NonNull
    public static CameraManagerCompat from(@NonNull Context context) {
        return from(context, MainThreadAsyncHandler.getInstance());
    }

    @NonNull
    public CameraCharacteristicsCompat getCameraCharacteristicsCompat(@NonNull String str) throws CameraAccessExceptionCompat {
        CameraCharacteristicsCompat cameraCharacteristicsCompat;
        synchronized (this.b) {
            cameraCharacteristicsCompat = this.b.get(str);
            if (cameraCharacteristicsCompat == null) {
                cameraCharacteristicsCompat = CameraCharacteristicsCompat.toCameraCharacteristicsCompat(this.f523a.getCameraCharacteristics(str));
                this.b.put(str, cameraCharacteristicsCompat);
            }
        }
        return cameraCharacteristicsCompat;
    }

    @NonNull
    public String[] getCameraIdList() throws CameraAccessExceptionCompat {
        return this.f523a.getCameraIdList();
    }

    @RequiresPermission("android.permission.CAMERA")
    public void openCamera(@NonNull String str, @NonNull Executor executor, @NonNull CameraDevice.StateCallback stateCallback) throws CameraAccessExceptionCompat {
        this.f523a.openCamera(str, executor, stateCallback);
    }

    public void registerAvailabilityCallback(@NonNull Executor executor, @NonNull CameraManager.AvailabilityCallback availabilityCallback) {
        this.f523a.registerAvailabilityCallback(executor, availabilityCallback);
    }

    public void unregisterAvailabilityCallback(@NonNull CameraManager.AvailabilityCallback availabilityCallback) {
        this.f523a.unregisterAvailabilityCallback(availabilityCallback);
    }

    @NonNull
    public CameraManager unwrap() {
        return this.f523a.getCameraManager();
    }

    @NonNull
    public static CameraManagerCompat from(@NonNull Context context, @NonNull Handler handler) {
        return new CameraManagerCompat(CameraManagerCompatImpl.from(context, handler));
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.TESTS})
    public static CameraManagerCompat from(@NonNull CameraManagerCompatImpl cameraManagerCompatImpl) {
        return new CameraManagerCompat(cameraManagerCompatImpl);
    }
}
