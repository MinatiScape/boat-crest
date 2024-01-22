package com.github.mikephil.charting.utils;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.View;
/* loaded from: classes9.dex */
public class ViewPortHandler {
    public final Matrix mMatrixTouch = new Matrix();
    public RectF mContentRect = new RectF();
    public float mChartWidth = 0.0f;
    public float mChartHeight = 0.0f;

    /* renamed from: a  reason: collision with root package name */
    public float f7966a = 1.0f;
    public float b = Float.MAX_VALUE;
    public float c = 1.0f;
    public float d = Float.MAX_VALUE;
    public float e = 1.0f;
    public float f = 1.0f;
    public float g = 0.0f;
    public float h = 0.0f;
    public float i = 0.0f;
    public float j = 0.0f;
    public float[] valsBufferForFitScreen = new float[9];
    public Matrix mCenterViewPortMatrixBuffer = new Matrix();
    public final float[] matrixBuffer = new float[9];

    public boolean canZoomInMoreX() {
        return this.e < this.d;
    }

    public boolean canZoomInMoreY() {
        return this.f < this.b;
    }

    public boolean canZoomOutMoreX() {
        return this.e > this.c;
    }

    public boolean canZoomOutMoreY() {
        return this.f > this.f7966a;
    }

    public void centerViewPort(float[] fArr, View view) {
        Matrix matrix = this.mCenterViewPortMatrixBuffer;
        matrix.reset();
        matrix.set(this.mMatrixTouch);
        matrix.postTranslate(-(fArr[0] - offsetLeft()), -(fArr[1] - offsetTop()));
        refresh(matrix, view, true);
    }

    public float contentBottom() {
        return this.mContentRect.bottom;
    }

    public float contentHeight() {
        return this.mContentRect.height();
    }

    public float contentLeft() {
        return this.mContentRect.left;
    }

    public float contentRight() {
        return this.mContentRect.right;
    }

    public float contentTop() {
        return this.mContentRect.top;
    }

    public float contentWidth() {
        return this.mContentRect.width();
    }

    public Matrix fitScreen() {
        Matrix matrix = new Matrix();
        fitScreen(matrix);
        return matrix;
    }

    public float getChartHeight() {
        return this.mChartHeight;
    }

    public float getChartWidth() {
        return this.mChartWidth;
    }

    public MPPointF getContentCenter() {
        return MPPointF.getInstance(this.mContentRect.centerX(), this.mContentRect.centerY());
    }

    public RectF getContentRect() {
        return this.mContentRect;
    }

    public Matrix getMatrixTouch() {
        return this.mMatrixTouch;
    }

    public float getMaxScaleX() {
        return this.d;
    }

    public float getMaxScaleY() {
        return this.b;
    }

    public float getMinScaleX() {
        return this.c;
    }

    public float getMinScaleY() {
        return this.f7966a;
    }

    public float getScaleX() {
        return this.e;
    }

    public float getScaleY() {
        return this.f;
    }

    public float getSmallestContentExtension() {
        return Math.min(this.mContentRect.width(), this.mContentRect.height());
    }

    public float getTransX() {
        return this.g;
    }

    public float getTransY() {
        return this.h;
    }

    public boolean hasChartDimens() {
        return this.mChartHeight > 0.0f && this.mChartWidth > 0.0f;
    }

    public boolean hasNoDragOffset() {
        return this.i <= 0.0f && this.j <= 0.0f;
    }

    public boolean isFullyZoomedOut() {
        return isFullyZoomedOutX() && isFullyZoomedOutY();
    }

    public boolean isFullyZoomedOutX() {
        float f = this.e;
        float f2 = this.c;
        return f <= f2 && f2 <= 1.0f;
    }

    public boolean isFullyZoomedOutY() {
        float f = this.f;
        float f2 = this.f7966a;
        return f <= f2 && f2 <= 1.0f;
    }

    public boolean isInBounds(float f, float f2) {
        return isInBoundsX(f) && isInBoundsY(f2);
    }

    public boolean isInBoundsBottom(float f) {
        return this.mContentRect.bottom >= ((float) ((int) (f * 100.0f))) / 100.0f;
    }

    public boolean isInBoundsLeft(float f) {
        return this.mContentRect.left <= f + 1.0f;
    }

    public boolean isInBoundsRight(float f) {
        return this.mContentRect.right >= (((float) ((int) (f * 100.0f))) / 100.0f) - 1.0f;
    }

    public boolean isInBoundsTop(float f) {
        return this.mContentRect.top <= f;
    }

    public boolean isInBoundsX(float f) {
        return isInBoundsLeft(f) && isInBoundsRight(f);
    }

    public boolean isInBoundsY(float f) {
        return isInBoundsTop(f) && isInBoundsBottom(f);
    }

    public void limitTransAndScale(Matrix matrix, RectF rectF) {
        float f;
        matrix.getValues(this.matrixBuffer);
        float[] fArr = this.matrixBuffer;
        float f2 = fArr[2];
        float f3 = fArr[0];
        float f4 = fArr[5];
        float f5 = fArr[4];
        this.e = Math.min(Math.max(this.c, f3), this.d);
        this.f = Math.min(Math.max(this.f7966a, f5), this.b);
        float f6 = 0.0f;
        if (rectF != null) {
            f6 = rectF.width();
            f = rectF.height();
        } else {
            f = 0.0f;
        }
        this.g = Math.min(Math.max(f2, ((-f6) * (this.e - 1.0f)) - this.i), this.i);
        float max = Math.max(Math.min(f4, (f * (this.f - 1.0f)) + this.j), -this.j);
        this.h = max;
        float[] fArr2 = this.matrixBuffer;
        fArr2[2] = this.g;
        fArr2[0] = this.e;
        fArr2[5] = max;
        fArr2[4] = this.f;
        matrix.setValues(fArr2);
    }

