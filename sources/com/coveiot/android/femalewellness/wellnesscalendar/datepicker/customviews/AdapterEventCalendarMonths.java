package com.coveiot.android.femalewellness.wellnesscalendar.datepicker.customviews;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.coveiot.android.femalewellness.R;
import com.coveiot.android.femalewellness.wellnesscalendar.AppPreferenceManager;
import com.coveiot.android.femalewellness.wellnesscalendar.DatePickerFragment;
import com.coveiot.android.femalewellness.wellnesscalendar.datepicker.DateRangeCalendarManager;
import com.coveiot.android.femalewellness.wellnesscalendar.datepicker.customviews.DateRangeCalendarView;
import com.coveiot.android.femalewellness.wellnesscalendar.datepicker.models.CalendarStyleAttr;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.WomenWellnessData;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/* loaded from: classes4.dex */
public class AdapterEventCalendarMonths extends PagerAdapter {
    public String c;
    public DatePickerFragment.OnDateItemClickListener d;
    public Context e;
    public List<Calendar> f;
    public CalendarStyleAttr g;
    public DateRangeCalendarView.CalendarListener h;
    public DateRangeCalendarManager i;
    public Handler j;
    public DateRangeMonthView k;
    public DateRangeCalendarView.CalendarListener l;

    /* loaded from: classes4.dex */
    public class a implements DateRangeCalendarView.CalendarListener {

        /* renamed from: com.coveiot.android.femalewellness.wellnesscalendar.datepicker.customviews.AdapterEventCalendarMonths$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        public class RunnableC0271a implements Runnable {
            public RunnableC0271a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                AdapterEventCalendarMonths.this.notifyDataSetChanged();
            }
        }

        public a() {
        }

        @Override // com.coveiot.android.femalewellness.wellnesscalendar.datepicker.customviews.DateRangeCalendarView.CalendarListener
        public void onDateRangeSelected(Calendar calendar, Calendar calendar2) {
            AdapterEventCalendarMonths.this.j.postDelayed(new RunnableC0271a(), 50L);
            if (AdapterEventCalendarMonths.this.h != null) {
                AdapterEventCalendarMonths.this.h.onDateRangeSelected(calendar, calendar2);
            }
        }

        @Override // com.coveiot.android.femalewellness.wellnesscalendar.datepicker.customviews.DateRangeCalendarView.CalendarListener
        public void onFirstDateSelected(Calendar calendar) {
            if (AdapterEventCalendarMonths.this.h != null) {
                AdapterEventCalendarMonths.this.h.onFirstDateSelected(calendar);
            }
        }
    }

    /* loaded from: classes4.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AdapterEventCalendarMonths.this.notifyDataSetChanged();
        }
    }

    public AdapterEventCalendarMonths(Context context, List<Calendar> list, CalendarStyleAttr calendarStyleAttr, String str) {
        this.f = new ArrayList();
        new ArrayList();
        this.l = new a();
        this.e = context;
        this.f = list;
        this.g = calendarStyleAttr;
        this.i = DateRangeCalendarManager.getINSTANCE();
        this.j = new Handler();
        this.c = str;
    }

    public final Calendar d(Calendar calendar) {
        Calendar calendar2 = (Calendar) calendar.clone();
        calendar2.set(5, 1);
        return calendar2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.f.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(this.e).inflate(R.layout.layout_pager_month, viewGroup, false);
        this.k = (DateRangeMonthView) viewGroup2.findViewById(R.id.cvEventCalendarView);
        onPageSelect(i);
        viewGroup.addView(viewGroup2);
        return viewGroup2;
    }

    public void invalidateCalendar() {
        this.j.postDelayed(new b(), 50L);
    }

    public boolean isEditable() {
        return this.g.isEditable();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view == obj;
    }

    public void onPageChange(int i) {
        DatePickerFragment.OnDateItemClickListener onDateItemClickListener = this.d;
        if (onDateItemClickListener != null) {
            onDateItemClickListener.isInPeriodOvulationSlot(null, null, null);
        }
    }

    public void onPageSelect(int i) {
        Calendar calendar = this.f.get(i);
        this.k.setSelectionType(this.c);
        this.k.drawCalendarForMonth(this.g, d(calendar), this.i);
        this.k.setCalendarListener(this.l);
        this.d = (DatePickerFragment.OnDateItemClickListener) this.e;
        this.g.setEditable(true);
        int i2 = d(calendar).get(2);
        LogHelper.d("currentMonth", " :" + i2);
        if (AppPreferenceManager.getInstance(this.e).isKeyFirstTime()) {
            WomenWellnessData womenWellnessData = UserDataManager.getInstance(this.e).getWomenWellnessData();
            if (womenWellnessData != null && womenWellnessData.getLastPeriodYear() != 0) {
                this.k.setSelectedRange();
            }
            this.k.setListeners();
        }
    }

    public void resetAllSelectedViews() {
        this.i.setPeriodStartDate(null);
        this.i.setPeriodEndDate(null);
        this.i.setOvulationEndDate(null);
        this.i.setOvulationStartDate(null);
        notifyDataSetChanged();
    }

    public void setCalendarListener(DateRangeCalendarView.CalendarListener calendarListener) {
        this.h = calendarListener;
    }

    public void setEditable(boolean z) {
        this.g.setEditable(z);
        notifyDataSetChanged();
    }
}
