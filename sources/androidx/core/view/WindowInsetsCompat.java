package androidx.core.view;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.Insets;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;
/* loaded from: classes.dex */
public class WindowInsetsCompat {
    @NonNull
    public static final WindowInsetsCompat CONSUMED;

    /* renamed from: a  reason: collision with root package name */
    public final k f1168a;

    /* loaded from: classes.dex */
    public static final class Type {

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        /* loaded from: classes.dex */
        public @interface InsetsType {
        }

        @SuppressLint({"WrongConstant"})
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static int a() {
            return -1;
        }

        public static int b(int i) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 4) {
                        if (i != 8) {
                            if (i != 16) {
                                if (i != 32) {
                                    if (i != 64) {
                                        if (i != 128) {
                                            if (i == 256) {
                                                return 8;
                                            }
                                            throw new IllegalArgumentException("type needs to be >= FIRST and <= LAST, type=" + i);
                                        }
                                        return 7;
                                    }
                                    return 6;
                                }
                                return 5;
                            }
                            return 4;
                        }
                        return 3;
                    }
                    return 2;
                }
                return 1;
            }
            return 0;
        }

        public static int captionBar() {
            return 4;
        }

        public static int displayCutout() {
            return 128;
        }

        public static int ime() {
            return 8;
        }

        public static int mandatorySystemGestures() {
            return 32;
        }

        public static int navigationBars() {
            return 2;
        }

        public static int statusBars() {
            return 1;
        }

        public static int systemBars() {
            return 7;
        }

        public static int systemGestures() {
            return 16;
        }

        public static int tappableElement() {
            return 64;
        }
    }

    @RequiresApi(21)
    @SuppressLint({"SoonBlockedPrivateApi"})
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static Field f1170a;
        public static Field b;
        public static Field c;
        public static boolean d;

        static {
            try {
                Field declaredField = View.class.getDeclaredField("mAttachInfo");
                f1170a = declaredField;
                declaredField.setAccessible(true);
                Class<?> cls = Class.forName("android.view.View$AttachInfo");
                Field declaredField2 = cls.getDeclaredField("mStableInsets");
                b = declaredField2;
                declaredField2.setAccessible(true);
                Field declaredField3 = cls.getDeclaredField("mContentInsets");
                c = declaredField3;
                declaredField3.setAccessible(true);
                d = true;
            } catch (ReflectiveOperationException e) {
                Log.w("WindowInsetsCompat", "Failed to get visible insets from AttachInfo " + e.getMessage(), e);
            }
        }

        @Nullable
        public static WindowInsetsCompat a(@NonNull View view) {
            if (d && view.isAttachedToWindow()) {
                try {
                    Object obj = f1170a.get(view.getRootView());
                    if (obj != null) {
                        Rect rect = (Rect) b.get(obj);
                        Rect rect2 = (Rect) c.get(obj);
                        if (rect != null && rect2 != null) {
                            WindowInsetsCompat build = new Builder().setStableInsets(Insets.of(rect)).setSystemWindowInsets(Insets.of(rect2)).build();
                            build.e(build);
                            build.a(view.getRootView());
                            return build;
                        }
                    }
                } catch (IllegalAccessException e) {
                    Log.w("WindowInsetsCompat", "Failed to get insets from AttachInfo. " + e.getMessage(), e);
                }
            }
            return null;
        }
    }

    @RequiresApi(30)
    /* loaded from: classes.dex */
    public static class d extends c {
        public d() {
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        public void d(int i, @NonNull Insets insets) {
            this.c.setInsets(l.a(i), insets.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        public void e(int i, @NonNull Insets insets) {
            this.c.setInsetsIgnoringVisibility(l.a(i), insets.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        public void k(int i, boolean z) {
            this.c.setVisible(l.a(i), z);
        }

        public d(@NonNull WindowInsetsCompat windowInsetsCompat) {
            super(windowInsetsCompat);
        }
    }

    /* loaded from: classes.dex */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        public final WindowInsetsCompat f1171a;
        public Insets[] b;

        public e() {
            this(new WindowInsetsCompat((WindowInsetsCompat) null));
        }

        public final void a() {
            Insets[] insetsArr = this.b;
            if (insetsArr != null) {
                Insets insets = insetsArr[Type.b(1)];
                Insets insets2 = this.b[Type.b(2)];
                if (insets2 == null) {
                    insets2 = this.f1171a.getInsets(2);
                }
                if (insets == null) {
                    insets = this.f1171a.getInsets(1);
                }
                i(Insets.max(insets, insets2));
                Insets insets3 = this.b[Type.b(16)];
                if (insets3 != null) {
                    h(insets3);
                }
                Insets insets4 = this.b[Type.b(32)];
                if (insets4 != null) {
                    f(insets4);
                }
                Insets insets5 = this.b[Type.b(64)];
                if (insets5 != null) {
                    j(insets5);
                }
            }
        }

        @NonNull
        public WindowInsetsCompat b() {
            a();
            return this.f1171a;
        }

        public void c(@Nullable DisplayCutoutCompat displayCutoutCompat) {
        }

        public void d(int i, @NonNull Insets insets) {
            if (this.b == null) {
                this.b = new Insets[9];
            }
            for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                if ((i & i2) != 0) {
                    this.b[Type.b(i2)] = insets;
                }
            }
        }

        public void e(int i, @NonNull Insets insets) {
            if (i == 8) {
                throw new IllegalArgumentException("Ignoring visibility inset not available for IME");
            }
        }

        public void f(@NonNull Insets insets) {
        }

        public void g(@NonNull Insets insets) {
        }

        public void h(@NonNull Insets insets) {
        }

        public void i(@NonNull Insets insets) {
        }

        public void j(@NonNull Insets insets) {
        }

        public void k(int i, boolean z) {
        }

        public e(@NonNull WindowInsetsCompat windowInsetsCompat) {
            this.f1171a = windowInsetsCompat;
        }
    }

    @RequiresApi(28)
    /* loaded from: classes.dex */
    public static class h extends g {
        public h(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull WindowInsets windowInsets) {
            super(windowInsetsCompat, windowInsets);
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        public WindowInsetsCompat a() {
            return WindowInsetsCompat.toWindowInsetsCompat(this.c.consumeDisplayCutout());
        }

        @Override // androidx.core.view.WindowInsetsCompat.f, androidx.core.view.WindowInsetsCompat.k
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof h) {
                h hVar = (h) obj;
                return Objects.equals(this.c, hVar.c) && Objects.equals(this.g, hVar.g);
            }
            return false;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @Nullable
        public DisplayCutoutCompat f() {
            return DisplayCutoutCompat.c(this.c.getDisplayCutout());
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        public int hashCode() {
            return this.c.hashCode();
        }

        public h(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull h hVar) {
            super(windowInsetsCompat, hVar);
        }
    }

    @RequiresApi(30)
    /* loaded from: classes.dex */
    public static class j extends i {
        @NonNull
        public static final WindowInsetsCompat q = WindowInsetsCompat.toWindowInsetsCompat(WindowInsets.CONSUMED);

        public j(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull WindowInsets windowInsets) {
            super(windowInsetsCompat, windowInsets);
        }

        @Override // androidx.core.view.WindowInsetsCompat.f, androidx.core.view.WindowInsetsCompat.k
        public final void d(@NonNull View view) {
        }

        @Override // androidx.core.view.WindowInsetsCompat.f, androidx.core.view.WindowInsetsCompat.k
        @NonNull
        public Insets g(int i) {
            return Insets.toCompatInsets(this.c.getInsets(l.a(i)));
        }

        @Override // androidx.core.view.WindowInsetsCompat.f, androidx.core.view.WindowInsetsCompat.k
        @NonNull
        public Insets h(int i) {
            return Insets.toCompatInsets(this.c.getInsetsIgnoringVisibility(l.a(i)));
        }

        @Override // androidx.core.view.WindowInsetsCompat.f, androidx.core.view.WindowInsetsCompat.k
        public boolean q(int i) {
            return this.c.isVisible(l.a(i));
        }

        public j(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull j jVar) {
            super(windowInsetsCompat, jVar);
        }
    }

    /* loaded from: classes.dex */
    public static class k {
        @NonNull
        public static final WindowInsetsCompat b = new Builder().build().consumeDisplayCutout().consumeStableInsets().consumeSystemWindowInsets();

        /* renamed from: a  reason: collision with root package name */
        public final WindowInsetsCompat f1172a;

        public k(@NonNull WindowInsetsCompat windowInsetsCompat) {
            this.f1172a = windowInsetsCompat;
        }

        @NonNull
        public WindowInsetsCompat a() {
            return this.f1172a;
        }

        @NonNull
        public WindowInsetsCompat b() {
            return this.f1172a;
        }

        @NonNull
        public WindowInsetsCompat c() {
            return this.f1172a;
        }

        public void d(@NonNull View view) {
        }

        public void e(@NonNull WindowInsetsCompat windowInsetsCompat) {
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof k) {
                k kVar = (k) obj;
                return p() == kVar.p() && o() == kVar.o() && ObjectsCompat.equals(l(), kVar.l()) && ObjectsCompat.equals(j(), kVar.j()) && ObjectsCompat.equals(f(), kVar.f());
            }
            return false;
        }

        @Nullable
        public DisplayCutoutCompat f() {
            return null;
        }

        @NonNull
        public Insets g(int i) {
            return Insets.NONE;
        }

        @NonNull
        public Insets h(int i) {
            if ((i & 8) == 0) {
                return Insets.NONE;
            }
            throw new IllegalArgumentException("Unable to query the maximum insets for IME");
        }

        public int hashCode() {
            return ObjectsCompat.hash(Boolean.valueOf(p()), Boolean.valueOf(o()), l(), j(), f());
        }

        @NonNull
        public Insets i() {
            return l();
        }

        @NonNull
        public Insets j() {
            return Insets.NONE;
        }

        @NonNull
        public Insets k() {
            return l();
        }

        @NonNull
        public Insets l() {
            return Insets.NONE;
        }

        @NonNull
        public Insets m() {
            return l();
        }

        @NonNull
        public WindowInsetsCompat n(int i, int i2, int i3, int i4) {
            return b;
        }

        public boolean o() {
            return false;
        }

        public boolean p() {
            return false;
        }

        public boolean q(int i) {
            return true;
        }

        public void r(Insets[] insetsArr) {
        }

        public void s(@NonNull Insets insets) {
        }

        public void t(@Nullable WindowInsetsCompat windowInsetsCompat) {
        }

        public void u(Insets insets) {
        }
    }

    @RequiresApi(30)
    /* loaded from: classes.dex */
    public static final class l {
        public static int a(int i) {
            int statusBars;
            int i2 = 0;
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if ((i & i3) != 0) {
                    if (i3 == 1) {
                        statusBars = WindowInsets.Type.statusBars();
                    } else if (i3 == 2) {
                        statusBars = WindowInsets.Type.navigationBars();
                    } else if (i3 == 4) {
                        statusBars = WindowInsets.Type.captionBar();
                    } else if (i3 == 8) {
                        statusBars = WindowInsets.Type.ime();
                    } else if (i3 == 16) {
                        statusBars = WindowInsets.Type.systemGestures();
                    } else if (i3 == 32) {
                        statusBars = WindowInsets.Type.mandatorySystemGestures();
                    } else if (i3 == 64) {
                        statusBars = WindowInsets.Type.tappableElement();
                    } else if (i3 == 128) {
                        statusBars = WindowInsets.Type.displayCutout();
                    }
                    i2 |= statusBars;
                }
            }
            return i2;
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 30) {
            CONSUMED = j.q;
        } else {
            CONSUMED = k.b;
        }
    }

    @RequiresApi(20)
    public WindowInsetsCompat(@NonNull WindowInsets windowInsets) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            this.f1168a = new j(this, windowInsets);
        } else if (i2 >= 29) {
            this.f1168a = new i(this, windowInsets);
        } else if (i2 >= 28) {
            this.f1168a = new h(this, windowInsets);
        } else if (i2 >= 21) {
            this.f1168a = new g(this, windowInsets);
        } else if (i2 >= 20) {
            this.f1168a = new f(this, windowInsets);
        } else {
            this.f1168a = new k(this);
        }
    }

    public static Insets b(@NonNull Insets insets, int i2, int i3, int i4, int i5) {
        int max = Math.max(0, insets.left - i2);
        int max2 = Math.max(0, insets.top - i3);
        int max3 = Math.max(0, insets.right - i4);
        int max4 = Math.max(0, insets.bottom - i5);
        return (max == i2 && max2 == i3 && max3 == i4 && max4 == i5) ? insets : Insets.of(max, max2, max3, max4);
    }

    @NonNull
    @RequiresApi(20)
    public static WindowInsetsCompat toWindowInsetsCompat(@NonNull WindowInsets windowInsets) {
        return toWindowInsetsCompat(windowInsets, null);
    }

    public void a(@NonNull View view) {
        this.f1168a.d(view);
    }

    public void c(Insets[] insetsArr) {
        this.f1168a.r(insetsArr);
    }

    @NonNull
    @Deprecated
    public WindowInsetsCompat consumeDisplayCutout() {
        return this.f1168a.a();
    }

    @NonNull
    @Deprecated
    public WindowInsetsCompat consumeStableInsets() {
        return this.f1168a.b();
    }

    @NonNull
    @Deprecated
    public WindowInsetsCompat consumeSystemWindowInsets() {
        return this.f1168a.c();
    }

    public void d(@NonNull Insets insets) {
        this.f1168a.s(insets);
    }

    public void e(@Nullable WindowInsetsCompat windowInsetsCompat) {
        this.f1168a.t(windowInsetsCompat);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WindowInsetsCompat) {
            return ObjectsCompat.equals(this.f1168a, ((WindowInsetsCompat) obj).f1168a);
        }
        return false;
    }

    public void f(@Nullable Insets insets) {
        this.f1168a.u(insets);
    }

    @Nullable
    public DisplayCutoutCompat getDisplayCutout() {
        return this.f1168a.f();
    }

    @NonNull
    public Insets getInsets(int i2) {
        return this.f1168a.g(i2);
    }

    @NonNull
    public Insets getInsetsIgnoringVisibility(int i2) {
        return this.f1168a.h(i2);
    }

    @NonNull
    @Deprecated
    public Insets getMandatorySystemGestureInsets() {
        return this.f1168a.i();
    }

    @Deprecated
    public int getStableInsetBottom() {
        return this.f1168a.j().bottom;
    }

    @Deprecated
    public int getStableInsetLeft() {
        return this.f1168a.j().left;
    }

    @Deprecated
    public int getStableInsetRight() {
        return this.f1168a.j().right;
    }

    @Deprecated
    public int getStableInsetTop() {
        return this.f1168a.j().top;
    }

    @NonNull
    @Deprecated
    public Insets getStableInsets() {
        return this.f1168a.j();
    }

    @NonNull
    @Deprecated
    public Insets getSystemGestureInsets() {
        return this.f1168a.k();
    }

    @Deprecated
    public int getSystemWindowInsetBottom() {
        return this.f1168a.l().bottom;
    }

    @Deprecated
    public int getSystemWindowInsetLeft() {
        return this.f1168a.l().left;
    }

    @Deprecated
    public int getSystemWindowInsetRight() {
        return this.f1168a.l().right;
    }

    @Deprecated
    public int getSystemWindowInsetTop() {
        return this.f1168a.l().top;
    }

    @NonNull
    @Deprecated
    public Insets getSystemWindowInsets() {
        return this.f1168a.l();
    }

    @NonNull
    @Deprecated
    public Insets getTappableElementInsets() {
        return this.f1168a.m();
    }

    public boolean hasInsets() {
        Insets insets = getInsets(Type.a());
        Insets insets2 = Insets.NONE;
        return (insets.equals(insets2) && getInsetsIgnoringVisibility(Type.a() ^ Type.ime()).equals(insets2) && getDisplayCutout() == null) ? false : true;
    }

    @Deprecated
    public boolean hasStableInsets() {
        return !this.f1168a.j().equals(Insets.NONE);
    }

    @Deprecated
    public boolean hasSystemWindowInsets() {
        return !this.f1168a.l().equals(Insets.NONE);
    }

    public int hashCode() {
        k kVar = this.f1168a;
        if (kVar == null) {
            return 0;
        }
        return kVar.hashCode();
    }

    @NonNull
    public WindowInsetsCompat inset(@NonNull Insets insets) {
        return inset(insets.left, insets.top, insets.right, insets.bottom);
    }

    public boolean isConsumed() {
        return this.f1168a.o();
    }

    public boolean isRound() {
        return this.f1168a.p();
    }

    public boolean isVisible(int i2) {
        return this.f1168a.q(i2);
    }

    @NonNull
    @Deprecated
    public WindowInsetsCompat replaceSystemWindowInsets(int i2, int i3, int i4, int i5) {
        return new Builder(this).setSystemWindowInsets(Insets.of(i2, i3, i4, i5)).build();
    }

    @Nullable
    @RequiresApi(20)
    public WindowInsets toWindowInsets() {
        k kVar = this.f1168a;
        if (kVar instanceof f) {
            return ((f) kVar).c;
        }
        return null;
    }

    @RequiresApi(api = 20)
    /* loaded from: classes.dex */
    public static class b extends e {
        public static Field e = null;
        public static boolean f = false;
        public static Constructor<WindowInsets> g = null;
        public static boolean h = false;
        public WindowInsets c;
        public Insets d;

        public b() {
            this.c = l();
        }

        @Nullable
        public static WindowInsets l() {
            if (!f) {
                try {
                    e = WindowInsets.class.getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException e2) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e2);
                }
                f = true;
            }
            Field field = e;
            if (field != null) {
                try {
                    WindowInsets windowInsets = (WindowInsets) field.get(null);
                    if (windowInsets != null) {
                        return new WindowInsets(windowInsets);
                    }
                } catch (ReflectiveOperationException e3) {
                    Log.i("WindowInsetsCompat", "Could not get value from WindowInsets.CONSUMED field", e3);
                }
            }
            if (!h) {
                try {
                    g = WindowInsets.class.getConstructor(Rect.class);
                } catch (ReflectiveOperationException e4) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e4);
                }
                h = true;
            }
            Constructor<WindowInsets> constructor = g;
            if (constructor != null) {
                try {
                    return constructor.newInstance(new Rect());
                } catch (ReflectiveOperationException e5) {
                    Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e5);
                }
            }
            return null;
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        @NonNull
        public WindowInsetsCompat b() {
            a();
            WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(this.c);
            windowInsetsCompat.c(this.b);
            windowInsetsCompat.f(this.d);
            return windowInsetsCompat;
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        public void g(@Nullable Insets insets) {
            this.d = insets;
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        public void i(@NonNull Insets insets) {
            WindowInsets windowInsets = this.c;
            if (windowInsets != null) {
                this.c = windowInsets.replaceSystemWindowInsets(insets.left, insets.top, insets.right, insets.bottom);
            }
        }

        public b(@NonNull WindowInsetsCompat windowInsetsCompat) {
            super(windowInsetsCompat);
            this.c = windowInsetsCompat.toWindowInsets();
        }
    }

    @RequiresApi(api = 29)
    /* loaded from: classes.dex */
    public static class c extends e {
        public final WindowInsets.Builder c;

        public c() {
            this.c = new WindowInsets.Builder();
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        @NonNull
        public WindowInsetsCompat b() {
            a();
            WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(this.c.build());
            windowInsetsCompat.c(this.b);
            return windowInsetsCompat;
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        public void c(@Nullable DisplayCutoutCompat displayCutoutCompat) {
            this.c.setDisplayCutout(displayCutoutCompat != null ? displayCutoutCompat.b() : null);
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        public void f(@NonNull Insets insets) {
            this.c.setMandatorySystemGestureInsets(insets.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        public void g(@NonNull Insets insets) {
            this.c.setStableInsets(insets.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        public void h(@NonNull Insets insets) {
            this.c.setSystemGestureInsets(insets.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        public void i(@NonNull Insets insets) {
            this.c.setSystemWindowInsets(insets.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.e
        public void j(@NonNull Insets insets) {
            this.c.setTappableElementInsets(insets.toPlatformInsets());
        }

        public c(@NonNull WindowInsetsCompat windowInsetsCompat) {
            super(windowInsetsCompat);
            WindowInsets.Builder builder;
            WindowInsets windowInsets = windowInsetsCompat.toWindowInsets();
            if (windowInsets != null) {
                builder = new WindowInsets.Builder(windowInsets);
            } else {
                builder = new WindowInsets.Builder();
            }
            this.c = builder;
        }
    }

    @RequiresApi(21)
    /* loaded from: classes.dex */
    public static class g extends f {
        public Insets m;

        public g(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull WindowInsets windowInsets) {
            super(windowInsetsCompat, windowInsets);
            this.m = null;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        public WindowInsetsCompat b() {
            return WindowInsetsCompat.toWindowInsetsCompat(this.c.consumeStableInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        public WindowInsetsCompat c() {
            return WindowInsetsCompat.toWindowInsetsCompat(this.c.consumeSystemWindowInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        public final Insets j() {
            if (this.m == null) {
                this.m = Insets.of(this.c.getStableInsetLeft(), this.c.getStableInsetTop(), this.c.getStableInsetRight(), this.c.getStableInsetBottom());
            }
            return this.m;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        public boolean o() {
            return this.c.isConsumed();
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        public void u(@Nullable Insets insets) {
            this.m = insets;
        }

        public g(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull g gVar) {
            super(windowInsetsCompat, gVar);
            this.m = null;
            this.m = gVar.m;
        }
    }

    @NonNull
    @RequiresApi(20)
    public static WindowInsetsCompat toWindowInsetsCompat(@NonNull WindowInsets windowInsets, @Nullable View view) {
        WindowInsetsCompat windowInsetsCompat = new WindowInsetsCompat((WindowInsets) Preconditions.checkNotNull(windowInsets));
        if (view != null && ViewCompat.isAttachedToWindow(view)) {
            windowInsetsCompat.e(ViewCompat.getRootWindowInsets(view));
            windowInsetsCompat.a(view.getRootView());
        }
        return windowInsetsCompat;
    }

    @NonNull
    public WindowInsetsCompat inset(@IntRange(from = 0) int i2, @IntRange(from = 0) int i3, @IntRange(from = 0) int i4, @IntRange(from = 0) int i5) {
        return this.f1168a.n(i2, i3, i4, i5);
    }

    @RequiresApi(20)
    /* loaded from: classes.dex */
    public static class f extends k {
        public static boolean h = false;
        public static Method i;
        public static Class<?> j;
        public static Field k;
        public static Field l;
        @NonNull
        public final WindowInsets c;
        public Insets[] d;
        public Insets e;
        public WindowInsetsCompat f;
        public Insets g;

        public f(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull WindowInsets windowInsets) {
            super(windowInsetsCompat);
            this.e = null;
            this.c = windowInsets;
        }

        @SuppressLint({"PrivateApi"})
        public static void A() {
            try {
                i = View.class.getDeclaredMethod("getViewRootImpl", new Class[0]);
                Class<?> cls = Class.forName("android.view.View$AttachInfo");
                j = cls;
                k = cls.getDeclaredField("mVisibleInsets");
                l = Class.forName("android.view.ViewRootImpl").getDeclaredField("mAttachInfo");
                k.setAccessible(true);
                l.setAccessible(true);
            } catch (ReflectiveOperationException e) {
                Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e.getMessage(), e);
            }
            h = true;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        public void d(@NonNull View view) {
            Insets y = y(view);
            if (y == null) {
                y = Insets.NONE;
            }
            s(y);
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        public void e(@NonNull WindowInsetsCompat windowInsetsCompat) {
            windowInsetsCompat.e(this.f);
            windowInsetsCompat.d(this.g);
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        public boolean equals(Object obj) {
            if (super.equals(obj)) {
                return Objects.equals(this.g, ((f) obj).g);
            }
            return false;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        public Insets g(int i2) {
            return v(i2, false);
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        public Insets h(int i2) {
            return v(i2, true);
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        public final Insets l() {
            if (this.e == null) {
                this.e = Insets.of(this.c.getSystemWindowInsetLeft(), this.c.getSystemWindowInsetTop(), this.c.getSystemWindowInsetRight(), this.c.getSystemWindowInsetBottom());
            }
            return this.e;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        public WindowInsetsCompat n(int i2, int i3, int i4, int i5) {
            Builder builder = new Builder(WindowInsetsCompat.toWindowInsetsCompat(this.c));
            builder.setSystemWindowInsets(WindowInsetsCompat.b(l(), i2, i3, i4, i5));
            builder.setStableInsets(WindowInsetsCompat.b(j(), i2, i3, i4, i5));
            return builder.build();
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        public boolean p() {
            return this.c.isRound();
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @SuppressLint({"WrongConstant"})
        public boolean q(int i2) {
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if ((i2 & i3) != 0 && !z(i3)) {
                    return false;
                }
            }
            return true;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        public void r(Insets[] insetsArr) {
            this.d = insetsArr;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        public void s(@NonNull Insets insets) {
            this.g = insets;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        public void t(@Nullable WindowInsetsCompat windowInsetsCompat) {
            this.f = windowInsetsCompat;
        }

        @NonNull
        @SuppressLint({"WrongConstant"})
        public final Insets v(int i2, boolean z) {
            Insets insets = Insets.NONE;
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if ((i2 & i3) != 0) {
                    insets = Insets.max(insets, w(i3, z));
                }
            }
            return insets;
        }

        @NonNull
        public Insets w(int i2, boolean z) {
            Insets stableInsets;
            int i3;
            DisplayCutoutCompat f;
            if (i2 == 1) {
                if (z) {
                    return Insets.of(0, Math.max(x().top, l().top), 0, 0);
                }
                return Insets.of(0, l().top, 0, 0);
            }
            if (i2 == 2) {
                if (z) {
                    Insets x = x();
                    Insets j2 = j();
                    return Insets.of(Math.max(x.left, j2.left), 0, Math.max(x.right, j2.right), Math.max(x.bottom, j2.bottom));
                }
                Insets l2 = l();
                WindowInsetsCompat windowInsetsCompat = this.f;
                stableInsets = windowInsetsCompat != null ? windowInsetsCompat.getStableInsets() : null;
                int i4 = l2.bottom;
                if (stableInsets != null) {
                    i4 = Math.min(i4, stableInsets.bottom);
                }
                return Insets.of(l2.left, 0, l2.right, i4);
            } else if (i2 != 8) {
                if (i2 != 16) {
                    if (i2 != 32) {
                        if (i2 != 64) {
                            if (i2 != 128) {
                                return Insets.NONE;
                            }
                            WindowInsetsCompat windowInsetsCompat2 = this.f;
                            if (windowInsetsCompat2 != null) {
                                f = windowInsetsCompat2.getDisplayCutout();
                            } else {
                                f = f();
                            }
                            if (f != null) {
                                return Insets.of(f.getSafeInsetLeft(), f.getSafeInsetTop(), f.getSafeInsetRight(), f.getSafeInsetBottom());
                            }
                            return Insets.NONE;
                        }
                        return m();
                    }
                    return i();
                }
                return k();
            } else {
                Insets[] insetsArr = this.d;
                stableInsets = insetsArr != null ? insetsArr[Type.b(8)] : null;
                if (stableInsets != null) {
                    return stableInsets;
                }
                Insets l3 = l();
                Insets x2 = x();
                int i5 = l3.bottom;
                if (i5 > x2.bottom) {
                    return Insets.of(0, 0, 0, i5);
                }
                Insets insets = this.g;
                if (insets != null && !insets.equals(Insets.NONE) && (i3 = this.g.bottom) > x2.bottom) {
                    return Insets.of(0, 0, 0, i3);
                }
                return Insets.NONE;
            }
        }

        public final Insets x() {
            WindowInsetsCompat windowInsetsCompat = this.f;
            if (windowInsetsCompat != null) {
                return windowInsetsCompat.getStableInsets();
            }
            return Insets.NONE;
        }

        @Nullable
        public final Insets y(@NonNull View view) {
            if (Build.VERSION.SDK_INT < 30) {
                if (!h) {
                    A();
                }
                Method method = i;
                if (method != null && j != null && k != null) {
                    try {
                        Object invoke = method.invoke(view, new Object[0]);
                        if (invoke == null) {
                            Log.w("WindowInsetsCompat", "Failed to get visible insets. getViewRootImpl() returned null from the provided view. This means that the view is either not attached or the method has been overridden", new NullPointerException());
                            return null;
                        }
                        Rect rect = (Rect) k.get(l.get(invoke));
                        if (rect != null) {
                            return Insets.of(rect);
                        }
                        return null;
                    } catch (ReflectiveOperationException e) {
                        Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e.getMessage(), e);
                    }
                }
                return null;
            }
            throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
        }

        public boolean z(int i2) {
            if (i2 != 1 && i2 != 2) {
                if (i2 == 4) {
                    return false;
                }
                if (i2 != 8 && i2 != 128) {
                    return true;
                }
            }
            return !w(i2, false).equals(Insets.NONE);
        }

        public f(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull f fVar) {
            this(windowInsetsCompat, new WindowInsets(fVar.c));
        }
    }

    @RequiresApi(29)
    /* loaded from: classes.dex */
    public static class i extends h {
        public Insets n;
        public Insets o;
        public Insets p;

        public i(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull WindowInsets windowInsets) {
            super(windowInsetsCompat, windowInsets);
            this.n = null;
            this.o = null;
            this.p = null;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        public Insets i() {
            if (this.o == null) {
                this.o = Insets.toCompatInsets(this.c.getMandatorySystemGestureInsets());
            }
            return this.o;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        public Insets k() {
            if (this.n == null) {
                this.n = Insets.toCompatInsets(this.c.getSystemGestureInsets());
            }
            return this.n;
        }

        @Override // androidx.core.view.WindowInsetsCompat.k
        @NonNull
        public Insets m() {
            if (this.p == null) {
                this.p = Insets.toCompatInsets(this.c.getTappableElementInsets());
            }
            return this.p;
        }

        @Override // androidx.core.view.WindowInsetsCompat.f, androidx.core.view.WindowInsetsCompat.k
        @NonNull
        public WindowInsetsCompat n(int i, int i2, int i3, int i4) {
            return WindowInsetsCompat.toWindowInsetsCompat(this.c.inset(i, i2, i3, i4));
        }

        @Override // androidx.core.view.WindowInsetsCompat.g, androidx.core.view.WindowInsetsCompat.k
        public void u(@Nullable Insets insets) {
        }

        public i(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull i iVar) {
            super(windowInsetsCompat, iVar);
            this.n = null;
            this.o = null;
            this.p = null;
        }
    }

    @NonNull
    @Deprecated
    public WindowInsetsCompat replaceSystemWindowInsets(@NonNull Rect rect) {
        return new Builder(this).setSystemWindowInsets(Insets.of(rect)).build();
    }

    /* loaded from: classes.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final e f1169a;

        public Builder() {
            int i = Build.VERSION.SDK_INT;
            if (i >= 30) {
                this.f1169a = new d();
            } else if (i >= 29) {
                this.f1169a = new c();
            } else if (i >= 20) {
                this.f1169a = new b();
            } else {
                this.f1169a = new e();
            }
        }

        @NonNull
        public WindowInsetsCompat build() {
            return this.f1169a.b();
        }

        @NonNull
        public Builder setDisplayCutout(@Nullable DisplayCutoutCompat displayCutoutCompat) {
            this.f1169a.c(displayCutoutCompat);
            return this;
        }

        @NonNull
        public Builder setInsets(int i, @NonNull Insets insets) {
            this.f1169a.d(i, insets);
            return this;
        }

        @NonNull
        public Builder setInsetsIgnoringVisibility(int i, @NonNull Insets insets) {
            this.f1169a.e(i, insets);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setMandatorySystemGestureInsets(@NonNull Insets insets) {
            this.f1169a.f(insets);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setStableInsets(@NonNull Insets insets) {
            this.f1169a.g(insets);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setSystemGestureInsets(@NonNull Insets insets) {
            this.f1169a.h(insets);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setSystemWindowInsets(@NonNull Insets insets) {
            this.f1169a.i(insets);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setTappableElementInsets(@NonNull Insets insets) {
            this.f1169a.j(insets);
            return this;
        }

        @NonNull
        public Builder setVisible(int i, boolean z) {
            this.f1169a.k(i, z);
            return this;
        }

        public Builder(@NonNull WindowInsetsCompat windowInsetsCompat) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 30) {
                this.f1169a = new d(windowInsetsCompat);
            } else if (i >= 29) {
                this.f1169a = new c(windowInsetsCompat);
            } else if (i >= 20) {
                this.f1169a = new b(windowInsetsCompat);
            } else {
                this.f1169a = new e(windowInsetsCompat);
            }
        }
    }

    public WindowInsetsCompat(@Nullable WindowInsetsCompat windowInsetsCompat) {
        if (windowInsetsCompat != null) {
            k kVar = windowInsetsCompat.f1168a;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 30 && (kVar instanceof j)) {
                this.f1168a = new j(this, (j) kVar);
            } else if (i2 >= 29 && (kVar instanceof i)) {
                this.f1168a = new i(this, (i) kVar);
            } else if (i2 >= 28 && (kVar instanceof h)) {
                this.f1168a = new h(this, (h) kVar);
            } else if (i2 >= 21 && (kVar instanceof g)) {
                this.f1168a = new g(this, (g) kVar);
            } else if (i2 >= 20 && (kVar instanceof f)) {
                this.f1168a = new f(this, (f) kVar);
            } else {
                this.f1168a = new k(this);
            }
            kVar.e(this);
            return;
        }
        this.f1168a = new k(this);
    }
}
