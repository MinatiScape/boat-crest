package com.github.siyamed.shapeimageview.shader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import com.github.siyamed.shapeimageview.R;
import com.github.siyamed.shapeimageview.path.SvgUtil;
import com.github.siyamed.shapeimageview.path.parser.PathInfo;
/* loaded from: classes9.dex */
public class SvgShader extends ShaderHelper {
    public static final int BORDER_TYPE_DEFAULT = 0;
    public static final int BORDER_TYPE_FILL = 1;
    public static final int STROKE_CAP_BUTT = 0;
    public static final int STROKE_CAP_DEFAULT = -1;
    public static final int STROKE_CAP_ROUND = 1;
    public static final int STROKE_CAP_SQUARE = 2;
    public static final int STROKE_JOIN_BEVEL = 0;
    public static final int STROKE_JOIN_DEFAULT = -1;
    public static final int STROKE_JOIN_MITER = 1;
    public static final int STROKE_JOIN_ROUND = 2;

    /* renamed from: a  reason: collision with root package name */
    public final Path f7981a;
    public final Path b;
    public final Matrix c;
    public final float[] d;
    public PathInfo e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;

    public SvgShader() {
        this.f7981a = new Path();
        this.b = new Path();
        this.c = new Matrix();
        this.d = new float[2];
        this.f = -1;
        this.g = 0;
        this.h = -1;
        this.i = -1;
        this.j = 0;
    }

    @Override // com.github.siyamed.shapeimageview.shader.ShaderHelper
    public void calculate(int i, int i2, float f, float f2, float f3, float f4, float f5) {
        float f6;
        float f7;
        float f8;
        this.f7981a.reset();
        this.b.reset();
        this.d[0] = this.e.getWidth();
        this.d[1] = this.e.getHeight();
        this.c.reset();
        float[] fArr = this.d;
        float min = Math.min(f / fArr[0], f2 / fArr[1]);
        this.c.setScale(min, min);
        this.c.postTranslate(Math.round((f - (this.d[0] * min)) * 0.5f), Math.round((f2 - (this.d[1] * min)) * 0.5f));
        this.e.transform(this.c, this.f7981a);
        Path path = this.f7981a;
        int i3 = this.borderWidth;
        path.offset(i3, i3);
        if (this.borderWidth > 0) {
            this.c.reset();
            if (this.g == 0) {
                int i4 = this.viewWidth;
                int i5 = this.borderWidth;
                f6 = i4 - i5;
                f7 = this.viewHeight - i5;
                f8 = i5 / 2.0f;
            } else {
                f6 = this.viewWidth;
                f7 = this.viewHeight;
                f8 = 0.0f;
            }
            float[] fArr2 = this.d;
            float min2 = Math.min(f6 / fArr2[0], f7 / fArr2[1]);
            this.c.setScale(min2, min2);
            this.c.postTranslate(Math.round(((f6 - (this.d[0] * min2)) * 0.5f) + f8), Math.round(((f7 - (this.d[1] * min2)) * 0.5f) + f8));
            this.e.transform(this.c, this.b);
        }
        this.c.reset();
        this.matrix.invert(this.c);
        this.f7981a.transform(this.c);
    }

    @Override // com.github.siyamed.shapeimageview.shader.ShaderHelper
    public void draw(Canvas canvas, Paint paint, Paint paint2) {
        canvas.save();
        canvas.drawPath(this.b, paint2);
        canvas.concat(this.matrix);
        canvas.drawPath(this.f7981a, paint);
        canvas.restore();
    }

    @Override // com.github.siyamed.shapeimageview.shader.ShaderHelper
    public void init(Context context, AttributeSet attributeSet, int i) {
        super.init(context, attributeSet, i);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShaderImageView, i, 0);
            this.f = obtainStyledAttributes.getResourceId(R.styleable.ShaderImageView_siShape, this.f);
            this.g = obtainStyledAttributes.getInt(R.styleable.ShaderImageView_siBorderType, this.g);
            this.h = obtainStyledAttributes.getInt(R.styleable.ShaderImageView_siStrokeCap, this.h);
            this.i = obtainStyledAttributes.getInt(R.styleable.ShaderImageView_siStrokeJoin, this.i);
            this.j = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ShaderImageView_siStrokeMiter, this.j);
            obtainStyledAttributes.recycle();
        }
        setShapeResId(context, this.f);
        setBorderType(this.g);
        setStrokeCap(this.h);
        setStrokeJoin(this.i);
        setStrokeMiter(this.j);
    }

    @Override // com.github.siyamed.shapeimageview.shader.ShaderHelper
    public void reset() {
        this.f7981a.reset();
        this.b.reset();
    }

    public void setBorderType(int i) {
        this.g = i;
        if (i != 1) {
            this.borderPaint.setStyle(Paint.Style.STROKE);
        } else {
            this.borderPaint.setStyle(Paint.Style.FILL);
        }
    }

    public void setShapeResId(Context context, int i) {
        if (i != -1) {
            this.e = SvgUtil.readSvg(context, i);
            return;
        }
        throw new RuntimeException("No resource is defined as shape");
    }

    public void setStrokeCap(int i) {
        this.h = i;
        if (i == 0) {
            this.borderPaint.setStrokeCap(Paint.Cap.BUTT);
        } else if (i == 1) {
            this.borderPaint.setStrokeCap(Paint.Cap.ROUND);
        } else if (i != 2) {
        } else {
            this.borderPaint.setStrokeCap(Paint.Cap.SQUARE);
        }
    }

    public void setStrokeJoin(int i) {
        this.i = i;
        if (i == 0) {
            this.borderPaint.setStrokeJoin(Paint.Join.BEVEL);
        } else if (i == 1) {
            this.borderPaint.setStrokeJoin(Paint.Join.MITER);
        } else if (i != 2) {
        } else {
            this.borderPaint.setStrokeJoin(Paint.Join.ROUND);
        }
    }

    public void setStrokeMiter(int i) {
        this.j = i;
        if (i > 0) {
            this.borderPaint.setStrokeMiter(i);
        }
    }

    public SvgShader(int i) {
        this.f7981a = new Path();
        this.b = new Path();
        this.c = new Matrix();
        this.d = new float[2];
        this.f = -1;
        this.g = 0;
        this.h = -1;
        this.i = -1;
        this.j = 0;
        this.f = i;
    }

    public SvgShader(int i, int i2) {
        this.f7981a = new Path();
        this.b = new Path();
        this.c = new Matrix();
        this.d = new float[2];
        this.f = -1;
        this.g = 0;
        this.h = -1;
        this.i = -1;
        this.j = 0;
        this.f = i;
        this.g = i2;
    }
}
