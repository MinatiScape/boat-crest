package com.theartofdev.edmodo.cropper;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
/* loaded from: classes12.dex */
public final class c extends Animation implements Animation.AnimationListener {
    public final ImageView h;
    public final CropOverlayView i;
    public final float[] j = new float[8];
    public final float[] k = new float[8];
    public final RectF l = new RectF();
    public final RectF m = new RectF();
    public final float[] n = new float[9];
    public final float[] o = new float[9];
    public final RectF p = new RectF();
    public final float[] q = new float[8];
    public final float[] r = new float[9];

    public c(ImageView imageView, CropOverlayView cropOverlayView) {
        this.h = imageView;
        this.i = cropOverlayView;
        setDuration(300L);
        setFillAfter(true);
        setInterpolator(new AccelerateDecelerateInterpolator());
        setAnimationListener(this);
    }

    public void a(float[] fArr, Matrix matrix) {
        System.arraycopy(fArr, 0, this.k, 0, 8);
        this.m.set(this.i.getCropWindowRect());
        matrix.getValues(this.o);
    }

    @Override // android.view.animation.Animation
    public void applyTransformation(float f, Transformation transformation) {
        float[] fArr;
        RectF rectF = this.p;
        RectF rectF2 = this.l;
        float f2 = rectF2.left;
        RectF rectF3 = this.m;
        rectF.left = f2 + ((rectF3.left - f2) * f);
        float f3 = rectF2.top;
        rectF.top = f3 + ((rectF3.top - f3) * f);
        float f4 = rectF2.right;
        rectF.right = f4 + ((rectF3.right - f4) * f);
        float f5 = rectF2.bottom;
        rectF.bottom = f5 + ((rectF3.bottom - f5) * f);
        this.i.setCropWindowRect(rectF);
        int i = 0;
        int i2 = 0;
        while (true) {
            fArr = this.q;
            if (i2 >= fArr.length) {
                break;
            }
            float[] fArr2 = this.j;
            fArr[i2] = fArr2[i2] + ((this.k[i2] - fArr2[i2]) * f);
            i2++;
        }
        this.i.setBounds(fArr, this.h.getWidth(), this.h.getHeight());
        while (true) {
            float[] fArr3 = this.r;
            if (i < fArr3.length) {
                float[] fArr4 = this.n;
                fArr3[i] = fArr4[i] + ((this.o[i] - fArr4[i]) * f);
                i++;
            } else {
                Matrix imageMatrix = this.h.getImageMatrix();
                imageMatrix.setValues(this.r);
                this.h.setImageMatrix(imageMatrix);
                this.h.invalidate();
                this.i.invalidate();
                return;
            }
        }
    }

    public void b(float[] fArr, Matrix matrix) {
        reset();
        System.arraycopy(fArr, 0, this.j, 0, 8);
        this.l.set(this.i.getCropWindowRect());
        matrix.getValues(this.n);
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        this.h.clearAnimation();
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationStart(Animation animation) {
    }
}
