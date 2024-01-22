package com.coveiot.android.theme;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.ColorInt;
import androidx.core.view.ViewCompat;
import com.google.android.gms.common.ConnectionResult;
/* loaded from: classes7.dex */
public class CircularProgressBar extends View {
    public State h;
    public float i;
    public float j;
    public int k;
    public int l;
    public int m;
    public int n;
    public float o;
    public int p;
    public RectF q;
    public Paint r;
    public Paint s;

    /* loaded from: classes7.dex */
    public enum State {
        CLOCKWISE,
        COUNTERCLOCKWISE
    }

    public CircularProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = State.CLOCKWISE;
        this.i = 0.0f;
        a(context, attributeSet);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.CircularProgressBar, 0, 0);
        if (obtainStyledAttributes.getInt(R.styleable.CircularProgressBar_cpbState, 0) == 0) {
            this.h = State.CLOCKWISE;
        } else {
            this.h = State.COUNTERCLOCKWISE;
        }
        this.i = obtainStyledAttributes.getFloat(R.styleable.CircularProgressBar_cpbProgressValue, this.i);
        this.j = obtainStyledAttributes.getDimension(R.styleable.CircularProgressBar_cpbProgressWidth, getResources().getDimension(R.dimen.progress_width));
        this.o = obtainStyledAttributes.getDimension(R.styleable.CircularProgressBar_cpbBackgroundWidth, getResources().getDimension(R.dimen.background_width));
        int i = obtainStyledAttributes.getInt(R.styleable.CircularProgressBar_cpbProgressColor, ViewCompat.MEASURED_STATE_MASK);
        this.k = i;
        this.l = obtainStyledAttributes.getInt(R.styleable.CircularProgressBar_cpbGradientStart, i);
        this.m = obtainStyledAttributes.getInt(R.styleable.CircularProgressBar_cpbGradientMiddle, this.k);
        this.n = obtainStyledAttributes.getInt(R.styleable.CircularProgressBar_cpbGradientEnd, this.k);
        this.p = obtainStyledAttributes.getInt(R.styleable.CircularProgressBar_cpbBackgroundColor, -7829368);
        obtainStyledAttributes.recycle();
        this.q = new RectF();
        Paint paint = new Paint(1);
        this.r = paint;
        paint.setColor(this.p);
        this.r.setStyle(Paint.Style.STROKE);
        this.r.setStrokeWidth(this.o);
        this.r.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f}, 0.0f));
        Paint paint2 = new Paint(1);
        this.s = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.s.setStrokeWidth(this.j);
        this.s.setStrokeCap(Paint.Cap.BUTT);
    }

    @ColorInt
    public int getBackgroundColor() {
        return this.p;
    }

    public float getBackgroundWidth() {
        return this.o;
    }

    @ColorInt
    public int getProgressColor() {
        return this.k;
    }

    public float getProgressValue() {
        return this.i;
    }

    public float getProgressWidth() {
        return this.j;
    }

    public State getState() {
        return this.h;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(this.q, this.r);
        if (this.h == State.CLOCKWISE) {
            canvas.drawArc(this.q, 270.0f, (this.i * 360.0f) / 100.0f, false, this.s);
            return;
        }
        canvas.drawArc(this.q, 270.0f, ((this.i * 360.0f) / 100.0f) - 360.0f, false, this.s);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int min = Math.min(View.getDefaultSize(getSuggestedMinimumWidth(), i), View.getDefaultSize(getSuggestedMinimumHeight(), i2));
        setMeasuredDimension(min, min);
        float f = this.j;
        float f2 = this.o;
        if (f <= f2) {
            f = f2;
        }
        float f3 = f / 2.0f;
        float f4 = 0.0f + f3;
        float f5 = min - f3;
        this.q.set(f4, f4, f5, f5);
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        float min = (Math.min(i, i2) - this.s.getStrokeWidth()) / 2.0f;
        float f = i / 2.0f;
        float f2 = i2 / 2.0f;
        this.q.set(f - min, f2 - min, f + min, min + f2);
        SweepGradient sweepGradient = new SweepGradient(f, f2, new int[]{this.l, this.m, this.n}, new float[]{0.0f, 0.5f, 1.0f});
        Matrix matrix = new Matrix();
        matrix.setRotate(270.0f, f, f2);
        sweepGradient.setLocalMatrix(matrix);
        this.s.setShader(sweepGradient);
    }

    @Override // android.view.View
    public void setBackgroundColor(@ColorInt int i) {
        this.p = i;
        this.r.setColor(i);
        invalidate();
        requestLayout();
    }

    public void setBackgroundWidth(float f) {
        this.o = f;
        this.r.setStrokeWidth(f);
        requestLayout();
        invalidate();
    }

    public void setProgressColor(@ColorInt int i) {
        this.k = i;
        this.s.setColor(i);
        invalidate();
        requestLayout();
    }

    public void setProgressValue(float f) {
        if (f > 100.0f) {
            f = 100.0f;
        }
        this.i = f;
        invalidate();
    }

    public void setProgressValueWithAnimation(float f) {
        setProgressValueWithAnimation(this.h, f, ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
    }

    public void setProgressWidth(float f) {
        this.j = f;
        this.s.setStrokeWidth(f);
        requestLayout();
        invalidate();
    }

    public void setState(State state) {
        this.h = state;
        requestLayout();
        invalidate();
    }

    public void setProgressValueWithAnimation(float f, int i) {
        setProgressValueWithAnimation(this.h, f, i);
    }

    public void setProgressValueWithAnimation(State state, float f) {
        setProgressValueWithAnimation(state, f, ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
    }

    public void setProgressValueWithAnimation(State state, float f, int i) {
        this.h = state;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "progressValue", f);
        ofFloat.setDuration(i);
        ofFloat.setInterpolator(new DecelerateInterpolator());
        ofFloat.start();
    }
}
