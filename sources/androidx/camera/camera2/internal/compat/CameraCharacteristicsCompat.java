package androidx.camera.camera2.internal.compat;

import android.hardware.camera2.CameraCharacteristics;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class CameraCharacteristicsCompat {
    @NonNull
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    public final Map<CameraCharacteristics.Key<?>, Object> f520a = new HashMap();
    @NonNull
    public final CameraCharacteristics b;

    public CameraCharacteristicsCompat(@NonNull CameraCharacteristics cameraCharacteristics) {
        this.b = cameraCharacteristics;
    }

    @NonNull
    @VisibleForTesting(otherwise = 3)
    public static CameraCharacteristicsCompat toCameraCharacteristicsCompat(@NonNull CameraCharacteristics cameraCharacteristics) {
        return new CameraCharacteristicsCompat(cameraCharacteristics);
    }

    @Nullable
    public <T> T get(@NonNull CameraCharacteristics.Key<T> key) {
        synchronized (this) {
            T t = (T) this.f520a.get(key);
            if (t != null) {
                return t;
            }
            T t2 = (T) this.b.get(key);
            if (t2 != null) {
                this.f520a.put(key, t2);
            }
            return t2;
        }
    }

    @NonNull
    public CameraCharacteristics toCameraCharacteristics() {
        return this.b;
    }
}
