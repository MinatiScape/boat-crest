package com.coveiot.android.theme.compundview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public class FitnessTracker extends View {
    public static final int CALORIES = 3;
    public static final int DISTANCE = 2;
    public static final int STEP = 1;
    public ValueAnimator A;
    public Drawable B;
    public ProgressChangeListener C;
    public float D;
    public float E;
    public float F;
    public float G;
    public int H;
    public int I;
    public float J;
    public RectF K;
    public RectF L;
    public RectF M;
    public Paint N;
    public Paint O;
    public Paint P;
    public Paint Q;
    public Paint R;
    public Paint S;
    public float T;
    public float U;
    public float V;
    public float h;
    public float i;
    public int j;
    public int k;
    public int l;
    public float m;
    public boolean n;
    public Drawable o;
    public ValueAnimator p;
    public float q;
    public float r;
    public int s;
    public int t;
    public ValueAnimator u;
    public Drawable v;
    public float w;
    public float x;
    public int y;
    public int z;

    /* loaded from: classes7.dex */
    public interface IndeterminateModeChangeListener {
        void onModeChange(boolean z);
    }

    /* loaded from: classes7.dex */
    public interface ProgressChangeListener {
        void onProgressChanged(float f, int i);
    }

    /* loaded from: classes7.dex */
    public class a implements ValueAnimator.AnimatorUpdateListener {
        public final /* synthetic */ int h;

        public a(int i) {
            this.h = i;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            FitnessTracker.this.f(((Float) valueAnimator.getAnimatedValue()).floatValue(), true, this.h);
        }
    }

    /* loaded from: classes7.dex */
    public class b implements ValueAnimator.AnimatorUpdateListener {
        public final /* synthetic */ int h;

        public b(int i) {
            this.h = i;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            FitnessTracker.this.f(((Float) valueAnimator.getAnimatedValue()).floatValue(), true, this.h);
        }
    }

    /* loaded from: classes7.dex */
    public class c implements ValueAnimator.AnimatorUpdateListener {
        public final /* synthetic */ int h;

        public c(int i) {
            this.h = i;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            FitnessTracker.this.f(((Float) valueAnimator.getAnimatedValue()).floatValue(), true, this.h);
        }
    }

    public FitnessTracker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = 0.0f;
        this.i = 100.0f;
        this.j = ViewCompat.MEASURED_STATE_MASK;
        this.k = -7829368;
        this.l = ViewCompat.MEASURED_STATE_MASK;
        this.m = 270.0f;
        this.n = true;
        this.q = 0.0f;
        this.r = 100.0f;
        this.s = ViewCompat.MEASURED_STATE_MASK;
        this.t = -7829368;
        this.w = 0.0f;
        this.x = 100.0f;
        this.y = ViewCompat.MEASURED_STATE_MASK;
        this.z = -7829368;
        this.D = getResources().getDimension(R.dimen.default_stroke_width);
        this.E = getResources().getDimension(R.dimen.default_background_stroke_width);
        this.F = getResources().getDimension(R.dimen.default_margin);
        this.G = getResources().getDimension(R.dimen.default_text_size);
        d(context, attributeSet);
    }

    public final void b(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(this.G);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(this.l);
        float measureText = paint.measureText("Steps");
        canvas.drawText("STEPS", this.H, this.I - (this.G / 3.0f), paint);
        int i = this.H;
        float f = measureText / 2.0f;
        int i2 = this.I;
        canvas.drawLine(i - f, i2, i + f, i2, paint);
        canvas.drawText("12000", this.H, this.I + this.G, paint);
    }

    public final Bitmap c(Context context, Drawable drawable) {
        Bitmap createBitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() > 0 && drawable.getIntrinsicHeight() > 0) {
            createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        } else {
            createBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public final void d(Context context, AttributeSet attributeSet) {
        this.K = new RectF();
        this.L = new RectF();
        this.M = new RectF();
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.FitnessTracker, 0, 0);
        try {
            this.h = obtainStyledAttributes.getFloat(R.styleable.FitnessTracker_ft_progress_steps, this.h);
            this.i = obtainStyledAttributes.getFloat(R.styleable.FitnessTracker_ft_progress_max_steps, this.i);
            this.D = obtainStyledAttributes.getDimension(R.styleable.FitnessTracker_ft_progressbar_width, this.D);
            this.E = obtainStyledAttributes.getDimension(R.styleable.FitnessTracker_ft_background_progressbar_width, this.E);
            this.j = obtainStyledAttributes.getInt(R.styleable.FitnessTracker_ft_progressbar_color_steps, this.j);
            this.k = obtainStyledAttributes.getInt(R.styleable.FitnessTracker_ft_background_progressbar_color_steps, this.k);
            this.l = obtainStyledAttributes.getColor(R.styleable.FitnessTracker_ft_text_color, this.l);
            this.s = obtainStyledAttributes.getInt(R.styleable.FitnessTracker_ft_progressbar_color_dist, this.j);
            this.t = obtainStyledAttributes.getInt(R.styleable.FitnessTracker_ft_background_progressbar_color_dist, this.k);
            this.q = obtainStyledAttributes.getFloat(R.styleable.FitnessTracker_ft_progress_dist, this.q);
            this.r = obtainStyledAttributes.getFloat(R.styleable.FitnessTracker_ft_progress_max_dist, this.r);
            this.y = obtainStyledAttributes.getInt(R.styleable.FitnessTracker_ft_progressbar_color_calories, this.y);
            this.w = obtainStyledAttributes.getFloat(R.styleable.FitnessTracker_ft_progress_calories, this.w);
            this.x = obtainStyledAttributes.getFloat(R.styleable.FitnessTracker_ft_progress_max_calories, this.x);
            this.z = obtainStyledAttributes.getInt(R.styleable.FitnessTracker_ft_background_progressbar_color_calories, this.z);
            this.o = obtainStyledAttributes.getDrawable(R.styleable.FitnessTracker_ft_progress_drawable_steps);
            this.v = obtainStyledAttributes.getDrawable(R.styleable.FitnessTracker_ft_progress_drawable_calories);
            this.B = obtainStyledAttributes.getDrawable(R.styleable.FitnessTracker_ft_progress_drawable_dist);
            this.F = obtainStyledAttributes.getDimension(R.styleable.FitnessTracker_ft_margin, this.F);
            this.G = obtainStyledAttributes.getDimension(R.styleable.FitnessTracker_ft_text_size, this.G);
            obtainStyledAttributes.recycle();
            Paint paint = new Paint(1);
            this.R = paint;
            paint.setColor(this.z);
            this.R.setStyle(Paint.Style.STROKE);
            this.R.setStrokeWidth(this.E);
            Paint paint2 = new Paint(1);
            this.S = paint2;
            paint2.setColor(this.y);
            this.S.setStyle(Paint.Style.STROKE);
            this.S.setStrokeWidth(this.D);
            this.S.setStrokeCap(Paint.Cap.ROUND);
            Paint paint3 = new Paint(1);
            this.P = paint3;
            paint3.setColor(this.t);
            this.P.setStyle(Paint.Style.STROKE);
            this.P.setStrokeWidth(this.E);
            Paint paint4 = new Paint(1);
            this.Q = paint4;
            paint4.setColor(this.s);
            this.Q.setStyle(Paint.Style.STROKE);
            this.Q.setStrokeWidth(this.D);
            this.Q.setStrokeCap(Paint.Cap.ROUND);
            Paint paint5 = new Paint(1);
            this.N = paint5;
            paint5.setColor(this.k);
            this.N.setStyle(Paint.Style.STROKE);
            this.N.setStrokeWidth(this.E);
            Paint paint6 = new Paint(1);
            this.O = paint6;
            paint6.setColor(this.j);
            this.O.setStyle(Paint.Style.STROKE);
            this.O.setStrokeWidth(this.D);
            this.O.setStrokeCap(Paint.Cap.ROUND);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public final void e() {
        requestLayout();
        invalidate();
    }

    public final void f(float f, boolean z, int i) {
        ValueAnimator valueAnimator;
        ValueAnimator valueAnimator2;
        ValueAnimator valueAnimator3;
        if (i == 1) {
            if (!z && (valueAnimator3 = this.p) != null) {
                valueAnimator3.cancel();
            }
            float f2 = this.i;
            if (f <= f2) {
                f2 = f;
            }
            this.h = f2;
            ProgressChangeListener progressChangeListener = this.C;
            if (progressChangeListener != null) {
                progressChangeListener.onProgressChanged(f, i);
            }
        } else if (i == 2) {
            if (!z && (valueAnimator2 = this.u) != null) {
                valueAnimator2.cancel();
            }
            float f3 = this.r;
            if (f <= f3) {
                f3 = f;
            }
            this.q = f3;
            ProgressChangeListener progressChangeListener2 = this.C;
            if (progressChangeListener2 != null) {
                progressChangeListener2.onProgressChanged(f, i);
            }
        } else if (i == 3) {
            if (!z && (valueAnimator = this.A) != null) {
                valueAnimator.cancel();
            }
            float f4 = this.x;
            if (f <= f4) {
                f4 = f;
            }
            this.w = f4;
            ProgressChangeListener progressChangeListener3 = this.C;
            if (progressChangeListener3 != null) {
                progressChangeListener3.onProgressChanged(f, i);
            }
        }
        invalidate();
    }

    public int getBackgroundColorStep() {
        return this.k;
    }

    public float getBackgroundProgressBarWidth() {
        return this.E;
    }

    public int getColor() {
        return this.j;
    }

    public float getProgress(int i) {
        if (i == 1) {
            return this.h;
        }
        return i == 2 ? this.q : this.w;
    }

    public float getProgressBarWidth() {
        return this.D;
    }

    public float getProgressMax() {
        return this.i;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ValueAnimator valueAnimator = this.p;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimator2 = this.u;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
        ValueAnimator valueAnimator3 = this.A;
        if (valueAnimator3 != null) {
            valueAnimator3.cancel();
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Bitmap c2;
        Bitmap c3;
        Bitmap c4;
        super.onDraw(canvas);
        canvas.drawOval(this.K, this.R);
        float f = ((this.n ? 360 : -360) * ((this.w * 100.0f) / this.x)) / 100.0f;
        canvas.drawArc(this.K, this.m, f, false, this.S);
        double d = f + 270.0f;
        float cos = (float) ((Math.cos(Math.toRadians(d)) * this.T) + this.H);
        float sin = (float) ((Math.sin(Math.toRadians(d)) * this.T) + this.H);
        canvas.drawCircle(cos, sin, 10.0f, this.S);
        canvas.drawBitmap(c(getContext(), this.v), cos - (c2.getWidth() / 2), sin - (c2.getHeight() / 2), (Paint) null);
        float f2 = ((this.n ? 360 : -360) * ((this.q * 100.0f) / this.r)) / 100.0f;
        canvas.drawOval(this.L, this.P);
        canvas.drawArc(this.L, this.m, f2, false, this.Q);
        double d2 = f2 + 270.0f;
        float cos2 = (float) ((Math.cos(Math.toRadians(d2)) * this.U) + this.H);
        float sin2 = (float) ((Math.sin(Math.toRadians(d2)) * this.U) + this.H);
        canvas.drawCircle(cos2, sin2, 10.0f, this.Q);
        canvas.drawBitmap(c(getContext(), this.B), cos2 - (c3.getWidth() / 2), sin2 - (c3.getHeight() / 2), (Paint) null);
        float f3 = ((this.n ? 360 : -360) * ((this.h * 100.0f) / this.i)) / 100.0f;
        canvas.drawOval(this.M, this.N);
        canvas.drawArc(this.M, this.m, f3, false, this.O);
        double d3 = f3 + 270.0f;
        float cos3 = (float) ((Math.cos(Math.toRadians(d3)) * this.V) + this.H);
        float sin3 = (float) ((Math.sin(Math.toRadians(d3)) * this.V) + this.H);
        canvas.drawCircle(cos3, sin3, 10.0f, this.O);
        canvas.drawBitmap(c(getContext(), this.o), cos3 - (c4.getWidth() / 2), sin3 - (c4.getHeight() / 2), (Paint) null);
        b(canvas);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int defaultSize = View.getDefaultSize(getSuggestedMinimumHeight(), i2);
        int defaultSize2 = View.getDefaultSize(getSuggestedMinimumWidth(), i);
        int min = Math.min(defaultSize2, defaultSize);
        setMeasuredDimension(min, min);
        float f = this.D;
        float f2 = this.E;
        if (f <= f2) {
            f = f2;
        }
        this.J = f;
        float f3 = min;
        this.K.set(f + 0.0f, 0.0f + f, f3 - f, f3 - f);
        RectF rectF = this.L;
        float f4 = this.F;
        float f5 = this.J;
        rectF.set((f5 * 2.0f) + f4, (f5 * 2.0f) + f4, (f3 - (f5 * 2.0f)) - f4, (f3 - (f5 * 2.0f)) - f4);
        RectF rectF2 = this.M;
        float f6 = this.F;
        float f7 = this.J;
        rectF2.set((f6 * 2.0f) + (f7 * 3.0f), (f6 * 2.0f) + (f7 * 3.0f), (f3 - (f7 * 3.0f)) - (f6 * 2.0f), (f3 - (f7 * 3.0f)) - (f6 * 2.0f));
        float f8 = defaultSize2 / 2;
        float f9 = this.J;
        this.T = f8 - f9;
        float f10 = this.F;
        this.U = f8 - ((f9 * 2.0f) + f10);
        this.V = f8 - ((f9 * 3.0f) + (f10 * 2.0f));
        int i3 = min / 2;
        this.H = i3;
        this.I = (int) (i3 - ((this.O.descent() + this.O.ascent()) / 2.0f));
    }

    public void setBackgroundColorStep(int i) {
        this.k = i;
        this.N.setColor(i);
        e();
    }

    public void setBackgroundProgressBarWidth(float f) {
        this.E = f;
        this.N.setStrokeWidth(f);
        e();
    }

    public void setColor(int i) {
        this.j = i;
        this.O.setColor(i);
        e();
    }

    public void setOnIndeterminateModeChangeListener(IndeterminateModeChangeListener indeterminateModeChangeListener) {
    }

    public void setOnProgressChangedListener(ProgressChangeListener progressChangeListener) {
        this.C = progressChangeListener;
    }

    public void setProgress(float f, int i) {
        f(f, false, i);
    }

    public void setProgressBarWidth(float f) {
        this.D = f;
        this.O.setStrokeWidth(f);
        e();
    }

    public void setProgressMax(float f) {
        if (f < 0.0f) {
            f = 100.0f;
        }
        this.i = f;
        e();
    }

    public void setProgressWithAnimation(float f, int i) {
        setProgressWithAnimation(f, 1000, i);
    }

    public void setProgressWithAnimation(float f, int i, int i2) {
        if (i2 == 1) {
            ValueAnimator valueAnimator = this.p;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(getProgress(i2), f);
            this.p = ofFloat;
            ofFloat.setDuration(i);
            this.p.addUpdateListener(new a(i2));
            this.p.start();
        } else if (i2 == 2) {
            ValueAnimator valueAnimator2 = this.u;
            if (valueAnimator2 != null) {
                valueAnimator2.cancel();
            }
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(getProgress(i2), f);
            this.u = ofFloat2;
            ofFloat2.setDuration(i);
            this.u.addUpdateListener(new b(i2));
            this.u.start();
        } else if (i2 != 3) {
        } else {
            ValueAnimator valueAnimator3 = this.A;
            if (valueAnimator3 != null) {
                valueAnimator3.cancel();
            }
            ValueAnimator ofFloat3 = ValueAnimator.ofFloat(getProgress(i2), f);
            this.A = ofFloat3;
            ofFloat3.setDuration(i);
            this.A.addUpdateListener(new c(i2));
            this.A.start();
        }
    }
}
