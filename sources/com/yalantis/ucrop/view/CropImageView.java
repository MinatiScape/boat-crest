package com.yalantis.ucrop.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.yalantis.ucrop.R;
import com.yalantis.ucrop.callback.BitmapCropCallback;
import com.yalantis.ucrop.callback.CropBoundsChangeListener;
import com.yalantis.ucrop.model.CropParameters;
import com.yalantis.ucrop.model.ImageState;
import com.yalantis.ucrop.task.BitmapCropTask;
import com.yalantis.ucrop.util.CubicEasing;
import com.yalantis.ucrop.util.RectUtils;
import com.yalantis.ucrop.view.TransformImageView;
import java.lang.ref.WeakReference;
import java.util.Arrays;
/* loaded from: classes12.dex */
public class CropImageView extends TransformImageView {
    public static final float DEFAULT_ASPECT_RATIO = 0.0f;
    public static final int DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION = 500;
    public static final int DEFAULT_MAX_BITMAP_SIZE = 0;
    public static final float DEFAULT_MAX_SCALE_MULTIPLIER = 10.0f;
    public static final float SOURCE_IMAGE_ASPECT_RATIO = 0.0f;
    public final RectF o;
    public final Matrix p;
    public float q;
    public float r;
    public CropBoundsChangeListener s;
    public Runnable t;
    public Runnable u;
    public float v;
    public float w;
    public int x;
    public int y;
    public long z;

    /* loaded from: classes12.dex */
    public static class a implements Runnable {
        public final WeakReference<CropImageView> h;
        public final long i;
        public final long j = System.currentTimeMillis();
        public final float k;
        public final float l;
        public final float m;
        public final float n;
        public final float o;
        public final float p;
        public final boolean q;

