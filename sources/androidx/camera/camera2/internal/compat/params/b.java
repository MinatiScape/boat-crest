package androidx.camera.camera2.internal.compat.params;

import android.hardware.camera2.params.OutputConfiguration;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.camera.core.Logger;
import androidx.core.util.Preconditions;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
@RequiresApi(26)
/* loaded from: classes.dex */
public class b extends androidx.camera.camera2.internal.compat.params.a {

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public final OutputConfiguration f539a;
        @Nullable
        public String b;

        public a(@NonNull OutputConfiguration outputConfiguration) {
            this.f539a = outputConfiguration;
        }

        public boolean equals(Object obj) {
            if (obj instanceof a) {
                a aVar = (a) obj;
                return Objects.equals(this.f539a, aVar.f539a) && Objects.equals(this.b, aVar.b);
            }
            return false;
        }

        public int hashCode() {
            int hashCode = this.f539a.hashCode() ^ 31;
            int i = (hashCode << 5) - hashCode;
            String str = this.b;
            return (str == null ? 0 : str.hashCode()) ^ i;
        }
    }

    public b(@NonNull Surface surface) {
        this(new a(new OutputConfiguration(surface)));
    }

    public static int l() throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = OutputConfiguration.class.getDeclaredField("MAX_SURFACES_COUNT");
        declaredField.setAccessible(true);
        return declaredField.getInt(null);
    }

    public static List<Surface> m(OutputConfiguration outputConfiguration) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = OutputConfiguration.class.getDeclaredField("mSurfaces");
        declaredField.setAccessible(true);
        return (List) declaredField.get(outputConfiguration);
    }

    @RequiresApi(26)
    public static b n(@NonNull OutputConfiguration outputConfiguration) {
        return new b(new a(outputConfiguration));
    }

    @Override // androidx.camera.camera2.internal.compat.params.d, androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    public void a(@NonNull Surface surface) {
        ((OutputConfiguration) i()).addSurface(surface);
    }

    @Override // androidx.camera.camera2.internal.compat.params.d, androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    public void b(@NonNull Surface surface) {
        if (getSurface() != surface) {
            try {
                if (m((OutputConfiguration) i()).remove(surface)) {
                    return;
                }
                throw new IllegalArgumentException("Surface is not part of this output configuration");
            } catch (IllegalAccessException | NoSuchFieldException e) {
                Logger.e("OutputConfigCompat", "Unable to remove surface from this output configuration.", e);
                return;
            }
        }
        throw new IllegalArgumentException("Cannot remove surface associated with this output configuration");
    }

    @Override // androidx.camera.camera2.internal.compat.params.a, androidx.camera.camera2.internal.compat.params.d, androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    public void c(@Nullable String str) {
        ((a) this.f540a).b = str;
    }

    @Override // androidx.camera.camera2.internal.compat.params.d, androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    public int d() {
        try {
            return l();
        } catch (IllegalAccessException | NoSuchFieldException e) {
            Logger.e("OutputConfigCompat", "Unable to retrieve max shared surface count.", e);
            return super.d();
        }
    }

    @Override // androidx.camera.camera2.internal.compat.params.a, androidx.camera.camera2.internal.compat.params.d, androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    @NonNull
    public List<Surface> e() {
        return ((OutputConfiguration) i()).getSurfaces();
    }

    @Override // androidx.camera.camera2.internal.compat.params.a, androidx.camera.camera2.internal.compat.params.d, androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    @Nullable
    public String g() {
        return ((a) this.f540a).b;
    }

    @Override // androidx.camera.camera2.internal.compat.params.a, androidx.camera.camera2.internal.compat.params.d, androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    public void h() {
        ((OutputConfiguration) i()).enableSurfaceSharing();
    }

    @Override // androidx.camera.camera2.internal.compat.params.a, androidx.camera.camera2.internal.compat.params.d, androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    public Object i() {
        Preconditions.checkArgument(this.f540a instanceof a);
        return ((a) this.f540a).f539a;
    }

    @Override // androidx.camera.camera2.internal.compat.params.a, androidx.camera.camera2.internal.compat.params.d
    public final boolean j() {
        throw new AssertionError("isSurfaceSharingEnabled() should not be called on API >= 26");
    }

    public b(@NonNull Object obj) {
        super(obj);
    }
}
