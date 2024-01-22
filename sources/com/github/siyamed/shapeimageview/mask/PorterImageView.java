package com.github.siyamed.shapeimageview.mask;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import com.github.siyamed.shapeimageview.R;
/* loaded from: classes9.dex */
public abstract class PorterImageView extends ImageView {
    public static final String p = PorterImageView.class.getSimpleName();
    public static final PorterDuffXfermode q = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    public Canvas h;
    public Bitmap i;
    public Paint j;
    public Canvas k;
    public Bitmap l;
    public Paint m;
    public boolean n;
    public boolean o;

    public PorterImageView(Context context) {
        super(context);
        this.n = true;
        this.o = false;
        b(context, null, 0);
    }

    public final void a(int i, int i2, int i3, int i4) {
        boolean z = false;
        boolean z2 = (i == i3 && i2 == i4) ? false : true;
        if (i > 0 && i2 > 0) {
            z = true;
        }
        if (z) {
            if (this.h == null || z2) {
                this.h = new Canvas();
                Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                this.i = createBitmap;
                this.h.setBitmap(createBitmap);
                this.j.reset();
                paintMaskCanvas(this.h, this.j, i, i2);
                this.k = new Canvas();
                Bitmap createBitmap2 = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                this.l = createBitmap2;
                this.k.setBitmap(createBitmap2);
                this.m = new Paint(1);
                this.n = true;
            }
        }
    }

    public final void b(Context context, AttributeSet attributeSet, int i) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShaderImageView, i, 0);
            this.o = obtainStyledAttributes.getBoolean(R.styleable.ShaderImageView_siSquare, false);
            obtainStyledAttributes.recycle();
        }
        if (getScaleType() == ImageView.ScaleType.FIT_CENTER) {
            setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        Paint paint = new Paint(1);
        this.j = paint;
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
    }

    @Override // android.view.View
    public void invalidate() {
        this.n = true;
        super.invalidate();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        Drawable drawable;
        if (!isInEditMode()) {
            int saveLayer = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), null, 31);
            try {
                try {
                    if (this.n && (drawable = getDrawable()) != null) {
                        this.n = false;
                        Matrix imageMatrix = getImageMatrix();
                        if (imageMatrix == null) {
                            drawable.draw(this.k);
                        } else {
                            int saveCount = this.k.getSaveCount();
                            this.k.save();
                            this.k.concat(imageMatrix);
                            drawable.draw(this.k);
                            this.k.restoreToCount(saveCount);
                        }
                        this.m.reset();
                        this.m.setFilterBitmap(false);
                        this.m.setXfermode(q);
                        this.k.drawBitmap(this.i, 0.0f, 0.0f, this.m);
                    }
                    if (!this.n) {
                        this.m.setXfermode(null);
                        canvas.drawBitmap(this.l, 0.0f, 0.0f, this.m);
                    }
                } catch (Exception e) {
                    Log.e(p, "Exception occured while drawing " + getId(), e);
                }
                return;
            } finally {
                canvas.restoreToCount(saveLayer);
            }
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.o) {
            int min = Math.min(getMeasuredWidth(), getMeasuredHeight());
            setMeasuredDimension(min, min);
        }
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        a(i, i2, i3, i4);
    }

    public abstract void paintMaskCanvas(Canvas canvas, Paint paint, int i, int i2);

    public void setSquare(boolean z) {
        this.o = z;
    }

    public PorterImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n = true;
        this.o = false;
        b(context, attributeSet, 0);
    }

    public PorterImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.n = true;
        this.o = false;
        b(context, attributeSet, i);
    }
}
