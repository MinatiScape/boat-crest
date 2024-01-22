package com.google.android.material.timepicker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.annotation.Dimension;
import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public class ClockHandView extends View {
    public ValueAnimator h;
    public boolean i;
    public float j;
    public float k;
    public boolean l;
    public int m;
    public final List<OnRotateListener> n;
    public final int o;
    public final float p;
    public final Paint q;
    public final RectF r;
    @Px
    public final int s;
    public float t;
    public boolean u;
    public OnActionUpListener v;
    public double w;
    public int x;

    /* loaded from: classes10.dex */
    public interface OnActionUpListener {
        void onActionUp(@FloatRange(from = 0.0d, to = 360.0d) float f, boolean z);
    }

    /* loaded from: classes10.dex */
    public interface OnRotateListener {
        void onRotate(@FloatRange(from = 0.0d, to = 360.0d) float f, boolean z);
    }

    /* loaded from: classes10.dex */
    public class a implements ValueAnimator.AnimatorUpdateListener {
        public a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            ClockHandView.this.n(((Float) valueAnimator.getAnimatedValue()).floatValue(), true);
        }
    }

    /* loaded from: classes10.dex */
    public class b extends AnimatorListenerAdapter {
        public b(ClockHandView clockHandView) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            animator.end();
        }
    }

    public ClockHandView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialClockStyle);
    }

    public void b(OnRotateListener onRotateListener) {
        this.n.add(onRotateListener);
    }

    public final void c(Canvas canvas) {
        int width;
        int height = getHeight() / 2;
        float width2 = getWidth() / 2;
        float f = height;
        this.q.setStrokeWidth(0.0f);
        canvas.drawCircle((this.x * ((float) Math.cos(this.w))) + width2, (this.x * ((float) Math.sin(this.w))) + f, this.o, this.q);
        double sin = Math.sin(this.w);
        double cos = Math.cos(this.w);
        this.q.setStrokeWidth(this.s);
        canvas.drawLine(width2, f, width + ((int) (cos * r6)), height + ((int) (r6 * sin)), this.q);
        canvas.drawCircle(width2, f, this.p, this.q);
    }

    public RectF d() {
        return this.r;
    }

    public final int e(float f, float f2) {
        int degrees = ((int) Math.toDegrees(Math.atan2(f2 - (getHeight() / 2), f - (getWidth() / 2)))) + 90;
        return degrees < 0 ? degrees + 360 : degrees;
    }

    @FloatRange(from = 0.0d, to = 360.0d)
    public float f() {
        return this.t;
    }

    public int g() {
        return this.o;
    }

    public final Pair<Float, Float> h(float f) {
        float f2 = f();
        if (Math.abs(f2 - f) > 180.0f) {
            if (f2 > 180.0f && f < 180.0f) {
                f += 360.0f;
            }
            if (f2 < 180.0f && f > 180.0f) {
                f2 += 360.0f;
            }
        }
        return new Pair<>(Float.valueOf(f2), Float.valueOf(f));
    }

    public final boolean i(float f, float f2, boolean z, boolean z2, boolean z3) {
        float e = e(f, f2);
        boolean z4 = false;
        boolean z5 = f() != e;
        if (z2 && z5) {
            return true;
        }
        if (z5 || z) {
            if (z3 && this.i) {
                z4 = true;
            }
            m(e, z4);
            return true;
        }
        return false;
    }

    public void j(boolean z) {
        this.i = z;
    }

    public void k(@Dimension int i) {
        this.x = i;
        invalidate();
    }

    public void l(@FloatRange(from = 0.0d, to = 360.0d) float f) {
        m(f, false);
    }

    public void m(@FloatRange(from = 0.0d, to = 360.0d) float f, boolean z) {
        ValueAnimator valueAnimator = this.h;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if (!z) {
            n(f, false);
            return;
        }
        Pair<Float, Float> h = h(f);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(((Float) h.first).floatValue(), ((Float) h.second).floatValue());
        this.h = ofFloat;
        ofFloat.setDuration(200L);
        this.h.addUpdateListener(new a());
        this.h.addListener(new b(this));
        this.h.start();
    }

    public final void n(@FloatRange(from = 0.0d, to = 360.0d) float f, boolean z) {
        float f2 = f % 360.0f;
        this.t = f2;
        this.w = Math.toRadians(f2 - 90.0f);
        float width = (getWidth() / 2) + (this.x * ((float) Math.cos(this.w)));
        float height = (getHeight() / 2) + (this.x * ((float) Math.sin(this.w)));
        RectF rectF = this.r;
        int i = this.o;
        rectF.set(width - i, height - i, width + i, height + i);
        for (OnRotateListener onRotateListener : this.n) {
            onRotateListener.onRotate(f2, z);
        }
        invalidate();
    }

    public void o(OnActionUpListener onActionUpListener) {
        this.v = onActionUpListener;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        c(canvas);
    }

    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        l(f());
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z2;
        boolean z3;
        OnActionUpListener onActionUpListener;
        int actionMasked = motionEvent.getActionMasked();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (actionMasked == 0) {
            this.j = x;
            this.k = y;
            this.l = true;
            this.u = false;
            z = false;
            z2 = false;
            z3 = true;
        } else if (actionMasked == 1 || actionMasked == 2) {
            int i = (int) (x - this.j);
            int i2 = (int) (y - this.k);
            this.l = (i * i) + (i2 * i2) > this.m;
            boolean z4 = this.u;
            z = actionMasked == 1;
            z3 = false;
            z2 = z4;
        } else {
            z = false;
            z2 = false;
            z3 = false;
        }
        boolean i3 = i(x, y, z2, z3, z) | this.u;
        this.u = i3;
        if (i3 && z && (onActionUpListener = this.v) != null) {
            onActionUpListener.onActionUp(e(x, y), this.l);
        }
        return true;
    }

    public ClockHandView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.n = new ArrayList();
        Paint paint = new Paint();
        this.q = paint;
        this.r = new RectF();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ClockHandView, i, R.style.Widget_MaterialComponents_TimePicker_Clock);
        this.x = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ClockHandView_materialCircleRadius, 0);
        this.o = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ClockHandView_selectorSize, 0);
        Resources resources = getResources();
        this.s = resources.getDimensionPixelSize(R.dimen.material_clock_hand_stroke_width);
        this.p = resources.getDimensionPixelSize(R.dimen.material_clock_hand_center_dot_radius);
        int color = obtainStyledAttributes.getColor(R.styleable.ClockHandView_clockHandColor, 0);
        paint.setAntiAlias(true);
        paint.setColor(color);
        l(0.0f);
        this.m = ViewConfiguration.get(context).getScaledTouchSlop();
        ViewCompat.setImportantForAccessibility(this, 2);
        obtainStyledAttributes.recycle();
    }
}
