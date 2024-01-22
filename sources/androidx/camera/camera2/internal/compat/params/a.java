package androidx.camera.camera2.internal.compat.params;

import android.hardware.camera2.params.OutputConfiguration;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.util.Preconditions;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
@RequiresApi(24)
/* loaded from: classes.dex */
public class a extends d {

    /* renamed from: androidx.camera.camera2.internal.compat.params.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0112a {

        /* renamed from: a  reason: collision with root package name */
        public final OutputConfiguration f538a;
        @Nullable
        public String b;
        public boolean c;

        public C0112a(@NonNull OutputConfiguration outputConfiguration) {
            this.f538a = outputConfiguration;
        }

        public boolean equals(Object obj) {
            if (obj instanceof C0112a) {
                C0112a c0112a = (C0112a) obj;
                return Objects.equals(this.f538a, c0112a.f538a) && this.c == c0112a.c && Objects.equals(this.b, c0112a.b);
            }
            return false;
        }

        public int hashCode() {
            int hashCode = this.f538a.hashCode() ^ 31;
            int i = (this.c ? 1 : 0) ^ ((hashCode << 5) - hashCode);
            int i2 = (i << 5) - i;
            String str = this.b;
            return (str == null ? 0 : str.hashCode()) ^ i2;
        }
    }

    public a(@NonNull Surface surface) {
        this(new C0112a(new OutputConfiguration(surface)));
    }

    @RequiresApi(24)
    public static a k(@NonNull OutputConfiguration outputConfiguration) {
        return new a(new C0112a(outputConfiguration));
    }

    @Override // androidx.camera.camera2.internal.compat.params.d, androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    public void c(@Nullable String str) {
        ((C0112a) this.f540a).b = str;
    }

    @Override // androidx.camera.camera2.internal.compat.params.d, androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    @NonNull
    public List<Surface> e() {
        return Collections.singletonList(getSurface());
    }

    @Override // androidx.camera.camera2.internal.compat.params.d, androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    public int f() {
        return ((OutputConfiguration) i()).getSurfaceGroupId();
    }

    @Override // androidx.camera.camera2.internal.compat.params.d, androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    @Nullable
    public String g() {
        return ((C0112a) this.f540a).b;
    }

    @Override // androidx.camera.camera2.internal.compat.params.d, androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    @Nullable
    public Surface getSurface() {
        return ((OutputConfiguration) i()).getSurface();
    }

    @Override // androidx.camera.camera2.internal.compat.params.d, androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    public void h() {
        ((C0112a) this.f540a).c = true;
    }

    @Override // androidx.camera.camera2.internal.compat.params.d, androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat.a
    public Object i() {
        Preconditions.checkArgument(this.f540a instanceof C0112a);
        return ((C0112a) this.f540a).f538a;
    }

    @Override // androidx.camera.camera2.internal.compat.params.d
    public boolean j() {
        return ((C0112a) this.f540a).c;
    }

    public a(@NonNull Object obj) {
        super(obj);
    }
}
