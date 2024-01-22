package com.github.mikephil.charting.components;

import android.graphics.DashPathEffect;
import android.util.Log;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public abstract class AxisBase extends ComponentBase {
    public IAxisValueFormatter mAxisValueFormatter;
    public int mDecimals;
    public int mEntryCount;
    public List<LimitLine> mLimitLines;

    /* renamed from: a  reason: collision with root package name */
    public int f7934a = -7829368;
    public float b = 1.0f;
    public int c = -7829368;
    public float d = 1.0f;
    public float[] mEntries = new float[0];
    public float[] mCenteredEntries = new float[0];
    public int e = 6;
    public float mGranularity = 1.0f;
    public boolean mGranularityEnabled = false;
    public boolean mForceLabels = false;
    public boolean mDrawGridLines = true;
    public boolean mDrawAxisLine = true;
    public boolean mDrawLabels = true;
    public boolean mCenterAxisLabels = false;
    public DashPathEffect f = null;
    public DashPathEffect g = null;
    public boolean mDrawLimitLineBehindData = false;
    public boolean mDrawGridLinesBehindData = true;
    public float mSpaceMin = 0.0f;
    public float mSpaceMax = 0.0f;
    public boolean mCustomAxisMin = false;
    public boolean mCustomAxisMax = false;
    public float mAxisMaximum = 0.0f;
    public float mAxisMinimum = 0.0f;
    public float mAxisRange = 0.0f;

    public AxisBase() {
        this.mTextSize = Utils.convertDpToPixel(10.0f);
        this.mXOffset = Utils.convertDpToPixel(5.0f);
        this.mYOffset = Utils.convertDpToPixel(5.0f);
        this.mLimitLines = new ArrayList();
    }

    public void addLimitLine(LimitLine limitLine) {
        this.mLimitLines.add(limitLine);
        if (this.mLimitLines.size() > 6) {
            Log.e("MPAndroiChart", "Warning! You have more than 6 LimitLines on your axis, do you really want that?");
        }
    }

    public void calculate(float f, float f2) {
        float f3 = this.mCustomAxisMin ? this.mAxisMinimum : f - this.mSpaceMin;
        float f4 = this.mCustomAxisMax ? this.mAxisMaximum : f2 + this.mSpaceMax;
        if (Math.abs(f4 - f3) == 0.0f) {
            f4 += 1.0f;
            f3 -= 1.0f;
        }
        this.mAxisMinimum = f3;
        this.mAxisMaximum = f4;
        this.mAxisRange = Math.abs(f4 - f3);
    }

    public void disableAxisLineDashedLine() {
        this.f = null;
    }

    public void disableGridDashedLine() {
        this.g = null;
    }

    public void enableAxisLineDashedLine(float f, float f2, float f3) {
        this.f = new DashPathEffect(new float[]{f, f2}, f3);
    }

    public void enableGridDashedLine(float f, float f2, float f3) {
        this.g = new DashPathEffect(new float[]{f, f2}, f3);
    }

    public int getAxisLineColor() {
        return this.c;
    }

    public DashPathEffect getAxisLineDashPathEffect() {
        return this.f;
    }

    public float getAxisLineWidth() {
        return this.d;
    }

    public float getAxisMaximum() {
        return this.mAxisMaximum;
    }

    public float getAxisMinimum() {
        return this.mAxisMinimum;
    }

    public String getFormattedLabel(int i) {
        return (i < 0 || i >= this.mEntries.length) ? "" : getValueFormatter().getFormattedValue(this.mEntries[i], this);
    }

    public float getGranularity() {
        return this.mGranularity;
    }

    public int getGridColor() {
        return this.f7934a;
    }

    public DashPathEffect getGridDashPathEffect() {
        return this.g;
    }

    public float getGridLineWidth() {
        return this.b;
    }

    public int getLabelCount() {
        return this.e;
    }

    public List<LimitLine> getLimitLines() {
        return this.mLimitLines;
    }

    public String getLongestLabel() {
        String str = "";
        for (int i = 0; i < this.mEntries.length; i++) {
            String formattedLabel = getFormattedLabel(i);
            if (formattedLabel != null && str.length() < formattedLabel.length()) {
                str = formattedLabel;
            }
        }
        return str;
    }

    public float getSpaceMax() {
        return this.mSpaceMax;
    }

    public float getSpaceMin() {
        return this.mSpaceMin;
    }

    public IAxisValueFormatter getValueFormatter() {
        IAxisValueFormatter iAxisValueFormatter = this.mAxisValueFormatter;
        if (iAxisValueFormatter == null || ((iAxisValueFormatter instanceof DefaultAxisValueFormatter) && ((DefaultAxisValueFormatter) iAxisValueFormatter).getDecimalDigits() != this.mDecimals)) {
            this.mAxisValueFormatter = new DefaultAxisValueFormatter(this.mDecimals);
        }
        return this.mAxisValueFormatter;
    }

    public boolean isAxisLineDashedLineEnabled() {
        return this.f != null;
    }

    public boolean isAxisMaxCustom() {
        return this.mCustomAxisMax;
    }

    public boolean isAxisMinCustom() {
        return this.mCustomAxisMin;
    }

    public boolean isCenterAxisLabelsEnabled() {
        return this.mCenterAxisLabels && this.mEntryCount > 0;
    }

    public boolean isDrawAxisLineEnabled() {
        return this.mDrawAxisLine;
    }

    public boolean isDrawGridLinesBehindDataEnabled() {
        return this.mDrawGridLinesBehindData;
    }

    public boolean isDrawGridLinesEnabled() {
        return this.mDrawGridLines;
    }

    public boolean isDrawLabelsEnabled() {
        return this.mDrawLabels;
    }

    public boolean isDrawLimitLinesBehindDataEnabled() {
        return this.mDrawLimitLineBehindData;
    }

    public boolean isForceLabelsEnabled() {
        return this.mForceLabels;
    }

    public boolean isGranularityEnabled() {
        return this.mGranularityEnabled;
    }

    public boolean isGridDashedLineEnabled() {
        return this.g != null;
    }

    public void removeAllLimitLines() {
        this.mLimitLines.clear();
    }

    public void removeLimitLine(LimitLine limitLine) {
        this.mLimitLines.remove(limitLine);
    }

    public void resetAxisMaximum() {
        this.mCustomAxisMax = false;
    }

    public void resetAxisMinimum() {
        this.mCustomAxisMin = false;
    }

    public void setAxisLineColor(int i) {
        this.c = i;
    }

    public void setAxisLineDashedLine(DashPathEffect dashPathEffect) {
        this.f = dashPathEffect;
    }

    public void setAxisLineWidth(float f) {
        this.d = Utils.convertDpToPixel(f);
    }

    @Deprecated
    public void setAxisMaxValue(float f) {
        setAxisMaximum(f);
    }

    public void setAxisMaximum(float f) {
        this.mCustomAxisMax = true;
        this.mAxisMaximum = f;
        this.mAxisRange = Math.abs(f - this.mAxisMinimum);
    }

    @Deprecated
    public void setAxisMinValue(float f) {
        setAxisMinimum(f);
    }

    public void setAxisMinimum(float f) {
        this.mCustomAxisMin = true;
        this.mAxisMinimum = f;
        this.mAxisRange = Math.abs(this.mAxisMaximum - f);
    }

    public void setCenterAxisLabels(boolean z) {
        this.mCenterAxisLabels = z;
    }

    public void setDrawAxisLine(boolean z) {
        this.mDrawAxisLine = z;
    }

    public void setDrawGridLines(boolean z) {
        this.mDrawGridLines = z;
    }

    public void setDrawGridLinesBehindData(boolean z) {
        this.mDrawGridLinesBehindData = z;
    }

    public void setDrawLabels(boolean z) {
        this.mDrawLabels = z;
    }

    public void setDrawLimitLinesBehindData(boolean z) {
        this.mDrawLimitLineBehindData = z;
    }

    public void setGranularity(float f) {
        this.mGranularity = f;
        this.mGranularityEnabled = true;
    }

    public void setGranularityEnabled(boolean z) {
        this.mGranularityEnabled = z;
    }

    public void setGridColor(int i) {
        this.f7934a = i;
    }

    public void setGridDashedLine(DashPathEffect dashPathEffect) {
        this.g = dashPathEffect;
    }

    public void setGridLineWidth(float f) {
        this.b = Utils.convertDpToPixel(f);
    }

    public void setLabelCount(int i) {
        if (i > 25) {
            i = 25;
        }
        if (i < 2) {
            i = 2;
        }
        this.e = i;
        this.mForceLabels = false;
    }

    public void setSpaceMax(float f) {
        this.mSpaceMax = f;
    }

    public void setSpaceMin(float f) {
        this.mSpaceMin = f;
    }

    public void setValueFormatter(IAxisValueFormatter iAxisValueFormatter) {
        if (iAxisValueFormatter == null) {
            this.mAxisValueFormatter = new DefaultAxisValueFormatter(this.mDecimals);
        } else {
            this.mAxisValueFormatter = iAxisValueFormatter;
        }
    }

    public void setLabelCount(int i, boolean z) {
        setLabelCount(i);
        this.mForceLabels = z;
    }
}
