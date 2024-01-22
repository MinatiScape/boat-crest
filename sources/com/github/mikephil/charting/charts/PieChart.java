package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.PieHighlighter;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.renderer.DataRenderer;
import com.github.mikephil.charting.renderer.PieChartRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;
/* loaded from: classes9.dex */
public class PieChart extends PieRadarChartBase<PieData> {
    public boolean A;
    public CharSequence B;
    public MPPointF C;
    public float D;
    public boolean E;
    public float F;
    public float mMaxAngle;
    public float mTransparentCircleRadiusPercent;
    public RectF t;
    public boolean u;
    public float[] v;
    public float[] w;
    public boolean x;
    public boolean y;
    public boolean z;

    public PieChart(Context context) {
        super(context);
        this.t = new RectF();
        this.u = true;
        this.v = new float[1];
        this.w = new float[1];
        this.x = true;
        this.y = false;
        this.z = false;
        this.A = false;
        this.B = "";
        this.C = MPPointF.getInstance(0.0f, 0.0f);
        this.D = 50.0f;
        this.mTransparentCircleRadiusPercent = 55.0f;
        this.E = true;
        this.F = 100.0f;
        this.mMaxAngle = 360.0f;
    }

    public final float b(float f, float f2) {
        return (f / f2) * this.mMaxAngle;
    }

    public final void c() {
        int entryCount = ((PieData) this.mData).getEntryCount();
        if (this.v.length != entryCount) {
            this.v = new float[entryCount];
        } else {
            for (int i = 0; i < entryCount; i++) {
                this.v[i] = 0.0f;
            }
        }
        if (this.w.length != entryCount) {
            this.w = new float[entryCount];
        } else {
            for (int i2 = 0; i2 < entryCount; i2++) {
                this.w[i2] = 0.0f;
            }
        }
        float yValueSum = ((PieData) this.mData).getYValueSum();
        List<IPieDataSet> dataSets = ((PieData) this.mData).getDataSets();
        int i3 = 0;
        for (int i4 = 0; i4 < ((PieData) this.mData).getDataSetCount(); i4++) {
            IPieDataSet iPieDataSet = dataSets.get(i4);
            for (int i5 = 0; i5 < iPieDataSet.getEntryCount(); i5++) {
                this.v[i3] = b(Math.abs(iPieDataSet.getEntryForIndex(i5).getY()), yValueSum);
                if (i3 == 0) {
                    this.w[i3] = this.v[i3];
                } else {
                    float[] fArr = this.w;
                    fArr[i3] = fArr[i3 - 1] + this.v[i3];
                }
                i3++;
            }
        }
    }

    @Override // com.github.mikephil.charting.charts.PieRadarChartBase, com.github.mikephil.charting.charts.Chart
    public void calcMinMax() {
        c();
    }

    @Override // com.github.mikephil.charting.charts.PieRadarChartBase, com.github.mikephil.charting.charts.Chart
    public void calculateOffsets() {
        super.calculateOffsets();
        if (this.mData == 0) {
            return;
        }
        float diameter = getDiameter() / 2.0f;
        MPPointF centerOffsets = getCenterOffsets();
        float selectionShift = ((PieData) this.mData).getDataSet().getSelectionShift();
        RectF rectF = this.t;
        float f = centerOffsets.x;
        float f2 = centerOffsets.y;
        rectF.set((f - diameter) + selectionShift, (f2 - diameter) + selectionShift, (f + diameter) - selectionShift, (f2 + diameter) - selectionShift);
        MPPointF.recycleInstance(centerOffsets);
    }

    public float[] getAbsoluteAngles() {
        return this.w;
    }

    public MPPointF getCenterCircleBox() {
        return MPPointF.getInstance(this.t.centerX(), this.t.centerY());
    }

    public CharSequence getCenterText() {
        return this.B;
    }

    public MPPointF getCenterTextOffset() {
        MPPointF mPPointF = this.C;
        return MPPointF.getInstance(mPPointF.x, mPPointF.y);
    }

    public float getCenterTextRadiusPercent() {
        return this.F;
    }

    public RectF getCircleBox() {
        return this.t;
    }

    public int getDataSetIndexForIndex(int i) {
        List<IPieDataSet> dataSets = ((PieData) this.mData).getDataSets();
        for (int i2 = 0; i2 < dataSets.size(); i2++) {
            if (dataSets.get(i2).getEntryForXValue(i, Float.NaN) != null) {
                return i2;
            }
        }
        return -1;
    }

