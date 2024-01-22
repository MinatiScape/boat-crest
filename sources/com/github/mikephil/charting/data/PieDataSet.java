package com.github.mikephil.charting.data;

import androidx.core.view.ViewCompat;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class PieDataSet extends DataSet<PieEntry> implements IPieDataSet {
    public float f;
    public boolean g;
    public float h;
    public ValuePosition i;
    public ValuePosition j;
    public boolean k;
    public int l;
    public float m;
    public float n;
    public float o;
    public float p;
    public boolean q;

    /* loaded from: classes9.dex */
    public enum ValuePosition {
        INSIDE_SLICE,
        OUTSIDE_SLICE
    }

    public PieDataSet(List<PieEntry> list, String str) {
        super(list, str);
        this.f = 0.0f;
        this.h = 18.0f;
        ValuePosition valuePosition = ValuePosition.INSIDE_SLICE;
        this.i = valuePosition;
        this.j = valuePosition;
        this.k = false;
        this.l = ViewCompat.MEASURED_STATE_MASK;
        this.m = 1.0f;
        this.n = 75.0f;
        this.o = 0.3f;
        this.p = 0.4f;
        this.q = true;
    }

    @Override // com.github.mikephil.charting.data.DataSet
    public DataSet<PieEntry> copy() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mValues.size(); i++) {
            arrayList.add(((PieEntry) this.mValues.get(i)).copy());
        }
        PieDataSet pieDataSet = new PieDataSet(arrayList, getLabel());
        copy(pieDataSet);
        return pieDataSet;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IPieDataSet
    public float getSelectionShift() {
        return this.h;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IPieDataSet
    public float getSliceSpace() {
        return this.f;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IPieDataSet
    public int getValueLineColor() {
        return this.l;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IPieDataSet
    public float getValueLinePart1Length() {
        return this.o;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IPieDataSet
    public float getValueLinePart1OffsetPercentage() {
        return this.n;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IPieDataSet
    public float getValueLinePart2Length() {
        return this.p;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IPieDataSet
    public float getValueLineWidth() {
        return this.m;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IPieDataSet
    public ValuePosition getXValuePosition() {
        return this.i;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IPieDataSet
    public ValuePosition getYValuePosition() {
        return this.j;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IPieDataSet
    public boolean isAutomaticallyDisableSliceSpacingEnabled() {
        return this.g;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IPieDataSet
    public boolean isUsingSliceColorAsValueLineColor() {
        return this.k;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.IPieDataSet
    public boolean isValueLineVariableLength() {
        return this.q;
    }

    public void setAutomaticallyDisableSliceSpacing(boolean z) {
        this.g = z;
    }

    public void setSelectionShift(float f) {
        this.h = Utils.convertDpToPixel(f);
    }

    public void setSliceSpace(float f) {
        if (f > 20.0f) {
            f = 20.0f;
        }
        if (f < 0.0f) {
            f = 0.0f;
        }
        this.f = Utils.convertDpToPixel(f);
    }

    public void setUsingSliceColorAsValueLineColor(boolean z) {
        this.k = z;
    }

    public void setValueLineColor(int i) {
        this.l = i;
    }

    public void setValueLinePart1Length(float f) {
        this.o = f;
    }

    public void setValueLinePart1OffsetPercentage(float f) {
        this.n = f;
    }

    public void setValueLinePart2Length(float f) {
        this.p = f;
    }

    public void setValueLineVariableLength(boolean z) {
        this.q = z;
    }

    public void setValueLineWidth(float f) {
        this.m = f;
    }

    public void setXValuePosition(ValuePosition valuePosition) {
        this.i = valuePosition;
    }

    public void setYValuePosition(ValuePosition valuePosition) {
        this.j = valuePosition;
    }

    @Override // com.github.mikephil.charting.data.DataSet
    public void calcMinMax(PieEntry pieEntry) {
        if (pieEntry == null) {
            return;
        }
        calcMinMaxY(pieEntry);
    }

    public void copy(PieDataSet pieDataSet) {
        super.copy((DataSet) pieDataSet);
    }
}
