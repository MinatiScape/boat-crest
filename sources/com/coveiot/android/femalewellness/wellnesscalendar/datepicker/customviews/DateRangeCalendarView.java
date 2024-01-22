package com.coveiot.android.femalewellness.wellnesscalendar.datepicker.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.femalewellness.R;
import com.coveiot.android.femalewellness.Utils;
import com.coveiot.android.femalewellness.db.FemaleWellnessRepository;
import com.coveiot.android.femalewellness.wellnesscalendar.AppPreferenceManager;
import com.coveiot.android.femalewellness.wellnesscalendar.datepicker.models.CalendarStyleAttr;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.WomenWellnessData;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
/* loaded from: classes4.dex */
public class DateRangeCalendarView extends LinearLayout {
    public String h;
    public AppCompatImageView i;
    public AppCompatImageView j;
    public List<Calendar> k;
    public AdapterEventCalendarMonths l;
    public Locale m;
    public ViewPager n;
    public CalendarStyleAttr o;
    public CalendarListener p;
    public CustomTextView tvYearTitle;

    /* loaded from: classes4.dex */
    public interface CalendarListener {
        void onDateRangeSelected(Calendar calendar, Calendar calendar2);

        void onFirstDateSelected(Calendar calendar);
    }

    /* loaded from: classes4.dex */
    public interface MonthTitleClick {
        void OnMonthTitleClick(String str);
    }

