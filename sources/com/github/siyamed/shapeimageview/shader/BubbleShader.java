package com.github.siyamed.shapeimageview.shader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import com.github.siyamed.shapeimageview.R;
/* loaded from: classes9.dex */
public class BubbleShader extends ShaderHelper {
    public int b;

    /* renamed from: a  reason: collision with root package name */
    public final Path f7977a = new Path();
    public ArrowPosition c = ArrowPosition.LEFT;

    /* loaded from: classes9.dex */
    public enum ArrowPosition {
        LEFT,
        RIGHT
    }

    /* loaded from: classes9.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7978a;

        static {
            int[] iArr = new int[ArrowPosition.values().length];
            f7978a = iArr;
            try {
                iArr[ArrowPosition.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7978a[ArrowPosition.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.github.siyamed.shapeimageview.shader.ShaderHelper
    public void calculate(int i, int i2, float f, float f2, float f3, float f4, float f5) {
        this.f7977a.reset();
        float f6 = -f4;
        float f7 = -f5;
        float f8 = this.b / f3;
        float f9 = i + (f4 * 2.0f);
        float f10 = i2 + (f5 * 2.0f);
        float f11 = (f10 / 2.0f) + f7;
        this.f7977a.setFillType(Path.FillType.EVEN_ODD);
        int i3 = a.f7978a[this.c.ordinal()];
        if (i3 == 1) {
            float f12 = f8 + f6;
            this.f7977a.addRect(f12, f7, f9 + f12, f10 + f7, Path.Direction.CW);
            this.f7977a.moveTo(f6, f11);
            this.f7977a.lineTo(f12, f11 - f8);
            this.f7977a.lineTo(f12, f8 + f11);
            this.f7977a.lineTo(f6, f11);
        } else if (i3 != 2) {
        } else {
            float f13 = f9 + f6;
            float f14 = f13 - f8;
            this.f7977a.addRect(f6, f7, f14, f10 + f7, Path.Direction.CW);
            this.f7977a.moveTo(f13, f11);
            this.f7977a.lineTo(f14, f11 - f8);
            this.f7977a.lineTo(f14, f8 + f11);
            this.f7977a.lineTo(f13, f11);
        }
    }

    @Override // com.github.siyamed.shapeimageview.shader.ShaderHelper
    public void draw(Canvas canvas, Paint paint, Paint paint2) {
        canvas.save();
        canvas.concat(this.matrix);
        canvas.drawPath(this.f7977a, paint);
        canvas.restore();
    }

    public ArrowPosition getArrowPosition() {
        return this.c;
    }

    public int getTriangleHeightPx() {
        return this.b;
    }

    @Override // com.github.siyamed.shapeimageview.shader.ShaderHelper
    public void init(Context context, AttributeSet attributeSet, int i) {
        super.init(context, attributeSet, i);
        this.borderWidth = 0;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShaderImageView, i, 0);
            this.b = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ShaderImageView_siTriangleHeight, 0);
            this.c = ArrowPosition.values()[obtainStyledAttributes.getInt(R.styleable.ShaderImageView_siArrowPosition, ArrowPosition.LEFT.ordinal())];
            obtainStyledAttributes.recycle();
        }
        if (this.b == 0) {
            this.b = dpToPx(context.getResources().getDisplayMetrics(), 10);
        }
    }

    @Override // com.github.siyamed.shapeimageview.shader.ShaderHelper
    public void reset() {
        this.f7977a.reset();
    }

    public void setArrowPosition(ArrowPosition arrowPosition) {
        this.c = arrowPosition;
    }

    public void setTriangleHeightPx(int i) {
        this.b = i;
    }
}
