package com.coveiot.android.theme.calendardaterangepicker.customviews;

import android.util.Log;
import com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarDateRangeManagerNew;
import com.coveiot.android.theme.calendardaterangepicker.customviews.DateView;
import com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes;
import com.coveiot.android.theme.calendardaterangepicker.models.DateTimingNew;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class CalendarDateRangeManagerNewImpl implements CalendarDateRangeManagerNew {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final CalendarStyleAttributes f6091a;
    public Calendar b;
    public Calendar c;
    public Calendar d;
    public Calendar e;
    @Nullable
    public Calendar f;
    @Nullable
    public Calendar g;
    @NotNull
    public final List<Calendar> h;

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes7.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CalendarStyleAttributes.DateSelectionMode.values().length];
            try {
                iArr[CalendarStyleAttributes.DateSelectionMode.SINGLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CalendarStyleAttributes.DateSelectionMode.FIXED_RANGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CalendarStyleAttributes.DateSelectionMode.FREE_RANGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public CalendarDateRangeManagerNewImpl(@NotNull Calendar startMonthDate, @NotNull Calendar endMonthDate, @NotNull CalendarStyleAttributes calendarStyleAttributes) {
        Intrinsics.checkNotNullParameter(startMonthDate, "startMonthDate");
        Intrinsics.checkNotNullParameter(endMonthDate, "endMonthDate");
        Intrinsics.checkNotNullParameter(calendarStyleAttributes, "calendarStyleAttributes");
        this.f6091a = calendarStyleAttributes;
        this.h = new ArrayList();
        setVisibleMonths(startMonthDate, endMonthDate);
    }

    public final void a(Calendar calendar, Calendar calendar2) {
        calendar.after(calendar2);
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarDateRangeManagerNew
    @NotNull
    public CalendarDateRangeManagerNew.DateSelectionState checkDateRange(@NotNull Calendar selectedDate) {
        Intrinsics.checkNotNullParameter(selectedDate, "selectedDate");
        Calendar calendar = this.f;
        if (calendar != null && this.g != null) {
            DateView.Companion companion = DateView.Companion;
            long containerKey = companion.getContainerKey(selectedDate);
            Calendar calendar2 = this.f;
            Intrinsics.checkNotNull(calendar2);
            long containerKey2 = companion.getContainerKey(calendar2);
            Calendar calendar3 = this.g;
            Intrinsics.checkNotNull(calendar3);
            long containerKey3 = companion.getContainerKey(calendar3);
            Calendar calendar4 = this.f;
            Intrinsics.checkNotNull(calendar4);
            if (CalendarRangeUtilsKt.isDateSame(selectedDate, calendar4)) {
                Calendar calendar5 = this.g;
                Intrinsics.checkNotNull(calendar5);
                if (CalendarRangeUtilsKt.isDateSame(selectedDate, calendar5)) {
                    return CalendarDateRangeManagerNew.DateSelectionState.START_END_SAME;
                }
            }
            Calendar calendar6 = this.f;
            Intrinsics.checkNotNull(calendar6);
            if (CalendarRangeUtilsKt.isDateSame(selectedDate, calendar6)) {
                return CalendarDateRangeManagerNew.DateSelectionState.START_DATE;
            }
            Calendar calendar7 = this.g;
            Intrinsics.checkNotNull(calendar7);
            if (CalendarRangeUtilsKt.isDateSame(selectedDate, calendar7)) {
                return CalendarDateRangeManagerNew.DateSelectionState.LAST_DATE;
            }
            boolean z = false;
            if (containerKey2 <= containerKey && containerKey < containerKey3) {
                z = true;
            }
            if (z) {
                return CalendarDateRangeManagerNew.DateSelectionState.IN_SELECTED_RANGE;
            }
        } else if (calendar != null) {
            Intrinsics.checkNotNull(calendar);
            if (CalendarRangeUtilsKt.isDateSame(selectedDate, calendar)) {
                return CalendarDateRangeManagerNew.DateSelectionState.START_END_SAME;
            }
        }
        return CalendarDateRangeManagerNew.DateSelectionState.UNKNOWN;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarDateRangeManagerNew
    @NotNull
    public Calendar getEndVisibleMonth() {
        Calendar calendar = this.c;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEndVisibleMonth");
            return null;
        }
        return calendar;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarDateRangeManagerNew
    @Nullable
    public Calendar getMaxSelectedDate() {
        return this.g;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarDateRangeManagerNew
    @Nullable
    public Calendar getMinSelectedDate() {
        return this.f;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarDateRangeManagerNew
    public int getMonthIndex(@NotNull Calendar month) {
        Intrinsics.checkNotNullParameter(month, "month");
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            Calendar calendar = this.h.get(i);
            if (month.get(1) == calendar.get(1) && month.get(2) == calendar.get(2)) {
                return i;
            }
        }
        throw new IllegalStateException("Month(" + month.getTime() + ") is not available in the given month range.");
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarDateRangeManagerNew
    @NotNull
    public Calendar getStartVisibleMonth() {
        Calendar calendar = this.b;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mStartVisibleMonth");
            return null;
        }
        return calendar;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarDateRangeManagerNew
    @NotNull
    public List<Calendar> getVisibleMonthDataList() {
        return this.h;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarDateRangeManagerNew
    public boolean isSelectableDate(@NotNull Calendar date) {
        boolean z;
        Intrinsics.checkNotNullParameter(date, "date");
        Calendar calendar = this.d;
        Calendar calendar2 = null;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mStartSelectableDate");
            calendar = null;
        }
        if (!date.before(calendar)) {
            Calendar calendar3 = this.e;
            if (calendar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEndSelectableDate");
            } else {
                calendar2 = calendar3;
            }
            if (!date.after(calendar2)) {
                z = true;
                if (!z || checkDateRange(date) == CalendarDateRangeManagerNew.DateSelectionState.UNKNOWN) {
                    CalendarRangeUtilsKt.printDate(date);
                    CalendarRangeUtilsKt.printDate(this.f);
                    CalendarRangeUtilsKt.printDate(this.g);
                }
                return z;
            }
        }
        z = false;
        if (!z) {
        }
        CalendarRangeUtilsKt.printDate(date);
        CalendarRangeUtilsKt.printDate(this.f);
        CalendarRangeUtilsKt.printDate(this.g);
        return z;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarDateRangeManagerNew
    public void resetSelectedDateRange() {
        this.f = null;
        this.g = null;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarDateRangeManagerNew
    public void setSelectableDateRange(@NotNull Calendar startDate, @NotNull Calendar endDate) {
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        a(startDate, endDate);
        Object clone = startDate.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar = (Calendar) clone;
        this.d = calendar;
        Calendar calendar2 = null;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mStartSelectableDate");
            calendar = null;
        }
        CalendarRangeUtilsKt.resetTime(calendar, DateTimingNew.START);
        Object clone2 = endDate.clone();
        Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar3 = (Calendar) clone2;
        this.e = calendar3;
        if (calendar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEndSelectableDate");
            calendar3 = null;
        }
        if (calendar3.getTime().compareTo(Calendar.getInstance().getTime()) > 0) {
            Calendar calendar4 = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar4, "getInstance()");
            this.e = calendar4;
        }
        Calendar calendar5 = this.e;
        if (calendar5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEndSelectableDate");
            calendar5 = null;
        }
        CalendarRangeUtilsKt.resetTime(calendar5, DateTimingNew.END);
        Calendar calendar6 = this.d;
        if (calendar6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mStartSelectableDate");
            calendar6 = null;
        }
        Calendar calendar7 = this.b;
        if (calendar7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mStartVisibleMonth");
            calendar7 = null;
        }
        calendar6.before(calendar7);
        Calendar calendar8 = this.e;
        if (calendar8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEndSelectableDate");
            calendar8 = null;
        }
        Calendar calendar9 = this.c;
        if (calendar9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEndVisibleMonth");
        } else {
            calendar2 = calendar9;
        }
        calendar8.after(calendar2);
        resetSelectedDateRange();
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarDateRangeManagerNew
    public void setSelectedDateRange(@NotNull Calendar startDate, @Nullable Calendar calendar) {
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        a(startDate, calendar);
        Calendar calendar2 = this.d;
        if (calendar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mStartSelectableDate");
            calendar2 = null;
        }
        startDate.before(calendar2);
        boolean z = false;
        if (calendar != null) {
            Calendar calendar3 = this.e;
            if (calendar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEndSelectableDate");
                calendar3 = null;
            }
            if (calendar.after(calendar3)) {
                z = true;
            }
        }
        if (z) {
            calendar.setTime(Calendar.getInstance().getTime());
        }
        CalendarStyleAttributes.DateSelectionMode dateSelectionMode = this.f6091a.getDateSelectionMode();
        int i = WhenMappings.$EnumSwitchMapping$0[dateSelectionMode.ordinal()];
        if (i == 1) {
            Object clone = startDate.clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
            calendar = (Calendar) clone;
            Log.w("CDRManagerImpl", "End date is ignored due date selection mode: " + dateSelectionMode);
        } else if (i == 2) {
            Log.w("CDRManagerImpl", "End date is ignored due date selection mode: " + dateSelectionMode);
            Object clone2 = startDate.clone();
            Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.Calendar");
            calendar = (Calendar) clone2;
            calendar.add(5, this.f6091a.getFixedDaysSelectionNumber());
        } else if (i != 3) {
            throw new NoWhenBranchMatchedException();
        }
        Log.i("CDRManagerImpl", "Selected dates: Start(" + CalendarRangeUtilsKt.printDate(startDate) + ")-End(" + CalendarRangeUtilsKt.printDate(calendar) + ") for mode:" + dateSelectionMode);
        Object clone3 = startDate.clone();
        Intrinsics.checkNotNull(clone3, "null cannot be cast to non-null type java.util.Calendar");
        this.f = (Calendar) clone3;
        this.g = (Calendar) (calendar != null ? calendar.clone() : null);
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarDateRangeManagerNew
    public void setVisibleMonths(@NotNull Calendar startMonth, @NotNull Calendar endMonth) {
        Intrinsics.checkNotNullParameter(startMonth, "startMonth");
        Intrinsics.checkNotNullParameter(endMonth, "endMonth");
        a(startMonth, endMonth);
        Object clone = startMonth.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar = (Calendar) clone;
        Object clone2 = endMonth.clone();
        Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar2 = (Calendar) clone2;
        if (calendar2.getTime().compareTo(Calendar.getInstance().getTime()) > 0) {
            calendar2 = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
        }
        calendar.set(5, 1);
        DateTimingNew dateTimingNew = DateTimingNew.START;
        CalendarRangeUtilsKt.resetTime(calendar, dateTimingNew);
        calendar2.set(5, calendar2.getActualMaximum(5));
        DateTimingNew dateTimingNew2 = DateTimingNew.END;
        CalendarRangeUtilsKt.resetTime(calendar2, dateTimingNew2);
        Object clone3 = calendar.clone();
        Intrinsics.checkNotNull(clone3, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar3 = (Calendar) clone3;
        this.b = calendar3;
        Calendar calendar4 = null;
        if (calendar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mStartVisibleMonth");
            calendar3 = null;
        }
        CalendarRangeUtilsKt.resetTime(calendar3, dateTimingNew);
        Object clone4 = calendar2.clone();
        Intrinsics.checkNotNull(clone4, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar5 = (Calendar) clone4;
        this.c = calendar5;
        if (calendar5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEndVisibleMonth");
            calendar5 = null;
        }
        CalendarRangeUtilsKt.resetTime(calendar5, dateTimingNew2);
        this.h.clear();
        Calendar calendar6 = this.b;
        if (calendar6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mStartVisibleMonth");
            calendar6 = null;
        }
        Object clone5 = calendar6.clone();
        Intrinsics.checkNotNull(clone5, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar7 = (Calendar) clone5;
        while (true) {
            Calendar calendar8 = this.c;
            if (calendar8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEndVisibleMonth");
                calendar8 = null;
            }
            if (CalendarRangeUtilsKt.isMonthSame(calendar7, calendar8)) {
                break;
            }
            List<Calendar> list = this.h;
            Object clone6 = calendar7.clone();
            Intrinsics.checkNotNull(clone6, "null cannot be cast to non-null type java.util.Calendar");
            list.add((Calendar) clone6);
            calendar7.add(2, 1);
        }
        List<Calendar> list2 = this.h;
        Object clone7 = calendar7.clone();
        Intrinsics.checkNotNull(clone7, "null cannot be cast to non-null type java.util.Calendar");
        list2.add((Calendar) clone7);
        Calendar calendar9 = this.b;
        if (calendar9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mStartVisibleMonth");
            calendar9 = null;
        }
        Calendar calendar10 = this.c;
        if (calendar10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEndVisibleMonth");
        } else {
            calendar4 = calendar10;
        }
        setSelectableDateRange(calendar9, calendar4);
    }
}
