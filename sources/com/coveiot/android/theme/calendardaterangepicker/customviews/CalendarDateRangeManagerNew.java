package com.coveiot.android.theme.calendardaterangepicker.customviews;

import java.util.Calendar;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public interface CalendarDateRangeManagerNew {
    @NotNull
    public static final Companion Companion = Companion.f6090a;
    @NotNull
    public static final String DATE_FORMAT = "yyyyMMddHHmm";

    /* loaded from: classes7.dex */
    public static final class Companion {
        @NotNull
        public static final String DATE_FORMAT = "yyyyMMddHHmm";

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ Companion f6090a = new Companion();
    }

    /* loaded from: classes7.dex */
    public enum DateSelectionState {
        UNKNOWN,
        START_DATE,
        LAST_DATE,
        START_END_SAME,
        IN_SELECTED_RANGE
    }

    @NotNull
    DateSelectionState checkDateRange(@NotNull Calendar calendar);

    @NotNull
    Calendar getEndVisibleMonth();

    @Nullable
    Calendar getMaxSelectedDate();

    @Nullable
    Calendar getMinSelectedDate();

    int getMonthIndex(@NotNull Calendar calendar);

    @NotNull
    Calendar getStartVisibleMonth();

    @NotNull
    List<Calendar> getVisibleMonthDataList();

    boolean isSelectableDate(@NotNull Calendar calendar);

    void resetSelectedDateRange();

    void setSelectableDateRange(@NotNull Calendar calendar, @NotNull Calendar calendar2);

    void setSelectedDateRange(@NotNull Calendar calendar, @Nullable Calendar calendar2);

    void setVisibleMonths(@NotNull Calendar calendar, @NotNull Calendar calendar2);
}
