package androidx.camera.core.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import androidx.annotation.NonNull;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CaptureConfig;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes.dex */
public final class SessionConfig {

    /* renamed from: a  reason: collision with root package name */
    public final List<DeferrableSurface> f712a;
    public final List<CameraDevice.StateCallback> b;
    public final List<CameraCaptureSession.StateCallback> c;
    public final List<CameraCaptureCallback> d;
    public final List<ErrorListener> e;
    public final CaptureConfig f;

    /* loaded from: classes.dex */
    public static class Builder extends a {
        @NonNull
        public static Builder createFrom(@NonNull UseCaseConfig<?> useCaseConfig) {
            OptionUnpacker sessionOptionUnpacker = useCaseConfig.getSessionOptionUnpacker(null);
            if (sessionOptionUnpacker != null) {
                Builder builder = new Builder();
                sessionOptionUnpacker.unpack(useCaseConfig, builder);
                return builder;
            }
            throw new IllegalStateException("Implementation is missing option unpacker for " + useCaseConfig.getTargetName(useCaseConfig.toString()));
        }

        public void addAllCameraCaptureCallbacks(@NonNull Collection<CameraCaptureCallback> collection) {
            this.b.addAllCameraCaptureCallbacks(collection);
            this.f.addAll(collection);
        }

        public void addAllDeviceStateCallbacks(@NonNull Collection<CameraDevice.StateCallback> collection) {
            for (CameraDevice.StateCallback stateCallback : collection) {
                addDeviceStateCallback(stateCallback);
            }
        }

        public void addAllRepeatingCameraCaptureCallbacks(@NonNull Collection<CameraCaptureCallback> collection) {
            this.b.addAllCameraCaptureCallbacks(collection);
        }

        public void addAllSessionStateCallbacks(@NonNull List<CameraCaptureSession.StateCallback> list) {
            for (CameraCaptureSession.StateCallback stateCallback : list) {
                addSessionStateCallback(stateCallback);
            }
        }

        public void addCameraCaptureCallback(@NonNull CameraCaptureCallback cameraCaptureCallback) {
            this.b.addCameraCaptureCallback(cameraCaptureCallback);
            this.f.add(cameraCaptureCallback);
        }

        public void addDeviceStateCallback(@NonNull CameraDevice.StateCallback stateCallback) {
            if (!this.c.contains(stateCallback)) {
                this.c.add(stateCallback);
                return;
            }
            throw new IllegalArgumentException("Duplicate device state callback.");
        }

        public void addErrorListener(@NonNull ErrorListener errorListener) {
            this.e.add(errorListener);
        }

        public void addImplementationOptions(@NonNull Config config) {
            this.b.addImplementationOptions(config);
        }

        public void addNonRepeatingSurface(@NonNull DeferrableSurface deferrableSurface) {
            this.f713a.add(deferrableSurface);
        }

        public void addRepeatingCameraCaptureCallback(@NonNull CameraCaptureCallback cameraCaptureCallback) {
            this.b.addCameraCaptureCallback(cameraCaptureCallback);
        }

        public void addSessionStateCallback(@NonNull CameraCaptureSession.StateCallback stateCallback) {
            if (!this.d.contains(stateCallback)) {
                this.d.add(stateCallback);
                return;
            }
            throw new IllegalArgumentException("Duplicate session state callback.");
        }

        public void addSurface(@NonNull DeferrableSurface deferrableSurface) {
            this.f713a.add(deferrableSurface);
            this.b.addSurface(deferrableSurface);
        }

        public void addTag(@NonNull String str, @NonNull Integer num) {
            this.b.addTag(str, num);
        }

        @NonNull
        public SessionConfig build() {
            return new SessionConfig(new ArrayList(this.f713a), this.c, this.d, this.f, this.e, this.b.build());
        }

        public void clearSurfaces() {
            this.f713a.clear();
            this.b.clearSurfaces();
        }

        @NonNull
        public List<CameraCaptureCallback> getSingleCameraCaptureCallbacks() {
            return Collections.unmodifiableList(this.f);
        }

        public void removeSurface(@NonNull DeferrableSurface deferrableSurface) {
            this.f713a.remove(deferrableSurface);
            this.b.removeSurface(deferrableSurface);
        }

        public void setImplementationOptions(@NonNull Config config) {
            this.b.setImplementationOptions(config);
        }

        public void setTemplateType(int i) {
            this.b.setTemplateType(i);
        }
    }

    /* loaded from: classes.dex */
    public interface ErrorListener {
        void onError(@NonNull SessionConfig sessionConfig, @NonNull SessionError sessionError);
    }

    /* loaded from: classes.dex */
    public interface OptionUnpacker {
        void unpack(@NonNull UseCaseConfig<?> useCaseConfig, @NonNull Builder builder);
    }

