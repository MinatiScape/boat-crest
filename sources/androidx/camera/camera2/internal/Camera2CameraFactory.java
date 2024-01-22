package androidx.camera.camera2.internal;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.InitializationException;
import androidx.camera.core.impl.CameraFactory;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CameraStateRegistry;
import androidx.camera.core.impl.CameraThreadConfig;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class Camera2CameraFactory implements CameraFactory {

    /* renamed from: a  reason: collision with root package name */
    public final CameraThreadConfig f499a;
    public final CameraManagerCompat c;
    public final List<String> d;
    public final Map<String, Camera2CameraInfoImpl> e = new HashMap();
    public final CameraStateRegistry b = new CameraStateRegistry(1);

    public Camera2CameraFactory(@NonNull Context context, @NonNull CameraThreadConfig cameraThreadConfig, @Nullable CameraSelector cameraSelector) throws InitializationException {
        this.f499a = cameraThreadConfig;
        this.c = CameraManagerCompat.from(context, cameraThreadConfig.getSchedulerHandler());
        this.d = o0.b(this, cameraSelector);
    }

    public Camera2CameraInfoImpl a(@NonNull String str) throws CameraUnavailableException {
        try {
            Camera2CameraInfoImpl camera2CameraInfoImpl = this.e.get(str);
            if (camera2CameraInfoImpl == null) {
                Camera2CameraInfoImpl camera2CameraInfoImpl2 = new Camera2CameraInfoImpl(str, this.c.getCameraCharacteristicsCompat(str));
                this.e.put(str, camera2CameraInfoImpl2);
                return camera2CameraInfoImpl2;
            }
            return camera2CameraInfoImpl;
        } catch (CameraAccessExceptionCompat e) {
            throw CameraUnavailableExceptionHelper.createFrom(e);
        }
    }

    @Override // androidx.camera.core.impl.CameraFactory
    @NonNull
    public Set<String> getAvailableCameraIds() {
        return new LinkedHashSet(this.d);
    }

    @Override // androidx.camera.core.impl.CameraFactory
    @NonNull
    public CameraInternal getCamera(@NonNull String str) throws CameraUnavailableException {
        if (this.d.contains(str)) {
            return new g0(this.c, str, a(str), this.b, this.f499a.getCameraExecutor(), this.f499a.getSchedulerHandler());
        }
        throw new IllegalArgumentException("The given camera id is not on the available camera id list.");
    }

    @Override // androidx.camera.core.impl.CameraFactory
    @NonNull
    public CameraManagerCompat getCameraManager() {
        return this.c;
    }
}
