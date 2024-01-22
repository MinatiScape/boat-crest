package com.github.mikephil.charting.data;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.highlight.Range;
@SuppressLint({"ParcelCreator"})
/* loaded from: classes9.dex */
public class BarEntry extends Entry {
    public float[] l;
    public Range[] m;
    public float n;
    public float o;

    public BarEntry(float f, float f2) {
        super(f, f2);
    }

    public static float b(float[] fArr) {
        float f = 0.0f;
        if (fArr == null) {
            return 0.0f;
        }
        for (float f2 : fArr) {
            f += f2;
        }
        return f;
    }

    public final void a() {
        float[] fArr = this.l;
        if (fArr == null) {
            this.n = 0.0f;
            this.o = 0.0f;
            return;
        }
        float f = 0.0f;
        float f2 = 0.0f;
        for (float f3 : fArr) {
            if (f3 <= 0.0f) {
                f += Math.abs(f3);
            } else {
                f2 += f3;
            }
        }
        this.n = f;
        this.o = f2;
    }

    public void calcRanges() {
        float[] yVals = getYVals();
        if (yVals == null || yVals.length == 0) {
            return;
        }
        this.m = new Range[yVals.length];
        float f = -getNegativeSum();
        int i = 0;
        float f2 = 0.0f;
        while (true) {
            Range[] rangeArr = this.m;
            if (i >= rangeArr.length) {
                return;
            }
            float f3 = yVals[i];
            if (f3 < 0.0f) {
                float f4 = f - f3;
                rangeArr[i] = new Range(f, f4);
                f = f4;
            } else {
                float f5 = f3 + f2;
                rangeArr[i] = new Range(f2, f5);
                f2 = f5;
            }
            i++;
        }
    }

    @Deprecated
    public float getBelowSum(int i) {
        return getSumBelow(i);
    }

    public float getNegativeSum() {
        return this.n;
    }

    public float getPositiveSum() {
        return this.o;
    }

    public Range[] getRanges() {
        return this.m;
    }

    public float getSumBelow(int i) {
        float[] fArr = this.l;
        float f = 0.0f;
        if (fArr == null) {
            return 0.0f;
        }
        for (int length = fArr.length - 1; length > i && length >= 0; length--) {
            f += this.l[length];
        }
        return f;
    }

    @Override // com.github.mikephil.charting.data.BaseEntry
    public float getY() {
        return super.getY();
    }

    public float[] getYVals() {
        return this.l;
    }

    public boolean isStacked() {
        return this.l != null;
    }

    public void setVals(float[] fArr) {
        setY(b(fArr));
        this.l = fArr;
        a();
        calcRanges();
    }

    public BarEntry(float f, float f2, Object obj) {
        super(f, f2, obj);
    }

    @Override // com.github.mikephil.charting.data.Entry
    public BarEntry copy() {
        BarEntry barEntry = new BarEntry(getX(), getY(), getData());
        barEntry.setVals(this.l);
        return barEntry;
    }

    public BarEntry(float f, float f2, Drawable drawable) {
        super(f, f2, drawable);
    }

    public BarEntry(float f, float f2, Drawable drawable, Object obj) {
        super(f, f2, drawable, obj);
    }

    public BarEntry(float f, float[] fArr) {
        super(f, b(fArr));
        this.l = fArr;
        a();
        calcRanges();
    }

    public BarEntry(float f, float[] fArr, Object obj) {
        super(f, b(fArr), obj);
        this.l = fArr;
        a();
        calcRanges();
    }

    public BarEntry(float f, float[] fArr, Drawable drawable) {
        super(f, b(fArr), drawable);
        this.l = fArr;
        a();
        calcRanges();
    }

    public BarEntry(float f, float[] fArr, Drawable drawable, Object obj) {
        super(f, b(fArr), drawable, obj);
        this.l = fArr;
        a();
        calcRanges();
    }
}
