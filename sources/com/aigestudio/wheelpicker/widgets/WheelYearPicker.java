package com.aigestudio.wheelpicker.widgets;

import android.content.Context;
import android.util.AttributeSet;
import com.aigestudio.wheelpicker.WheelPicker;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/* loaded from: classes.dex */
public class WheelYearPicker extends WheelPicker implements IWheelYearPicker {
    public int o0;
    public int p0;
    public int q0;

    public WheelYearPicker(Context context) {
        this(context, null);
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelYearPicker
    public int getCurrentYear() {
        return Integer.valueOf(String.valueOf(getData().get(getCurrentItemPosition()))).intValue();
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelYearPicker
    public int getSelectedYear() {
        return this.q0;
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelYearPicker
    public int getYearEnd() {
        return this.p0;
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelYearPicker
    public int getYearStart() {
        return this.o0;
    }

    public final void m() {
        setSelectedItemPosition(this.q0 - this.o0);
    }

    public final void n() {
        ArrayList arrayList = new ArrayList();
        for (int i = this.o0; i <= this.p0; i++) {
            arrayList.add(Integer.valueOf(i));
        }
        super.setData(arrayList);
    }

    @Override // com.aigestudio.wheelpicker.WheelPicker, com.aigestudio.wheelpicker.IWheelPicker
    public void setData(List list) {
        throw new UnsupportedOperationException("You can not invoke setData in WheelYearPicker");
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelYearPicker
    public void setSelectedYear(int i) {
        this.q0 = i;
        m();
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelYearPicker
    public void setYearEnd(int i) {
        this.p0 = i;
        n();
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelYearPicker
    public void setYearFrame(int i, int i2) {
        this.o0 = i;
        this.p0 = i2;
        this.q0 = getCurrentYear();
        n();
        m();
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelYearPicker
    public void setYearStart(int i) {
        this.o0 = i;
        this.q0 = getCurrentYear();
        n();
        m();
    }

    public WheelYearPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.o0 = 1000;
        this.p0 = 3000;
        n();
        this.q0 = Calendar.getInstance().get(1);
        m();
    }
}
