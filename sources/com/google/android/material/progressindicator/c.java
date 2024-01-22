package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Property;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.animation.AnimationUtils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public abstract class c extends Drawable implements Animatable2Compat {
    public static final Property<c, Float> v = new C0432c(Float.class, "growFraction");
    public final Context h;
    public final BaseProgressIndicatorSpec i;
    public ValueAnimator k;
    public ValueAnimator l;
    public boolean m;
    public boolean n;
    public float o;
    public List<Animatable2Compat.AnimationCallback> p;
    public Animatable2Compat.AnimationCallback q;
    public boolean r;
    public float s;
    public int u;
    public final Paint t = new Paint();
    public AnimatorDurationScaleProvider j = new AnimatorDurationScaleProvider();

    /* loaded from: classes10.dex */
    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            c.this.e();
        }
    }

    /* loaded from: classes10.dex */
    public class b extends AnimatorListenerAdapter {
        public b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            c.super.setVisible(false, false);
            c.this.d();
        }
    }

    /* renamed from: com.google.android.material.progressindicator.c$c  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public class C0432c extends Property<c, Float> {
        public C0432c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public Float get(c cVar) {
            return Float.valueOf(cVar.g());
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(c cVar, Float f) {
            cVar.i(f.floatValue());
        }
    }

    public c(@NonNull Context context, @NonNull BaseProgressIndicatorSpec baseProgressIndicatorSpec) {
        this.h = context;
        this.i = baseProgressIndicatorSpec;
        setAlpha(255);
    }

    public void clearAnimationCallbacks() {
        this.p.clear();
        this.p = null;
    }

    public final void d() {
        Animatable2Compat.AnimationCallback animationCallback = this.q;
        if (animationCallback != null) {
            animationCallback.onAnimationEnd(this);
        }
        List<Animatable2Compat.AnimationCallback> list = this.p;
        if (list == null || this.r) {
            return;
        }
        for (Animatable2Compat.AnimationCallback animationCallback2 : list) {
            animationCallback2.onAnimationEnd(this);
        }
    }

    public final void e() {
        Animatable2Compat.AnimationCallback animationCallback = this.q;
        if (animationCallback != null) {
            animationCallback.onAnimationStart(this);
        }
        List<Animatable2Compat.AnimationCallback> list = this.p;
        if (list == null || this.r) {
            return;
        }
        for (Animatable2Compat.AnimationCallback animationCallback2 : list) {
            animationCallback2.onAnimationStart(this);
        }
    }

    public final void f(@NonNull ValueAnimator... valueAnimatorArr) {
        boolean z = this.r;
        this.r = true;
        for (ValueAnimator valueAnimator : valueAnimatorArr) {
            valueAnimator.end();
        }
        this.r = z;
    }

    public float g() {
        if (this.i.isShowAnimationEnabled() || this.i.isHideAnimationEnabled()) {
            if (!this.n && !this.m) {
                return this.s;
            }
            return this.o;
        }
        return 1.0f;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.u;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public final void h() {
        if (this.k == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, v, 0.0f, 1.0f);
            this.k = ofFloat;
            ofFloat.setDuration(500L);
            this.k.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            k(this.k);
        }
        if (this.l == null) {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, v, 1.0f, 0.0f);
            this.l = ofFloat2;
            ofFloat2.setDuration(500L);
            this.l.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            j(this.l);
        }
    }

    public boolean hideNow() {
        return setVisible(false, false, false);
    }

    public void i(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (this.s != f) {
            this.s = f;
            invalidateSelf();
        }
    }

    public boolean isHiding() {
        ValueAnimator valueAnimator = this.l;
        return (valueAnimator != null && valueAnimator.isRunning()) || this.n;
    }

    public boolean isRunning() {
        return isShowing() || isHiding();
    }

    public boolean isShowing() {
        ValueAnimator valueAnimator = this.k;
        return (valueAnimator != null && valueAnimator.isRunning()) || this.m;
    }

    public final void j(@NonNull ValueAnimator valueAnimator) {
        ValueAnimator valueAnimator2 = this.l;
        if (valueAnimator2 != null && valueAnimator2.isRunning()) {
            throw new IllegalArgumentException("Cannot set hideAnimator while the current hideAnimator is running.");
        }
        this.l = valueAnimator;
        valueAnimator.addListener(new b());
    }

    public final void k(@NonNull ValueAnimator valueAnimator) {
        ValueAnimator valueAnimator2 = this.k;
        if (valueAnimator2 != null && valueAnimator2.isRunning()) {
            throw new IllegalArgumentException("Cannot set showAnimator while the current showAnimator is running.");
        }
        this.k = valueAnimator;
        valueAnimator.addListener(new a());
    }

    public boolean l(boolean z, boolean z2, boolean z3) {
        h();
        if (isVisible() || z) {
            ValueAnimator valueAnimator = z ? this.k : this.l;
            if (!z3) {
                if (valueAnimator.isRunning()) {
                    valueAnimator.end();
                } else {
                    f(valueAnimator);
                }
                return super.setVisible(z, false);
            } else if (z3 && valueAnimator.isRunning()) {
                return false;
            } else {
                boolean z4 = !z || super.setVisible(z, false);
                if (!(z ? this.i.isShowAnimationEnabled() : this.i.isHideAnimationEnabled())) {
                    f(valueAnimator);
                    return z4;
                }
                if (!z2 && Build.VERSION.SDK_INT >= 19 && valueAnimator.isPaused()) {
                    valueAnimator.resume();
                } else {
                    valueAnimator.start();
                }
                return z4;
            }
        }
        return false;
    }

    public void registerAnimationCallback(@NonNull Animatable2Compat.AnimationCallback animationCallback) {
        if (this.p == null) {
            this.p = new ArrayList();
        }
        if (this.p.contains(animationCallback)) {
            return;
        }
        this.p.add(animationCallback);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.u = i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.t.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        return setVisible(z, z2, true);
    }

    public void start() {
        l(true, true, false);
    }

    public void stop() {
        l(false, true, false);
    }

    public boolean unregisterAnimationCallback(@NonNull Animatable2Compat.AnimationCallback animationCallback) {
        List<Animatable2Compat.AnimationCallback> list = this.p;
        if (list == null || !list.contains(animationCallback)) {
            return false;
        }
        this.p.remove(animationCallback);
        if (this.p.isEmpty()) {
            this.p = null;
            return true;
        }
        return true;
    }

    public boolean setVisible(boolean z, boolean z2, boolean z3) {
        return l(z, z2, z3 && this.j.getSystemAnimatorDurationScale(this.h.getContentResolver()) > 0.0f);
    }
}
