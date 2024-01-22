package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Path;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;
/* loaded from: classes9.dex */
public class YAxisRendererRadarChart extends YAxisRenderer {

    /* renamed from: a  reason: collision with root package name */
    public RadarChart f7962a;
    public Path b;

    public YAxisRendererRadarChart(ViewPortHandler viewPortHandler, YAxis yAxis, RadarChart radarChart) {
        super(viewPortHandler, yAxis, null);
        this.b = new Path();
        this.f7962a = radarChart;
    }

    @Override // com.github.mikephil.charting.renderer.AxisRenderer
    public void computeAxisValues(float f, float f2) {
        int i;
        float f3 = f;
        int labelCount = this.mAxis.getLabelCount();
        double abs = Math.abs(f2 - f3);
        if (labelCount != 0 && abs > 0.0d && !Double.isInfinite(abs)) {
            double roundToNextSignificant = Utils.roundToNextSignificant(abs / labelCount);
            if (this.mAxis.isGranularityEnabled() && roundToNextSignificant < this.mAxis.getGranularity()) {
                roundToNextSignificant = this.mAxis.getGranularity();
            }
            double roundToNextSignificant2 = Utils.roundToNextSignificant(Math.pow(10.0d, (int) Math.log10(roundToNextSignificant)));
            if (((int) (roundToNextSignificant / roundToNextSignificant2)) > 5) {
                roundToNextSignificant = Math.floor(roundToNextSignificant2 * 10.0d);
            }
            boolean isCenterAxisLabelsEnabled = this.mAxis.isCenterAxisLabelsEnabled();
            if (this.mAxis.isForceLabelsEnabled()) {
                float f4 = ((float) abs) / (labelCount - 1);
                AxisBase axisBase = this.mAxis;
                axisBase.mEntryCount = labelCount;
                if (axisBase.mEntries.length < labelCount) {
                    axisBase.mEntries = new float[labelCount];
                }
                for (int i2 = 0; i2 < labelCount; i2++) {
                    this.mAxis.mEntries[i2] = f3;
                    f3 += f4;
                }
            } else {
                int i3 = (roundToNextSignificant > 0.0d ? 1 : (roundToNextSignificant == 0.0d ? 0 : -1));
                double ceil = i3 == 0 ? 0.0d : Math.ceil(f3 / roundToNextSignificant) * roundToNextSignificant;
                if (isCenterAxisLabelsEnabled) {
                    ceil -= roundToNextSignificant;
                }
                double nextUp = i3 == 0 ? 0.0d : Utils.nextUp(Math.floor(f2 / roundToNextSignificant) * roundToNextSignificant);
                if (i3 != 0) {
                    i = isCenterAxisLabelsEnabled;
                    for (double d = ceil; d <= nextUp; d += roundToNextSignificant) {
                        i++;
                    }
                } else {
                    i = isCenterAxisLabelsEnabled;
                }
                int i4 = i + 1;
                AxisBase axisBase2 = this.mAxis;
                axisBase2.mEntryCount = i4;
                if (axisBase2.mEntries.length < i4) {
                    axisBase2.mEntries = new float[i4];
                }
                for (int i5 = 0; i5 < i4; i5++) {
                    if (ceil == 0.0d) {
                        ceil = 0.0d;
                    }
                    this.mAxis.mEntries[i5] = (float) ceil;
                    ceil += roundToNextSignificant;
                }
                labelCount = i4;
            }
            if (roundToNextSignificant < 1.0d) {
                this.mAxis.mDecimals = (int) Math.ceil(-Math.log10(roundToNextSignificant));
            } else {
                this.mAxis.mDecimals = 0;
            }
            if (isCenterAxisLabelsEnabled != 0) {
                AxisBase axisBase3 = this.mAxis;
                if (axisBase3.mCenteredEntries.length < labelCount) {
                    axisBase3.mCenteredEntries = new float[labelCount];
                }
                float[] fArr = axisBase3.mEntries;
                float f5 = (fArr[1] - fArr[0]) / 2.0f;
                for (int i6 = 0; i6 < labelCount; i6++) {
                    AxisBase axisBase4 = this.mAxis;
                    axisBase4.mCenteredEntries[i6] = axisBase4.mEntries[i6] + f5;
                }
            }
            AxisBase axisBase5 = this.mAxis;
            float[] fArr2 = axisBase5.mEntries;
            float f6 = fArr2[0];
            axisBase5.mAxisMinimum = f6;
            float f7 = fArr2[labelCount - 1];
            axisBase5.mAxisMaximum = f7;
            axisBase5.mAxisRange = Math.abs(f7 - f6);
            return;
        }
        AxisBase axisBase6 = this.mAxis;
        axisBase6.mEntries = new float[0];
        axisBase6.mCenteredEntries = new float[0];
        axisBase6.mEntryCount = 0;
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderAxisLabels(Canvas canvas) {
        if (this.mYAxis.isEnabled() && this.mYAxis.isDrawLabelsEnabled()) {
            this.mAxisLabelPaint.setTypeface(this.mYAxis.getTypeface());
            this.mAxisLabelPaint.setTextSize(this.mYAxis.getTextSize());
            this.mAxisLabelPaint.setColor(this.mYAxis.getTextColor());
            MPPointF centerOffsets = this.f7962a.getCenterOffsets();
            MPPointF mPPointF = MPPointF.getInstance(0.0f, 0.0f);
            float factor = this.f7962a.getFactor();
            int i = this.mYAxis.isDrawTopYLabelEntryEnabled() ? this.mYAxis.mEntryCount : this.mYAxis.mEntryCount - 1;
            for (int i2 = !this.mYAxis.isDrawBottomYLabelEntryEnabled(); i2 < i; i2++) {
                YAxis yAxis = this.mYAxis;
                Utils.getPosition(centerOffsets, (yAxis.mEntries[i2] - yAxis.mAxisMinimum) * factor, this.f7962a.getRotationAngle(), mPPointF);
                canvas.drawText(this.mYAxis.getFormattedLabel(i2), mPPointF.x + 10.0f, mPPointF.y, this.mAxisLabelPaint);
            }
            MPPointF.recycleInstance(centerOffsets);
            MPPointF.recycleInstance(mPPointF);
        }
    }

    @Override // com.github.mikephil.charting.renderer.YAxisRenderer, com.github.mikephil.charting.renderer.AxisRenderer
    public void renderLimitLines(Canvas canvas) {
        List<LimitLine> limitLines = this.mYAxis.getLimitLines();
        if (limitLines == null) {
            return;
        }
        float sliceAngle = this.f7962a.getSliceAngle();
        float factor = this.f7962a.getFactor();
        MPPointF centerOffsets = this.f7962a.getCenterOffsets();
        MPPointF mPPointF = MPPointF.getInstance(0.0f, 0.0f);
        for (int i = 0; i < limitLines.size(); i++) {
            LimitLine limitLine = limitLines.get(i);
            if (limitLine.isEnabled()) {
                this.mLimitLinePaint.setColor(limitLine.getLineColor());
                this.mLimitLinePaint.setPathEffect(limitLine.getDashPathEffect());
                this.mLimitLinePaint.setStrokeWidth(limitLine.getLineWidth());
                float limit = (limitLine.getLimit() - this.f7962a.getYChartMin()) * factor;
                Path path = this.b;
                path.reset();
                for (int i2 = 0; i2 < ((RadarData) this.f7962a.getData()).getMaxEntryCountSet().getEntryCount(); i2++) {
                    Utils.getPosition(centerOffsets, limit, (i2 * sliceAngle) + this.f7962a.getRotationAngle(), mPPointF);
                    if (i2 == 0) {
                        path.moveTo(mPPointF.x, mPPointF.y);
                    } else {
                        path.lineTo(mPPointF.x, mPPointF.y);
                    }
                }
                path.close();
                canvas.drawPath(path, this.mLimitLinePaint);
            }
        }
        MPPointF.recycleInstance(centerOffsets);
        MPPointF.recycleInstance(mPPointF);
    }
}
