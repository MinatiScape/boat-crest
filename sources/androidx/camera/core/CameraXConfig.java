package androidx.camera.core;

import android.os.Handler;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.CameraFactory;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.internal.TargetConfig;
import java.util.UUID;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class CameraXConfig implements TargetConfig<CameraX> {
    public static final Config.Option<CameraFactory.Provider> b = Config.Option.create("camerax.core.appConfig.cameraFactoryProvider", CameraFactory.Provider.class);
    public static final Config.Option<CameraDeviceSurfaceManager.Provider> c = Config.Option.create("camerax.core.appConfig.deviceSurfaceManagerProvider", CameraDeviceSurfaceManager.Provider.class);
    public static final Config.Option<UseCaseConfigFactory.Provider> d = Config.Option.create("camerax.core.appConfig.useCaseConfigFactoryProvider", UseCaseConfigFactory.Provider.class);
    public static final Config.Option<Executor> e = Config.Option.create("camerax.core.appConfig.cameraExecutor", Executor.class);
    public static final Config.Option<Handler> f = Config.Option.create("camerax.core.appConfig.schedulerHandler", Handler.class);
    public static final Config.Option<Integer> g = Config.Option.create("camerax.core.appConfig.minimumLoggingLevel", Integer.TYPE);
    public static final Config.Option<CameraSelector> h = Config.Option.create("camerax.core.appConfig.availableCamerasLimiter", CameraSelector.class);

    /* renamed from: a  reason: collision with root package name */
    public final OptionsBundle f620a;

    /* loaded from: classes.dex */
    public static final class Builder implements TargetConfig.Builder<CameraX, Builder> {

        /* renamed from: a  reason: collision with root package name */
        public final MutableOptionsBundle f621a;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder() {
            this(MutableOptionsBundle.create());
        }

        @NonNull
        public static Builder fromConfig(@NonNull CameraXConfig cameraXConfig) {
            return new Builder(MutableOptionsBundle.from((Config) cameraXConfig));
        }

        @NonNull
        public final MutableConfig a() {
            return this.f621a;
        }

        @NonNull
        public CameraXConfig build() {
            return new CameraXConfig(OptionsBundle.from(this.f621a));
        }

        @NonNull
        @ExperimentalAvailableCamerasLimiter
        public Builder setAvailableCamerasLimiter(@NonNull CameraSelector cameraSelector) {
            a().insertOption(CameraXConfig.h, cameraSelector);
            return this;
        }

        @NonNull
        public Builder setCameraExecutor(@NonNull Executor executor) {
            a().insertOption(CameraXConfig.e, executor);
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setCameraFactoryProvider(@NonNull CameraFactory.Provider provider) {
            a().insertOption(CameraXConfig.b, provider);
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setDeviceSurfaceManagerProvider(@NonNull CameraDeviceSurfaceManager.Provider provider) {
            a().insertOption(CameraXConfig.c, provider);
            return this;
        }

        @NonNull
        @ExperimentalLogging
        public Builder setMinimumLoggingLevel(@IntRange(from = 3, to = 6) int i) {
            a().insertOption(CameraXConfig.g, Integer.valueOf(i));
            return this;
        }

        @NonNull
        @ExperimentalCustomizableThreads
        public Builder setSchedulerHandler(@NonNull Handler handler) {
            a().insertOption(CameraXConfig.f, handler);
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setUseCaseConfigFactoryProvider(@NonNull UseCaseConfigFactory.Provider provider) {
            a().insertOption(CameraXConfig.d, provider);
            return this;
        }

        public Builder(MutableOptionsBundle mutableOptionsBundle) {
            this.f621a = mutableOptionsBundle;
            Class cls = (Class) mutableOptionsBundle.retrieveOption(TargetConfig.OPTION_TARGET_CLASS, null);
            if (cls != null && !cls.equals(CameraX.class)) {
                throw new IllegalArgumentException("Invalid target class configuration for " + this + ": " + cls);
            }
            setTargetClass(CameraX.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.internal.TargetConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setTargetClass(@NonNull Class<CameraX> cls) {
            a().insertOption(TargetConfig.OPTION_TARGET_CLASS, cls);
            if (a().retrieveOption(TargetConfig.OPTION_TARGET_NAME, null) == null) {
                setTargetName(cls.getCanonicalName() + "-" + UUID.randomUUID());
            }
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.internal.TargetConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setTargetName(@NonNull String str) {
            a().insertOption(TargetConfig.OPTION_TARGET_NAME, str);
            return this;
        }
    }

    /* loaded from: classes.dex */
    public interface Provider {
        @NonNull
        CameraXConfig getCameraXConfig();
    }

    public CameraXConfig(OptionsBundle optionsBundle) {
        this.f620a = optionsBundle;
    }

    @Nullable
    @ExperimentalAvailableCamerasLimiter
    public CameraSelector getAvailableCamerasLimiter(@Nullable CameraSelector cameraSelector) {
        return (CameraSelector) this.f620a.retrieveOption(h, cameraSelector);
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Executor getCameraExecutor(@Nullable Executor executor) {
        return (Executor) this.f620a.retrieveOption(e, executor);
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CameraFactory.Provider getCameraFactoryProvider(@Nullable CameraFactory.Provider provider) {
        return (CameraFactory.Provider) this.f620a.retrieveOption(b, provider);
    }

    @Override // androidx.camera.core.impl.ReadableConfig
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Config getConfig() {
        return this.f620a;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CameraDeviceSurfaceManager.Provider getDeviceSurfaceManagerProvider(@Nullable CameraDeviceSurfaceManager.Provider provider) {
        return (CameraDeviceSurfaceManager.Provider) this.f620a.retrieveOption(c, provider);
    }

    @ExperimentalLogging
    public int getMinimumLoggingLevel() {
        return ((Integer) this.f620a.retrieveOption(g, 3)).intValue();
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Handler getSchedulerHandler(@Nullable Handler handler) {
        return (Handler) this.f620a.retrieveOption(f, handler);
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public UseCaseConfigFactory.Provider getUseCaseConfigFactoryProvider(@Nullable UseCaseConfigFactory.Provider provider) {
        return (UseCaseConfigFactory.Provider) this.f620a.retrieveOption(d, provider);
    }
}
