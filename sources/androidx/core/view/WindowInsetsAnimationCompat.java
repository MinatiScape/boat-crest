package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.R;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class WindowInsetsAnimationCompat {

    /* renamed from: a  reason: collision with root package name */
    public c f1160a;

    /* loaded from: classes.dex */
    public static abstract class Callback {
        public static final int DISPATCH_MODE_CONTINUE_ON_SUBTREE = 1;
        public static final int DISPATCH_MODE_STOP = 0;

        /* renamed from: a  reason: collision with root package name */
        public WindowInsets f1162a;
        public final int b;

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        /* loaded from: classes.dex */
        public @interface DispatchMode {
        }

        public Callback(int i) {
            this.b = i;
        }

        public final int getDispatchMode() {
            return this.b;
        }

        public void onEnd(@NonNull WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        }

        public void onPrepare(@NonNull WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        }

        @NonNull
        public abstract WindowInsetsCompat onProgress(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull List<WindowInsetsAnimationCompat> list);

        @NonNull
        public BoundsCompat onStart(@NonNull WindowInsetsAnimationCompat windowInsetsAnimationCompat, @NonNull BoundsCompat boundsCompat) {
            return boundsCompat;
        }
    }

    @RequiresApi(21)
    /* loaded from: classes.dex */
    public static class a extends c {

        @RequiresApi(21)
        /* renamed from: androidx.core.view.WindowInsetsAnimationCompat$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class View$OnApplyWindowInsetsListenerC0135a implements View.OnApplyWindowInsetsListener {

            /* renamed from: a  reason: collision with root package name */
            public final Callback f1163a;
            public WindowInsetsCompat b;

            /* renamed from: androidx.core.view.WindowInsetsAnimationCompat$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes.dex */
            public class C0136a implements ValueAnimator.AnimatorUpdateListener {
                public final /* synthetic */ WindowInsetsAnimationCompat h;
                public final /* synthetic */ WindowInsetsCompat i;
                public final /* synthetic */ WindowInsetsCompat j;
                public final /* synthetic */ int k;
                public final /* synthetic */ View l;

                public C0136a(View$OnApplyWindowInsetsListenerC0135a view$OnApplyWindowInsetsListenerC0135a, WindowInsetsAnimationCompat windowInsetsAnimationCompat, WindowInsetsCompat windowInsetsCompat, WindowInsetsCompat windowInsetsCompat2, int i, View view) {
                    this.h = windowInsetsAnimationCompat;
                    this.i = windowInsetsCompat;
                    this.j = windowInsetsCompat2;
                    this.k = i;
                    this.l = view;
                }

                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.h.setFraction(valueAnimator.getAnimatedFraction());
                    a.n(this.l, a.r(this.i, this.j, this.h.getInterpolatedFraction(), this.k), Collections.singletonList(this.h));
                }
            }

            /* renamed from: androidx.core.view.WindowInsetsAnimationCompat$a$a$b */
            /* loaded from: classes.dex */
            public class b extends AnimatorListenerAdapter {
                public final /* synthetic */ WindowInsetsAnimationCompat h;
                public final /* synthetic */ View i;

                public b(View$OnApplyWindowInsetsListenerC0135a view$OnApplyWindowInsetsListenerC0135a, WindowInsetsAnimationCompat windowInsetsAnimationCompat, View view) {
                    this.h = windowInsetsAnimationCompat;
                    this.i = view;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    this.h.setFraction(1.0f);
                    a.l(this.i, this.h);
                }
            }

            /* renamed from: androidx.core.view.WindowInsetsAnimationCompat$a$a$c */
            /* loaded from: classes.dex */
            public class c implements Runnable {
                public final /* synthetic */ View h;
                public final /* synthetic */ WindowInsetsAnimationCompat i;
                public final /* synthetic */ BoundsCompat j;
                public final /* synthetic */ ValueAnimator k;

                public c(View$OnApplyWindowInsetsListenerC0135a view$OnApplyWindowInsetsListenerC0135a, View view, WindowInsetsAnimationCompat windowInsetsAnimationCompat, BoundsCompat boundsCompat, ValueAnimator valueAnimator) {
                    this.h = view;
                    this.i = windowInsetsAnimationCompat;
                    this.j = boundsCompat;
                    this.k = valueAnimator;
                }

                @Override // java.lang.Runnable
                public void run() {
                    a.o(this.h, this.i, this.j);
                    this.k.start();
                }
            }

            public View$OnApplyWindowInsetsListenerC0135a(@NonNull View view, @NonNull Callback callback) {
                this.f1163a = callback;
                WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(view);
                this.b = rootWindowInsets != null ? new WindowInsetsCompat.Builder(rootWindowInsets).build() : null;
            }

            @Override // android.view.View.OnApplyWindowInsetsListener
            public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                if (!view.isLaidOut()) {
                    this.b = WindowInsetsCompat.toWindowInsetsCompat(windowInsets, view);
                    return a.p(view, windowInsets);
                }
                WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(windowInsets, view);
                if (this.b == null) {
                    this.b = ViewCompat.getRootWindowInsets(view);
                }
                if (this.b == null) {
                    this.b = windowInsetsCompat;
                    return a.p(view, windowInsets);
                }
                Callback q = a.q(view);
                if (q != null && Objects.equals(q.f1162a, windowInsets)) {
                    return a.p(view, windowInsets);
                }
                int i = a.i(windowInsetsCompat, this.b);
                if (i == 0) {
                    return a.p(view, windowInsets);
                }
                WindowInsetsCompat windowInsetsCompat2 = this.b;
                WindowInsetsAnimationCompat windowInsetsAnimationCompat = new WindowInsetsAnimationCompat(i, new DecelerateInterpolator(), 160L);
                windowInsetsAnimationCompat.setFraction(0.0f);
                ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(windowInsetsAnimationCompat.getDurationMillis());
                BoundsCompat j = a.j(windowInsetsCompat, windowInsetsCompat2, i);
                a.m(view, windowInsetsAnimationCompat, windowInsets, false);
                duration.addUpdateListener(new C0136a(this, windowInsetsAnimationCompat, windowInsetsCompat, windowInsetsCompat2, i, view));
                duration.addListener(new b(this, windowInsetsAnimationCompat, view));
                OneShotPreDrawListener.add(view, new c(this, view, windowInsetsAnimationCompat, j, duration));
                this.b = windowInsetsCompat;
                return a.p(view, windowInsets);
            }
        }

        public a(int i, @Nullable Interpolator interpolator, long j) {
            super(i, interpolator, j);
        }

        @SuppressLint({"WrongConstant"})
        public static int i(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull WindowInsetsCompat windowInsetsCompat2) {
            int i = 0;
            for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                if (!windowInsetsCompat.getInsets(i2).equals(windowInsetsCompat2.getInsets(i2))) {
                    i |= i2;
                }
            }
            return i;
        }

        @NonNull
        public static BoundsCompat j(@NonNull WindowInsetsCompat windowInsetsCompat, @NonNull WindowInsetsCompat windowInsetsCompat2, int i) {
            Insets insets = windowInsetsCompat.getInsets(i);
            Insets insets2 = windowInsetsCompat2.getInsets(i);
            return new BoundsCompat(Insets.of(Math.min(insets.left, insets2.left), Math.min(insets.top, insets2.top), Math.min(insets.right, insets2.right), Math.min(insets.bottom, insets2.bottom)), Insets.of(Math.max(insets.left, insets2.left), Math.max(insets.top, insets2.top), Math.max(insets.right, insets2.right), Math.max(insets.bottom, insets2.bottom)));
        }

        @NonNull
        public static View.OnApplyWindowInsetsListener k(@NonNull View view, @NonNull Callback callback) {
            return new View$OnApplyWindowInsetsListenerC0135a(view, callback);
        }

        public static void l(@NonNull View view, @NonNull WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
            Callback q = q(view);
            if (q != null) {
                q.onEnd(windowInsetsAnimationCompat);
                if (q.getDispatchMode() == 0) {
                    return;
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    l(viewGroup.getChildAt(i), windowInsetsAnimationCompat);
                }
            }
        }

        public static void m(View view, WindowInsetsAnimationCompat windowInsetsAnimationCompat, WindowInsets windowInsets, boolean z) {
            Callback q = q(view);
            if (q != null) {
                q.f1162a = windowInsets;
                if (!z) {
                    q.onPrepare(windowInsetsAnimationCompat);
                    z = q.getDispatchMode() == 0;
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    m(viewGroup.getChildAt(i), windowInsetsAnimationCompat, windowInsets, z);
                }
            }
        }

        public static void n(@NonNull View view, @NonNull WindowInsetsCompat windowInsetsCompat, @NonNull List<WindowInsetsAnimationCompat> list) {
            Callback q = q(view);
            if (q != null) {
                windowInsetsCompat = q.onProgress(windowInsetsCompat, list);
                if (q.getDispatchMode() == 0) {
                    return;
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    n(viewGroup.getChildAt(i), windowInsetsCompat, list);
                }
            }
        }

        public static void o(View view, WindowInsetsAnimationCompat windowInsetsAnimationCompat, BoundsCompat boundsCompat) {
            Callback q = q(view);
            if (q != null) {
                q.onStart(windowInsetsAnimationCompat, boundsCompat);
                if (q.getDispatchMode() == 0) {
                    return;
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    o(viewGroup.getChildAt(i), windowInsetsAnimationCompat, boundsCompat);
                }
            }
        }

        @NonNull
        public static WindowInsets p(@NonNull View view, @NonNull WindowInsets windowInsets) {
            return view.getTag(R.id.tag_on_apply_window_listener) != null ? windowInsets : view.onApplyWindowInsets(windowInsets);
        }

        @Nullable
        public static Callback q(View view) {
            Object tag = view.getTag(R.id.tag_window_insets_animation_callback);
            if (tag instanceof View$OnApplyWindowInsetsListenerC0135a) {
                return ((View$OnApplyWindowInsetsListenerC0135a) tag).f1163a;
            }
            return null;
        }

        @SuppressLint({"WrongConstant"})
        public static WindowInsetsCompat r(WindowInsetsCompat windowInsetsCompat, WindowInsetsCompat windowInsetsCompat2, float f, int i) {
            WindowInsetsCompat.Builder builder = new WindowInsetsCompat.Builder(windowInsetsCompat);
            for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                if ((i & i2) == 0) {
                    builder.setInsets(i2, windowInsetsCompat.getInsets(i2));
                } else {
                    Insets insets = windowInsetsCompat.getInsets(i2);
                    Insets insets2 = windowInsetsCompat2.getInsets(i2);
                    float f2 = 1.0f - f;
                    builder.setInsets(i2, WindowInsetsCompat.b(insets, (int) (((insets.left - insets2.left) * f2) + 0.5d), (int) (((insets.top - insets2.top) * f2) + 0.5d), (int) (((insets.right - insets2.right) * f2) + 0.5d), (int) (((insets.bottom - insets2.bottom) * f2) + 0.5d)));
                }
            }
            return builder.build();
        }

        public static void s(@NonNull View view, @Nullable Callback callback) {
            Object tag = view.getTag(R.id.tag_on_apply_window_listener);
            if (callback == null) {
                view.setTag(R.id.tag_window_insets_animation_callback, null);
                if (tag == null) {
                    view.setOnApplyWindowInsetsListener(null);
                    return;
                }
                return;
            }
            View.OnApplyWindowInsetsListener k = k(view, callback);
            view.setTag(R.id.tag_window_insets_animation_callback, k);
            if (tag == null) {
                view.setOnApplyWindowInsetsListener(k);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public final int f1165a;
        public float b;
        @Nullable
        public final Interpolator c;
        public final long d;
        public float e;

        public c(int i, @Nullable Interpolator interpolator, long j) {
            this.f1165a = i;
            this.c = interpolator;
            this.d = j;
        }

        public float a() {
            return this.e;
        }

        public long b() {
            return this.d;
        }

        public float c() {
            return this.b;
        }

        public float d() {
            Interpolator interpolator = this.c;
            if (interpolator != null) {
                return interpolator.getInterpolation(this.b);
            }
            return this.b;
        }

        @Nullable
        public Interpolator e() {
            return this.c;
        }

        public int f() {
            return this.f1165a;
        }

        public void g(float f) {
            this.e = f;
        }

        public void h(float f) {
            this.b = f;
        }
    }

    public WindowInsetsAnimationCompat(int i, @Nullable Interpolator interpolator, long j) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            this.f1160a = new b(i, interpolator, j);
        } else if (i2 >= 21) {
            this.f1160a = new a(i, interpolator, j);
        } else {
            this.f1160a = new c(0, interpolator, j);
        }
    }

    public static void a(@NonNull View view, @Nullable Callback callback) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 30) {
            b.l(view, callback);
        } else if (i >= 21) {
            a.s(view, callback);
        }
    }

    @RequiresApi(30)
    public static WindowInsetsAnimationCompat b(WindowInsetsAnimation windowInsetsAnimation) {
        return new WindowInsetsAnimationCompat(windowInsetsAnimation);
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getAlpha() {
        return this.f1160a.a();
    }

    public long getDurationMillis() {
        return this.f1160a.b();
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getFraction() {
        return this.f1160a.c();
    }

    public float getInterpolatedFraction() {
        return this.f1160a.d();
    }

    @Nullable
    public Interpolator getInterpolator() {
        return this.f1160a.e();
    }

    public int getTypeMask() {
        return this.f1160a.f();
    }

    public void setAlpha(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.f1160a.g(f);
    }

    public void setFraction(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.f1160a.h(f);
    }

    @RequiresApi(30)
    /* loaded from: classes.dex */
    public static class b extends c {
        @NonNull
        public final WindowInsetsAnimation f;

        @RequiresApi(30)
        /* loaded from: classes.dex */
        public static class a extends WindowInsetsAnimation.Callback {

            /* renamed from: a  reason: collision with root package name */
            public final Callback f1164a;
            public List<WindowInsetsAnimationCompat> b;
            public ArrayList<WindowInsetsAnimationCompat> c;
            public final HashMap<WindowInsetsAnimation, WindowInsetsAnimationCompat> d;

            public a(@NonNull Callback callback) {
                super(callback.getDispatchMode());
                this.d = new HashMap<>();
                this.f1164a = callback;
            }

            @NonNull
            public final WindowInsetsAnimationCompat a(@NonNull WindowInsetsAnimation windowInsetsAnimation) {
                WindowInsetsAnimationCompat windowInsetsAnimationCompat = this.d.get(windowInsetsAnimation);
                if (windowInsetsAnimationCompat == null) {
                    WindowInsetsAnimationCompat b = WindowInsetsAnimationCompat.b(windowInsetsAnimation);
                    this.d.put(windowInsetsAnimation, b);
                    return b;
                }
                return windowInsetsAnimationCompat;
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public void onEnd(@NonNull WindowInsetsAnimation windowInsetsAnimation) {
                this.f1164a.onEnd(a(windowInsetsAnimation));
                this.d.remove(windowInsetsAnimation);
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public void onPrepare(@NonNull WindowInsetsAnimation windowInsetsAnimation) {
                this.f1164a.onPrepare(a(windowInsetsAnimation));
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            @NonNull
            public WindowInsets onProgress(@NonNull WindowInsets windowInsets, @NonNull List<WindowInsetsAnimation> list) {
                ArrayList<WindowInsetsAnimationCompat> arrayList = this.c;
                if (arrayList == null) {
                    ArrayList<WindowInsetsAnimationCompat> arrayList2 = new ArrayList<>(list.size());
                    this.c = arrayList2;
                    this.b = Collections.unmodifiableList(arrayList2);
                } else {
                    arrayList.clear();
                }
                for (int size = list.size() - 1; size >= 0; size--) {
                    WindowInsetsAnimation windowInsetsAnimation = list.get(size);
                    WindowInsetsAnimationCompat a2 = a(windowInsetsAnimation);
                    a2.setFraction(windowInsetsAnimation.getFraction());
                    this.c.add(a2);
                }
                return this.f1164a.onProgress(WindowInsetsCompat.toWindowInsetsCompat(windowInsets), this.b).toWindowInsets();
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            @NonNull
            public WindowInsetsAnimation.Bounds onStart(@NonNull WindowInsetsAnimation windowInsetsAnimation, @NonNull WindowInsetsAnimation.Bounds bounds) {
                return this.f1164a.onStart(a(windowInsetsAnimation), BoundsCompat.toBoundsCompat(bounds)).toBounds();
            }
        }

        public b(@NonNull WindowInsetsAnimation windowInsetsAnimation) {
            super(0, null, 0L);
            this.f = windowInsetsAnimation;
        }

        @NonNull
        public static WindowInsetsAnimation.Bounds i(@NonNull BoundsCompat boundsCompat) {
            return new WindowInsetsAnimation.Bounds(boundsCompat.getLowerBound().toPlatformInsets(), boundsCompat.getUpperBound().toPlatformInsets());
        }

        @NonNull
        public static Insets j(@NonNull WindowInsetsAnimation.Bounds bounds) {
            return Insets.toCompatInsets(bounds.getUpperBound());
        }

        @NonNull
        public static Insets k(@NonNull WindowInsetsAnimation.Bounds bounds) {
            return Insets.toCompatInsets(bounds.getLowerBound());
        }

        public static void l(@NonNull View view, @Nullable Callback callback) {
            view.setWindowInsetsAnimationCallback(callback != null ? new a(callback) : null);
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.c
        public long b() {
            return this.f.getDurationMillis();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.c
        public float c() {
            return this.f.getFraction();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.c
        public float d() {
            return this.f.getInterpolatedFraction();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.c
        @Nullable
        public Interpolator e() {
            return this.f.getInterpolator();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.c
        public int f() {
            return this.f.getTypeMask();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.c
        public void h(float f) {
            this.f.setFraction(f);
        }

        public b(int i, Interpolator interpolator, long j) {
            this(new WindowInsetsAnimation(i, interpolator, j));
        }
    }

    /* loaded from: classes.dex */
    public static final class BoundsCompat {

        /* renamed from: a  reason: collision with root package name */
        public final Insets f1161a;
        public final Insets b;

        public BoundsCompat(@NonNull Insets insets, @NonNull Insets insets2) {
            this.f1161a = insets;
            this.b = insets2;
        }

        @NonNull
        @RequiresApi(30)
        public static BoundsCompat toBoundsCompat(@NonNull WindowInsetsAnimation.Bounds bounds) {
            return new BoundsCompat(bounds);
        }

        @NonNull
        public Insets getLowerBound() {
            return this.f1161a;
        }

        @NonNull
        public Insets getUpperBound() {
            return this.b;
        }

        @NonNull
        public BoundsCompat inset(@NonNull Insets insets) {
            return new BoundsCompat(WindowInsetsCompat.b(this.f1161a, insets.left, insets.top, insets.right, insets.bottom), WindowInsetsCompat.b(this.b, insets.left, insets.top, insets.right, insets.bottom));
        }

        @NonNull
        @RequiresApi(30)
        public WindowInsetsAnimation.Bounds toBounds() {
            return b.i(this);
        }

        public String toString() {
            return "Bounds{lower=" + this.f1161a + " upper=" + this.b + "}";
        }

        @RequiresApi(30)
        public BoundsCompat(@NonNull WindowInsetsAnimation.Bounds bounds) {
            this.f1161a = b.k(bounds);
            this.b = b.j(bounds);
        }
    }

    @RequiresApi(30)
    public WindowInsetsAnimationCompat(@NonNull WindowInsetsAnimation windowInsetsAnimation) {
        this(0, null, 0L);
        if (Build.VERSION.SDK_INT >= 30) {
            this.f1160a = new b(windowInsetsAnimation);
        }
    }
}
