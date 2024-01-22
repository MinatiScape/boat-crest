package androidx.camera.camera2.internal.compat;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.os.Build;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.camera.core.impl.utils.MainThreadAsyncHandler;
import java.util.concurrent.Executor;
@RequiresApi(21)
/* loaded from: classes.dex */
public final class CameraDeviceCompat {
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final int SESSION_OPERATION_MODE_CONSTRAINED_HIGH_SPEED = 1;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final int SESSION_OPERATION_MODE_NORMAL = 0;

    /* renamed from: a  reason: collision with root package name */
    public final a f521a;

    /* loaded from: classes.dex */
    public interface a {
        @NonNull
        CameraDevice a();

        void b(@NonNull SessionConfigurationCompat sessionConfigurationCompat) throws CameraAccessException;
    }

    /* loaded from: classes.dex */
    public static final class b extends CameraDevice.StateCallback {

        /* renamed from: a  reason: collision with root package name */
        public final CameraDevice.StateCallback f522a;
        public final Executor b;

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public final /* synthetic */ CameraDevice h;

            public a(CameraDevice cameraDevice) {
                this.h = cameraDevice;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f522a.onOpened(this.h);
            }
        }

        /* renamed from: androidx.camera.camera2.internal.compat.CameraDeviceCompat$b$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class RunnableC0110b implements Runnable {
            public final /* synthetic */ CameraDevice h;

            public RunnableC0110b(CameraDevice cameraDevice) {
                this.h = cameraDevice;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f522a.onDisconnected(this.h);
            }
        }

        /* loaded from: classes.dex */
        public class c implements Runnable {
            public final /* synthetic */ CameraDevice h;
            public final /* synthetic */ int i;

            public c(CameraDevice cameraDevice, int i) {
                this.h = cameraDevice;
                this.i = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f522a.onError(this.h, this.i);
            }
        }

        /* loaded from: classes.dex */
        public class d implements Runnable {
            public final /* synthetic */ CameraDevice h;

            public d(CameraDevice cameraDevice) {
                this.h = cameraDevice;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f522a.onClosed(this.h);
            }
        }

        public b(@NonNull Executor executor, @NonNull CameraDevice.StateCallback stateCallback) {
            this.b = executor;
            this.f522a = stateCallback;
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(@NonNull CameraDevice cameraDevice) {
            this.b.execute(new d(cameraDevice));
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            this.b.execute(new RunnableC0110b(cameraDevice));
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(@NonNull CameraDevice cameraDevice, int i) {
            this.b.execute(new c(cameraDevice, i));
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(@NonNull CameraDevice cameraDevice) {
            this.b.execute(new a(cameraDevice));
        }
    }

    public CameraDeviceCompat(@NonNull CameraDevice cameraDevice, @NonNull Handler handler) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            this.f521a = new e(cameraDevice);
        } else if (i >= 24) {
            this.f521a = d.i(cameraDevice, handler);
        } else if (i >= 23) {
            this.f521a = c.h(cameraDevice, handler);
        } else {
            this.f521a = f.e(cameraDevice, handler);
        }
    }

    @NonNull
    public static CameraDeviceCompat toCameraDeviceCompat(@NonNull CameraDevice cameraDevice) {
        return toCameraDeviceCompat(cameraDevice, MainThreadAsyncHandler.getInstance());
    }

    public void createCaptureSession(@NonNull SessionConfigurationCompat sessionConfigurationCompat) throws CameraAccessException {
        this.f521a.b(sessionConfigurationCompat);
    }

    @NonNull
    public CameraDevice toCameraDevice() {
        return this.f521a.a();
    }

    @NonNull
    public static CameraDeviceCompat toCameraDeviceCompat(@NonNull CameraDevice cameraDevice, @NonNull Handler handler) {
        return new CameraDeviceCompat(cameraDevice, handler);
    }
}
