package com.github.siyamed.shapeimageview.mask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
/* loaded from: classes9.dex */
public class PorterCircularImageView extends PorterImageView {
    public final RectF r;

    public PorterCircularImageView(Context context) {
        super(context);
        this.r = new RectF();
        c();
    }

    public final void c() {
        setSquare(true);
    }

    @Override // com.github.siyamed.shapeimageview.mask.PorterImageView
    public void paintMaskCanvas(Canvas canvas, Paint paint, int i, int i2) {
        this.r.set(0.0f, 0.0f, i, i2);
        canvas.drawCircle(this.r.centerX(), this.r.centerY(), Math.min(i, i2) / 2.0f, paint);
    }

    public PorterCircularImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.r = new RectF();
        c();
    }

    public PorterCircularImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.r = new RectF();
        c();
    }
}
