package com.github.siyamed.shapeimageview.shader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import androidx.core.view.ViewCompat;
import com.github.siyamed.shapeimageview.R;
/* loaded from: classes9.dex */
public abstract class ShaderHelper {
    public final Paint borderPaint;
    public Drawable drawable;
    public final Paint imagePaint;
    public BitmapShader shader;
    public int viewHeight;
    public int viewWidth;
    public int borderColor = ViewCompat.MEASURED_STATE_MASK;
    public int borderWidth = 0;
    public float borderAlpha = 1.0f;
    public boolean square = false;
    public final Matrix matrix = new Matrix();

    public ShaderHelper() {
        Paint paint = new Paint();
        this.borderPaint = paint;
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.imagePaint = paint2;
        paint2.setAntiAlias(true);
    }

    public abstract void calculate(int i, int i2, float f, float f2, float f3, float f4, float f5);

    public Bitmap calculateDrawableSizes() {
        float f;
        float round;
        Bitmap bitmap = getBitmap();
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width > 0 && height > 0) {
                float round2 = Math.round(this.viewWidth - (this.borderWidth * 2.0f));
                float round3 = Math.round(this.viewHeight - (this.borderWidth * 2.0f));
                float f2 = width;
                float f3 = height;
                float f4 = 0.0f;
                if (f2 * round3 > round2 * f3) {
                    f = round3 / f3;
                    round = 0.0f;
                    f4 = Math.round(((round2 / f) - f2) / 2.0f);
                } else {
                    float f5 = round2 / f2;
                    f = f5;
                    round = Math.round(((round3 / f5) - f3) / 2.0f);
                }
                this.matrix.setScale(f, f);
                this.matrix.preTranslate(f4, round);
                Matrix matrix = this.matrix;
                int i = this.borderWidth;
                matrix.postTranslate(i, i);
                calculate(width, height, round2, round3, f, f4, round);
                return bitmap;
            }
        }
        reset();
        return null;
    }

    public void createShader() {
        Bitmap calculateDrawableSizes = calculateDrawableSizes();
        if (calculateDrawableSizes == null || calculateDrawableSizes.getWidth() <= 0 || calculateDrawableSizes.getHeight() <= 0) {
            return;
        }
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(calculateDrawableSizes, tileMode, tileMode);
        this.shader = bitmapShader;
        this.imagePaint.setShader(bitmapShader);
    }

    public final int dpToPx(DisplayMetrics displayMetrics, int i) {
        return Math.round(i * (displayMetrics.xdpi / 160.0f));
    }

    public abstract void draw(Canvas canvas, Paint paint, Paint paint2);

    public Bitmap getBitmap() {
        Drawable drawable = this.drawable;
        if (drawable == null || !(drawable instanceof BitmapDrawable)) {
            return null;
        }
        return ((BitmapDrawable) drawable).getBitmap();
    }

    public final float getBorderAlpha() {
        return this.borderAlpha;
    }

    public final int getBorderColor() {
        return this.borderColor;
    }

    public final int getBorderWidth() {
        return this.borderWidth;
    }

    public void init(Context context, AttributeSet attributeSet, int i) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShaderImageView, i, 0);
            this.borderColor = obtainStyledAttributes.getColor(R.styleable.ShaderImageView_siBorderColor, this.borderColor);
            this.borderWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ShaderImageView_siBorderWidth, this.borderWidth);
            this.borderAlpha = obtainStyledAttributes.getFloat(R.styleable.ShaderImageView_siBorderAlpha, this.borderAlpha);
            this.square = obtainStyledAttributes.getBoolean(R.styleable.ShaderImageView_siSquare, this.square);
            obtainStyledAttributes.recycle();
        }
        this.borderPaint.setColor(this.borderColor);
        this.borderPaint.setAlpha(Float.valueOf(this.borderAlpha * 255.0f).intValue());
        this.borderPaint.setStrokeWidth(this.borderWidth);
    }

    public final boolean isSquare() {
        return this.square;
    }

    public boolean onDraw(Canvas canvas) {
        if (this.shader == null) {
            createShader();
        }
        if (this.shader == null || this.viewWidth <= 0 || this.viewHeight <= 0) {
            return false;
        }
        draw(canvas, this.imagePaint, this.borderPaint);
        return true;
    }

    public final void onImageDrawableReset(Drawable drawable) {
        this.drawable = drawable;
        this.shader = null;
        this.imagePaint.setShader(null);
    }

    public void onSizeChanged(int i, int i2) {
        if (this.viewWidth == i && this.viewHeight == i2) {
            return;
        }
        this.viewWidth = i;
        this.viewHeight = i2;
        if (isSquare()) {
            int min = Math.min(i, i2);
            this.viewHeight = min;
            this.viewWidth = min;
        }
        if (this.shader != null) {
            calculateDrawableSizes();
        }
    }

    public abstract void reset();

    public final void setBorderAlpha(float f) {
        this.borderAlpha = f;
        Paint paint = this.borderPaint;
        if (paint != null) {
            paint.setAlpha(Float.valueOf(f * 255.0f).intValue());
        }
    }

    public final void setBorderColor(int i) {
        this.borderColor = i;
        Paint paint = this.borderPaint;
        if (paint != null) {
            paint.setColor(i);
        }
    }

    public final void setBorderWidth(int i) {
        this.borderWidth = i;
        Paint paint = this.borderPaint;
        if (paint != null) {
            paint.setStrokeWidth(i);
        }
    }

    public final void setSquare(boolean z) {
        this.square = z;
    }
}
