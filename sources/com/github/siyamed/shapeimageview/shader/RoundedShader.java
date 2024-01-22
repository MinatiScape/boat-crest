package com.github.siyamed.shapeimageview.shader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.github.siyamed.shapeimageview.R;
/* loaded from: classes9.dex */
public class RoundedShader extends ShaderHelper {

    /* renamed from: a  reason: collision with root package name */
    public final RectF f7980a = new RectF();
    public final RectF b = new RectF();
    public int c = 0;
    public int d;

    @Override // com.github.siyamed.shapeimageview.shader.ShaderHelper
    public void calculate(int i, int i2, float f, float f2, float f3, float f4, float f5) {
        this.b.set(-f4, -f5, i + f4, i2 + f5);
        this.d = Math.round(this.c / f3);
    }

    @Override // com.github.siyamed.shapeimageview.shader.ShaderHelper
    public void draw(Canvas canvas, Paint paint, Paint paint2) {
        RectF rectF = this.f7980a;
        int i = this.c;
        canvas.drawRoundRect(rectF, i, i, paint2);
        canvas.save();
        canvas.concat(this.matrix);
        RectF rectF2 = this.b;
        int i2 = this.d;
        canvas.drawRoundRect(rectF2, i2, i2, paint);
        canvas.restore();
    }

    public final int getRadius() {
        return this.c;
    }

    @Override // com.github.siyamed.shapeimageview.shader.ShaderHelper
    public void init(Context context, AttributeSet attributeSet, int i) {
        super.init(context, attributeSet, i);
        this.borderPaint.setStrokeWidth(this.borderWidth * 2);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShaderImageView, i, 0);
            this.c = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ShaderImageView_siRadius, this.c);
            obtainStyledAttributes.recycle();
        }
    }

    @Override // com.github.siyamed.shapeimageview.shader.ShaderHelper
    public void onSizeChanged(int i, int i2) {
        super.onSizeChanged(i, i2);
        RectF rectF = this.f7980a;
        int i3 = this.borderWidth;
        rectF.set(i3, i3, this.viewWidth - i3, this.viewHeight - i3);
    }

    @Override // com.github.siyamed.shapeimageview.shader.ShaderHelper
    public void reset() {
        this.b.set(0.0f, 0.0f, 0.0f, 0.0f);
        this.d = 0;
    }

    public final void setRadius(int i) {
        this.c = i;
    }
}
