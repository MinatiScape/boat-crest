package com.coveiot.android.theme.calendardaterangepicker.customviews;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import java.util.Calendar;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public interface DateRangeCalendarViewApi {
    @Nullable
    Calendar getEndDate();

    @Nullable
    Calendar getStartDate();

    boolean isEditable();

    void resetAllSelectedViews();

    void setCalendarListener(@NotNull CalendarListenerNew calendarListenerNew);

    void setCurrentMonth(@NotNull Calendar calendar);

    void setEditable(boolean z);

    void setFixedDaysSelection(int i);

    void setFonts(@NotNull Typeface typeface);

    void setNavLeftImage(@NotNull Drawable drawable);

    void setNavRightImage(@NotNull Drawable drawable);

    void setSelectableDateRange(@NotNull Calendar calendar, @NotNull Calendar calendar2);

    void setSelectedDateRange(@NotNull Calendar calendar, @NotNull Calendar calendar2);

    void setVisibleMonthRange(@NotNull Calendar calendar, @NotNull Calendar calendar2);

    void setWeekOffset(int i);
}
