package androidx.constraintlayout.utils.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.motion.widget.Debug;
import androidx.constraintlayout.motion.widget.FloatLayout;
import androidx.constraintlayout.widget.R;
import androidx.core.view.GravityCompat;
/* loaded from: classes.dex */
public class MotionLabel extends View implements FloatLayout {
    public static String f0 = "MotionLabel";
    public int A;
    public int B;
    public String C;
    public Layout D;
    public int E;
    public int F;
    public boolean G;
    public float H;
    public float I;
    public float J;
    public Drawable K;
    public Matrix L;
    public Bitmap M;
    public BitmapShader N;
    public Matrix O;
    public float P;
    public float Q;
    public float R;
    public float S;
    public Paint T;
    public int U;
    public Rect V;
    public Paint W;
    public float a0;
    public float b0;
    public float c0;
    public float d0;
    public float e0;
    public TextPaint h;
    public Path i;
    public int j;
    public int k;
    public boolean l;
    public float m;
    public float n;
    public ViewOutlineProvider o;
    public RectF p;
    public float q;
    public float r;
    public int s;
    public int t;
    public float u;
    public String v;
    public boolean w;
    public Rect x;
    public int y;
    public int z;

    /* loaded from: classes.dex */
    public class a extends ViewOutlineProvider {
        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            int width = MotionLabel.this.getWidth();
            int height = MotionLabel.this.getHeight();
            outline.setRoundRect(0, 0, width, height, (Math.min(width, height) * MotionLabel.this.m) / 2.0f);
        }
    }

    /* loaded from: classes.dex */
    public class b extends ViewOutlineProvider {
        public b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, MotionLabel.this.getWidth(), MotionLabel.this.getHeight(), MotionLabel.this.n);
        }
    }

    public MotionLabel(Context context) {
        super(context);
        this.h = new TextPaint();
        this.i = new Path();
        this.j = 65535;
        this.k = 65535;
        this.l = false;
        this.m = 0.0f;
        this.n = Float.NaN;
        this.q = 48.0f;
        this.r = Float.NaN;
        this.u = 0.0f;
        this.v = "Hello World";
        this.w = true;
        this.x = new Rect();
        this.y = 1;
        this.z = 1;
        this.A = 1;
        this.B = 1;
        this.E = 8388659;
        this.F = 0;
        this.G = false;
        this.P = Float.NaN;
        this.Q = Float.NaN;
        this.R = 0.0f;
        this.S = 0.0f;
        this.T = new Paint();
        this.U = 0;
        this.b0 = Float.NaN;
        this.c0 = Float.NaN;
        this.d0 = Float.NaN;
        this.e0 = Float.NaN;
        f(context, null);
    }

    private float getHorizontalOffset() {
        float f = Float.isNaN(this.r) ? 1.0f : this.q / this.r;
        TextPaint textPaint = this.h;
        String str = this.v;
        return (((((Float.isNaN(this.I) ? getMeasuredWidth() : this.I) - getPaddingLeft()) - getPaddingRight()) - (f * textPaint.measureText(str, 0, str.length()))) * (this.R + 1.0f)) / 2.0f;
    }

    private float getVerticalOffset() {
        float f = Float.isNaN(this.r) ? 1.0f : this.q / this.r;
        Paint.FontMetrics fontMetrics = this.h.getFontMetrics();
        float measuredHeight = ((Float.isNaN(this.J) ? getMeasuredHeight() : this.J) - getPaddingTop()) - getPaddingBottom();
        float f2 = fontMetrics.descent;
        float f3 = fontMetrics.ascent;
        return (((measuredHeight - ((f2 - f3) * f)) * (1.0f - this.S)) / 2.0f) - (f * f3);
    }

    public final void c(float f, float f2, float f3, float f4) {
        if (this.O == null) {
            return;
        }
        this.I = f3 - f;
        this.J = f4 - f2;
        k();
    }

    public Bitmap d(Bitmap bitmap, int i) {
        System.nanoTime();
        int width = bitmap.getWidth() / 2;
        int height = bitmap.getHeight() / 2;
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        for (int i2 = 0; i2 < i && width >= 32 && height >= 32; i2++) {
            width /= 2;
            height /= 2;
            createScaledBitmap = Bitmap.createScaledBitmap(createScaledBitmap, width, height, true);
        }
        return createScaledBitmap;
    }

    public void e(float f) {
        if (this.l || f != 1.0f) {
            this.i.reset();
            String str = this.v;
            int length = str.length();
            this.h.getTextBounds(str, 0, length, this.x);
            this.h.getTextPath(str, 0, length, 0.0f, 0.0f, this.i);
            if (f != 1.0f) {
                Log.v(f0, Debug.getLoc() + " scale " + f);
                Matrix matrix = new Matrix();
                matrix.postScale(f, f);
                this.i.transform(matrix);
            }
            Rect rect = this.x;
            rect.right--;
            rect.left++;
            rect.bottom++;
            rect.top--;
            RectF rectF = new RectF();
            rectF.bottom = getHeight();
            rectF.right = getWidth();
            this.w = false;
        }
    }

    public final void f(Context context, AttributeSet attributeSet) {
        h(context, attributeSet);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.MotionLabel);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.MotionLabel_android_text) {
                    setText(obtainStyledAttributes.getText(index));
                } else if (index == R.styleable.MotionLabel_android_fontFamily) {
                    this.C = obtainStyledAttributes.getString(index);
                } else if (index == R.styleable.MotionLabel_scaleFromTextSize) {
                    this.r = obtainStyledAttributes.getDimensionPixelSize(index, (int) this.r);
                } else if (index == R.styleable.MotionLabel_android_textSize) {
                    this.q = obtainStyledAttributes.getDimensionPixelSize(index, (int) this.q);
                } else if (index == R.styleable.MotionLabel_android_textStyle) {
                    this.s = obtainStyledAttributes.getInt(index, this.s);
                } else if (index == R.styleable.MotionLabel_android_typeface) {
                    this.t = obtainStyledAttributes.getInt(index, this.t);
                } else if (index == R.styleable.MotionLabel_android_textColor) {
                    this.j = obtainStyledAttributes.getColor(index, this.j);
                } else if (index == R.styleable.MotionLabel_borderRound) {
                    float dimension = obtainStyledAttributes.getDimension(index, this.n);
                    this.n = dimension;
                    if (Build.VERSION.SDK_INT >= 21) {
                        setRound(dimension);
                    }
                } else if (index == R.styleable.MotionLabel_borderRoundPercent) {
                    float f = obtainStyledAttributes.getFloat(index, this.m);
                    this.m = f;
                    if (Build.VERSION.SDK_INT >= 21) {
                        setRoundPercent(f);
                    }
                } else if (index == R.styleable.MotionLabel_android_gravity) {
                    setGravity(obtainStyledAttributes.getInt(index, -1));
                } else if (index == R.styleable.MotionLabel_android_autoSizeTextType) {
                    this.F = obtainStyledAttributes.getInt(index, 0);
                } else if (index == R.styleable.MotionLabel_textOutlineColor) {
                    this.k = obtainStyledAttributes.getInt(index, this.k);
                    this.l = true;
                } else if (index == R.styleable.MotionLabel_textOutlineThickness) {
                    this.u = obtainStyledAttributes.getDimension(index, this.u);
                    this.l = true;
                } else if (index == R.styleable.MotionLabel_textBackground) {
                    this.K = obtainStyledAttributes.getDrawable(index);
                    this.l = true;
                } else if (index == R.styleable.MotionLabel_textBackgroundPanX) {
                    this.b0 = obtainStyledAttributes.getFloat(index, this.b0);
                } else if (index == R.styleable.MotionLabel_textBackgroundPanY) {
                    this.c0 = obtainStyledAttributes.getFloat(index, this.c0);
                } else if (index == R.styleable.MotionLabel_textPanX) {
                    this.R = obtainStyledAttributes.getFloat(index, this.R);
                } else if (index == R.styleable.MotionLabel_textPanY) {
                    this.S = obtainStyledAttributes.getFloat(index, this.S);
                } else if (index == R.styleable.MotionLabel_textBackgroundRotate) {
                    this.e0 = obtainStyledAttributes.getFloat(index, this.e0);
                } else if (index == R.styleable.MotionLabel_textBackgroundZoom) {
                    this.d0 = obtainStyledAttributes.getFloat(index, this.d0);
                } else if (index == R.styleable.MotionLabel_textureHeight) {
                    this.P = obtainStyledAttributes.getDimension(index, this.P);
                } else if (index == R.styleable.MotionLabel_textureWidth) {
                    this.Q = obtainStyledAttributes.getDimension(index, this.Q);
                } else if (index == R.styleable.MotionLabel_textureEffect) {
                    this.U = obtainStyledAttributes.getInt(index, this.U);
                }
            }
            obtainStyledAttributes.recycle();
        }
        j();
        i();
    }

    public final void g(String str, int i, int i2) {
        Typeface typeface;
        Typeface create;
        if (str != null) {
            typeface = Typeface.create(str, i2);
            if (typeface != null) {
                setTypeface(typeface);
                return;
            }
        } else {
            typeface = null;
        }
        if (i == 1) {
            typeface = Typeface.SANS_SERIF;
        } else if (i == 2) {
            typeface = Typeface.SERIF;
        } else if (i == 3) {
            typeface = Typeface.MONOSPACE;
        }
        if (i2 > 0) {
            if (typeface == null) {
                create = Typeface.defaultFromStyle(i2);
            } else {
                create = Typeface.create(typeface, i2);
            }
            setTypeface(create);
            int i3 = (~(create != null ? create.getStyle() : 0)) & i2;
            this.h.setFakeBoldText((i3 & 1) != 0);
            this.h.setTextSkewX((i3 & 2) != 0 ? -0.25f : 0.0f);
            return;
        }
        this.h.setFakeBoldText(false);
        this.h.setTextSkewX(0.0f);
        setTypeface(typeface);
    }

    public float getRound() {
        return this.n;
    }

    public float getRoundPercent() {
        return this.m;
    }

    public float getScaleFromTextSize() {
        return this.r;
    }

    public float getTextBackgroundPanX() {
        return this.b0;
    }

    public float getTextBackgroundPanY() {
        return this.c0;
    }

    public float getTextBackgroundRotate() {
        return this.e0;
    }

    public float getTextBackgroundZoom() {
        return this.d0;
    }

    public int getTextOutlineColor() {
        return this.k;
    }

    public float getTextPanX() {
        return this.R;
    }

    public float getTextPanY() {
        return this.S;
    }

    public float getTextureHeight() {
        return this.P;
    }

    public float getTextureWidth() {
        return this.Q;
    }

    public Typeface getTypeface() {
        return this.h.getTypeface();
    }

    public final void h(Context context, @Nullable AttributeSet attributeSet) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(androidx.appcompat.R.attr.colorPrimary, typedValue, true);
        TextPaint textPaint = this.h;
        int i = typedValue.data;
        this.j = i;
        textPaint.setColor(i);
    }

    public void i() {
        this.y = getPaddingLeft();
        this.z = getPaddingRight();
        this.A = getPaddingTop();
        this.B = getPaddingBottom();
        g(this.C, this.t, this.s);
        this.h.setColor(this.j);
        this.h.setStrokeWidth(this.u);
        this.h.setStyle(Paint.Style.FILL_AND_STROKE);
        this.h.setFlags(128);
        setTextSize(this.q);
        this.h.setAntiAlias(true);
    }

    public final void j() {
        if (this.K != null) {
            this.O = new Matrix();
            int intrinsicWidth = this.K.getIntrinsicWidth();
            int intrinsicHeight = this.K.getIntrinsicHeight();
            if (intrinsicWidth <= 0 && (intrinsicWidth = getWidth()) == 0) {
                intrinsicWidth = Float.isNaN(this.Q) ? 128 : (int) this.Q;
            }
            if (intrinsicHeight <= 0 && (intrinsicHeight = getHeight()) == 0) {
                intrinsicHeight = Float.isNaN(this.P) ? 128 : (int) this.P;
            }
            if (this.U != 0) {
                intrinsicWidth /= 2;
                intrinsicHeight /= 2;
            }
            this.M = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(this.M);
            this.K.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            this.K.setFilterBitmap(true);
            this.K.draw(canvas);
            if (this.U != 0) {
                this.M = d(this.M, 4);
            }
            Bitmap bitmap = this.M;
            Shader.TileMode tileMode = Shader.TileMode.REPEAT;
            this.N = new BitmapShader(bitmap, tileMode, tileMode);
        }
    }

    public final void k() {
        float f = Float.isNaN(this.b0) ? 0.0f : this.b0;
        float f2 = Float.isNaN(this.c0) ? 0.0f : this.c0;
        float f3 = Float.isNaN(this.d0) ? 1.0f : this.d0;
        float f4 = Float.isNaN(this.e0) ? 0.0f : this.e0;
        this.O.reset();
        float width = this.M.getWidth();
        float height = this.M.getHeight();
        float f5 = Float.isNaN(this.Q) ? this.I : this.Q;
        float f6 = Float.isNaN(this.P) ? this.J : this.P;
        float f7 = f3 * (width * f6 < height * f5 ? f5 / width : f6 / height);
        this.O.postScale(f7, f7);
        float f8 = width * f7;
        float f9 = f5 - f8;
        float f10 = f7 * height;
        float f11 = f6 - f10;
        if (!Float.isNaN(this.P)) {
            f11 = this.P / 2.0f;
        }
        if (!Float.isNaN(this.Q)) {
            f9 = this.Q / 2.0f;
        }
        this.O.postTranslate((((f * f9) + f5) - f8) * 0.5f, (((f2 * f11) + f6) - f10) * 0.5f);
        this.O.postRotate(f4, f5 / 2.0f, f6 / 2.0f);
        this.N.setLocalMatrix(this.O);
    }

    @Override // android.view.View
    public void layout(int i, int i2, int i3, int i4) {
        super.layout(i, i2, i3, i4);
        boolean isNaN = Float.isNaN(this.r);
        float f = isNaN ? 1.0f : this.q / this.r;
        this.I = i3 - i;
        this.J = i4 - i2;
        if (this.G) {
            if (this.V == null) {
                this.W = new Paint();
                this.V = new Rect();
                this.W.set(this.h);
                this.a0 = this.W.getTextSize();
            }
            Paint paint = this.W;
            String str = this.v;
            paint.getTextBounds(str, 0, str.length(), this.V);
            int width = this.V.width();
            int height = (int) (this.V.height() * 1.3f);
            float f2 = (this.I - this.z) - this.y;
            float f3 = (this.J - this.B) - this.A;
            if (isNaN) {
                float f4 = width;
                float f5 = height;
                if (f4 * f3 > f5 * f2) {
                    this.h.setTextSize((this.a0 * f2) / f4);
                } else {
                    this.h.setTextSize((this.a0 * f3) / f5);
                }
            } else {
                float f6 = width;
                float f7 = height;
                f = f6 * f3 > f7 * f2 ? f2 / f6 : f3 / f7;
            }
        }
        if (this.l || !isNaN) {
            c(i, i2, i3, i4);
            e(f);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        float f = Float.isNaN(this.r) ? 1.0f : this.q / this.r;
        super.onDraw(canvas);
        if (!this.l && f == 1.0f) {
            canvas.drawText(this.v, this.H + this.y + getHorizontalOffset(), this.A + getVerticalOffset(), this.h);
            return;
        }
        if (this.w) {
            e(f);
        }
        if (this.L == null) {
            this.L = new Matrix();
        }
        if (this.l) {
            this.T.set(this.h);
            this.L.reset();
            float horizontalOffset = this.y + getHorizontalOffset();
            float verticalOffset = this.A + getVerticalOffset();
            this.L.postTranslate(horizontalOffset, verticalOffset);
            this.L.preScale(f, f);
            this.i.transform(this.L);
            if (this.N != null) {
                this.h.setFilterBitmap(true);
                this.h.setShader(this.N);
            } else {
                this.h.setColor(this.j);
            }
            this.h.setStyle(Paint.Style.FILL);
            this.h.setStrokeWidth(this.u);
            canvas.drawPath(this.i, this.h);
            if (this.N != null) {
                this.h.setShader(null);
            }
            this.h.setColor(this.k);
            this.h.setStyle(Paint.Style.STROKE);
            this.h.setStrokeWidth(this.u);
            canvas.drawPath(this.i, this.h);
            this.L.reset();
            this.L.postTranslate(-horizontalOffset, -verticalOffset);
            this.i.transform(this.L);
            this.h.set(this.T);
            return;
        }
        float horizontalOffset2 = this.y + getHorizontalOffset();
        float verticalOffset2 = this.A + getVerticalOffset();
        this.L.reset();
        this.L.preTranslate(horizontalOffset2, verticalOffset2);
        this.i.transform(this.L);
        this.h.setColor(this.j);
        this.h.setStyle(Paint.Style.FILL_AND_STROKE);
        this.h.setStrokeWidth(this.u);
        canvas.drawPath(this.i, this.h);
        this.L.reset();
        this.L.preTranslate(-horizontalOffset2, -verticalOffset2);
        this.i.transform(this.L);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        this.G = false;
        this.y = getPaddingLeft();
        this.z = getPaddingRight();
        this.A = getPaddingTop();
        this.B = getPaddingBottom();
        if (mode == 1073741824 && mode2 == 1073741824) {
            if (this.F != 0) {
                this.G = true;
            }
        } else {
            TextPaint textPaint = this.h;
            String str = this.v;
            textPaint.getTextBounds(str, 0, str.length(), this.x);
            if (mode != 1073741824) {
                size = (int) (this.x.width() + 0.99999f);
            }
            size += this.y + this.z;
            if (mode2 != 1073741824) {
                int fontMetricsInt = (int) (this.h.getFontMetricsInt(null) + 0.99999f);
                if (mode2 == Integer.MIN_VALUE) {
                    fontMetricsInt = Math.min(size2, fontMetricsInt);
                }
                size2 = this.A + this.B + fontMetricsInt;
            }
        }
        setMeasuredDimension(size, size2);
    }

    @SuppressLint({"RtlHardcoded"})
    public void setGravity(int i) {
        if ((i & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) == 0) {
            i |= GravityCompat.START;
        }
        if ((i & 112) == 0) {
            i |= 48;
        }
        if (i != this.E) {
            invalidate();
        }
        this.E = i;
        int i2 = i & 112;
        if (i2 == 48) {
            this.S = -1.0f;
        } else if (i2 != 80) {
            this.S = 0.0f;
        } else {
            this.S = 1.0f;
        }
        int i3 = i & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if (i3 != 3) {
            if (i3 != 5) {
                if (i3 != 8388611) {
                    if (i3 != 8388613) {
                        this.R = 0.0f;
                        return;
                    }
                }
            }
            this.R = 1.0f;
            return;
        }
        this.R = -1.0f;
    }

    @RequiresApi(21)
    public void setRound(float f) {
        if (Float.isNaN(f)) {
            this.n = f;
            float f2 = this.m;
            this.m = -1.0f;
            setRoundPercent(f2);
            return;
        }
        boolean z = this.n != f;
        this.n = f;
        if (f != 0.0f) {
            if (this.i == null) {
                this.i = new Path();
            }
            if (this.p == null) {
                this.p = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.o == null) {
                    b bVar = new b();
                    this.o = bVar;
                    setOutlineProvider(bVar);
                }
                setClipToOutline(true);
            }
            this.p.set(0.0f, 0.0f, getWidth(), getHeight());
            this.i.reset();
            Path path = this.i;
            RectF rectF = this.p;
            float f3 = this.n;
            path.addRoundRect(rectF, f3, f3, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    @RequiresApi(21)
    public void setRoundPercent(float f) {
        boolean z = this.m != f;
        this.m = f;
        if (f != 0.0f) {
            if (this.i == null) {
                this.i = new Path();
            }
            if (this.p == null) {
                this.p = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.o == null) {
                    a aVar = new a();
                    this.o = aVar;
                    setOutlineProvider(aVar);
                }
                setClipToOutline(true);
            }
            int width = getWidth();
            int height = getHeight();
            float min = (Math.min(width, height) * this.m) / 2.0f;
            this.p.set(0.0f, 0.0f, width, height);
            this.i.reset();
            this.i.addRoundRect(this.p, min, min, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setScaleFromTextSize(float f) {
        this.r = f;
    }

    public void setText(CharSequence charSequence) {
        this.v = charSequence.toString();
        invalidate();
    }

    public void setTextBackgroundPanX(float f) {
        this.b0 = f;
        k();
        invalidate();
    }

    public void setTextBackgroundPanY(float f) {
        this.c0 = f;
        k();
        invalidate();
    }

    public void setTextBackgroundRotate(float f) {
        this.e0 = f;
        k();
        invalidate();
    }

    public void setTextBackgroundZoom(float f) {
        this.d0 = f;
        k();
        invalidate();
    }

    public void setTextFillColor(int i) {
        this.j = i;
        invalidate();
    }

    public void setTextOutlineColor(int i) {
        this.k = i;
        this.l = true;
        invalidate();
    }

    public void setTextOutlineThickness(float f) {
        this.u = f;
        this.l = true;
        if (Float.isNaN(f)) {
            this.u = 1.0f;
            this.l = false;
        }
        invalidate();
    }

    public void setTextPanX(float f) {
        this.R = f;
        invalidate();
    }

    public void setTextPanY(float f) {
        this.S = f;
        invalidate();
    }

    public void setTextSize(float f) {
        this.q = f;
        String str = f0;
        Log.v(str, Debug.getLoc() + "  " + f + " / " + this.r);
        TextPaint textPaint = this.h;
        if (!Float.isNaN(this.r)) {
            f = this.r;
        }
        textPaint.setTextSize(f);
        e(Float.isNaN(this.r) ? 1.0f : this.q / this.r);
        requestLayout();
        invalidate();
    }

    public void setTextureHeight(float f) {
        this.P = f;
        k();
        invalidate();
    }

    public void setTextureWidth(float f) {
        this.Q = f;
        k();
        invalidate();
    }

    public void setTypeface(Typeface typeface) {
        if (this.h.getTypeface() != typeface) {
            this.h.setTypeface(typeface);
            if (this.D != null) {
                this.D = null;
                requestLayout();
                invalidate();
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.FloatLayout
    public void layout(float f, float f2, float f3, float f4) {
        int i = (int) (f + 0.5f);
        this.H = f - i;
        int i2 = (int) (f3 + 0.5f);
        int i3 = i2 - i;
        int i4 = (int) (f4 + 0.5f);
        int i5 = (int) (0.5f + f2);
        int i6 = i4 - i5;
        float f5 = f3 - f;
        this.I = f5;
        float f6 = f4 - f2;
        this.J = f6;
        c(f, f2, f3, f4);
        if (getMeasuredHeight() == i6 && getMeasuredWidth() == i3) {
            super.layout(i, i5, i2, i4);
        } else {
            measure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), View.MeasureSpec.makeMeasureSpec(i6, 1073741824));
            super.layout(i, i5, i2, i4);
        }
        if (this.G) {
            if (this.V == null) {
                this.W = new Paint();
                this.V = new Rect();
                this.W.set(this.h);
                this.a0 = this.W.getTextSize();
            }
            this.I = f5;
            this.J = f6;
            Paint paint = this.W;
            String str = this.v;
            paint.getTextBounds(str, 0, str.length(), this.V);
            float height = this.V.height() * 1.3f;
            float f7 = (f5 - this.z) - this.y;
            float f8 = (f6 - this.B) - this.A;
            float width = this.V.width();
            if (width * f8 > height * f7) {
                this.h.setTextSize((this.a0 * f7) / width);
            } else {
                this.h.setTextSize((this.a0 * f8) / height);
            }
            if (this.l || !Float.isNaN(this.r)) {
                e(Float.isNaN(this.r) ? 1.0f : this.q / this.r);
            }
        }
    }

    public MotionLabel(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = new TextPaint();
        this.i = new Path();
        this.j = 65535;
        this.k = 65535;
        this.l = false;
        this.m = 0.0f;
        this.n = Float.NaN;
        this.q = 48.0f;
        this.r = Float.NaN;
        this.u = 0.0f;
        this.v = "Hello World";
        this.w = true;
        this.x = new Rect();
        this.y = 1;
        this.z = 1;
        this.A = 1;
        this.B = 1;
        this.E = 8388659;
        this.F = 0;
        this.G = false;
        this.P = Float.NaN;
        this.Q = Float.NaN;
        this.R = 0.0f;
        this.S = 0.0f;
        this.T = new Paint();
        this.U = 0;
        this.b0 = Float.NaN;
        this.c0 = Float.NaN;
        this.d0 = Float.NaN;
        this.e0 = Float.NaN;
        f(context, attributeSet);
    }

    public MotionLabel(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = new TextPaint();
        this.i = new Path();
        this.j = 65535;
        this.k = 65535;
        this.l = false;
        this.m = 0.0f;
        this.n = Float.NaN;
        this.q = 48.0f;
        this.r = Float.NaN;
        this.u = 0.0f;
        this.v = "Hello World";
        this.w = true;
        this.x = new Rect();
        this.y = 1;
        this.z = 1;
        this.A = 1;
        this.B = 1;
        this.E = 8388659;
        this.F = 0;
        this.G = false;
        this.P = Float.NaN;
        this.Q = Float.NaN;
        this.R = 0.0f;
        this.S = 0.0f;
        this.T = new Paint();
        this.U = 0;
        this.b0 = Float.NaN;
        this.c0 = Float.NaN;
        this.d0 = Float.NaN;
        this.e0 = Float.NaN;
        f(context, attributeSet);
    }
}
