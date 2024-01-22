package com.coveiot.android.theme.compundview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public class AnimatedDotsView extends LinearLayout {
    public static final String h = AnimatedDotsView.class.getSimpleName();
    public static final int i = Color.parseColor("#9c9c9c");
    public static final int j = Color.parseColor("#9c9c9c");
    public static final Interpolator k = new AccelerateInterpolator(1.0f);
    public AnimatorSet animatorSet;
    public int blinkingColor;
    public int dotCount;
    public int dotRadius;
    public CircleView[] dotViews;
    public int neutralColor;
    public boolean stop;

    /* loaded from: classes7.dex */
    public class a implements Animator.AnimatorListener {
        public final /* synthetic */ AnimatorSet h;

        public a(AnimatorSet animatorSet) {
            this.h = animatorSet;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (AnimatedDotsView.this.stop) {
                return;
            }
            this.h.start();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* loaded from: classes7.dex */
    public class b implements Animator.AnimatorListener {
        public final /* synthetic */ CircleView h;

        public b(AnimatedDotsView animatedDotsView, CircleView circleView) {
            this.h = circleView;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.h.setFilled(false);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    public AnimatedDotsView(Context context) {
        this(context, null);
    }

    public final void a() {
        this.dotViews = new CircleView[this.dotCount];
        Context context = getContext();
        for (int i2 = 0; i2 < this.dotCount; i2++) {
            Log.e(h, "add view: " + i2);
            this.dotViews[i2] = new CircleView(context);
            this.dotViews[i2].setRadius(this.dotRadius);
            this.dotViews[i2].setColor(this.neutralColor, false);
            this.dotViews[i2].setFilled(false);
            addView(this.dotViews[i2], new LinearLayout.LayoutParams(-2, -2));
        }
        this.animatorSet = b();
    }

    public final AnimatorSet b() {
        ObjectAnimator[] objectAnimatorArr = new ObjectAnimator[this.dotCount];
        long j2 = 200;
        for (int i2 = 0; i2 < this.dotCount; i2++) {
            objectAnimatorArr[i2] = createAnimator(this.dotViews[i2], j2);
            j2 += 200;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(objectAnimatorArr);
        animatorSet.setInterpolator(new AccelerateInterpolator(1.0f));
        animatorSet.addListener(new a(animatorSet));
        return animatorSet;
    }

    public ObjectAnimator createAnimator(CircleView circleView, long j2) {
        ObjectAnimator ofObject = ObjectAnimator.ofObject(circleView, "filled", new ArgbEvaluator(), 0, 1);
        ofObject.setDuration(j2);
        ofObject.setRepeatCount(0);
        ofObject.setInterpolator(k);
        ofObject.addListener(new b(this, circleView));
        return ofObject;
    }

    public int getBlinkingColor() {
        return this.blinkingColor;
    }

    public int getDotCount() {
        return this.dotCount;
    }

    public int getDotRadius() {
        return this.dotRadius;
    }

    public int getNeutralColor() {
        return this.neutralColor;
    }

    public boolean isStop() {
        return this.stop;
    }

    public void startAnimation() {
        this.stop = false;
        this.animatorSet.start();
    }

    public void stopAnimation() {
        this.stop = true;
        this.animatorSet.end();
    }

    public AnimatedDotsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public AnimatedDotsView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.stop = false;
        int i3 = j;
        this.blinkingColor = i3;
        int i4 = i;
        this.neutralColor = i4;
        this.dotRadius = 15;
        LinearLayout.inflate(context, R.layout.v_animated_dots, this);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AnimatedDotsView);
            try {
                this.dotRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AnimatedDotsView_adv___dotRadius, 15);
                this.dotCount = obtainStyledAttributes.getInt(R.styleable.AnimatedDotsView_adv___dotCount, 5);
                this.blinkingColor = obtainStyledAttributes.getColor(R.styleable.AnimatedDotsView_adv___dotBlinkingColor, i3);
                this.neutralColor = obtainStyledAttributes.getColor(R.styleable.AnimatedDotsView_adv___dotNeutralColor, i4);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        setOrientation(0);
        int i5 = this.dotCount;
        if (i5 >= 1 && i5 <= 10) {
            a();
            return;
        }
        throw new IllegalArgumentException("The number of dot should be between [1, 10]");
    }
}
