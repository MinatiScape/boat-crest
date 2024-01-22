package androidx.camera.camera2.internal.compat.params;

import android.hardware.camera2.params.OutputConfiguration;
import android.os.Build;
import android.util.Size;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.util.List;
@RequiresApi(21)
/* loaded from: classes.dex */
public final class OutputConfigurationCompat {
    public static final int SURFACE_GROUP_ID_NONE = -1;

    /* renamed from: a  reason: collision with root package name */
    public final a f534a;

    /* loaded from: classes.dex */
    public interface a {
        void a(@NonNull Surface surface);

        void b(@NonNull Surface surface);

        void c(@Nullable String str);

        int d();

        List<Surface> e();

        int f();

        @Nullable
        String g();

        @Nullable
        Surface getSurface();

        void h();

        @Nullable
        Object i();
    }

    public OutputConfigurationCompat(@NonNull Surface surface) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            this.f534a = new c(surface);
        } else if (i >= 26) {
            this.f534a = new b(surface);
        } else if (i >= 24) {
            this.f534a = new androidx.camera.camera2.internal.compat.params.a(surface);
        } else {
            this.f534a = new d(surface);
        }
    }

    @Nullable
    public static OutputConfigurationCompat wrap(@Nullable Object obj) {
        a k;
        if (obj == null) {
            return null;
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            k = c.o((OutputConfiguration) obj);
        } else if (i >= 26) {
            k = b.n((OutputConfiguration) obj);
        } else {
            k = i >= 24 ? androidx.camera.camera2.internal.compat.params.a.k((OutputConfiguration) obj) : null;
        }
        if (k == null) {
            return null;
        }
        return new OutputConfigurationCompat(k);
    }

    public void addSurface(@NonNull Surface surface) {
        this.f534a.a(surface);
    }

    public void enableSurfaceSharing() {
        this.f534a.h();
    }

    public boolean equals(Object obj) {
        if (obj instanceof OutputConfigurationCompat) {
            return this.f534a.equals(((OutputConfigurationCompat) obj).f534a);
        }
        return false;
    }

    public int getMaxSharedSurfaceCount() {
        return this.f534a.d();
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public String getPhysicalCameraId() {
        return this.f534a.g();
    }

    @Nullable
    public Surface getSurface() {
        return this.f534a.getSurface();
    }

    public int getSurfaceGroupId() {
        return this.f534a.f();
    }

    @NonNull
    public List<Surface> getSurfaces() {
        return this.f534a.e();
    }

    public int hashCode() {
        return this.f534a.hashCode();
    }

    public void removeSurface(@NonNull Surface surface) {
        this.f534a.b(surface);
    }

    public void setPhysicalCameraId(@Nullable String str) {
        this.f534a.c(str);
    }

    @Nullable
    public Object unwrap() {
        return this.f534a.i();
    }

    @RequiresApi(26)
    public <T> OutputConfigurationCompat(@NonNull Size size, @NonNull Class<T> cls) {
        OutputConfiguration outputConfiguration = new OutputConfiguration(size, cls);
        if (Build.VERSION.SDK_INT >= 28) {
            this.f534a = c.o(outputConfiguration);
        } else {
            this.f534a = b.n(outputConfiguration);
        }
    }

    public OutputConfigurationCompat(@NonNull a aVar) {
        this.f534a = aVar;
    }
}
