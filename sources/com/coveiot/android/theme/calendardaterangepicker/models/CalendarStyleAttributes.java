package com.coveiot.android.theme.calendardaterangepicker.models;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorInt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public interface CalendarStyleAttributes {
    @NotNull
    public static final Companion Companion = Companion.f6097a;
    public static final int DEFAULT_FIXED_DAYS_SELECTION = 7;

    /* loaded from: classes7.dex */
    public static final class Companion {
        public static final int DEFAULT_FIXED_DAYS_SELECTION = 7;

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ Companion f6097a = new Companion();
    }

    /* loaded from: classes7.dex */
    public enum DateSelectionMode {
        FREE_RANGE,
        SINGLE,
        FIXED_RANGE
    }

    @NotNull
    DateSelectionMode getDateSelectionMode();

    @ColorInt
    int getDefaultDateColor();

    @ColorInt
    int getDisableDateColor();

    int getFixedDaysSelectionNumber();

    @Nullable
    Typeface getFonts();

    @Nullable
    Drawable getHeaderBg();

    @ColorInt
    int getRangeDateColor();

    @ColorInt
    int getRangeStripColor();

    @ColorInt
    int getSelectedDateCircleColor();

    @ColorInt
    int getSelectedDateColor();

    float getTextSizeDate();

    float getTextSizeTitle();

    float getTextSizeWeek();

    @ColorInt
    int getTitleColor();

    @ColorInt
    int getWeekColor();

    int getWeekOffset();

    boolean isEditable();

    boolean isShouldEnabledTime();

    void setDateSelectionMode(@NotNull DateSelectionMode dateSelectionMode);

    void setEditable(boolean z);

    void setFixedDaysSelectionNumber(int i);

    void setFonts(@Nullable Typeface typeface);

    void setHeaderBg(@Nullable Drawable drawable);

    void setWeekOffset(int i);
}