    public float offsetBottom() {
        return this.mChartHeight - this.mContentRect.bottom;
    }

    public float offsetLeft() {
        return this.mContentRect.left;
    }

    public float offsetRight() {
        return this.mChartWidth - this.mContentRect.right;
    }

    public float offsetTop() {
        return this.mContentRect.top;
    }

    public Matrix refresh(Matrix matrix, View view, boolean z) {
        this.mMatrixTouch.set(matrix);
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
        if (z) {
            view.invalidate();
        }
        matrix.set(this.mMatrixTouch);
        return matrix;
    }

    public void resetZoom(Matrix matrix) {
        matrix.reset();
        matrix.set(this.mMatrixTouch);
        matrix.postScale(1.0f, 1.0f, 0.0f, 0.0f);
    }

    public void restrainViewPort(float f, float f2, float f3, float f4) {
        this.mContentRect.set(f, f2, this.mChartWidth - f3, this.mChartHeight - f4);
    }

    public void setChartDimens(float f, float f2) {
        float offsetLeft = offsetLeft();
        float offsetTop = offsetTop();
        float offsetRight = offsetRight();
        float offsetBottom = offsetBottom();
        this.mChartHeight = f2;
        this.mChartWidth = f;
        restrainViewPort(offsetLeft, offsetTop, offsetRight, offsetBottom);
    }

    public void setDragOffsetX(float f) {
        this.i = Utils.convertDpToPixel(f);
    }

    public void setDragOffsetY(float f) {
        this.j = Utils.convertDpToPixel(f);
    }

    public void setMaximumScaleX(float f) {
        if (f == 0.0f) {
            f = Float.MAX_VALUE;
        }
        this.d = f;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public void setMaximumScaleY(float f) {
        if (f == 0.0f) {
            f = Float.MAX_VALUE;
        }
        this.b = f;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public void setMinMaxScaleX(float f, float f2) {
        if (f < 1.0f) {
            f = 1.0f;
        }
        if (f2 == 0.0f) {
            f2 = Float.MAX_VALUE;
        }
        this.c = f;
        this.d = f2;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public void setMinMaxScaleY(float f, float f2) {
        if (f < 1.0f) {
            f = 1.0f;
        }
        if (f2 == 0.0f) {
            f2 = Float.MAX_VALUE;
        }
        this.f7966a = f;
        this.b = f2;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public void setMinimumScaleX(float f) {
        if (f < 1.0f) {
            f = 1.0f;
        }
        this.c = f;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public void setMinimumScaleY(float f) {
        if (f < 1.0f) {
            f = 1.0f;
        }
        this.f7966a = f;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public Matrix setZoom(float f, float f2) {
        Matrix matrix = new Matrix();
        setZoom(f, f2, matrix);
        return matrix;
    }

    public Matrix translate(float[] fArr) {
        Matrix matrix = new Matrix();
        translate(fArr, matrix);
        return matrix;
    }

    public Matrix zoom(float f, float f2) {
        Matrix matrix = new Matrix();
        zoom(f, f2, matrix);
        return matrix;
    }

    public Matrix zoomIn(float f, float f2) {
        Matrix matrix = new Matrix();
        zoomIn(f, f2, matrix);
        return matrix;
    }

    public Matrix zoomOut(float f, float f2) {
        Matrix matrix = new Matrix();
        zoomOut(f, f2, matrix);
        return matrix;
    }

    public void fitScreen(Matrix matrix) {
        this.c = 1.0f;
        this.f7966a = 1.0f;
        matrix.set(this.mMatrixTouch);
        float[] fArr = this.valsBufferForFitScreen;
        for (int i = 0; i < 9; i++) {
            fArr[i] = 0.0f;
        }
        matrix.getValues(fArr);
        fArr[2] = 0.0f;
        fArr[5] = 0.0f;
        fArr[0] = 1.0f;
        fArr[4] = 1.0f;
        matrix.setValues(fArr);
    }

    public void setZoom(float f, float f2, Matrix matrix) {
        matrix.reset();
        matrix.set(this.mMatrixTouch);
        matrix.setScale(f, f2);
    }

    public void translate(float[] fArr, Matrix matrix) {
        matrix.reset();
        matrix.set(this.mMatrixTouch);
        matrix.postTranslate(-(fArr[0] - offsetLeft()), -(fArr[1] - offsetTop()));
    }

    public void zoom(float f, float f2, Matrix matrix) {
        matrix.reset();
        matrix.set(this.mMatrixTouch);
        matrix.postScale(f, f2);
    }

    public void zoomIn(float f, float f2, Matrix matrix) {
        matrix.reset();
        matrix.set(this.mMatrixTouch);
        matrix.postScale(1.4f, 1.4f, f, f2);
    }

    public void zoomOut(float f, float f2, Matrix matrix) {
        matrix.reset();
        matrix.set(this.mMatrixTouch);
        matrix.postScale(0.7f, 0.7f, f, f2);
    }

    public Matrix setZoom(float f, float f2, float f3, float f4) {
        Matrix matrix = new Matrix();
        matrix.set(this.mMatrixTouch);
        matrix.setScale(f, f2, f3, f4);
        return matrix;
    }

    public Matrix zoom(float f, float f2, float f3, float f4) {
        Matrix matrix = new Matrix();
        zoom(f, f2, f3, f4, matrix);
        return matrix;
    }

    public void zoom(float f, float f2, float f3, float f4, Matrix matrix) {
        matrix.reset();
        matrix.set(this.mMatrixTouch);
        matrix.postScale(f, f2, f3, f4);
    }
}
