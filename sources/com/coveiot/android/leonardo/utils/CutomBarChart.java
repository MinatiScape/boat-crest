package com.coveiot.android.leonardo.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import com.coveiot.android.leonardo.dashboard.health.model.WalkData;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.renderer.BarChartRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes5.dex */
public class CutomBarChart extends BarChartRenderer {
    public final Bitmap b;
    public RectF c;
    public int d;

    public CutomBarChart(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler, Bitmap bitmap) {
        super(barDataProvider, chartAnimator, viewPortHandler);
        this.c = new RectF();
        this.b = bitmap;
    }

    public final Path a(RectF rectF, float f, float f2, boolean z, boolean z2, boolean z3, boolean z4) {
        float f3 = rectF.top;
        float f4 = rectF.left;
        float f5 = rectF.right;
        float f6 = rectF.bottom;
        Path path = new Path();
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        float f7 = f5 - f4;
        float f8 = f6 - f3;
        float f9 = f7 / 2.0f;
        if (f > f9) {
            f = f9;
        }
        float f10 = f8 / 2.0f;
        if (f2 > f10) {
            f2 = f10;
        }
        float f11 = f7 - (f * 2.0f);
        float f12 = f8 - (2.0f * f2);
        path.moveTo(f5, f3 + f2);
        if (z2) {
            float f13 = -f2;
            path.rQuadTo(0.0f, f13, -f, f13);
        } else {
            path.rLineTo(0.0f, -f2);
            path.rLineTo(-f, 0.0f);
        }
        path.rLineTo(-f11, 0.0f);
        if (z) {
            float f14 = -f;
            path.rQuadTo(f14, 0.0f, f14, f2);
        } else {
            path.rLineTo(-f, 0.0f);
            path.rLineTo(0.0f, f2);
        }
        path.rLineTo(0.0f, f12);
        if (z4) {
            path.rQuadTo(0.0f, f2, f, f2);
        } else {
            path.rLineTo(0.0f, f2);
            path.rLineTo(f, 0.0f);
        }
        path.rLineTo(f11, 0.0f);
        if (z3) {
            path.rQuadTo(f, 0.0f, f, -f2);
        } else {
            path.rLineTo(f, 0.0f);
            path.rLineTo(0.0f, -f2);
        }
        path.rLineTo(0.0f, -f12);
        path.close();
        return path;
    }

    public final Bitmap b(BarBuffer barBuffer) {
        float[] fArr = barBuffer.buffer;
        int ceil = (int) Math.ceil(fArr[2] - fArr[0]);
        return Bitmap.createScaledBitmap(this.b, ceil, ceil, false);
    }

