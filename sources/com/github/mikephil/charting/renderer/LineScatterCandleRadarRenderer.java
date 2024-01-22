package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Path;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.interfaces.datasets.ILineScatterCandleRadarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
/* loaded from: classes9.dex */
public abstract class LineScatterCandleRadarRenderer extends BarLineScatterCandleBubbleRenderer {

    /* renamed from: a  reason: collision with root package name */
    public Path f7959a;

    public LineScatterCandleRadarRenderer(ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.f7959a = new Path();
    }

    public void drawHighlightLines(Canvas canvas, float f, float f2, ILineScatterCandleRadarDataSet iLineScatterCandleRadarDataSet) {
        this.mHighlightPaint.setColor(iLineScatterCandleRadarDataSet.getHighLightColor());
        this.mHighlightPaint.setStrokeWidth(iLineScatterCandleRadarDataSet.getHighlightLineWidth());
        this.mHighlightPaint.setPathEffect(iLineScatterCandleRadarDataSet.getDashPathEffectHighlight());
        if (iLineScatterCandleRadarDataSet.isVerticalHighlightIndicatorEnabled()) {
            this.f7959a.reset();
            this.f7959a.moveTo(f, this.mViewPortHandler.contentTop());
            this.f7959a.lineTo(f, this.mViewPortHandler.contentBottom());
            canvas.drawPath(this.f7959a, this.mHighlightPaint);
        }
        if (iLineScatterCandleRadarDataSet.isHorizontalHighlightIndicatorEnabled()) {
            this.f7959a.reset();
            this.f7959a.moveTo(this.mViewPortHandler.contentLeft(), f2);
            this.f7959a.lineTo(this.mViewPortHandler.contentRight(), f2);
            canvas.drawPath(this.f7959a, this.mHighlightPaint);
        }
    }
}