    public float[] getDrawAngles() {
        return this.v;
    }

    public float getHoleRadius() {
        return this.D;
    }

    @Override // com.github.mikephil.charting.charts.PieRadarChartBase
    public int getIndexForAngle(float f) {
        float normalizedAngle = Utils.getNormalizedAngle(f - getRotationAngle());
        int i = 0;
        while (true) {
            float[] fArr = this.w;
            if (i >= fArr.length) {
                return -1;
            }
            if (fArr[i] > normalizedAngle) {
                return i;
            }
            i++;
        }
    }

    @Override // com.github.mikephil.charting.charts.Chart
    public float[] getMarkerPosition(Highlight highlight) {
        int x;
        MPPointF centerCircleBox = getCenterCircleBox();
        float radius = getRadius();
        float f = (radius / 10.0f) * 3.6f;
        if (isDrawHoleEnabled()) {
            f = (radius - ((radius / 100.0f) * getHoleRadius())) / 2.0f;
        }
        float f2 = radius - f;
        float rotationAngle = getRotationAngle();
        float f3 = this.v[(int) highlight.getX()] / 2.0f;
        double d = f2;
        float cos = (float) ((Math.cos(Math.toRadians(((this.w[x] + rotationAngle) - f3) * this.mAnimator.getPhaseY())) * d) + centerCircleBox.x);
        MPPointF.recycleInstance(centerCircleBox);
        return new float[]{cos, (float) ((d * Math.sin(Math.toRadians(((rotationAngle + this.w[x]) - f3) * this.mAnimator.getPhaseY()))) + centerCircleBox.y)};
    }

    public float getMaxAngle() {
        return this.mMaxAngle;
    }

    @Override // com.github.mikephil.charting.charts.PieRadarChartBase
    public float getRadius() {
        RectF rectF = this.t;
        if (rectF == null) {
            return 0.0f;
        }
        return Math.min(rectF.width() / 2.0f, this.t.height() / 2.0f);
    }

    @Override // com.github.mikephil.charting.charts.PieRadarChartBase
    public float getRequiredBaseOffset() {
        return 0.0f;
    }

    @Override // com.github.mikephil.charting.charts.PieRadarChartBase
    public float getRequiredLegendOffset() {
        return this.mLegendRenderer.getLabelPaint().getTextSize() * 2.0f;
    }

    public float getTransparentCircleRadius() {
        return this.mTransparentCircleRadiusPercent;
    }

    @Override // com.github.mikephil.charting.charts.Chart
    @Deprecated
    public XAxis getXAxis() {
        throw new RuntimeException("PieChart has no XAxis");
    }

    @Override // com.github.mikephil.charting.charts.PieRadarChartBase, com.github.mikephil.charting.charts.Chart
    public void init() {
        super.init();
        this.mRenderer = new PieChartRenderer(this, this.mAnimator, this.mViewPortHandler);
        this.mXAxis = null;
        this.mHighlighter = new PieHighlighter(this);
    }

    public boolean isDrawCenterTextEnabled() {
        return this.E;
    }

    public boolean isDrawEntryLabelsEnabled() {
        return this.u;
    }

    public boolean isDrawHoleEnabled() {
        return this.x;
    }

    public boolean isDrawRoundedSlicesEnabled() {
        return this.A;
    }

    public boolean isDrawSlicesUnderHoleEnabled() {
        return this.y;
    }

    public boolean isUsePercentValuesEnabled() {
        return this.z;
    }

    public boolean needsHighlight(int i) {
        if (!valuesToHighlight()) {
            return false;
        }
        int i2 = 0;
        while (true) {
            Highlight[] highlightArr = this.mIndicesToHighlight;
            if (i2 >= highlightArr.length) {
                return false;
            }
            if (((int) highlightArr[i2].getX()) == i) {
                return true;
            }
            i2++;
        }
    }

