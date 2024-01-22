package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.buffer.HorizontalBarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.List;
/* loaded from: classes9.dex */
public class HorizontalBarChartRenderer extends BarChartRenderer {
    public RectF b;

    public HorizontalBarChartRenderer(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(barDataProvider, chartAnimator, viewPortHandler);
        this.b = new RectF();
        this.mValuePaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    public void drawDataSet(Canvas canvas, IBarDataSet iBarDataSet, int i) {
        Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
        this.mBarBorderPaint.setColor(iBarDataSet.getBarBorderColor());
        this.mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(iBarDataSet.getBarBorderWidth()));
        boolean z = iBarDataSet.getBarBorderWidth() > 0.0f;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        if (this.mChart.isDrawBarShadowEnabled()) {
            this.mShadowPaint.setColor(iBarDataSet.getBarShadowColor());
            float barWidth = this.mChart.getBarData().getBarWidth() / 2.0f;
            int min = Math.min((int) Math.ceil(iBarDataSet.getEntryCount() * phaseX), iBarDataSet.getEntryCount());
            for (int i2 = 0; i2 < min; i2++) {
                float x = ((BarEntry) iBarDataSet.getEntryForIndex(i2)).getX();
                RectF rectF = this.b;
                rectF.top = x - barWidth;
                rectF.bottom = x + barWidth;
                transformer.rectValueToPixel(rectF);
                if (this.mViewPortHandler.isInBoundsTop(this.b.bottom)) {
                    if (!this.mViewPortHandler.isInBoundsBottom(this.b.top)) {
                        break;
                    }
                    this.b.left = this.mViewPortHandler.contentLeft();
                    this.b.right = this.mViewPortHandler.contentRight();
                    canvas.drawRect(this.b, this.mShadowPaint);
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
        for (int i3 = 0; i3 < barBuffer.size(); i3 += 4) {
            int i4 = i3 + 3;
            if (!this.mViewPortHandler.isInBoundsTop(barBuffer.buffer[i4])) {
                return;
            }
            int i5 = i3 + 1;
            if (this.mViewPortHandler.isInBoundsBottom(barBuffer.buffer[i5])) {
                if (!z2) {
                    this.mRenderPaint.setColor(iBarDataSet.getColor(i3 / 4));
                }
                float[] fArr = barBuffer.buffer;
                int i6 = i3 + 2;
                canvas.drawRect(fArr[i3], fArr[i5], fArr[i6], fArr[i4], this.mRenderPaint);
                if (z) {
                    float[] fArr2 = barBuffer.buffer;
                    canvas.drawRect(fArr2[i3], fArr2[i5], fArr2[i6], fArr2[i4], this.mBarBorderPaint);
                }
            }
        }
    }

    public void drawValue(Canvas canvas, String str, float f, float f2, int i) {
        this.mValuePaint.setColor(i);
        canvas.drawText(str, f, f2, this.mValuePaint);
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        List list;
        MPPointF mPPointF;
        int i;
        float[] fArr;
        float f;
        int i2;
        float[] fArr2;
        float f2;
        float f3;
        BarEntry barEntry;
        float f4;
        int i3;
        List list2;
        boolean z;
        MPPointF mPPointF2;
        IValueFormatter iValueFormatter;
        float f5;
        BarBuffer barBuffer;
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getBarData().getDataSets();
            float convertDpToPixel = Utils.convertDpToPixel(5.0f);
            boolean isDrawValueAboveBarEnabled = this.mChart.isDrawValueAboveBarEnabled();
            int i4 = 0;
            while (i4 < this.mChart.getBarData().getDataSetCount()) {
                IBarDataSet iBarDataSet = (IBarDataSet) dataSets.get(i4);
                if (shouldDrawValues(iBarDataSet)) {
                    boolean isInverted = this.mChart.isInverted(iBarDataSet.getAxisDependency());
                    applyValueTextStyle(iBarDataSet);
                    float f6 = 2.0f;
                    float calcTextHeight = Utils.calcTextHeight(this.mValuePaint, BleConst.GetDeviceMacAddress) / 2.0f;
                    IValueFormatter valueFormatter = iBarDataSet.getValueFormatter();
                    BarBuffer barBuffer2 = this.mBarBuffers[i4];
                    float phaseY = this.mAnimator.getPhaseY();
                    MPPointF mPPointF3 = MPPointF.getInstance(iBarDataSet.getIconsOffset());
                    mPPointF3.x = Utils.convertDpToPixel(mPPointF3.x);
                    mPPointF3.y = Utils.convertDpToPixel(mPPointF3.y);
                    if (!iBarDataSet.isStacked()) {
                        int i5 = 0;
                        while (i5 < barBuffer2.buffer.length * this.mAnimator.getPhaseX()) {
                            float[] fArr3 = barBuffer2.buffer;
                            int i6 = i5 + 1;
                            float f7 = (fArr3[i6] + fArr3[i5 + 3]) / f6;
                            if (!this.mViewPortHandler.isInBoundsTop(fArr3[i6])) {
                                break;
                            }
                            if (this.mViewPortHandler.isInBoundsX(barBuffer2.buffer[i5]) && this.mViewPortHandler.isInBoundsBottom(barBuffer2.buffer[i6])) {
                                BarEntry barEntry2 = (BarEntry) iBarDataSet.getEntryForIndex(i5 / 4);
                                float y = barEntry2.getY();
                                String formattedValue = valueFormatter.getFormattedValue(y, barEntry2, i4, this.mViewPortHandler);
                                MPPointF mPPointF4 = mPPointF3;
                                float calcTextWidth = Utils.calcTextWidth(this.mValuePaint, formattedValue);
                                float f8 = isDrawValueAboveBarEnabled ? convertDpToPixel : -(calcTextWidth + convertDpToPixel);
                                IValueFormatter iValueFormatter2 = valueFormatter;
                                float f9 = isDrawValueAboveBarEnabled ? -(calcTextWidth + convertDpToPixel) : convertDpToPixel;
                                if (isInverted) {
                                    f8 = (-f8) - calcTextWidth;
                                    f9 = (-f9) - calcTextWidth;
                                }
                                float f10 = f8;
                                float f11 = f9;
                                if (iBarDataSet.isDrawValuesEnabled()) {
                                    f4 = y;
                                    i3 = i5;
                                    list2 = dataSets;
                                    mPPointF2 = mPPointF4;
                                    f5 = calcTextHeight;
                                    barBuffer = barBuffer2;
                                    z = isInverted;
                                    iValueFormatter = iValueFormatter2;
                                    drawValue(canvas, formattedValue, (y >= 0.0f ? f10 : f11) + barBuffer2.buffer[i5 + 2], f7 + calcTextHeight, iBarDataSet.getValueTextColor(i5 / 2));
                                } else {
                                    f4 = y;
                                    i3 = i5;
                                    list2 = dataSets;
                                    z = isInverted;
                                    mPPointF2 = mPPointF4;
                                    iValueFormatter = iValueFormatter2;
                                    f5 = calcTextHeight;
                                    barBuffer = barBuffer2;
                                }
                                if (barEntry2.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                    Drawable icon = barEntry2.getIcon();
                                    float f12 = barBuffer.buffer[i3 + 2];
                                    if (f4 < 0.0f) {
                                        f10 = f11;
                                    }
                                    Utils.drawImage(canvas, icon, (int) (f12 + f10 + mPPointF2.x), (int) (f7 + mPPointF2.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                }
                            } else {
                                i3 = i5;
                                list2 = dataSets;
                                z = isInverted;
                                f5 = calcTextHeight;
                                mPPointF2 = mPPointF3;
                                barBuffer = barBuffer2;
                                iValueFormatter = valueFormatter;
                            }
                            i5 = i3 + 4;
                            mPPointF3 = mPPointF2;
                            valueFormatter = iValueFormatter;
                            barBuffer2 = barBuffer;
                            calcTextHeight = f5;
                            dataSets = list2;
                            isInverted = z;
                            f6 = 2.0f;
                        }
                        list = dataSets;
                        mPPointF = mPPointF3;
                    } else {
                        list = dataSets;
                        mPPointF = mPPointF3;
                        Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
                        int i7 = 0;
                        int i8 = 0;
                        while (i7 < iBarDataSet.getEntryCount() * this.mAnimator.getPhaseX()) {
                            BarEntry barEntry3 = (BarEntry) iBarDataSet.getEntryForIndex(i7);
                            int valueTextColor = iBarDataSet.getValueTextColor(i7);
                            float[] yVals = barEntry3.getYVals();
                            if (yVals == null) {
                                int i9 = i8 + 1;
                                if (!this.mViewPortHandler.isInBoundsTop(barBuffer2.buffer[i9])) {
                                    break;
                                } else if (this.mViewPortHandler.isInBoundsX(barBuffer2.buffer[i8]) && this.mViewPortHandler.isInBoundsBottom(barBuffer2.buffer[i9])) {
                                    String formattedValue2 = valueFormatter.getFormattedValue(barEntry3.getY(), barEntry3, i4, this.mViewPortHandler);
                                    float calcTextWidth2 = Utils.calcTextWidth(this.mValuePaint, formattedValue2);
                                    float f13 = isDrawValueAboveBarEnabled ? convertDpToPixel : -(calcTextWidth2 + convertDpToPixel);
                                    float f14 = isDrawValueAboveBarEnabled ? -(calcTextWidth2 + convertDpToPixel) : convertDpToPixel;
                                    if (isInverted) {
                                        f13 = (-f13) - calcTextWidth2;
                                        f14 = (-f14) - calcTextWidth2;
                                    }
                                    float f15 = f13;
                                    float f16 = f14;
                                    if (iBarDataSet.isDrawValuesEnabled()) {
                                        i = i7;
                                        fArr = yVals;
                                        barEntry = barEntry3;
                                        drawValue(canvas, formattedValue2, barBuffer2.buffer[i8 + 2] + (barEntry3.getY() >= 0.0f ? f15 : f16), barBuffer2.buffer[i9] + calcTextHeight, valueTextColor);
                                    } else {
                                        barEntry = barEntry3;
                                        i = i7;
                                        fArr = yVals;
                                    }
                                    if (barEntry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                        Drawable icon2 = barEntry.getIcon();
                                        float f17 = barBuffer2.buffer[i8 + 2];
                                        if (barEntry.getY() < 0.0f) {
                                            f15 = f16;
                                        }
                                        Utils.drawImage(canvas, icon2, (int) (f17 + f15 + mPPointF.x), (int) (barBuffer2.buffer[i9] + mPPointF.y), icon2.getIntrinsicWidth(), icon2.getIntrinsicHeight());
                                    }
                                }
                            } else {
                                i = i7;
                                fArr = yVals;
                                int length = fArr.length * 2;
                                float[] fArr4 = new float[length];
                                float f18 = -barEntry3.getNegativeSum();
                                float f19 = 0.0f;
                                int i10 = 0;
                                int i11 = 0;
                                while (i10 < length) {
                                    float f20 = fArr[i11];
                                    int i12 = (f20 > 0.0f ? 1 : (f20 == 0.0f ? 0 : -1));
                                    if (i12 == 0 && (f19 == 0.0f || f18 == 0.0f)) {
                                        float f21 = f18;
                                        f18 = f20;
                                        f3 = f21;
                                    } else if (i12 >= 0) {
                                        f19 += f20;
                                        f3 = f18;
                                        f18 = f19;
                                    } else {
                                        f3 = f18 - f20;
                                    }
                                    fArr4[i10] = f18 * phaseY;
                                    i10 += 2;
                                    i11++;
                                    f18 = f3;
                                }
                                transformer.pointValuesToPixel(fArr4);
                                int i13 = 0;
                                while (i13 < length) {
                                    float f22 = fArr[i13 / 2];
                                    String formattedValue3 = valueFormatter.getFormattedValue(f22, barEntry3, i4, this.mViewPortHandler);
                                    float calcTextWidth3 = Utils.calcTextWidth(this.mValuePaint, formattedValue3);
                                    float f23 = isDrawValueAboveBarEnabled ? convertDpToPixel : -(calcTextWidth3 + convertDpToPixel);
                                    int i14 = length;
                                    float f24 = isDrawValueAboveBarEnabled ? -(calcTextWidth3 + convertDpToPixel) : convertDpToPixel;
                                    if (isInverted) {
                                        f23 = (-f23) - calcTextWidth3;
                                        f24 = (-f24) - calcTextWidth3;
                                    }
                                    boolean z2 = (f22 == 0.0f && f18 == 0.0f && f19 > 0.0f) || f22 < 0.0f;
                                    float f25 = fArr4[i13];
                                    if (z2) {
                                        f23 = f24;
                                    }
                                    float f26 = f25 + f23;
                                    float[] fArr5 = barBuffer2.buffer;
                                    float f27 = (fArr5[i8 + 1] + fArr5[i8 + 3]) / 2.0f;
                                    if (!this.mViewPortHandler.isInBoundsTop(f27)) {
                                        break;
                                    }
                                    if (this.mViewPortHandler.isInBoundsX(f26) && this.mViewPortHandler.isInBoundsBottom(f27)) {
                                        if (iBarDataSet.isDrawValuesEnabled()) {
                                            f = f27;
                                            i2 = i13;
                                            fArr2 = fArr4;
                                            f2 = f26;
                                            drawValue(canvas, formattedValue3, f26, f27 + calcTextHeight, valueTextColor);
                                        } else {
                                            f = f27;
                                            i2 = i13;
                                            fArr2 = fArr4;
                                            f2 = f26;
                                        }
                                        if (barEntry3.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                            Drawable icon3 = barEntry3.getIcon();
                                            Utils.drawImage(canvas, icon3, (int) (f2 + mPPointF.x), (int) (f + mPPointF.y), icon3.getIntrinsicWidth(), icon3.getIntrinsicHeight());
                                        }
                                    } else {
                                        i2 = i13;
                                        fArr2 = fArr4;
                                    }
                                    i13 = i2 + 2;
                                    length = i14;
                                    fArr4 = fArr2;
                                }
                            }
                            i8 = fArr == null ? i8 + 4 : i8 + (fArr.length * 4);
                            i7 = i + 1;
                        }
                    }
                    MPPointF.recycleInstance(mPPointF);
                } else {
                    list = dataSets;
                }
                i4++;
                dataSets = list;
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new HorizontalBarBuffer[barData.getDataSetCount()];
        for (int i = 0; i < this.mBarBuffers.length; i++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i);
            this.mBarBuffers[i] = new HorizontalBarBuffer(iBarDataSet.getEntryCount() * 4 * (iBarDataSet.isStacked() ? iBarDataSet.getStackSize() : 1), barData.getDataSetCount(), iBarDataSet.isStacked());
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public boolean isDrawingValuesAllowed(ChartInterface chartInterface) {
        return ((float) chartInterface.getData().getEntryCount()) < ((float) chartInterface.getMaxVisibleCount()) * this.mViewPortHandler.getScaleY();
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    public void prepareBarHighlight(float f, float f2, float f3, float f4, Transformer transformer) {
        this.mBarRect.set(f2, f - f4, f3, f + f4);
        transformer.rectToPixelPhaseHorizontal(this.mBarRect, this.mAnimator.getPhaseY());
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    public void setHighlightDrawPos(Highlight highlight, RectF rectF) {
        highlight.setDraw(rectF.centerY(), rectF.right);
    }
}
