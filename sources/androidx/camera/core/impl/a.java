package androidx.camera.core.impl;

import android.os.Handler;
import androidx.annotation.NonNull;
import java.util.Objects;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class a extends CameraThreadConfig {

    /* renamed from: a  reason: collision with root package name */
    public final Executor f720a;
    public final Handler b;

    public a(Executor executor, Handler handler) {
        Objects.requireNonNull(executor, "Null cameraExecutor");
        this.f720a = executor;
        Objects.requireNonNull(handler, "Null schedulerHandler");
        this.b = handler;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CameraThreadConfig) {
            CameraThreadConfig cameraThreadConfig = (CameraThreadConfig) obj;
            return this.f720a.equals(cameraThreadConfig.getCameraExecutor()) && this.b.equals(cameraThreadConfig.getSchedulerHandler());
        }
        return false;
    }

    @Override // androidx.camera.core.impl.CameraThreadConfig
    @NonNull
    public Executor getCameraExecutor() {
        return this.f720a;
    }

    @Override // androidx.camera.core.impl.CameraThreadConfig
    @NonNull
    public Handler getSchedulerHandler() {
        return this.b;
    }

    public int hashCode() {
        return ((this.f720a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    public String toString() {
        return "CameraThreadConfig{cameraExecutor=" + this.f720a + ", schedulerHandler=" + this.b + "}";
    }
}
