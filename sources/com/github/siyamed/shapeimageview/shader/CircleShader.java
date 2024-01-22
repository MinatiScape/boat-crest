package com.github.siyamed.shapeimageview.shader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
/* loaded from: classes9.dex */
public class CircleShader extends ShaderHelper {

    /* renamed from: a  reason: collision with root package name */
    public float f7979a;
    public float b;
    public float c;
    public float d;
    public int e;

    @Override // com.github.siyamed.shapeimageview.shader.ShaderHelper
    public void calculate(int i, int i2, float f, float f2, float f3, float f4, float f5) {
        this.b = Math.round(i / 2.0f);
        this.c = Math.round(i2 / 2.0f);
        this.e = Math.round(((f / f3) / 2.0f) + 0.5f);
    }

    @Override // com.github.siyamed.shapeimageview.shader.ShaderHelper
    public void draw(Canvas canvas, Paint paint, Paint paint2) {
        float f = this.f7979a;
        canvas.drawCircle(f, f, this.d, paint2);
        canvas.save();
        canvas.concat(this.matrix);
        canvas.drawCircle(this.b, this.c, this.e, paint);
        canvas.restore();
    }

    public final float getBorderRadius() {
        return this.d;
    }

    @Override // com.github.siyamed.shapeimageview.shader.ShaderHelper
    public void init(Context context, AttributeSet attributeSet, int i) {
        super.init(context, attributeSet, i);
        this.square = true;
    }

    @Override // com.github.siyamed.shapeimageview.shader.ShaderHelper
    public void onSizeChanged(int i, int i2) {
        super.onSizeChanged(i, i2);
        this.f7979a = Math.round(this.viewWidth / 2.0f);
        this.d = Math.round((this.viewWidth - this.borderWidth) / 2.0f);
    }

    @Override // com.github.siyamed.shapeimageview.shader.ShaderHelper
    public void reset() {
        this.e = 0;
        this.b = 0.0f;
        this.c = 0.0f;
    }

    public final void setBorderRadius(float f) {
        this.d = f;
    }
}
