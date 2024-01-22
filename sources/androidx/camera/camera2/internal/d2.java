package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import androidx.annotation.NonNull;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.core.CameraControl;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class d2 {

    /* renamed from: a  reason: collision with root package name */
    public final Camera2CameraControlImpl f552a;
    public final MutableLiveData<Integer> b;
    public final boolean c;
    public final Executor d;
    public boolean e;
    public CallbackToFutureAdapter.Completer<Void> f;
    public boolean g;
    public final Camera2CameraControlImpl.CaptureResultListener h;

    /* loaded from: classes.dex */
    public class a implements Camera2CameraControlImpl.CaptureResultListener {
        public a() {
        }

        @Override // androidx.camera.camera2.internal.Camera2CameraControlImpl.CaptureResultListener
        public boolean onCaptureResult(@NonNull TotalCaptureResult totalCaptureResult) {
            if (d2.this.f != null) {
                Integer num = (Integer) totalCaptureResult.getRequest().get(CaptureRequest.FLASH_MODE);
                boolean z = num != null && num.intValue() == 2;
                d2 d2Var = d2.this;
                if (z == d2Var.g) {
                    d2Var.f.set(null);
                    d2.this.f = null;
                }
            }
            return false;
        }
    }

    public d2(@NonNull Camera2CameraControlImpl camera2CameraControlImpl, @NonNull CameraCharacteristicsCompat cameraCharacteristicsCompat, @NonNull Executor executor) {
        a aVar = new a();
        this.h = aVar;
        this.f552a = camera2CameraControlImpl;
        this.d = executor;
        Boolean bool = (Boolean) cameraCharacteristicsCompat.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
        this.c = bool != null && bool.booleanValue();
        this.b = new MutableLiveData<>(0);
        camera2CameraControlImpl.l(aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object g(final boolean z, final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.d.execute(new Runnable() { // from class: androidx.camera.camera2.internal.c2
            @Override // java.lang.Runnable
            public final void run() {
                d2.this.f(completer, z);
            }
        });
        return "enableTorch: " + z;
    }

    public ListenableFuture<Void> c(final boolean z) {
        if (!this.c) {
            Logger.d("TorchControl", "Unable to enableTorch due to there is no flash unit.");
            return Futures.immediateFailedFuture(new IllegalStateException("No flash unit"));
        }
        i(this.b, Integer.valueOf(z ? 1 : 0));
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.b2
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object g;
                g = d2.this.g(z, completer);
                return g;
            }
        });
    }

    /* renamed from: d */
    public void f(@NonNull CallbackToFutureAdapter.Completer<Void> completer, boolean z) {
        if (!this.e) {
            i(this.b, 0);
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
            return;
        }
        this.g = z;
        this.f552a.o(z);
        i(this.b, Integer.valueOf(z ? 1 : 0));
        CallbackToFutureAdapter.Completer<Void> completer2 = this.f;
        if (completer2 != null) {
            completer2.setException(new CameraControl.OperationCanceledException("There is a new enableTorch being set"));
        }
        this.f = completer;
    }

    @NonNull
    public LiveData<Integer> e() {
        return this.b;
    }

    public void h(boolean z) {
        if (this.e == z) {
            return;
        }
        this.e = z;
        if (z) {
            return;
        }
        if (this.g) {
            this.g = false;
            this.f552a.o(false);
            i(this.b, 0);
        }
        CallbackToFutureAdapter.Completer<Void> completer = this.f;
        if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
            this.f = null;
        }
    }

    public final <T> void i(@NonNull MutableLiveData<T> mutableLiveData, T t) {
        if (Threads.isMainThread()) {
            mutableLiveData.setValue(t);
        } else {
            mutableLiveData.postValue(t);
        }
    }
}
