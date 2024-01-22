package com.aigestudio.wheelpicker.widgets;

import android.content.Context;
import android.util.AttributeSet;
import com.aigestudio.wheelpicker.WheelPicker;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class WheelDayPicker extends WheelPicker implements IWheelDayPicker {
    public static final Map<Integer, List<Integer>> s0 = new HashMap();
    public Calendar o0;
    public int p0;
    public int q0;
    public int r0;

    public WheelDayPicker(Context context) {
        this(context, null);
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDayPicker
    public int getCurrentDay() {
        return Integer.valueOf(String.valueOf(getData().get(getCurrentItemPosition()))).intValue();
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDayPicker
    public int getMonth() {
        return this.q0;
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDayPicker
    public int getSelectedDay() {
        return this.r0;
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDayPicker
    public int getYear() {
        return this.p0;
    }

    public final void m() {
        this.o0.set(1, this.p0);
        this.o0.set(2, this.q0);
        int actualMaximum = this.o0.getActualMaximum(5);
        List<Integer> list = s0.get(Integer.valueOf(actualMaximum));
        if (list == null) {
            list = new ArrayList<>();
            for (int i = 1; i <= actualMaximum; i++) {
                list.add(Integer.valueOf(i));
            }
            s0.put(Integer.valueOf(actualMaximum), list);
        }
        super.setData(list);
    }

    public final void n() {
        setSelectedItemPosition(this.r0 - 1);
    }

    @Override // com.aigestudio.wheelpicker.WheelPicker, com.aigestudio.wheelpicker.IWheelPicker
    public void setData(List list) {
        throw new UnsupportedOperationException("You can not invoke setData in WheelDayPicker");
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDayPicker
    public void setMonth(int i) {
        this.q0 = i - 1;
        m();
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDayPicker
    public void setSelectedDay(int i) {
        this.r0 = i;
        n();
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDayPicker
    public void setYear(int i) {
        this.p0 = i;
        m();
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDayPicker
    public void setYearAndMonth(int i, int i2) {
        this.p0 = i;
        this.q0 = i2 - 1;
        m();
    }

    public WheelDayPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Calendar calendar = Calendar.getInstance();
        this.o0 = calendar;
        this.p0 = calendar.get(1);
        this.q0 = this.o0.get(2);
        m();
        this.r0 = this.o0.get(5);
        n();
    }
}
