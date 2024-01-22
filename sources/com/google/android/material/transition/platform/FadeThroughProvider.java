package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
@RequiresApi(21)
/* loaded from: classes10.dex */
public final class FadeThroughProvider implements VisibilityAnimatorProvider {

    /* renamed from: a  reason: collision with root package name */
    public float f10418a = 0.35f;

    /* loaded from: classes10.dex */
    public class a implements ValueAnimator.AnimatorUpdateListener {
        public final /* synthetic */ View h;
        public final /* synthetic */ float i;
        public final /* synthetic */ float j;
        public final /* synthetic */ float k;
        public final /* synthetic */ float l;

        public a(View view, float f, float f2, float f3, float f4) {
            this.h = view;
            this.i = f;
            this.j = f2;
            this.k = f3;
            this.l = f4;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.h.setAlpha(j.l(this.i, this.j, this.k, this.l, ((Float) valueAnimator.getAnimatedValue()).floatValue()));
        }
    }

    /* loaded from: classes10.dex */
    public class b extends AnimatorListenerAdapter {
        public final /* synthetic */ View h;
        public final /* synthetic */ float i;

        public b(View view, float f) {
            this.h = view;
            this.i = f;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.h.setAlpha(this.i);
        }
    }

    public static Animator a(View view, float f, float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3, @FloatRange(from = 0.0d, to = 1.0d) float f4, float f5) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new a(view, f, f2, f3, f4));
        ofFloat.addListener(new b(view, f5));
        return ofFloat;
    }

    @Override // com.google.android.material.transition.platform.VisibilityAnimatorProvider
    @Nullable
    public Animator createAppear(@NonNull ViewGroup viewGroup, @NonNull View view) {
        float alpha = view.getAlpha() == 0.0f ? 1.0f : view.getAlpha();
        return a(view, 0.0f, alpha, this.f10418a, 1.0f, alpha);
    }

    @Override // com.google.android.material.transition.platform.VisibilityAnimatorProvider
    @Nullable
    public Animator createDisappear(@NonNull ViewGroup viewGroup, @NonNull View view) {
        float alpha = view.getAlpha() == 0.0f ? 1.0f : view.getAlpha();
        return a(view, alpha, 0.0f, 0.0f, this.f10418a, alpha);
    }

    public float getProgressThreshold() {
        return this.f10418a;
    }

    public void setProgressThreshold(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.f10418a = f;
    }
}
