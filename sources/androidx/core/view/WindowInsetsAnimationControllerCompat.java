package androidx.core.view;

import android.annotation.SuppressLint;
import android.view.WindowInsetsAnimationController;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.Insets;
/* loaded from: classes.dex */
public final class WindowInsetsAnimationControllerCompat {

    /* renamed from: a  reason: collision with root package name */
    public final b f1166a;

    @RequiresApi(30)
    /* loaded from: classes.dex */
    public static class a extends b {

        /* renamed from: a  reason: collision with root package name */
        public final WindowInsetsAnimationController f1167a;

        public a(@NonNull WindowInsetsAnimationController windowInsetsAnimationController) {
            this.f1167a = windowInsetsAnimationController;
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.b
        public void a(boolean z) {
            this.f1167a.finish(z);
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.b
        public float b() {
            return this.f1167a.getCurrentAlpha();
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.b
        public float c() {
            return this.f1167a.getCurrentFraction();
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.b
        @NonNull
        public Insets d() {
            return Insets.toCompatInsets(this.f1167a.getCurrentInsets());
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.b
        @NonNull
        public Insets e() {
            return Insets.toCompatInsets(this.f1167a.getHiddenStateInsets());
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.b
        @NonNull
        public Insets f() {
            return Insets.toCompatInsets(this.f1167a.getShownStateInsets());
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.b
        @SuppressLint({"WrongConstant"})
        public int g() {
            return this.f1167a.getTypes();
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.b
        public boolean h() {
            return this.f1167a.isCancelled();
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.b
        public boolean i() {
            return this.f1167a.isFinished();
        }

        @Override // androidx.core.view.WindowInsetsAnimationControllerCompat.b
        public void j(@Nullable Insets insets, float f, float f2) {
            this.f1167a.setInsetsAndAlpha(insets == null ? null : insets.toPlatformInsets(), f, f2);
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        public void a(boolean z) {
            throw null;
        }

        public float b() {
            throw null;
        }

        @FloatRange(from = 0.0d, to = 1.0d)
        public float c() {
            throw null;
        }

        @NonNull
        public Insets d() {
            throw null;
        }

        @NonNull
        public Insets e() {
            throw null;
        }

        @NonNull
        public Insets f() {
            throw null;
        }

        public int g() {
            throw null;
        }

        public boolean h() {
            throw null;
        }

        public boolean i() {
            throw null;
        }

        public void j(@Nullable Insets insets, @FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
            throw null;
        }
    }

    @RequiresApi(30)
    public WindowInsetsAnimationControllerCompat(@NonNull WindowInsetsAnimationController windowInsetsAnimationController) {
        this.f1166a = new a(windowInsetsAnimationController);
    }

    public void finish(boolean z) {
        this.f1166a.a(z);
    }

    public float getCurrentAlpha() {
        return this.f1166a.b();
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getCurrentFraction() {
        return this.f1166a.c();
    }

    @NonNull
    public Insets getCurrentInsets() {
        return this.f1166a.d();
    }

    @NonNull
    public Insets getHiddenStateInsets() {
        return this.f1166a.e();
    }

    @NonNull
    public Insets getShownStateInsets() {
        return this.f1166a.f();
    }

    public int getTypes() {
        return this.f1166a.g();
    }

    public boolean isCancelled() {
        return this.f1166a.h();
    }

    public boolean isFinished() {
        return this.f1166a.i();
    }

    public boolean isReady() {
        return (isFinished() || isCancelled()) ? false : true;
    }

    public void setInsetsAndAlpha(@Nullable Insets insets, @FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
        this.f1166a.j(insets, f, f2);
    }
}
