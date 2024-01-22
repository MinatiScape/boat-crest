package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.transition.Transition;
/* loaded from: classes.dex */
public class s {

    /* loaded from: classes.dex */
    public static class a extends AnimatorListenerAdapter implements Transition.TransitionListener {
        public final View h;
        public final View i;
        public final int j;
        public final int k;
        public int[] l;
        public float m;
        public float n;
        public final float o;
        public final float p;

        public a(View view, View view2, int i, int i2, float f, float f2) {
            this.i = view;
            this.h = view2;
            this.j = i - Math.round(view.getTranslationX());
            this.k = i2 - Math.round(view.getTranslationY());
            this.o = f;
            this.p = f2;
            int i3 = R.id.transition_position;
            int[] iArr = (int[]) view2.getTag(i3);
            this.l = iArr;
            if (iArr != null) {
                view2.setTag(i3, null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            if (this.l == null) {
                this.l = new int[2];
            }
            this.l[0] = Math.round(this.j + this.i.getTranslationX());
            this.l[1] = Math.round(this.k + this.i.getTranslationY());
            this.h.setTag(R.id.transition_position, this.l);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationPause(Animator animator) {
            this.m = this.i.getTranslationX();
            this.n = this.i.getTranslationY();
            this.i.setTranslationX(this.o);
            this.i.setTranslationY(this.p);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationResume(Animator animator) {
            this.i.setTranslationX(this.m);
            this.i.setTranslationY(this.n);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionCancel(@NonNull Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(@NonNull Transition transition) {
            this.i.setTranslationX(this.o);
            this.i.setTranslationY(this.p);
            transition.removeListener(this);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionPause(@NonNull Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionResume(@NonNull Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionStart(@NonNull Transition transition) {
        }
    }

    @Nullable
    public static Animator a(@NonNull View view, @NonNull TransitionValues transitionValues, int i, int i2, float f, float f2, float f3, float f4, @Nullable TimeInterpolator timeInterpolator, @NonNull Transition transition) {
        float f5;
        float f6;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        int[] iArr = (int[]) transitionValues.view.getTag(R.id.transition_position);
        if (iArr != null) {
            f5 = (iArr[0] - i) + translationX;
            f6 = (iArr[1] - i2) + translationY;
        } else {
            f5 = f;
            f6 = f2;
        }
        int round = i + Math.round(f5 - translationX);
        int round2 = i2 + Math.round(f6 - translationY);
        view.setTranslationX(f5);
        view.setTranslationY(f6);
        if (f5 == f3 && f6 == f4) {
            return null;
        }
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(View.TRANSLATION_X, f5, f3), PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, f6, f4));
        a aVar = new a(view, transitionValues.view, round, round2, translationX, translationY);
        transition.addListener(aVar);
        ofPropertyValuesHolder.addListener(aVar);
        androidx.transition.a.a(ofPropertyValuesHolder, aVar);
        ofPropertyValuesHolder.setInterpolator(timeInterpolator);
        return ofPropertyValuesHolder;
    }
}
