package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;
import java.util.Collection;
/* loaded from: classes9.dex */
public class IndexAxisValueFormatter implements IAxisValueFormatter {

    /* renamed from: a  reason: collision with root package name */
    public String[] f7945a;
    public int b;

    public IndexAxisValueFormatter() {
        this.f7945a = new String[0];
        this.b = 0;
    }

    @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
    public String getFormattedValue(float f, AxisBase axisBase) {
        int round = Math.round(f);
        return (round < 0 || round >= this.b || round != ((int) f)) ? "" : this.f7945a[round];
    }

    public String[] getValues() {
        return this.f7945a;
    }

    public void setValues(String[] strArr) {
        if (strArr == null) {
            strArr = new String[0];
        }
        this.f7945a = strArr;
        this.b = strArr.length;
    }

    public IndexAxisValueFormatter(String[] strArr) {
        this.f7945a = new String[0];
        this.b = 0;
        if (strArr != null) {
            setValues(strArr);
        }
    }

    public IndexAxisValueFormatter(Collection<String> collection) {
        this.f7945a = new String[0];
        this.b = 0;
        if (collection != null) {
            setValues((String[]) collection.toArray(new String[collection.size()]));
        }
    }
}
