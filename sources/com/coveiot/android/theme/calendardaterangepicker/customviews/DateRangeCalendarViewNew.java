package com.coveiot.android.theme.calendardaterangepicker.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttrImplNew;
import com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class DateRangeCalendarViewNew extends LinearLayout implements DateRangeCalendarViewApi {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    public TextView h;
    public AppCompatImageView i;
    public AppCompatImageView j;
    public AdapterEventCalendarMonthsNew k;
    public Locale l;
    public ViewPager m;
    public CalendarStyleAttributes n;
    public CalendarDateRangeManagerNewImpl o;

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateRangeCalendarViewNew(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        c(context, null);
    }

    public static final void e(DateRangeCalendarViewNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ViewPager viewPager = this$0.m;
        ViewPager viewPager2 = null;
        if (viewPager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vpCalendar");
            viewPager = null;
        }
        int currentItem = viewPager.getCurrentItem() - 1;
        if (currentItem > -1) {
            ViewPager viewPager3 = this$0.m;
            if (viewPager3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("vpCalendar");
            } else {
                viewPager2 = viewPager3;
            }
            viewPager2.setCurrentItem(currentItem);
        }
    }

    public static final void f(DateRangeCalendarViewNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ViewPager viewPager = this$0.m;
        ViewPager viewPager2 = null;
        if (viewPager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vpCalendar");
            viewPager = null;
        }
        int currentItem = viewPager.getCurrentItem() + 1;
        CalendarDateRangeManagerNewImpl calendarDateRangeManagerNewImpl = this$0.o;
        if (calendarDateRangeManagerNewImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
            calendarDateRangeManagerNewImpl = null;
        }
        if (currentItem < calendarDateRangeManagerNewImpl.getVisibleMonthDataList().size()) {
            ViewPager viewPager3 = this$0.m;
            if (viewPager3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("vpCalendar");
            } else {
                viewPager2 = viewPager3;
            }
            viewPager2.setCurrentItem(currentItem);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCalendarYearTitle(int i) {
        CalendarDateRangeManagerNewImpl calendarDateRangeManagerNewImpl = this.o;
        CalendarStyleAttributes calendarStyleAttributes = null;
        if (calendarDateRangeManagerNewImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
            calendarDateRangeManagerNewImpl = null;
        }
        Calendar calendar = calendarDateRangeManagerNewImpl.getVisibleMonthDataList().get(i);
        Locale locale = this.l;
        if (locale == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locale");
            locale = null;
        }
        String dateText = new DateFormatSymbols(locale).getMonths()[calendar.get(2)];
        StringBuilder sb = new StringBuilder();
        Intrinsics.checkNotNullExpressionValue(dateText, "dateText");
        String substring = dateText.substring(0, 1);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        String upperCase = substring.toUpperCase();
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
        sb.append(upperCase);
        sb.append((Object) dateText.subSequence(1, dateText.length()));
        String str = sb.toString() + ' ' + calendar.get(1);
        TextView textView = this.h;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvYearTitle");
            textView = null;
        }
        textView.setText(str);
        TextView textView2 = this.h;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvYearTitle");
            textView2 = null;
        }
        CalendarStyleAttributes calendarStyleAttributes2 = this.n;
        if (calendarStyleAttributes2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
        } else {
            calendarStyleAttributes = calendarStyleAttributes2;
        }
        textView2.setTextColor(calendarStyleAttributes.getTitleColor());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setNavigationHeader(int i) {
        AppCompatImageView appCompatImageView = this.j;
        AppCompatImageView appCompatImageView2 = null;
        if (appCompatImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imgVNavRight");
            appCompatImageView = null;
        }
        appCompatImageView.setVisibility(0);
        AppCompatImageView appCompatImageView3 = this.i;
        if (appCompatImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imgVNavLeft");
            appCompatImageView3 = null;
        }
        appCompatImageView3.setVisibility(0);
        CalendarDateRangeManagerNewImpl calendarDateRangeManagerNewImpl = this.o;
        if (calendarDateRangeManagerNewImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
            calendarDateRangeManagerNewImpl = null;
        }
        if (calendarDateRangeManagerNewImpl.getVisibleMonthDataList().size() == 1) {
            AppCompatImageView appCompatImageView4 = this.i;
            if (appCompatImageView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("imgVNavLeft");
                appCompatImageView4 = null;
            }
            appCompatImageView4.setVisibility(4);
            AppCompatImageView appCompatImageView5 = this.j;
            if (appCompatImageView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("imgVNavRight");
            } else {
                appCompatImageView2 = appCompatImageView5;
            }
            appCompatImageView2.setVisibility(4);
        } else if (i == 0) {
            AppCompatImageView appCompatImageView6 = this.i;
            if (appCompatImageView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("imgVNavLeft");
            } else {
                appCompatImageView2 = appCompatImageView6;
            }
            appCompatImageView2.setVisibility(4);
        } else {
            CalendarDateRangeManagerNewImpl calendarDateRangeManagerNewImpl2 = this.o;
            if (calendarDateRangeManagerNewImpl2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
                calendarDateRangeManagerNewImpl2 = null;
            }
            if (i == calendarDateRangeManagerNewImpl2.getVisibleMonthDataList().size() - 1) {
                AppCompatImageView appCompatImageView7 = this.j;
                if (appCompatImageView7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("imgVNavRight");
                } else {
                    appCompatImageView2 = appCompatImageView7;
                }
                appCompatImageView2.setVisibility(4);
            }
        }
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    public final void c(Context context, AttributeSet attributeSet) {
        Locale locale = context.getResources().getConfiguration().locale;
        Intrinsics.checkNotNullExpressionValue(locale, "context.resources.configuration.locale");
        this.l = locale;
        this.n = new CalendarStyleAttrImplNew(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.layout_calendar_container_new, (ViewGroup) this, true);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rlHeaderCalendar);
        CalendarStyleAttributes calendarStyleAttributes = this.n;
        ViewPager viewPager = null;
        if (calendarStyleAttributes == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
            calendarStyleAttributes = null;
        }
        relativeLayout.setBackground(calendarStyleAttributes.getHeaderBg());
        View findViewById = findViewById(R.id.tvYearTitle);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.tvYearTitle)");
        TextView textView = (TextView) findViewById;
        this.h = textView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvYearTitle");
            textView = null;
        }
        CalendarStyleAttributes calendarStyleAttributes2 = this.n;
        if (calendarStyleAttributes2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
            calendarStyleAttributes2 = null;
        }
        textView.setTextSize(0, calendarStyleAttributes2.getTextSizeTitle());
        View findViewById2 = findViewById(R.id.imgVNavLeft);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(R.id.imgVNavLeft)");
        this.i = (AppCompatImageView) findViewById2;
        View findViewById3 = findViewById(R.id.imgVNavRight);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(R.id.imgVNavRight)");
        this.j = (AppCompatImageView) findViewById3;
        View findViewById4 = findViewById(R.id.vpCalendar);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(R.id.vpCalendar)");
        this.m = (ViewPager) findViewById4;
        Object clone = Calendar.getInstance().clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar = (Calendar) clone;
        calendar.add(2, -30);
        Object clone2 = Calendar.getInstance().clone();
        Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar2 = (Calendar) clone2;
        calendar2.add(2, 30);
        CalendarStyleAttributes calendarStyleAttributes3 = this.n;
        if (calendarStyleAttributes3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
            calendarStyleAttributes3 = null;
        }
        this.o = new CalendarDateRangeManagerNewImpl(calendar, calendar2, calendarStyleAttributes3);
        CalendarDateRangeManagerNewImpl calendarDateRangeManagerNewImpl = this.o;
        if (calendarDateRangeManagerNewImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
            calendarDateRangeManagerNewImpl = null;
        }
        CalendarStyleAttributes calendarStyleAttributes4 = this.n;
        if (calendarStyleAttributes4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
            calendarStyleAttributes4 = null;
        }
        this.k = new AdapterEventCalendarMonthsNew(context, calendarDateRangeManagerNewImpl, calendarStyleAttributes4);
        ViewPager viewPager2 = this.m;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vpCalendar");
            viewPager2 = null;
        }
        AdapterEventCalendarMonthsNew adapterEventCalendarMonthsNew = this.k;
        if (adapterEventCalendarMonthsNew == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonthsNew");
            adapterEventCalendarMonthsNew = null;
        }
        viewPager2.setAdapter(adapterEventCalendarMonthsNew);
        ViewPager viewPager3 = this.m;
        if (viewPager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vpCalendar");
            viewPager3 = null;
        }
        viewPager3.setOffscreenPageLimit(0);
        ViewPager viewPager4 = this.m;
        if (viewPager4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vpCalendar");
        } else {
            viewPager = viewPager4;
        }
        viewPager.setCurrentItem(30);
        setCalendarYearTitle(30);
        d();
    }

    public final void d() {
        ViewPager viewPager = this.m;
        AppCompatImageView appCompatImageView = null;
        if (viewPager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vpCalendar");
            viewPager = null;
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeCalendarViewNew$setListeners$1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                DateRangeCalendarViewNew.this.setCalendarYearTitle(i);
                DateRangeCalendarViewNew.this.setNavigationHeader(i);
            }
        });
        AppCompatImageView appCompatImageView2 = this.i;
        if (appCompatImageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imgVNavLeft");
            appCompatImageView2 = null;
        }
        appCompatImageView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.calendardaterangepicker.customviews.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DateRangeCalendarViewNew.e(DateRangeCalendarViewNew.this, view);
            }
        });
        AppCompatImageView appCompatImageView3 = this.j;
        if (appCompatImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imgVNavRight");
        } else {
            appCompatImageView = appCompatImageView3;
        }
        appCompatImageView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.calendardaterangepicker.customviews.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DateRangeCalendarViewNew.f(DateRangeCalendarViewNew.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    @Nullable
    public Calendar getEndDate() {
        CalendarDateRangeManagerNewImpl calendarDateRangeManagerNewImpl = this.o;
        if (calendarDateRangeManagerNewImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
            calendarDateRangeManagerNewImpl = null;
        }
        return calendarDateRangeManagerNewImpl.getMaxSelectedDate();
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    @Nullable
    public Calendar getStartDate() {
        CalendarDateRangeManagerNewImpl calendarDateRangeManagerNewImpl = this.o;
        if (calendarDateRangeManagerNewImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
            calendarDateRangeManagerNewImpl = null;
        }
        return calendarDateRangeManagerNewImpl.getMinSelectedDate();
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public boolean isEditable() {
        AdapterEventCalendarMonthsNew adapterEventCalendarMonthsNew = this.k;
        if (adapterEventCalendarMonthsNew == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonthsNew");
            adapterEventCalendarMonthsNew = null;
        }
        return adapterEventCalendarMonthsNew.isEditable();
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void resetAllSelectedViews() {
        CalendarDateRangeManagerNewImpl calendarDateRangeManagerNewImpl = this.o;
        AdapterEventCalendarMonthsNew adapterEventCalendarMonthsNew = null;
        if (calendarDateRangeManagerNewImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
            calendarDateRangeManagerNewImpl = null;
        }
        calendarDateRangeManagerNewImpl.resetSelectedDateRange();
        AdapterEventCalendarMonthsNew adapterEventCalendarMonthsNew2 = this.k;
        if (adapterEventCalendarMonthsNew2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonthsNew");
        } else {
            adapterEventCalendarMonthsNew = adapterEventCalendarMonthsNew2;
        }
        adapterEventCalendarMonthsNew.resetAllSelectedViews();
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setCalendarListener(@NotNull CalendarListenerNew calendarListenerNew) {
        Intrinsics.checkNotNullParameter(calendarListenerNew, "calendarListenerNew");
        AdapterEventCalendarMonthsNew adapterEventCalendarMonthsNew = this.k;
        if (adapterEventCalendarMonthsNew == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonthsNew");
            adapterEventCalendarMonthsNew = null;
        }
        adapterEventCalendarMonthsNew.setCalendarListener(calendarListenerNew);
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setCurrentMonth(@NotNull Calendar calendar) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        ViewPager viewPager = this.m;
        CalendarDateRangeManagerNewImpl calendarDateRangeManagerNewImpl = null;
        if (viewPager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vpCalendar");
            viewPager = null;
        }
        CalendarDateRangeManagerNewImpl calendarDateRangeManagerNewImpl2 = this.o;
        if (calendarDateRangeManagerNewImpl2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
        } else {
            calendarDateRangeManagerNewImpl = calendarDateRangeManagerNewImpl2;
        }
        viewPager.setCurrentItem(calendarDateRangeManagerNewImpl.getMonthIndex(calendar));
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setEditable(boolean z) {
        AdapterEventCalendarMonthsNew adapterEventCalendarMonthsNew = this.k;
        if (adapterEventCalendarMonthsNew == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonthsNew");
            adapterEventCalendarMonthsNew = null;
        }
        adapterEventCalendarMonthsNew.setEditable(z);
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setFixedDaysSelection(int i) {
        CalendarStyleAttributes calendarStyleAttributes = this.n;
        AdapterEventCalendarMonthsNew adapterEventCalendarMonthsNew = null;
        if (calendarStyleAttributes == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
            calendarStyleAttributes = null;
        }
        calendarStyleAttributes.setFixedDaysSelectionNumber(i);
        AdapterEventCalendarMonthsNew adapterEventCalendarMonthsNew2 = this.k;
        if (adapterEventCalendarMonthsNew2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonthsNew");
        } else {
            adapterEventCalendarMonthsNew = adapterEventCalendarMonthsNew2;
        }
        adapterEventCalendarMonthsNew.invalidateCalendar();
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setFonts(@NotNull Typeface fonts) {
        Intrinsics.checkNotNullParameter(fonts, "fonts");
        TextView textView = this.h;
        AdapterEventCalendarMonthsNew adapterEventCalendarMonthsNew = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvYearTitle");
            textView = null;
        }
        textView.setTypeface(fonts);
        CalendarStyleAttributes calendarStyleAttributes = this.n;
        if (calendarStyleAttributes == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
            calendarStyleAttributes = null;
        }
        calendarStyleAttributes.setFonts(fonts);
        AdapterEventCalendarMonthsNew adapterEventCalendarMonthsNew2 = this.k;
        if (adapterEventCalendarMonthsNew2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonthsNew");
        } else {
            adapterEventCalendarMonthsNew = adapterEventCalendarMonthsNew2;
        }
        adapterEventCalendarMonthsNew.invalidateCalendar();
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setNavLeftImage(@NotNull Drawable leftDrawable) {
        Intrinsics.checkNotNullParameter(leftDrawable, "leftDrawable");
        AppCompatImageView appCompatImageView = this.i;
        if (appCompatImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imgVNavLeft");
            appCompatImageView = null;
        }
        appCompatImageView.setImageDrawable(leftDrawable);
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setNavRightImage(@NotNull Drawable rightDrawable) {
        Intrinsics.checkNotNullParameter(rightDrawable, "rightDrawable");
        AppCompatImageView appCompatImageView = this.j;
        if (appCompatImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imgVNavRight");
            appCompatImageView = null;
        }
        appCompatImageView.setImageDrawable(rightDrawable);
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setSelectableDateRange(@NotNull Calendar startDate, @NotNull Calendar endDate) {
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        CalendarDateRangeManagerNewImpl calendarDateRangeManagerNewImpl = this.o;
        AdapterEventCalendarMonthsNew adapterEventCalendarMonthsNew = null;
        if (calendarDateRangeManagerNewImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
            calendarDateRangeManagerNewImpl = null;
        }
        calendarDateRangeManagerNewImpl.setSelectableDateRange(startDate, endDate);
        AdapterEventCalendarMonthsNew adapterEventCalendarMonthsNew2 = this.k;
        if (adapterEventCalendarMonthsNew2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonthsNew");
        } else {
            adapterEventCalendarMonthsNew = adapterEventCalendarMonthsNew2;
        }
        adapterEventCalendarMonthsNew.notifyDataSetChanged();
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setSelectedDateRange(@NotNull Calendar startDate, @NotNull Calendar endDate) {
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        CalendarDateRangeManagerNewImpl calendarDateRangeManagerNewImpl = this.o;
        AdapterEventCalendarMonthsNew adapterEventCalendarMonthsNew = null;
        if (calendarDateRangeManagerNewImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
            calendarDateRangeManagerNewImpl = null;
        }
        calendarDateRangeManagerNewImpl.setSelectedDateRange(startDate, endDate);
        AdapterEventCalendarMonthsNew adapterEventCalendarMonthsNew2 = this.k;
        if (adapterEventCalendarMonthsNew2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonthsNew");
        } else {
            adapterEventCalendarMonthsNew = adapterEventCalendarMonthsNew2;
        }
        adapterEventCalendarMonthsNew.notifyDataSetChanged();
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setVisibleMonthRange(@NotNull Calendar startMonth, @NotNull Calendar endMonth) {
        Intrinsics.checkNotNullParameter(startMonth, "startMonth");
        Intrinsics.checkNotNullParameter(endMonth, "endMonth");
        CalendarDateRangeManagerNewImpl calendarDateRangeManagerNewImpl = this.o;
        ViewPager viewPager = null;
        if (calendarDateRangeManagerNewImpl == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDateRangeCalendarManager");
            calendarDateRangeManagerNewImpl = null;
        }
        calendarDateRangeManagerNewImpl.setVisibleMonths(startMonth, endMonth);
        AdapterEventCalendarMonthsNew adapterEventCalendarMonthsNew = this.k;
        if (adapterEventCalendarMonthsNew == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonthsNew");
            adapterEventCalendarMonthsNew = null;
        }
        adapterEventCalendarMonthsNew.notifyDataSetChanged();
        ViewPager viewPager2 = this.m;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vpCalendar");
        } else {
            viewPager = viewPager2;
        }
        viewPager.setCurrentItem(0);
        setCalendarYearTitle(0);
        setNavigationHeader(0);
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeCalendarViewApi
    public void setWeekOffset(int i) {
        CalendarStyleAttributes calendarStyleAttributes = this.n;
        AdapterEventCalendarMonthsNew adapterEventCalendarMonthsNew = null;
        if (calendarStyleAttributes == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
            calendarStyleAttributes = null;
        }
        calendarStyleAttributes.setWeekOffset(i);
        AdapterEventCalendarMonthsNew adapterEventCalendarMonthsNew2 = this.k;
        if (adapterEventCalendarMonthsNew2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapterEventCalendarMonthsNew");
        } else {
            adapterEventCalendarMonthsNew = adapterEventCalendarMonthsNew2;
        }
        adapterEventCalendarMonthsNew.invalidateCalendar();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateRangeCalendarViewNew(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        c(context, attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateRangeCalendarViewNew(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        c(context, attributeSet);
    }
}
