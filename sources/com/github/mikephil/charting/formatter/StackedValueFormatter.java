package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.jstyle.blesdk1860.constant.BleConst;
import java.text.DecimalFormat;
/* loaded from: classes9.dex */
public class StackedValueFormatter implements IValueFormatter {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7947a;
    public String b;
    public DecimalFormat c;

    public StackedValueFormatter(boolean z, String str, int i) {
        this.f7947a = z;
        this.b = str;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 == 0) {
                stringBuffer.append(".");
            }
            stringBuffer.append(BleConst.GetDeviceTime);
        }
        this.c = new DecimalFormat("###,###,###,##0" + stringBuffer.toString());
    }

    @Override // com.github.mikephil.charting.formatter.IValueFormatter
    public String getFormattedValue(float f, Entry entry, int i, ViewPortHandler viewPortHandler) {
        BarEntry barEntry;
        float[] yVals;
        if (!this.f7947a && (entry instanceof BarEntry) && (yVals = (barEntry = (BarEntry) entry).getYVals()) != null) {
            if (yVals[yVals.length - 1] == f) {
                return this.c.format(barEntry.getY()) + this.b;
            }
            return "";
        }
        return this.c.format(f) + this.b;
    }
}
