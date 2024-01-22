package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraDevice;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public final class CameraDeviceStateCallbacks {

    /* loaded from: classes.dex */
    public static final class a extends CameraDevice.StateCallback {

        /* renamed from: a  reason: collision with root package name */
        public final List<CameraDevice.StateCallback> f506a = new ArrayList();

        public a(@NonNull List<CameraDevice.StateCallback> list) {
            for (CameraDevice.StateCallback stateCallback : list) {
                if (!(stateCallback instanceof b)) {
                    this.f506a.add(stateCallback);
                }
            }
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(@NonNull CameraDevice cameraDevice) {
            for (CameraDevice.StateCallback stateCallback : this.f506a) {
                stateCallback.onClosed(cameraDevice);
            }
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            for (CameraDevice.StateCallback stateCallback : this.f506a) {
                stateCallback.onDisconnected(cameraDevice);
            }
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(@NonNull CameraDevice cameraDevice, int i) {
            for (CameraDevice.StateCallback stateCallback : this.f506a) {
                stateCallback.onError(cameraDevice, i);
            }
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(@NonNull CameraDevice cameraDevice) {
            for (CameraDevice.StateCallback stateCallback : this.f506a) {
                stateCallback.onOpened(cameraDevice);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class b extends CameraDevice.StateCallback {
        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(@NonNull CameraDevice cameraDevice) {
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(@NonNull CameraDevice cameraDevice, int i) {
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(@NonNull CameraDevice cameraDevice) {
        }
    }

    @NonNull
    public static CameraDevice.StateCallback createComboCallback(@NonNull List<CameraDevice.StateCallback> list) {
        if (list.isEmpty()) {
            return createNoOpCallback();
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        return new a(list);
    }

    @NonNull
    public static CameraDevice.StateCallback createNoOpCallback() {
        return new b();
    }

    @NonNull
    public static CameraDevice.StateCallback createComboCallback(@NonNull CameraDevice.StateCallback... stateCallbackArr) {
        return createComboCallback(Arrays.asList(stateCallbackArr));
    }
}
