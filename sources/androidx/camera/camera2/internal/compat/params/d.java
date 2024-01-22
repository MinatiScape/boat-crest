package androidx.camera.camera2.internal.compat.params;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Size;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat;
import androidx.camera.core.Logger;
import androidx.core.util.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
@RequiresApi(21)
/* loaded from: classes.dex */
public class d implements OutputConfigurationCompat.a {

    /* renamed from: a  reason: collision with root package name */
    public final Object f540a;

    /* loaded from: classes.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public final List<Surface> f541a;
        public final Size b;
        public final int c;
        public final int d;
        @Nullable
        public String e;
        public boolean f = false;

        public a(@NonNull Surface surface) {
            Preconditions.checkNotNull(surface, "Surface must not be null");
            this.f541a = Collections.singletonList(surface);
            this.b = c(surface);
            this.c = a(surface);
            this.d = b(surface);
        }

        @SuppressLint({"BlockedPrivateApi"})
        public static int a(@NonNull Surface surface) {
            try {
                Method declaredMethod = Class.forName("android.hardware.camera2.legacy.LegacyCameraDevice").getDeclaredMethod("detectSurfaceType", Surface.class);
                if (Build.VERSION.SDK_INT < 22) {
                    declaredMethod.setAccessible(true);
                }
                return ((Integer) declaredMethod.invoke(null, surface)).intValue();
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                Logger.e("OutputConfigCompat", "Unable to retrieve surface format.", e);
                return 0;
            }
        }

        @SuppressLint({"SoonBlockedPrivateApi"})
        public static int b(@NonNull Surface surface) {
            try {
                return ((Integer) Surface.class.getDeclaredMethod("getGenerationId", new Class[0]).invoke(surface, new Object[0])).intValue();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                Logger.e("OutputConfigCompat", "Unable to retrieve surface generation id.", e);
                return -1;
            }
        }

        @SuppressLint({"BlockedPrivateApi"})
        public static Size c(@NonNull Surface surface) {
            try {
                Method declaredMethod = Class.forName("android.hardware.camera2.legacy.LegacyCameraDevice").getDeclaredMethod("getSurfaceSize", Surface.class);
                declaredMethod.setAccessible(true);
                return (Size) declaredMethod.invoke(null, surface);
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                Logger.e("OutputConfigCompat", "Unable to retrieve surface size.", e);
                return null;
            }
        }

        public boolean equals(Object obj) {
            if (obj instanceof a) {
                a aVar = (a) obj;
                if (this.b.equals(aVar.b) && this.c == aVar.c && this.d == aVar.d && this.f == aVar.f && Objects.equals(this.e, aVar.e)) {
                    int min = Math.min(this.f541a.size(), aVar.f541a.size());
                    for (int i = 0; i < min; i++) {
                        if (this.f541a.get(i) != aVar.f541a.get(i)) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            int hashCode = this.f541a.hashCode() ^ 31;
            int i = this.d ^ ((hashCode << 5) - hashCode);
            int hashCode2 = this.b.hashCode() ^ ((i << 5) - i);
            int i2 = this.c ^ ((hashCode2 << 5) - hashCode2);
            int i3 = (this.f ? 1 : 0) ^ ((i2 << 5) - i2);
            int i4 = (i3 << 5) - i3;
            String str = this.e;
            return (str == null ? 0 : str.hashCode()) ^ i4;
        }
    }

    public d(@NonNull Surface surface) {
        this.f540a = new a(surface);
    }

    @Override // androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    public void a(@NonNull Surface surface) {
        Preconditions.checkNotNull(surface, "Surface must not be null");
        if (getSurface() != surface) {
            if (!j()) {
                throw new IllegalStateException("Cannot have 2 surfaces for a non-sharing configuration");
            }
            throw new IllegalArgumentException("Exceeds maximum number of surfaces");
        }
        throw new IllegalStateException("Surface is already added!");
    }

    @Override // androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    public void b(@NonNull Surface surface) {
        if (getSurface() == surface) {
            throw new IllegalArgumentException("Cannot remove surface associated with this output configuration");
        }
        throw new IllegalArgumentException("Surface is not part of this output configuration");
    }

    @Override // androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    public void c(@Nullable String str) {
        ((a) this.f540a).e = str;
    }

    @Override // androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    public int d() {
        return 1;
    }

    @Override // androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    @NonNull
    public List<Surface> e() {
        return ((a) this.f540a).f541a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof d) {
            return Objects.equals(this.f540a, ((d) obj).f540a);
        }
        return false;
    }

    @Override // androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    public int f() {
        return -1;
    }

    @Override // androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    @Nullable
    public String g() {
        return ((a) this.f540a).e;
    }

    @Override // androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    @Nullable
    public Surface getSurface() {
        List<Surface> list = ((a) this.f540a).f541a;
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override // androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    public void h() {
        ((a) this.f540a).f = true;
    }

    public int hashCode() {
        return this.f540a.hashCode();
    }

    @Override // androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    @Nullable
    public Object i() {
        return null;
    }

    public boolean j() {
        return ((a) this.f540a).f;
    }

    public d(@NonNull Object obj) {
        this.f540a = obj;
    }
}
