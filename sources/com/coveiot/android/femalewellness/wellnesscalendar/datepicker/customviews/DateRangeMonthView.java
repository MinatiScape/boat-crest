package com.coveiot.android.femalewellness.wellnesscalendar.datepicker.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.coveiot.android.femalewellness.R;
import com.coveiot.android.femalewellness.Utils;
import com.coveiot.android.femalewellness.wellnesscalendar.AppPreferenceManager;
import com.coveiot.android.femalewellness.wellnesscalendar.Constants;
import com.coveiot.android.femalewellness.wellnesscalendar.DatePickerFragment;
import com.coveiot.android.femalewellness.wellnesscalendar.datepicker.DateRangeCalendarManager;
import com.coveiot.android.femalewellness.wellnesscalendar.datepicker.customviews.DateRangeCalendarView;
import com.coveiot.android.femalewellness.wellnesscalendar.datepicker.models.CalendarStyleAttr;
import com.coveiot.android.femalewellness.wellnesscalendar.datepicker.models.DayContainer;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
/* loaded from: classes4.dex */
public class DateRangeMonthView extends LinearLayout {
    public View.OnClickListener dayClickListener;
    public DatePickerFragment.OnDateItemClickListener h;
    public LinearLayout i;
    public LinearLayout j;
    public Calendar k;
    public CalendarStyleAttr l;
    public DateRangeCalendarView.CalendarListener m;
    public DateRangeCalendarManager n;
    public String o;
    public int p;

    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (DateRangeMonthView.this.l.isEditable()) {
                AppPreferenceManager.getInstance(DateRangeMonthView.this.getContext()).setSymptomDate(null);
                int intValue = ((Integer) view.getTag()).intValue();
                Calendar calendar = Calendar.getInstance();
                Date date = new Date();
                try {
                    date = DateRangeCalendarManager.SIMPLE_DATE_FORMAT.parse(String.valueOf(intValue));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                calendar.setTime(date);
                DateRangeMonthView.this.m.onFirstDateSelected(calendar);
                AppPreferenceManager.getInstance(DateRangeMonthView.this.getContext()).setSelectedDate(calendar.getTime().toString());
                DateRangeMonthView dateRangeMonthView = DateRangeMonthView.this;
                dateRangeMonthView.h.isInPeriodOvulationSlot(dateRangeMonthView.n.getPeriodDate(calendar, DateRangeMonthView.this.getContext()), DateRangeMonthView.this.n.getOvulationDate(calendar, DateRangeMonthView.this.getContext()), calendar);
                DateRangeMonthView.this.setSelectedRange();
            }
        }
    }

    static {
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
    }

    public DateRangeMonthView(Context context) {
        super(context);
        this.p = 6;
        this.dayClickListener = new a();
        j(context, null);
    }

    public final void d(DayContainer dayContainer) {
        dayContainer.tvDate.setBackgroundColor(0);
        dayContainer.strip.setBackgroundColor(0);
        dayContainer.rootView.setBackgroundColor(0);
        dayContainer.tvDate.setTextColor(this.l.getDisableDateColor());
        dayContainer.rootView.setVisibility(0);
        dayContainer.rootView.setOnClickListener(null);
    }

    public void drawCalendarForMonth(CalendarStyleAttr calendarStyleAttr, Calendar calendar, DateRangeCalendarManager dateRangeCalendarManager) {
        this.l = calendarStyleAttr;
        this.k = (Calendar) calendar.clone();
        this.n = dateRangeCalendarManager;
        m();
        setWeekTitleColor(calendarStyleAttr.getWeekColor());
        e(this.k);
    }

    public final void e(Calendar calendar) {
        Calendar calendar2 = (Calendar) calendar.clone();
        this.k = calendar2;
        calendar2.set(5, 1);
        this.k.set(10, 0);
        this.k.set(12, 0);
        this.k.set(13, 0);
        String[] stringArray = getContext().getResources().getStringArray(R.array.week_sun_sat);
        for (int i = 0; i < 7; i++) {
            ((CustomTextView) this.j.getChildAt(i)).setText(stringArray[(this.l.getWeekOffset() + i) % 7]);
        }
        int weekOffset = calendar.get(7) - this.l.getWeekOffset();
        if (weekOffset < 1) {
            weekOffset += 7;
        }
        calendar.add(5, (-weekOffset) + 1);
        for (int i2 = 0; i2 < this.i.getChildCount(); i2++) {
            LinearLayout linearLayout = (LinearLayout) this.i.getChildAt(i2);
            for (int i3 = 0; i3 < 7; i3++) {
                DayContainer dayContainer = new DayContainer((RelativeLayout) linearLayout.getChildAt(i3));
                dayContainer.tvDate.setText(String.valueOf(calendar.get(5)));
                if (this.l.getFonts() != null) {
                    dayContainer.tvDate.setTypeface(this.l.getFonts());
                }
                f(dayContainer, calendar);
                calendar.add(5, 1);
            }
        }
    }

    public final void f(DayContainer dayContainer, Calendar calendar) {
        Calendar calendar2 = Calendar.getInstance();
        int i = calendar.get(5);
        if (this.k.get(2) != calendar.get(2)) {
            i(dayContainer);
        } else if (calendar2.after(calendar) && calendar2.get(6) != calendar.get(6) && !this.l.isEnabledPastDates()) {
            d(dayContainer);
            dayContainer.tvDate.setText(String.valueOf(i));
        } else {
            if (this.n.isPeriodDate(calendar, getContext())) {
                k(dayContainer, true, calendar, true);
            } else if (this.n.isOvulationDate(calendar, getContext())) {
                k(dayContainer, true, calendar, false);
            } else {
                g(dayContainer);
            }
            dayContainer.tvDate.setText(String.valueOf(i));
        }
        l(dayContainer, calendar);
        dayContainer.rootView.setTag(Integer.valueOf(DayContainer.GetContainerKey(calendar)));
    }

    public final void g(DayContainer dayContainer) {
        dayContainer.tvDate.setBackgroundColor(0);
        dayContainer.tvDate.setPadding(0, 0, 0, 0);
        dayContainer.strip.setBackgroundColor(0);
        dayContainer.rootView.setBackgroundColor(0);
        dayContainer.tvDate.setTextColor(this.l.getDefaultDateColor());
        dayContainer.rootView.setVisibility(0);
        dayContainer.rootView.setOnClickListener(this.dayClickListener);
    }

    public String getSelectionType() {
        return this.o;
    }

    public final List<Calendar> h(Calendar calendar) {
        ArrayList arrayList = new ArrayList();
        if (UserDataManager.getInstance(getContext()).getWomenWellnessData() != null && UserDataManager.getInstance(getContext()).getWomenWellnessData().getLastPeriodYear() != 0) {
            int menstruationCycleLength = UserDataManager.getInstance(getContext()).getWomenWellnessData().getMenstruationCycleLength();
            Calendar calendar2 = (Calendar) Calendar.getInstance().clone();
            calendar2.set(2, UserDataManager.getInstance(getContext()).getWomenWellnessData().getLastPeriodMonth() - 1);
            calendar2.set(1, UserDataManager.getInstance(getContext()).getWomenWellnessData().getLastPeriodYear());
            calendar2.set(5, UserDataManager.getInstance(getContext()).getWomenWellnessData().getLastPeriodDay());
            calendar2.set(11, 0);
            calendar2.set(12, 0);
            calendar2.set(13, 0);
            calendar2.set(14, 0);
            Calendar calendar3 = (Calendar) calendar2.clone();
            calendar3.add(5, Math.round(((int) Utils.Companion.daysBetween(calendar2, calendar)) / menstruationCycleLength) * menstruationCycleLength);
            Calendar calendar4 = (Calendar) calendar3.clone();
            calendar4.add(5, -menstruationCycleLength);
            Calendar calendar5 = (Calendar) calendar3.clone();
            calendar5.add(5, menstruationCycleLength);
            if (calendar4.getTimeInMillis() >= calendar2.getTimeInMillis()) {
                arrayList.add(calendar4);
            }
            arrayList.add(calendar3);
            arrayList.add(calendar5);
        }
        return arrayList;
    }

    public final void i(DayContainer dayContainer) {
        dayContainer.tvDate.setText("");
        dayContainer.tvDate.setBackgroundColor(0);
        dayContainer.strip.setBackgroundColor(0);
        dayContainer.rootView.setBackgroundColor(0);
        dayContainer.rootView.setVisibility(4);
        dayContainer.rootView.setOnClickListener(null);
    }

    public final void j(Context context, AttributeSet attributeSet) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_calendar_month, (ViewGroup) this, true);
        this.i = (LinearLayout) linearLayout.findViewById(R.id.llDaysContainer);
        this.j = (LinearLayout) linearLayout.findViewById(R.id.llTitleWeekContainer);
        new Constants(context);
        this.h = (DatePickerFragment.OnDateItemClickListener) getContext();
        isInEditMode();
    }

    public final void k(DayContainer dayContainer, boolean z, Calendar calendar, boolean z2) {
        if (SessionManager.getInstance(getContext()).isWomenWellnessFTUNormalDay()) {
            Context context = getContext();
            int i = R.drawable.tv_default_bg;
            Drawable drawable = ContextCompat.getDrawable(context, i);
            dayContainer.tvDate.setBackgroundResource(i);
            dayContainer.strip.setBackground(drawable);
        } else if (z2) {
            Context context2 = getContext();
            int i2 = R.drawable.tv_period_bg;
            Drawable drawable2 = ContextCompat.getDrawable(context2, i2);
            if (z) {
                drawable2 = ContextCompat.getDrawable(getContext(), i2);
            }
            dayContainer.strip.setBackground(drawable2);
            if (z) {
                dayContainer.tvDate.setBackgroundResource(i2);
            }
        } else {
            Context context3 = getContext();
            int i3 = R.drawable.tv_ovulation_bg;
            Drawable drawable3 = ContextCompat.getDrawable(context3, i3);
            if (z) {
                drawable3 = ContextCompat.getDrawable(getContext(), i3);
            }
            dayContainer.strip.setBackground(drawable3);
            if (z) {
                dayContainer.tvDate.setBackgroundResource(i3);
            }
        }
        dayContainer.tvDate.setBackgroundColor(0);
        dayContainer.rootView.setBackgroundColor(0);
        dayContainer.tvDate.setTextColor(this.l.getRangeDateColor());
        dayContainer.rootView.setVisibility(0);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) dayContainer.strip.getLayoutParams();
        layoutParams.setMargins(10, 0, 10, 0);
        dayContainer.strip.setLayoutParams(layoutParams);
        dayContainer.rootView.setOnClickListener(this.dayClickListener);
    }

    public final void l(DayContainer dayContainer, Calendar calendar) {
        Date date = new Date();
        Calendar calendar2 = Calendar.getInstance();
        String symptomDate = AppPreferenceManager.getInstance(getContext()).getSymptomDate();
        String selectedDate = AppPreferenceManager.getInstance(getContext()).getSelectedDate();
        if (symptomDate != null && !symptomDate.isEmpty()) {
            try {
                date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(symptomDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (selectedDate != null) {
            try {
                date = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).parse(selectedDate);
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
        }
        calendar2.setTime(date);
        if (calendar2.get(6) == calendar.get(6) && calendar2.get(2) == calendar.get(2) && calendar2.get(1) == calendar.get(1)) {
            dayContainer.tvDate.setBackgroundResource(R.drawable.tv_selected_date);
        }
    }

    public final void m() {
        for (int i = 0; i < this.j.getChildCount(); i++) {
            ((CustomTextView) this.j.getChildAt(i)).setTypeface(this.l.getFonts());
        }
    }

    public void resetAllSelectedViews() {
        this.n.setPeriodStartDate(null);
        this.n.setPeriodEndDate(null);
        e(this.k);
    }

    public void setCalendarListener(DateRangeCalendarView.CalendarListener calendarListener) {
        this.m = calendarListener;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setListeners() {
        /*
            r6 = this;
            java.util.Calendar r0 = java.util.Calendar.getInstance()
            java.lang.Object r0 = r0.clone()
            java.util.Calendar r0 = (java.util.Calendar) r0
            android.content.Context r1 = r6.getContext()
            com.coveiot.android.femalewellness.wellnesscalendar.AppPreferenceManager r1 = com.coveiot.android.femalewellness.wellnesscalendar.AppPreferenceManager.getInstance(r1)
            java.lang.String r1 = r1.getSelectedDate()
            r2 = 0
            if (r1 == 0) goto L2b
            java.text.SimpleDateFormat r3 = new java.text.SimpleDateFormat
            java.util.Locale r4 = java.util.Locale.ENGLISH
            java.lang.String r5 = "EE MMM dd HH:mm:ss z yyyy"
            r3.<init>(r5, r4)
            java.util.Date r1 = r3.parse(r1)     // Catch: java.text.ParseException -> L27
            goto L2c
        L27:
            r1 = move-exception
            r1.printStackTrace()
        L2b:
            r1 = r2
        L2c:
            if (r1 != 0) goto L43
            java.util.Date r1 = r0.getTime()
            java.text.SimpleDateFormat r3 = com.coveiot.android.femalewellness.wellnesscalendar.datepicker.DateRangeCalendarManager.SIMPLE_DATE_FORMAT     // Catch: java.text.ParseException -> L3f
            java.lang.String r3 = r3.format(r1)     // Catch: java.text.ParseException -> L3f
            java.text.SimpleDateFormat r4 = com.coveiot.android.femalewellness.wellnesscalendar.datepicker.DateRangeCalendarManager.SIMPLE_DATE_FORMAT     // Catch: java.text.ParseException -> L3f
            java.util.Date r1 = r4.parse(r3)     // Catch: java.text.ParseException -> L3f
            goto L43
        L3f:
            r3 = move-exception
            r3.printStackTrace()
        L43:
            r0.setTime(r1)
            r1 = 2
            int r3 = r0.get(r1)
            java.util.Calendar r4 = r6.k
            int r1 = r4.get(r1)
            if (r3 != r1) goto L6d
            com.coveiot.android.femalewellness.wellnesscalendar.DatePickerFragment$OnDateItemClickListener r1 = r6.h
            com.coveiot.android.femalewellness.wellnesscalendar.datepicker.DateRangeCalendarManager r2 = r6.n
            android.content.Context r3 = r6.getContext()
            java.util.Calendar r2 = r2.getPeriodDate(r0, r3)
            com.coveiot.android.femalewellness.wellnesscalendar.datepicker.DateRangeCalendarManager r3 = r6.n
            android.content.Context r4 = r6.getContext()
            java.util.Calendar r3 = r3.getOvulationDate(r0, r4)
            r1.isInPeriodOvulationSlot(r2, r3, r0)
            goto L72
        L6d:
            com.coveiot.android.femalewellness.wellnesscalendar.DatePickerFragment$OnDateItemClickListener r0 = r6.h
            r0.isInPeriodOvulationSlot(r2, r2, r2)
        L72:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.femalewellness.wellnesscalendar.datepicker.customviews.DateRangeMonthView.setListeners():void");
    }

    public void setSelectedRange() {
        List<Calendar> h = h(this.k);
        AppPreferenceManager.getInstance(getContext()).setKeyFirstTime(Boolean.FALSE);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < h.size(); i++) {
            int menstruationPeriodLength = UserDataManager.getInstance(getContext()).getWomenWellnessData().getMenstruationPeriodLength();
            Calendar calendar = (Calendar) h.get(i).clone();
            calendar.add(6, menstruationPeriodLength);
            arrayList.add(calendar);
            Calendar calendar2 = (Calendar) h.get(i).clone();
            int menstruationCycleLength = UserDataManager.getInstance(getContext()).getWomenWellnessData().getMenstruationCycleLength();
            Utils.Companion companion = Utils.Companion;
            int ovulationDaysConst = (menstruationCycleLength - companion.getOvulationDaysConst()) - companion.getDaysBeforeForOvulation();
            this.p = ovulationDaysConst;
            calendar2.add(6, ovulationDaysConst);
            arrayList3.add(calendar2);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(calendar2.getTime());
            calendar3.add(5, companion.getDaysBeforeForOvulation() + companion.getDaysAfterForOvulation());
            arrayList2.add(calendar3);
        }
        this.n.setPeriodStartDate(h);
        this.n.setPeriodEndDate(arrayList);
        this.n.setOvulationEndDate(arrayList2);
        this.n.setOvulationStartDate(arrayList3);
        e(this.k);
    }

    public void setSelectionType(String str) {
        this.o = str;
    }

    public void setWeekTitleColor(int i) {
        for (int i2 = 0; i2 < this.j.getChildCount(); i2++) {
            ((CustomTextView) this.j.getChildAt(i2)).setTextColor(i);
        }
    }

    public DateRangeMonthView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.p = 6;
        this.dayClickListener = new a();
        j(context, attributeSet);
    }

    public DateRangeMonthView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.p = 6;
        this.dayClickListener = new a();
        j(context, attributeSet);
    }

    @TargetApi(21)
    public DateRangeMonthView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.p = 6;
        this.dayClickListener = new a();
        j(context, attributeSet);
    }
}
