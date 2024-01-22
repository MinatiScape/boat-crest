package com.coveiot.android.theme.calendardaterangepicker.customviews;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes;
import java.util.Calendar;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class AdapterEventCalendarMonthsNew extends PagerAdapter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public final Context c;
    @NotNull
    public final CalendarStyleAttributes d;
    @Nullable
    public CalendarListenerNew e;
    @NotNull
    public final CalendarDateRangeManagerNew f;
    @NotNull
    public final Handler g;
    @NotNull
    public final CalendarListenerNew h;

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public AdapterEventCalendarMonthsNew(@NotNull Context mContext, @NotNull CalendarDateRangeManagerNewImpl calendarDateRangeManager, @NotNull CalendarStyleAttributes calendarStyleAttr) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(calendarDateRangeManager, "calendarDateRangeManager");
        Intrinsics.checkNotNullParameter(calendarStyleAttr, "calendarStyleAttr");
        this.c = mContext;
        this.g = new Handler();
        this.h = new AdapterEventCalendarMonthsNew$calendarAdapterListener$1(this);
        this.f = calendarDateRangeManager;
        this.d = calendarStyleAttr;
    }

    public static final void d(AdapterEventCalendarMonthsNew this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.notifyDataSetChanged();
    }

    public final Calendar c(Calendar calendar) {
        Object clone = calendar.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar2 = (Calendar) clone;
        calendar2.set(5, 1);
        return calendar2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(@NotNull ViewGroup collection, int i, @NotNull Object view) {
        Intrinsics.checkNotNullParameter(collection, "collection");
        Intrinsics.checkNotNullParameter(view, "view");
        collection.removeView((View) view);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.f.getVisibleMonthDataList().size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(@NotNull Object object) {
        Intrinsics.checkNotNullParameter(object, "object");
        return -2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @NotNull
    public Object instantiateItem(@NotNull ViewGroup container, int i) {
        Intrinsics.checkNotNullParameter(container, "container");
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.layout_pager_month_new, container, false);
        Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type android.view.ViewGroup");
        ViewGroup viewGroup = (ViewGroup) inflate;
        View findViewById = viewGroup.findViewById(R.id.cvEventCalendarView);
        Intrinsics.checkNotNullExpressionValue(findViewById, "layout.findViewById(R.id.cvEventCalendarView)");
        DateRangeMonthViewNew dateRangeMonthViewNew = (DateRangeMonthViewNew) findViewById;
        dateRangeMonthViewNew.drawCalendarForMonth(this.d, c(this.f.getVisibleMonthDataList().get(i)), this.f);
        dateRangeMonthViewNew.setCalendarListener(this.h);
        container.addView(viewGroup);
        return viewGroup;
    }

    public final void invalidateCalendar() {
        this.g.postDelayed(new Runnable() { // from class: com.coveiot.android.theme.calendardaterangepicker.customviews.a
            @Override // java.lang.Runnable
            public final void run() {
                AdapterEventCalendarMonthsNew.d(AdapterEventCalendarMonthsNew.this);
            }
        }, 50L);
    }

    public final boolean isEditable() {
        return this.d.isEditable();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(@NotNull View view, @NotNull Object obj) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(obj, "obj");
        return view == obj;
    }

    public final void resetAllSelectedViews() {
        notifyDataSetChanged();
    }

    public final void setCalendarListener(@Nullable CalendarListenerNew calendarListenerNew) {
        this.e = calendarListenerNew;
    }

    public final void setEditable(boolean z) {
        this.d.setEditable(z);
        notifyDataSetChanged();
    }
}
