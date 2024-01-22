package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.util.Property;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.animation.ArgbEvaluatorCompat;
import com.google.android.material.color.MaterialColors;
/* loaded from: classes10.dex */
public final class b extends e<ObjectAnimator> {
    public static final int[] l = {0, 1350, 2700, 4050};
    public static final int[] m = {667, 2017, 3367, 4717};
    public static final int[] n = {1000, 2350, 3700, 5050};
    public static final Property<b, Float> o = new c(Float.class, "animationFraction");
    public static final Property<b, Float> p = new d(Float.class, "completeEndFraction");
    public ObjectAnimator d;
    public ObjectAnimator e;
    public final FastOutSlowInInterpolator f;
    public final BaseProgressIndicatorSpec g;
    public int h;
    public float i;
    public float j;
    public Animatable2Compat.AnimationCallback k;

    /* loaded from: classes10.dex */
    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            super.onAnimationRepeat(animator);
            b bVar = b.this;
            bVar.h = (bVar.h + 4) % b.this.g.indicatorColors.length;
        }
    }

    /* renamed from: com.google.android.material.progressindicator.b$b  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public class C0431b extends AnimatorListenerAdapter {
        public C0431b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            b.this.a();
            b bVar = b.this;
            Animatable2Compat.AnimationCallback animationCallback = bVar.k;
            if (animationCallback != null) {
                animationCallback.onAnimationEnd(bVar.f10335a);
            }
        }
    }

    /* loaded from: classes10.dex */
    public class c extends Property<b, Float> {
        public c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public Float get(b bVar) {
            return Float.valueOf(bVar.o());
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(b bVar, Float f) {
            bVar.t(f.floatValue());
        }
    }

    /* loaded from: classes10.dex */
    public class d extends Property<b, Float> {
        public d(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public Float get(b bVar) {
            return Float.valueOf(bVar.p());
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(b bVar, Float f) {
            bVar.u(f.floatValue());
        }
    }

    public b(@NonNull CircularProgressIndicatorSpec circularProgressIndicatorSpec) {
        super(1);
        this.h = 0;
        this.k = null;
        this.g = circularProgressIndicatorSpec;
        this.f = new FastOutSlowInInterpolator();
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
        s();
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
        if (this.f10335a.isVisible()) {
            this.e.start();
        } else {
            a();
        }
    }

    @Override // com.google.android.material.progressindicator.e
    public void g() {
        q();
        s();
        this.d.start();
    }

    @Override // com.google.android.material.progressindicator.e
    public void h() {
        this.k = null;
    }

    public final float o() {
        return this.i;
    }

    public final float p() {
        return this.j;
    }

    public final void q() {
        if (this.d == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, o, 0.0f, 1.0f);
            this.d = ofFloat;
            ofFloat.setDuration(5400L);
            this.d.setInterpolator(null);
            this.d.setRepeatCount(-1);
            this.d.addListener(new a());
        }
        if (this.e == null) {
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, p, 0.0f, 1.0f);
            this.e = ofFloat2;
            ofFloat2.setDuration(333L);
            this.e.setInterpolator(this.f);
            this.e.addListener(new C0431b());
        }
    }

    public final void r(int i) {
        for (int i2 = 0; i2 < 4; i2++) {
            float b = b(i, n[i2], com.veryfit.multi.nativeprotocol.b.n1);
            if (b >= 0.0f && b <= 1.0f) {
                int i3 = i2 + this.h;
                int[] iArr = this.g.indicatorColors;
                int length = i3 % iArr.length;
                this.c[0] = ArgbEvaluatorCompat.getInstance().evaluate(this.f.getInterpolation(b), Integer.valueOf(MaterialColors.compositeARGBWithAlpha(iArr[length], this.f10335a.getAlpha())), Integer.valueOf(MaterialColors.compositeARGBWithAlpha(this.g.indicatorColors[(length + 1) % iArr.length], this.f10335a.getAlpha()))).intValue();
                return;
            }
        }
    }

    @VisibleForTesting
    public void s() {
        this.h = 0;
        this.c[0] = MaterialColors.compositeARGBWithAlpha(this.g.indicatorColors[0], this.f10335a.getAlpha());
        this.j = 0.0f;
    }

    @VisibleForTesting
    public void t(float f) {
        this.i = f;
        int i = (int) (f * 5400.0f);
        v(i);
        r(i);
        this.f10335a.invalidateSelf();
    }

    public final void u(float f) {
        this.j = f;
    }

    public final void v(int i) {
        float[] fArr = this.b;
        float f = this.i;
        fArr[0] = (f * 1520.0f) - 20.0f;
        fArr[1] = f * 1520.0f;
        for (int i2 = 0; i2 < 4; i2++) {
            float b = b(i, l[i2], 667);
            float[] fArr2 = this.b;
            fArr2[1] = fArr2[1] + (this.f.getInterpolation(b) * 250.0f);
            float b2 = b(i, m[i2], 667);
            float[] fArr3 = this.b;
            fArr3[0] = fArr3[0] + (this.f.getInterpolation(b2) * 250.0f);
        }
        float[] fArr4 = this.b;
        fArr4[0] = fArr4[0] + ((fArr4[1] - fArr4[0]) * this.j);
        fArr4[0] = fArr4[0] / 360.0f;
        fArr4[1] = fArr4[1] / 360.0f;
    }
}
