package com.aigestudio.wheelpicker.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.aigestudio.wheelpicker.IDebug;
import com.aigestudio.wheelpicker.IWheelPicker;
import com.aigestudio.wheelpicker.R;
import com.aigestudio.wheelpicker.WheelPicker;
import com.jstyle.blesdk1860.constant.BleConst;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public class WheelDatePicker extends LinearLayout implements WheelPicker.OnItemSelectedListener, IDebug, IWheelPicker, IWheelDatePicker, IWheelYearPicker, IWheelMonthPicker, IWheelDayPicker {
    public static final SimpleDateFormat r = new SimpleDateFormat("yyyy-M-d", Locale.getDefault());
    public WheelYearPicker h;
    public WheelMonthPicker i;
    public WheelDayPicker j;
    public OnDateSelectedListener k;
    public TextView l;
    public TextView m;
    public TextView n;
    public int o;
    public int p;
    public int q;

    /* loaded from: classes.dex */
    public interface OnDateSelectedListener {
        void onDateSelected(WheelDatePicker wheelDatePicker, Date date);
    }

    public WheelDatePicker(Context context) {
        this(context, null);
    }

    public final void a() {
        List data = this.h.getData();
        String valueOf = String.valueOf(data.get(data.size() - 1));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < valueOf.length(); i++) {
            sb.append(BleConst.GetDeviceTime);
        }
        this.h.setMaximumWidthText(sb.toString());
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDatePicker
    public Date getCurrentDate() {
        try {
            return r.parse(this.o + "-" + this.p + "-" + this.q);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDayPicker
    public int getCurrentDay() {
        return this.j.getCurrentDay();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    @Deprecated
    public int getCurrentItemPosition() {
        throw new UnsupportedOperationException("You can not get position of current item fromWheelDatePicker");
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelMonthPicker
    public int getCurrentMonth() {
        return this.i.getCurrentMonth();
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelYearPicker
    public int getCurrentYear() {
        return this.h.getCurrentYear();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public int getCurtainColor() {
        if (this.h.getCurtainColor() == this.i.getCurtainColor() && this.i.getCurtainColor() == this.j.getCurtainColor()) {
            return this.h.getCurtainColor();
        }
        throw new RuntimeException("Can not get curtain color correctly from WheelDatePicker!");
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    @Deprecated
    public List getData() {
        throw new UnsupportedOperationException("You can not get data source from WheelDatePicker");
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public int getIndicatorColor() {
        if (this.h.getCurtainColor() == this.i.getCurtainColor() && this.i.getCurtainColor() == this.j.getCurtainColor()) {
            return this.h.getCurtainColor();
        }
        throw new RuntimeException("Can not get indicator color correctly from WheelDatePicker!");
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public int getIndicatorSize() {
        if (this.h.getIndicatorSize() == this.i.getIndicatorSize() && this.i.getIndicatorSize() == this.j.getIndicatorSize()) {
            return this.h.getIndicatorSize();
        }
        throw new RuntimeException("Can not get indicator size correctly from WheelDatePicker!");
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    @Deprecated
    public int getItemAlign() {
        throw new UnsupportedOperationException("You can not get item align from WheelDatePicker");
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDatePicker
    public int getItemAlignDay() {
        return this.j.getItemAlign();
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDatePicker
    public int getItemAlignMonth() {
        return this.i.getItemAlign();
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDatePicker
    public int getItemAlignYear() {
        return this.h.getItemAlign();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public int getItemSpace() {
        if (this.h.getItemSpace() == this.i.getItemSpace() && this.i.getItemSpace() == this.j.getItemSpace()) {
            return this.h.getItemSpace();
        }
        throw new RuntimeException("Can not get item space correctly from WheelDatePicker!");
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public int getItemTextColor() {
        if (this.h.getItemTextColor() == this.i.getItemTextColor() && this.i.getItemTextColor() == this.j.getItemTextColor()) {
            return this.h.getItemTextColor();
        }
        throw new RuntimeException("Can not get color of item text correctly fromWheelDatePicker!");
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public int getItemTextSize() {
        if (this.h.getItemTextSize() == this.i.getItemTextSize() && this.i.getItemTextSize() == this.j.getItemTextSize()) {
            return this.h.getItemTextSize();
        }
        throw new RuntimeException("Can not get size of item text correctly fromWheelDatePicker!");
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    @Deprecated
    public String getMaximumWidthText() {
        throw new UnsupportedOperationException("You can not get maximum width text fromWheelDatePicker");
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    @Deprecated
    public int getMaximumWidthTextPosition() {
        throw new UnsupportedOperationException("You can not get maximum width text positionfrom WheelDatePicker");
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDayPicker
    public int getMonth() {
        return getSelectedMonth();
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDayPicker
    public int getSelectedDay() {
        return this.j.getSelectedDay();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    @Deprecated
    public int getSelectedItemPosition() {
        throw new UnsupportedOperationException("You can not get position of selected item fromWheelDatePicker");
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public int getSelectedItemTextColor() {
        if (this.h.getSelectedItemTextColor() == this.i.getSelectedItemTextColor() && this.i.getSelectedItemTextColor() == this.j.getSelectedItemTextColor()) {
            return this.h.getSelectedItemTextColor();
        }
        throw new RuntimeException("Can not get color of selected item text correctly fromWheelDatePicker!");
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelMonthPicker
    public int getSelectedMonth() {
        return this.i.getSelectedMonth();
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelYearPicker
    public int getSelectedYear() {
        return this.h.getSelectedYear();
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDatePicker
    public TextView getTextViewDay() {
        return this.n;
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDatePicker
    public TextView getTextViewMonth() {
        return this.m;
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDatePicker
    public TextView getTextViewYear() {
        return this.l;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public Typeface getTypeface() {
        if (this.h.getTypeface().equals(this.i.getTypeface()) && this.i.getTypeface().equals(this.j.getTypeface())) {
            return this.h.getTypeface();
        }
        throw new RuntimeException("Can not get typeface correctly from WheelDatePicker!");
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public int getVisibleItemCount() {
        if (this.h.getVisibleItemCount() == this.i.getVisibleItemCount() && this.i.getVisibleItemCount() == this.j.getVisibleItemCount()) {
            return this.h.getVisibleItemCount();
        }
        throw new ArithmeticException("Can not get visible item count correctly fromWheelDatePicker!");
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDatePicker
    public WheelDayPicker getWheelDayPicker() {
        return this.j;
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDatePicker
    public WheelMonthPicker getWheelMonthPicker() {
        return this.i;
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDatePicker
    public WheelYearPicker getWheelYearPicker() {
        return this.h;
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDayPicker
    public int getYear() {
        return getSelectedYear();
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelYearPicker
    public int getYearEnd() {
        return this.h.getYearEnd();
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelYearPicker
    public int getYearStart() {
        return this.h.getYearStart();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public boolean hasAtmospheric() {
        return this.h.hasAtmospheric() && this.i.hasAtmospheric() && this.j.hasAtmospheric();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public boolean hasCurtain() {
        return this.h.hasCurtain() && this.i.hasCurtain() && this.j.hasCurtain();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public boolean hasIndicator() {
        return this.h.hasIndicator() && this.i.hasIndicator() && this.j.hasIndicator();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    @Deprecated
    public boolean hasSameWidth() {
        throw new UnsupportedOperationException("You don't need to set same width forWheelDatePicker");
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public boolean isCurved() {
        return this.h.isCurved() && this.i.isCurved() && this.j.isCurved();
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public boolean isCyclic() {
        return this.h.isCyclic() && this.i.isCyclic() && this.j.isCyclic();
    }

    @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
    public void onItemSelected(WheelPicker wheelPicker, Object obj, int i) {
        if (wheelPicker.getId() == R.id.wheel_date_picker_year) {
            int intValue = ((Integer) obj).intValue();
            this.o = intValue;
            this.j.setYear(intValue);
        } else if (wheelPicker.getId() == R.id.wheel_date_picker_month) {
            int intValue2 = ((Integer) obj).intValue();
            this.p = intValue2;
            this.j.setMonth(intValue2);
        }
        this.q = this.j.getCurrentDay();
        String str = this.o + "-" + this.p + "-" + this.q;
        OnDateSelectedListener onDateSelectedListener = this.k;
        if (onDateSelectedListener != null) {
            try {
                onDateSelectedListener.onDateSelected(this, r.parse(str));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setAtmospheric(boolean z) {
        this.h.setAtmospheric(z);
        this.i.setAtmospheric(z);
        this.j.setAtmospheric(z);
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setCurtain(boolean z) {
        this.h.setCurtain(z);
        this.i.setCurtain(z);
        this.j.setCurtain(z);
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setCurtainColor(int i) {
        this.h.setCurtainColor(i);
        this.i.setCurtainColor(i);
        this.j.setCurtainColor(i);
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setCurved(boolean z) {
        this.h.setCurved(z);
        this.i.setCurved(z);
        this.j.setCurved(z);
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setCyclic(boolean z) {
        this.h.setCyclic(z);
        this.i.setCyclic(z);
        this.j.setCyclic(z);
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    @Deprecated
    public void setData(List list) {
        throw new UnsupportedOperationException("You don't need to set data source forWheelDatePicker");
    }

    @Override // com.aigestudio.wheelpicker.IDebug
    public void setDebug(boolean z) {
        this.h.setDebug(z);
        this.i.setDebug(z);
        this.j.setDebug(z);
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setIndicator(boolean z) {
        this.h.setIndicator(z);
        this.i.setIndicator(z);
        this.j.setIndicator(z);
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setIndicatorColor(int i) {
        this.h.setIndicatorColor(i);
        this.i.setIndicatorColor(i);
        this.j.setIndicatorColor(i);
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setIndicatorSize(int i) {
        this.h.setIndicatorSize(i);
        this.i.setIndicatorSize(i);
        this.j.setIndicatorSize(i);
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    @Deprecated
    public void setItemAlign(int i) {
        throw new UnsupportedOperationException("You don't need to set item align forWheelDatePicker");
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDatePicker
    public void setItemAlignDay(int i) {
        this.j.setItemAlign(i);
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDatePicker
    public void setItemAlignMonth(int i) {
        this.i.setItemAlign(i);
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDatePicker
    public void setItemAlignYear(int i) {
        this.h.setItemAlign(i);
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setItemSpace(int i) {
        this.h.setItemSpace(i);
        this.i.setItemSpace(i);
        this.j.setItemSpace(i);
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setItemTextColor(int i) {
        this.h.setItemTextColor(i);
        this.i.setItemTextColor(i);
        this.j.setItemTextColor(i);
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setItemTextSize(int i) {
        this.h.setItemTextSize(i);
        this.i.setItemTextSize(i);
        this.j.setItemTextSize(i);
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    @Deprecated
    public void setMaximumWidthText(String str) {
        throw new UnsupportedOperationException("You don't need to set maximum width text forWheelDatePicker");
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    @Deprecated
    public void setMaximumWidthTextPosition(int i) {
        throw new UnsupportedOperationException("You don't need to set maximum width textposition for WheelDatePicker");
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDayPicker
    public void setMonth(int i) {
        this.p = i;
        this.i.setSelectedMonth(i);
        this.j.setMonth(i);
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDatePicker
    public void setOnDateSelectedListener(OnDateSelectedListener onDateSelectedListener) {
        this.k = onDateSelectedListener;
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    @Deprecated
    public void setOnItemSelectedListener(WheelPicker.OnItemSelectedListener onItemSelectedListener) {
        throw new UnsupportedOperationException("You can not set OnItemSelectedListener forWheelDatePicker");
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    @Deprecated
    public void setOnWheelChangeListener(WheelPicker.OnWheelChangeListener onWheelChangeListener) {
        throw new UnsupportedOperationException("WheelDatePicker unsupport setOnWheelChangeListener");
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    @Deprecated
    public void setSameWidth(boolean z) {
        throw new UnsupportedOperationException("You don't need to set same width forWheelDatePicker");
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDayPicker
    public void setSelectedDay(int i) {
        this.q = i;
        this.j.setSelectedDay(i);
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    @Deprecated
    public void setSelectedItemPosition(int i) {
        throw new UnsupportedOperationException("You can not set position of selected item forWheelDatePicker");
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setSelectedItemTextColor(int i) {
        this.h.setSelectedItemTextColor(i);
        this.i.setSelectedItemTextColor(i);
        this.j.setSelectedItemTextColor(i);
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelMonthPicker
    public void setSelectedMonth(int i) {
        this.p = i;
        this.i.setSelectedMonth(i);
        this.j.setMonth(i);
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelYearPicker
    public void setSelectedYear(int i) {
        this.o = i;
        this.h.setSelectedYear(i);
        this.j.setYear(i);
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setTypeface(Typeface typeface) {
        this.h.setTypeface(typeface);
        this.i.setTypeface(typeface);
        this.j.setTypeface(typeface);
    }

    @Override // com.aigestudio.wheelpicker.IWheelPicker
    public void setVisibleItemCount(int i) {
        this.h.setVisibleItemCount(i);
        this.i.setVisibleItemCount(i);
        this.j.setVisibleItemCount(i);
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDayPicker
    public void setYear(int i) {
        this.o = i;
        this.h.setSelectedYear(i);
        this.j.setYear(i);
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelDayPicker
    public void setYearAndMonth(int i, int i2) {
        this.o = i;
        this.p = i2;
        this.h.setSelectedYear(i);
        this.i.setSelectedMonth(i2);
        this.j.setYearAndMonth(i, i2);
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelYearPicker
    public void setYearEnd(int i) {
        this.h.setYearEnd(i);
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelYearPicker
    public void setYearFrame(int i, int i2) {
        this.h.setYearFrame(i, i2);
    }

    @Override // com.aigestudio.wheelpicker.widgets.IWheelYearPicker
    public void setYearStart(int i) {
        this.h.setYearStart(i);
    }

    public WheelDatePicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.view_wheel_date_picker, this);
        this.h = (WheelYearPicker) findViewById(R.id.wheel_date_picker_year);
        this.i = (WheelMonthPicker) findViewById(R.id.wheel_date_picker_month);
        this.j = (WheelDayPicker) findViewById(R.id.wheel_date_picker_day);
        this.h.setOnItemSelectedListener(this);
        this.i.setOnItemSelectedListener(this);
        this.j.setOnItemSelectedListener(this);
        a();
        this.i.setMaximumWidthText("00");
        this.j.setMaximumWidthText("00");
        this.l = (TextView) findViewById(R.id.wheel_date_picker_year_tv);
        this.m = (TextView) findViewById(R.id.wheel_date_picker_month_tv);
        this.n = (TextView) findViewById(R.id.wheel_date_picker_day_tv);
        this.o = this.h.getCurrentYear();
        this.p = this.i.getCurrentMonth();
        this.q = this.j.getCurrentDay();
    }
}