        public a(CropImageView cropImageView, long j, float f, float f2, float f3, float f4, float f5, float f6, boolean z) {
            this.h = new WeakReference<>(cropImageView);
            this.i = j;
            this.k = f;
            this.l = f2;
            this.m = f3;
            this.n = f4;
            this.o = f5;
            this.p = f6;
            this.q = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            CropImageView cropImageView = this.h.get();
            if (cropImageView == null) {
                return;
            }
            float min = (float) Math.min(this.i, System.currentTimeMillis() - this.j);
            float easeOut = CubicEasing.easeOut(min, 0.0f, this.m, (float) this.i);
            float easeOut2 = CubicEasing.easeOut(min, 0.0f, this.n, (float) this.i);
            float easeInOut = CubicEasing.easeInOut(min, 0.0f, this.p, (float) this.i);
            if (min < ((float) this.i)) {
                float[] fArr = cropImageView.mCurrentImageCenter;
                cropImageView.postTranslate(easeOut - (fArr[0] - this.k), easeOut2 - (fArr[1] - this.l));
                if (!this.q) {
                    cropImageView.zoomInImage(this.o + easeInOut, cropImageView.o.centerX(), cropImageView.o.centerY());
                }
                if (cropImageView.isImageWrapCropBounds()) {
                    return;
                }
                cropImageView.post(this);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static class b implements Runnable {
        public final WeakReference<CropImageView> h;
        public final long i;
        public final long j = System.currentTimeMillis();
        public final float k;
        public final float l;
        public final float m;
        public final float n;

        public b(CropImageView cropImageView, long j, float f, float f2, float f3, float f4) {
            this.h = new WeakReference<>(cropImageView);
            this.i = j;
            this.k = f;
            this.l = f2;
            this.m = f3;
            this.n = f4;
        }

        @Override // java.lang.Runnable
        public void run() {
            CropImageView cropImageView = this.h.get();
            if (cropImageView == null) {
                return;
            }
            float min = (float) Math.min(this.i, System.currentTimeMillis() - this.j);
            float easeInOut = CubicEasing.easeInOut(min, 0.0f, this.l, (float) this.i);
            if (min < ((float) this.i)) {
                cropImageView.zoomInImage(this.k + easeInOut, this.m, this.n);
                cropImageView.post(this);
                return;
            }
            cropImageView.setImageToWrapCropBounds();
        }
    }

    public CropImageView(Context context) {
        this(context, null);
    }

    public void cancelAllAnimations() {
        removeCallbacks(this.t);
        removeCallbacks(this.u);
    }

    public void cropAndSaveImage(@NonNull Bitmap.CompressFormat compressFormat, int i, @Nullable BitmapCropCallback bitmapCropCallback) {
        cancelAllAnimations();
        setImageToWrapCropBounds(false);
        new BitmapCropTask(getContext(), getViewBitmap(), new ImageState(this.o, RectUtils.trapToRect(this.mCurrentImageCorners), getCurrentScale(), getCurrentAngle()), new CropParameters(this.x, this.y, compressFormat, i, getImageInputPath(), getImageOutputPath(), getExifInfo()), bitmapCropCallback).execute(new Void[0]);
    }

    public final float[] f() {
        this.p.reset();
        this.p.setRotate(-getCurrentAngle());
        float[] fArr = this.mCurrentImageCorners;
        float[] copyOf = Arrays.copyOf(fArr, fArr.length);
        float[] cornersFromRect = RectUtils.getCornersFromRect(this.o);
        this.p.mapPoints(copyOf);
        this.p.mapPoints(cornersFromRect);
        RectF trapToRect = RectUtils.trapToRect(copyOf);
        RectF trapToRect2 = RectUtils.trapToRect(cornersFromRect);
        float f = trapToRect.left - trapToRect2.left;
        float f2 = trapToRect.top - trapToRect2.top;
        float f3 = trapToRect.right - trapToRect2.right;
        float f4 = trapToRect.bottom - trapToRect2.bottom;
        float[] fArr2 = new float[4];
        if (f <= 0.0f) {
            f = 0.0f;
        }
        fArr2[0] = f;
        if (f2 <= 0.0f) {
            f2 = 0.0f;
        }
        fArr2[1] = f2;
        if (f3 >= 0.0f) {
            f3 = 0.0f;
        }
        fArr2[2] = f3;
        if (f4 >= 0.0f) {
            f4 = 0.0f;
        }
        fArr2[3] = f4;
        this.p.reset();
        this.p.setRotate(getCurrentAngle());
        this.p.mapPoints(fArr2);
        return fArr2;
    }

    public final void g() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        h(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }

    @Nullable
    public CropBoundsChangeListener getCropBoundsChangeListener() {
        return this.s;
    }

    public float getMaxScale() {
        return this.v;
    }

    public float getMinScale() {
        return this.w;
    }

    public float getTargetAspectRatio() {
        return this.q;
    }

    public final void h(float f, float f2) {
        float min = Math.min(Math.min(this.o.width() / f, this.o.width() / f2), Math.min(this.o.height() / f2, this.o.height() / f));
        this.w = min;
        this.v = min * this.r;
    }

    public final void i(float f, float f2) {
        float width = this.o.width();
        float height = this.o.height();
        float max = Math.max(this.o.width() / f, this.o.height() / f2);
        RectF rectF = this.o;
        float f3 = ((height - (f2 * max)) / 2.0f) + rectF.top;
        this.mCurrentImageMatrix.reset();
        this.mCurrentImageMatrix.postScale(max, max);
        this.mCurrentImageMatrix.postTranslate(((width - (f * max)) / 2.0f) + rectF.left, f3);
        setImageMatrix(this.mCurrentImageMatrix);
    }

    public boolean isImageWrapCropBounds() {
        return isImageWrapCropBounds(this.mCurrentImageCorners);
    }

    @Override // com.yalantis.ucrop.view.TransformImageView
    public void onImageLaidOut() {
        int i;
        int i2;
        int i3;
        super.onImageLaidOut();
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        float intrinsicWidth = drawable.getIntrinsicWidth();
        float intrinsicHeight = drawable.getIntrinsicHeight();
        if (this.q == 0.0f) {
            this.q = intrinsicWidth / intrinsicHeight;
        }
        int i4 = this.mThisWidth;
        float f = this.q;
        int i5 = (int) (i4 / f);
        int i6 = this.mThisHeight;
        if (i5 > i6) {
            this.o.set((i4 - ((int) (i6 * f))) / 2, 0.0f, i2 + i3, i6);
        } else {
            this.o.set(0.0f, (i6 - i5) / 2, i4, i5 + i);
        }
        h(intrinsicWidth, intrinsicHeight);
        i(intrinsicWidth, intrinsicHeight);
        CropBoundsChangeListener cropBoundsChangeListener = this.s;
        if (cropBoundsChangeListener != null) {
            cropBoundsChangeListener.onCropAspectRatioChanged(this.q);
        }
        TransformImageView.TransformImageListener transformImageListener = this.mTransformImageListener;
        if (transformImageListener != null) {
            transformImageListener.onScale(getCurrentScale());
            this.mTransformImageListener.onRotate(getCurrentAngle());
        }
    }

    public void postRotate(float f) {
        postRotate(f, this.o.centerX(), this.o.centerY());
    }

    @Override // com.yalantis.ucrop.view.TransformImageView
    public void postScale(float f, float f2, float f3) {
        if (f > 1.0f && getCurrentScale() * f <= getMaxScale()) {
            super.postScale(f, f2, f3);
        } else if (f >= 1.0f || getCurrentScale() * f < getMinScale()) {
        } else {
            super.postScale(f, f2, f3);
        }
    }

    public void processStyledAttributes(@NonNull TypedArray typedArray) {
        float abs = Math.abs(typedArray.getFloat(R.styleable.ucrop_UCropView_ucrop_aspect_ratio_x, 0.0f));
        float abs2 = Math.abs(typedArray.getFloat(R.styleable.ucrop_UCropView_ucrop_aspect_ratio_y, 0.0f));
        if (abs != 0.0f && abs2 != 0.0f) {
            this.q = abs / abs2;
        } else {
            this.q = 0.0f;
        }
    }

    public void setCropBoundsChangeListener(@Nullable CropBoundsChangeListener cropBoundsChangeListener) {
        this.s = cropBoundsChangeListener;
    }

    public void setCropRect(RectF rectF) {
        this.q = rectF.width() / rectF.height();
        this.o.set(rectF.left - getPaddingLeft(), rectF.top - getPaddingTop(), rectF.right - getPaddingRight(), rectF.bottom - getPaddingBottom());
        g();
        setImageToWrapCropBounds();
    }

    public void setImageToWrapCropBounds() {
        setImageToWrapCropBounds(true);
    }

    public void setImageToWrapCropBoundsAnimDuration(@IntRange(from = 100) long j) {
        if (j > 0) {
            this.z = j;
            return;
        }
        throw new IllegalArgumentException("Animation duration cannot be negative value.");
    }

    public void setMaxResultImageSizeX(@IntRange(from = 10) int i) {
        this.x = i;
    }

    public void setMaxResultImageSizeY(@IntRange(from = 10) int i) {
        this.y = i;
    }

    public void setMaxScaleMultiplier(float f) {
        this.r = f;
    }

    public void setTargetAspectRatio(float f) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            this.q = f;
            return;
        }
        if (f == 0.0f) {
            this.q = drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight();
        } else {
            this.q = f;
        }
        CropBoundsChangeListener cropBoundsChangeListener = this.s;
        if (cropBoundsChangeListener != null) {
            cropBoundsChangeListener.onCropAspectRatioChanged(this.q);
        }
    }

