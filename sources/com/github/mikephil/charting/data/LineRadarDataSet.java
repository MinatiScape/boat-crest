package com.github.mikephil.charting.data;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet;
import com.github.mikephil.charting.utils.Utils;
import java.util.List;
/* loaded from: classes9.dex */
public abstract class LineRadarDataSet<T extends Entry> extends LineScatterCandleRadarDataSet<T> implements ILineRadarDataSet<T> {
    public int f;
    public int g;
    public float h;
    public boolean i;
    public Drawable mFillDrawable;

    public LineRadarDataSet(List<T> list, String str) {
        super(list, str);
        this.f = Color.rgb(140, 234, 255);
        this.g = 85;
        this.h = 2.5f;
        this.i = false;
    }

    public void copy(LineRadarDataSet lineRadarDataSet) {
        super.copy((LineScatterCandleRadarDataSet) lineRadarDataSet);
        lineRadarDataSet.i = this.i;
        lineRadarDataSet.g = this.g;
        lineRadarDataSet.f = this.f;
        lineRadarDataSet.mFillDrawable = this.mFillDrawable;
        lineRadarDataSet.h = this.h;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet
    public int getFillAlpha() {
        return this.g;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet
    public int getFillColor() {
        return this.f;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet
    public Drawable getFillDrawable() {
        return this.mFillDrawable;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet
    public float getLineWidth() {
        return this.h;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet
    public boolean isDrawFilledEnabled() {
        return this.i;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineRadarDataSet
    public void setDrawFilled(boolean z) {
        this.i = z;
    }

    public void setFillAlpha(int i) {
        this.g = i;
    }

    public void setFillColor(int i) {
        this.f = i;
        this.mFillDrawable = null;
    }

    @TargetApi(18)
    public void setFillDrawable(Drawable drawable) {
        this.mFillDrawable = drawable;
    }

    public void setLineWidth(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 10.0f) {
            f = 10.0f;
        }
        this.h = Utils.convertDpToPixel(f);
    }
}
