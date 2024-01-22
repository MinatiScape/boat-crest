package com.coveiot.android.theme.compundview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
/* loaded from: classes7.dex */
public class DottedCircleProgressBarCustom extends View {
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;

    /* loaded from: classes7.dex */
    public class a implements Animation.AnimationListener {
        public a() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
            DottedCircleProgressBarCustom.c(DottedCircleProgressBarCustom.this);
            if (DottedCircleProgressBarCustom.this.j > DottedCircleProgressBarCustom.this.k) {
                DottedCircleProgressBarCustom.this.j = 1;
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* loaded from: classes7.dex */
    public class b extends Animation {
        public b() {
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            super.applyTransformation(f, transformation);
            DottedCircleProgressBarCustom.this.invalidate();
        }

        public /* synthetic */ b(DottedCircleProgressBarCustom dottedCircleProgressBarCustom, a aVar) {
            this();
        }
    }

    public DottedCircleProgressBarCustom(Context context) {
        super(context);
        this.h = 10;
        this.i = 13;
        this.j = 1;
        this.k = 8;
        this.l = 50;
    }

    public static /* synthetic */ int c(DottedCircleProgressBarCustom dottedCircleProgressBarCustom) {
        int i = dottedCircleProgressBarCustom.j;
        dottedCircleProgressBarCustom.j = i + 1;
        return i;
    }

    public final void e(Canvas canvas, Paint paint) {
        for (int i = 1; i <= this.k; i++) {
            if (i == this.j) {
                double d = 45 * i * 0.017453292519943295d;
                canvas.drawCircle((float) (this.l * Math.cos(d)), (float) (this.l * Math.sin(d)), this.i, paint);
            } else {
                double d2 = 45 * i * 0.017453292519943295d;
                canvas.drawCircle((float) (this.l * Math.cos(d2)), (float) (this.l * Math.sin(d2)), this.h, paint);
            }
        }
    }

    public final void f() {
        b bVar = new b(this, null);
        bVar.setDuration(100L);
        bVar.setRepeatCount(-1);
        bVar.setInterpolator(new LinearInterpolator());
        bVar.setAnimationListener(new a());
        startAnimation(bVar);
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        f();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#ffffff"));
        e(canvas, paint);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int i3 = this.h;
        setMeasuredDimension((i3 * 3) + 100, (i3 * 3) + 100);
    }

    public DottedCircleProgressBarCustom(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = 10;
        this.i = 13;
        this.j = 1;
        this.k = 8;
        this.l = 50;
    }

    public DottedCircleProgressBarCustom(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = 10;
        this.i = 13;
        this.j = 1;
        this.k = 8;
        this.l = 50;
    }
}
