package androidx.camera.core.impl;

import androidx.annotation.NonNull;
import androidx.camera.core.impl.SurfaceConfig;
import java.util.Objects;
/* loaded from: classes.dex */
public final class d extends SurfaceConfig {

    /* renamed from: a  reason: collision with root package name */
    public final SurfaceConfig.ConfigType f723a;
    public final SurfaceConfig.ConfigSize b;

    public d(SurfaceConfig.ConfigType configType, SurfaceConfig.ConfigSize configSize) {
        Objects.requireNonNull(configType, "Null configType");
        this.f723a = configType;
        Objects.requireNonNull(configSize, "Null configSize");
        this.b = configSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SurfaceConfig) {
            SurfaceConfig surfaceConfig = (SurfaceConfig) obj;
            return this.f723a.equals(surfaceConfig.getConfigType()) && this.b.equals(surfaceConfig.getConfigSize());
        }
        return false;
    }

    @Override // androidx.camera.core.impl.SurfaceConfig
    @NonNull
    public SurfaceConfig.ConfigSize getConfigSize() {
        return this.b;
    }

    @Override // androidx.camera.core.impl.SurfaceConfig
    @NonNull
    public SurfaceConfig.ConfigType getConfigType() {
        return this.f723a;
    }

    public int hashCode() {
        return ((this.f723a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    public String toString() {
        return "SurfaceConfig{configType=" + this.f723a + ", configSize=" + this.b + "}";
    }
}
