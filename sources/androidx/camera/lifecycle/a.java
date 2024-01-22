package androidx.camera.lifecycle;

import androidx.annotation.NonNull;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import androidx.camera.lifecycle.LifecycleCameraRepository;
import androidx.lifecycle.LifecycleOwner;
import java.util.Objects;
/* loaded from: classes.dex */
public final class a extends LifecycleCameraRepository.a {

    /* renamed from: a  reason: collision with root package name */
    public final LifecycleOwner f796a;
    public final CameraUseCaseAdapter.CameraId b;

    public a(LifecycleOwner lifecycleOwner, CameraUseCaseAdapter.CameraId cameraId) {
        Objects.requireNonNull(lifecycleOwner, "Null lifecycleOwner");
        this.f796a = lifecycleOwner;
        Objects.requireNonNull(cameraId, "Null cameraId");
        this.b = cameraId;
    }

    @Override // androidx.camera.lifecycle.LifecycleCameraRepository.a
    @NonNull
    public CameraUseCaseAdapter.CameraId b() {
        return this.b;
    }

    @Override // androidx.camera.lifecycle.LifecycleCameraRepository.a
    @NonNull
    public LifecycleOwner c() {
        return this.f796a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof LifecycleCameraRepository.a) {
            LifecycleCameraRepository.a aVar = (LifecycleCameraRepository.a) obj;
            return this.f796a.equals(aVar.c()) && this.b.equals(aVar.b());
        }
        return false;
    }

    public int hashCode() {
        return ((this.f796a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    public String toString() {
        return "Key{lifecycleOwner=" + this.f796a + ", cameraId=" + this.b + "}";
    }
}
