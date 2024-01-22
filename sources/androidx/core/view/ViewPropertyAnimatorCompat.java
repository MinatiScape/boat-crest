package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public final class ViewPropertyAnimatorCompat {

    /* renamed from: a  reason: collision with root package name */
    public final WeakReference<View> f1158a;
    public Runnable b = null;
    public Runnable c = null;
    public int d = -1;

    /* loaded from: classes.dex */
    public class a extends AnimatorListenerAdapter {
        public final /* synthetic */ ViewPropertyAnimatorListener h;
        public final /* synthetic */ View i;

        public a(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, ViewPropertyAnimatorListener viewPropertyAnimatorListener, View view) {
            this.h = viewPropertyAnimatorListener;
            this.i = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.h.onAnimationCancel(this.i);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.h.onAnimationEnd(this.i);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.h.onAnimationStart(this.i);
        }
    }

    @RequiresApi(16)
    /* loaded from: classes.dex */
    public static class b {
        @DoNotInline
        public static ViewPropertyAnimator a(ViewPropertyAnimator viewPropertyAnimator, Runnable runnable) {
            return viewPropertyAnimator.withEndAction(runnable);
        }

        @DoNotInline
        public static ViewPropertyAnimator b(ViewPropertyAnimator viewPropertyAnimator) {
            return viewPropertyAnimator.withLayer();
        }

        @DoNotInline
        public static ViewPropertyAnimator c(ViewPropertyAnimator viewPropertyAnimator, Runnable runnable) {
            return viewPropertyAnimator.withStartAction(runnable);
        }
    }

    @RequiresApi(18)
    /* loaded from: classes.dex */
    public static class c {
        @DoNotInline
        public static Interpolator a(ViewPropertyAnimator viewPropertyAnimator) {
            return (Interpolator) viewPropertyAnimator.getInterpolator();
        }
    }

    @RequiresApi(19)
    /* loaded from: classes.dex */
    public static class d {
        @DoNotInline
        public static ViewPropertyAnimator a(ViewPropertyAnimator viewPropertyAnimator, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
            return viewPropertyAnimator.setUpdateListener(animatorUpdateListener);
        }
    }

    @RequiresApi(21)
    /* loaded from: classes.dex */
    public static class e {
        @DoNotInline
        public static ViewPropertyAnimator a(ViewPropertyAnimator viewPropertyAnimator, float f) {
            return viewPropertyAnimator.translationZ(f);
        }

        @DoNotInline
        public static ViewPropertyAnimator b(ViewPropertyAnimator viewPropertyAnimator, float f) {
            return viewPropertyAnimator.translationZBy(f);
        }

        @DoNotInline
        public static ViewPropertyAnimator c(ViewPropertyAnimator viewPropertyAnimator, float f) {
            return viewPropertyAnimator.z(f);
        }

        @DoNotInline
        public static ViewPropertyAnimator d(ViewPropertyAnimator viewPropertyAnimator, float f) {
            return viewPropertyAnimator.zBy(f);
        }
    }

    /* loaded from: classes.dex */
    public static class f implements ViewPropertyAnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public ViewPropertyAnimatorCompat f1159a;
        public boolean b;

        public f(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
            this.f1159a = viewPropertyAnimatorCompat;
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationCancel(@NonNull View view) {
            Object tag = view.getTag(2113929216);
            ViewPropertyAnimatorListener viewPropertyAnimatorListener = tag instanceof ViewPropertyAnimatorListener ? (ViewPropertyAnimatorListener) tag : null;
            if (viewPropertyAnimatorListener != null) {
                viewPropertyAnimatorListener.onAnimationCancel(view);
            }
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        @SuppressLint({"WrongConstant"})
        public void onAnimationEnd(@NonNull View view) {
            int i = this.f1159a.d;
            if (i > -1) {
                view.setLayerType(i, null);
                this.f1159a.d = -1;
            }
            if (Build.VERSION.SDK_INT >= 16 || !this.b) {
                ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.f1159a;
                Runnable runnable = viewPropertyAnimatorCompat.c;
                if (runnable != null) {
                    viewPropertyAnimatorCompat.c = null;
                    runnable.run();
                }
                Object tag = view.getTag(2113929216);
                ViewPropertyAnimatorListener viewPropertyAnimatorListener = tag instanceof ViewPropertyAnimatorListener ? (ViewPropertyAnimatorListener) tag : null;
                if (viewPropertyAnimatorListener != null) {
                    viewPropertyAnimatorListener.onAnimationEnd(view);
                }
                this.b = true;
            }
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationStart(@NonNull View view) {
            this.b = false;
            if (this.f1159a.d > -1) {
                view.setLayerType(2, null);
            }
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = this.f1159a;
            Runnable runnable = viewPropertyAnimatorCompat.b;
            if (runnable != null) {
                viewPropertyAnimatorCompat.b = null;
                runnable.run();
            }
            Object tag = view.getTag(2113929216);
            ViewPropertyAnimatorListener viewPropertyAnimatorListener = tag instanceof ViewPropertyAnimatorListener ? (ViewPropertyAnimatorListener) tag : null;
            if (viewPropertyAnimatorListener != null) {
                viewPropertyAnimatorListener.onAnimationStart(view);
            }
        }
    }

    public ViewPropertyAnimatorCompat(View view) {
        this.f1158a = new WeakReference<>(view);
    }

    @NonNull
    public ViewPropertyAnimatorCompat alpha(float f2) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().alpha(f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat alphaBy(float f2) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().alphaBy(f2);
        }
        return this;
    }

    public final void c(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        if (viewPropertyAnimatorListener != null) {
            view.animate().setListener(new a(this, viewPropertyAnimatorListener, view));
        } else {
            view.animate().setListener(null);
        }
    }

    public void cancel() {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public long getDuration() {
        View view = this.f1158a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    @Nullable
    public Interpolator getInterpolator() {
        View view = this.f1158a.get();
        if (view == null || Build.VERSION.SDK_INT < 18) {
            return null;
        }
        return c.a(view.animate());
    }

    public long getStartDelay() {
        View view = this.f1158a.get();
        if (view != null) {
            return view.animate().getStartDelay();
        }
        return 0L;
    }

    @NonNull
    public ViewPropertyAnimatorCompat rotation(float f2) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().rotation(f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat rotationBy(float f2) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().rotationBy(f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat rotationX(float f2) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().rotationX(f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat rotationXBy(float f2) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().rotationXBy(f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat rotationY(float f2) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().rotationY(f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat rotationYBy(float f2) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().rotationYBy(f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat scaleX(float f2) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().scaleX(f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat scaleXBy(float f2) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().scaleXBy(f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat scaleY(float f2) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().scaleY(f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat scaleYBy(float f2) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().scaleYBy(f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat setDuration(long j) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat setInterpolator(@Nullable Interpolator interpolator) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat setListener(@Nullable ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        View view = this.f1158a.get();
        if (view != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                c(view, viewPropertyAnimatorListener);
            } else {
                view.setTag(2113929216, viewPropertyAnimatorListener);
                c(view, new f(this));
            }
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat setStartDelay(long j) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().setStartDelay(j);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat setUpdateListener(@Nullable final ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
        final View view = this.f1158a.get();
        if (view != null && Build.VERSION.SDK_INT >= 19) {
            d.a(view.animate(), viewPropertyAnimatorUpdateListener != null ? new ValueAnimator.AnimatorUpdateListener() { // from class: androidx.core.view.j
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ViewPropertyAnimatorUpdateListener.this.onAnimationUpdate(view);
                }
            } : null);
        }
        return this;
    }

    public void start() {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    @NonNull
    public ViewPropertyAnimatorCompat translationX(float f2) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().translationX(f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat translationXBy(float f2) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().translationXBy(f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat translationY(float f2) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().translationY(f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat translationYBy(float f2) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().translationYBy(f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat translationZ(float f2) {
        View view = this.f1158a.get();
        if (view != null && Build.VERSION.SDK_INT >= 21) {
            e.a(view.animate(), f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat translationZBy(float f2) {
        View view = this.f1158a.get();
        if (view != null && Build.VERSION.SDK_INT >= 21) {
            e.b(view.animate(), f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat withEndAction(@NonNull Runnable runnable) {
        View view = this.f1158a.get();
        if (view != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                b.a(view.animate(), runnable);
            } else {
                c(view, new f(this));
                this.c = runnable;
            }
        }
        return this;
    }

    @NonNull
    @SuppressLint({"WrongConstant"})
    public ViewPropertyAnimatorCompat withLayer() {
        View view = this.f1158a.get();
        if (view != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                b.b(view.animate());
            } else {
                this.d = view.getLayerType();
                c(view, new f(this));
            }
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat withStartAction(@NonNull Runnable runnable) {
        View view = this.f1158a.get();
        if (view != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                b.c(view.animate(), runnable);
            } else {
                c(view, new f(this));
                this.b = runnable;
            }
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat x(float f2) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().x(f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat xBy(float f2) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().xBy(f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat y(float f2) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().y(f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat yBy(float f2) {
        View view = this.f1158a.get();
        if (view != null) {
            view.animate().yBy(f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat z(float f2) {
        View view = this.f1158a.get();
        if (view != null && Build.VERSION.SDK_INT >= 21) {
            e.c(view.animate(), f2);
        }
        return this;
    }

    @NonNull
    public ViewPropertyAnimatorCompat zBy(float f2) {
        View view = this.f1158a.get();
        if (view != null && Build.VERSION.SDK_INT >= 21) {
            e.d(view.animate(), f2);
        }
        return this;
    }
}
