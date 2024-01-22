package com.aigestudio.wheelpicker.widgets;

import android.widget.TextView;
import com.aigestudio.wheelpicker.widgets.WheelDatePicker;
import java.util.Date;
/* loaded from: classes.dex */
public interface IWheelDatePicker {
    Date getCurrentDate();

    int getItemAlignDay();

    int getItemAlignMonth();

    int getItemAlignYear();

    TextView getTextViewDay();

    TextView getTextViewMonth();

    TextView getTextViewYear();

    WheelDayPicker getWheelDayPicker();

    WheelMonthPicker getWheelMonthPicker();

    WheelYearPicker getWheelYearPicker();

    void setItemAlignDay(int i);

    void setItemAlignMonth(int i);

    void setItemAlignYear(int i);

    void setOnDateSelectedListener(WheelDatePicker.OnDateSelectedListener onDateSelectedListener);
}
