package com.github.mikephil.charting.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.github.mikephil.charting.animation.Easing;
/* loaded from: classes9.dex */
public class ChartAnimator {

    /* renamed from: a  reason: collision with root package name */
    public ValueAnimator.AnimatorUpdateListener f7929a;
    public float mPhaseY = 1.0f;
    public float mPhaseX = 1.0f;

    public ChartAnimator() {
    }

    @RequiresApi(11)
    public final ObjectAnimator a(int i, Easing.EasingFunction easingFunction) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseX", 0.0f, 1.0f);
        ofFloat.setInterpolator(easingFunction);
        ofFloat.setDuration(i);
        return ofFloat;
    }

    @RequiresApi(11)
    public void animateX(int i) {
        animateX(i, Easing.Linear);
    }

    @RequiresApi(11)
    public void animateXY(int i, int i2) {
        Easing.EasingFunction easingFunction = Easing.Linear;
        animateXY(i, i2, easingFunction, easingFunction);
    }

    @RequiresApi(11)
    public void animateY(int i) {
        animateY(i, Easing.Linear);
    }

    @RequiresApi(11)
    public final ObjectAnimator b(int i, Easing.EasingFunction easingFunction) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseY", 0.0f, 1.0f);
        ofFloat.setInterpolator(easingFunction);
        ofFloat.setDuration(i);
        return ofFloat;
    }

    public float getPhaseX() {
        return this.mPhaseX;
    }

    public float getPhaseY() {
        return this.mPhaseY;
    }

    public void setPhaseX(float f) {
        if (f > 1.0f) {
            f = 1.0f;
        } else if (f < 0.0f) {
            f = 0.0f;
        }
        this.mPhaseX = f;
    }

    public void setPhaseY(float f) {
        if (f > 1.0f) {
            f = 1.0f;
        } else if (f < 0.0f) {
            f = 0.0f;
        }
        this.mPhaseY = f;
    }

    @RequiresApi(11)
    public void animateX(int i, Easing.EasingFunction easingFunction) {
        ObjectAnimator a2 = a(i, easingFunction);
        a2.addUpdateListener(this.f7929a);
        a2.start();
    }

    @RequiresApi(11)
    public void animateXY(int i, int i2, Easing.EasingFunction easingFunction) {
        ObjectAnimator a2 = a(i, easingFunction);
        ObjectAnimator b = b(i2, easingFunction);
        if (i > i2) {
            a2.addUpdateListener(this.f7929a);
        } else {
            b.addUpdateListener(this.f7929a);
        }
        a2.start();
        b.start();
    }

    @RequiresApi(11)
    public void animateY(int i, Easing.EasingFunction easingFunction) {
        ObjectAnimator b = b(i, easingFunction);
        b.addUpdateListener(this.f7929a);
        b.start();
    }

    @RequiresApi(11)
    public ChartAnimator(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.f7929a = animatorUpdateListener;
    }

    @Deprecated
    public void animateX(int i, Easing.EasingOption easingOption) {
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseX", 0.0f, 1.0f);
        ofFloat.setInterpolator(Easing.getEasingFunctionFromOption(easingOption));
        ofFloat.setDuration(i);
        ofFloat.addUpdateListener(this.f7929a);
        ofFloat.start();
    }

    @Deprecated
    public void animateY(int i, Easing.EasingOption easingOption) {
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseY", 0.0f, 1.0f);
        ofFloat.setInterpolator(Easing.getEasingFunctionFromOption(easingOption));
        ofFloat.setDuration(i);
        ofFloat.addUpdateListener(this.f7929a);
        ofFloat.start();
    }

    @RequiresApi(11)
    public void animateXY(int i, int i2, Easing.EasingFunction easingFunction, Easing.EasingFunction easingFunction2) {
        ObjectAnimator a2 = a(i, easingFunction);
        ObjectAnimator b = b(i2, easingFunction2);
        if (i > i2) {
            a2.addUpdateListener(this.f7929a);
        } else {
            b.addUpdateListener(this.f7929a);
        }
        a2.start();
        b.start();
    }

    @Deprecated
    public void animateXY(int i, int i2, Easing.EasingOption easingOption, Easing.EasingOption easingOption2) {
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "phaseY", 0.0f, 1.0f);
        ofFloat.setInterpolator(Easing.getEasingFunctionFromOption(easingOption2));
        ofFloat.setDuration(i2);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, "phaseX", 0.0f, 1.0f);
        ofFloat2.setInterpolator(Easing.getEasingFunctionFromOption(easingOption));
        ofFloat2.setDuration(i);
        if (i > i2) {
            ofFloat2.addUpdateListener(this.f7929a);
        } else {
            ofFloat.addUpdateListener(this.f7929a);
        }
        ofFloat2.start();
        ofFloat.start();
    }
}
