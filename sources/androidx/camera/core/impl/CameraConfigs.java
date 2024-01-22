package androidx.camera.core.impl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.impl.UseCaseConfigFactory;
/* loaded from: classes.dex */
public class CameraConfigs {

    /* renamed from: a  reason: collision with root package name */
    public static final CameraConfig f692a = new a();

    /* loaded from: classes.dex */
    public static final class a implements CameraConfig {

        /* renamed from: a  reason: collision with root package name */
        public final UseCaseConfigFactory f693a = new C0114a(this);

        /* renamed from: androidx.camera.core.impl.CameraConfigs$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0114a implements UseCaseConfigFactory {
            public C0114a(a aVar) {
            }

            @Override // androidx.camera.core.impl.UseCaseConfigFactory
            @Nullable
            public Config getConfig(@NonNull UseCaseConfigFactory.CaptureType captureType) {
                return null;
            }
        }

        @Override // androidx.camera.core.impl.ReadableConfig
        @NonNull
        public Config getConfig() {
            return OptionsBundle.emptyBundle();
        }

        @Override // androidx.camera.core.impl.CameraConfig
        @NonNull
        public UseCaseConfigFactory getUseCaseConfigFactory() {
            return this.f693a;
        }
    }

    @NonNull
    public static CameraConfig emptyConfig() {
        return f692a;
    }
}
