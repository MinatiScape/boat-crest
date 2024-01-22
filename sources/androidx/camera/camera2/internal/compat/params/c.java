package androidx.camera.camera2.internal.compat.params;

import android.hardware.camera2.params.OutputConfiguration;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.util.Preconditions;
@RequiresApi(28)
/* loaded from: classes.dex */
public class c extends b {
    public c(@NonNull Surface surface) {
        super(new OutputConfiguration(surface));
    }

    @RequiresApi(28)
    public static c o(@NonNull OutputConfiguration outputConfiguration) {
        return new c(outputConfiguration);
    }

    @Override // androidx.camera.camera2.internal.compat.params.b, androidx.camera.camera2.internal.compat.params.d, androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    public void b(@NonNull Surface surface) {
        ((OutputConfiguration) i()).removeSurface(surface);
    }

    @Override // androidx.camera.camera2.internal.compat.params.b, androidx.camera.camera2.internal.compat.params.a, androidx.camera.camera2.internal.compat.params.d, androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    public void c(@Nullable String str) {
        ((OutputConfiguration) i()).setPhysicalCameraId(str);
    }

    @Override // androidx.camera.camera2.internal.compat.params.b, androidx.camera.camera2.internal.compat.params.d, androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    public int d() {
        return ((OutputConfiguration) i()).getMaxSharedSurfaceCount();
    }

    @Override // androidx.camera.camera2.internal.compat.params.b, androidx.camera.camera2.internal.compat.params.a, androidx.camera.camera2.internal.compat.params.d, androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    @Nullable
    public String g() {
        return null;
    }

    @Override // androidx.camera.camera2.internal.compat.params.b, androidx.camera.camera2.internal.compat.params.a, androidx.camera.camera2.internal.compat.params.d, androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    public Object i() {
        Preconditions.checkArgument(this.f540a instanceof OutputConfiguration);
        return this.f540a;
    }

    public c(@NonNull Object obj) {
        super(obj);
    }
}
