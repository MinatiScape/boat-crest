package com.github.mikephil.charting.components;

import android.graphics.Paint;
import com.github.mikephil.charting.utils.Utils;
/* loaded from: classes9.dex */
public class YAxis extends AxisBase {
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public YAxisLabelPosition l;
    public AxisDependency m;
    public boolean mDrawZeroLine;
    public boolean mInverted;
    public float mMaxWidth;
    public float mMinWidth;
    public float mSpacePercentBottom;
    public float mSpacePercentTop;
    public int mZeroLineColor;
    public float mZeroLineWidth;

    /* loaded from: classes9.dex */
    public enum AxisDependency {
        LEFT,
        RIGHT
    }

    /* loaded from: classes9.dex */
    public enum YAxisLabelPosition {
        OUTSIDE_CHART,
        INSIDE_CHART
    }

    public YAxis() {
        this.h = true;
        this.i = true;
        this.mInverted = false;
        this.mDrawZeroLine = false;
        this.j = false;
        this.k = false;
        this.mZeroLineColor = -7829368;
        this.mZeroLineWidth = 1.0f;
        this.mSpacePercentTop = 10.0f;
        this.mSpacePercentBottom = 10.0f;
        this.l = YAxisLabelPosition.OUTSIDE_CHART;
        this.mMinWidth = 0.0f;
        this.mMaxWidth = Float.POSITIVE_INFINITY;
        this.m = AxisDependency.LEFT;
        this.mYOffset = 0.0f;
    }

    @Override // com.github.mikephil.charting.components.AxisBase
    public void calculate(float f, float f2) {
        if (Math.abs(f2 - f) == 0.0f) {
            f2 += 1.0f;
            f -= 1.0f;
        }
        float abs = Math.abs(f2 - f);
        this.mAxisMinimum = this.mCustomAxisMin ? this.mAxisMinimum : f - ((abs / 100.0f) * getSpaceBottom());
        float spaceTop = this.mCustomAxisMax ? this.mAxisMaximum : f2 + ((abs / 100.0f) * getSpaceTop());
        this.mAxisMaximum = spaceTop;
        this.mAxisRange = Math.abs(this.mAxisMinimum - spaceTop);
    }

    public AxisDependency getAxisDependency() {
        return this.m;
    }

    public YAxisLabelPosition getLabelPosition() {
        return this.l;
    }

    public float getMaxWidth() {
        return this.mMaxWidth;
    }

    public float getMinWidth() {
        return this.mMinWidth;
    }

    public float getRequiredHeightSpace(Paint paint) {
        paint.setTextSize(this.mTextSize);
        return Utils.calcTextHeight(paint, getLongestLabel()) + (getYOffset() * 2.0f);
    }

    public float getRequiredWidthSpace(Paint paint) {
        paint.setTextSize(this.mTextSize);
        float calcTextWidth = Utils.calcTextWidth(paint, getLongestLabel()) + (getXOffset() * 2.0f);
        float minWidth = getMinWidth();
        float maxWidth = getMaxWidth();
        if (minWidth > 0.0f) {
            minWidth = Utils.convertDpToPixel(minWidth);
        }
        if (maxWidth > 0.0f && maxWidth != Float.POSITIVE_INFINITY) {
            maxWidth = Utils.convertDpToPixel(maxWidth);
        }
        if (maxWidth <= 0.0d) {
            maxWidth = calcTextWidth;
        }
        return Math.max(minWidth, Math.min(calcTextWidth, maxWidth));
    }

    public float getSpaceBottom() {
        return this.mSpacePercentBottom;
    }

    public float getSpaceTop() {
        return this.mSpacePercentTop;
    }

    public int getZeroLineColor() {
        return this.mZeroLineColor;
    }

    public float getZeroLineWidth() {
        return this.mZeroLineWidth;
    }

    public boolean isDrawBottomYLabelEntryEnabled() {
        return this.h;
    }

    public boolean isDrawTopYLabelEntryEnabled() {
        return this.i;
    }

    public boolean isDrawZeroLineEnabled() {
        return this.mDrawZeroLine;
    }

    public boolean isInverted() {
        return this.mInverted;
    }

    @Deprecated
    public boolean isUseAutoScaleMaxRestriction() {
        return this.k;
    }

    @Deprecated
    public boolean isUseAutoScaleMinRestriction() {
        return this.j;
    }

    public boolean needsOffset() {
        return isEnabled() && isDrawLabelsEnabled() && getLabelPosition() == YAxisLabelPosition.OUTSIDE_CHART;
    }

    public void setDrawTopYLabelEntry(boolean z) {
        this.i = z;
    }

    public void setDrawZeroLine(boolean z) {
        this.mDrawZeroLine = z;
    }

    public void setInverted(boolean z) {
        this.mInverted = z;
    }

    public void setMaxWidth(float f) {
        this.mMaxWidth = f;
    }

    public void setMinWidth(float f) {
        this.mMinWidth = f;
    }

    public void setPosition(YAxisLabelPosition yAxisLabelPosition) {
        this.l = yAxisLabelPosition;
    }

    public void setSpaceBottom(float f) {
        this.mSpacePercentBottom = f;
    }

    public void setSpaceTop(float f) {
        this.mSpacePercentTop = f;
    }

    @Deprecated
    public void setStartAtZero(boolean z) {
        if (z) {
            setAxisMinimum(0.0f);
        } else {
            resetAxisMinimum();
        }
    }

    @Deprecated
    public void setUseAutoScaleMaxRestriction(boolean z) {
        this.k = z;
    }

    @Deprecated
    public void setUseAutoScaleMinRestriction(boolean z) {
        this.j = z;
    }

    public void setZeroLineColor(int i) {
        this.mZeroLineColor = i;
    }

    public void setZeroLineWidth(float f) {
        this.mZeroLineWidth = Utils.convertDpToPixel(f);
    }

    public YAxis(AxisDependency axisDependency) {
        this.h = true;
        this.i = true;
        this.mInverted = false;
        this.mDrawZeroLine = false;
        this.j = false;
        this.k = false;
        this.mZeroLineColor = -7829368;
        this.mZeroLineWidth = 1.0f;
        this.mSpacePercentTop = 10.0f;
        this.mSpacePercentBottom = 10.0f;
        this.l = YAxisLabelPosition.OUTSIDE_CHART;
        this.mMinWidth = 0.0f;
        this.mMaxWidth = Float.POSITIVE_INFINITY;
        this.m = axisDependency;
        this.mYOffset = 0.0f;
    }
}
