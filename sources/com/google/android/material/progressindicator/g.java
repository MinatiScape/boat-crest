package com.google.android.material.progressindicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.util.Property;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.color.MaterialColors;
import java.util.Arrays;
/* loaded from: classes10.dex */
public final class g extends e<ObjectAnimator> {
    public static final Property<g, Float> j = new b(Float.class, "animationFraction");
    public ObjectAnimator d;
    public FastOutSlowInInterpolator e;
    public final BaseProgressIndicatorSpec f;
    public int g;
    public boolean h;
    public float i;

    /* loaded from: classes10.dex */
    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            super.onAnimationRepeat(animator);
            g gVar = g.this;
            gVar.g = (gVar.g + 1) % g.this.f.indicatorColors.length;
            g.this.h = true;
        }
    }

    /* loaded from: classes10.dex */
    public class b extends Property<g, Float> {
        public b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public Float get(g gVar) {
            return Float.valueOf(gVar.n());
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(g gVar, Float f) {
            gVar.r(f.floatValue());
        }
    }

    public g(@NonNull LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(3);
        this.g = 1;
        this.f = linearProgressIndicatorSpec;
        this.e = new FastOutSlowInInterpolator();
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
    public void d(@Nullable Animatable2Compat.AnimationCallback animationCallback) {
    }

    @Override // com.google.android.material.progressindicator.e
    public void f() {
    }

    @Override // com.google.android.material.progressindicator.e
    public void g() {
        o();
        q();
        this.d.start();
    }

    @Override // com.google.android.material.progressindicator.e
    public void h() {
    }

    public final float n() {
        return this.i;
    }

    public final void o() {
        if (this.d == null) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, j, 0.0f, 1.0f);
            this.d = ofFloat;
            ofFloat.setDuration(333L);
            this.d.setInterpolator(null);
            this.d.setRepeatCount(-1);
            this.d.addListener(new a());
        }
    }

    public final void p() {
        if (!this.h || this.b[3] >= 1.0f) {
            return;
        }
        int[] iArr = this.c;
        iArr[2] = iArr[1];
        iArr[1] = iArr[0];
        iArr[0] = MaterialColors.compositeARGBWithAlpha(this.f.indicatorColors[this.g], this.f10335a.getAlpha());
        this.h = false;
    }

    @VisibleForTesting
    public void q() {
        this.h = true;
        this.g = 1;
        Arrays.fill(this.c, MaterialColors.compositeARGBWithAlpha(this.f.indicatorColors[0], this.f10335a.getAlpha()));
    }

    @VisibleForTesting
    public void r(float f) {
        this.i = f;
        s((int) (f * 333.0f));
        p();
        this.f10335a.invalidateSelf();
    }

    public final void s(int i) {
        this.b[0] = 0.0f;
        float b2 = b(i, 0, 667);
        float[] fArr = this.b;
        float interpolation = this.e.getInterpolation(b2);
        fArr[2] = interpolation;
        fArr[1] = interpolation;
        float[] fArr2 = this.b;
        float interpolation2 = this.e.getInterpolation(b2 + 0.49925038f);
        fArr2[4] = interpolation2;
        fArr2[3] = interpolation2;
        this.b[5] = 1.0f;
    }
}
