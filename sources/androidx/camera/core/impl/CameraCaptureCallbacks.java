package androidx.camera.core.impl;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public final class CameraCaptureCallbacks {

    /* loaded from: classes.dex */
    public static final class ComboCameraCaptureCallback extends CameraCaptureCallback {

        /* renamed from: a  reason: collision with root package name */
        public final List<CameraCaptureCallback> f690a = new ArrayList();

        public ComboCameraCaptureCallback(@NonNull List<CameraCaptureCallback> list) {
            for (CameraCaptureCallback cameraCaptureCallback : list) {
                if (!(cameraCaptureCallback instanceof a)) {
                    this.f690a.add(cameraCaptureCallback);
                }
            }
        }

        @NonNull
        public List<CameraCaptureCallback> getCallbacks() {
            return this.f690a;
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCancelled() {
            for (CameraCaptureCallback cameraCaptureCallback : this.f690a) {
                cameraCaptureCallback.onCaptureCancelled();
            }
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCompleted(@NonNull CameraCaptureResult cameraCaptureResult) {
            for (CameraCaptureCallback cameraCaptureCallback : this.f690a) {
                cameraCaptureCallback.onCaptureCompleted(cameraCaptureResult);
            }
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureFailed(@NonNull CameraCaptureFailure cameraCaptureFailure) {
            for (CameraCaptureCallback cameraCaptureCallback : this.f690a) {
                cameraCaptureCallback.onCaptureFailed(cameraCaptureFailure);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class a extends CameraCaptureCallback {
        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCompleted(@NonNull CameraCaptureResult cameraCaptureResult) {
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureFailed(@NonNull CameraCaptureFailure cameraCaptureFailure) {
        }
    }

    @NonNull
    public static CameraCaptureCallback a(@NonNull List<CameraCaptureCallback> list) {
        if (list.isEmpty()) {
            return createNoOpCallback();
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        return new ComboCameraCaptureCallback(list);
    }

    @NonNull
    public static CameraCaptureCallback createComboCallback(@NonNull CameraCaptureCallback... cameraCaptureCallbackArr) {
        return a(Arrays.asList(cameraCaptureCallbackArr));
    }

    @NonNull
    public static CameraCaptureCallback createNoOpCallback() {
        return new a();
    }
}
