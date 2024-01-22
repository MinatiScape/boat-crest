package com.skyfishjy.library;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes12.dex */
public class RippleBackground extends RelativeLayout {
    public int h;
    public float i;
    public float j;
    public int k;
    public int l;
    public int m;
    public float n;
    public int o;
    public Paint p;
    public boolean q;
    public AnimatorSet r;
    public ArrayList<Animator> s;
    public RelativeLayout.LayoutParams t;
    public ArrayList<a> u;

    /* loaded from: classes12.dex */
    public class a extends View {
        public a(Context context) {
            super(context);
            setVisibility(4);
        }

        @Override // android.view.View
        public void onDraw(Canvas canvas) {
            float min = Math.min(getWidth(), getHeight()) / 2;
            canvas.drawCircle(min, min, min - RippleBackground.this.i, RippleBackground.this.p);
        }
    }

    public RippleBackground(Context context) {
        super(context);
        this.q = false;
        this.u = new ArrayList<>();
    }

    public final void c(Context context, AttributeSet attributeSet) {
        if (isInEditMode()) {
            return;
        }
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RippleBackground);
            this.h = obtainStyledAttributes.getColor(R.styleable.RippleBackground_rb_color, getResources().getColor(R.color.rippelColor));
            this.i = obtainStyledAttributes.getDimension(R.styleable.RippleBackground_rb_strokeWidth, getResources().getDimension(R.dimen.rippleStrokeWidth));
            this.j = obtainStyledAttributes.getDimension(R.styleable.RippleBackground_rb_radius, getResources().getDimension(R.dimen.rippleRadius));
            this.k = obtainStyledAttributes.getInt(R.styleable.RippleBackground_rb_duration, 3000);
            this.l = obtainStyledAttributes.getInt(R.styleable.RippleBackground_rb_rippleAmount, 6);
            this.n = obtainStyledAttributes.getFloat(R.styleable.RippleBackground_rb_scale, 6.0f);
            this.o = obtainStyledAttributes.getInt(R.styleable.RippleBackground_rb_type, 0);
            obtainStyledAttributes.recycle();
            this.m = this.k / this.l;
            Paint paint = new Paint();
            this.p = paint;
            paint.setAntiAlias(true);
            if (this.o == 0) {
                this.i = 0.0f;
                this.p.setStyle(Paint.Style.FILL);
            } else {
                this.p.setStyle(Paint.Style.STROKE);
            }
            this.p.setColor(this.h);
            float f = this.j;
            float f2 = this.i;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) ((f + f2) * 2.0f), (int) ((f + f2) * 2.0f));
            this.t = layoutParams;
            layoutParams.addRule(13, -1);
            AnimatorSet animatorSet = new AnimatorSet();
            this.r = animatorSet;
            animatorSet.setDuration(this.k);
            this.r.setInterpolator(new AccelerateDecelerateInterpolator());
            this.s = new ArrayList<>();
            for (int i = 0; i < this.l; i++) {
                a aVar = new a(getContext());
                addView(aVar, this.t);
                this.u.add(aVar);
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(aVar, "ScaleX", 1.0f, this.n);
                ofFloat.setRepeatCount(-1);
                ofFloat.setRepeatMode(1);
                ofFloat.setStartDelay(this.m * i);
                this.s.add(ofFloat);
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(aVar, "ScaleY", 1.0f, this.n);
                ofFloat2.setRepeatCount(-1);
                ofFloat2.setRepeatMode(1);
                ofFloat2.setStartDelay(this.m * i);
                this.s.add(ofFloat2);
                ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(aVar, "Alpha", 1.0f, 0.0f);
                ofFloat3.setRepeatCount(-1);
                ofFloat3.setRepeatMode(1);
                ofFloat3.setStartDelay(this.m * i);
                this.s.add(ofFloat3);
            }
            this.r.playTogether(this.s);
            return;
        }
        throw new IllegalArgumentException("Attributes should be provided to this view,");
    }

    public boolean isRippleAnimationRunning() {
        return this.q;
    }

    public void startRippleAnimation() {
        if (isRippleAnimationRunning()) {
            return;
        }
        Iterator<a> it = this.u.iterator();
        while (it.hasNext()) {
            it.next().setVisibility(0);
        }
        this.r.start();
        this.q = true;
    }

    public void stopRippleAnimation() {
        if (isRippleAnimationRunning()) {
            this.r.end();
            this.q = false;
        }
    }

    public RippleBackground(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.q = false;
        this.u = new ArrayList<>();
        c(context, attributeSet);
    }

    public RippleBackground(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.q = false;
        this.u = new ArrayList<>();
        c(context, attributeSet);
    }
}