    public void drawBarImages(Canvas canvas, IBarDataSet iBarDataSet, int i) {
        BarBuffer barBuffer = this.mBarBuffers[i];
        Bitmap b = b(barBuffer);
        int width = b.getWidth();
        int i2 = width / 2;
        for (int i3 = 0; i3 < barBuffer.buffer.length * this.mAnimator.getPhaseX(); i3 += 4) {
            float[] fArr = barBuffer.buffer;
            float f = fArr[i3];
            float f2 = fArr[i3 + 2];
            float f3 = fArr[i3 + 1];
            float f4 = (f + f2) / 2.0f;
            float f5 = fArr[i3 + 3] - width;
            if (!this.mViewPortHandler.isInBoundsRight(f4)) {
                return;
            }
            if (this.mViewPortHandler.isInBoundsY(f5) && this.mViewPortHandler.isInBoundsLeft(f4)) {
                BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForIndex(i3 / 4);
                if (barEntry.getData() instanceof WalkData) {
                    WalkData walkData = (WalkData) barEntry.getData();
                    if (walkData.getSteps() >= walkData.getStepsTarget()) {
                        drawImage(canvas, b, f4 - i2, f5);
                    }
                }
                barEntry.getY();
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        initBuffers();
        super.drawData(canvas);
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    public void drawDataSet(Canvas canvas, IBarDataSet iBarDataSet, int i) {
        Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
        this.mBarBorderPaint.setColor(iBarDataSet.getBarBorderColor());
        this.mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(iBarDataSet.getBarBorderWidth()));
        this.mShadowPaint.setColor(iBarDataSet.getBarShadowColor());
        boolean z = iBarDataSet.getBarBorderWidth() > 0.0f;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        if (this.mChart.isDrawBarShadowEnabled()) {
            this.mShadowPaint.setColor(iBarDataSet.getBarShadowColor());
            float barWidth = this.mChart.getBarData().getBarWidth() / 2.0f;
            double min = Math.min(Math.ceil((int) (iBarDataSet.getEntryCount() * phaseX)), iBarDataSet.getEntryCount());
            for (int i2 = 0; i2 < min; i2++) {
                float x = ((BarEntry) iBarDataSet.getEntryForIndex(i2)).getX();
                RectF rectF = this.c;
                rectF.left = x - barWidth;
                rectF.right = x + barWidth;
                transformer.rectValueToPixel(rectF);
                if (this.mViewPortHandler.isInBoundsLeft(this.c.right)) {
                    if (!this.mViewPortHandler.isInBoundsRight(this.c.left)) {
                        break;
                    }
                    this.c.top = this.mViewPortHandler.contentTop();
                    this.c.bottom = this.mViewPortHandler.contentBottom();
                    RectF rectF2 = this.mBarRect;
                    int i3 = this.d;
                    canvas.drawRoundRect(rectF2, i3, i3, this.mShadowPaint);
                }
            }
        }
        BarBuffer barBuffer = this.mBarBuffers[i];
        barBuffer.setPhases(phaseX, phaseY);
        barBuffer.setDataSet(i);
        barBuffer.setInverted(this.mChart.isInverted(iBarDataSet.getAxisDependency()));
        barBuffer.setBarWidth(this.mChart.getBarData().getBarWidth());
        barBuffer.feed(iBarDataSet);
        transformer.pointValuesToPixel(barBuffer.buffer);
        boolean z2 = iBarDataSet.getColors().size() == 1;
        if (z2) {
            this.mRenderPaint.setColor(iBarDataSet.getColor());
        }
        for (int i4 = 0; i4 < barBuffer.size(); i4 += 4) {
            int i5 = i4 + 2;
            if (this.mViewPortHandler.isInBoundsLeft(barBuffer.buffer[i5])) {
                if (!this.mViewPortHandler.isInBoundsRight(barBuffer.buffer[i4])) {
                    break;
                }
                if (!z2) {
                    this.mRenderPaint.setColor(iBarDataSet.getColor(i4 / 4));
                }
                if (iBarDataSet.getGradientColor() != null) {
                    GradientColor gradientColor = iBarDataSet.getGradientColor();
                    Paint paint = this.mRenderPaint;
                    float[] fArr = barBuffer.buffer;
                    paint.setShader(new LinearGradient(fArr[i4], fArr[i4 + 3], fArr[i4], fArr[i4 + 1], gradientColor.getStartColor(), gradientColor.getEndColor(), Shader.TileMode.MIRROR));
                }
                if (iBarDataSet.getGradientColors() != null) {
                    Paint paint2 = this.mRenderPaint;
                    float[] fArr2 = barBuffer.buffer;
                    float f = fArr2[i4];
                    float f2 = fArr2[i4 + 3];
                    float f3 = fArr2[i4];
                    float f4 = fArr2[i4 + 1];
                    int i6 = i4 / 4;
                    paint2.setShader(new LinearGradient(f, f2, f3, f4, iBarDataSet.getGradientColor(i6).getStartColor(), iBarDataSet.getGradientColor(i6).getEndColor(), Shader.TileMode.MIRROR));
                }
                float[] fArr3 = barBuffer.buffer;
                int i7 = i4 + 1;
                int i8 = i4 + 3;
                RectF rectF3 = new RectF(fArr3[i4], fArr3[i7], fArr3[i5], fArr3[i8]);
                int i9 = this.d;
                canvas.drawPath(a(rectF3, i9, i9, true, true, false, false), this.mRenderPaint);
                if (z) {
                    float[] fArr4 = barBuffer.buffer;
                    RectF rectF4 = new RectF(fArr4[i4], fArr4[i7], fArr4[i5], fArr4[i8]);
                    int i10 = this.d;
                    canvas.drawPath(a(rectF4, i10, i10, true, true, false, false), this.mBarBorderPaint);
                }
            }
        }
        if (this.b != null) {
            drawBarImages(canvas, iBarDataSet, i);
        }
    }

    public void drawImage(Canvas canvas, Bitmap bitmap, float f, float f2) {
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, f, f2, (Paint) null);
        }
    }

    public void setRadius(int i) {
        this.d = i;
    }
}
