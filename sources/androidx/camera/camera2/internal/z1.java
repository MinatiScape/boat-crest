package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.os.Handler;
import android.view.Surface;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
/* loaded from: classes.dex */
public class z1 extends v1 {
    public final Object o;
    @NonNull
    public final Set<String> p;
    @NonNull
    public final ListenableFuture<Void> q;
    public CallbackToFutureAdapter.Completer<Void> r;
    @Nullable
    @GuardedBy("mObjectLock")
    public List<DeferrableSurface> s;
    @Nullable
    @GuardedBy("mObjectLock")
    public ListenableFuture<Void> t;
    @Nullable
    @GuardedBy("mObjectLock")
    public ListenableFuture<List<Surface>> u;
    @GuardedBy("mObjectLock")
    public boolean v;
    public final CameraCaptureSession.CaptureCallback w;

    /* loaded from: classes.dex */
    public class a extends CameraCaptureSession.CaptureCallback {
        public a() {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureSequenceAborted(@NonNull CameraCaptureSession cameraCaptureSession, int i) {
            CallbackToFutureAdapter.Completer<Void> completer = z1.this.r;
            if (completer != null) {
                completer.setCancelled();
                z1.this.r = null;
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureStarted(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, long j, long j2) {
            CallbackToFutureAdapter.Completer<Void> completer = z1.this.r;
            if (completer != null) {
                completer.set(null);
                z1.this.r = null;
            }
        }
    }

    public z1(@NonNull Set<String> set, @NonNull x0 x0Var, @NonNull Executor executor, @NonNull ScheduledExecutorService scheduledExecutorService, @NonNull Handler handler) {
        super(x0Var, executor, scheduledExecutorService, handler);
        this.o = new Object();
        this.w = new a();
        this.p = set;
        if (set.contains("wait_for_request")) {
            this.q = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.x1
                @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                    Object Q;
                    Q = z1.this.Q(completer);
                    return Q;
                }
            });
        } else {
            this.q = Futures.immediateFuture(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void C() {
        M("Session call super.close()");
        super.close();
    }

    public static void N(@NonNull Set<SynchronizedCaptureSession> set) {
        for (SynchronizedCaptureSession synchronizedCaptureSession : set) {
            synchronizedCaptureSession.b().o(synchronizedCaptureSession);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object Q(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.r = completer;
        return "StartStreamingFuture[session=" + this + "]";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ListenableFuture R(CameraDevice cameraDevice, SessionConfigurationCompat sessionConfigurationCompat, List list, List list2) throws Exception {
        return super.a(cameraDevice, sessionConfigurationCompat, list);
    }

    public void L() {
        synchronized (this.o) {
            if (this.s == null) {
                M("deferrableSurface == null, maybe forceClose, skip close");
                return;
            }
            if (this.p.contains("deferrableSurface_close")) {
                for (DeferrableSurface deferrableSurface : this.s) {
                    deferrableSurface.close();
                }
                M("deferrableSurface closed");
            }
        }
    }

    public void M(String str) {
        Logger.d("SyncCaptureSessionImpl", "[" + this + "] " + str);
    }

    public final void O(@NonNull Set<SynchronizedCaptureSession> set) {
        for (SynchronizedCaptureSession synchronizedCaptureSession : set) {
            synchronizedCaptureSession.b().p(synchronizedCaptureSession);
        }
    }

    public final List<ListenableFuture<Void>> P(@NonNull String str, List<SynchronizedCaptureSession> list) {
        ArrayList arrayList = new ArrayList();
        for (SynchronizedCaptureSession synchronizedCaptureSession : list) {
            arrayList.add(synchronizedCaptureSession.i(str));
        }
        return arrayList;
    }

    @Override // androidx.camera.camera2.internal.v1, androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.b
    @NonNull
    public ListenableFuture<Void> a(@NonNull final CameraDevice cameraDevice, @NonNull final SessionConfigurationCompat sessionConfigurationCompat, @NonNull final List<DeferrableSurface> list) {
        ListenableFuture<Void> nonCancellationPropagating;
        synchronized (this.o) {
            FutureChain transformAsync = FutureChain.from(Futures.successfulAsList(P("wait_for_request", this.b.e()))).transformAsync(new AsyncFunction() { // from class: androidx.camera.camera2.internal.w1
                @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
                public final ListenableFuture apply(Object obj) {
                    ListenableFuture R;
                    R = z1.this.R(cameraDevice, sessionConfigurationCompat, list, (List) obj);
                    return R;
                }
            }, CameraXExecutors.directExecutor());
            this.t = transformAsync;
            nonCancellationPropagating = Futures.nonCancellationPropagating(transformAsync);
        }
        return nonCancellationPropagating;
    }

    @Override // androidx.camera.camera2.internal.v1, androidx.camera.camera2.internal.SynchronizedCaptureSession
    public void close() {
        M("Session call close()");
        if (this.p.contains("wait_for_request")) {
            synchronized (this.o) {
                if (!this.v) {
                    this.q.cancel(true);
                }
            }
        }
        this.q.addListener(new Runnable() { // from class: androidx.camera.camera2.internal.y1
            @Override // java.lang.Runnable
            public final void run() {
                z1.this.C();
            }
        }, getExecutor());
    }

    @Override // androidx.camera.camera2.internal.v1, androidx.camera.camera2.internal.SynchronizedCaptureSession
    public int f(@NonNull CaptureRequest captureRequest, @NonNull CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        int f;
        if (this.p.contains("wait_for_request")) {
            synchronized (this.o) {
                this.v = true;
                f = super.f(captureRequest, Camera2CaptureCallbacks.createComboCallback(this.w, captureCallback));
            }
            return f;
        }
        return super.f(captureRequest, captureCallback);
    }

    @Override // androidx.camera.camera2.internal.v1, androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.b
    @NonNull
    public ListenableFuture<List<Surface>> h(@NonNull List<DeferrableSurface> list, long j) {
        ListenableFuture<List<Surface>> nonCancellationPropagating;
        synchronized (this.o) {
            this.s = list;
            nonCancellationPropagating = Futures.nonCancellationPropagating(super.h(list, j));
        }
        return nonCancellationPropagating;
    }

    @Override // androidx.camera.camera2.internal.v1, androidx.camera.camera2.internal.SynchronizedCaptureSession
    @NonNull
    public ListenableFuture<Void> i(@NonNull String str) {
        str.hashCode();
        if (!str.equals("wait_for_request")) {
            return super.i(str);
        }
        return Futures.nonCancellationPropagating(this.q);
    }

    @Override // androidx.camera.camera2.internal.v1, androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void o(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        L();
        M("onClosed()");
        super.o(synchronizedCaptureSession);
    }

    @Override // androidx.camera.camera2.internal.v1, androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void q(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        SynchronizedCaptureSession next;
        SynchronizedCaptureSession next2;
        M("Session onConfigured()");
        if (this.p.contains("force_close")) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator<SynchronizedCaptureSession> it = this.b.f().iterator();
            while (it.hasNext() && (next2 = it.next()) != synchronizedCaptureSession) {
                linkedHashSet.add(next2);
            }
            O(linkedHashSet);
        }
        super.q(synchronizedCaptureSession);
        if (this.p.contains("force_close")) {
            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
            Iterator<SynchronizedCaptureSession> it2 = this.b.d().iterator();
            while (it2.hasNext() && (next = it2.next()) != synchronizedCaptureSession) {
                linkedHashSet2.add(next);
            }
            N(linkedHashSet2);
        }
    }

    @Override // androidx.camera.camera2.internal.v1, androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.b
    public boolean stop() {
        boolean stop;
        synchronized (this.o) {
            if (B()) {
                L();
            } else {
                ListenableFuture<Void> listenableFuture = this.t;
                if (listenableFuture != null) {
                    listenableFuture.cancel(true);
                }
                ListenableFuture<List<Surface>> listenableFuture2 = this.u;
                if (listenableFuture2 != null) {
                    listenableFuture2.cancel(true);
                }
            }
            stop = super.stop();
        }
        return stop;
    }
}
