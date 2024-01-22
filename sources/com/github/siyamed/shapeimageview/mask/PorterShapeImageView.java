package com.github.siyamed.shapeimageview.mask;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.github.siyamed.shapeimageview.R;
/* loaded from: classes9.dex */
public class PorterShapeImageView extends PorterImageView {
    public Drawable r;
    public Matrix s;
    public Matrix t;

    public PorterShapeImageView(Context context) {
        super(context);
        b(context, null, 0);
    }

    private void b(Context context, AttributeSet attributeSet, int i) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShaderImageView, i, 0);
            this.r = obtainStyledAttributes.getDrawable(R.styleable.ShaderImageView_siShape);
            obtainStyledAttributes.recycle();
        }
        this.s = new Matrix();
    }

    public final void c(int i, int i2) {
        float f;
        float f2;
        float f3;
        float f4;
        this.t = null;
        int intrinsicWidth = this.r.getIntrinsicWidth();
        int intrinsicHeight = this.r.getIntrinsicHeight();
        boolean z = i == intrinsicWidth && i2 == intrinsicHeight;
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0 || z) {
            return;
        }
        this.r.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        float min = Math.min(i / intrinsicWidth, i2 / intrinsicHeight);
        this.s.setScale(min, min);
        this.s.postTranslate((int) (((f - (f2 * min)) * 0.5f) + 0.5f), (int) (((f3 - (f4 * min)) * 0.5f) + 0.5f));
    }

    @Override // com.github.siyamed.shapeimageview.mask.PorterImageView
    public void paintMaskCanvas(Canvas canvas, Paint paint, int i, int i2) {
        Drawable drawable = this.r;
        if (drawable != null) {
            if (drawable instanceof BitmapDrawable) {
                c(i, i2);
                if (this.t != null) {
                    int saveCount = canvas.getSaveCount();
                    canvas.save();
                    canvas.concat(this.s);
                    this.r.draw(canvas);
                    canvas.restoreToCount(saveCount);
                    return;
                }
            }
            this.r.setBounds(0, 0, i, i2);
            this.r.draw(canvas);
        }
    }

    public PorterShapeImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b(context, attributeSet, 0);
    }

    public PorterShapeImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b(context, attributeSet, i);
    }
}
