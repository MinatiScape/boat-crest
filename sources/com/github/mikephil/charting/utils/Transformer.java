package com.github.mikephil.charting.utils;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import java.util.List;
/* loaded from: classes9.dex */
public class Transformer {
    public ViewPortHandler mViewPortHandler;
    public Matrix mMatrixValueToPx = new Matrix();
    public Matrix mMatrixOffset = new Matrix();
    public float[] valuePointsForGenerateTransformedValuesScatter = new float[1];
    public float[] valuePointsForGenerateTransformedValuesBubble = new float[1];
    public float[] valuePointsForGenerateTransformedValuesLine = new float[1];
    public float[] valuePointsForGenerateTransformedValuesCandle = new float[1];
    public Matrix mPixelToValueMatrixBuffer = new Matrix();

    /* renamed from: a  reason: collision with root package name */
    public float[] f7964a = new float[2];
    public Matrix b = new Matrix();
    public Matrix c = new Matrix();

    public Transformer(ViewPortHandler viewPortHandler) {
        this.mViewPortHandler = viewPortHandler;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    public float[] generateTransformedValuesBubble(IBubbleDataSet iBubbleDataSet, float f, int i, int i2) {
        int i3 = ((i2 - i) + 1) * 2;
        if (this.valuePointsForGenerateTransformedValuesBubble.length != i3) {
            this.valuePointsForGenerateTransformedValuesBubble = new float[i3];
        }
        float[] fArr = this.valuePointsForGenerateTransformedValuesBubble;
        for (int i4 = 0; i4 < i3; i4 += 2) {
            ?? entryForIndex = iBubbleDataSet.getEntryForIndex((i4 / 2) + i);
            if (entryForIndex != 0) {
                fArr[i4] = entryForIndex.getX();
                fArr[i4 + 1] = entryForIndex.getY() * f;
            } else {
                fArr[i4] = 0.0f;
                fArr[i4 + 1] = 0.0f;
            }
        }
        getValueToPixelMatrix().mapPoints(fArr);
        return fArr;
    }

    public float[] generateTransformedValuesCandle(ICandleDataSet iCandleDataSet, float f, float f2, int i, int i2) {
        int i3 = ((int) (((i2 - i) * f) + 1.0f)) * 2;
        if (this.valuePointsForGenerateTransformedValuesCandle.length != i3) {
            this.valuePointsForGenerateTransformedValuesCandle = new float[i3];
        }
        float[] fArr = this.valuePointsForGenerateTransformedValuesCandle;
        for (int i4 = 0; i4 < i3; i4 += 2) {
            CandleEntry candleEntry = (CandleEntry) iCandleDataSet.getEntryForIndex((i4 / 2) + i);
            if (candleEntry != null) {
                fArr[i4] = candleEntry.getX();
                fArr[i4 + 1] = candleEntry.getHigh() * f2;
            } else {
                fArr[i4] = 0.0f;
                fArr[i4 + 1] = 0.0f;
            }
        }
        getValueToPixelMatrix().mapPoints(fArr);
        return fArr;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    public float[] generateTransformedValuesLine(ILineDataSet iLineDataSet, float f, float f2, int i, int i2) {
        int i3 = (((int) ((i2 - i) * f)) + 1) * 2;
        if (this.valuePointsForGenerateTransformedValuesLine.length != i3) {
            this.valuePointsForGenerateTransformedValuesLine = new float[i3];
        }
        float[] fArr = this.valuePointsForGenerateTransformedValuesLine;
        for (int i4 = 0; i4 < i3; i4 += 2) {
            ?? entryForIndex = iLineDataSet.getEntryForIndex((i4 / 2) + i);
            if (entryForIndex != 0) {
                fArr[i4] = entryForIndex.getX();
                fArr[i4 + 1] = entryForIndex.getY() * f2;
            } else {
                fArr[i4] = 0.0f;
                fArr[i4 + 1] = 0.0f;
            }
        }
        getValueToPixelMatrix().mapPoints(fArr);
        return fArr;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.github.mikephil.charting.data.Entry, com.github.mikephil.charting.data.BaseEntry] */
    public float[] generateTransformedValuesScatter(IScatterDataSet iScatterDataSet, float f, float f2, int i, int i2) {
        int i3 = ((int) (((i2 - i) * f) + 1.0f)) * 2;
        if (this.valuePointsForGenerateTransformedValuesScatter.length != i3) {
            this.valuePointsForGenerateTransformedValuesScatter = new float[i3];
        }
        float[] fArr = this.valuePointsForGenerateTransformedValuesScatter;
        for (int i4 = 0; i4 < i3; i4 += 2) {
            ?? entryForIndex = iScatterDataSet.getEntryForIndex((i4 / 2) + i);
            if (entryForIndex != 0) {
                fArr[i4] = entryForIndex.getX();
                fArr[i4 + 1] = entryForIndex.getY() * f2;
            } else {
                fArr[i4] = 0.0f;
                fArr[i4 + 1] = 0.0f;
            }
        }
        getValueToPixelMatrix().mapPoints(fArr);
        return fArr;
    }

    public Matrix getOffsetMatrix() {
        return this.mMatrixOffset;
    }

    public MPPointD getPixelForValues(float f, float f2) {
        float[] fArr = this.f7964a;
        fArr[0] = f;
        fArr[1] = f2;
        pointValuesToPixel(fArr);
        float[] fArr2 = this.f7964a;
        return MPPointD.getInstance(fArr2[0], fArr2[1]);
    }

    public Matrix getPixelToValueMatrix() {
        getValueToPixelMatrix().invert(this.c);
        return this.c;
    }

    public Matrix getValueMatrix() {
        return this.mMatrixValueToPx;
    }

    public Matrix getValueToPixelMatrix() {
        this.b.set(this.mMatrixValueToPx);
        this.b.postConcat(this.mViewPortHandler.mMatrixTouch);
        this.b.postConcat(this.mMatrixOffset);
        return this.b;
    }

    public MPPointD getValuesByTouchPoint(float f, float f2) {
        MPPointD mPPointD = MPPointD.getInstance(0.0d, 0.0d);
        getValuesByTouchPoint(f, f2, mPPointD);
        return mPPointD;
    }

    public void pathValueToPixel(Path path) {
        path.transform(this.mMatrixValueToPx);
        path.transform(this.mViewPortHandler.getMatrixTouch());
        path.transform(this.mMatrixOffset);
    }

    public void pathValuesToPixel(List<Path> list) {
        for (int i = 0; i < list.size(); i++) {
            pathValueToPixel(list.get(i));
        }
    }

    public void pixelsToValue(float[] fArr) {
        Matrix matrix = this.mPixelToValueMatrixBuffer;
        matrix.reset();
        this.mMatrixOffset.invert(matrix);
        matrix.mapPoints(fArr);
        this.mViewPortHandler.getMatrixTouch().invert(matrix);
        matrix.mapPoints(fArr);
        this.mMatrixValueToPx.invert(matrix);
        matrix.mapPoints(fArr);
    }

    public void pointValuesToPixel(float[] fArr) {
        this.mMatrixValueToPx.mapPoints(fArr);
        this.mViewPortHandler.getMatrixTouch().mapPoints(fArr);
        this.mMatrixOffset.mapPoints(fArr);
    }

    public void prepareMatrixOffset(boolean z) {
        this.mMatrixOffset.reset();
        if (!z) {
            this.mMatrixOffset.postTranslate(this.mViewPortHandler.offsetLeft(), this.mViewPortHandler.getChartHeight() - this.mViewPortHandler.offsetBottom());
            return;
        }
        this.mMatrixOffset.setTranslate(this.mViewPortHandler.offsetLeft(), -this.mViewPortHandler.offsetTop());
        this.mMatrixOffset.postScale(1.0f, -1.0f);
    }

    public void prepareMatrixValuePx(float f, float f2, float f3, float f4) {
        float contentWidth = this.mViewPortHandler.contentWidth() / f2;
        float contentHeight = this.mViewPortHandler.contentHeight() / f3;
        if (Float.isInfinite(contentWidth)) {
            contentWidth = 0.0f;
        }
        if (Float.isInfinite(contentHeight)) {
            contentHeight = 0.0f;
        }
        this.mMatrixValueToPx.reset();
        this.mMatrixValueToPx.postTranslate(-f, -f4);
        this.mMatrixValueToPx.postScale(contentWidth, -contentHeight);
    }

    public void rectToPixelPhase(RectF rectF, float f) {
        rectF.top *= f;
        rectF.bottom *= f;
        this.mMatrixValueToPx.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }

    public void rectToPixelPhaseHorizontal(RectF rectF, float f) {
        rectF.left *= f;
        rectF.right *= f;
        this.mMatrixValueToPx.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }

    public void rectValueToPixel(RectF rectF) {
        this.mMatrixValueToPx.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }

    public void rectValueToPixelHorizontal(RectF rectF) {
        this.mMatrixValueToPx.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }

    public void rectValuesToPixel(List<RectF> list) {
        Matrix valueToPixelMatrix = getValueToPixelMatrix();
        for (int i = 0; i < list.size(); i++) {
            valueToPixelMatrix.mapRect(list.get(i));
        }
    }

    public void getValuesByTouchPoint(float f, float f2, MPPointD mPPointD) {
        float[] fArr = this.f7964a;
        fArr[0] = f;
        fArr[1] = f2;
        pixelsToValue(fArr);
        float[] fArr2 = this.f7964a;
        mPPointD.x = fArr2[0];
        mPPointD.y = fArr2[1];
    }

    public void rectValueToPixelHorizontal(RectF rectF, float f) {
        rectF.left *= f;
        rectF.right *= f;
        this.mMatrixValueToPx.mapRect(rectF);
        this.mViewPortHandler.getMatrixTouch().mapRect(rectF);
        this.mMatrixOffset.mapRect(rectF);
    }
}
