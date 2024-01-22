package com.mappls.sdk.maps.utils;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import androidx.annotation.AnimatorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
/* loaded from: classes11.dex */
public class AnimatorUtils {

    /* loaded from: classes11.dex */
    public interface OnAnimationEndListener {
        void onAnimationEnd();
    }

    /* loaded from: classes11.dex */
    public class a extends AnimatorListenerAdapter {
        public final /* synthetic */ View h;
        public final /* synthetic */ OnAnimationEndListener i;

        public a(View view, OnAnimationEndListener onAnimationEndListener) {
            this.h = view;
            this.i = onAnimationEndListener;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            this.h.setLayerType(0, null);
            OnAnimationEndListener onAnimationEndListener = this.i;
            if (onAnimationEndListener != null) {
                onAnimationEndListener.onAnimationEnd();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b extends AnimatorListenerAdapter {
        public final /* synthetic */ View h;

        public b(View view) {
            this.h = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            this.h.setLayerType(0, null);
        }
    }

    /* loaded from: classes11.dex */
    public class c extends AnimatorListenerAdapter {
        public final /* synthetic */ View h;

        public c(View view) {
            this.h = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            this.h.setLayerType(0, null);
        }
    }

    /* loaded from: classes11.dex */
    public class d extends AnimatorListenerAdapter {
        public final /* synthetic */ View h;
        public final /* synthetic */ OnAnimationEndListener i;

        public d(View view, OnAnimationEndListener onAnimationEndListener) {
            this.h = view;
            this.i = onAnimationEndListener;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            this.h.setLayerType(0, null);
            OnAnimationEndListener onAnimationEndListener = this.i;
            if (onAnimationEndListener != null) {
                onAnimationEndListener.onAnimationEnd();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            this.h.setVisibility(0);
        }
    }

    public static void alpha(@NonNull View view, float f, @Nullable OnAnimationEndListener onAnimationEndListener) {
        view.setLayerType(2, null);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, view.getAlpha(), f);
        ofFloat.addListener(new d(view, onAnimationEndListener));
        ofFloat.start();
    }

    public static void animate(@NonNull View view, @AnimatorRes int i, @Nullable OnAnimationEndListener onAnimationEndListener) {
        animate(view, i, -1, onAnimationEndListener);
    }

    public static void rotate(@NonNull View view, float f) {
        view.setLayerType(2, null);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ROTATION, view.getRotation(), f);
        ofFloat.addListener(new b(view));
        ofFloat.start();
    }

    public static void rotateBy(@NonNull View view, float f) {
        view.setLayerType(2, null);
        view.animate().rotationBy(f).setInterpolator(new FastOutSlowInInterpolator()).setListener(new c(view));
    }

    public static void animate(@Nullable View view, @AnimatorRes int i, int i2, @Nullable OnAnimationEndListener onAnimationEndListener) {
        if (view == null) {
            return;
        }
        view.setLayerType(2, null);
        Animator loadAnimator = AnimatorInflater.loadAnimator(view.getContext(), i);
        if (i2 != -1) {
            loadAnimator.setDuration(i2);
        }
        loadAnimator.addListener(new a(view, onAnimationEndListener));
        loadAnimator.setTarget(view);
        loadAnimator.start();
    }

    public static void alpha(@NonNull View view, float f) {
        alpha(view, f, null);
    }

    public static void animate(@NonNull View view, @AnimatorRes int i) {
        animate(view, i, -1);
    }

    public static void animate(@NonNull View view, @AnimatorRes int i, int i2) {
        animate(view, i, i2, null);
    }
}