    /* loaded from: classes.dex */
    public enum SessionError {
        SESSION_ERROR_SURFACE_NEEDS_RESET,
        SESSION_ERROR_UNKNOWN
    }

    /* loaded from: classes.dex */
    public static final class ValidatingBuilder extends a {
        public boolean g = true;
        public boolean h = false;

        public void add(@NonNull SessionConfig sessionConfig) {
            CaptureConfig repeatingCaptureConfig = sessionConfig.getRepeatingCaptureConfig();
            if (repeatingCaptureConfig.getTemplateType() != -1) {
                if (!this.h) {
                    this.b.setTemplateType(repeatingCaptureConfig.getTemplateType());
                    this.h = true;
                } else if (this.b.getTemplateType() != repeatingCaptureConfig.getTemplateType()) {
                    Logger.d("ValidatingBuilder", "Invalid configuration due to template type: " + this.b.getTemplateType() + " != " + repeatingCaptureConfig.getTemplateType());
                    this.g = false;
                }
            }
            this.b.addAllTags(sessionConfig.getRepeatingCaptureConfig().getTagBundle());
            this.c.addAll(sessionConfig.getDeviceStateCallbacks());
            this.d.addAll(sessionConfig.getSessionStateCallbacks());
            this.b.addAllCameraCaptureCallbacks(sessionConfig.getRepeatingCameraCaptureCallbacks());
            this.f.addAll(sessionConfig.getSingleCameraCaptureCallbacks());
            this.e.addAll(sessionConfig.getErrorListeners());
            this.f713a.addAll(sessionConfig.getSurfaces());
            this.b.getSurfaces().addAll(repeatingCaptureConfig.getSurfaces());
            if (!this.f713a.containsAll(this.b.getSurfaces())) {
                Logger.d("ValidatingBuilder", "Invalid configuration due to capture request surfaces are not a subset of surfaces");
                this.g = false;
            }
            this.b.addImplementationOptions(repeatingCaptureConfig.getImplementationOptions());
        }

        @NonNull
        public SessionConfig build() {
            if (this.g) {
                return new SessionConfig(new ArrayList(this.f713a), this.c, this.d, this.f, this.e, this.b.build());
            }
            throw new IllegalArgumentException("Unsupported session configuration combination");
        }

        public boolean isValid() {
            return this.h && this.g;
        }
    }

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final Set<DeferrableSurface> f713a = new HashSet();
        public final CaptureConfig.Builder b = new CaptureConfig.Builder();
        public final List<CameraDevice.StateCallback> c = new ArrayList();
        public final List<CameraCaptureSession.StateCallback> d = new ArrayList();
        public final List<ErrorListener> e = new ArrayList();
        public final List<CameraCaptureCallback> f = new ArrayList();
    }

    public SessionConfig(List<DeferrableSurface> list, List<CameraDevice.StateCallback> list2, List<CameraCaptureSession.StateCallback> list3, List<CameraCaptureCallback> list4, List<ErrorListener> list5, CaptureConfig captureConfig) {
        this.f712a = list;
        this.b = Collections.unmodifiableList(list2);
        this.c = Collections.unmodifiableList(list3);
        this.d = Collections.unmodifiableList(list4);
        this.e = Collections.unmodifiableList(list5);
        this.f = captureConfig;
    }

    @NonNull
    public static SessionConfig defaultEmptySessionConfig() {
        return new SessionConfig(new ArrayList(), new ArrayList(0), new ArrayList(0), new ArrayList(0), new ArrayList(0), new CaptureConfig.Builder().build());
    }

    @NonNull
    public List<CameraDevice.StateCallback> getDeviceStateCallbacks() {
        return this.b;
    }

    @NonNull
    public List<ErrorListener> getErrorListeners() {
        return this.e;
    }

    @NonNull
    public Config getImplementationOptions() {
        return this.f.getImplementationOptions();
    }

    @NonNull
    public List<CameraCaptureCallback> getRepeatingCameraCaptureCallbacks() {
        return this.f.getCameraCaptureCallbacks();
    }

    @NonNull
    public CaptureConfig getRepeatingCaptureConfig() {
        return this.f;
    }

    @NonNull
    public List<CameraCaptureSession.StateCallback> getSessionStateCallbacks() {
        return this.c;
    }

    @NonNull
    public List<CameraCaptureCallback> getSingleCameraCaptureCallbacks() {
        return this.d;
    }

    @NonNull
    public List<DeferrableSurface> getSurfaces() {
        return Collections.unmodifiableList(this.f712a);
    }

    public int getTemplateType() {
        return this.f.getTemplateType();
    }
}
