package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCaptureSession;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.camera.camera2.internal.SynchronizedCaptureSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public final class a2 extends SynchronizedCaptureSession.StateCallback {

    /* renamed from: a  reason: collision with root package name */
    public final List<SynchronizedCaptureSession.StateCallback> f512a;

    public a2(@NonNull List<SynchronizedCaptureSession.StateCallback> list) {
        ArrayList arrayList = new ArrayList();
        this.f512a = arrayList;
        arrayList.addAll(list);
    }

    @NonNull
    public static SynchronizedCaptureSession.StateCallback u(@NonNull SynchronizedCaptureSession.StateCallback... stateCallbackArr) {
        return new a2(Arrays.asList(stateCallbackArr));
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void m(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        for (SynchronizedCaptureSession.StateCallback stateCallback : this.f512a) {
            stateCallback.m(synchronizedCaptureSession);
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    @RequiresApi(api = 26)
    public void n(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        for (SynchronizedCaptureSession.StateCallback stateCallback : this.f512a) {
            stateCallback.n(synchronizedCaptureSession);
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void o(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        for (SynchronizedCaptureSession.StateCallback stateCallback : this.f512a) {
            stateCallback.o(synchronizedCaptureSession);
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void p(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        for (SynchronizedCaptureSession.StateCallback stateCallback : this.f512a) {
            stateCallback.p(synchronizedCaptureSession);
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void q(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        for (SynchronizedCaptureSession.StateCallback stateCallback : this.f512a) {
            stateCallback.q(synchronizedCaptureSession);
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void r(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        for (SynchronizedCaptureSession.StateCallback stateCallback : this.f512a) {
            stateCallback.r(synchronizedCaptureSession);
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void s(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        for (SynchronizedCaptureSession.StateCallback stateCallback : this.f512a) {
            stateCallback.s(synchronizedCaptureSession);
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    @RequiresApi(api = 23)
    public void t(@NonNull SynchronizedCaptureSession synchronizedCaptureSession, @NonNull Surface surface) {
        for (SynchronizedCaptureSession.StateCallback stateCallback : this.f512a) {
            stateCallback.t(synchronizedCaptureSession, surface);
        }
    }

    /* loaded from: classes.dex */
    public static class a extends SynchronizedCaptureSession.StateCallback {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final CameraCaptureSession.StateCallback f513a;

        public a(@NonNull CameraCaptureSession.StateCallback stateCallback) {
            this.f513a = stateCallback;
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void m(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
            this.f513a.onActive(synchronizedCaptureSession.k().toCameraCaptureSession());
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        @RequiresApi(api = 26)
        public void n(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
            this.f513a.onCaptureQueueEmpty(synchronizedCaptureSession.k().toCameraCaptureSession());
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void o(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
            this.f513a.onClosed(synchronizedCaptureSession.k().toCameraCaptureSession());
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void p(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
            this.f513a.onConfigureFailed(synchronizedCaptureSession.k().toCameraCaptureSession());
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void q(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
            this.f513a.onConfigured(synchronizedCaptureSession.k().toCameraCaptureSession());
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void r(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
            this.f513a.onReady(synchronizedCaptureSession.k().toCameraCaptureSession());
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void s(@NonNull SynchronizedCaptureSession synchronizedCaptureSession) {
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        @RequiresApi(api = 23)
        public void t(@NonNull SynchronizedCaptureSession synchronizedCaptureSession, @NonNull Surface surface) {
            this.f513a.onSurfacePrepared(synchronizedCaptureSession.k().toCameraCaptureSession(), surface);
        }

        public a(@NonNull List<CameraCaptureSession.StateCallback> list) {
            this(CameraCaptureSessionStateCallbacks.createComboCallback(list));
        }
    }
}