    @Override // com.github.mikephil.charting.charts.Chart, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        DataRenderer dataRenderer = this.mRenderer;
        if (dataRenderer != null && (dataRenderer instanceof PieChartRenderer)) {
            ((PieChartRenderer) dataRenderer).releaseBitmap();
        }
        super.onDetachedFromWindow();
    }

    @Override // com.github.mikephil.charting.charts.Chart, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mData == 0) {
            return;
        }
        this.mRenderer.drawData(canvas);
        if (valuesToHighlight()) {
            this.mRenderer.drawHighlighted(canvas, this.mIndicesToHighlight);
        }
        this.mRenderer.drawExtras(canvas);
        this.mRenderer.drawValues(canvas);
        this.mLegendRenderer.renderLegend(canvas);
        drawDescription(canvas);
        drawMarkers(canvas);
    }

    public void setCenterText(CharSequence charSequence) {
        if (charSequence == null) {
            this.B = "";
        } else {
            this.B = charSequence;
        }
    }

    public void setCenterTextColor(int i) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText().setColor(i);
    }

    public void setCenterTextOffset(float f, float f2) {
        this.C.x = Utils.convertDpToPixel(f);
        this.C.y = Utils.convertDpToPixel(f2);
    }

    public void setCenterTextRadiusPercent(float f) {
        this.F = f;
    }

    public void setCenterTextSize(float f) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText().setTextSize(Utils.convertDpToPixel(f));
    }

    public void setCenterTextSizePixels(float f) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText().setTextSize(f);
    }

    public void setCenterTextTypeface(Typeface typeface) {
        ((PieChartRenderer) this.mRenderer).getPaintCenterText().setTypeface(typeface);
    }

    public void setDrawCenterText(boolean z) {
        this.E = z;
    }

    public void setDrawEntryLabels(boolean z) {
        this.u = z;
    }

    public void setDrawHoleEnabled(boolean z) {
        this.x = z;
    }

    @Deprecated
    public void setDrawSliceText(boolean z) {
        this.u = z;
    }

    public void setDrawSlicesUnderHole(boolean z) {
        this.y = z;
    }

    public void setEntryLabelColor(int i) {
        ((PieChartRenderer) this.mRenderer).getPaintEntryLabels().setColor(i);
    }

    public void setEntryLabelTextSize(float f) {
        ((PieChartRenderer) this.mRenderer).getPaintEntryLabels().setTextSize(Utils.convertDpToPixel(f));
    }

    public void setEntryLabelTypeface(Typeface typeface) {
        ((PieChartRenderer) this.mRenderer).getPaintEntryLabels().setTypeface(typeface);
    }

    public void setHoleColor(int i) {
        ((PieChartRenderer) this.mRenderer).getPaintHole().setColor(i);
    }

    public void setHoleRadius(float f) {
        this.D = f;
    }

    public void setMaxAngle(float f) {
        if (f > 360.0f) {
            f = 360.0f;
        }
        if (f < 90.0f) {
            f = 90.0f;
        }
        this.mMaxAngle = f;
    }

    public void setTransparentCircleAlpha(int i) {
        ((PieChartRenderer) this.mRenderer).getPaintTransparentCircle().setAlpha(i);
    }

    public void setTransparentCircleColor(int i) {
        Paint paintTransparentCircle = ((PieChartRenderer) this.mRenderer).getPaintTransparentCircle();
        int alpha = paintTransparentCircle.getAlpha();
        paintTransparentCircle.setColor(i);
        paintTransparentCircle.setAlpha(alpha);
    }

    public void setTransparentCircleRadius(float f) {
        this.mTransparentCircleRadiusPercent = f;
    }

    public void setUsePercentValues(boolean z) {
        this.z = z;
    }

    public PieChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.t = new RectF();
        this.u = true;
        this.v = new float[1];
        this.w = new float[1];
        this.x = true;
        this.y = false;
        this.z = false;
        this.A = false;
        this.B = "";
        this.C = MPPointF.getInstance(0.0f, 0.0f);
        this.D = 50.0f;
        this.mTransparentCircleRadiusPercent = 55.0f;
        this.E = true;
        this.F = 100.0f;
        this.mMaxAngle = 360.0f;
    }

    public PieChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.t = new RectF();
        this.u = true;
        this.v = new float[1];
        this.w = new float[1];
        this.x = true;
        this.y = false;
        this.z = false;
        this.A = false;
        this.B = "";
        this.C = MPPointF.getInstance(0.0f, 0.0f);
        this.D = 50.0f;
        this.mTransparentCircleRadiusPercent = 55.0f;
        this.E = true;
        this.F = 100.0f;
        this.mMaxAngle = 360.0f;
    }
}
