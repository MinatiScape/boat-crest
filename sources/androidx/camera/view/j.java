package androidx.camera.view;

import androidx.annotation.GuardedBy;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.Observable;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.view.PreviewView;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.lifecycle.MutableLiveData;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class j implements Observable.Observer<CameraInternal.State> {

    /* renamed from: a  reason: collision with root package name */
    public final CameraInfoInternal f814a;
    public final MutableLiveData<PreviewView.StreamState> b;
    @GuardedBy("this")
    public PreviewView.StreamState c;
    public final p d;
    public ListenableFuture<Void> e;
    public boolean f = false;

    /* loaded from: classes.dex */
    public class a implements FutureCallback<Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ List f815a;
        public final /* synthetic */ CameraInfo b;

        public a(List list, CameraInfo cameraInfo) {
            this.f815a = list;
            this.b = cameraInfo;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        /* renamed from: a */
        public void onSuccess(@Nullable Void r2) {
            j.this.e = null;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable th) {
            j.this.e = null;
            if (this.f815a.isEmpty()) {
                return;
            }
            for (CameraCaptureCallback cameraCaptureCallback : this.f815a) {
                ((CameraInfoInternal) this.b).removeSessionCaptureCallback(cameraCaptureCallback);
            }
            this.f815a.clear();
        }
    }

    /* loaded from: classes.dex */
    public class b extends CameraCaptureCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CallbackToFutureAdapter.Completer f816a;
        public final /* synthetic */ CameraInfo b;

        public b(j jVar, CallbackToFutureAdapter.Completer completer, CameraInfo cameraInfo) {
            this.f816a = completer;
            this.b = cameraInfo;
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCompleted(@NonNull CameraCaptureResult cameraCaptureResult) {
            this.f816a.set(null);
            ((CameraInfoInternal) this.b).removeSessionCaptureCallback(this);
        }
    }

    public j(CameraInfoInternal cameraInfoInternal, MutableLiveData<PreviewView.StreamState> mutableLiveData, p pVar) {
        this.f814a = cameraInfoInternal;
        this.b = mutableLiveData;
        this.d = pVar;
        synchronized (this) {
            this.c = mutableLiveData.getValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ListenableFuture f(Void r1) throws Exception {
        return this.d.i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Void g(Void r1) {
        k(PreviewView.StreamState.STREAMING);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object h(CameraInfo cameraInfo, List list, CallbackToFutureAdapter.Completer completer) throws Exception {
        b bVar = new b(this, completer, cameraInfo);
        list.add(bVar);
        ((CameraInfoInternal) cameraInfo).addSessionCaptureCallback(CameraXExecutors.directExecutor(), bVar);
        return "waitForCaptureResult";
    }

    public final void d() {
        ListenableFuture<Void> listenableFuture = this.e;
        if (listenableFuture != null) {
            listenableFuture.cancel(false);
            this.e = null;
        }
    }

    public void e() {
        d();
    }

    @Override // androidx.camera.core.impl.Observable.Observer
    @MainThread
    /* renamed from: i */
    public void onNewData(@Nullable CameraInternal.State state) {
        if (state != CameraInternal.State.CLOSING && state != CameraInternal.State.CLOSED && state != CameraInternal.State.RELEASING && state != CameraInternal.State.RELEASED) {
            if ((state == CameraInternal.State.OPENING || state == CameraInternal.State.OPEN || state == CameraInternal.State.PENDING_OPEN) && !this.f) {
                j(this.f814a);
                this.f = true;
                return;
            }
            return;
        }
        k(PreviewView.StreamState.IDLE);
        if (this.f) {
            this.f = false;
            d();
        }
    }

    @MainThread
    public final void j(CameraInfo cameraInfo) {
        k(PreviewView.StreamState.IDLE);
        ArrayList arrayList = new ArrayList();
        FutureChain transform = FutureChain.from(l(cameraInfo, arrayList)).transformAsync(new AsyncFunction() { // from class: androidx.camera.view.h
            @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                ListenableFuture f;
                f = j.this.f((Void) obj);
                return f;
            }
        }, CameraXExecutors.directExecutor()).transform(new Function() { // from class: androidx.camera.view.g
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                Void g;
                g = j.this.g((Void) obj);
                return g;
            }
        }, CameraXExecutors.directExecutor());
        this.e = transform;
        Futures.addCallback(transform, new a(arrayList, cameraInfo), CameraXExecutors.directExecutor());
    }

    public void k(PreviewView.StreamState streamState) {
        synchronized (this) {
            if (this.c.equals(streamState)) {
                return;
            }
            this.c = streamState;
            Logger.d("StreamStateObserver", "Update Preview stream state to " + streamState);
            this.b.postValue(streamState);
        }
    }

    public final ListenableFuture<Void> l(final CameraInfo cameraInfo, final List<CameraCaptureCallback> list) {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.view.i
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object h;
                h = j.this.h(cameraInfo, list, completer);
                return h;
            }
        });
    }

    @Override // androidx.camera.core.impl.Observable.Observer
    @MainThread
    public void onError(@NonNull Throwable th) {
        e();
        k(PreviewView.StreamState.IDLE);
    }
}
