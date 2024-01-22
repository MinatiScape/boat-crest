package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes10.dex */
public final class ScaleProvider implements VisibilityAnimatorProvider {

    /* renamed from: a  reason: collision with root package name */
    public float f10407a;
    public float b;
    public float c;
    public float d;
    public boolean e;
    public boolean f;

    /* loaded from: classes10.dex */
    public class a extends AnimatorListenerAdapter {
        public final /* synthetic */ View h;
        public final /* synthetic */ float i;
        public final /* synthetic */ float j;

        public a(View view, float f, float f2) {
            this.h = view;
            this.i = f;
            this.j = f2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.h.setScaleX(this.i);
            this.h.setScaleY(this.j);
        }
    }

    public ScaleProvider() {
        this(true);
    }

    public static Animator a(View view, float f, float f2) {
        float scaleX = view.getScaleX();
        float scaleY = view.getScaleY();
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(View.SCALE_X, scaleX * f, scaleX * f2), PropertyValuesHolder.ofFloat(View.SCALE_Y, f * scaleY, f2 * scaleY));
        ofPropertyValuesHolder.addListener(new a(view, scaleX, scaleY));
        return ofPropertyValuesHolder;
    }

    @Override // com.google.android.material.transition.VisibilityAnimatorProvider
    @Nullable
    public Animator createAppear(@NonNull ViewGroup viewGroup, @NonNull View view) {
        if (this.e) {
            return a(view, this.c, this.d);
        }
        return a(view, this.b, this.f10407a);
    }

    @Override // com.google.android.material.transition.VisibilityAnimatorProvider
    @Nullable
    public Animator createDisappear(@NonNull ViewGroup viewGroup, @NonNull View view) {
        if (this.f) {
            if (this.e) {
                return a(view, this.f10407a, this.b);
            }
            return a(view, this.d, this.c);
        }
        return null;
    }

    public float getIncomingEndScale() {
        return this.d;
    }

    public float getIncomingStartScale() {
        return this.c;
    }

    public float getOutgoingEndScale() {
        return this.b;
    }

    public float getOutgoingStartScale() {
        return this.f10407a;
    }

    public boolean isGrowing() {
        return this.e;
    }

    public boolean isScaleOnDisappear() {
        return this.f;
    }

    public void setGrowing(boolean z) {
        this.e = z;
    }

    public void setIncomingEndScale(float f) {
        this.d = f;
    }

    public void setIncomingStartScale(float f) {
        this.c = f;
    }

    public void setOutgoingEndScale(float f) {
        this.b = f;
    }

    public void setOutgoingStartScale(float f) {
        this.f10407a = f;
    }

    public void setScaleOnDisappear(boolean z) {
        this.f = z;
    }

    public ScaleProvider(boolean z) {
        this.f10407a = 1.0f;
        this.b = 1.1f;
        this.c = 0.8f;
        this.d = 1.0f;
        this.f = true;
        this.e = z;
    }
}
