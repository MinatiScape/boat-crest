package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Property;
import android.view.animation.Interpolator;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimationUtilsCompat;
import com.coveiot.android.bleabstract.utils.idoUtils.IDOUtils;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import java.util.Arrays;
/* loaded from: classes10.dex */
public final class h extends e<ObjectAnimator> {
    public static final int[] l = {533, 567, 850, 750};
    public static final int[] m = {1267, 1000, com.veryfit.multi.nativeprotocol.b.n1, 0};
    public static final Property<h, Float> n = new c(Float.class, "animationFraction");
    public ObjectAnimator d;
    public ObjectAnimator e;
    public final Interpolator[] f;
    public final BaseProgressIndicatorSpec g;
    public int h;
    public boolean i;
    public float j;
    public Animatable2Compat.AnimationCallback k;

    /* loaded from: classes10.dex */
    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            super.onAnimationRepeat(animator);
            h hVar = h.this;
            hVar.h = (hVar.h + 1) % h.this.g.indicatorColors.length;
            h.this.i = true;
        }
    }

    /* loaded from: classes10.dex */
    public class b extends AnimatorListenerAdapter {
        public b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            h.this.a();
            h hVar = h.this;
            Animatable2Compat.AnimationCallback animationCallback = hVar.k;
            if (animationCallback != null) {
                animationCallback.onAnimationEnd(hVar.f10335a);
            }
        }
    }

    /* loaded from: classes10.dex */
    public class c extends Property<h, Float> {
        public c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public Float get(h hVar) {
            return Float.valueOf(hVar.n());
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(h hVar, Float f) {
            hVar.r(f.floatValue());
        }
    }

    public h(@NonNull Context context, @NonNull LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(2);
        this.h = 0;
        this.k = null;
        this.g = linearProgressIndicatorSpec;
        this.f = new Interpolator[]{AnimationUtilsCompat.loadInterpolator(context, R.animator.linear_indeterminate_line1_head_interpolator), AnimationUtilsCompat.loadInterpolator(context, R.animator.linear_indeterminate_line1_tail_interpolator), AnimationUtilsCompat.loadInterpolator(context, R.animator.linear_indeterminate_line2_head_interpolator), AnimationUtilsCompat.loadInterpolator(context, R.animator.linear_indeterminate_line2_tail_interpolator)};
    }

    @Override // com.google.android.material.progressindicator.e
    public void a() {
        ObjectAnimator objectAnimator = this.d;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    @Override // com.google.android.material.progressindicator.e
    public void c() {
        q();
    }

    @Override // com.google.android.material.progressindicator.e
    public void d(@NonNull Animatable2Compat.AnimationCallback animationCallback) {
        this.k = animationCallback;
    }

    @Override // com.google.android.material.progressindicator.e
    public void f() {
        ObjectAnimator objectAnimator = this.e;
        if (objectAnimator == null || objectAnimator.isRunning()) {
            return;
        }
        a();
        if (this.f10335a.isVisible()) {
            this.e.setFloatValues(this.j, 1.0f);
            this.e.setDuration((1.0f - this.j) * 1800.0f);
            this.e.start();
        }
    }

    @Override // com.google.android.material.progressindicator.e
    public void g() {
        o();
        q();
        this.d.start();
    }

    @Override // com.google.android.material.progressindicator.e
    public void h() {
        this.k = null;
    }

    public final float n() {
        return this.j;
    }

    public final void o() {
        if (this.d == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, n, 0.0f, 1.0f);
            this.d = ofFloat;
            ofFloat.setDuration(IDOUtils.DEFAULT_EXERCISE_TIME);
            this.d.setInterpolator(null);
            this.d.setRepeatCount(-1);
            this.d.addListener(new a());
        }
        if (this.e == null) {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, n, 1.0f);
            this.e = ofFloat2;
            ofFloat2.setDuration(IDOUtils.DEFAULT_EXERCISE_TIME);
            this.e.setInterpolator(null);
            this.e.addListener(new b());
        }
    }

    public final void p() {
        if (this.i) {
            Arrays.fill(this.c, MaterialColors.compositeARGBWithAlpha(this.g.indicatorColors[this.h], this.f10335a.getAlpha()));
            this.i = false;
        }
    }

    @VisibleForTesting
    public void q() {
        this.h = 0;
        int compositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(this.g.indicatorColors[0], this.f10335a.getAlpha());
        int[] iArr = this.c;
        iArr[0] = compositeARGBWithAlpha;
        iArr[1] = compositeARGBWithAlpha;
    }

    @VisibleForTesting
    public void r(float f) {
        this.j = f;
        s((int) (f * 1800.0f));
        p();
        this.f10335a.invalidateSelf();
    }

    public final void s(int i) {
        for (int i2 = 0; i2 < 4; i2++) {
            this.b[i2] = Math.max(0.0f, Math.min(1.0f, this.f[i2].getInterpolation(b(i, m[i2], l[i2]))));
        }
    }
}
