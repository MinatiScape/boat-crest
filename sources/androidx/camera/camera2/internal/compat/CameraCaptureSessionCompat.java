package androidx.camera.camera2.internal.compat;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Build;
import android.os.Handler;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.camera.core.impl.utils.MainThreadAsyncHandler;
import java.util.List;
import java.util.concurrent.Executor;
@RequiresApi(21)
/* loaded from: classes.dex */
public final class CameraCaptureSessionCompat {

    /* renamed from: a  reason: collision with root package name */
    public final a f517a;

    /* loaded from: classes.dex */
    public interface a {
        @NonNull
        CameraCaptureSession a();

        int b(@NonNull List<CaptureRequest> list, @NonNull Executor executor, @NonNull CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException;

        int c(@NonNull List<CaptureRequest> list, @NonNull Executor executor, @NonNull CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException;

        int d(@NonNull CaptureRequest captureRequest, @NonNull Executor executor, @NonNull CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException;

        int e(@NonNull CaptureRequest captureRequest, @NonNull Executor executor, @NonNull CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException;
    }

    /* loaded from: classes.dex */
    public static final class b extends CameraCaptureSession.CaptureCallback {

        /* renamed from: a  reason: collision with root package name */
        public final CameraCaptureSession.CaptureCallback f518a;
        public final Executor b;

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public final /* synthetic */ CameraCaptureSession h;
            public final /* synthetic */ CaptureRequest i;
            public final /* synthetic */ long j;
            public final /* synthetic */ long k;

            public a(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, long j, long j2) {
                this.h = cameraCaptureSession;
                this.i = captureRequest;
                this.j = j;
                this.k = j2;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f518a.onCaptureStarted(this.h, this.i, this.j, this.k);
            }
        }

        /* renamed from: androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat$b$b  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class RunnableC0108b implements Runnable {
            public final /* synthetic */ CameraCaptureSession h;
            public final /* synthetic */ CaptureRequest i;
            public final /* synthetic */ CaptureResult j;

            public RunnableC0108b(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureResult captureResult) {
                this.h = cameraCaptureSession;
                this.i = captureRequest;
                this.j = captureResult;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f518a.onCaptureProgressed(this.h, this.i, this.j);
            }
        }

        /* loaded from: classes.dex */
        public class c implements Runnable {
            public final /* synthetic */ CameraCaptureSession h;
            public final /* synthetic */ CaptureRequest i;
            public final /* synthetic */ TotalCaptureResult j;

            public c(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
                this.h = cameraCaptureSession;
                this.i = captureRequest;
                this.j = totalCaptureResult;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f518a.onCaptureCompleted(this.h, this.i, this.j);
            }
        }

        /* loaded from: classes.dex */
        public class d implements Runnable {
            public final /* synthetic */ CameraCaptureSession h;
            public final /* synthetic */ CaptureRequest i;
            public final /* synthetic */ CaptureFailure j;

            public d(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
                this.h = cameraCaptureSession;
                this.i = captureRequest;
                this.j = captureFailure;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f518a.onCaptureFailed(this.h, this.i, this.j);
            }
        }

        /* loaded from: classes.dex */
        public class e implements Runnable {
            public final /* synthetic */ CameraCaptureSession h;
            public final /* synthetic */ int i;
            public final /* synthetic */ long j;

            public e(CameraCaptureSession cameraCaptureSession, int i, long j) {
                this.h = cameraCaptureSession;
                this.i = i;
                this.j = j;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f518a.onCaptureSequenceCompleted(this.h, this.i, this.j);
            }
        }

        /* loaded from: classes.dex */
        public class f implements Runnable {
            public final /* synthetic */ CameraCaptureSession h;
            public final /* synthetic */ int i;

            public f(CameraCaptureSession cameraCaptureSession, int i) {
                this.h = cameraCaptureSession;
                this.i = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f518a.onCaptureSequenceAborted(this.h, this.i);
            }
        }

        /* loaded from: classes.dex */
        public class g implements Runnable {
            public final /* synthetic */ CameraCaptureSession h;
            public final /* synthetic */ CaptureRequest i;
            public final /* synthetic */ Surface j;
            public final /* synthetic */ long k;

            public g(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, Surface surface, long j) {
                this.h = cameraCaptureSession;
                this.i = captureRequest;
                this.j = surface;
                this.k = j;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f518a.onCaptureBufferLost(this.h, this.i, this.j, this.k);
            }
        }

        public b(@NonNull Executor executor, @NonNull CameraCaptureSession.CaptureCallback captureCallback) {
            this.b = executor;
            this.f518a = captureCallback;
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        @RequiresApi(24)
        public void onCaptureBufferLost(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull Surface surface, long j) {
            this.b.execute(new g(cameraCaptureSession, captureRequest, surface, j));
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull TotalCaptureResult totalCaptureResult) {
            this.b.execute(new c(cameraCaptureSession, captureRequest, totalCaptureResult));
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureFailed(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull CaptureFailure captureFailure) {
            this.b.execute(new d(cameraCaptureSession, captureRequest, captureFailure));
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureProgressed(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull CaptureResult captureResult) {
            this.b.execute(new RunnableC0108b(cameraCaptureSession, captureRequest, captureResult));
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureSequenceAborted(@NonNull CameraCaptureSession cameraCaptureSession, int i) {
            this.b.execute(new f(cameraCaptureSession, i));
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureSequenceCompleted(@NonNull CameraCaptureSession cameraCaptureSession, int i, long j) {
            this.b.execute(new e(cameraCaptureSession, i, j));
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureStarted(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, long j, long j2) {
            this.b.execute(new a(cameraCaptureSession, captureRequest, j, j2));
        }
    }

    /* loaded from: classes.dex */
    public static final class c extends CameraCaptureSession.StateCallback {

        /* renamed from: a  reason: collision with root package name */
        public final CameraCaptureSession.StateCallback f519a;
        public final Executor b;

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public final /* synthetic */ CameraCaptureSession h;

            public a(CameraCaptureSession cameraCaptureSession) {
                this.h = cameraCaptureSession;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.f519a.onConfigured(this.h);
            }
        }

        /* loaded from: classes.dex */
        public class b implements Runnable {
            public final /* synthetic */ CameraCaptureSession h;

            public b(CameraCaptureSession cameraCaptureSession) {
                this.h = cameraCaptureSession;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.f519a.onConfigureFailed(this.h);
            }
        }

        /* renamed from: androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat$c$c  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class RunnableC0109c implements Runnable {
            public final /* synthetic */ CameraCaptureSession h;

            public RunnableC0109c(CameraCaptureSession cameraCaptureSession) {
                this.h = cameraCaptureSession;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.f519a.onReady(this.h);
            }
        }

        /* loaded from: classes.dex */
        public class d implements Runnable {
            public final /* synthetic */ CameraCaptureSession h;

            public d(CameraCaptureSession cameraCaptureSession) {
                this.h = cameraCaptureSession;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.f519a.onActive(this.h);
            }
        }

        /* loaded from: classes.dex */
        public class e implements Runnable {
            public final /* synthetic */ CameraCaptureSession h;

            public e(CameraCaptureSession cameraCaptureSession) {
                this.h = cameraCaptureSession;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.f519a.onCaptureQueueEmpty(this.h);
            }
        }

        /* loaded from: classes.dex */
        public class f implements Runnable {
            public final /* synthetic */ CameraCaptureSession h;

            public f(CameraCaptureSession cameraCaptureSession) {
                this.h = cameraCaptureSession;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.f519a.onClosed(this.h);
            }
        }

        /* loaded from: classes.dex */
        public class g implements Runnable {
            public final /* synthetic */ CameraCaptureSession h;
            public final /* synthetic */ Surface i;

            public g(CameraCaptureSession cameraCaptureSession, Surface surface) {
                this.h = cameraCaptureSession;
                this.i = surface;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.f519a.onSurfacePrepared(this.h, this.i);
            }
        }

        public c(@NonNull Executor executor, @NonNull CameraCaptureSession.StateCallback stateCallback) {
            this.b = executor;
            this.f519a = stateCallback;
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onActive(@NonNull CameraCaptureSession cameraCaptureSession) {
            this.b.execute(new d(cameraCaptureSession));
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        @RequiresApi(26)
        public void onCaptureQueueEmpty(@NonNull CameraCaptureSession cameraCaptureSession) {
            this.b.execute(new e(cameraCaptureSession));
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onClosed(@NonNull CameraCaptureSession cameraCaptureSession) {
            this.b.execute(new f(cameraCaptureSession));
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
            this.b.execute(new b(cameraCaptureSession));
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
            this.b.execute(new a(cameraCaptureSession));
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onReady(@NonNull CameraCaptureSession cameraCaptureSession) {
            this.b.execute(new RunnableC0109c(cameraCaptureSession));
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        @RequiresApi(23)
        public void onSurfacePrepared(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull Surface surface) {
            this.b.execute(new g(cameraCaptureSession, surface));
        }
    }

    public CameraCaptureSessionCompat(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull Handler handler) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f517a = new androidx.camera.camera2.internal.compat.a(cameraCaptureSession);
        } else {
            this.f517a = androidx.camera.camera2.internal.compat.b.f(cameraCaptureSession, handler);
        }
    }

    @NonNull
    public static CameraCaptureSessionCompat toCameraCaptureSessionCompat(@NonNull CameraCaptureSession cameraCaptureSession) {
        return toCameraCaptureSessionCompat(cameraCaptureSession, MainThreadAsyncHandler.getInstance());
    }

    public int captureBurstRequests(@NonNull List<CaptureRequest> list, @NonNull Executor executor, @NonNull CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return this.f517a.b(list, executor, captureCallback);
    }

    public int captureSingleRequest(@NonNull CaptureRequest captureRequest, @NonNull Executor executor, @NonNull CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return this.f517a.e(captureRequest, executor, captureCallback);
    }

    public int setRepeatingBurstRequests(@NonNull List<CaptureRequest> list, @NonNull Executor executor, @NonNull CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return this.f517a.c(list, executor, captureCallback);
    }

    public int setSingleRepeatingRequest(@NonNull CaptureRequest captureRequest, @NonNull Executor executor, @NonNull CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return this.f517a.d(captureRequest, executor, captureCallback);
    }

    @NonNull
    public CameraCaptureSession toCameraCaptureSession() {
        return this.f517a.a();
    }

    @NonNull
    public static CameraCaptureSessionCompat toCameraCaptureSessionCompat(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull Handler handler) {
        return new CameraCaptureSessionCompat(cameraCaptureSession, handler);
    }
}
