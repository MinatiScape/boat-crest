package com.coveiot.android.femalewellness.wellnesscalendar.datepicker.models;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.core.content.ContextCompat;
import com.coveiot.android.femalewellness.R;
/* loaded from: classes4.dex */
public class CalendarStyleAttr {

    /* renamed from: a  reason: collision with root package name */
    public Typeface f4408a;
    public Drawable b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public float k;
    public float l;
    public float m;
    public boolean n = false;
    public int o = 0;
    public boolean p = false;
    public boolean q = true;

    public CalendarStyleAttr(Context context) {
        setDefAttributes(context);
    }

    public int getDefaultDateColor() {
        return this.h;
    }

    public int getDisableDateColor() {
        return this.i;
    }

    public Typeface getFonts() {
        return this.f4408a;
    }

    public Drawable getHeaderBg() {
        return this.b;
    }

    public int getRangeDateColor() {
        return this.j;
    }

    public int getRangeStripColor() {
        return this.e;
    }

    public int getSelectedDateCircleColor() {
        return this.f;
    }

    public int getSelectedDateColor() {
        return this.g;
    }

    public float getTextSizeDate() {
        return this.m;
    }

    public float getTextSizeTitle() {
        return this.k;
    }

    public float getTextSizeWeek() {
        return this.l;
    }

    public int getTitleColor() {
        return this.c;
    }

    public int getWeekColor() {
        return this.d;
    }

    public int getWeekOffset() {
        return this.o;
    }

    public boolean isEditable() {
        return this.q;
    }

    public boolean isEnabledPastDates() {
        return this.p;
    }

    public boolean isShouldEnabledTime() {
        return this.n;
    }

    public void setAttributes(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DateRangeMonthView, 0, 0);
            try {
                this.c = obtainStyledAttributes.getColor(R.styleable.DateRangeMonthView_title_color, this.c);
                this.b = obtainStyledAttributes.getDrawable(R.styleable.DateRangeMonthView_header_bg);
                this.d = obtainStyledAttributes.getColor(R.styleable.DateRangeMonthView_week_color, this.d);
                this.e = obtainStyledAttributes.getColor(R.styleable.DateRangeMonthView_range_color, this.e);
                this.f = obtainStyledAttributes.getColor(R.styleable.DateRangeMonthView_selected_date_circle_color, this.f);
                this.n = obtainStyledAttributes.getBoolean(R.styleable.DateRangeMonthView_enable_time_selection, false);
                this.p = obtainStyledAttributes.getBoolean(R.styleable.DateRangeMonthView_enable_past_date, false);
                this.q = obtainStyledAttributes.getBoolean(R.styleable.DateRangeMonthView_editable, true);
                this.k = obtainStyledAttributes.getDimension(R.styleable.DateRangeMonthView_text_size_title, this.k);
                this.l = obtainStyledAttributes.getDimension(R.styleable.DateRangeMonthView_text_size_week, this.l);
                this.m = obtainStyledAttributes.getDimension(R.styleable.DateRangeMonthView_text_size_date, this.m);
                this.g = obtainStyledAttributes.getColor(R.styleable.DateRangeMonthView_selected_date_color, this.g);
                this.h = obtainStyledAttributes.getColor(R.styleable.DateRangeMonthView_default_date_color, this.h);
                this.j = obtainStyledAttributes.getColor(R.styleable.DateRangeMonthView_range_date_color, this.j);
                this.i = obtainStyledAttributes.getColor(R.styleable.DateRangeMonthView_disable_date_color, this.i);
                setWeekOffset(obtainStyledAttributes.getColor(R.styleable.DateRangeMonthView_week_offset, 0));
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    public void setDefAttributes(Context context) {
        setTextSizeTitle(context.getResources().getDimension(R.dimen.text_size_title));
        setTextSizeWeek(context.getResources().getDimension(R.dimen.text_size_week));
        setTextSizeDate(context.getResources().getDimension(R.dimen.text_size_date));
        setTitleColor(ContextCompat.getColor(context, R.color.title_color));
        int i = R.color.range_bg_color;
        setWeekColor(ContextCompat.getColor(context, i));
        setRangeStripColor(ContextCompat.getColor(context, i));
        setSelectedDateCircleColor(ContextCompat.getColor(context, i));
        setSelectedDateColor(ContextCompat.getColor(context, R.color.white));
        setDefaultDateColor(ContextCompat.getColor(context, i));
        setRangeDateColor(ContextCompat.getColor(context, i));
        setDisableDateColor(ContextCompat.getColor(context, i));
    }

    public void setDefaultDateColor(int i) {
        this.h = i;
    }

    public void setDisableDateColor(int i) {
        this.i = i;
    }

    public void setEditable(boolean z) {
        this.q = z;
    }

    public void setEnabledPastDates(boolean z) {
        this.p = z;
    }

    public void setFonts(Typeface typeface) {
        this.f4408a = typeface;
    }

    public void setHeaderBg(Drawable drawable) {
        this.b = drawable;
    }

    public void setRangeDateColor(int i) {
        this.j = i;
    }

    public void setRangeStripColor(int i) {
        this.e = i;
    }

    public void setSelectedDateCircleColor(int i) {
        this.f = i;
    }

    public void setSelectedDateColor(int i) {
        this.g = i;
    }

    public void setShouldEnabledTime(boolean z) {
        this.n = z;
    }

    public void setTextSizeDate(float f) {
        this.m = f;
    }

    public void setTextSizeTitle(float f) {
        this.k = f;
    }

    public void setTextSizeWeek(float f) {
        this.l = f;
    }

    public void setTitleColor(int i) {
        this.c = i;
    }

    public void setWeekColor(int i) {
        this.d = i;
    }

    public void setWeekOffset(int i) {
        if (i >= 0 && i <= 6) {
            this.o = i;
            return;
        }
        throw new RuntimeException("Week offset can only be between 0 to 6. 0->Sun, 1->Mon, 2->Tue, 3->Wed, 4->Thu, 5->Fri, 6->Sat");
    }

    public CalendarStyleAttr(Context context, AttributeSet attributeSet) {
        setDefAttributes(context);
        setAttributes(context, attributeSet);
    }
}
