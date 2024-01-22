package androidx.camera.camera2.internal.compat.params;

import android.annotation.SuppressLint;
import android.hardware.camera2.params.InputConfiguration;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import java.util.Objects;
@RequiresApi(21)
/* loaded from: classes.dex */
public final class InputConfigurationCompat {

    /* renamed from: a  reason: collision with root package name */
    public final c f531a;

    @VisibleForTesting
    /* loaded from: classes.dex */
    public static final class b implements c {

        /* renamed from: a  reason: collision with root package name */
        public final int f533a;
        public final int b;
        public final int c;

        public b(int i, int i2, int i3) {
            this.f533a = i;
            this.b = i2;
            this.c = i3;
        }

        @Override // androidx.camera.camera2.internal.compat.params.InputConfigurationCompat.c
        public Object a() {
            return null;
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                b bVar = (b) obj;
                return bVar.getWidth() == this.f533a && bVar.getHeight() == this.b && bVar.getFormat() == this.c;
            }
            return false;
        }

        @Override // androidx.camera.camera2.internal.compat.params.InputConfigurationCompat.c
        public int getFormat() {
            return this.c;
        }

        @Override // androidx.camera.camera2.internal.compat.params.InputConfigurationCompat.c
        public int getHeight() {
            return this.b;
        }

        @Override // androidx.camera.camera2.internal.compat.params.InputConfigurationCompat.c
        public int getWidth() {
            return this.f533a;
        }

        public int hashCode() {
            int i = this.f533a ^ 31;
            int i2 = this.b ^ ((i << 5) - i);
            return this.c ^ ((i2 << 5) - i2);
        }

        @SuppressLint({"DefaultLocale"})
        public String toString() {
            return String.format("InputConfiguration(w:%d, h:%d, format:%d)", Integer.valueOf(this.f533a), Integer.valueOf(this.b), Integer.valueOf(this.c));
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        @Nullable
        Object a();

        int getFormat();

        int getHeight();

        int getWidth();
    }

    public InputConfigurationCompat(int i, int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.f531a = new a(i, i2, i3);
        } else {
            this.f531a = new b(i, i2, i3);
        }
    }

    @Nullable
    public static InputConfigurationCompat wrap(@Nullable Object obj) {
        if (obj != null && Build.VERSION.SDK_INT >= 23) {
            return new InputConfigurationCompat(new a(obj));
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (obj instanceof InputConfigurationCompat) {
            return this.f531a.equals(((InputConfigurationCompat) obj).f531a);
        }
        return false;
    }

    public int getFormat() {
        return this.f531a.getFormat();
    }

    public int getHeight() {
        return this.f531a.getHeight();
    }

    public int getWidth() {
        return this.f531a.getWidth();
    }

    public int hashCode() {
        return this.f531a.hashCode();
    }

    public String toString() {
        return this.f531a.toString();
    }

    @Nullable
    public Object unwrap() {
        return this.f531a.a();
    }

    @RequiresApi(23)
    /* loaded from: classes.dex */
    public static final class a implements c {

        /* renamed from: a  reason: collision with root package name */
        public final InputConfiguration f532a;

        public a(@NonNull Object obj) {
            this.f532a = (InputConfiguration) obj;
        }

        @Override // androidx.camera.camera2.internal.compat.params.InputConfigurationCompat.c
        @Nullable
        public Object a() {
            return this.f532a;
        }

        public boolean equals(Object obj) {
            if (obj instanceof c) {
                return Objects.equals(this.f532a, ((c) obj).a());
            }
            return false;
        }

        @Override // androidx.camera.camera2.internal.compat.params.InputConfigurationCompat.c
        public int getFormat() {
            return this.f532a.getFormat();
        }

        @Override // androidx.camera.camera2.internal.compat.params.InputConfigurationCompat.c
        public int getHeight() {
            return this.f532a.getHeight();
        }

        @Override // androidx.camera.camera2.internal.compat.params.InputConfigurationCompat.c
        public int getWidth() {
            return this.f532a.getWidth();
        }

        public int hashCode() {
            return this.f532a.hashCode();
        }

        public String toString() {
            return this.f532a.toString();
        }

        public a(int i, int i2, int i3) {
            this(new InputConfiguration(i, i2, i3));
        }
    }

    public InputConfigurationCompat(@NonNull c cVar) {
        this.f531a = cVar;
    }
}
