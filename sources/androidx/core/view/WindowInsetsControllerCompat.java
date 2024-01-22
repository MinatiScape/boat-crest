package androidx.core.view;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.CancellationSignal;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsAnimationControlListener;
import android.view.WindowInsetsAnimationController;
import android.view.WindowInsetsController;
import android.view.animation.Interpolator;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.collection.SimpleArrayMap;
import androidx.core.view.WindowInsetsControllerCompat;
/* loaded from: classes.dex */
public final class WindowInsetsControllerCompat {
    public static final int BEHAVIOR_SHOW_BARS_BY_SWIPE = 1;
    public static final int BEHAVIOR_SHOW_BARS_BY_TOUCH = 0;
    public static final int BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE = 2;

    /* renamed from: a  reason: collision with root package name */
    public final e f1173a;

    /* loaded from: classes.dex */
    public interface OnControllableInsetsChangedListener {
        void onControllableInsetsChanged(@NonNull WindowInsetsControllerCompat windowInsetsControllerCompat, int i);
    }

    @RequiresApi(20)
    /* loaded from: classes.dex */
    public static class a extends e {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final Window f1174a;
        @NonNull
        public final View b;

        public a(@NonNull Window window, @NonNull View view) {
            this.f1174a = window;
            this.b = view;
        }