    /* loaded from: classes4.dex */
    public class a implements ViewPager.OnPageChangeListener {
        public a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            AppPreferenceManager.getInstance(DateRangeCalendarView.this.getContext()).setSymptomDate(null);
            AppPreferenceManager.getInstance(DateRangeCalendarView.this.getContext()).setKeyFirstTime(Boolean.TRUE);
            DateRangeCalendarView.this.setCalendarYearTitle(i);
            DateRangeCalendarView.this.setNavigationHeader(i);
            if (DateRangeCalendarView.this.l != null) {
                DateRangeCalendarView.this.l.onPageChange(i);
                DateRangeCalendarView.this.l.onPageSelect(i);
            }
        }
    }

    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppPreferenceManager.getInstance(DateRangeCalendarView.this.getContext()).setKeyFirstTime(Boolean.TRUE);
            int currentItem = DateRangeCalendarView.this.n.getCurrentItem() - 1;
            if (currentItem > -1) {
                DateRangeCalendarView.this.n.setCurrentItem(currentItem);
            }
        }
    }

    /* loaded from: classes4.dex */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppPreferenceManager.getInstance(DateRangeCalendarView.this.getContext()).setKeyFirstTime(Boolean.TRUE);
            int currentItem = DateRangeCalendarView.this.n.getCurrentItem() + 1;
            if (currentItem < DateRangeCalendarView.this.k.size()) {
                DateRangeCalendarView.this.n.setCurrentItem(currentItem);
            }
        }
    }

    /* loaded from: classes4.dex */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((MonthTitleClick) DateRangeCalendarView.this.getContext()).OnMonthTitleClick(DateRangeCalendarView.this.tvYearTitle.getText().toString().split(HexStringBuilder.DEFAULT_SEPARATOR)[1]);
        }
    }

    public DateRangeCalendarView(Context context) {
        super(context);
        this.k = new ArrayList();
        f(context, null);
    }

    private int getMonthsListAndIndex() {
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        String oldestLMD = FemaleWellnessRepository.Companion.getInstance(getContext()).getOldestLMD();
        Calendar calendar2 = (Calendar) Calendar.getInstance().clone();
        Calendar calendar3 = (Calendar) calendar.clone();
        if (oldestLMD != null) {
            try {
                calendar3.setTime(new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(oldestLMD));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        WomenWellnessData womenWellnessData = UserDataManager.getInstance(getContext()).getWomenWellnessData();
        if (womenWellnessData != null && womenWellnessData.getLastPeriodYear() != 0) {
            Calendar calendar4 = (Calendar) calendar.clone();
            calendar4.set(5, womenWellnessData.getLastPeriodDay());
            calendar4.set(1, womenWellnessData.getLastPeriodYear());
            calendar4.set(2, womenWellnessData.getLastPeriodMonth() - 1);
            if (calendar3.getTimeInMillis() < calendar4.getTimeInMillis()) {
                calendar2.setTime(calendar3.getTime());
            } else {
                calendar2.setTime(calendar4.getTime());
            }
        }
        Utils.Companion companion = Utils.Companion;
        int monthsBetweenDates = companion.monthsBetweenDates(calendar2.getTime(), calendar.getTime()) + (12 - calendar.get(2)) + 12;
        SimpleDateFormat simpleDateFormat = companion.getSimpleDateFormat("dd/MM/yyyy");
        int i = -1;
        for (int i2 = 0; i2 < monthsBetweenDates; i2++) {
            Calendar calendar5 = (Calendar) calendar2.clone();
            this.k.add(calendar5);
            String symptomDate = AppPreferenceManager.getInstance(getContext()).getSymptomDate();
            if (symptomDate != null && !symptomDate.isEmpty()) {
                Calendar calendar6 = (Calendar) Calendar.getInstance().clone();
                try {
                    calendar6.setTime(simpleDateFormat.parse(AppPreferenceManager.getInstance(getContext()).getSymptomDate()));
                    if (calendar6.get(2) == calendar5.get(2) && calendar6.get(1) == calendar5.get(1)) {
                        i = i2;
                    }
                } catch (ParseException e2) {
                    e2.printStackTrace();
                }
            }
            if (i == -1 && calendar5.get(2) == calendar.get(2) && calendar5.get(1) == calendar.get(1)) {
                i = i2;
            }
            calendar2.add(2, 1);
        }
        int i3 = i != -1 ? i : 0;
        Collections.sort(this.k);
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCalendarYearTitle(int i) {
        Calendar calendar;
        String str = new DateFormatSymbols(this.m).getMonths()[this.k.get(i).get(2)];
        this.tvYearTitle.setText((str.substring(0, 1).toUpperCase() + ((Object) str.subSequence(1, str.length()))) + HexStringBuilder.DEFAULT_SEPARATOR + calendar.get(1));
        this.tvYearTitle.setTextColor(this.o.getTitleColor());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNavigationHeader(int i) {
        this.j.setImageResource(R.drawable.ic_female_wellness_rightarrow_white);
        this.i.setImageResource(R.drawable.ic_female_wellness_leftarrow_white);
        if (this.k.size() == 1) {
            this.i.setImageResource(R.drawable.ic_female_wellness_leftarrow_grey);
            this.j.setImageResource(R.drawable.ic_female_wellness_rightarrow_grey);
        } else if (i == 0) {
            this.i.setImageResource(R.drawable.ic_female_wellness_leftarrow_grey);
        } else if (i == this.k.size() - 1) {
            this.j.setImageResource(R.drawable.ic_female_wellness_rightarrow_grey);
        }
    }

    public final void f(Context context, AttributeSet attributeSet) {
        this.m = context.getResources().getConfiguration().locale;
        CalendarStyleAttr calendarStyleAttr = new CalendarStyleAttr(context, attributeSet);
        this.o = calendarStyleAttr;
        calendarStyleAttr.setTextSizeDate(12.0f);
        this.o.setTextSizeTitle(12.0f);
        this.o.setTextSizeWeek(14.0f);
        LayoutInflater.from(context).inflate(R.layout.layout_calendar_container, (ViewGroup) this, true);
        ((RelativeLayout) findViewById(R.id.rlHeaderCalendar)).setBackground(this.o.getHeaderBg());
        this.tvYearTitle = (CustomTextView) findViewById(R.id.tvYearTitle);
        this.i = (AppCompatImageView) findViewById(R.id.imgVNavLeft);
        this.j = (AppCompatImageView) findViewById(R.id.imgVNavRight);
        this.n = (ViewPager) findViewById(R.id.vpCalendar);
        this.k.clear();
        int monthsListAndIndex = getMonthsListAndIndex();
        AdapterEventCalendarMonths adapterEventCalendarMonths = new AdapterEventCalendarMonths(context, this.k, this.o, getSelectionType());
        this.l = adapterEventCalendarMonths;
        this.n.setAdapter(adapterEventCalendarMonths);
        this.n.setOffscreenPageLimit(0);
        this.n.setCurrentItem(monthsListAndIndex);
        setCalendarYearTitle(monthsListAndIndex);
        g();
    }

    public final void g() {
        this.n.addOnPageChangeListener(new a());
        this.i.setOnClickListener(new b());
        this.j.setOnClickListener(new c());
        this.tvYearTitle.setOnClickListener(new d());
    }

    public String getSelectionType() {
        return this.h;
    }

    public boolean isEditable() {
        return this.l.isEditable();
    }

    public void resetAllSelectedViews() {
        this.l.resetAllSelectedViews();
    }

    public void setCalendarListener(CalendarListener calendarListener) {
        this.p = calendarListener;
        this.l.setCalendarListener(calendarListener);
    }

    public void setEditable(boolean z) {
        this.l.setEditable(z);
    }

    public void setFonts(Typeface typeface) {
        this.tvYearTitle.setTypeface(typeface);
        this.o.setFonts(typeface);
        this.l.invalidateCalendar();
    }

    public void setNavLeftImage(@NonNull Drawable drawable) {
        this.i.setImageDrawable(drawable);
    }

    public void setNavRightImage(@NonNull Drawable drawable) {
        this.j.setImageDrawable(drawable);
    }

    public void setSelectedDateRange(@Nullable Calendar calendar, @Nullable Calendar calendar2) {
        if (calendar == null && calendar2 != null) {
            throw new RuntimeException("Start date cannot be null if you are setting end date.");
        }
        if (calendar2 != null && calendar2.before(calendar)) {
            throw new RuntimeException("Start date cannot be after end date.");
        }
    }

    public void setSelectionType(String str) {
        this.h = str;
    }

    public void setVisibleMonthRange(Calendar calendar, Calendar calendar2) {
        if (calendar != null) {
            calendar.set(5, 1);
            calendar.set(10, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            if (calendar2 != null) {
                calendar2.set(5, 1);
                calendar2.set(10, 0);
                calendar2.set(12, 0);
                calendar2.set(13, 0);
                calendar2.set(14, 0);
                if (!calendar.after(calendar2)) {
                    this.k.clear();
                    getMonthsListAndIndex();
                    this.l.notifyDataSetChanged();
                    return;
                }
                throw new IllegalArgumentException("Start month can not be greater than end month.");
            }
            throw new IllegalArgumentException("End month can not be null.");
        }
        throw new IllegalArgumentException("Start month can not be null.");
    }

    public void setWeekOffset(int i) {
        this.o.setWeekOffset(i);
        this.l.invalidateCalendar();
    }

    public DateRangeCalendarView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = new ArrayList();
        f(context, attributeSet);
    }

    public DateRangeCalendarView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = new ArrayList();
        f(context, attributeSet);
    }
}
