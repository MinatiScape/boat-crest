package com.coveiot.android.theme.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import com.coveiot.android.theme.R;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.Range;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.renderer.BarChartRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes7.dex */
public class RoundedBarChart extends BarChart {

    /* loaded from: classes7.dex */
    public class a extends BarChartRenderer {
        public int b;
        public RectF c;

        public a(RoundedBarChart roundedBarChart, BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler, int i) {
            super(barDataProvider, chartAnimator, viewPortHandler);
            this.c = new RectF();
            this.b = i;
        }

        @Override // com.github.mikephil.charting.renderer.BarChartRenderer
        public void drawDataSet(Canvas canvas, IBarDataSet iBarDataSet, int i) {
            try {
                Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
                this.mBarBorderPaint.setColor(iBarDataSet.getBarBorderColor());
                this.mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(iBarDataSet.getBarBorderWidth()));
                boolean z = true;
                boolean z2 = iBarDataSet.getBarBorderWidth() > 0.0f;
                float phaseX = this.mAnimator.getPhaseX();
                float phaseY = this.mAnimator.getPhaseY();
                if (this.mChart.isDrawBarShadowEnabled()) {
                    this.mShadowPaint.setColor(iBarDataSet.getBarShadowColor());
                    float barWidth = this.mChart.getBarData().getBarWidth() / 2.0f;
                    int min = Math.min((int) Math.ceil(iBarDataSet.getEntryCount() * phaseX), iBarDataSet.getEntryCount());
                    for (int i2 = 0; i2 < min; i2++) {
                        if (i2 >= 0) {
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
                                RectF rectF2 = this.c;
                                int i3 = this.b;
                                canvas.drawRoundRect(rectF2, i3, i3, this.mShadowPaint);
                            }
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
                if (iBarDataSet.getColors().size() != 1) {
                    z = false;
                }
                if (z) {
                    this.mRenderPaint.setColor(iBarDataSet.getColor());
                }
                for (int i4 = 0; i4 < barBuffer.size(); i4 += 4) {
                    int i5 = i4 + 2;
                    if (this.mViewPortHandler.isInBoundsLeft(barBuffer.buffer[i5])) {
                        if (!this.mViewPortHandler.isInBoundsRight(barBuffer.buffer[i4])) {
                            return;
                        }
                        if (!z) {
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
                            paint2.setShader(new LinearGradient(fArr2[i4], fArr2[i4 + 3], fArr2[i4], fArr2[i4 + 1], iBarDataSet.getGradientColor(i4 / 4).getStartColor(), iBarDataSet.getGradientColor(i4 / 4).getEndColor(), Shader.TileMode.MIRROR));
                        }
                        float[] fArr3 = barBuffer.buffer;
                        float f = fArr3[i4];
                        int i6 = i4 + 1;
                        float f2 = fArr3[i6];
                        float f3 = fArr3[i5];
                        int i7 = i4 + 3;
                        float f4 = fArr3[i7];
                        int i8 = this.b;
                        canvas.drawRoundRect(f, f2, f3, f4, i8, i8, this.mRenderPaint);
                        if (z2) {
                            float[] fArr4 = barBuffer.buffer;
                            float f5 = fArr4[i4];
                            float f6 = fArr4[i6];
                            float f7 = fArr4[i5];
                            float f8 = fArr4[i7];
                            int i9 = this.b;
                            canvas.drawRoundRect(f5, f6, f7, f8, i9, i9, this.mBarBorderPaint);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.github.mikephil.charting.renderer.BarChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
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
                                RectF rectF = this.mBarRect;
                                int i = this.b;
                                canvas.drawRoundRect(rectF, i, i, this.mHighlightPaint);
                            }
                        } else {
                            y = barEntry.getY();
                            f = 0.0f;
                        }
                        f2 = f;
                        f3 = y;
                        prepareBarHighlight(barEntry.getX(), f3, f2, barData.getBarWidth() / 2.0f, transformer);
                        setHighlightDrawPos(highlight, this.mBarRect);
                        RectF rectF2 = this.mBarRect;
                        int i2 = this.b;
                        canvas.drawRoundRect(rectF2, i2, i2, this.mHighlightPaint);
                    }
                }
            }
        }

        @Override // com.github.mikephil.charting.renderer.BarChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
        public void drawValues(Canvas canvas) {
            try {
                super.drawValues(canvas);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public RoundedBarChart(Context context) {
        super(context);
    }

    public final void b(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.RoundedBarChart, 0, 0);
        try {
            setRadius(obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedBarChart_radius, 0));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void setRadius(int i) {
        setRenderer(new a(this, this, getAnimator(), getViewPortHandler(), i));
    }

    public RoundedBarChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b(context, attributeSet);
    }

    public RoundedBarChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b(context, attributeSet);
    }
}
