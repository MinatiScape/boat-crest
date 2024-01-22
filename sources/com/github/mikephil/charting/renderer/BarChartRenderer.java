package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.Range;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.List;
/* loaded from: classes9.dex */
public class BarChartRenderer extends BarLineScatterCandleBubbleRenderer {

    /* renamed from: a  reason: collision with root package name */
    public RectF f7951a;
    public Paint mBarBorderPaint;
    public BarBuffer[] mBarBuffers;
    public RectF mBarRect;
    public BarDataProvider mChart;
    public Paint mShadowPaint;

    public BarChartRenderer(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mBarRect = new RectF();
        this.f7951a = new RectF();
        this.mChart = barDataProvider;
        Paint paint = new Paint(1);
        this.mHighlightPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.mHighlightPaint.setColor(Color.rgb(0, 0, 0));
        this.mHighlightPaint.setAlpha(120);
        Paint paint2 = new Paint(1);
        this.mShadowPaint = paint2;
        paint2.setStyle(Paint.Style.FILL);
        Paint paint3 = new Paint(1);
        this.mBarBorderPaint = paint3;
        paint3.setStyle(Paint.Style.STROKE);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        BarData barData = this.mChart.getBarData();
        for (int i = 0; i < barData.getDataSetCount(); i++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i);
            if (iBarDataSet.isVisible()) {
                drawDataSet(canvas, iBarDataSet, i);
            }
        }
    }

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
                RectF rectF = this.f7951a;
                rectF.left = x - barWidth;
                rectF.right = x + barWidth;
                transformer.rectValueToPixel(rectF);
                if (this.mViewPortHandler.isInBoundsLeft(this.f7951a.right)) {
                    if (!this.mViewPortHandler.isInBoundsRight(this.f7951a.left)) {
                        break;
                    }
                    this.f7951a.top = this.mViewPortHandler.contentTop();
                    this.f7951a.bottom = this.mViewPortHandler.contentBottom();
                    canvas.drawRect(this.f7951a, this.mShadowPaint);
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
            int i4 = i3 + 2;
            if (this.mViewPortHandler.isInBoundsLeft(barBuffer.buffer[i4])) {
                if (!this.mViewPortHandler.isInBoundsRight(barBuffer.buffer[i3])) {
                    return;
                }
                if (!z2) {
                    this.mRenderPaint.setColor(iBarDataSet.getColor(i3 / 4));
                }
                if (iBarDataSet.getGradientColor() != null) {
                    GradientColor gradientColor = iBarDataSet.getGradientColor();
                    Paint paint = this.mRenderPaint;
                    float[] fArr = barBuffer.buffer;
                    paint.setShader(new LinearGradient(fArr[i3], fArr[i3 + 3], fArr[i3], fArr[i3 + 1], gradientColor.getStartColor(), gradientColor.getEndColor(), Shader.TileMode.MIRROR));
                }
                if (iBarDataSet.getGradientColors() != null) {
                    Paint paint2 = this.mRenderPaint;
                    float[] fArr2 = barBuffer.buffer;
                    float f = fArr2[i3];
                    float f2 = fArr2[i3 + 3];
                    float f3 = fArr2[i3];
                    float f4 = fArr2[i3 + 1];
                    int i5 = i3 / 4;
                    paint2.setShader(new LinearGradient(f, f2, f3, f4, iBarDataSet.getGradientColor(i5).getStartColor(), iBarDataSet.getGradientColor(i5).getEndColor(), Shader.TileMode.MIRROR));
                }
                float[] fArr3 = barBuffer.buffer;
                int i6 = i3 + 1;
                int i7 = i3 + 3;
                canvas.drawRect(fArr3[i3], fArr3[i6], fArr3[i4], fArr3[i7], this.mRenderPaint);
                if (z) {
                    float[] fArr4 = barBuffer.buffer;
                    canvas.drawRect(fArr4[i3], fArr4[i6], fArr4[i4], fArr4[i7], this.mBarBorderPaint);
                }
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        float y;
        float f;
        float f2;
        float f3;
        BarData barData = this.mChart.getBarData();
        for (Highlight highlight : highlightArr) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iBarDataSet != null && iBarDataSet.isHighlightEnabled()) {
                BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (isInBoundsX(barEntry, iBarDataSet)) {
                    Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
                    this.mHighlightPaint.setColor(iBarDataSet.getHighLightColor());
                    this.mHighlightPaint.setAlpha(iBarDataSet.getHighLightAlpha());
                    if (highlight.getStackIndex() >= 0 && barEntry.isStacked()) {
                        if (this.mChart.isHighlightFullBarEnabled()) {
                            y = barEntry.getPositiveSum();
                            f = -barEntry.getNegativeSum();
                        } else {
                            Range range = barEntry.getRanges()[highlight.getStackIndex()];
                            f3 = range.from;
                            f2 = range.to;
                            prepareBarHighlight(barEntry.getX(), f3, f2, barData.getBarWidth() / 2.0f, transformer);
                            setHighlightDrawPos(highlight, this.mBarRect);
                            canvas.drawRect(this.mBarRect, this.mHighlightPaint);
                        }
                    } else {
                        y = barEntry.getY();
                        f = 0.0f;
                    }
                    f2 = f;
                    f3 = y;
                    prepareBarHighlight(barEntry.getX(), f3, f2, barData.getBarWidth() / 2.0f, transformer);
                    setHighlightDrawPos(highlight, this.mBarRect);
                    canvas.drawRect(this.mBarRect, this.mHighlightPaint);
                }
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        MPPointF mPPointF;
        List list;
        int i;
        float f;
        boolean z;
        float[] fArr;
        Transformer transformer;
        int i2;
        float[] fArr2;
        int i3;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        boolean z2;
        int i4;
        MPPointF mPPointF2;
        List list2;
        BarBuffer barBuffer;
        float f7;
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getBarData().getDataSets();
            float convertDpToPixel = Utils.convertDpToPixel(4.5f);
            boolean isDrawValueAboveBarEnabled = this.mChart.isDrawValueAboveBarEnabled();
            int i5 = 0;
            while (i5 < this.mChart.getBarData().getDataSetCount()) {
                IBarDataSet iBarDataSet = (IBarDataSet) dataSets.get(i5);
                if (shouldDrawValues(iBarDataSet)) {
                    applyValueTextStyle(iBarDataSet);
                    boolean isInverted = this.mChart.isInverted(iBarDataSet.getAxisDependency());
                    float calcTextHeight = Utils.calcTextHeight(this.mValuePaint, BleConst.SetStepGoal);
                    float f8 = isDrawValueAboveBarEnabled ? -convertDpToPixel : calcTextHeight + convertDpToPixel;
                    float f9 = isDrawValueAboveBarEnabled ? calcTextHeight + convertDpToPixel : -convertDpToPixel;
                    if (isInverted) {
                        f8 = (-f8) - calcTextHeight;
                        f9 = (-f9) - calcTextHeight;
                    }
                    float f10 = f8;
                    float f11 = f9;
                    BarBuffer barBuffer2 = this.mBarBuffers[i5];
                    float phaseY = this.mAnimator.getPhaseY();
                    MPPointF mPPointF3 = MPPointF.getInstance(iBarDataSet.getIconsOffset());
                    mPPointF3.x = Utils.convertDpToPixel(mPPointF3.x);
                    mPPointF3.y = Utils.convertDpToPixel(mPPointF3.y);
                    if (!iBarDataSet.isStacked()) {
                        int i6 = 0;
                        while (i6 < barBuffer2.buffer.length * this.mAnimator.getPhaseX()) {
                            float[] fArr3 = barBuffer2.buffer;
                            float f12 = (fArr3[i6] + fArr3[i6 + 2]) / 2.0f;
                            if (!this.mViewPortHandler.isInBoundsRight(f12)) {
                                break;
                            }
                            int i7 = i6 + 1;
                            if (this.mViewPortHandler.isInBoundsY(barBuffer2.buffer[i7]) && this.mViewPortHandler.isInBoundsLeft(f12)) {
                                int i8 = i6 / 4;
                                Entry entry = (BarEntry) iBarDataSet.getEntryForIndex(i8);
                                float y = entry.getY();
                                if (iBarDataSet.isDrawValuesEnabled()) {
                                    f7 = f12;
                                    i4 = i6;
                                    mPPointF2 = mPPointF3;
                                    list2 = dataSets;
                                    barBuffer = barBuffer2;
                                    drawValue(canvas, iBarDataSet.getValueFormatter(), y, entry, i5, f7, y >= 0.0f ? barBuffer2.buffer[i7] + f10 : barBuffer2.buffer[i6 + 3] + f11, iBarDataSet.getValueTextColor(i8));
                                } else {
                                    f7 = f12;
                                    i4 = i6;
                                    mPPointF2 = mPPointF3;
                                    list2 = dataSets;
                                    barBuffer = barBuffer2;
                                }
                                if (entry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                    Drawable icon = entry.getIcon();
                                    Utils.drawImage(canvas, icon, (int) (f7 + mPPointF2.x), (int) ((y >= 0.0f ? barBuffer.buffer[i7] + f10 : barBuffer.buffer[i4 + 3] + f11) + mPPointF2.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                }
                            } else {
                                i4 = i6;
                                mPPointF2 = mPPointF3;
                                list2 = dataSets;
                                barBuffer = barBuffer2;
                            }
                            i6 = i4 + 4;
                            barBuffer2 = barBuffer;
                            mPPointF3 = mPPointF2;
                            dataSets = list2;
                        }
                        mPPointF = mPPointF3;
                        list = dataSets;
                    } else {
                        mPPointF = mPPointF3;
                        list = dataSets;
                        Transformer transformer2 = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
                        int i9 = 0;
                        int i10 = 0;
                        while (i9 < iBarDataSet.getEntryCount() * this.mAnimator.getPhaseX()) {
                            BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForIndex(i9);
                            float[] yVals = barEntry.getYVals();
                            float[] fArr4 = barBuffer2.buffer;
                            float f13 = (fArr4[i10] + fArr4[i10 + 2]) / 2.0f;
                            int valueTextColor = iBarDataSet.getValueTextColor(i9);
                            if (yVals == null) {
                                if (!this.mViewPortHandler.isInBoundsRight(f13)) {
                                    break;
                                }
                                int i11 = i10 + 1;
                                if (this.mViewPortHandler.isInBoundsY(barBuffer2.buffer[i11]) && this.mViewPortHandler.isInBoundsLeft(f13)) {
                                    if (iBarDataSet.isDrawValuesEnabled()) {
                                        f5 = f13;
                                        f = convertDpToPixel;
                                        fArr = yVals;
                                        i = i9;
                                        z = isDrawValueAboveBarEnabled;
                                        transformer = transformer2;
                                        drawValue(canvas, iBarDataSet.getValueFormatter(), barEntry.getY(), barEntry, i5, f5, barBuffer2.buffer[i11] + (barEntry.getY() >= 0.0f ? f10 : f11), valueTextColor);
                                    } else {
                                        f5 = f13;
                                        i = i9;
                                        f = convertDpToPixel;
                                        z = isDrawValueAboveBarEnabled;
                                        fArr = yVals;
                                        transformer = transformer2;
                                    }
                                    if (barEntry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                        Drawable icon2 = barEntry.getIcon();
                                        Utils.drawImage(canvas, icon2, (int) (f5 + mPPointF.x), (int) (barBuffer2.buffer[i11] + (barEntry.getY() >= 0.0f ? f10 : f11) + mPPointF.y), icon2.getIntrinsicWidth(), icon2.getIntrinsicHeight());
                                    }
                                } else {
                                    transformer2 = transformer2;
                                    isDrawValueAboveBarEnabled = isDrawValueAboveBarEnabled;
                                    convertDpToPixel = convertDpToPixel;
                                    i9 = i9;
                                }
                            } else {
                                i = i9;
                                f = convertDpToPixel;
                                z = isDrawValueAboveBarEnabled;
                                fArr = yVals;
                                transformer = transformer2;
                                float f14 = f13;
                                int length = fArr.length * 2;
                                float[] fArr5 = new float[length];
                                float f15 = -barEntry.getNegativeSum();
                                float f16 = 0.0f;
                                int i12 = 0;
                                int i13 = 0;
                                while (i12 < length) {
                                    float f17 = fArr[i13];
                                    int i14 = (f17 > 0.0f ? 1 : (f17 == 0.0f ? 0 : -1));
                                    if (i14 == 0 && (f16 == 0.0f || f15 == 0.0f)) {
                                        float f18 = f15;
                                        f15 = f17;
                                        f4 = f18;
                                    } else if (i14 >= 0) {
                                        f16 += f17;
                                        f4 = f15;
                                        f15 = f16;
                                    } else {
                                        f4 = f15 - f17;
                                    }
                                    fArr5[i12 + 1] = f15 * phaseY;
                                    i12 += 2;
                                    i13++;
                                    f15 = f4;
                                }
                                transformer.pointValuesToPixel(fArr5);
                                int i15 = 0;
                                while (i15 < length) {
                                    int i16 = i15 / 2;
                                    float f19 = fArr[i16];
                                    float f20 = fArr5[i15 + 1] + (((f19 > 0.0f ? 1 : (f19 == 0.0f ? 0 : -1)) == 0 && (f15 > 0.0f ? 1 : (f15 == 0.0f ? 0 : -1)) == 0 && (f16 > 0.0f ? 1 : (f16 == 0.0f ? 0 : -1)) > 0) || (f19 > 0.0f ? 1 : (f19 == 0.0f ? 0 : -1)) < 0 ? f11 : f10);
                                    if (!this.mViewPortHandler.isInBoundsRight(f14)) {
                                        break;
                                    }
                                    if (this.mViewPortHandler.isInBoundsY(f20) && this.mViewPortHandler.isInBoundsLeft(f14)) {
                                        if (iBarDataSet.isDrawValuesEnabled()) {
                                            f3 = f20;
                                            i2 = i15;
                                            fArr2 = fArr5;
                                            i3 = length;
                                            f2 = f14;
                                            drawValue(canvas, iBarDataSet.getValueFormatter(), fArr[i16], barEntry, i5, f14, f3, valueTextColor);
                                        } else {
                                            f3 = f20;
                                            i2 = i15;
                                            fArr2 = fArr5;
                                            i3 = length;
                                            f2 = f14;
                                        }
                                        if (barEntry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                            Drawable icon3 = barEntry.getIcon();
                                            Utils.drawImage(canvas, icon3, (int) (f2 + mPPointF.x), (int) (f3 + mPPointF.y), icon3.getIntrinsicWidth(), icon3.getIntrinsicHeight());
                                        }
                                    } else {
                                        i2 = i15;
                                        fArr2 = fArr5;
                                        i3 = length;
                                        f2 = f14;
                                    }
                                    i15 = i2 + 2;
                                    fArr5 = fArr2;
                                    length = i3;
                                    f14 = f2;
                                }
                            }
                            i10 = fArr == null ? i10 + 4 : i10 + (fArr.length * 4);
                            i9 = i + 1;
                            transformer2 = transformer;
                            isDrawValueAboveBarEnabled = z;
                            convertDpToPixel = f;
                        }
                    }
                    f6 = convertDpToPixel;
                    z2 = isDrawValueAboveBarEnabled;
                    MPPointF.recycleInstance(mPPointF);
                } else {
                    list = dataSets;
                    f6 = convertDpToPixel;
                    z2 = isDrawValueAboveBarEnabled;
                }
                i5++;
                dataSets = list;
                isDrawValueAboveBarEnabled = z2;
                convertDpToPixel = f6;
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new BarBuffer[barData.getDataSetCount()];
        for (int i = 0; i < this.mBarBuffers.length; i++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i);
            this.mBarBuffers[i] = new BarBuffer(iBarDataSet.getEntryCount() * 4 * (iBarDataSet.isStacked() ? iBarDataSet.getStackSize() : 1), barData.getDataSetCount(), iBarDataSet.isStacked());
        }
    }

    public void prepareBarHighlight(float f, float f2, float f3, float f4, Transformer transformer) {
        this.mBarRect.set(f - f4, f2, f + f4, f3);
        transformer.rectToPixelPhase(this.mBarRect, this.mAnimator.getPhaseY());
    }

    public void setHighlightDrawPos(Highlight highlight, RectF rectF) {
        highlight.setDraw(rectF.centerX(), rectF.top);
    }
}
