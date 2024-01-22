package com.coveiot.android.theme.compundview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.theme.R;
import com.realsil.sdk.dfu.DfuException;
/* loaded from: classes7.dex */
public class ColorArcProgressBar extends View {
    public int A;
    public float B;
    public float C;
    public float D;
    public float E;
    public float F;
    public float G;
    public float H;
    public float I;
    public int J;
    public float K;
    public float L;
    public final int M;
    public String N;
    public String O;
    public String P;
    public String Q;
    public String R;
    public String S;
    public boolean T;
    public boolean U;
    public boolean V;
    public boolean W;
    public float a0;
    public int h;
    public float i;
    public float j;
    public Paint k;
    public Paint l;
    public Paint m;
    public Paint n;
    public Paint o;
    public Paint p;
    public RectF q;
    public ValueAnimator r;
    public PaintFlagsDrawFilter s;
    public SweepGradient t;
    public Matrix u;
    public float v;
    public float w;
    public float x;
    public float y;
    public int[] z;

    /* loaded from: classes7.dex */
    public class a implements ValueAnimator.AnimatorUpdateListener {
        public a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            ColorArcProgressBar.this.x = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            ColorArcProgressBar colorArcProgressBar = ColorArcProgressBar.this;
            colorArcProgressBar.D = colorArcProgressBar.x / ColorArcProgressBar.this.a0;
        }
    }

    public ColorArcProgressBar(Context context) {
        super(context, null);
        this.h = 150;
        this.v = 180.0f;
        this.w = 270.0f;
        this.x = 0.0f;
        this.z = new int[]{-16711936, InputDeviceCompat.SOURCE_ANY, SupportMenu.CATEGORY_MASK, SupportMenu.CATEGORY_MASK};
        this.B = 60.0f;
        this.C = 0.0f;
        this.D = 0.0f;
        this.E = e(2.0f);
        this.F = e(10.0f);
        this.G = e(14.0f);
        this.H = e(10.0f);
        this.I = e(13.0f);
        this.J = 1000;
        this.K = e(20.0f);
        this.L = e(5.0f);
        this.M = e(5.0f);
        this.N = Constants.WHITE;
        this.O = "#111111";
        this.P = "#111111";
        this.Q = "#33ffffff";
        g();
    }

    private int getScreenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private void setIsNeedDial(boolean z) {
        this.V = z;
    }

    private void setIsNeedTitle(boolean z) {
        this.T = z;
    }

    private void setIsNeedUnit(boolean z) {
        this.U = z;
    }

    private void setTitle(String str) {
        this.R = str;
    }

    public final int e(float f) {
        return (int) ((getContext().getResources().getDisplayMetrics().density * f) + ((f >= 0.0f ? 1 : -1) * 0.5f));
    }

    public final void f(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ColorArcProgressBar);
        int color = obtainStyledAttributes.getColor(R.styleable.ColorArcProgressBar_front_color1, -16711936);
        int color2 = obtainStyledAttributes.getColor(R.styleable.ColorArcProgressBar_front_color2, color);
        int color3 = obtainStyledAttributes.getColor(R.styleable.ColorArcProgressBar_front_color3, color);
        this.A = obtainStyledAttributes.getColor(R.styleable.ColorArcProgressBar_text_color, color);
        this.z = new int[]{color, color2, color3, color3};
        this.w = obtainStyledAttributes.getInteger(R.styleable.ColorArcProgressBar_total_engle, DfuException.ERROR_READ_DEVICE_INFO_ERROR);
        this.E = obtainStyledAttributes.getDimension(R.styleable.ColorArcProgressBar_back_width, e(2.0f));
        this.G = obtainStyledAttributes.getDimension(R.styleable.ColorArcProgressBar_text_size, e(2.0f));
        this.F = obtainStyledAttributes.getDimension(R.styleable.ColorArcProgressBar_front_width, e(10.0f));
        this.T = obtainStyledAttributes.getBoolean(R.styleable.ColorArcProgressBar_is_need_title, false);
        this.W = obtainStyledAttributes.getBoolean(R.styleable.ColorArcProgressBar_is_need_content, false);
        this.U = obtainStyledAttributes.getBoolean(R.styleable.ColorArcProgressBar_is_need_unit, false);
        this.V = obtainStyledAttributes.getBoolean(R.styleable.ColorArcProgressBar_is_need_dial, false);
        this.S = obtainStyledAttributes.getString(R.styleable.ColorArcProgressBar_string_unit);
        this.R = obtainStyledAttributes.getString(R.styleable.ColorArcProgressBar_string_title);
        this.D = obtainStyledAttributes.getFloat(R.styleable.ColorArcProgressBar_current_value, 0.0f);
        this.B = obtainStyledAttributes.getFloat(R.styleable.ColorArcProgressBar_max_value, 60.0f);
        this.C = obtainStyledAttributes.getFloat(R.styleable.ColorArcProgressBar_min_value, 0.0f);
        setCurrentValues(this.D);
        setMaxValues(this.B);
        obtainStyledAttributes.recycle();
    }

    public final void g() {
        this.h = e(90.0f);
        RectF rectF = new RectF();
        this.q = rectF;
        float f = this.K;
        float f2 = this.F;
        int i = this.M;
        rectF.top = (f2 / 2.0f) + f + i;
        rectF.left = (f2 / 2.0f) + f + i;
        int i2 = this.h;
        rectF.right = i2 + (f2 / 2.0f) + f + i;
        rectF.bottom = i2 + (f2 / 2.0f) + f + i;
        this.i = ((((f * 2.0f) + f2) + i2) + (i * 2)) / 2.0f;
        this.j = ((((f * 2.0f) + f2) + i2) + (i * 2)) / 2.0f;
        Paint paint = new Paint();
        this.o = paint;
        paint.setColor(Color.parseColor(this.O));
        Paint paint2 = new Paint();
        this.k = paint2;
        paint2.setAntiAlias(true);
        this.k.setStyle(Paint.Style.STROKE);
        this.k.setStrokeWidth(this.E);
        this.k.setColor(Color.parseColor(this.Q));
        this.k.setStrokeCap(Paint.Cap.ROUND);
        Paint paint3 = new Paint();
        this.l = paint3;
        paint3.setAntiAlias(true);
        this.l.setStyle(Paint.Style.STROKE);
        this.l.setStrokeCap(Paint.Cap.ROUND);
        this.l.setStrokeWidth(this.F);
        this.l.setColor(-16711936);
        Paint paint4 = new Paint();
        this.m = paint4;
        paint4.setTextSize(this.G);
        this.m.setColor(this.A);
        this.m.setTextAlign(Paint.Align.CENTER);
        Paint paint5 = new Paint();
        this.n = paint5;
        paint5.setTextSize(this.H);
        this.n.setColor(Color.parseColor(this.N));
        this.n.setTextAlign(Paint.Align.CENTER);
        Paint paint6 = new Paint();
        this.p = paint6;
        paint6.setTextSize(this.I);
        this.p.setColor(Color.parseColor(this.N));
        this.p.setTextAlign(Paint.Align.CENTER);
        this.s = new PaintFlagsDrawFilter(0, 3);
        this.t = new SweepGradient(this.i, this.j, this.z, (float[]) null);
        this.u = new Matrix();
    }

    public final void h(float f, float f2, int i) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, f2);
        this.r = ofFloat;
        ofFloat.setDuration(i);
        this.r.setTarget(Float.valueOf(this.x));
        this.r.addUpdateListener(new a());
        this.r.start();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        canvas.setDrawFilter(this.s);
        if (this.V) {
            for (int i = 0; i < 40; i++) {
                if (i > 15 && i < 25) {
                    canvas.rotate(9.0f, this.i, this.j);
                } else {
                    if (i % 5 == 0) {
                        this.o.setStrokeWidth(e(2.0f));
                        this.o.setColor(Color.parseColor(this.O));
                        float f = this.i;
                        float f2 = this.j;
                        int i2 = this.h;
                        float f3 = this.F;
                        int i3 = this.M;
                        canvas.drawLine(f, ((f2 - (i2 / 2)) - (f3 / 2.0f)) - i3, f, (((f2 - (i2 / 2)) - (f3 / 2.0f)) - i3) - this.K, this.o);
                    } else {
                        this.o.setStrokeWidth(e(1.4f));
                        this.o.setColor(Color.parseColor(this.P));
                        float f4 = this.i;
                        float f5 = this.j;
                        int i4 = this.h;
                        float f6 = this.F;
                        int i5 = this.M;
                        float f7 = this.K;
                        float f8 = this.L;
                        canvas.drawLine(f4, (((f5 - (i4 / 2)) - (f6 / 2.0f)) - i5) - ((f7 - f8) / 2.0f), f4, ((((f5 - (i4 / 2)) - (f6 / 2.0f)) - i5) - ((f7 - f8) / 2.0f)) - f8, this.o);
                    }
                    canvas.rotate(9.0f, this.i, this.j);
                }
            }
        }
        canvas.drawArc(this.q, this.v, this.w, false, this.k);
        this.u.setRotate(130.0f, this.i, this.j);
        this.t.setLocalMatrix(this.u);
        this.l.setShader(this.t);
        canvas.drawArc(this.q, this.v, this.x, false, this.l);
        if (this.W) {
            float f9 = this.D;
            if (f9 > 0.0f) {
                canvas.drawText(String.format("%.0f", Float.valueOf(f9)), this.i, this.j + (this.G / 3.0f), this.m);
            } else {
                canvas.drawText("--", this.i, this.j + (this.G / 3.0f), this.m);
            }
        }
        if (this.U) {
            canvas.drawText(this.S, this.i, this.j + 10.0f + ((this.G * 2.0f) / 2.0f), this.n);
        }
        if (this.T) {
            canvas.drawText(this.R, this.i, this.j - ((this.G * 2.0f) / 3.0f), this.p);
        }
        canvas.drawText(String.valueOf((int) this.C), this.q.left, this.j + 10.0f + ((this.G * 2.0f) / 2.0f), this.n);
        canvas.drawText(String.valueOf((int) this.B), this.q.right, this.j + 10.0f + ((this.G * 2.0f) / 2.0f), this.n);
        invalidate();
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        float f = this.K;
        float f2 = this.F;
        int i3 = this.h;
        int i4 = this.M;
        setMeasuredDimension((int) ((f * 2.0f) + f2 + i3 + (i4 * 2)), (int) ((f * 2.0f) + f2 + i3 + (i4 * 2)));
    }

    public void setBgArcWidth(int i) {
        this.E = i;
    }

    public void setCurrentValues(float f) {
        float f2 = this.B;
        if (f > f2) {
            f = f2;
        }
        if (f < 0.0f) {
            f = 0.0f;
        }
        this.D = f;
        float f3 = this.x;
        this.y = f3;
        h(f3, f * this.a0, this.J);
    }

    public void setDiameter(int i) {
        this.h = e(i);
    }

    public void setHintSize(int i) {
        this.H = i;
    }

    public void setMaxValues(float f) {
        this.B = f;
        this.a0 = this.w / f;
    }

    public void setProgressWidth(int i) {
        this.F = i;
    }

    public void setTextSize(int i) {
        this.G = i;
    }

    public void setUnit(String str) {
        this.S = str;
        invalidate();
    }

    public ColorArcProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.h = 150;
        this.v = 180.0f;
        this.w = 270.0f;
        this.x = 0.0f;
        this.z = new int[]{-16711936, InputDeviceCompat.SOURCE_ANY, SupportMenu.CATEGORY_MASK, SupportMenu.CATEGORY_MASK};
        this.B = 60.0f;
        this.C = 0.0f;
        this.D = 0.0f;
        this.E = e(2.0f);
        this.F = e(10.0f);
        this.G = e(14.0f);
        this.H = e(10.0f);
        this.I = e(13.0f);
        this.J = 1000;
        this.K = e(20.0f);
        this.L = e(5.0f);
        this.M = e(5.0f);
        this.N = Constants.WHITE;
        this.O = "#111111";
        this.P = "#111111";
        this.Q = "#33ffffff";
        f(context, attributeSet);
        g();
    }

    public ColorArcProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = 150;
        this.v = 180.0f;
        this.w = 270.0f;
        this.x = 0.0f;
        this.z = new int[]{-16711936, InputDeviceCompat.SOURCE_ANY, SupportMenu.CATEGORY_MASK, SupportMenu.CATEGORY_MASK};
        this.B = 60.0f;
        this.C = 0.0f;
        this.D = 0.0f;
        this.E = e(2.0f);
        this.F = e(10.0f);
        this.G = e(14.0f);
        this.H = e(10.0f);
        this.I = e(13.0f);
        this.J = 1000;
        this.K = e(20.0f);
        this.L = e(5.0f);
        this.M = e(5.0f);
        this.N = Constants.WHITE;
        this.O = "#111111";
        this.P = "#111111";
        this.Q = "#33ffffff";
        f(context, attributeSet);
        g();
    }
}