    public void zoomImageToPosition(float f, float f2, float f3, long j) {
        if (f > getMaxScale()) {
            f = getMaxScale();
        }
        float currentScale = getCurrentScale();
        b bVar = new b(this, j, currentScale, f - currentScale, f2, f3);
        this.u = bVar;
        post(bVar);
    }

    public void zoomInImage(float f) {
        zoomInImage(f, this.o.centerX(), this.o.centerY());
    }

    public void zoomOutImage(float f) {
        zoomOutImage(f, this.o.centerX(), this.o.centerY());
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public boolean isImageWrapCropBounds(float[] fArr) {
        this.p.reset();
        this.p.setRotate(-getCurrentAngle());
        float[] copyOf = Arrays.copyOf(fArr, fArr.length);
        this.p.mapPoints(copyOf);
        float[] cornersFromRect = RectUtils.getCornersFromRect(this.o);
        this.p.mapPoints(cornersFromRect);
        return RectUtils.trapToRect(copyOf).contains(RectUtils.trapToRect(cornersFromRect));
    }

    public void setImageToWrapCropBounds(boolean z) {
        float f;
        float max;
        float f2;
        if (!this.mBitmapLaidOut || isImageWrapCropBounds()) {
            return;
        }
        float[] fArr = this.mCurrentImageCenter;
        float f3 = fArr[0];
        float f4 = fArr[1];
        float currentScale = getCurrentScale();
        float centerX = this.o.centerX() - f3;
        float centerY = this.o.centerY() - f4;
        this.p.reset();
        this.p.setTranslate(centerX, centerY);
        float[] fArr2 = this.mCurrentImageCorners;
        float[] copyOf = Arrays.copyOf(fArr2, fArr2.length);
        this.p.mapPoints(copyOf);
        boolean isImageWrapCropBounds = isImageWrapCropBounds(copyOf);
        if (isImageWrapCropBounds) {
            float[] f5 = f();
            f2 = -(f5[1] + f5[3]);
            f = -(f5[0] + f5[2]);
            max = 0.0f;
        } else {
            RectF rectF = new RectF(this.o);
            this.p.reset();
            this.p.setRotate(getCurrentAngle());
            this.p.mapRect(rectF);
            float[] rectSidesFromCorners = RectUtils.getRectSidesFromCorners(this.mCurrentImageCorners);
            f = centerX;
            max = (Math.max(rectF.width() / rectSidesFromCorners[0], rectF.height() / rectSidesFromCorners[1]) * currentScale) - currentScale;
            f2 = centerY;
        }
        if (z) {
            a aVar = new a(this, this.z, f3, f4, f, f2, currentScale, max, isImageWrapCropBounds);
            this.t = aVar;
            post(aVar);
            return;
        }
        postTranslate(f, f2);
        if (isImageWrapCropBounds) {
            return;
        }
        zoomInImage(currentScale + max, this.o.centerX(), this.o.centerY());
    }

    public void zoomInImage(float f, float f2, float f3) {
        if (f <= getMaxScale()) {
            postScale(f / getCurrentScale(), f2, f3);
        }
    }

    public void zoomOutImage(float f, float f2, float f3) {
        if (f >= getMinScale()) {
            postScale(f / getCurrentScale(), f2, f3);
        }
    }

    public CropImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.o = new RectF();
        this.p = new Matrix();
        this.r = 10.0f;
        this.u = null;
        this.x = 0;
        this.y = 0;
        this.z = 500L;
    }
}
