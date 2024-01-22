package com.aigestudio.wheelpicker.widgets;

import android.content.Context;
import android.util.AttributeSet;
import com.aigestudio.wheelpicker.WheelPicker;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/* loaded from: classes.dex */
public class WheelMonthPicker extends WheelPicker implements IWheelMonthPicker {
    public int o0;

    public WheelMonthPicker(Context context) {
        this(context, null);
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelMonthPicker
    public int getCurrentMonth() {
        return Integer.valueOf(String.valueOf(getData().get(getCurrentItemPosition()))).intValue();
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelMonthPicker
    public int getSelectedMonth() {
        return this.o0;
    }

    public final void m() {
        setSelectedItemPosition(this.o0 - 1);
    }

    @Override // com.aigestudio.wheelpicker.WheelPicker, com.aigestudio.wheelpicker.IWheelPicker
    public void setData(List list) {
        throw new UnsupportedOperationException("You can not invoke setData in WheelMonthPicker");
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelMonthPicker
    public void setSelectedMonth(int i) {
        this.o0 = i;
        m();
    }

    public WheelMonthPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i <= 12; i++) {
            arrayList.add(Integer.valueOf(i));
        }
        super.setData(arrayList);
        this.o0 = Calendar.getInstance().get(2) + 1;
        m();
    }
}
