package com.github.siyamed.shapeimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.github.siyamed.shapeimageview.shader.ShaderHelper;
/* loaded from: classes9.dex */
public abstract class ShaderImageView extends ImageView {
    public ShaderHelper h;

    public ShaderImageView(Context context) {
        super(context);
        a(context, null, 0);
    }

    public final void a(Context context, AttributeSet attributeSet, int i) {
        getPathHelper().init(context, attributeSet, i);
    }

    public abstract ShaderHelper createImageViewHelper();

    public float getBorderAlpha() {
        return getPathHelper().getBorderAlpha();
    }

    public int getBorderWidth() {
        return getPathHelper().getBorderWidth();
    }

    public ShaderHelper getPathHelper() {
        if (this.h == null) {
            this.h = createImageViewHelper();
        }
        return this.h;
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        if (getPathHelper().onDraw(canvas)) {
            return;
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        if (getPathHelper().isSquare()) {
            super.onMeasure(i, i);
        } else {
            super.onMeasure(i, i2);
        }
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        getPathHelper().onSizeChanged(i, i2);
    }

    public void setBorderAlpha(float f) {
        getPathHelper().setBorderAlpha(f);
        invalidate();
    }

    public void setBorderColor(int i) {
        getPathHelper().setBorderColor(i);
        invalidate();
    }

    public void setBorderWidth(int i) {
        getPathHelper().setBorderWidth(i);
        invalidate();
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        getPathHelper().onImageDrawableReset(getDrawable());
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        getPathHelper().onImageDrawableReset(getDrawable());
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        getPathHelper().onImageDrawableReset(getDrawable());
    }

    public void setSquare(boolean z) {
        getPathHelper().setSquare(z);
        invalidate();
    }

    public ShaderImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public ShaderImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }
}
