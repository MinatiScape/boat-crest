package androidx.camera.core;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.util.Size;
import androidx.annotation.CallSuper;
import androidx.annotation.GuardedBy;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.internal.TargetConfig;
import androidx.camera.core.internal.utils.UseCaseConfigUtil;
import androidx.core.util.Preconditions;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes.dex */
public abstract class UseCase {
    @Nullable
    public UseCaseConfig<?> d;
    @NonNull
    public UseCaseConfig<?> e;
    @NonNull
    public UseCaseConfig<?> f;
    public Size g;
    @Nullable
    public UseCaseConfig<?> h;
    @Nullable
    public Rect i;
    @GuardedBy("mCameraLock")
    public CameraInternal j;

    /* renamed from: a  reason: collision with root package name */
    public final Set<StateChangeCallback> f656a = new HashSet();
    public final Object b = new Object();
    public b c = b.INACTIVE;
    public SessionConfig k = SessionConfig.defaultEmptySessionConfig();

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public interface EventCallback {
        void onAttach(@NonNull CameraInfo cameraInfo);

        void onDetach();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public interface StateChangeCallback {
        void onUseCaseActive(@NonNull UseCase useCase);

        void onUseCaseInactive(@NonNull UseCase useCase);

        void onUseCaseReset(@NonNull UseCase useCase);

        void onUseCaseUpdated(@NonNull UseCase useCase);
    }

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f657a;

        static {
            int[] iArr = new int[b.values().length];
            f657a = iArr;
            try {
                iArr[b.INACTIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f657a[b.ACTIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum b {
        ACTIVE,
        INACTIVE
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public UseCase(@NonNull UseCaseConfig<?> useCaseConfig) {
        this.e = useCaseConfig;
        this.f = useCaseConfig;
    }

    public final void a(@NonNull StateChangeCallback stateChangeCallback) {
        this.f656a.add(stateChangeCallback);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [androidx.camera.core.impl.UseCaseConfig, androidx.camera.core.impl.UseCaseConfig<?>] */
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public UseCaseConfig<?> b(@NonNull CameraInfoInternal cameraInfoInternal, @NonNull UseCaseConfig.Builder<?, ?, ?> builder) {
        return builder.getUseCaseConfig();
    }

    public final void c(@NonNull StateChangeCallback stateChangeCallback) {
        this.f656a.remove(stateChangeCallback);
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Size getAttachedSurfaceResolution() {
        return this.g;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CameraInternal getCamera() {
        CameraInternal cameraInternal;
        synchronized (this.b) {
            cameraInternal = this.j;
        }
        return cameraInternal;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CameraControlInternal getCameraControl() {
        synchronized (this.b) {
            CameraInternal cameraInternal = this.j;
            if (cameraInternal == null) {
                return CameraControlInternal.DEFAULT_EMPTY_INSTANCE;
            }
            return cameraInternal.getCameraControlInternal();
        }
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public String getCameraId() {
        CameraInternal camera = getCamera();
        return ((CameraInternal) Preconditions.checkNotNull(camera, "No camera attached to use case: " + this)).getCameraInfoInternal().getCameraId();
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public UseCaseConfig<?> getCurrentConfig() {
        return this.f;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public abstract UseCaseConfig<?> getDefaultConfig(boolean z, @NonNull UseCaseConfigFactory useCaseConfigFactory);

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getImageFormat() {
        return this.f.getInputFormat();
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public String getName() {
        UseCaseConfig<?> useCaseConfig = this.f;
        return useCaseConfig.getTargetName("<UnknownUseCase-" + hashCode() + ">");
    }

    @IntRange(from = 0, to = 359)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getRelativeRotation(@NonNull CameraInternal cameraInternal) {
        return cameraInternal.getCameraInfoInternal().getSensorRotationDegrees(getTargetRotationInternal());
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public SessionConfig getSessionConfig() {
        return this.k;
    }

    @SuppressLint({"WrongConstant"})
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getTargetRotationInternal() {
        return ((ImageOutputConfig) this.f).getTargetRotation(0);
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public abstract UseCaseConfig.Builder<?, ?, ?> getUseCaseConfigBuilder(@NonNull Config config);

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Rect getViewPortCropRect() {
        return this.i;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isCurrentCamera(@NonNull String str) {
        if (getCamera() == null) {
            return false;
        }
        return Objects.equals(str, getCameraId());
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public UseCaseConfig<?> mergeConfigs(@NonNull CameraInfoInternal cameraInfoInternal, @Nullable UseCaseConfig<?> useCaseConfig, @Nullable UseCaseConfig<?> useCaseConfig2) {
        MutableOptionsBundle create;
        if (useCaseConfig2 != null) {
            create = MutableOptionsBundle.from((Config) useCaseConfig2);
            create.removeOption(TargetConfig.OPTION_TARGET_NAME);
        } else {
            create = MutableOptionsBundle.create();
        }
        for (Config.Option<?> option : this.e.listOptions()) {
            create.insertOption(option, this.e.getOptionPriority(option), this.e.retrieveOption(option));
        }
        if (useCaseConfig != null) {
            for (Config.Option<?> option2 : useCaseConfig.listOptions()) {
                if (!option2.getId().equals(TargetConfig.OPTION_TARGET_NAME.getId())) {
                    create.insertOption(option2, useCaseConfig.getOptionPriority(option2), useCaseConfig.retrieveOption(option2));
                }
            }
        }
        if (create.containsOption(ImageOutputConfig.OPTION_TARGET_RESOLUTION)) {
            Config.Option<Integer> option3 = ImageOutputConfig.OPTION_TARGET_ASPECT_RATIO;
            if (create.containsOption(option3)) {
                create.removeOption(option3);
            }
        }
        return b(cameraInfoInternal, getUseCaseConfigBuilder(create));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final void notifyActive() {
        this.c = b.ACTIVE;
        notifyState();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final void notifyInactive() {
        this.c = b.INACTIVE;
        notifyState();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final void notifyReset() {
        for (StateChangeCallback stateChangeCallback : this.f656a) {
            stateChangeCallback.onUseCaseReset(this);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final void notifyState() {
        int i = a.f657a[this.c.ordinal()];
        if (i == 1) {
            for (StateChangeCallback stateChangeCallback : this.f656a) {
                stateChangeCallback.onUseCaseInactive(this);
            }
        } else if (i == 2) {
            for (StateChangeCallback stateChangeCallback2 : this.f656a) {
                stateChangeCallback2.onUseCaseActive(this);
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final void notifyUpdated() {
        for (StateChangeCallback stateChangeCallback : this.f656a) {
            stateChangeCallback.onUseCaseUpdated(this);
        }
    }

    @SuppressLint({"WrongConstant"})
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onAttach(@NonNull CameraInternal cameraInternal, @Nullable UseCaseConfig<?> useCaseConfig, @Nullable UseCaseConfig<?> useCaseConfig2) {
        synchronized (this.b) {
            this.j = cameraInternal;
            a(cameraInternal);
        }
        this.d = useCaseConfig;
        this.h = useCaseConfig2;
        UseCaseConfig<?> mergeConfigs = mergeConfigs(cameraInternal.getCameraInfoInternal(), this.d, this.h);
        this.f = mergeConfigs;
        EventCallback useCaseEventCallback = mergeConfigs.getUseCaseEventCallback(null);
        if (useCaseEventCallback != null) {
            useCaseEventCallback.onAttach(cameraInternal.getCameraInfoInternal());
        }
        onAttached();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onAttached() {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onCameraControlReady() {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void onDetach(@NonNull CameraInternal cameraInternal) {
        onDetached();
        EventCallback useCaseEventCallback = this.f.getUseCaseEventCallback(null);
        if (useCaseEventCallback != null) {
            useCaseEventCallback.onDetach();
        }
        synchronized (this.b) {
            Preconditions.checkArgument(cameraInternal == this.j);
            c(this.j);
            this.j = null;
        }
        this.g = null;
        this.i = null;
        this.f = this.e;
        this.d = null;
        this.h = null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onDetached() {
    }

    @CallSuper
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onStateAttached() {
        onCameraControlReady();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onStateDetached() {
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public abstract Size onSuggestedResolutionUpdated(@NonNull Size size);

    /* JADX WARN: Type inference failed for: r3v1, types: [androidx.camera.core.impl.UseCaseConfig, androidx.camera.core.impl.UseCaseConfig<?>] */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean setTargetRotationInternal(int i) {
        int targetRotation = ((ImageOutputConfig) getCurrentConfig()).getTargetRotation(-1);
        if (targetRotation == -1 || targetRotation != i) {
            UseCaseConfig.Builder<?, ?, ?> useCaseConfigBuilder = getUseCaseConfigBuilder(this.e);
            UseCaseConfigUtil.updateTargetRotationAndRelatedConfigs(useCaseConfigBuilder, i);
            this.e = useCaseConfigBuilder.getUseCaseConfig();
            CameraInternal camera = getCamera();
            if (camera == null) {
                this.f = this.e;
                return true;
            }
            this.f = mergeConfigs(camera.getCameraInfoInternal(), this.d, this.h);
            return true;
        }
        return false;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void setViewPortCropRect(@NonNull Rect rect) {
        this.i = rect;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void updateSessionConfig(@NonNull SessionConfig sessionConfig) {
        this.k = sessionConfig;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void updateSuggestedResolution(@NonNull Size size) {
        this.g = onSuggestedResolutionUpdated(size);
    }
}
