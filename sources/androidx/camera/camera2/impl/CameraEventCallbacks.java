package androidx.camera.camera2.impl;

import androidx.annotation.NonNull;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.MultiValueSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public final class CameraEventCallbacks extends MultiValueSet<CameraEventCallback> {

    /* loaded from: classes.dex */
    public static final class ComboCameraEventCallback {

        /* renamed from: a  reason: collision with root package name */
        public final List<CameraEventCallback> f494a = new ArrayList();

        public ComboCameraEventCallback(List<CameraEventCallback> list) {
            for (CameraEventCallback cameraEventCallback : list) {
                this.f494a.add(cameraEventCallback);
            }
        }

        @NonNull
        public List<CameraEventCallback> getCallbacks() {
            return this.f494a;
        }

        @NonNull
        public List<CaptureConfig> onDisableSession() {
            ArrayList arrayList = new ArrayList();
            for (CameraEventCallback cameraEventCallback : this.f494a) {
                CaptureConfig onDisableSession = cameraEventCallback.onDisableSession();
                if (onDisableSession != null) {
                    arrayList.add(onDisableSession);
                }
            }
            return arrayList;
        }

        @NonNull
        public List<CaptureConfig> onEnableSession() {
            ArrayList arrayList = new ArrayList();
            for (CameraEventCallback cameraEventCallback : this.f494a) {
                CaptureConfig onEnableSession = cameraEventCallback.onEnableSession();
                if (onEnableSession != null) {
                    arrayList.add(onEnableSession);
                }
            }
            return arrayList;
        }

        @NonNull
        public List<CaptureConfig> onPresetSession() {
            ArrayList arrayList = new ArrayList();
            for (CameraEventCallback cameraEventCallback : this.f494a) {
                CaptureConfig onPresetSession = cameraEventCallback.onPresetSession();
                if (onPresetSession != null) {
                    arrayList.add(onPresetSession);
                }
            }
            return arrayList;
        }

        @NonNull
        public List<CaptureConfig> onRepeating() {
            ArrayList arrayList = new ArrayList();
            for (CameraEventCallback cameraEventCallback : this.f494a) {
                CaptureConfig onRepeating = cameraEventCallback.onRepeating();
                if (onRepeating != null) {
                    arrayList.add(onRepeating);
                }
            }
            return arrayList;
        }
    }

    public CameraEventCallbacks(@NonNull CameraEventCallback... cameraEventCallbackArr) {
        addAll(Arrays.asList(cameraEventCallbackArr));
    }

    @NonNull
    public static CameraEventCallbacks createEmptyCallback() {
        return new CameraEventCallbacks(new CameraEventCallback[0]);
    }

    @NonNull
    public ComboCameraEventCallback createComboCallback() {
        return new ComboCameraEventCallback(getAllItems());
    }

    @Override // androidx.camera.core.impl.MultiValueSet
    @NonNull
    /* renamed from: clone */
    public MultiValueSet<CameraEventCallback> mo3clone() {
        CameraEventCallbacks createEmptyCallback = createEmptyCallback();
        createEmptyCallback.addAll(getAllItems());
        return createEmptyCallback;
    }
}