        public static /* synthetic */ void n(View view) {
            ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 0);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public void a(OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public void b(int i, long j, Interpolator interpolator, CancellationSignal cancellationSignal, WindowInsetsAnimationControlListenerCompat windowInsetsAnimationControlListenerCompat) {
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public int c() {
            return 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public void d(int i) {
            for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                if ((i & i2) != 0) {
                    m(i2);
                }
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public void g(@NonNull OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public void j(int i) {
            if (i == 0) {
                r(6144);
            } else if (i == 1) {
                r(4096);
                o(2048);
            } else if (i != 2) {
            } else {
                r(2048);
                o(4096);
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public void k(int i) {
            for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                if ((i & i2) != 0) {
                    q(i2);
                }
            }
        }

        public final void m(int i) {
            if (i == 1) {
                o(4);
            } else if (i == 2) {
                o(2);
            } else if (i != 8) {
            } else {
                ((InputMethodManager) this.f1174a.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.f1174a.getDecorView().getWindowToken(), 0);
            }
        }

        public void o(int i) {
            View decorView = this.f1174a.getDecorView();
            decorView.setSystemUiVisibility(i | decorView.getSystemUiVisibility());
        }

        public void p(int i) {
            this.f1174a.addFlags(i);
        }

        public final void q(int i) {
            if (i == 1) {
                r(4);
                s(1024);
            } else if (i == 2) {
                r(2);
            } else if (i != 8) {
            } else {
                final View view = this.b;
                if (!view.isInEditMode() && !view.onCheckIsTextEditor()) {
                    view = this.f1174a.getCurrentFocus();
                } else {
                    view.requestFocus();
                }
                if (view == null) {
                    view = this.f1174a.findViewById(16908290);
                }
                if (view == null || !view.hasWindowFocus()) {
                    return;
                }
                view.post(new Runnable() { // from class: androidx.core.view.k
                    @Override // java.lang.Runnable
                    public final void run() {
                        WindowInsetsControllerCompat.a.n(view);
                    }
                });
            }
        }

        public void r(int i) {
            View decorView = this.f1174a.getDecorView();
            decorView.setSystemUiVisibility((~i) & decorView.getSystemUiVisibility());
        }

        public void s(int i) {
            this.f1174a.clearFlags(i);
        }
    }

    @RequiresApi(23)
    /* loaded from: classes.dex */
    public static class b extends a {
        public b(@NonNull Window window, @Nullable View view) {
            super(window, view);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public boolean f() {
            return (this.f1174a.getDecorView().getSystemUiVisibility() & 8192) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public void i(boolean z) {
            if (z) {
                s(67108864);
                p(Integer.MIN_VALUE);
                o(8192);
                return;
            }
            r(8192);
        }
    }

    @RequiresApi(26)
    /* loaded from: classes.dex */
    public static class c extends b {
        public c(@NonNull Window window, @Nullable View view) {
            super(window, view);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public boolean e() {
            return (this.f1174a.getDecorView().getSystemUiVisibility() & 16) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public void h(boolean z) {
            if (z) {
                s(134217728);
                p(Integer.MIN_VALUE);
                o(16);
                return;
            }
            r(16);
        }
    }

    /* loaded from: classes.dex */
    public static class e {
        public void a(OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        }

        public void b(int i, long j, Interpolator interpolator, CancellationSignal cancellationSignal, WindowInsetsAnimationControlListenerCompat windowInsetsAnimationControlListenerCompat) {
        }

        public int c() {
            return 0;
        }

        public void d(int i) {
        }

        public boolean e() {
            return false;
        }

        public boolean f() {
            return false;
        }

        public void g(@NonNull OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        }

        public void h(boolean z) {
        }

        public void i(boolean z) {
        }

        public void j(int i) {
        }

        public void k(int i) {
        }
    }

    @RequiresApi(30)
    @Deprecated
    public WindowInsetsControllerCompat(@NonNull WindowInsetsController windowInsetsController) {
        this.f1173a = new d(windowInsetsController, this);
    }

    @NonNull
    @RequiresApi(30)
    @Deprecated
    public static WindowInsetsControllerCompat toWindowInsetsControllerCompat(@NonNull WindowInsetsController windowInsetsController) {
        return new WindowInsetsControllerCompat(windowInsetsController);
    }

    public void addOnControllableInsetsChangedListener(@NonNull OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        this.f1173a.a(onControllableInsetsChangedListener);
    }

    public void controlWindowInsetsAnimation(int i, long j, @Nullable Interpolator interpolator, @Nullable CancellationSignal cancellationSignal, @NonNull WindowInsetsAnimationControlListenerCompat windowInsetsAnimationControlListenerCompat) {
        this.f1173a.b(i, j, interpolator, cancellationSignal, windowInsetsAnimationControlListenerCompat);
    }

    @SuppressLint({"WrongConstant"})
    public int getSystemBarsBehavior() {
        return this.f1173a.c();
    }

    public void hide(int i) {
        this.f1173a.d(i);
    }

    public boolean isAppearanceLightNavigationBars() {
        return this.f1173a.e();
    }

    public boolean isAppearanceLightStatusBars() {
        return this.f1173a.f();
    }

    public void removeOnControllableInsetsChangedListener(@NonNull OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        this.f1173a.g(onControllableInsetsChangedListener);
    }

    public void setAppearanceLightNavigationBars(boolean z) {
        this.f1173a.h(z);
    }

    public void setAppearanceLightStatusBars(boolean z) {
        this.f1173a.i(z);
    }

    public void setSystemBarsBehavior(int i) {
        this.f1173a.j(i);
    }

    public void show(int i) {
        this.f1173a.k(i);
    }

    @RequiresApi(30)
    /* loaded from: classes.dex */
    public static class d extends e {

        /* renamed from: a  reason: collision with root package name */
        public final WindowInsetsControllerCompat f1175a;
        public final WindowInsetsController b;
        public final SimpleArrayMap<OnControllableInsetsChangedListener, WindowInsetsController.OnControllableInsetsChangedListener> c;
        public Window d;

        /* loaded from: classes.dex */
        public class a implements WindowInsetsAnimationControlListener {

            /* renamed from: a  reason: collision with root package name */
            public WindowInsetsAnimationControllerCompat f1176a = null;
            public final /* synthetic */ WindowInsetsAnimationControlListenerCompat b;

            public a(d dVar, WindowInsetsAnimationControlListenerCompat windowInsetsAnimationControlListenerCompat) {
                this.b = windowInsetsAnimationControlListenerCompat;
            }

            @Override // android.view.WindowInsetsAnimationControlListener
            public void onCancelled(@Nullable WindowInsetsAnimationController windowInsetsAnimationController) {
                this.b.onCancelled(windowInsetsAnimationController == null ? null : this.f1176a);
            }

            @Override // android.view.WindowInsetsAnimationControlListener
            public void onFinished(@NonNull WindowInsetsAnimationController windowInsetsAnimationController) {
                this.b.onFinished(this.f1176a);
            }

            @Override // android.view.WindowInsetsAnimationControlListener
            public void onReady(@NonNull WindowInsetsAnimationController windowInsetsAnimationController, int i) {
                WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat = new WindowInsetsAnimationControllerCompat(windowInsetsAnimationController);
                this.f1176a = windowInsetsAnimationControllerCompat;
                this.b.onReady(windowInsetsAnimationControllerCompat, i);
            }
        }

        public d(@NonNull Window window, @NonNull WindowInsetsControllerCompat windowInsetsControllerCompat) {
            this(window.getInsetsController(), windowInsetsControllerCompat);
            this.d = window;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void m(OnControllableInsetsChangedListener onControllableInsetsChangedListener, WindowInsetsController windowInsetsController, int i) {
            if (this.b == windowInsetsController) {
                onControllableInsetsChangedListener.onControllableInsetsChanged(this.f1175a, i);
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public void a(@NonNull final OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
            if (this.c.containsKey(onControllableInsetsChangedListener)) {
                return;
            }
            WindowInsetsController.OnControllableInsetsChangedListener onControllableInsetsChangedListener2 = new WindowInsetsController.OnControllableInsetsChangedListener() { // from class: androidx.core.view.l
                @Override // android.view.WindowInsetsController.OnControllableInsetsChangedListener
                public final void onControllableInsetsChanged(WindowInsetsController windowInsetsController, int i) {
                    WindowInsetsControllerCompat.d.this.m(onControllableInsetsChangedListener, windowInsetsController, i);
                }
            };
            this.c.put(onControllableInsetsChangedListener, onControllableInsetsChangedListener2);
            this.b.addOnControllableInsetsChangedListener(onControllableInsetsChangedListener2);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public void b(int i, long j, @Nullable Interpolator interpolator, @Nullable CancellationSignal cancellationSignal, @NonNull WindowInsetsAnimationControlListenerCompat windowInsetsAnimationControlListenerCompat) {
            this.b.controlWindowInsetsAnimation(i, j, interpolator, cancellationSignal, new a(this, windowInsetsAnimationControlListenerCompat));
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        @SuppressLint({"WrongConstant"})
        public int c() {
            return this.b.getSystemBarsBehavior();
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public void d(int i) {
            this.b.hide(i);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public boolean e() {
            return (this.b.getSystemBarsAppearance() & 16) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public boolean f() {
            return (this.b.getSystemBarsAppearance() & 8) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public void g(@NonNull OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
            WindowInsetsController.OnControllableInsetsChangedListener remove = this.c.remove(onControllableInsetsChangedListener);
            if (remove != null) {
                this.b.removeOnControllableInsetsChangedListener(remove);
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public void h(boolean z) {
            if (z) {
                if (this.d != null) {
                    n(16);
                }
                this.b.setSystemBarsAppearance(16, 16);
                return;
            }
            if (this.d != null) {
                o(16);
            }
            this.b.setSystemBarsAppearance(0, 16);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public void i(boolean z) {
            if (z) {
                if (this.d != null) {
                    n(8192);
                }
                this.b.setSystemBarsAppearance(8, 8);
                return;
            }
            if (this.d != null) {
                o(8192);
            }
            this.b.setSystemBarsAppearance(0, 8);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public void j(int i) {
            this.b.setSystemBarsBehavior(i);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.e
        public void k(int i) {
            Window window = this.d;
            if (window != null && (i & 8) != 0 && Build.VERSION.SDK_INT < 32) {
                ((InputMethodManager) window.getContext().getSystemService("input_method")).isActive();
            }
            this.b.show(i);
        }

        public void n(int i) {
            View decorView = this.d.getDecorView();
            decorView.setSystemUiVisibility(i | decorView.getSystemUiVisibility());
        }

        public void o(int i) {
            View decorView = this.d.getDecorView();
            decorView.setSystemUiVisibility((~i) & decorView.getSystemUiVisibility());
        }

        public d(@NonNull WindowInsetsController windowInsetsController, @NonNull WindowInsetsControllerCompat windowInsetsControllerCompat) {
            this.c = new SimpleArrayMap<>();
            this.b = windowInsetsController;
            this.f1175a = windowInsetsControllerCompat;
        }
    }

    public WindowInsetsControllerCompat(@NonNull Window window, @NonNull View view) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 30) {
            this.f1173a = new d(window, this);
        } else if (i >= 26) {
            this.f1173a = new c(window, view);
        } else if (i >= 23) {
            this.f1173a = new b(window, view);
        } else if (i >= 20) {
            this.f1173a = new a(window, view);
        } else {
            this.f1173a = new e();
        }
    }
}
