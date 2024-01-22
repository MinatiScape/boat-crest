package com.github.mikephil.charting.formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.jstyle.blesdk1860.constant.BleConst;
import java.text.DecimalFormat;
/* loaded from: classes9.dex */
public class DefaultAxisValueFormatter implements IAxisValueFormatter {
    public int digits;
    public DecimalFormat mFormat;

    public DefaultAxisValueFormatter(int i) {
        this.digits = 0;
        this.digits = i;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 == 0) {
                stringBuffer.append(".");
            }
            stringBuffer.append(BleConst.GetDeviceTime);
        }
        this.mFormat = new DecimalFormat("###,###,###,##0" + stringBuffer.toString());
    }

    public int getDecimalDigits() {
        return this.digits;
    }

    @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
    public String getFormattedValue(float f, AxisBase axisBase) {
        return this.mFormat.format(f);
    }
}
