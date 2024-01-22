package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@RequiresApi(21)
/* loaded from: classes10.dex */
public final class SlideDistanceProvider implements VisibilityAnimatorProvider {

    /* renamed from: a  reason: collision with root package name */
    public int f10430a;
    @Px
    public int b = -1;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes10.dex */
    public @interface GravityFlag {
    }

    /* loaded from: classes10.dex */
    public class a extends AnimatorListenerAdapter {
        public final /* synthetic */ View h;
        public final /* synthetic */ float i;

        public a(View view, float f) {
            this.h = view;
            this.i = f;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.h.setTranslationX(this.i);
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
            this.h.setTranslationY(this.i);
        }
    }

    public SlideDistanceProvider(int i) {
        this.f10430a = i;
    }

    public static Animator a(View view, View view2, int i, @Px int i2) {
        float translationX = view2.getTranslationX();
        float translationY = view2.getTranslationY();
        if (i != 3) {
            if (i != 5) {
                if (i != 48) {
                    if (i != 80) {
                        if (i == 8388611) {
                            return c(view2, f(view) ? i2 + translationX : translationX - i2, translationX, translationX);
                        } else if (i == 8388613) {
                            return c(view2, f(view) ? translationX - i2 : i2 + translationX, translationX, translationX);
                        } else {
                            throw new IllegalArgumentException("Invalid slide direction: " + i);
                        }
                    }
                    return d(view2, i2 + translationY, translationY, translationY);
                }
                return d(view2, translationY - i2, translationY, translationY);
            }
            return c(view2, translationX - i2, translationX, translationX);
        }
        return c(view2, i2 + translationX, translationX, translationX);
    }

    public static Animator b(View view, View view2, int i, @Px int i2) {
        float translationX = view2.getTranslationX();
        float translationY = view2.getTranslationY();
        if (i != 3) {
            if (i != 5) {
                if (i != 48) {
                    if (i != 80) {
                        if (i == 8388611) {
                            return c(view2, translationX, f(view) ? translationX - i2 : i2 + translationX, translationX);
                        } else if (i == 8388613) {
                            return c(view2, translationX, f(view) ? i2 + translationX : translationX - i2, translationX);
                        } else {
                            throw new IllegalArgumentException("Invalid slide direction: " + i);
                        }
                    }
                    return d(view2, translationY, translationY - i2, translationY);
                }
                return d(view2, translationY, i2 + translationY, translationY);
            }
            return c(view2, translationX, i2 + translationX, translationX);
        }
        return c(view2, translationX, translationX - i2, translationX);
    }

    public static Animator c(View view, float f, float f2, float f3) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(View.TRANSLATION_X, f, f2));
        ofPropertyValuesHolder.addListener(new a(view, f3));
        return ofPropertyValuesHolder;
    }

    public static Animator d(View view, float f, float f2, float f3) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, f, f2));
        ofPropertyValuesHolder.addListener(new b(view, f3));
        return ofPropertyValuesHolder;
    }

    public static boolean f(View view) {
        return ViewCompat.getLayoutDirection(view) == 1;
    }

    @Override // com.google.android.material.transition.platform.VisibilityAnimatorProvider
    @Nullable
    public Animator createAppear(@NonNull ViewGroup viewGroup, @NonNull View view) {
        return a(viewGroup, view, this.f10430a, e(view.getContext()));
    }

    @Override // com.google.android.material.transition.platform.VisibilityAnimatorProvider
    @Nullable
    public Animator createDisappear(@NonNull ViewGroup viewGroup, @NonNull View view) {
        return b(viewGroup, view, this.f10430a, e(view.getContext()));
    }

    public final int e(Context context) {
        int i = this.b;
        return i != -1 ? i : context.getResources().getDimensionPixelSize(R.dimen.mtrl_transition_shared_axis_slide_distance);
    }

    @Px
    public int getSlideDistance() {
        return this.b;
    }

    public int getSlideEdge() {
        return this.f10430a;
    }

    public void setSlideDistance(@Px int i) {
        if (i >= 0) {
            this.b = i;
            return;
        }
        throw new IllegalArgumentException("Slide distance must be positive. If attempting to reverse the direction of the slide, use setSlideEdge(int) instead.");
    }

    public void setSlideEdge(int i) {
        this.f10430a = i;
    }
}
