package com.github.mikephil.charting.data;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.util.Log;
import com.github.mikephil.charting.formatter.DefaultFillFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class LineDataSet extends LineRadarDataSet<Entry> implements ILineDataSet {
    public Mode j;
    public List<Integer> k;
    public int l;
    public float m;
    public float n;
    public float o;
    public DashPathEffect p;
    public IFillFormatter q;
    public boolean r;
    public boolean s;

    /* loaded from: classes9.dex */
    public enum Mode {
        LINEAR,
        STEPPED,
        CUBIC_BEZIER,
        HORIZONTAL_BEZIER
    }

    public LineDataSet(List<Entry> list, String str) {
        super(list, str);
        this.j = Mode.LINEAR;
        this.k = null;
        this.l = -1;
        this.m = 8.0f;
        this.n = 4.0f;
        this.o = 0.2f;
        this.p = null;
        this.q = new DefaultFillFormatter();
        this.r = true;
        this.s = true;
        if (this.k == null) {
            this.k = new ArrayList();
        }
        this.k.clear();
        this.k.add(Integer.valueOf(Color.rgb(140, 234, 255)));
    }

    @Override // com.github.mikephil.charting.data.DataSet
    public DataSet<Entry> copy() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mValues.size(); i++) {
            arrayList.add(((Entry) this.mValues.get(i)).copy());
        }
        LineDataSet lineDataSet = new LineDataSet(arrayList, getLabel());
        copy(lineDataSet);
        return lineDataSet;
    }

    public void disableDashedLine() {
        this.p = null;
    }

    public void enableDashedLine(float f, float f2, float f3) {
        this.p = new DashPathEffect(new float[]{f, f2}, f3);
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineDataSet
    public int getCircleColor(int i) {
        return this.k.get(i).intValue();
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineDataSet
    public int getCircleColorCount() {
        return this.k.size();
    }

    public List<Integer> getCircleColors() {
        return this.k;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineDataSet
    public int getCircleHoleColor() {
        return this.l;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineDataSet
    public float getCircleHoleRadius() {
        return this.n;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineDataSet
    public float getCircleRadius() {
        return this.m;
    }

    @Deprecated
    public float getCircleSize() {
        return getCircleRadius();
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineDataSet
    public float getCubicIntensity() {
        return this.o;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineDataSet
    public DashPathEffect getDashPathEffect() {
        return this.p;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineDataSet
    public IFillFormatter getFillFormatter() {
        return this.q;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineDataSet
    public Mode getMode() {
        return this.j;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineDataSet
    public boolean isDashedLineEnabled() {
        return this.p != null;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineDataSet
    public boolean isDrawCircleHoleEnabled() {
        return this.s;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineDataSet
    public boolean isDrawCirclesEnabled() {
        return this.r;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineDataSet
    @Deprecated
    public boolean isDrawCubicEnabled() {
        return this.j == Mode.CUBIC_BEZIER;
    }

    @Override // com.github.mikephil.charting.interfaces.datasets.ILineDataSet
    @Deprecated
    public boolean isDrawSteppedEnabled() {
        return this.j == Mode.STEPPED;
    }

    public void resetCircleColors() {
        if (this.k == null) {
            this.k = new ArrayList();
        }
        this.k.clear();
    }

    public void setCircleColor(int i) {
        resetCircleColors();
        this.k.add(Integer.valueOf(i));
    }

    public void setCircleColors(List<Integer> list) {
        this.k = list;
    }

    public void setCircleHoleColor(int i) {
        this.l = i;
    }

    public void setCircleHoleRadius(float f) {
        if (f >= 0.5f) {
            this.n = Utils.convertDpToPixel(f);
        } else {
            Log.e("LineDataSet", "Circle radius cannot be < 0.5");
        }
    }

    public void setCircleRadius(float f) {
        if (f >= 1.0f) {
            this.m = Utils.convertDpToPixel(f);
        } else {
            Log.e("LineDataSet", "Circle radius cannot be < 1");
        }
    }

    @Deprecated
    public void setCircleSize(float f) {
        setCircleRadius(f);
    }

    public void setCubicIntensity(float f) {
        if (f > 1.0f) {
            f = 1.0f;
        }
        if (f < 0.05f) {
            f = 0.05f;
        }
        this.o = f;
    }

    public void setDrawCircleHole(boolean z) {
        this.s = z;
    }

    public void setDrawCircles(boolean z) {
        this.r = z;
    }

    public void setFillFormatter(IFillFormatter iFillFormatter) {
        if (iFillFormatter == null) {
            this.q = new DefaultFillFormatter();
        } else {
            this.q = iFillFormatter;
        }
    }

    public void setMode(Mode mode) {
        this.j = mode;
    }

    public void setCircleColors(int... iArr) {
        this.k = ColorTemplate.createColors(iArr);
    }

    public void setCircleColors(int[] iArr, Context context) {
        List<Integer> list = this.k;
        if (list == null) {
            list = new ArrayList<>();
        }
        list.clear();
        for (int i : iArr) {
            list.add(Integer.valueOf(context.getResources().getColor(i)));
        }
        this.k = list;
    }

    public void copy(LineDataSet lineDataSet) {
        super.copy((LineRadarDataSet) lineDataSet);
        lineDataSet.k = this.k;
        lineDataSet.l = this.l;
        lineDataSet.n = this.n;
        lineDataSet.m = this.m;
        lineDataSet.o = this.o;
        lineDataSet.p = this.p;
        lineDataSet.s = this.s;
        lineDataSet.r = this.s;
        lineDataSet.q = this.q;
        lineDataSet.j = this.j;
    }
}
