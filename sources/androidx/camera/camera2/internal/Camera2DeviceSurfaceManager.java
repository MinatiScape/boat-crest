package androidx.camera.camera2.internal;

import android.content.Context;
import android.media.CamcorderProfile;
import android.util.Size;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.SurfaceConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class Camera2DeviceSurfaceManager implements CameraDeviceSurfaceManager {

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, p1> f502a;
    public final b b;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Camera2DeviceSurfaceManager(@NonNull Context context, @Nullable Object obj, @NonNull Set<String> set) throws CameraUnavailableException {
        this(context, new b() { // from class: androidx.camera.camera2.internal.l0
            @Override // androidx.camera.camera2.internal.b
            public final boolean a(int i, int i2) {
                return CamcorderProfile.hasProfile(i, i2);
            }
        }, obj, set);
    }

    public final void a(@NonNull Context context, @NonNull CameraManagerCompat cameraManagerCompat, @NonNull Set<String> set) throws CameraUnavailableException {
        Preconditions.checkNotNull(context);
        for (String str : set) {
            this.f502a.put(str, new p1(context, str, cameraManagerCompat, this.b));
        }
    }

    @Override // androidx.camera.core.impl.CameraDeviceSurfaceManager
    public boolean checkSupported(@NonNull String str, @Nullable List<SurfaceConfig> list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        p1 p1Var = this.f502a.get(str);
        if (p1Var != null) {
            return p1Var.b(list);
        }
        return false;
    }

    @Override // androidx.camera.core.impl.CameraDeviceSurfaceManager
    @NonNull
    public Map<UseCaseConfig<?>, Size> getSuggestedResolutions(@NonNull String str, @NonNull List<SurfaceConfig> list, @NonNull List<UseCaseConfig<?>> list2) {
        Preconditions.checkArgument(!list2.isEmpty(), "No new use cases to be bound.");
        ArrayList arrayList = new ArrayList(list);
        for (UseCaseConfig<?> useCaseConfig : list2) {
            arrayList.add(transformSurfaceConfig(str, useCaseConfig.getInputFormat(), new Size(640, 480)));
        }
        p1 p1Var = this.f502a.get(str);
        if (p1Var != null) {
            if (p1Var.b(arrayList)) {
                return p1Var.x(list, list2);
            }
            throw new IllegalArgumentException("No supported surface combination is found for camera device - Id : " + str + ".  May be attempting to bind too many use cases. Existing surfaces: " + list + " New configs: " + list2);
        }
        throw new IllegalArgumentException("No such camera id in supported combination list: " + str);
    }

    @Override // androidx.camera.core.impl.CameraDeviceSurfaceManager
    @Nullable
    public SurfaceConfig transformSurfaceConfig(@NonNull String str, int i, @NonNull Size size) {
        p1 p1Var = this.f502a.get(str);
        if (p1Var != null) {
            return p1Var.J(i, size);
        }
        return null;
    }

    public Camera2DeviceSurfaceManager(@NonNull Context context, @NonNull b bVar, @Nullable Object obj, @NonNull Set<String> set) throws CameraUnavailableException {
        CameraManagerCompat from;
        this.f502a = new HashMap();
        Preconditions.checkNotNull(bVar);
        this.b = bVar;
        if (obj instanceof CameraManagerCompat) {
            from = (CameraManagerCompat) obj;
        } else {
            from = CameraManagerCompat.from(context);
        }
        a(context, from, set);
    }
}
