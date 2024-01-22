package androidx.transition;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
/* loaded from: classes.dex */
public class b0 {

    /* renamed from: a  reason: collision with root package name */
    public static final h0 f1709a;
    public static final Property<View, Float> b;
    public static final Property<View, Rect> c;

    /* loaded from: classes.dex */
    public static class a extends Property<View, Float> {
        public a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public Float get(View view) {
            return Float.valueOf(b0.c(view));
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(View view, Float f) {
            b0.h(view, f.floatValue());
        }
    }

    /* loaded from: classes.dex */
    public static class b extends Property<View, Rect> {
        public b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public Rect get(View view) {
            return ViewCompat.getClipBounds(view);
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(View view, Rect rect) {
            ViewCompat.setClipBounds(view, rect);
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            f1709a = new g0();
        } else if (i >= 23) {
            f1709a = new f0();
        } else if (i >= 22) {
            f1709a = new e0();
        } else if (i >= 21) {
            f1709a = new d0();
        } else if (i >= 19) {
            f1709a = new c0();
        } else {
            f1709a = new h0();
        }
        b = new a(Float.class, "translationAlpha");
        c = new b(Rect.class, "clipBounds");
    }

    public static void a(@NonNull View view) {
        f1709a.a(view);
    }

    public static a0 b(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 18) {
            return new z(view);
        }
        return y.c(view);
    }

    public static float c(@NonNull View view) {
        return f1709a.c(view);
    }

    public static k0 d(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= 18) {
            return new j0(view);
        }
        return new i0(view.getWindowToken());
    }

    public static void e(@NonNull View view) {
        f1709a.d(view);
    }

    public static void f(@NonNull View view, @Nullable Matrix matrix) {
        f1709a.e(view, matrix);
    }

    public static void g(@NonNull View view, int i, int i2, int i3, int i4) {
        f1709a.f(view, i, i2, i3, i4);
    }

    public static void h(@NonNull View view, float f) {
        f1709a.g(view, f);
    }

    public static void i(@NonNull View view, int i) {
        f1709a.h(view, i);
    }

    public static void j(@NonNull View view, @NonNull Matrix matrix) {
        f1709a.i(view, matrix);
    }

    public static void k(@NonNull View view, @NonNull Matrix matrix) {
        f1709a.j(view, matrix);
    }
}
